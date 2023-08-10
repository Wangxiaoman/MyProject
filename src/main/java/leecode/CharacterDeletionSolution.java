package leecode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaomanwang
 * 给出两个字符串 str 和 sub，你的任务是在 str 中完全删除那些在 sub 中存在的字符。
 */
public class CharacterDeletionSolution {
    /**
     * @param str: The first string given
     * @param sub: The given second string
     * @return: Returns the deleted string
     */
    public String characterDeletion(String str, String sub) {
        // write your code here
        Set<Character> cs = new HashSet<>();
        for (int i = 0; i < sub.length(); i++) {
            cs.add(sub.charAt(i));
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (!cs.contains(str.charAt(i))) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
}
