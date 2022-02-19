package com.lma.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.lma.accelarators.ActionDriver;
import com.lma.utilities.MyOwnException;
import com.lma.utilities.Report;
import com.lma.wrapperClasses.MyWait;
import com.lma.wrapperClasses.MyWebElement;

public class LmaLoginPage extends LmaCommonPage {

	private static final Logger log = LogManager.getLogger(LmaLoginPage.class.getName());

	WebDriver ldriver;
	ExtentTest testCase;

	public LmaLoginPage(WebDriver driver) {
		super(driver);

		this.ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	// ===================== PAGE OBJECTS ======================

	@FindBy(how = How.NAME, using = "ctl00$ContentPlaceHolder1$Username")
	public WebElement userNameTextBox;
	
	@FindBy(how = How.XPATH, using = "//a[@class='emp-login-btn']")
	public WebElement employeeLoginButton;

	@FindBy(how = How.NAME, using = "ctl00$ContentPlaceHolder1$Password")
	public WebElement passwordTextBox;

	@FindBy(how = How.XPATH, using = "//input[@value='Login']")
	public WebElement logInButton;
	
	@FindBy(how = How.ID, using = "contenthead")
	public WebElement home;

	// ===================== PAGE METHODS ======================

	public void login(String divorinternal, ExtentTest test) throws InterruptedException, MyOwnException {

		log.info("METHOD(login) EXECUTION STARTED SUCCESSFULLY");
		try {
			/*MyWait.until(ldriver, "ELEMENT_VISIBLE", 90, employeeLoginButton);
			MyWebElement.clickOn(employeeLoginButton);*/
			Thread.sleep(2000);
			
			/*Actions builder = new Actions(driver);
	        Actions seriesOfActions = builder.moveToElement(userNameTextBox).click().sendKeys(userNameTextBox, "19678");	        
	        seriesOfActions.perform();
	        builder.moveToElement(passwordTextBox).click().sendKeys(passwordTextBox, "brahmini@456").perform();*/
			//userNameTextBox.sendKeys("20843");
			
				MyWebElement.enterText(userNameTextBox, lmaCounterPartyMap.get("IUUserName"));
				MyWebElement.enterText(passwordTextBox, lmaCounterPartyMap.get("IUPassword"));
				
				ActionDriver.screenShot(System.getProperty("user.dir") + configProps.getProperty("results") + "_"
						+ testRunTimeStamp + "\\1_HelahChoiceLogin_Screen.png");
				Report.logTestCaseStatusWithSnapShot(test, "INFO", "Login To Health Choice is initiated successfully",
						System.getProperty("user.dir") + configProps.getProperty("results") + "_" + testRunTimeStamp + "\\" + "1_HelahChoiceLogin_Screen.png");
			
			logInButton.click();	
			
			//Assert.assertTrue(home.getText().equalsIgnoreCase("HOME"));
			
			// LogInformation for this login method
			//Report.logTestCaseStatus(test, "INFO", "Login To Health Choice is initiated successfully");
			
			

		} 
		catch (AssertionError exp) {
			log.error(exp.getMessage());
			Report.logTestCaseStatus(test, "FAIL", "<font color=red><b>Login To ePortal Is Not Successful</b></font><br />"
					+ exp.getMessage() + "<br />");
			ActionDriver.screenShot(System.getProperty("user.dir") + configProps.getProperty("results") + "_"
					+ testRunTimeStamp + "\\1_Login_failed.png");
			throwException(
					"UNABLE TO LOGIN INTO THE LIMA APPLICATION FROM THE METHOD login\n" + exp.getMessage() + "\n");
		}catch (Exception exp) {
			log.error(exp.getMessage());
			Report.logTestCaseStatus(test, "FAIL", "<font color=red><b>Login To ePortal Is Not Successful</b></font><br />"
					+ exp.getMessage() + "<br />");
			ActionDriver.screenShot(System.getProperty("user.dir") + configProps.getProperty("results") + "_"
					+ testRunTimeStamp + "\\1_Login_failed.png");
			throwException(
					"UNABLE TO LOGIN INTO THE LIMA APPLICATION FROM THE METHOD login\n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");
	}

}
