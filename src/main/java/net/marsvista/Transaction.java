package net.marsvista;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 12/10/14
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction implements Comparable<Transaction> {

    String channel;
    String paymentId;
    long timestamp;
    double fromAmount;
    double toAmount;
    String fromCurrency;
    String toCurrency;
    String game;
    String user;

    public String toString() {
        return channel + "," + user + "," + paymentId + "," + timestamp + "," + fromCurrency + "," + fromAmount + "," + toCurrency + "," + toAmount + "," + game;
    }

    public String printHeader() {
        return "channel, user, payment-id, timestamp, fromCurrency, fromAmount, toCurrency, toAmount, game";
    }

    public Transaction() {

    }

    public Transaction(String t) {

        JSONParser parser = new JSONParser();

        try {
            Map<String, Object> p = (Map)parser.parse(t);
            this.channel = (String) p.get("channel");
            this.paymentId = (String) p.get("payment");
            this.user = (String) p.get("user");
            this.timestamp = (Long) p.get("rx_timestamp");
            this.fromAmount = Double.parseDouble((String)p.get("fromAmount"));
            this.fromCurrency = (String) p.get("fromCurrency");
            this.toAmount = Integer.parseInt((String)p.get("toAmount"));
            this.toCurrency = (String) p.get("toCurrency");
            this.game = (String) p.get("game");
        } catch (Exception e) {
            System.out.println("Failed to parse:" + e.getMessage());
        }

        return;
    }

    public int compareTo(Transaction t) {
        if (this.timestamp > t.timestamp) {
            return 1;
        } else if (this.timestamp < t.timestamp) {
            return -1;
        } else {
            return 0;
        }
    }


}
