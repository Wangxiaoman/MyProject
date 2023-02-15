package leecode;

import java.util.concurrent.CountDownLatch;

public class OrderThreadLoop {
    private static int num = 10;
    private static CountDownLatch o1 = new CountDownLatch(num);
    private static CountDownLatch o2 = new CountDownLatch(num);
    private static CountDownLatch o3 = new CountDownLatch(num);
    
    public static void main(String[] args) {
        Runnable r1 = () -> {
            while(o1.getCount() > 0) {
                if(o1.getCount() == o2.getCount() && o1.getCount() == o3.getCount()) {
                    System.out.print(o1.getCount()+":1");
                    o1.countDown();
                }
            }
        };
        
        Runnable r2 = () -> {
            while(o2.getCount() > 0) {
                if(o1.getCount()+1 == o2.getCount() && o1.getCount()+1 == o3.getCount()) {
                    System.out.print("2");
                    o2.countDown();
                }
            }
        };
        
        Runnable r3 = () -> {
            while(o3.getCount() > 0) {
                if(o1.getCount() == o2.getCount() && o2.getCount()+1 == o3.getCount()) {
                    System.out.println("3");
                    o3.countDown();
                }
            }
        };
        
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
    }
}
