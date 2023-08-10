package leecode;

public class MaskingPersonalInformation {
    /**
     * @param s: personal information string S
     * @return: the correct "mask" 
     */
    public static String maskingPersonalInformation(String s) {
        // Write your code here.
        if(s.contains("@")) {
            int index = s.indexOf("@");
            s = s.toLowerCase();
            return s.substring(0,1) +"*****"+ s.substring(index-1,index) + s.substring(index);
        } else {
            String phone = s.replace("+", "").replace("-", "").replace("(", "").replace(")", "").replace(" ","");
            if(phone.length() == 10) {
                return "***-***-"+phone.substring(6,10);
            } else {
                String prefix = "+*";
                if(phone.length() == 12) prefix = "+**";
                if(phone.length() == 13) prefix = "+***";
                return prefix + "-***-***-"+phone.substring(phone.length()-4,phone.length());
            }
        }
    }
    
    public static void main(String[] args) {
        String s = "abc@aa.com";
        int index = s.indexOf("@");
        System.out.println("index:"+index);
        System.out.println(maskingPersonalInformation("1(234)567-890"));
        System.out.println(maskingPersonalInformation("86-(10)12345678"));
    }
}
