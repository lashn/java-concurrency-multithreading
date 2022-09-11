package ThreadExamples;


class Thread1 extends Thread {
    public void run()
    {
        System.out.println("running class thread");
        try{
            System.out.println("start sleeping class T1 thread count "+ Thread1.activeCount());
            Thread.sleep(2000);
            System.out.println("done sleeping class T1 thread  count "+ Thread1.activeCount());
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("class T1 thread done");
    }

}

public class JavaThreadClass {
    public static void main(String[] args) throws InterruptedException {
    // states: run, suspended, resume, blocked(waiting for a resource)
    Thread T1= new Thread1();
    System.out.println("MAIN: before start class T1 thread "+T1.getState());
    T1.start();

//    System.out.println("in main thread before sleep "+T1.getState());
//    Thread.sleep(1000);
    System.out.println("MAIN thread before join "+T1.getState());
//    {
//        T1.join();
//        System.out.println("MAIN thread join and waits with class T1 Thread");
//    }
    System.out.println("MAIN thread after join "+T1.getState());
    System.out.println("MAIN thread done");

    }
}
