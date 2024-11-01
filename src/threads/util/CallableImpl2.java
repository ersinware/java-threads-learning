package threads.util;

import java.util.concurrent.Callable;

public class CallableImpl2 implements Callable<String> {

	private final long waitTime;

	public CallableImpl2(int waitTime) {
		this.waitTime = waitTime;
	}

	@Override
	public String call() {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//return the thread name executing this callable task
		return Thread.currentThread().getName();
	}
}
