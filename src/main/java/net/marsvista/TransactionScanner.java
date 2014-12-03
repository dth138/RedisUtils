package net.marsvista;

import redis.clients.jedis.*;
import static redis.clients.jedis.ScanParams.SCAN_POINTER_START;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 12/10/14
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionScanner  {

    JedisPool pool;
    TreeSet<Transaction> transactions;
    HashMap<String, TreeSet<Transaction>> channelTransactions;

    public TransactionScanner() {
        this.pool = new JedisPool(new JedisPoolConfig(), "", 12155, 300, "", 0, "redislabs", false);
        transactions = new TreeSet<Transaction>();
        channelTransactions = new HashMap<String, TreeSet<Transaction>>();
    }

    void addChannelTransaction(Transaction t) {
        if (!channelTransactions.containsKey(t.channel)){
            channelTransactions.put(t.channel, new TreeSet<Transaction>());
        }
        channelTransactions.get(t.channel).add(t);
    }

    void key(String key) {
        Jedis jedis = pool.getResource();
        String value = jedis.get(key);
        Transaction t = new Transaction(value);
        addChannelTransaction(t);
        transactions.add(t);
//        System.out.println(t);
        pool.returnResource(jedis);
    }

    public void scan() {

        int processedCount = 0;

        try  {
            Jedis jedis = pool.getResource();

            ScanResult<String> rs;
            String cursor = SCAN_POINTER_START;
            ScanParams sp = new ScanParams();
            sp.match("*channel_*_payment*");

            do{
                rs = jedis.scan(cursor, sp);

                cursor = rs.getStringCursor();
                if (!cursor.equals(Integer.toString(rs.getCursor()))){
                    System.out.println("Failed to validate cursor");
                }
                List<String> keys = rs.getResult();
                for (String key : keys) {
                    key(key);
                    processedCount++;
                }
                System.out.println("Processed :" + processedCount);
            } while (!cursor.equals("0"));

        } catch (Exception e){
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
        }
        printTree();
    }

    void printTree() {
        System.out.println("--------------------------------");
        Transaction shouldBeStatic = new Transaction();
        System.out.println(shouldBeStatic.printHeader());
        for (String channel : channelTransactions.keySet()) {
            for (Transaction t : channelTransactions.get(channel)) {
                System.out.println(t);
            }
        }
    }

    public static void main(String args[]) {
        TransactionScanner scanner = new TransactionScanner();
        scanner.scan();
    }

}
