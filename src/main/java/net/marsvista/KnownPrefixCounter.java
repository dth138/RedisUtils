package net.marsvista;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 12/3/14
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class KnownPrefixCounter implements KeyCounter {
    Map keys;

    Set<String> prefixes;
    Set<String> unknownKeys;

    public KnownPrefixCounter() {
        keys = new TreeMap<String, Integer>();
        unknownKeys = new HashSet<String>();
        prefixes = new HashSet<String>();

        prefixes.add("PARTY_MEMBERS:h:");
        prefixes.add("global_s_wallet_");
        prefixes.add("FRIEND_META");
        prefixes.add("FR_INVITE_SENT");
        prefixes.add("FR_INVITE_PENDING");
        prefixes.add("FRIENDS:z:");
        prefixes.add("NF:");
        prefixes.add("NFQ:");
        prefixes.add("PARTY:h");
        prefixes.add("PARTY_MEMBERS");
        prefixes.add("itdep301_h_player");
        prefixes.add("itdep301_h_session");
        prefixes.add("local_h_leaderboard_");
        prefixes.add("local_h_player");
        prefixes.add("local_h_session");
        prefixes.add("local_s_fb_access_token");
        prefixes.add("local_z_leaderboard_");
    }

    public int count(String key) {
        for (String pre : prefixes) {
            if (key.startsWith(pre)) {
                if(keys.containsKey(pre)) {
                    keys.put(pre, ((Integer)keys.get(pre) + 1));
                } else {
                    keys.put(pre, 1);
                }
                return (Integer)keys.get(pre);
            }
        }

        unknownKeys.add(key);
        return 0;
    }

    public void printTree() {
        Set<String> keySet = keys.keySet();
        for (String k : keySet) {
            System.out.println("Key: " + k + " : " + keys.get(k));
        }

        for (String u : unknownKeys){
            System.out.println("UnknownKey: " + u);
        }

        return;
    }
}
