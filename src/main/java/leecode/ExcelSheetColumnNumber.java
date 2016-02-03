package leecode;
/**
 * 
* @ClassName: ExcelSheetColumnNumber 
* @Description
* 
* Related to question Excel Sheet Column Title
    Given a column title as appear in an Excel sheet, return its corresponding column number.
    For example:
    
        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
* @author xiaoman 
* @date 2015年12月9日 上午10:46:58 
*
 */
public class ExcelSheetColumnNumber {
    public static int titleToNumber(String s) {
        int result = 0;
        for(int i=0;i<s.length();i++){
            result = (int) (result + char2Int(s.charAt(i))*Math.pow(26,s.length()-i-1));
        }
        
        return result;
    }
    
    private static int char2Int(char c){
        return (int)c - 64;
    }
    
    public static void main(String[] args) {
        System.out.println((int)'a');
        System.out.println((int)'A');
        System.out.println((int)'Z');
        
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("A"));
        System.out.println(titleToNumber("AAA"));
    }
}
