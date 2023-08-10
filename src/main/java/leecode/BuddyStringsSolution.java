package leecode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuddyStringsSolution {
    /**
     * @param a: string A
     * @param b: string B
     * @return: bool
     */
    public boolean buddyStrings(String a, String b) {
        if (a.length() <= 1 || b.length() <= 1 || a.length() != b.length()) {
            return false;
        }
        if (a.equals(b)) {
            Set<Character> cs = new HashSet<>();
            for (int i = 0; i < a.length(); i++) {
                cs.add(a.charAt(i));
            }
            return cs.size() < a.length();
        } else {
            List<Integer> clist = new ArrayList<>();
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    clist.add(i);
                }
            }
            if (clist.size() != 2) {
                return false;
            } else {
                return a.charAt(clist.get(0)) == b.charAt(clist.get(1))
                        && a.charAt(clist.get(1)) == b.charAt(clist.get(0));
            }
        }
    }
}
