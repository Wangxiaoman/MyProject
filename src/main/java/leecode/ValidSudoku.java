package leecode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaomanwang
 * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
 * 
 *
 */

public class ValidSudoku {
    
    private static Set<Character> getNumbers(){
        Set<Character> set = new HashSet<>();
        set.add('1');
        set.add('2');
        set.add('3');
        set.add('4');
        set.add('5');
        set.add('6');
        set.add('7');
        set.add('8');
        return set;
    }
    
//    public static boolean isValidSudoku(char[][] board) {
        
//    }
    
    public static boolean row(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> set = getNumbers();
            for (int j = 0; j < 9; j++) {
                boolean exist = set.remove(board[i][j]);
                if(!exist) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean column(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> set = getNumbers();
            for (int j = 0; j < 9; j++) {
                boolean exist = set.remove(board[j][i]);
                if(!exist) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        
    }
}
