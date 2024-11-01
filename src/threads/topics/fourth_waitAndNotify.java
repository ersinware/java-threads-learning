package threads.topics;

import threads.util.Message;
import threads.util.Notifier;
import threads.util.Waiter;

//it's worth mentioning that all these low-level APIs, such as wait(), notify() and notifyAll(),
//are traditional methods that work well, but higher-level mechanisms are often simpler and
//better — such as Java's native Lock and Condition interfaces (available in java.util
//.concurrent.locks package).

public class fourth_waitAndNotify {
	//wait
	//Object wait methods has three variance, one which waits indefinitely for any other thread to
	//call notify or notifyAll method on the object to wake up the current thread. Other two
	//variances puts the current thread in wait for specific amount of time before they wake up.

	//notify
	//notify method wakes up only one thread waiting on the object and that thread starts execution
	//So if there are multiple threads waiting for an object, this method will wake up only one
	//of them. The choice of the thread to wake depends on the OS implementation of thread
	//management.

	//notifyAll
	//notifyAll method wakes up all the threads waiting on the object, although which one will
	//process first depends on the OS implementation.

	//These methods can be used to implement producer consumer problem where consumer threads are
	//waiting for the objects in Queue and producer threads put object in queue and notify the
	//waiting threads.

	//public static void yield()
	//Causes the currently running thread to yield to any other threads of the same priority that are waiting to be scheduled.

	//Let’s see an example where multiple threads work on the same object and we use wait, notify
	//and notifyAll methods.
	public static void main(String[] args) {
		Message message = new Message("process it");

		Waiter waiterOne = new Waiter(message);
		new Thread(waiterOne,"waiterOne").start();

		Waiter waiterTwo = new Waiter(message);
		new Thread(waiterTwo, "waiterTwo").start();

		Notifier notifier = new Notifier(message);
		new Thread(notifier, "notifier").start();
	}
}
