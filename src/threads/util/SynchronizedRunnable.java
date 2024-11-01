package threads.util;

public class SynchronizedRunnable implements Runnable {
	//When a method is synchronized, it locks the Object, if method is static it locks the Class,
	//so it’s always best practice to use synchronized block to lock the only sections of method
	//that needs synchronization.

	//You should use the lowest level of locking, for example, if there are multiple synchronized
	//block in a class and one of them is locking the Object, then other synchronized blocks
	//will also be not available for execution by other threads. When we lock an Object, it
	//acquires a lock on all the fields of the Object.

	//mutex nesnesi kullanıldığında bir sorun olmuyor, sdaece synchronized bloğu kitleniyor
	//diğer thread'lar bu sınıf üzerinde diğer işlemleri yapmaya devam edebilir

	private int count;

	//dummy object variable for synchronization
	private final Object mutex = new Object();

	@Override
	//public synchronized void run() {
	public void run() {
		for (int i = 1; i < 5; i++) {
			//using synchronized block to read, increment and update count value synchronously
			processSomething(i);
			//mutex'e birden fazla thread aynı anda erişemez (bir thread'ın işi bitmeden yani), bu
			//yüzden diğer thread'lar bu bloğun bitmesini bekler (mutex ile kitleme yapılıyor)
			synchronized (mutex) {
				count++;
				System.out.println(Thread.currentThread().getName() + ", " + count);
			}
		}
	}

	public int getCount() {
		return this.count;
	}

	private void processSomething(int i) {
		try {
			Thread.sleep(i * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
