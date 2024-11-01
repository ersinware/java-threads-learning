package threads.util;

public class RunnableImpl2 implements Runnable {
	private final String command;

	public RunnableImpl2(String command) {
		this.command = command;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started, command: " + command);
		processCommand();
		System.out.println(Thread.currentThread().getName() + " ended");
	}

	private void processCommand() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.command;
	}
}
