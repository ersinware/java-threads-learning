package threads.topics;

public class sixth_SingletonSafety {

	private static volatile sixth_SingletonSafety instance = null;
	private static final Object mutex = new Object();

	private sixth_SingletonSafety() {

	}

	//The local variable result seems unnecessary. But, it’s there to improve the performance of
	//our code. In cases where the instance is already initialized (most of the time), the
	//volatile field is only accessed once (due to “return result;” instead of “return instance;”)
	//This can improve the method’s overall performance by as much as 25 percent.

	//sanırım, nesneyi, metod'un içinde, thread stack'inde, oluşturarak, volatile ile işaretlenmiş nesneye
	//her seferinde main memory üzerinden erişilmesini engelliyor
	//visibility sorunları oluşturmaz mı?
	public static sixth_SingletonSafety getInstance() {
		sixth_SingletonSafety result = instance;
		if (result == null)
			synchronized (mutex) {
				result = instance;
				if (result == null)
					result = instance = new sixth_SingletonSafety();
			}

		return result;
	}
}

