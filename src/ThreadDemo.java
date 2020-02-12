import java.io.IOException;

public class ThreadDemo {

    public static Object lock = new Object();

    public static void main(String[] args) throws IOException, InterruptedException {
        //ThreadYield
//        ThreadYield("thread1").start();
//        ThreadYield("thread2").start();

        //ThreadSleep
//        ThreadSleep("thread1").start();
//        ThreadSleep("thread2").start();


        //ThreadJoin
        Thread thread1 = ThreadSleepNoLock("thread1");
        Thread thread2 = ThreadSleepNoLock("thread2");

        thread1.start();


        thread1.join();
        System.out.println("等待线程thread1执行完毕/结束");
        thread2.start();
        thread2.join();
        System.out.println("等待线程thread2执行完毕/结束");


        ObjectWait("thread1").start();
        ObjectWait("thread2").start();
        System.in.read();

    }


    private static Thread ThreadYield(String threadName) {
        return new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + ":::" + i);
                if (i == 5) {
                    Thread.yield();
                }
            }
        }, threadName);

    }
    private static Thread ObjectWait(String threadName) {
        return new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ":::" + i);
                    lock.notifyAll();
                    if (i == 10) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "::: start waiting");
                            lock.wait();
                            System.out.println(Thread.currentThread().getName() + "::: end waiting");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, threadName);

    }

    private static Thread ThreadSleep(String threadName) {
        return new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ":::" + i);
                    if (i == 10) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "::: start sleeping");
                            Thread.sleep(1000L);
                            System.out.println(Thread.currentThread().getName() + "::: end sleeping");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

        }, threadName);

    }

    private static Thread ThreadSleepNoLock(String threadName) {
        return new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + ":::" + i);
                if (i == 10) {
                    try {
//                        System.out.println(Thread.currentThread().getName() + "::: start sleeping");
                        Thread.sleep(1000L);
//                        System.out.println(Thread.currentThread().getName() + "::: end sleeping");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }


        }, threadName);

    }

}
