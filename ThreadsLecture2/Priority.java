package ThreadsLecture2;

//https://www.tutorialspoint.com/javaexamples/thread_priorityinfo.htm
public class Priority {
	private static Runnable makeRunnable() {
		Runnable runnable = new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					Thread t = Thread.currentThread();
					System.out.println(i + " in run() - priority = " + t.getPriority() + ", name = " + t.getName());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException x) {
					}
				}
			}
		};
		return runnable;
	}

	public static void main(String[] args) {
		System.out.println("in main() - Thread.currentThread(). getPriority()=" + Thread.currentThread().getPriority());
		//what is the name of this thread
		System.out.println("in main() - Thread.currentThread().getName()=" + Thread.currentThread().getName());

		Thread threadA = new Thread(makeRunnable(), "threadA");
		threadA.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException x) {
		}
		System.out.println("end of  main() - threadA.getPriority() = " + threadA.getPriority());
		//main finishes first
	}
}
