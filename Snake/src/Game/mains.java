package Game;

import java.util.concurrent.TimeUnit;

public class mains 
{
	public static void main(String[] args) throws InterruptedException {
		 
        long startTime = System.nanoTime();
 
 
        // sleep for 10 seconds
        TimeUnit.SECONDS.sleep(10);
 
        long endTime = System.nanoTime();
 
        long timeElapsed = endTime - startTime;
 
        System.out.println("Execution time in nanoseconds  : " + timeElapsed);
 
        System.out.println("Execution time in milliseconds : " +
                                timeElapsed / 1000000);
    }
}
	
	