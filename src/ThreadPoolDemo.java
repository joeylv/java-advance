import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> {
            System.out.println("Thread running");

        };
        executorService.execute(runnable);


        


    }
}
