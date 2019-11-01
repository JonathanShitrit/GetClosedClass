package newPackage.enrollSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import newPackage.SeleniumFunctions;

public class Login extends SeleniumFunctions {

	public Login(WebDriver _driver, WebDriverWait _wait) {
		super(_driver, _wait);
	}

	public void insertLoginKeys() {

		// Insert Username
		By usernameLocator = By.id("CUNYfirstUsernameH");
		String username = "Jonathan.shitrit66";
		waitAndSendKey(usernameLocator, username);  	

		// Insert Pass
		By passwordLocator = By.id("CUNYfirstPassword");
		String password = "Kobejack55;";
		waitAndSendKey(passwordLocator, password);

		// Click Submit
		By submitLocator = By.id("submit");
		waitAndClick(submitLocator);
	}


	public void login() {

		// Clicks the link to login page
		By cunyfirstLocator = By.cssSelector("a[href=\"https://home.cunyfirst.cuny.edu\"]");
		this.waitAndClick(cunyfirstLocator);  

		// Login
		this.insertLoginKeys();


	}

	public void toStudentCenter() {
		// Click Student Center
		String studentCenterURL = "https://home.cunyfirst.cuny.edu/psp/cnyepprd/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSS_STUDENT_CENTER.GBL?FolderPath=PORTAL_ROOT_OBJECT.HC_SSS_STUDENT_CENTER&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder";
		By studentcenterLocator = By.cssSelector("a[href=\""+studentCenterURL+"\"]");
		this.waitAndClick(studentcenterLocator); 
	}


}
