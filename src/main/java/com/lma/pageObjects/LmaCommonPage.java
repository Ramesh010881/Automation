package com.lma.pageObjects;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.lma.utilities.MyOwnException;

public class LmaCommonPage extends LmaPages {

	private static final Logger log = LogManager.getLogger(LmaCommonPage.class.getName());

	WebDriver ldriver;
	
	public LmaCommonPage(WebDriver driver) {
		super(driver);
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	// ========================= Common PAGE OBJECTS throughout Application  =========================                     
	
	

	// ========================= Common PAGE METHODS throughout Application ============================

	
	
	public void initiateBrowser() throws MyOwnException {

		log.info("METHOD(initiateBrowser) EXECUTION STARTED SUCCESSFULLY");
		try {
			if (sBrowserType.equalsIgnoreCase("") || sBrowserType == null)
				sBrowser = configProps.getProperty("browserType");
			else
				sBrowser = sBrowserType;
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
			if (sBrowser.equalsIgnoreCase("firefox")) {
				//TODO: Firefox Stuff
			} else if (sBrowser.equalsIgnoreCase("ie")) {
				//TODO-IE stuff
			} else if (sBrowser.equalsIgnoreCase("chrome")) {
				System.out.println("inside chrome");
				System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
				System.out.println("inside chrome1");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--test-type");
				webDriver = new ChromeDriver(options);
				System.out.println("inside chrome2");
				log.info("Initiated Chrome session");
				webDriver.manage().window().maximize();
				System.out.println("inside chrome3");
				
			}
			
			driver = new EventFiringWebDriver(webDriver);
			driver.manage().deleteAllCookies();
			int implicitWaitTime = 10;
			if ("ie".equalsIgnoreCase(configProps.getProperty("browserType"))) {
				driver.manage().timeouts().implicitlyWait(implicitWaitTime * 2, TimeUnit.SECONDS);
			} else {
				driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
			}
			
			lmaPages = new LmaPages(webDriver);
		} catch (Exception exp) {
			log.error(exp.getMessage());			
		}
		log.info("METHOD(initiateBrowser) EXECUTED SUCCESSFULLY");
	}

}
