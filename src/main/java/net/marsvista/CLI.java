eackage net.marsvista;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 12/1/14
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CLI {

    public static void main(String args[]) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost", 6379, 10, "");
        KeyCounter keyCounter = new KnownPrefixCounter();

        try  {
            Jedis jedis = pool.getResource();

            ScanResult<String> rs; // = jedis.scan("0");
            String cursor = "0";
            do{
                rs = jedis.scan(cursor);
                cursor = rs.getStringCursor();
                List<String> keys = rs.getResult();
                for (String key : keys) {
                    keyCounter.count(key);
                }
            } while (!cursor.equals("0"));

        } catch (Exception e){
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
        }

        keyCounter.printTree();
    }


}
