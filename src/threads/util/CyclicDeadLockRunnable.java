package threads.util;

public class CyclicDeadLockRunnable implements Runnable {
	private final Object obj1;
	private final Object obj2;

	public CyclicDeadLockRunnable(Object o1, Object o2) {
		this.obj1 = o1;
		this.obj2 = o2;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();

		System.out.println(threadName + " acquiring lock on " + obj1);
		synchronized (obj1) {
			System.out.println(threadName + " acquired lock on " + obj1);
			work();
			System.out.println(threadName + " acquiring lock on " + obj2);
			synchronized (obj2) {
				System.out.println(threadName + " acquired lock on " + obj2);
				work();
			}
			System.out.println(threadName + " released lock on " + obj2);
		}
		System.out.println(threadName + " released lock on " + obj1);

		System.out.println(threadName + " finished execution.");
	}

	private void work() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

