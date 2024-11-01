package threads.topics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import threads.util.CallableImpl;

public class thirteenth_Callable {
	//Java 5 introduced java.util.concurrent.Callable interface in concurrency package that is
	//similar to Runnable interface but it can return any Object and able to throw Exception.

	//Java Callable interface use Generic to define the return type of Object. Executors class
	//provide useful methods to execute Java Callable in a thread pool. Since callable tasks run
	//in parallel, we have to wait for the returned Object.

	//Java Callable tasks return java.util.concurrent.Future object. Using Java Future object, we
	//can find out the status of the Callable task and get the returned Object. It provides get()
	//method that can wait for the Callable to finish and then return the result.

	//Java Future provides cancel() method to cancel the associated Callable task.
	//There is an overloaded version of get() method where we can specify the time to wait for the
	//result, it’s useful to avoid current thread getting blocked for longer time. There are
	//isDone() and isCancelled() methods to find out the current status of associated Callable task
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Callable<String> callable = new CallableImpl();
		List<Future<String>> futureList = new ArrayList<>();

		for (int i = 1; i < 101; i++)
			futureList.add(executorService.submit(callable));

		for (Future<String> threadName : futureList)
			try {
				//get metodu bu thread'i kitliyor, Callable'ın call metodundan yanıt gelene kadar
				System.out.println(new Date() + "::" + threadName.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

		executorService.shutdown();
		System.out.println("end");
	}
}