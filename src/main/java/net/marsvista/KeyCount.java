package net.marsvista;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 12/2/14
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeyCount implements Comparator<KeyCount>, Comparable<KeyCount> {
    String key;
    int count;

    public KeyCount(String name) {
        count = 1;
        this.key = name;
    }

    public String getKey() {
        return key;
    }

    public int getCount() {
        return count;
    }

    public int incr() {
        count += 1;
        return count;
    }

    public int compareTo(KeyCount key) {

        return 0;
    }

    public int compare(KeyCount a, KeyCount b) {
        return 1;
    }


}
