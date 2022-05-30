package ThreadsLecture1;
//https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html
public class SleepMessages {
	public static void main(String args[]) throws InterruptedException {
		String importantMessages[] = { "Mares eat oats", "Does eat oats", "Little lambs eat ivy",
				"A kid will eat ivy too" };

		for (int i = 0; i < importantMessages.length; i++) {
			// Pause for seconds
			Thread.sleep(1000);//this is sleeping the main thread
			// Print a message
			System.out.println(importantMessages[i]);
		}
	}
}