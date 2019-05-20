package leecode;

/**
 * 
 * 1047. Remove All Adjacent Duplicates In String
 * @author xiaomanwang
 *
 */
public class RemoveAdjacentDuplicates {
	
	public static String removeDuplicates(String S) {
        int i = 0;
        StringBuffer sb = new StringBuffer();
		while(true){
        		if(i == S.length()){
        			return sb.toString();
        		}
        		char c = S.charAt(i);
        		
    			if(sb.length() == 0 || sb.charAt(sb.length()-1) != c){
    				sb.append(c);
    			} else {
    				sb.deleteCharAt(sb.length() - 1);
    			}
    			i++;
		}
    }
	
	public static void main(String[] args) {
		System.out.println(removeDuplicates("abb"));
		System.out.println(removeDuplicates("abbacddc"));
		System.out.println(removeDuplicates("abbadacbaddc"));
	}
	
}
