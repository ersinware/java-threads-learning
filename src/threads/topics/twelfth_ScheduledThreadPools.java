package threads.topics;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import threads.util.RejectedExecutionHandlerImpl;
import threads.util.RunnableImpl2;

//Sometimes we need to execute a task periodically or after specific delay. Java provides Timer
//Class through which we can achieve this but sometimes we need to run similar tasks in parallel
//So creating multiple Timer objects will be an overhead to the system and it’s better to have
//a thread pool of scheduled tasks.
//
//Java provides scheduled thread pool implementation through ScheduledThreadPoolExecutor class
//that implements ScheduledExecutorService interface. ScheduledExecutorService defines the
//contract methods to schedule a task with different options.
public class twelfth_ScheduledThreadPools {

	public static void main(String[] args) {
		executors();
	}

	private static void executors() {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

		ScheduledFuture<?> scheduledFuture = null;
		//schedule to run after sometime
		//örnek olsun diye böyle ama birden fazla runnable kullanmak doğru değil sanırım
		for (int i = 1; i < 3; i++) {
			RunnableImpl2 runnable = new RunnableImpl2(String.valueOf(i));
			//Note that all the schedule() methods return instance of ScheduledFuture that we can use
			//to get the thread state information and delay time for the thread.
			scheduledFuture = scheduledExecutorService.schedule(runnable, 5, TimeUnit.SECONDS);

			//The sequence of task executions continues indefinitely until one of the following exceptional completions occur:
			//The task is explicitly cancelled via the returned future.
			//The executor terminates, also resulting in task cancellation.
			//An execution of the task throws an exception. In this case calling get on the returned
			//future will throw ExecutionException, holding the exception as its cause.
			//			scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(
			//					runnable,
			//					5,
			//					10,
			//					TimeUnit.SECONDS);

			//			scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(
			//					runnable,
			//					5,
			//					10,
			//					TimeUnit.SECONDS);
		}

		//add some delay to let some threads spawn by scheduler
		try {
			//Thread.sleep(6000);
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//thread sleeping ise durdurmuyor, bir iş yapıyorsa durdurmuyor
		//scheduledFuture.cancel(true);

		//thread sleeping ise durduruyor, ama bir iş yapıyorsa durdurmuyor
		//scheduledExecutorService.shutdownNow();

		//thread sleeping ise durdurmuyor, bir iş yapıyorsa durdurmuyor
		System.out.println("shutdown");
		scheduledExecutorService.shutdown();

		//wait for all tasks to finish
		while (!scheduledExecutorService.isTerminated()) {}
		System.out.println("Finished all threads");
	}
}