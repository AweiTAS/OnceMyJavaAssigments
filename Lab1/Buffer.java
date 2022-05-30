public class Buffer {
	private int[] buf; // buffer storage
	private int last; // last occupied position

	private Object lock1 = new Object();

	public Buffer(int sz){
		buf = new int[sz];
		last = 0;
	}
	public boolean isFull(){ 
		return(last == buf.length); 
	}
	public boolean isEmpty(){
		return (last == 0);
	}
	public void put(int c) {  synchronized(lock1){
		while(isFull()){
			try {
				wait();
			}catch(InterruptedException e){
				e.printStackTrace();	
			}
		}
		buf[last++] = c;
		notify(); //to the buffer
	}}
	public int get(){  synchronized(lock1){
		while (isEmpty()) {
			try {
				wait();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		int c = buf[0];
		System.arraycopy(buf, 1, buf, 0, --last);
		notify();
		return c;
	}}
}