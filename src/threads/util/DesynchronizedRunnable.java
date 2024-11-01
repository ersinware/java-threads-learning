package threads.util;

public class DesynchronizedRunnable implements Runnable {
	private int count;

	@Override
	public void run() {
		for (int i = 1; i < 5; i++) {
			processSomething(i);
			//hangi thread'ın ne zaman arttırım yapacağı belli olmaz count++ -> okuma, toplama ve
			//atama, toplamda 3 işlem bir thread okuduktan sonra diğer thread'a sıra gelebilir ve bu
			//thread değerini arttırırsa, önceki thread hatalı toplama ve atama yapar
			count++;
			System.out.println(Thread.currentThread().getName() + ", " + count);
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
