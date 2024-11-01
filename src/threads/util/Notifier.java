package threads.util;

public record Notifier(Message message) implements Runnable {

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " started");

		try {
			Thread.sleep(5000);
			synchronized (message) {
				message.setMessage("message from " + threadName);
				//				message.notify();
				message.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

