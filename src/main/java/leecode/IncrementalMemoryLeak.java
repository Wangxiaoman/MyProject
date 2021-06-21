package leecode;

// 1860. 增长的内存泄露
public class IncrementalMemoryLeak {
    public static int[] memLeak(int memory1, int memory2) {
        int[] result = new int[3];
        for(int i=1;;i++) {
            result[0] = i;
            result[1] = memory1;
            result[2] = memory2;
            if(memory1 >= memory2) {
                if(memory1 < i) {
                    return result;
                } else {
                    memory1 = memory1 - i;
                }
            } else {
                if(memory2 < i) {
                    return result;
                } else {
                    memory2 = memory2 - i;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] result = memLeak(8,11);
        for(int i : result) {
            System.out.print(i + " ");
        }
    }
}
