package threads.topics;

import java.util.Random;
import threads.util.ThreadLocalRunnable;

public class eighth_ThreadLocal {
	//her thread için ayrı nesneler yaratılır, aynı runnable nesnesini kullanmalarına rağmen

	public static void main(String[] args) throws InterruptedException {
		ThreadLocalRunnable threadLocalRunnable = new ThreadLocalRunnable();

		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(threadLocalRunnable, "myThread" + i);

			Thread.sleep(new Random().nextInt(2000));

			thread.start();
		}
	}
}
