package newPackage.enrollSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import newPackage.SeleniumFunctions;

public class Enroll extends SeleniumFunctions {

	public Enroll(WebDriver _driver, WebDriverWait _wait) {
		super(_driver, _wait);
	}

	public void enrollType(String type) throws Exception {

		By iframeLocator = By.id("ptifrmtgtframe");
		SwitchToIframe(iframeLocator);

		type = type.toLowerCase();
		if (type == "add") {
			// Enroll Add
			By addLocator = By.cssSelector("option[value=\"1005\"]");
			waitAndClick(addLocator);
		}
		else if (type == "swap"){
			// Enroll Swap
			By swapLocator = By.cssSelector("option[value=\"1015\"]");
			waitAndClick(swapLocator);
		}

		// Click Submit 
		By swapLocator = By.cssSelector("img[title=\"Go\"]");
		waitAndClick(swapLocator);
	}


	public void term(String semester) throws Exception {

		semester = semester.toLowerCase();
		if (semester.matches("spring")) {
			// Spring Term
			By springtermLocator = By.id("SSR_DUMMY_RECV1$sels$0$$0");
			waitAndClick(springtermLocator);
		}
		if (semester.matches("fall")){
			// Fall Term
			By falltermLocator = By.id("SSR_DUMMY_RECV1$sels$1$$0");
			waitAndClick(falltermLocator);
		}
		else {
			throw new Exception("Not a valid term! Must be either fall or spring.");
		}


		// Submit
		By submitLocator = By.cssSelector("a[name=\"DERIVED_SSS_SCT_SSR_PB_GO\"]");
		waitAndClick(submitLocator);
	}


	public void enrollSwapWithClass(int classNumber) throws InterruptedException{

		try {
			// Select desired class from shopping cart drop-down list
			By shoppingcartLocator = By.id("DERIVED_REGFRM1_SSR_CLASSNAME_35$183$");
			By classLocator = By.cssSelector("option[value=\""+classNumber+"\"]");

			waitAndFind(shoppingcartLocator).findElement(classLocator).click();
		}
		catch(Exception e) {
			System.out.println("Make sure your class is in your enrollment cart.");
		}
		// Click Select      
		By selectLocator = By.cssSelector("a[name=\"DERIVED_REGFRM1_SSR_PB_ADDTOLIST1$184$\"]");
		waitAndClick(selectLocator);

		// Click Finish Swapping
		By finishLocator = By.cssSelector("a[name=\"DERIVED_REGFRM1_SSR_PB_SUBMIT\"]");
		waitAndClick(finishLocator);

	}

	public void attemptToEnroll() {
		// Click Proceed to step 2 out of 3
		By step2out3Locator = By.id("DERIVED_REGFRM1_LINK_ADD_ENRL$82$");
		waitAndClick(step2out3Locator);

		// Click Finish Enrolling
		By submitLocator = By.id("DERIVED_REGFRM1_SSR_PB_SUBMIT");
		waitAndClick(submitLocator);
	}


}
