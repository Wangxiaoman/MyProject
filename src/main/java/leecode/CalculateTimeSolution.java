package leecode;

import java.util.HashMap;
import java.util.Map;

public class CalculateTimeSolution {
    /**
     * @param keyboard: Customized keyboard strings
     * @param word: A string
     * @return: Total number of moves
     */
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            keyMap.put(keyboard.charAt(i), i);
        }

        int count = keyMap.get(word.charAt(0));
        for (int i = 1; i < word.length(); i++) {
            count += Math.abs(keyMap.get(word.charAt(i)) - keyMap.get(word.charAt(i-1)));
        }
        return count;
    }
}
