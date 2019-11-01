package globalSearch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import newPackage.SeleniumFunctions;

public class GlobalSearchClass extends SeleniumFunctions {

	public GlobalSearchClass(WebDriver _driver, WebDriverWait _wait, String _baseUrl) {
		super(_driver, _wait);
		visit(_baseUrl);	
	}

	public void test() {


		// Select Queens College
		waitForElement(driver, "input[value=\"QNS01\"]");
		driver.findElement(By.cssSelector("input[value=\"QNS01\"]")).click();

		// Select Fall 2019
		waitForElement(driver, "option[value=\"1199\"]");
		driver.findElement(By.cssSelector("option[value=\"1199\"]")).click();

		// Click next
		driver.findElement(By.cssSelector("input[name=\"next_btn\"]")).click();

		// On Search page
		if(waitForElement(driver, "input[name=\"search_btn_search\"]")) {

			// All subjects 
			List<WebElement> allSubjects = driver.findElement(By.id("subject_ld")).findElements(By.tagName("option"));
			for (int i = 1; i < allSubjects.size(); i++) {

				WebElement subject = allSubjects.get(i);

				// Get the name of the subject and its shorthand code
				String sbj = subject.getAttribute("value");
				String sbjName = subject.getText();
				System.out.println("\""+sbj+"\": \""+sbjName+"\",");

				// Use these names to navigate to that subjects search page
//				driver.get("https://globalsearch.cuny.edu/CFGlobalSearchTool/CFSearchToolController?"
//						+ "selectedSubjectName="+sbjName+"&"
//						+ "subject_name="+sbj+"&"
//						+ "selectedCCareerName=Undergraduate&"
//						+ "courseCareer=UGRD&"
//						+ "selectedCAttrName=&"
//						+ "courseAttr=&"
//						+ "selectedCAttrVName=&"
//						+ "courseAttValue=&"
//						+ "selectedReqDName=&"
//						+ "reqDesignation=&"
//						+ "selectedSessionName=&"
//						+ "class_session=&"
//						+ "ModeOfIns=P&"
//						+ "selectedModeInsName=In+Person%2C+&"
//						+ "meetingStart=LT&"
//						+ "selectedMeetingStartName=less+than&"
//						+ "meetingStartText=&"
//						+ "AndMeetingStartText=&"
//						+ "meetingEnd=LE&"
//						+ "selectedMeetingEndName=less+than+or+equal+to&"
//						+ "meetingEndText=&"
//						+ "AndMeetingEndText=&"
//						+ "daysOfWeek=I&"
//						+ "selectedDaysOfWeekName=include+only+these+days&"
//						+ "instructor=B&"
//						+ "selectedInstructorName=begins+with&"
//						+ "instructorName=&"
//						+ "search_btn_search=Search");

				// Here we can get all the information about all the classes for this subject
//				By warningLocator = By.cssSelector("span[class=\"SSSMSGWARNINGTEXT\"]");
				
//				if (isWarningDisplayed(warningLocator) == false)
//					getClassInfo(driver);
				


//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				// Back to Search Criteria page
//				driver.navigate().back();
//
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

				// Reassign these variables b/c the DOM changed
				allSubjects = driver.findElement(By.id("subject_ld")).findElements(By.tagName("option"));
				subject = allSubjects.get(i++);

			}


		}




	}
	
	public void getAllColleges() {
		By institutionDropDownList = By.cssSelector("span[class=\"cunylite_DROPDOWNLIST\"]");
//		List<WebElement> institutions = waitAndFind(institutionDropDownList).findElements(By.tagName("label"));
		List<WebElement> institutionsCode = waitAndFind(institutionDropDownList).findElements(By.tagName("input"));

		List<WebElement> institutions = waitAndFindElements(institutionDropDownList);

		for(int i = 0; i < institutions.size(); i++) {
			
			WebElement singleInstitution = institutions.get(i);
			WebElement singleInstitutionCode = institutionsCode.get(i);

			String college_code = singleInstitutionCode.getAttribute("id");
			String college_name = singleInstitution.getText().replaceFirst(" ", "");

			System.out.println('"' + college_name + '"' + ":" + " " + '"' + college_code + '"' + ",");
			
			institutions = waitAndFind(institutionDropDownList).findElements(By.tagName("label"));
			institutionsCode = waitAndFind(institutionDropDownList).findElements(By.tagName("input"));
		}
	}
	
	public void getAllSubjectsForCollege(String college_code) {
		// Select Queens College
		waitForElement(driver, "input[value=\""+college_code+"\"]");
		driver.findElement(By.cssSelector("input[value=\""+college_code+"\"]")).click();

		// Select Fall 2019
		waitForElement(driver, "option[value=\"1199\"]");
		driver.findElement(By.cssSelector("option[value=\"1199\"]")).click();

		// Click next
		driver.findElement(By.cssSelector("input[name=\"next_btn\"]")).click();

		// On Search page
		if(waitForElement(driver, "input[name=\"search_btn_search\"]")) {

			// All subjects 
			List<WebElement> allSubjects = driver.findElement(By.id("subject_ld")).findElements(By.tagName("option"));
			for (int i = 1; i < allSubjects.size(); i++) {

				WebElement subject = allSubjects.get(i);

				// Get the name of the subject and its shorthand code
				String sbj = subject.getAttribute("value");
				String sbjName = subject.getText();
				System.out.println("\""+sbj+"\": \""+sbjName+"\",");
				
				// Reassign these variables b/c the DOM changed
				allSubjects = driver.findElement(By.id("subject_ld")).findElements(By.tagName("option"));
				subject = allSubjects.get(i++);

			}


		}
	}

//	public static void setSchool(String school, String subject, String sbj) throws Exception {			
//
//		String command2 = ""+/**~!{*/"curl -X PATCH -d '{"
//				+ "\r\n\"" +((school))+ "\": {"
//				+ "\r\n  \"subjects\": {"
//				+ "\r\n  	\"" +((subject))+ "\": \"" +((sbj))+ "\""
//				+ "\r\n  }"
//				+ "\r\n}"
//				+ "\r\n}' 'https://restful-java-87120.firebaseio.com/colleges.json?print=pretty'"/**}*/;
//
//		String command3 = ""+/**~!{*/"\"" +((subject))+ "\": \"" +((sbj))+ "\""/**}*/;
//		System.out.println(command3);
//		System.out.println();
//	}

	public boolean isWarningDisplayed(By locator) {
		try {
			driver.findElement(locator).isDisplayed();
			System.out.println("is displayed");
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("is NOT displayed");
			return false;
		}
	}
	
	public void getClassInfo(WebDriver driver) {

		// Open first drop down
		waitForElement(driver, "img[src=\"images/expand.gif\"]");
		driver.findElement(By.cssSelector("img[src=\"images/expand.gif\"]")).click();

		// Opens all the drop downs 
		List<WebElement> dropDowns = driver.findElements(By.cssSelector("img[src=\"images/expand_subject.gif\"]"));
		for(WebElement item: dropDowns) {
			item.click();
		}

		// Displays all the classes
		String classSection = "";
		String divId;
		List<WebElement> divs = driver.findElements(By.cssSelector("span[class=\"cunylite_LABEL\"]"));
		int index = 0;
		// Loops through all contentDivImg's
		for(WebElement item: divs) {

			// Gets each course ie. CSCI 111 - Intro. Algorithmic Problem Solv.
			classSection = item.getText();

			divId = item.findElement(By.tagName("a")).getAttribute("href").split("'")[1];
			List<WebElement> contentRows = driver.findElement(By.id(divId)).findElements(By.tagName("a"));

			// Loops through each row in this contentDivImg
			System.out.println(classSection);
			for(int i = 0; i < contentRows.size(); i++) {
				if (i % 2 == 0) {
					int classNumber = Integer.parseInt(contentRows.get(i).getText());
					System.out.print(classNumber + "\t");
				}
				else {
					String classes = contentRows.get(i).getText();
					System.out.println(classes);
				}
			}
			System.out.println();
			index++;
		}
	}

	public static boolean waitForElement(WebDriver driver, String cssSelectorStr){
		try{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelectorStr)));
			return true; 
		}
		catch(org.openqa.selenium.NoSuchElementException e){
			return false;
		} 
	}

	public static void switchToIframe(String frameId, WebDriver driver) {
		WebElement editorFrame = driver.findElement(By.id(frameId));
		driver.switchTo().frame(editorFrame);
	}

	public static void goToSearchResults(WebDriver driver) {

		// Click Next
		waitForElement(driver, "input[value=\"Next\"]");
		driver.findElement(By.cssSelector("input[value=\"Next\"]")).click();

		// Select Subject
		waitForElement(driver, "option[value=\"MATH\"]");
		driver.findElement(By.cssSelector("option[value=\"MATH\"]")).click();

		// Select Undergraduate
		waitForElement(driver, "option[value=\"UGRD\"]");
		driver.findElement(By.cssSelector("option[value=\"UGRD\"]")).click();

		// Un-check Show Open Classes Only
		waitForElement(driver, "input[name=\"open_class\"]");
		driver.findElement(By.cssSelector("input[name=\"open_class\"]")).click();

		// Check check box In Person
		waitForElement(driver, "input[value=\"P\"]");
		driver.findElement(By.cssSelector("input[value=\"P\"]")).click();

		// Open drop down
		waitForElement(driver, "a[id=\"imageDivLink\"]");
		driver.findElement(By.cssSelector("a[id=\"imageDivLink\"]")).click();

		// Insert instructors name
		waitForElement(driver, "input[name=\"instructorName\"]");
		driver.findElement(By.cssSelector("input[name=\"instructorName\"]")).sendKeys("Liu");

		// Click search
		waitForElement(driver, "input[name=\"search_btn_search\"]");
		driver.findElement(By.cssSelector("input[name=\"search_btn_search\"]")).click();

		// Open first drop down
		waitForElement(driver, "a[id=\"imageDivLink_inst0\"]");
		driver.findElement(By.cssSelector("a[id=\"imageDivLink_inst0\"]")).click();

		// Open second drop down
		waitForElement(driver, "a[id=\"imageDivLink1\"]");
		driver.findElement(By.cssSelector("a[id=\"imageDivLink1\"]")).click();

	}


}
