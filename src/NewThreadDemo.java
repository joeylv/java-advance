import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1. 使用Runnable实现
        Runnable runnable = () -> {
            System.out.println("Thread running");

        };
        Thread runThread = new Thread(runnable);
        runThread.start();

        //2. 继承Thread实现
        Thread thread = new MyThread();
        thread.start();


        //3. 使用Callable实现

        Callable<String> callable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        String result = futureTask.get();
        System.out.println(result);


    }

    // 继承Thread实现
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread Running");
        }
    }

    //实现Callable接口实现
    public static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("calling");
            return "calling";
        }
    }


}
