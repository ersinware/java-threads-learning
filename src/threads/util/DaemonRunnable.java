package threads.util;

public class DaemonRunnable implements Runnable {

	@Override
	public void run() {
		while (true) {
			processSomething();
		}
	}

	private void processSomething() {
		try {
			System.out.println("Processing daemon thread");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
