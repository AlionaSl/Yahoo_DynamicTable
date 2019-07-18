package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import utilities.ExtentManager;

public class DriverInstance {

	public static WebDriver wd;
	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(DriverInstance.class.getName());

	public static WebDriverWait waitWd;
	public ExtentReports extReports = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {

		if (wd == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
//				System.setProperty("webdriver.gecko.driver", "/src/test/resources/executables/gecko.exe");
				wd = new FirefoxDriver();

			} else if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver.exe");
				wd = new ChromeDriver();
				log.debug("Chrome Launched !!!");

			} else if (config.getProperty("browser").equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/IEDriver.exe");
				wd = new InternetExplorerDriver();
			}

			wd.get(config.getProperty("testsiteUrl"));
			log.debug("Navigated to: " + config.getProperty("testsiteUrl"));
			wd.manage().window().maximize();

			waitingWebDriver();

//			waitWd = new WebDriverWait(wd, 5);
		}
	}

	public void waitingWebDriver() {
		wd.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
	}

	@AfterSuite
	public void teerDown() {
		if (wd != null) {
			wd.quit();
		}
		log.debug("Test execution completed!!!");
	}
}
