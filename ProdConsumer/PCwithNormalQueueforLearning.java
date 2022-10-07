package ProdConsumer;

import java.util.PriorityQueue;
import java.util.Queue;



class Prod1 implements Runnable {
    Queue que;
    Prod1(Queue que){
        this.que=que;
    }
    //produce messages and add to queue
    @Override
    public void run() {
        try{
            for (int i=0;i<10;i++) {
                if (que.size()<5) {
                    System.out.println(que.add("message by Prod" + i));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Cons1 implements Runnable {
    Queue que;
    Cons1(Queue que){
        this.que=que;
    }
    //consume messages when the queue is not empty
    @Override
    public void run(){
        try{
            for (int i=0;i<5;i++) {
                while(que.isEmpty()!=true) {
                    System.out.println("Cons" + i + " consumed " + que.poll());
                    System.out.println(que);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


public class PCwithNormalQueueforLearning {
    public static void main(String[] args) throws InterruptedException {
        Queue que = new PriorityQueue(5);
        Thread P1 = new Thread(new Prod1(que));
        Thread C1 = new Thread(new Cons1(que));
        Thread C2 = new Thread(new Cons1(que));
        P1.start();
        C1.start();
        System.out.println(que);

    }

}


