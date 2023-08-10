package leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaomanwang
 * 给定一副牌，每张牌上都写着一个整数。
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。仅当你可选的 X >= 2 时返回 true。
 */
public class HasGroupsSizeXSolution {
    /**
     * @param deck: a integer array
     * @return: return a value of bool
     */
    public boolean hasGroupsSizeX(List<Integer> deck) {
        if(deck.size() <= 1) {
            return false;
        }
        
        Map<Integer,Integer> map = new HashMap<>();
        deck.stream().forEach(i -> {
            Integer c = map.getOrDefault(i, 0);
            map.put(i, c+1);
        });
        
        boolean countEquals = true;
        boolean last2 = true;
        List<Integer> values = new ArrayList<>(map.values());
        for(int i=1;i< values.size();i++) {
            if(values.get(i) != values.get(i-1)) {
                countEquals = false;
            }
            if (values.get(i) % 2 == 1) {
                last2 = false;
            }
        }
        if(countEquals) return true;
        return last2;
    }
    
    
    
    public static void main(String[] args) {
        
    }
}
