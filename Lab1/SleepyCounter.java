public class SleepyCounter implements Runnable{
	protected int counter;
	public void run(){
		for(counter = 10; counter <= 500; counter++){
			System.out.println("" + counter);
			try{
				Thread.sleep(50);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]){
		SleepyCounter s1 = new SleepyCounter();
		Thread c1 = new Thread(s1);
		
		c1.start();
	}
}