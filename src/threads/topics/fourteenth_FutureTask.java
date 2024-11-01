package threads.topics;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import threads.util.CallableImpl2;

public class fourteenth_FutureTask {

	public static void main(String[] args) {
		CallableImpl2 callable1 = new CallableImpl2(1000);
		CallableImpl2 callable2 = new CallableImpl2(2000);

		FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
		FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(futureTask1);
		executor.execute(futureTask2);

		while (true) {
			try {
				if (futureTask1.isDone() && futureTask2.isDone()) {
					System.out.println("Done");
					//shut down executor service
					executor.shutdown();
					return;
				}

				if (!futureTask1.isDone()) {
					//wait indefinitely for future task to complete
					System.out.println("FutureTask1 output: " + futureTask1.get());
				}

				System.out.println("Waiting for FutureTask2 to complete");
				//maximum bekleme süresi 200, eğer bu sürede cevap gelmezse, null döndürür
				String s = futureTask2.get(200, TimeUnit.MILLISECONDS);
				if (s != null)
					System.out.println("FutureTask2 output: " + s);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				//do nothing
			}
		}
	}
}