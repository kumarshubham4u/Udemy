package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}
	
	
	private By emailID= By.name("emai1l");
	private By password=By.name("password");
	private By loginButton=By.name("submit");
	
	public WebElement getEmailID() {
		
		return driver.findElement(emailID);
	}
	
	public WebElement getPassword() {
		
		return driver.findElement(password);
	}
	
	public WebElement getLoginButton() {
		
		return driver.findElement(loginButton);
	}
}
