package net.marsvista;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 12/2/14
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExactKeyCounter implements KeyCounter {
    Map keys;

    public ExactKeyCounter() {
        keys = new TreeMap<String, Integer>();
    }

    public int count(String key) {
        Integer count;
        if(keys.containsKey(key)) {
            keys.put(key, ((Integer)keys.get(key) + 1));
        } else {
            keys.put(key, 1);
        }
        return (Integer)keys.get(key);
    }

    public void printTree() {

        Set<String> keySet = keys.keySet();
        for (String k : keySet) {
            System.out.println("Key: " + k + " : " + keys.get(k));
        }
        return;
    }
}
