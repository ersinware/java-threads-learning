package threads.topics;

import java.util.Timer;

import threads.util.MyTimerTask;

public class tenth_TimerTaskAndTimer {
	//Java Timer class is thread safe and multiple threads can share a single Timer object without
	//need for external synchronization. Timer class uses java.util.TaskQueue to add tasks at
	//given regular interval and at any time there can be only one thread running the TimerTask,
	//for example if you are creating a Timer to run every 10 seconds but single thread execution
	//takes 20 seconds, then Timer object will keep adding tasks to the queue and as soon as one
	//thread is finished, it will notify the queue and another thread will start executing.
	//
	//Java Timer class uses Object wait and notify methods to schedule the tasks.

	//While scheduling tasks using Timer, you should make sure that time interval is more than
	//normal thread execution, otherwise tasks queue size will keep growing and eventually task
	//will be executing always.

	public static void main(String[] args) {
		//running timer task as daemon thread
		//Java Timer object can be created to run the associated tasks as a daemon thread.
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(new MyTimerTask(), 0, 1000);
		//timer.schedule(new MyTimerTask(), 1000);
		System.out.println("TimerTask started");

		//cancel after sometime
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//eğer daemon thread olarak yaratılmamışsa, cancel metodu thread'ın işini bitirmesini bekler
		timer.cancel();
		System.out.println("TimerTask cancelled");
	}
}
