package threads.util;

import java.util.concurrent.ThreadPoolExecutor;

public class MonitorRunnable implements Runnable {
	private final ThreadPoolExecutor threadPoolExecutor;
	private final int delay;
	private boolean run = true;

	public MonitorRunnable(ThreadPoolExecutor threadPoolExecutor, int delay) {
		this.threadPoolExecutor = threadPoolExecutor;
		this.delay = delay;
	}

	public void shutdown() {
		this.run = false;
	}

	@Override
	public void run() {
		while (run) {
			System.out.printf("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s%n",
					threadPoolExecutor.getPoolSize(),
					threadPoolExecutor.getCorePoolSize(),
					threadPoolExecutor.getActiveCount(),
					threadPoolExecutor.getCompletedTaskCount(),
					threadPoolExecutor.getTaskCount(),
					threadPoolExecutor.isShutdown(),
					threadPoolExecutor.isTerminated()
			);

			try {
				Thread.sleep(delay * 1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}