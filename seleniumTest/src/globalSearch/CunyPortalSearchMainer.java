package globalSearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CunyPortalSearchMainer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "/Selenium/chromedriver");
		
    	ChromeOptions option = new ChromeOptions();
    	option.addArguments("window-size=1920,1080");
//    	option.addArguments("--headless");
    	option.addArguments("--disable-gpu");
    	
    	
        String baseUrl            = "https://ssologin.cuny.edu/errorPage/pages/Error.html";
    	String killChromeTasksCmd = "kill $(ps aux | grep chrome | grep -v grep | awk '{print $2}')";
    	String killGoogleTasksCmd = "kill $(ps aux | grep google | grep -v grep | awk '{print $2}')";

    	killTask(killGoogleTasksCmd);
    	killTask(killChromeTasksCmd);
    	
    	WebDriver driver   = new ChromeDriver(option);
		WebDriverWait wait = new WebDriverWait(driver, 30);	

    	
		CunyPortalSearchClass sc = new CunyPortalSearchClass(driver, wait, baseUrl);
		try {
			sc.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		driver.close();

	}

	   // Kills the specified task in linux
	   public static void killTask(String task){
	        try {
	        	Runtime.getRuntime().exec(new String[] { "bash", "-c", task});
	        	System.out.println("killed tasks");
	        	Thread.sleep(1000);
	        }
			catch(Exception e) {
				System.out.println(e);
			}
	   }
}
