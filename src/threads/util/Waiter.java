package threads.util;

public record Waiter(Message message) implements Runnable {

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " started");

		synchronized (message) {
			try {
				System.out.println(threadName + " waiting to get notified at time: " + System.currentTimeMillis());
				message.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(threadName + " got notified at time: " + System.currentTimeMillis());
			//process the message now
			System.out.println(threadName + " processed: " + message.getMessage());
		}
	}
}
