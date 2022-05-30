class MyThread extends Thread{
	public void run(){
		try{
			Thread.sleep(1000);
			System.out.println("Finished!");
		}catch(InterruptedException e){
			System.out.println("I'm interrupted!");
			System.exit(-1);
		}
	}
	public static void main(String args[]){
		MyThread t1 = new MyThread();
		t1.start();
		try{
			t1.interrupt();
		}catch(Exception e){
			System.out.println("Exception handled "+e);
		}
	}
}