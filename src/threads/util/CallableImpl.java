package threads.util;

import java.util.concurrent.Callable;

public class CallableImpl implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		//return the thread name executing this callable task
		return Thread.currentThread().getName();
	}
}
