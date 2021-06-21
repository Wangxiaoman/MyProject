package leecode;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest{
    private static int num = 3;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> System.out.println("---------"));
    private static ExecutorService executorService = Executors.newFixedThreadPool(num);
    
    public static void main(String[] args) {
        System.out.println("main thread in");
        executorService.submit(()->{
            try {
                System.out.println("A 1--");
                cyclicBarrier.await();
                System.out.println("A 2--");
                cyclicBarrier.await();
                System.out.println("A 3--");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        executorService.submit(()->{
            try {
                System.out.println("B 1--");
                cyclicBarrier.await();
                System.out.println("B 2--");
                cyclicBarrier.await();
                System.out.println("B 3--");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        executorService.submit(()->{
            try {
                System.out.println("C 1--");
                cyclicBarrier.await();
                System.out.println("C 2--");
                cyclicBarrier.await();
                System.out.println("C 3--");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        executorService.shutdown();
    }
}
