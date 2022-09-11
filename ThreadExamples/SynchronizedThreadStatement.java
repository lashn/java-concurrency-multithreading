package ThreadExamples;

class SyncStatement extends Thread {
    String name;
    static int share = 5_000_000;
    //ensure to use static so that class intrinsic lock makes it all obj's to update same share variable above

    SyncStatement(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 2_500_000; i++) {
            try {
                //synchronized (this)- each object will create their own instances and the result will be unexpected
                //synchronized (share) - with int being primitive type is not supported so we need to use Integer object
                //with Integer share - result will be unexpected as these are immutable
                synchronized (SyncStatement.class) {
                    share--;
                }
                System.out.println("Thread:" + name + " decremented share, curr value:" + share);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class SynchronizedThreadStatement {
    public static void main(String[] args) {
        Thread T1 = new SyncStatement("T1");
        Thread T2 = new SyncStatement("T2");
        T1.start();
        T2.start();

    }
}
