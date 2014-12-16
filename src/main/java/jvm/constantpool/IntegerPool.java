package jvm.constantpool;

public class IntegerPool {
	public boolean boolean_value = true;  
    public byte byte_value = 33;  
    public short short_value = 1;  
    public char char_vlaue = 'a';  
    public int int_value = 234567;  
    public long long_value = 100L;  
    public float float_value = 3.33F;  
    public double double_value = 3.1415;  
      
    public String ss = "12345";  
	public static void main(String[] args) {
		Integer i1 = 127;
		Integer i2 = 127;
		System.out.println(i1 == i2);
	}
}
