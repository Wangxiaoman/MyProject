package leecode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class FizzBuzzThread {
    private AtomicInteger initCounter = new AtomicInteger(1);
    private int n;

    public FizzBuzzThread(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (initCounter.get() > n) {
                    return;
                }
                if (initCounter.get() % 3 == 0 && initCounter.get() % 15 != 0) {
                    printFizz.run();
                    initCounter.incrementAndGet();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (initCounter.get() > n) {
                    return;
                }
                if (initCounter.get() % 5 == 0 && initCounter.get() % 15 != 0) {
                    printBuzz.run();
                    initCounter.incrementAndGet();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (initCounter.get() > n) {
                    return;
                }
                if (initCounter.get() % 15 == 0) {
                    printFizzBuzz.run();
                    initCounter.incrementAndGet();
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (initCounter.get() > n) {
                    return;
                }
                if (initCounter.get() % 3 != 0 && initCounter.get() % 5 != 0) {
                    printNumber.accept(initCounter.get());
                    initCounter.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzzThread fbt = new FizzBuzzThread(18);
        Runnable printFizz = () -> System.out.println("fizz");
        Runnable printBuzz = () -> System.out.println("buzz");
        Runnable printFizzBuzz = () -> System.out.println("fizzbuzz");
        IntConsumer printNumber = i -> System.out.println(i);
        new Thread(() -> {
            try {
                fbt.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fbt.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fbt.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fbt.number(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
