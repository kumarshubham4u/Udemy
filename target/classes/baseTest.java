package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest {
	
	// adding Log4j logger
	private static Logger log = LogManager.getLogger(baseTest.class.getName());


	public static WebDriver driver;
	public Properties prop;

	@BeforeMethod
	public void browserInitialization() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");

		log.info("Initializing Browser");
		
		// for initializing Chrome Browser
		if (browserName.contains("chrome")) {

			DesiredCapabilities ch = new DesiredCapabilities();
			ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromeDriver\\chromedriver.exe");

			ChromeOptions option = new ChromeOptions();

			if (browserName.contains("headless")) {

				option.addArguments("headless");
			}

			option.merge(ch);

			driver = new ChromeDriver(option);

		}

		// for initializing Firefox Browser
		if (browserName.contains("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\drivers\\firefoxDriver\\geckodriver.exe");

			FirefoxOptions options = new FirefoxOptions();

			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new FirefoxDriver(options);

		}

		// for initializing Internet Explorer Browser
		if (browserName.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\drivers\\internetExplorerDriver\\IEDriverServer3_4.exe");
			driver = new InternetExplorerDriver();
		}
		
		log.info("Maximizing Window");
		driver.manage().window().maximize();
		
		log.info("Deleting All Cookies");
		driver.manage().deleteAllCookies();
		
		log.info("Assigning Implicit Wait");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		log.info("Hitting URL : "+ prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
	}
	
	
	
	@AfterMethod
	public void teardown() {
		
		log.info("Closing browser");
		driver.close();
	}
	
	
	public String getScreenshotPath(String testcaseName,WebDriver driver) throws IOException {
		
		
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testcaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

}
