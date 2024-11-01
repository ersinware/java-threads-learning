package threads.util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class ThreadLocalRunnable implements Runnable {

    //bu nesne, her thread için ayrı ayrı yaratılacak
    //SimpleDateFormat is not thread-safe, so give one to each thread
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    @Override
    public void run() {
        System.out.println("thread name: " + Thread.currentThread().getName() + ", default formatter:" + formatter.get().toPattern());

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(new SimpleDateFormat());

        System.out.println("thread name: " + Thread.currentThread().getName() + ", changed formatter:" + formatter.get().toPattern());
    }
}
