public class Counter extends Thread{
	protected int counter;
	public void run(){
		for (counter = 10; counter <= 500; counter++){
			System.out.println("" + counter);
		}
	}
	public static void main(String args[]){
		Counter c1 = new Counter();
		c1.start();
	}
}