package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	public WebDriver driver;
	public LandingPage(WebDriver driver) {
		
		this.driver=driver;
	}
	
	
	private By loginLink= By.linkText("Log in");
	
	
	
	public WebElement getLoginLink() {
		
		return driver.findElement(loginLink);
	}
}
