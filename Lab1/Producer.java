public class Producer implements Runnable {
	private Buffer buffer;
	public Producer(Buffer b) { buffer = b; }
	public void run() {
		for (int i=0; i<250; i++) {
			buffer.put((int)((i % 51)));
		}
	}
}