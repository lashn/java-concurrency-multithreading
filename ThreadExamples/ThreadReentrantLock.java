package ThreadExamples;

import java.util.concurrent.locks.ReentrantLock;

class Rlock extends Thread {
    static ReentrantLock relock = new ReentrantLock();
    String name;
    static int share = 5_000_000;

    Rlock(String name) {
        this.name = name;
    }

    public void run() {
        relock.lock();

        try {
            for (int i=0; i<2_500_000;i++) {
                this.share--;
                System.out.println("Thread:" + name + " decremented share, curr value:" + share);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            relock.unlock();
            System.out.println("class "+name+" thread done");
        }
    }
}

public class ThreadReentrantLock {
    public static void main(String[] args){
        Thread T1 = new Rlock("T1");
        Thread T2 = new Rlock("T2");
        T1.start();
        T2.start();
    }
}
