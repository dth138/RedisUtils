package net.marsvista;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 12/3/14
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergingKeyCounter implements KeyCounter {

    Map<String, Integer> keys;

    public MergingKeyCounter() {
        keys = new HashMap<String, Integer>();
    }



    public int count(String key) {
        //Assumptions:
        //Delimiter is : or _



        return 0;
    }


    public void printTree() {

    }
}
