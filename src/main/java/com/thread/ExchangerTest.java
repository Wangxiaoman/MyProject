package com.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 
 * @author xiaoman
 * 
 * 交换两个线程的数据
 * 如果伙伴节点没有到达，那么这个线程会挂起；
 * 如果伙伴节点已经到达，那么唤醒这个线程，进行数据交换；
 * 如果当前线程被中断或者等待超时，那么抛出异常
 *
 */

public class ExchangerTest {
    public static void main(String[] args) {
        List<String> producerBuffer = new ArrayList<>();
        List<String> consumerBuffer = new ArrayList<>();
        
        Exchanger<List<String>> exchanger = new Exchanger<>();
        
        Thread p = new Thread(new ExchangerProducer(producerBuffer,exchanger));
        Thread c = new Thread(new ExchangerConsumer(consumerBuffer,exchanger));
        
        p.start();
        c.start();
    }
}

class ExchangerProducer implements Runnable{
    
    private List<String> buffer;
    private Exchanger<List<String>> exchanger;
    
    public ExchangerProducer(List<String> buffer,Exchanger<List<String>> exchanger){
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Producer : Cycle :" + i);
            for(int j = 0 ; j < 10 ; j++){
                String message = "Event " + ((i * 10 ) + j);
                System.out.println("Producer : " + message);
                buffer.add(message);
            }
            
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("Producer : buffer size :" + buffer.size());
        }
    }
}

class ExchangerConsumer implements Runnable{
    
    private List<String> buffer;
    private Exchanger<List<String>> exchanger;
    
    public ExchangerConsumer(List<String> buffer,Exchanger<List<String>> exchanger){
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("Consumer : buffer size :" + buffer.size());
            Iterator<String> iter = buffer.iterator();
            while(iter.hasNext()){
                System.out.println("Consumer buffer : " + iter.next());
                iter.remove();
            }
        }
    }
}
