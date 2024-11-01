package threads.topics;

import threads.util.DaemonRunnable;

public class seventh_DaemonThread {
	//Daemon thread in java can be useful to run some tasks in background. When we create a thread
	//in java, by default it’s a user thread and if it’s running JVM will not terminate the
	//program.
	//
	//When a thread is marked as daemon thread, JVM doesn’t wait it to finish to terminate the
	//program. As soon as all the user threads are finished, JVM terminates the program as well as
	//all the associated daemon threads.

	//If we don’t set the “daemonThread” thread to be run as daemon thread, the program will never
	//terminate even after main thread is finished it’s execution. Notice that DaemonThread is
	//having a while true loop with thread sleep, so it will never terminate on it’s own.
	//
	//Try to comment the statement to set thread as daemon thread and run the program. You will
	//notice that program runs indefinitely and you will have to manually quit it.

	//Daemon Thread Usage
	//
	//Usually we create a daemon thread for functionalities that are not critical to system. For
	//example logging thread or monitoring thread to capture the system resource details and their
	//state. If you are not okay will a thread being terminated, don’t create it as a daemon
	//thread.
	//
	//Also it’s better to avoid daemon threads for IO operations because it can cause resource leak
	//when program just terminates and resources are not closed properly.
	public static void main(String[] args)  {
		Thread dt = new Thread(new DaemonRunnable(), "daemonThread");
		dt.setDaemon(true);
		dt.start();

		//continue program
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Finishing program");
	}
}
