package ThreadExamples;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter implements Runnable {
    public static int count = 1;
    public String name;
    public static Lock lock = new ReentrantLock();
    public static CyclicBarrier blockLock = new CyclicBarrier(5);

    Counter(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (this.name.contains("Th3+")) {

            //add 3 to count
            try {
                lock.lock();
                count += 3;
                System.out.println(name + "-added 3 and current count:" + count);
            } finally {
                lock.unlock();
            }
            try {
//                entry for Thread set1 - say 5 threads
//                first set of threads to execute
                blockLock.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        } else if (this.name.contains("Th2x")) {
            //double the count
            try {
//                entry for Thread set2 - say 5 threads
//                waits for all set1 count to complete, that’s why added at the start
                blockLock.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            try {
                lock.lock();
                count *= 2;
                System.out.println(name + "-multiplied 2 and current count:" + count);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}


public class ThreadBarrierRaceCond {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService Th3pPool = Executors.newFixedThreadPool(10);
        ExecutorService Th2xPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            Th3pPool.submit(new Counter("Th3+" + i));
        }
        for (int i = 0; i < 5; i++) {
            Th2xPool.submit(new Counter("Th2x" + i));
        }

        Th3pPool.shutdown();
        Th2xPool.shutdown();
        Thread.sleep(2000);
        System.out.println("Final count value" + Counter.count);
    }
}
