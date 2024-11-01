package threads.topics;

public class second_JoinExample {

	public static void main(String[] args) {
		Runnable runnable = getRunnable();

		Thread threadOne = new Thread(runnable, "threadOne");
		Thread threadTwo = new Thread(runnable, "threadTwo");
		Thread threadThree = new Thread(runnable, "threadThree");

		threadOne.start();

		//start second thread after waiting for 2 seconds or if it's dead
		try {
			threadOne.join(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadTwo.start();

		//start third thread only when first thread is dead
		try {
			threadTwo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadThree.start();

		//let all threads finish execution before finishing main thread
		try {
			threadOne.join();
			threadTwo.join();
			threadThree.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("all threads are dead, exiting main thread");
	}

	private static Runnable getRunnable() {
		return () -> {
			System.out.println(Thread.currentThread().getName() + " started");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " ended");
		};
	}
}
