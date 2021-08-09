package leecode;

public class FibonacciNumber {

    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[] fibs = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i < 2) {
                fibs[i] = i;
            } else {
                fibs[i] = fibs[i - 1] + fibs[i - 2];
            }
        }
        return fibs[n];
    }
    
    public static int fib1(int n) {
        if(n < 2) {
            return n;
        }
        if(n == 2) {
            return 1;
        }
        int pre = 1,cur = 1;
        for (int i = 3; i <= n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }

    public static void main(String[] args) {
        // System.out.println(fib(0));
        // System.out.println(fib(1));
        // System.out.println(fib(2));
        System.out.println(fib(4));
        System.out.println(fib1(4));
        System.out.println(fib1(3));
        System.out.println(fib1(2));
    }
}
