package leecode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

// number 950
/**
 * 
输入：[17,13,11,2,3,5,7]
输出：[2,13,3,11,5,17,7]
 * 
 * @author xiaomanwang
 *
 */
public class RevealCards {

    public static int[] deckRevealedIncreasing(int[] deck) {
        if(deck.length < 3) {
            return deck;
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i : deck) {
            list.add(i);
        }
        Collections.sort(list);
        int[] result = new int[deck.length];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(list.get(list.size() - 1));
        stack.push(list.get(list.size() - 2));
        for (int i = list.size() - 3; i >= 0; i--) {
            int last = stack.pollLast();
            stack.push(last);
            stack.push(list.get(i));
        }

        int i = 0;
        for (int j : stack) {
            result[i++] = j;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = deckRevealedIncreasing(new int[] {17, 13, 11, 2, 3, 5, 7});
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
