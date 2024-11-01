package threads.topics;

import threads.util.RunnableImpl;
import threads.util.WorkerThread;

public class first_ThreadAndRunnable {
    //time slicing is the OS feature to share processor time between different processes and threads.
    //Once we start any thread, it’s execution depends on the OS implementation of time slicing, and
    //we can’t control their execution. However, we can set threads priority but even then it
    //does not guarantee that higher priority thread will be executed first.

    //Context switching between threads is usually less expensive than between processes.
    //Thread intercommunication is relatively easier than process communication.

    public static void main(String[] args) {
//        Thread t1 = new WorkerThread();
//        Thread t2 = new WorkerThread();
//        Thread t3 = new WorkerThread();
//
//        t1.start();
//        t2.start();
//        t3.start();
////        Interrupts this thread, causing it to continue execution if it was blocked for any reason.
//        t3.interrupt();

//         |||

////        Implementing the runnable interface is a better option than extending the thread class
////        since we can extend only one class, but we can implement multiple interfaces in Java.
//        Runnable runnable = new RunnableImpl();
//
////        we can do this way because Runnable is a functional interface
////        		Runnable runnable = () -> {
////        			for (int i = 0; i <= 4; i++)
////        				System.out.println(Thread.currentThread().getName() + ": " + i);
////        		};
//
//        Thread t1 = new Thread(runnable);
//        Thread t2 = new Thread(runnable);
//        Thread t3 = new Thread(runnable);
//
//        t1.start();
//        t2.start();
//        t3.start();
    }
}

