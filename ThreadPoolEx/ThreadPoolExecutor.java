package ThreadPoolEx;
import java.util.concurrent.*;


public class ThreadPoolExecutor {
    public static void main(String[] args) {
//        Executor ThPool = new ThreadPoolExecutor();
        int numProcs = Runtime.getRuntime().availableProcessors();
        Executor ThreadPool = Executors.newFixedThreadPool(5);
        
        }
    }
}
