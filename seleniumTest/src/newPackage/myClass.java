package newPackage;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class myClass extends SeleniumFunctions {

	//	this.driver.get("https://hrsa.cunyfirst.cuny.edu/psc/cnyhcprd/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES_2.SSR_SSENRL_CART.GBL?Page=SSR_SSENRL_CART&Action=A&ACAD_CAREER=UGRD&EMPLID=23493566&INSTITUTION=QNS01&STRM=1199&TargetFrameName=None");
	//	String getEnrollStr = "https://hrsa.cunyfirst.cuny.edu/psp/cnyhcprd/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_SWAP.GBL?ACAD_CAREER=UGRD&EMPLID=23493566&ENRL_REQUEST_ID=0046952732&INSTITUTION=QNS01&STRM=1199&PAGE=SSR_SSENRL_SWAP&Action=A&TargetFrameName=None";
	
	private Login login; 
	private Enroll enroll;

	public myClass(WebDriver _driver, WebDriverWait _wait, String _baseUrl) {
		super(_driver, _wait);
		visit(_baseUrl);
		
		login = new Login(_driver, _wait);
		enroll = new Enroll(_driver, _wait);
	}


	// Checks if the success image appeared after attempting to enroll
	private boolean checkStatus() {
		try {
			// From here down is Success
			By successLocator = By.cssSelector("img[alt=\"Success\"][class=\"SSSIMAGECENTER\"]");
			waitFor(successLocator);
			return true;
		}
		catch(Exception e) {
			return false;
		}

	}


	/**
	 * Returns a boolean isSuccess variable. Return true if successfully
	 * swapped with specified class number {@code classNumber} and
	 * false if otherwise. 
	 * <p>
	 * This method returns after it completes the process of logging
	 * into users CunyFirst account, navigating to enroll swap, 
	 * selecting the specified term, and attempting to swap a class you
	 * are in with the class you want with specified class number. 
	 *
	 * @param  term either fall or spring
	 * @param  classNumber the course's class number 
	 * @return boolean isSuccess variable
	*/
	public boolean enrollSwap(String term, int classNumber) throws Exception {

		// Logs in
		this.login.login();
		
		// Navigate to Student Center
		this.login.toStudentCenter();

		// Navigates to Enroll Swap
		this.enroll.enrollType("swap");

		// Enter fall term
		this.enroll.term(term);

		// Enroll Swap with desired class
		this.enroll.enrollSwapWithClass(classNumber);

		// Check if enrolled or not
		boolean isSuccess = this.checkStatus();

		return isSuccess;
	}


	/**
	 * Returns a boolean isSuccess variable. Return true if successfully
	 * Added a new class with course number and false if otherwise. 
	 * <p>
	 * This method returns after it completes the process of logging
	 * into users CunyFirst account, navigating to enroll add, 
	 * selecting the specified term, and attempting to enroll in all
	 * classes in shopping cart.
	 * 
	 * @param  term either fall or spring
	 * @param  classNumber the course's class number 
	 * @return boolean isSuccess variable
	*/
	public boolean enrollAdd(String term) throws Exception {

		// Logs in
		this.login.login();
		
		// Navigate to Student Center
		this.login.toStudentCenter();

		// Navigates to Enroll Add
		this.enroll.enrollType("add");

		// Enter fall term
		this.enroll.term(term);

		// Enroll into all classes in shopping cart and that are open
		this.enroll.attemptToEnroll();

		// Check if enrolled or not
		boolean isSuccess = this.checkStatus();

		return isSuccess;
	}

	// Navigates to the enroll page via a url request
	public boolean requestEnrollSwap(int classNumber) throws InterruptedException {

		// Clicks the link to login page
		By cunyfirstLocator = By.cssSelector("a[href=\"https://home.cunyfirst.cuny.edu\"]");
		this.waitAndClick(cunyfirstLocator);  

		// Login
		this.login.insertLoginKeys();

		// Navigates to Enroll Add
		visit("https://hrsa.cunyfirst.cuny.edu/psc/cnyhcprd/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_SWAP.GBL?Page=SSR_SSENRL_SWAP&Action=A&ACAD_CAREER=UGRD&ENRL_REQUEST_ID=0000000000&INSTITUTION=QNS01&STRM=1199&TargetFrameName=None");

		// Enroll Swap with desired class
		this.enroll.enrollSwapWithClass(classNumber);

		// Check if enrolled or not
		boolean isSuccess = this.checkStatus();

		return isSuccess;
	}

	// Navigates to the enroll page via a url request
	public boolean requestEnrollAdd() throws InterruptedException {

		// Clicks the link to login page
		By cunyfirstLocator = By.cssSelector("a[href=\"https://home.cunyfirst.cuny.edu\"]");
		this.waitAndClick(cunyfirstLocator);  

		// Login
		this.login.insertLoginKeys();

		// Navigates to Enroll Add
		visit("https://hrsa.cunyfirst.cuny.edu/psc/cnyhcprd/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_CART.GBL?Page=SSR_SSENRL_CART&Action=A&ACAD_CAREER=UGRD&EMPLID=23493566&INSTITUTION=QNS01&STRM=1199&TargetFrameName=None");

		// Enroll Swap with desired class
		this.enroll.attemptToEnroll();

		// Check if enrolled or not
		boolean isSuccess = this.checkStatus();

		return isSuccess;
	}

}
