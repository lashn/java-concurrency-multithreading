package ProdConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Prod implements Runnable {
    BlockingQueue que;
    Prod(BlockingQueue que){
        this.que=que;
    }
    //produce messages and add to queue
    @Override
    public void run() {
        try{
            for (int i=0;i<10;i++) {
                if (que.size()<5) {
//                    MyMessage
                    System.out.println(que.add("message by Prod" + i));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Cons implements Runnable {
    BlockingQueue que;
    Cons(BlockingQueue que){
        this.que=que;
    }
    //consume messages when the queue is not empty
    @Override
    public void run(){
        try{
            for (int i=0;i<20;i++) {
                while(que.isEmpty()!=true) {
                    System.out.println("Cons" + i + " consumed " + que.take());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


public class ProdConSingleThread {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue que = new ArrayBlockingQueue(5);
        Thread P1 = new Thread(new Prod(que));
        Thread C1 = new Thread(new Cons(que));
        Thread C2 = new Thread(new Cons(que));
        P1.start();
        C1.start();
//        System.out.println("at que);

    }

}

