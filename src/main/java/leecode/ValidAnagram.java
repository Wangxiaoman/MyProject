package leecode;

import java.util.Arrays;

/**
 * 
* @ClassName: ValidAnagram 
* @Description: 
* Given two strings s and t, write a function to determine if t is an anagram of s.
	For example,
	s = "anagram", t = "nagaram", return true.	
	s = "rat", t = "car", return false.
	
* @author xiaoman 
* @date 2015年12月7日 下午5:59:16 
*
 */
public class ValidAnagram {
	public static boolean isAnagram(String s, String t) {
		if((s != null && t==null) ||(s == null && t != null) || (s.length() != t.length())){
			return false;
		}
		
		char[] sa = s.toCharArray();
		char[] ta = t.toCharArray();
		
		Arrays.sort(sa);
		Arrays.sort(ta);
		
		for(int i=0;i<sa.length;i++){
			if(sa[i] != ta[i]){
				return false;
			}
		}
		//return ta.toString().equals(sa.toString());
		
		return true;
    }
	
	
	//利用char对应int，不过处理中文会有问题
	public static boolean isAnagram1(String s, String t) {
        if(s.length() != t.length()) return false;
        int ALL_CHARS = 255;
        int[] chars = new int[ALL_CHARS];
        for(int i = 0; i < s.length(); ++i) {
            chars[s.charAt(i)]++;
            chars[t.charAt(i)]--;
        }
        for(int i = 0; i < ALL_CHARS; ++i) {
            if(chars[i] != 0) return false;
        }
        return true;
    }
	
	
	
	public static void main(String[] args) {
		String s = "asdfasf1我的";
		String t = "asdfa1我的sf";
		
		System.out.println(isAnagram(t,s));
	}
}
