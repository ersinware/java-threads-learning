package threads.topics;

import threads.util.CyclicDeadLockRunnable;

public class ninth_CyclicDeadLock {
	//jcmd ile deadlock'ları tesipt edebilirsin
	//jcmd PID Thread.print

	//How to avoid deadlock in java
	//
	//These are some of the guidelines using which we can avoid most of the deadlock situations.
	//
	//Avoid Nested Locks: This is the most common reason for deadlocks, avoid locking another
	//resource if you already hold one. It’s almost impossible to get deadlock situation if you
	//are working with only one object lock. For example, here is the another implementation of
	//run() method without nested lock and program runs successfully without deadlock situation.
	//
	//	public void run() {
	//		String name = Thread.currentThread().getName();
	//		System.out.println(name + " acquiring lock on " + obj1);
	//		synchronized (obj1) {
	//			System.out.println(name + " acquired lock on " + obj1);
	//			work();
	//		}
	//		System.out.println(name + " released lock on " + obj1);
	//		System.out.println(name + " acquiring lock on " + obj2);
	//		synchronized (obj2) {
	//			System.out.println(name + " acquired lock on " + obj2);
	//			work();
	//		}
	//		System.out.println(name + " released lock on " + obj2);
	//
	//		System.out.println(name + " finished execution.");
	//	}
	//
	//Lock Only What is Required: You should acquire lock only on the resources you have to work
	//on, for example in above program I am locking the complete Object resource but if we are
	//only interested in one of it’s fields, then we should lock only that specific field not
	//complete object.

	//Avoid waiting indefinitely: You can get deadlock if two threads are waiting for each other to
	//finish indefinitely using thread join. If your thread has to wait for another thread to
	//finish, it’s always best to use join with maximum time you want to wait for thread to finish

	public static void main(String[] args) throws InterruptedException {
		Object obj1 = new Object();
		Object obj2 = new Object();
		//Object obj3 = new Object();

		Thread t1 = new Thread(new CyclicDeadLockRunnable(obj1, obj2), "thread1");
		Thread t2 = new Thread(new CyclicDeadLockRunnable(obj2, obj1), "thread2");

		//şöyle üçlü de olabilir
		//Thread t2 = new Thread(new CyclickDeadBlockRunnable(obj2, obj2), "thread2");
		//Thread t3 = new Thread(new CyclickDeadBlockRunnable(obj3, obj1), "thread3");

		t1.start();
		Thread.sleep(5000);
		t2.start();
		//Thread.sleep(5000);
		//t3.start();
	}
}
