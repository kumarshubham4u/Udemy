package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.baseTest;

public class Test_Udemy extends baseTest {

	// adding Log4j logger
	private static Logger log = LogManager.getLogger(baseTest.class.getName());

	@Test
	public void login() {

		LandingPage objLand = new LandingPage(driver);
		LoginPage objLogin = new LoginPage(driver);

		log.debug("Clicking on Login link");
		objLand.getLoginLink().click();
		log.info("Login Link is clicked");

		log.debug("Sending keys in email field");
		objLogin.getEmailID().sendKeys("kumarshubham2994@gmail.com");
		log.info("Email id is provided");

		log.debug("Sending keys in password field");
		objLogin.getPassword().sendKeys("Shubham@4u");
		log.info("password is provided");

		log.debug("Clicking on Login button");
		objLogin.getLoginButton().click();
		log.info("Login button is clicked");
	}

}
