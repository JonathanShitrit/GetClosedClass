package globalSearch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import newPackage.SeleniumFunctions;
import newPackage.enrollSteps.Login;

public class CunyPortalSearchClass extends SeleniumFunctions {

	Login login;
	
	public CunyPortalSearchClass(WebDriver _driver, WebDriverWait _wait, String _baseUrl) {
		super(_driver, _wait);
		visit(_baseUrl);
		login = new Login(_driver, _wait);

	}
	
	public void test() throws InterruptedException {
		navigateToSearch();
		
		getAllSubjects();
		
	}
	
	
	public void navigateToSearch() {

		login.login();
		login.toStudentCenter();

		By iframeLocator = By.id("ptifrmtgtframe");
		SwitchToIframe(iframeLocator);

		By searchBtnLocator = By.id("DERIVED_SSS_SCR_SSS_LINK_ANCHOR1");
		waitAndClick(searchBtnLocator);

	}
	
	public void getAllColleges() {

		By collegesLocator = By.id("CLASS_SRCH_WRK2_INSTITUTION$31$");
		List<WebElement> colleges = waitAndFind(collegesLocator).findElements(By.tagName("option"));

		String college_code = "";
		String college_name = "";
		// for every college in the institution drowpdown menu
		for(WebElement college: colleges) {

			// if this college exists
			if(!college.getAttribute("value").matches("")) {
				college_code = college.getAttribute("value");
				college_name = college.getText();

				System.out.println('"' + college_name + '"' + ":" + " " + '"' + college_code + '"' + ",");

			}
		}
	}
	
	public void getAllSubjects() throws InterruptedException {

		By collegesLocator = By.id("CLASS_SRCH_WRK2_INSTITUTION$31$");
		List<WebElement> colleges = waitAndFind(collegesLocator).findElements(By.tagName("option"));
		
		String college_code = "";
		String college_name = "";
		
		// for every college in the institution drowpdown menu
		for(int i = 0; i < colleges.size(); i++) {
			
			WebElement college = colleges.get(i);
			
			// if this college exists
			if(!college.getAttribute("value").matches("")) {
				
				// Click this college which will retrieve all of its subjects in the subjects dropdown menu
				System.out.println(college.getText());
				college.click();
				Thread.sleep(1000);
				
				// choose the fall term
				By fallTermLocator = By.cssSelector("option[value=\"1199\"]");
				waitAndClick(fallTermLocator);
				Thread.sleep(1000);
				
			
				By subjectDropDownLocator = By.id("SSR_CLSRCH_WRK_SUBJECT_SRCH$0");
				List<WebElement> subjects = waitAndFind(subjectDropDownLocator).findElements(By.tagName("option"));
				
				String subject_code = "";
				String subject_name = "";
				
				// retrieve each subject in the subjects dropdown menu
				for(int j = 0; j < subjects.size(); j++) {
					
					WebElement subject = subjects.get(j);					
					// if this subject exists
					if(!subject.getAttribute("value").matches("")) {
						
						// retrive the subjects name and code
						if(subject.getText().split(" - ").length == 2) {
							subject_name = subject.getText().split(" - ")[1];
							subject_code =  subject.getText().split(" - ")[0];
						}
						else if (subject.getText().split("-").length == 2){
							subject_name = subject.getText().split("-")[1];
							subject_code =  subject.getText().split("-")[0];
						}
						
						System.out.println('"' + subject_name + '"' + ":" + " " + '"' + subject_code + '"' + ",");
					}
					
					subjects = waitAndFind(subjectDropDownLocator).findElements(By.tagName("option"));
				}
				
				System.out.println();
				System.out.println();
				colleges = waitAndFind(collegesLocator).findElements(By.tagName("option"));
//				college = colleges.get(i++);
			}
		}
	}

}
