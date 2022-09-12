package ProdConsumer;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;

class Producer implements Runnable {
    ArrayBlockingQueue que;
    Producer(ArrayBlockingQueue que){
        this.que=que;
    }
    //produce messages and add to queue
    @Override
    public void run() {
        String ThreadName = printThread();
        que.add("message by "+ThreadName);
    }
    public String printThread(){
        Thread t = Thread.currentThread();
        return t.getName();
    }
}

class Consumer implements Runnable {
    ArrayBlockingQueue que;
    Consumer(ArrayBlockingQueue que){
        this.que=que;
    }
    //consume messages when the queue is not empty
    @Override
    public void run(){
        String ThreadName = printThread();
//        message = que.poll();
        System.out.println(ThreadName+" consumed "+que.poll());
    }
    public String printThread(){
        Thread t = Thread.currentThread();
        return t.getName();
    }
}

public class ProducerConsumer {
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
        for (int i=0;i<4;i++){
            Thread P1 = new Thread(new Producer(que));
            P1.start();
//            Thread.sleep(2000);
        }
        System.out.println(que);
        for (int i=0;i<5;i++){
            Thread C1 = new Thread(new Consumer(que));
            C1.start();
//            Thread.sleep(2000);
        }
        System.out.println(que);
    }

}