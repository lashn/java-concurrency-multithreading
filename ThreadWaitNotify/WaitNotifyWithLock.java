package ThreadWaitNotify;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

class SharedFile implements Runnable{
    @Override
    public void run(){

    }
}


class Philosopher extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 5;

    public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        while(sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            secondChopstick.lock();

            // take a piece of sushi
            if (sushiCount > 0) {
                sushiCount--;
                System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
            }

            // put down chopsticks
            secondChopstick.unlock();
            firstChopstick.unlock();
        }
    }
}


public class WaitNotifyWithLock {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        create 10- files - write to them and read them
         */


//        ExecutorService fwThread = Executors.newFixedThreadPool(10);
//        fwThread.submit();
//
//        ExecutorService frThread = Executors.newFixedThreadPool(10);
//        frThread.submit();
////        Thread.sleep(1000);
//        fwThread.shutdown();
//        frThread.shutdown();
    }
}
