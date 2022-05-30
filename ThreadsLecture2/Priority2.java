package ThreadsLecture2;
//https://www.javatpoint.com/priority-of-a-thread
class Priority2 extends Thread{  
	 public void run(){  
	   System.out.println("starting thread, name is:"+Thread.currentThread().getName());  
	   System.out.println("ending thread, priority is:"+Thread.currentThread().getPriority());  
	  
	  }  
	 public static void main(String args[]){  
	  Priority2 m1=new Priority2();  
	  Priority2 m2=new Priority2();  
	  m1.setPriority(Thread.MIN_PRIORITY);  
	  m2.setPriority(Thread.MAX_PRIORITY);  
	  m1.start();  
	  m2.start();  
	 }  
	}     
