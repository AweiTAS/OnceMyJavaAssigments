public class BufferApp{
	public static void main(String[] args){
		Buffer b1 = new Buffer(50);
		Producer p1 = new Producer(b1);
		Thread t1 = new Thread(p1);
		Consumer c1 = new Consumer(b1);
		Thread t2 = new Thread(c1);
		
		t2.start();
		t1.start();
	}
}