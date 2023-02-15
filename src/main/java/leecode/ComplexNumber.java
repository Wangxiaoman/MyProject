package leecode;

public class ComplexNumber {
    public static String complexNumberMultiply(String num1, String num2) {
        String[] complexNum1 = num1.split("\\+");
        String[] complexNum2 = num2.split("\\+");
        int num1Int = Integer.valueOf(complexNum1[0]);
        int num1Imaginary = Integer.valueOf(complexNum1[1].replace("i", ""));
        
        int num2Int = Integer.valueOf(complexNum2[0]);
        int num2Imaginary = Integer.valueOf(complexNum2[1].replace("i", ""));
        
        int numInt = num1Int*num2Int - num1Imaginary*num2Imaginary;
        int numImaginary = num1Int*num2Imaginary + num2Int*num1Imaginary;
        
        return numInt + "+" + numImaginary + "i";
    }
    
    public static void main(String[] args) {
        System.out.println(complexNumberMultiply("1+1i","1+1i"));
        System.out.println(complexNumberMultiply("1+-1i","1+-1i"));
        System.out.println(complexNumberMultiply("1+-1i","0+0i"));
    }
}
