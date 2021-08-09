package leecode;

import java.util.concurrent.CountDownLatch;

public class OrderThread {
    private static CountDownLatch o1 = new CountDownLatch(1);
    private static CountDownLatch o2 = new CountDownLatch(1);
    
    public static void main(String[] args) {
        Runnable r1 = () -> {
            System.out.println("1");
            o1.countDown();
        };
        
        Runnable r2 = () -> {
            try {
                o1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2");
            o2.countDown();
        };
        
        Runnable r3 = () -> {
            try {
                o2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3");
        };
        
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
    }
}
