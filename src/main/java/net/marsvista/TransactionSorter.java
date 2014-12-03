package net.marsvista;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 12/10/14
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionSorter implements Comparator<Transaction> {

    public int compare(Transaction a, Transaction b) {
        if (a.timestamp > b.timestamp) {
            return 1;
        } else if (a.timestamp < b.timestamp) {
            return -1;
        } else {
            return 0;
        }
    }
}
