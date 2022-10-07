package ThreadPoolEx;
import java.util.concurrent.*;

class ThreadPool{

}

public class ThreadPoolExecutor {
    public static void main(String[] args) {
//        Executor ThPool = new ThreadPoolExecutor();
        int numProc = Runtime.getRuntime().availableProcessors();
        Executor ThreadPool = Executors.newFixedThreadPool(numProc);

        }
    }

