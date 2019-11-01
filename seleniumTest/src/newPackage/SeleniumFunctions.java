package newPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumFunctions {

	protected WebDriver driver; 
	protected WebDriverWait wait;

	public SeleniumFunctions(WebDriver _driver, WebDriverWait _wait) {
		// TODO Auto-generated constructor stub
		this.driver = _driver;
		this.wait = _wait;
	}

	public void visit(String url) {
		driver.get(url);
	}
	
	public WebElement waitFor(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement find(By locator) {
		return driver.findElement(locator);
	}

	public java.util.List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}
	
	public void sendKey(By locator, String key) {
		find(locator).sendKeys(key);
	}

	public void click(By locator) {
		find(locator).click();
	}

	public WebElement waitAndFind(By locator){
		waitFor(locator);
		return find(locator);
	}
	
	public java.util.List<WebElement> waitAndFindElements(By locator){
		waitFor(locator);
		return findElements(locator);
	}

	public void waitAndSendKey(By locator, String key){
		waitFor(locator);
		sendKey(locator, key);
	}

	public void waitAndClick(By locator){
		waitFor(locator);
		click(locator);
	}
	
    public void SwitchToIframe(By locator) {
        driver.switchTo().frame(find(locator));
    }
}
