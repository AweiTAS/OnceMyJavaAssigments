public class CounterApp{
	public static void main(String args[]){
		Counter c1 = new Counter();
		SleepyCounter s1 = new SleepyCounter();
		Thread t1 = new Thread(s1);
		t1.start();
		c1.start();
		t1.yield();
		t1.interrupt();
		System.out.println("" + t1 +"\n"+ s1);
	}
}