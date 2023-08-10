package leecode;

public class StretchWordSolution {
    /**
     * @param s: the string
     * @return: The numbers of strings
     */
    public static long stretchWord(String s) {
        int twoMore = 0;
        char lastChar = s.charAt(0);
        int charNum = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == lastChar) {
                charNum++;
            } else {
                if (charNum >= 2) {
                    twoMore++;
                }
                lastChar = c;
                charNum = 1;
            }
            
            if(i == s.length()-1 && charNum>=2) {
                twoMore++;
            }
        }
        return Double.valueOf(Math.pow(2, twoMore)).longValue();
    }
    
    public static void main(String[] args) {
        System.out.println(stretchWord("aaabbbcccdddeeeffffggghhhiiiijjjjjkkkkkeeeeefffffvvvvvvddddddqqqqqqeeeeerrrrr"));
        System.out.println(Math.pow(2, 18));
    }
}
