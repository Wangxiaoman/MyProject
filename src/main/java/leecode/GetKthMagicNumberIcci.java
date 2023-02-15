package leecode;
/**
 * 面试题 17.09. 第 k 个数
 * @author xiaomanwang
 * 
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21
 *
 */
public class GetKthMagicNumberIcci {
    public static int getKthMagicNumber(int k) {
        if(k == 1) {
            return 1;
        }
        int count = 1;
        for (int i = 1;; i = i + 2) {
            if(divide(i)) {
                if(count ++ == k) {
                    return i;
                }
            }
        }
    }
    
    private static boolean divide(int num) {
        if(num == 1) {
            return true;
        }
        int last3 = num % 3;
        int last5 = num % 5;
        int last7 = num % 7;
        
        if(last3 == 0) {
            return divide(num/3);
        }
        
        if(last5 == 0) {
            return divide(num/5);
        }
        
        if(last7 == 0) {
            return divide(num/7);
        }
        
        return false;
    }
    
    
    public static int getKthMagicNumber1(int k) {
        int [] result = new int[k];
        result[0] = 1;
        // 定义三个 指针，分别表示 resultA、B、C 的下标
        int point3 = 0;
        int point5 = 0;
        int point7 = 0;
        for (int i = 1; i < k; i++) {
            int resultN = Math.min(Math.min(result[point3] * 3, result[point5] * 5), result[point7] * 7);
            if (resultN % 3 == 0) {
                point3++;
            }
            if (resultN % 5 == 0) {
                point5++;
            }
            if (resultN % 7 == 0) {
                point7++;
            }
            result[i] = resultN;
            System.out.println("i="+i+",resultN="+resultN+",point3="+point3+",point5="+point5+",point7="+point7);
        }
        return result[k - 1];
    }
    
    public static void main(String[] args) {
//        System.out.println(getKthMagicNumber(5));
//        System.out.println(getKthMagicNumber(7));
//        System.out.println(getKthMagicNumber(17));
        
        getKthMagicNumber1(15);
    }
}
