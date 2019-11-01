package globalSearch;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlobalSearchMainer {

	public static void main(String[] args) {
		Course c = new Course();

		System.setProperty("webdriver.chrome.driver", "/Selenium/chromedriver");
		
    	ChromeOptions option = new ChromeOptions();
    	option.addArguments("window-size=1920,1080");
//    	option.addArguments("--headless");
    	option.addArguments("--disable-gpu");
    	
    	
        String baseUrl            = "https://globalsearch.cuny.edu/CFGlobalSearchTool/CFSearchToolController";
    	String killChromeTasksCmd = "kill $(ps aux | grep chrome | grep -v grep | awk '{print $2}')";
    	String killGoogleTasksCmd = "kill $(ps aux | grep google | grep -v grep | awk '{print $2}')";

    	killTask(killGoogleTasksCmd);
    	killTask(killChromeTasksCmd);
    	
    	WebDriver driver   = new ChromeDriver(option);
		WebDriverWait wait = new WebDriverWait(driver, 30);	
		
		GlobalSearchClass gs = new GlobalSearchClass(driver, wait, baseUrl);
		gs.getAllColleges();
		
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
