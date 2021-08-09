package leecode;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintFoobarAlternately {
    private int n;
    private AtomicInteger ai = new AtomicInteger(1);

    public PrintFoobarAlternately(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (ai.get() > 2*n) {
                    return;
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                if (ai.get() % 2 == 1) {
                    printFoo.run();
                    ai.incrementAndGet();
                }
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (ai.get() > 2*n) {
                    return;
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                if (ai.get() % 2 == 0) {
                    printBar.run();
                    ai.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintFoobarAlternately fbt = new PrintFoobarAlternately(10);
        Runnable printFoo = () -> System.out.print("foo");
        Runnable printBar = () -> System.out.println("bar");
        new Thread(() -> {
            try {
                fbt.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fbt.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
