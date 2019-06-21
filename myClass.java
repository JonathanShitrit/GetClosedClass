package newPackage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class myClass {

	private WebDriver driver; 
	private WebDriverWait wait;
	private String baseUrl;
	
//	this.driver.get("https://hrsa.cunyfirst.cuny.edu/psc/cnyhcprd/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES_2.SSR_SSENRL_CART.GBL?Page=SSR_SSENRL_CART&Action=A&ACAD_CAREER=UGRD&EMPLID=23493566&INSTITUTION=QNS01&STRM=1199&TargetFrameName=None");

//	String getEnrollStr = "https://hrsa.cunyfirst.cuny.edu/psp/cnyhcprd/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_SWAP.GBL?ACAD_CAREER=UGRD&EMPLID=23493566&ENRL_REQUEST_ID=0046952732&INSTITUTION=QNS01&STRM=1199&PAGE=SSR_SSENRL_SWAP&Action=A&TargetFrameName=None";
//	this.driver.get(getEnrollStr);
	
	
	public myClass(WebDriver _driver, String _baseUrl) {
		this.driver = _driver;
		this.baseUrl = _baseUrl;
		this.wait = new WebDriverWait(this.driver, 60);
		
		driver.get(this.baseUrl);
	}
	
    
    public boolean waitForThenSendKey(String cssSelectorStr, String key){
		try{
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelectorStr)));
			this.driver.findElement(By.cssSelector(cssSelectorStr)).sendKeys(key);
		    return true; 
		}
		catch(org.openqa.selenium.NoSuchElementException e){
		    return false;
		} 
    }
    
    public boolean waitForThenClick(String cssSelectorStr){
		try{
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelectorStr)));
			this.driver.findElement(By.cssSelector(cssSelectorStr)).click();
		    return true; 
		}
		catch(org.openqa.selenium.NoSuchElementException e){
		    return false;
		} 
    }
    
    private void insertLoginKeys() throws InterruptedException {
    	
		// Insert User name
    	waitForThenSendKey("input[name=\"usernameH\"]", "Jonathan.shitrit66");  	
//    	waitForThenSendKey(driver, "[name=loginform] > .form-control", "Jonathan.shitrit66");

        // Insert Pass
    	waitForThenSendKey("input[name=\"password\"]", "Kobejack55;");

        // Click Submit
    	waitForThenClick("button[name=\"submit\"]");
    }
    
    private void login() throws InterruptedException {
    	
    	// Clicks the link to login page
    	this.waitForThenClick("a[href=\"https://home.cunyfirst.cuny.edu\"]");  

        // Login
    	this.insertLoginKeys();
    	       
    	// Click Student Center
		String studentCenterURL = "https://home.cunyfirst.cuny.edu/psp/cnyepprd/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSS_STUDENT_CENTER.GBL?FolderPath=PORTAL_ROOT_OBJECT.HC_SSS_STUDENT_CENTER&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder";
		this.waitForThenClick("a[href=\""+studentCenterURL+"\"]");  
    }
    
    private void SwitchToIframe(String frameId, WebDriver driver) {
        WebElement editorFrame = driver.findElement(By.id(frameId));
        driver.switchTo().frame(editorFrame);
    }
  
    
    private void enrollSwapBtn() {
    	
        // Click Enroll Swap
        SwitchToIframe("ptifrmtgtframe", driver);
        waitForThenClick("option[value=\"1015\"]");
        
        // Click Submit 
        waitForThenClick("img[title=\"Go\"]");

    }
    
    private void enrollAddBtn() {
    	
        // Click Enroll Swap
        SwitchToIframe("ptifrmtgtframe", driver);
        waitForThenClick("option[value=\"1005\"]");
        
        // Click Submit 
        waitForThenClick("img[title=\"Go\"]");
        
    }
    
    private void fallTerm() {
    	
        // Click Term
    	waitForThenClick("input[id=\"SSR_DUMMY_RECV1$sels$1$$0\"]");
    	
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table[id=\"SSR_DUMMY_RECV1$scroll$0\"]>tbody")));
//    	List<WebElement> tbodyList = driver.findElements(By.cssSelector("table[id=\"SSR_DUMMY_RECV1$scroll$0\"]>tbody"));
//    	int lastTermValue = tbodyList.size() - 3;
//        waitForThenClick(driver, wait, "input[value=\""+ lastTermValue +"\"]");

    	
        // Submit
        waitForThenClick("a[name=\"DERIVED_SSS_SCT_SSR_PB_GO\"]");
    }
    
//    private void refreshEnrollSwap() {
//    	
//    	// Click back to Swap to reload
//        waitForThenClick("td[class=\"SSSTABACTIVE\"][width=\"14%\"] > a");
//
//    	// Select desired class from shopping cart drop-down list
//        waitForThenClick("option[value=\"46708\"]");
//        
//        // Click Select      
//        waitForThenClick("a[name=\"DERIVED_REGFRM1_SSR_PB_ADDTOLIST1$184$\"]");
//
//    }
    
//    private boolean waitForElement(String xpathStr){
//		try{
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathStr)));
//		    return true; 
//		}
//		catch(org.openqa.selenium.NoSuchElementException e){
//		    return false;
//		} 
//    }
    
    private void enrollSwapWithClass() throws InterruptedException{
    	
    	// Select desired class from shopping cart drop-down list
        waitForThenClick("option[value=\"46708\"]");
        
        // Click Select      
        waitForThenClick("a[name=\"DERIVED_REGFRM1_SSR_PB_ADDTOLIST1$184$\"]");
        
        // Click Finish Swapping
        waitForThenClick("a[name=\"DERIVED_REGFRM1_SSR_PB_SUBMIT\"]");

    }
    
    public boolean checkStatus() {
    	try {
    		this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt=\"Success\"][class=\"SSSIMAGECENTER\"]")));
    		// From here down is Success
    		
    		return true;
    	}
    	catch(Exception e) {
    		return false;
    	}
    	
    }


    public boolean enrollSwap() throws InterruptedException {
    	
    	// Logs in and navigate to Student Center
    	this.login();
    	
        // Navigates to Enroll Swap
		this.enrollSwapBtn();
        
        // Enter fall term
		this.fallTerm();
        
        // Enroll Swap with desired class
		this.enrollSwapWithClass();
		
		// Check if enrolled or not
		boolean isSuccess = this.checkStatus();
	
		return isSuccess;
    }
    
    
    public void attemptToEnroll() {
    	 // Click Proceed to step 2 out of 3
        waitForThenClick("a[id=\"DERIVED_REGFRM1_LINK_ADD_ENRL$82$\"]");
        
        // Click Finish Enrolling
        waitForThenClick("a[id=\"DERIVED_REGFRM1_SSR_PB_SUBMIT\"]");
    }
    
    public boolean enrollAdd() throws InterruptedException {
    	
    	// Logs in and navigate to Student Center
    	this.login();

        // Navigates to Enroll Swap
		this.enrollAddBtn();
        
        // Enter fall term
		this.fallTerm();
        
        // Enroll Swap with desired class
		this.attemptToEnroll();
		
		// Check if enrolled or not
		boolean isSuccess = this.checkStatus();
	
		return isSuccess;
    }
    
}
