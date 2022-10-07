package ProdConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.*;
//optimization over src/ProdConsumer/ProducerConsumer.java which tries to consume when queue is empty


class Producer1 implements Runnable {
    ArrayBlockingQueue que;
    static Lock lock;
    Condition prodcondition;
    Condition conscondition;
    Producer1(ArrayBlockingQueue que, Lock lock, Condition prodcondition,Condition conscondition){
        this.que=que;
        this.lock=lock;
        this.prodcondition=prodcondition;
        this.conscondition=conscondition;
    }
    //produce messages and add to queue
    @Override
    public void run() {
        String ThreadName = printThread();
        lock.lock();
        //queue is full, so wait - spurious wakeups are also handled
        while(que.size()==5){
            try {
                System.out.println(ThreadName+" waiting in producer block | que size: "+que.size());
                System.out.println(que);
                prodcondition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        que.add("message by "+ThreadName);
        //wait and notify methods can also be used to achieve same functionality
        conscondition.signal();
        lock.unlock();
    }
    public String printThread(){
        Thread t = Thread.currentThread();
        return t.getName();
    }
}

class Consumer1 implements Runnable {
    ArrayBlockingQueue que;
    static Lock lock;
    Condition prodcondition;
    Condition conscondition;
    Consumer1(ArrayBlockingQueue que, Lock lock, Condition prodcondition,Condition conscondition){
        this.que=que;
        this.lock=lock;
        this.prodcondition=prodcondition;
        this.conscondition=conscondition;
    }
    //consume messages when the queue is not empty
    @Override
    public void run(){
        String ThreadName = printThread();
        lock.lock();
        //queue is full, so wait - spurious wakeups are also handled
        while(que.isEmpty()){
            try {
                System.out.println(ThreadName+" waiting in consumer block | que size: "+que.size());
                System.out.println(que);
                conscondition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(ThreadName+" consumed "+que.poll());
        prodcondition.signal();
        lock.unlock();
    }
    public String printThread(){
        Thread t = Thread.currentThread();
        return t.getName();
    }
}

public class ProducerConsumerBlockingLock {
    /*bufferoverflow - when producer produces more than queue length - data will be lost
    Average rate of production < Average rate of consumption
    If no queue - Mutex + condition variable to introduce thread safe queue
    Some languages - provide unbounded queue - indefinite length using linkedlist - but still limited by h/w memory
    Rules:
    1.Ensure mutual exclusion of producers and consumers
    2.Prevent producers from trying to add data to a full queue
    3.Prevent consumers from trying to remove data from a empty queue/buffer
     */

    public static void main(String[] args) throws InterruptedException {
        //using a blocking queue which is thread safe
        ArrayBlockingQueue que = new ArrayBlockingQueue(5);
        Lock lock = new ReentrantLock();
        Condition prodcondition = lock.newCondition();
        Condition conscondition = lock.newCondition();



        for (int i=0;i<25;i++){
            Thread P1 = new Thread(new Producer1(que,lock,prodcondition,conscondition));
            P1.start();
//            Thread.sleep(2000);
        }
        System.out.println(que);
        for (int i=0;i<25;i++){
            Thread C1 = new Thread(new Consumer1(que,lock,prodcondition,conscondition));
            C1.start();
//            Thread.sleep(2000);
        }
    }
}
