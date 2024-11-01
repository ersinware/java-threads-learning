package threads.topics;

import threads.util.DesynchronizedRunnable;
import threads.util.SynchronizedRunnable;

public class fifth_ThreadSafety {
    //the source: https://www.journaldev.com/1061/thread-safety-in-java

    public static void main(String[] args) {
//        problematicPoint();
//        explanations();
//        synchronizedRunnable();
//        deadLockAndSynchronizationExamples();
    }

    private static void problematicPoint() {
        DesynchronizedRunnable desynchronizedRunnable = new DesynchronizedRunnable();

        Thread threadOne = new Thread(desynchronizedRunnable, "threadOne");
        Thread threadTwo = new Thread(desynchronizedRunnable, "threadTwo");

        threadOne.start();
        threadTwo.start();

        //wait for threads to finish processing
        try {
            threadOne.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Processing count: " + desynchronizedRunnable.getCount());
    }

    private static void explanations() {
        // ||| suggestions

        //The reason for data inconsistency is that updating any field value is not an atomic
        //process, it requires three steps; first to read the current value, second to do the
        //necessary operations to get the updated value and third to assign the updated value to the
        //field reference.
        //
        //Thread safety in java is the process to make our program safe to use in multithreaded
        //environment, there are different ways through which we can make our program thread safe.
        //
        //Synchronization is the easiest and most widely used tool for thread safety in java.
        //
        //Use of Atomic Wrapper classes from java.util.concurrent.atomic package. For example AtomicInteger
        //
        //Use of locks from java.util.concurrent.locks package.
        //
        //Using thread safe collection classes

        // ||| volatile keyword, visibility and atomicity

        // ||||

        //As Trying as indicated, volatile deals only with visibility.
        //
        //Consider this snippet in a concurrent environment:
        //boolean isStopped = false;
        //		while (!isStopped) {
        //			// do some kind of work
        //		}
        //
        //The idea here is that some thread could change the value of isStopped from false to true in
        //order to indicate to the subsequent loop that it is time to stop looping.
        //
        //Intuitively, there is no problem. Logically if another thread makes isStopped equal to
        //true, then the loop must terminate. The reality is that the loop will likely never
        //terminate even if another thread makes isStopped equal to true.
        //
        //The reason for this is not intuitive, but consider that modern processors have multiple
        //cores and that each core has multiple registers and multiple levels of cache memory that
        //are not accessible to other processors. In other words, values that are cached in one
        //processor's local memory are not visible to threads executing on a different processor.
        //Herein lies one of the central problems with concurrency: visibility.
        //
        //The Java Memory Model makes no guarantees whatsoever about when changes that are made to a
        //variable in one thread may become visible to other threads. In order to guarantee that
        //updates are visible as soon as they are made, you must synchronize.
        //
        //The volatile keyword is a weak form of synchronization. While it does nothing for mutual
        //exclusion or atomicity, it does provide a guarantee that changes made to a variable in one
        //thread will become visible to other threads as soon as it is made. Because individual
        //reads and writes to variables that are not 8-bytes are atomic in Java, declaring variables
        //volatile provides an easy mechanism for providing visibility in situations where there are
        //no other atomicity or mutual exclusion requirements.

        // ||||

        //Assume i = 0
        //Thread A reads i, calculates i+1, which is 1
        //Thread B sets i to 1000 and returns
        //Thread A now sets i to the result of the operation, which is i = 1
        //
        //Volatile and Atomic are two different concepts. Volatile ensures, that a certain, expected
        //(memory) state is true across different threads, while Atomics ensure that operation on
        //variables are performed atomically.
        //
        //Threads may create local copies of variables, and the JVM can reorder code to optimize it
        //volatile only ensures, that at the moment of access of such a variable, the new value will
        //be immediately visible to all other threads and the order of execution ensures, that the
        //code is at the state you would expect it to be.

        // ||| important points

        //Synchronization is the tool using which we can achieve thread-safety, JVM guarantees that
        //synchronized code will be executed by only one thread at a time. java keyword synchronized
        //is used to create synchronized code, and internally it uses locks on Object or Class to
        //make sure only one thread is executing the synchronized code.

        //Java synchronization works on locking and unlocking of the resource before any thread
        //enters into synchronized code, it has to acquire the lock on the Object and when code
        //execution ends, it unlocks the resource that can be locked by other threads. In the
        //meantime, other threads are in wait state to lock the synchronized resource.

        //We can use synchronized keyword in two ways, one is to make a complete method synchronized
        //and another way is to create synchronized block.

        // !!!
        //When a method is synchronized, it locks the Object, if method is static it locks the Class,
        //so it’s always best practice to use synchronized block to lock the only sections of method
        //that needs synchronization.

        //While creating a synchronized block, we need to provide the resource on which lock will be
        //acquired, it can be XYZ.class or any Object field of the class.

        //synchronized(this) will lock the Object before entering into the synchronized block.

        //You should use the lowest level of locking, for example, if there are multiple synchronized
        //block in a class and one of them is locking the Object, then other synchronized blocks
        //will also be not available for execution by other threads. When we lock an Object, it
        //acquires a lock on all the fields of the Object.

        //Java Synchronization provides data integrity on the cost of performance, so it should be
        //used only when it’s absolutely necessary.

        //Java Synchronization works only in the same JVM, so if you need to lock some resource in
        //multiple JVM environment, it will not work and you might have to look after some global
        //locking mechanism.

        //Java Synchronization could result in deadlocks, check this post about deadlock in java and
        //how to avoid them. https://www.journaldev.com/1058/deadlock-in-java-example

        //Java synchronized keyword cannot be used for constructors and variables.

        // !!!
        //It is preferable to create a dummy private Object to use for the synchronized block so that
        //it’s reference can’t be changed by any other code. For example, if you have a setter
        //method for Object on which you are synchronizing, it’s reference can be changed by some
        //other code leads to the parallel execution of the synchronized block.

        //We should not use any object that is maintained in a constant pool, for example String
        //should not be used for synchronization because if any other code is also locking on same
        //String, it will try to acquire lock on the same reference object from String pool and even
        //though both the codes are unrelated, they will lock each other.
        //https://www.journaldev.com/797/what-is-java-string-pool
    }

    private static void synchronizedRunnable() {
        SynchronizedRunnable synchronizedRunnable = new SynchronizedRunnable();

        Thread threadOne = new Thread(synchronizedRunnable, "threadOne");
        Thread threadTwo = new Thread(synchronizedRunnable, "threadTwo");

        threadOne.start();
        threadTwo.start();

        //wait for threads to finish processing
        try {
            threadOne.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Processing count: " + synchronizedRunnable.getCount());
    }

    private static void deadLockAndSynchronizationExamples() {
        // |||

        //public class MyObject {
        //
        //  // Locks on the object's monitor
        //  public synchronized void doSomething() {
        //    // ...
        //  }
        //}
        //
        //// Hackers code
        //MyObject myObject = new MyObject();
        //synchronized (myObject) {
        //  while (true) {
        //    // Indefinitely delay myObject
        //    Thread.sleep(Integer.MAX_VALUE);
        //  }
        //}
        //
        //Notice that hacker’s code is trying to lock the myObject instance and once it gets the
        //lock, it’s never releasing it causing doSomething() method to block on waiting for the
        //lock, this will cause the system to go on deadlock and cause Denial of Service (DoS).

        // |||

        //public class MyObject {
        //  //locks on the class object's monitor
        //  public static synchronized void doSomething() {
        //    // ...
        //  }
        //}
        //
        //// hackers code
        //synchronized (MyObject.class) {
        //  while (true) {
        //    Thread.sleep(Integer.MAX_VALUE); // Indefinitely delay MyObject
        //  }
        //}
        //
        //Notice that hacker code is getting a lock on the class monitor and not releasing it, it
        //will cause deadlock and DoS in the system.

        // |||

        //Here is another example where multiple threads are working on the same array of Strings and
        //once processed, appending thread name to the array value.
        //
        //
        //package com.journaldev.threads;
        //
        //import java.util.Arrays;
        //
        //public class SyncronizedMethod {
        //
        //    public static void main(String[] args) throws InterruptedException {
        //        String[] arr = {"1","2","3","4","5","6"};
        //        HashMapProcessor hmp = new HashMapProcessor(arr);
        //        Thread t1=new Thread(hmp, "t1");
        //        Thread t2=new Thread(hmp, "t2");
        //        Thread t3=new Thread(hmp, "t3");
        //        long start = System.currentTimeMillis();
        //        //start all the threads
        //        t1.start();t2.start();t3.start();
        //        //wait for threads to finish
        //        t1.join();t2.join();t3.join();
        //        System.out.println("Time taken= "+(System.currentTimeMillis()-start));
        //        //check the shared variable value now
        //        System.out.println(Arrays.asList(hmp.getMap()));
        //    }
        //
        //}
        //
        //class HashMapProcessor implements Runnable{
        //
        //    private String[] strArr = null;
        //
        //    public HashMapProcessor(String[] m){
        //        this.strArr=m;
        //    }
        //
        //    public String[] getMap() {
        //        return strArr;
        //    }
        //
        //    @Override
        //    public void run() {
        //        processArr(Thread.currentThread().getName());
        //    }
        //
        //    private void processArr(String name) {
        //        for(int i=0; i < strArr.length; i++){
        //            //process data and append thread name
        //            processSomething(i);
        //            addThreadName(i, name);
        //        }
        //    }
        //
        //    private void addThreadName(int i, String name) {
        //        strArr[i] = strArr[i] +":"+name;
        //    }
        //
        //    private void processSomething(int index) {
        //        // processing some job
        //        try {
        //            Thread.sleep(index*1000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //
        //}
        //
        //Here is the output when I run the above program.
        //
        //
        //Time taken= 15005
        //[1:t2:t3, 2:t1, 3:t3, 4:t1:t3, 5:t2:t1, 6:t3]
        //
        //The String array values are corrupted because of shared data and no synchronization. Here
        //is how we can change addThreadName() method to make our program thread-safe.
        //
        //    private Object lock = new Object();
        //    private void addThreadName(int i, String name) {
        //        synchronized(lock){
        //        strArr[i] = strArr[i] +":"+name;
        //        }
        //    }
        //
        //After this change, our program works fine and here is the correct output of the program.
        //
        //Time taken= 15004
        //[1:t1:t2:t3, 2:t2:t1:t3, 3:t2:t3:t1, 4:t3:t2:t1, 5:t2:t1:t3, 6:t2:t1:t3]
    }
}

