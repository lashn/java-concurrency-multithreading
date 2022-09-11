package ThreadExamples;

class ThreadA extends Thread {
    String name;
    ThreadA(String name)
    {
        this.name=name;
    }
    public void run() {
        System.out.println("running class thread: "+name);
        try {
            System.out.println("start sleeping class "+name+" thread count " + ThreadA.activeCount());
            Thread.sleep(2000);
//            System.out.println("done sleeping class "+ThreadA.name+" thread  count " + ThreadA.activeCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("class "+name+" thread done");
    }

}

public class ThreadAttributes {
    public static void main(String[] args) throws InterruptedException {
        // states: run, suspended, resume, blocked(waiting for a resource)
        Thread TA1 = new ThreadA("TA1");
        TA1.start();
        System.out.println("MAIN: TA1 thread state " + TA1.getState() + " active count " +ThreadA.activeCount());
//        TA1.join();
//        Thread.sleep(3000);

        Thread TA2 = new ThreadA("TA2");
        System.out.println("MAIN: TA2 thread state " + TA2.getState() + " active count " +ThreadA.activeCount());
        TA2.start();
//        TA2.start();


        TA2.join();
        TA1.join();

//        System.out.println("MAIN thread after join " + TA1.getState());
        System.out.println("MAIN thread done");

    }
}
