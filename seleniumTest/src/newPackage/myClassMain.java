package newPackage;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class myClassMain {

	   public static void main(String[] args) throws InterruptedException {
	      	
		   if (args == null || args.length < 2){
		        System.out.println("2 params are required, term and class number. \nExample: java -jar SeleniumProjectWithArguments.jar fall 46708");           
		    }
		   			   
			System.setProperty("webdriver.chrome.driver", "/Selenium/chromedriver");
			
	    	ChromeOptions option = new ChromeOptions();
	    	option.addArguments("window-size=1920,1080");
//	    	option.addArguments("--headless");
	    	option.addArguments("--disable-gpu");
	    	
	 		String semesterTerm = args[0]; //"fall"
	 		int classNumber     = Integer.parseInt(args[1]); //46708
 			boolean isSuccess   = false;
 			

	        String baseUrl            = "https://ssologin.cuny.edu/errorPage/pages/Error.html";
        	String killChromeTasksCmd = "kill $(ps aux | grep chrome | grep -v grep | awk '{print $2}')";
        	String killGoogleTasksCmd = "kill $(ps aux | grep google | grep -v grep | awk '{print $2}')";

        	killTask(killGoogleTasksCmd);
        	killTask(killChromeTasksCmd);

 			
 			// While didn't successfully swap
 			while(!isSuccess) {
 				
 				boolean error = false;
 				
 				// Initialize a new Chrome Driver with optional options
 				WebDriver driver   = new ChromeDriver(option);
 				WebDriverWait wait = new WebDriverWait(driver, 30);
 				
 				try {
	 	 			myClass mc = new myClass(driver, wait, baseUrl);
	 	 			isSuccess  = mc.enrollSwap(semesterTerm, classNumber);	 				
 				}
 				catch(Exception e) {
 					System.out.println(e);
 					error = true;
 				}

 				tearDown(driver, killGoogleTasksCmd, killChromeTasksCmd);

 				if (error) {
 					// If there is an error then try again in  1 minute
 					Thread.sleep(60000);
 					System.out.println("Ended with an error.");
 				}
 				else {
 					// If it finished normally then try again in 10 seconds
 					Thread.sleep(10000);
 					System.out.println("Fini2shed normally.");
 				}
 				

 			} //end while
 			
 			System.out.println("Email user that he successfully enrolled");

				        
	    }
	   
	   // Kills the specified task in linux
	   public static void killTask(String task){
	        try {
	        	Runtime.getRuntime().exec(new String[] { "bash", "-c", task});
	        	System.out.println("killed tasks");
	        }
			catch(Exception e) {
				System.out.println(e);
			}
	   }
	   
	   // The conclusion to each test. Closes and kills tasks to start fresh for next test
	   public static void tearDown(WebDriver driver, String task1, String task2) {
			driver.close();
			killTask(task1);
			killTask(task2);

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	   }

}
