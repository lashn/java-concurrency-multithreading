package ThreadExamples;

class SyncMethod extends Thread {
    String name;
    static int share = 5_000_000;
    //ensure to use static so that class intrinsic lock makes it all obj's to update same share variable above
    //private below to avoid access from other classes
    private static synchronized void decShare() {
        share--;
    }
    SyncMethod(String name) {
        this.name = name;
    }
    public void run() {
        for (int i = 0; i < 2_500_000; i++) {
            try {
                decShare();
                System.out.println("Thread:" + name + " decremented share, curr value:" + share);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class SynchronizedThreadMethod {
    public static void main(String[] args) throws InterruptedException {
        Thread T1 = new SyncMethod("T1");
        Thread T2 = new SyncMethod("T2");
        T1.start();
        T2.start();
    }
}
