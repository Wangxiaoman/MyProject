package leecode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ThreadOrder {
    public static void main(String[] args) {
        Foo1 foo = new Foo1();
        Runnable printFirst = () -> System.out.println("first");
        Runnable printSecond = () -> System.out.println("second");
        Runnable printThird = () -> System.out.println("third");
        new Thread(() -> {
            try {
                foo.third(printThird);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.first(printFirst);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.second(printSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class Foo1 {
    private Semaphore firstSecond = new Semaphore(0);
    private Semaphore secondThead = new Semaphore(0);

    public Foo1() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstSecond.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstSecond.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondThead.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondThead.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo {
    private CountDownLatch firstSecond = new CountDownLatch(1);
    private CountDownLatch secondThead = new CountDownLatch(1);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstSecond.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstSecond.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondThead.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondThead.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
