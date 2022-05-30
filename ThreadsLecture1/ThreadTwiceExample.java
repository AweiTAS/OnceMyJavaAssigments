package ThreadsLecture1;
//https://beginnersbook.com/2015/03/can-we-start-a-thread-twice-in-java/
public class ThreadTwiceExample implements Runnable {
	   public void run(){  
		Thread t = Thread.currentThread();
	        System.out.println(t.getName()+" is executing.");
	   }  
	   
	   
//	   public static void main(String args[]){  
//		Thread th1 = new Thread(new ThreadTwiceExample(), "th1"); 
//		th1.start();  
//		//throws java.lang.IllegalThreadStateException
//		th1.start();  
//	   } 
	   
	   public static void main(String args[]){  
			Thread th1 = new Thread(new ThreadTwiceExample(), "th1"); 
			th1.start();  
			Thread th2 = new Thread(new ThreadTwiceExample(), "th2"); 
			th2.start();  
		   }    
	}