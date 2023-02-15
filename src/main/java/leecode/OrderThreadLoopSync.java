package leecode;

public class OrderThreadLoopSync {
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        
        OrderSyncThread ta = new OrderSyncThread("a", c, a, 10);
        OrderSyncThread tb = new OrderSyncThread("b", a, b, 10);
        OrderSyncThread tc = new OrderSyncThread("c", b, c, 10);
        
        new Thread(ta).start();
        Thread.sleep(10);
        new Thread(tb).start();
        Thread.sleep(10);
        new Thread(tc).start();
    }
}


class OrderSyncThread implements Runnable {

    private String name;
    private Object prev;
    private Object self;
    private Integer printCount;

    public OrderSyncThread(String name, Object prev, Object self, Integer printCount) {
        this.name = name;
        this.prev = prev;
        this.self = self;
        this.printCount = printCount;
    }

    @Override
    public void run() {
        while (printCount > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.println(name);
                    printCount--;
                    self.notifyAll();
                }
                try {
                    if(printCount == 0) {
                        prev.notify();
                    } else {
                        prev.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
