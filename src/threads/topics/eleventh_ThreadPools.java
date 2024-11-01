package threads.topics;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import threads.util.MonitorRunnable;
import threads.util.RejectedExecutionHandlerImpl;
import threads.util.RunnableImpl2;

public class eleventh_ThreadPools {
	public static void main(String[] args) {
		//		executors();
		//		threadPoolExecutor();
	}

	private static void executors() {
		//Creates a thread pool that maintains enough threads to support the given parallelism level,
		//and may use multiple queues to reduce contention. The parallelism level corresponds to the
		//maximum number of threads actively engaged in, or available to engage in, task processing.
		//The actual number of threads may grow and shrink dynamically. A work-stealing pool makes
		//no guarantees about the order in which submitted tasks are executed.
		//ExecutorService executorService = Executors.newWorkStealingPool(5);

		//Creates a thread pool that reuses a fixed number of threads operating off a shared
		//unbounded queue. At any point, at most nThreads threads will be active processing tasks.
		//If additional tasks are submitted when all threads are active, they will wait in the queue
		//until a thread is available. If any thread terminates due to a failure during execution
		//prior to shutdown, a new one will take its place if needed to execute subsequent tasks.
		//The threads in the pool will exist until it is explicitly shutdown.
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 1; i < 11; i++) {
			//örnek olsun diye böyle ama birden fazla runnable kullanmak doğru değil sanırım
			Runnable runnable = new RunnableImpl2(String.valueOf(i));
			executorService.execute(runnable);
		}
		//In the above program, we are creating a fixed-size thread pool of 5 worker threads. Then we
		//are submitting 10 jobs to this pool, since the pool size is 5, it will start working on 5
		//jobs and other jobs will be in wait state, as soon as one of the job is finished, another
		//job from the wait queue will be picked up by worker thread and get’s executed.

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//thread sleeping ise durduruyor, ama bir iş yapıyorsa durdurmuyor
		//executorService.shutdownNow();

		//no new tasks will be accepted.
		//thread sleeping ise durdurmuyor, bir iş yapıyorsa durdurmuyor
		System.out.println("shutdown");
		executorService.shutdown();

		while (!executorService.isTerminated()) {}
		System.out.println("finished all threads");
	}

	//pool executor ile minumum ve maximum thread sayısını belirleyebiliyorsun
	private static void threadPoolExecutor() {
		//RejectedExecutionHandler implementation
		RejectedExecutionHandlerImpl rejectedExecutionHandler = new RejectedExecutionHandlerImpl();

		//Get the ThreadFactory implementation to use
		ThreadFactory threadFactory = Executors.defaultThreadFactory();

		//creating the ThreadPoolExecutor
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				//sürekli aktif olacak thread sayısı
				2,
				//gerektiğinde yaratılacak thread'lerle birlikte maximum thread sayısı
				4,
				//thread'in işi bittiğinde öldürülmeden önceki bekleme süresi
				10,
				//keepAliveTime'ın birimi
				TimeUnit.SECONDS,
				//eğer maximum thread sayısından çok runnable nesnesi execute edilmek için verilirse,
				//onları tutacak array (diğer thread'lerin işi bitince, bu array'deki runnable'ları
				//execute edecek)
				new ArrayBlockingQueue<Runnable>(2),
				threadFactory,
				//4(maximum thread sayısı)+2(task'leri tutan array'in size'ı)=6 task'ten fazla task
				//verilirse (6 task varken daha fazla task execute edilmek istenirse) çalışacak handler
				rejectedExecutionHandler
		);

		//start the monitoring thread
		MonitorRunnable monitorRunnable = new MonitorRunnable(threadPoolExecutor, 3);
		Thread monitorThread = new Thread(monitorRunnable);
		monitorThread.start();

		//submit work to the thread pool
		for (int i = 1; i < 11; i++)
			//örnek olsun diye böyle ama birden fazla runnable kullanmak doğru değil sanırım
			threadPoolExecutor.execute(new RunnableImpl2("command" + i));

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//thread'ler sleeping ise durduruyor, ama bir iş yapıyorsa durdurmuyor
		//threadPoolExecutor.shutdownNow();

		//thread'ler sleeping ise durdurmuyor, bir iş yapıyorsa durdurmuyor
		threadPoolExecutor.shutdown();

		//shut down the monitorRunnable (shutdown metodunu kendim yazdım)
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		monitorRunnable.shutdown();
	}
}