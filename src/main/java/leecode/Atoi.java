package leecode;

public class Atoi {
	public static int atoi(String str) {
		str = str.trim();
		if(str == null || str.equals(""))
            return 0;
		
        boolean plus = true;
        if(str.length()>1 && (str.charAt(0)=='+' || str.charAt(0)=='-')){
        	plus = str.charAt(0)=='+'?true:false;
        	
        	if(str.length()>2 && (str.charAt(1)=='+' || str.charAt(1)=='-')){
        		return 0;
        	}
        }
        
        str = str.replace("-", "").replace("+", "");
        
        for(int i=0;i<str.length();i++){
			int stri = (int)str.charAt(i);
			if(stri<48 || stri>57 || stri==' '){
				if(i == 0){
					return 0;
				}
				str = str.substring(0,i);
			}
		}
        
        if(plus){
        	try{
        		return Integer.valueOf(str);
        	}catch(Exception e){
        		return Integer.MAX_VALUE;
        	}
        }else{
        	try{
        		return Integer.valueOf("-"+str);
        	}catch(Exception e){
        		return Integer.MIN_VALUE;
        	}
        }
    }
	
	public static void main(String[] args) {
		System.out.println(atoi("--1123123"));
		System.out.println(atoi("-1123123"));
		System.out.println(atoi("-+01123123"));
		System.out.println(atoi("+-1123123"));
		System.out.println(atoi("++1123123"));
		System.out.println(atoi("2147483648"));
		System.out.println(atoi("-0012a42"));
		System.out.println(atoi("-11919730356x"));
		System.out.println(atoi("123  456"));
		
		System.out.println(Integer.valueOf("-0012"));
	}
}
