package newPackage;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class myClassMain {

	   public static void main(String[] args) throws InterruptedException {
	    	
	    	ChromeOptions option = new ChromeOptions();
	    	option.addArguments("window-size=1920,1080");
	    	option.addArguments("--headless");
//	    	option.addArguments("--no-sandbox");
	    	
//	    	System.out.println("Please type username");
//	    	Scanner s = new Scanner(System.in);
//	    	String user = s.nextLine();
	    	
			System.setProperty("webdriver.chrome.driver", "/Selenium/chromedriver");
			
			// Base url to first navigate to
	        String baseUrl = "https://ssologin.cuny.edu/errorPage/pages/Error.html";

//	        try {
//	        	Runtime.getRuntime().exec("kill $(ps aux | grep chromedriver | grep -v grep | awk '{print $2}')").waitFor();
//	        }
//			catch(Exception e) {
//				System.out.println(e);
//			}

 			boolean success = false;
 			
 			// While didn't successfully swap
 			while(!success) {
 				
 				boolean error = false;
 				try {
	 		        // Initialize a new Chrome Driver with optional options
	 				WebDriver driver = new ChromeDriver(option);
	
	 	 			myClass mc = new myClass(driver, baseUrl);
	 				success = mc.enrollSwap();
	 				
//	 				Runtime.getRuntime().exec("kill $(ps aux | grep chromedriver | grep -v grep | awk '{print $2}')").waitFor();
	 				driver.close();
	 				
 				}
 				catch(Exception e) {
 					error = true;
 				}

 				if (error) {
 					// If there is an error wait 1 minute
 					Thread.sleep(60000);
 					System.out.println("Ended with an error.");
 				}
 				else {
 					// If it finished normally then wait only 10 seconds
// 					Thread.sleep(10000);
 					System.out.println("Finished normally.");
 				}
 			}
 			
 			System.out.println("Email user that he successfully enrolled");

				        
	    }

}
