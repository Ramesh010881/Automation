package com.lma.pageObjects;

import java.awt.Robot;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
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

public class LmaCounterPartyPage extends LmaCommonPage{

	private static final Logger log = LogManager.getLogger(LmaCounterPartyPage.class.getName());

	WebDriver ldriver;
	ExtentTest testCase;

	public LmaCounterPartyPage(WebDriver driver) {
		super(driver);

		this.ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	// ===================== PAGE OBJECTS ======================

	@FindBy(how = How.LINK_TEXT, using = "Add one")
	public WebElement AddLegalEntity;

	@FindBy(how = How.NAME, using = "name")
	public WebElement CounterPartyName;

	@FindBy(how = How.NAME, using = "ofCounterpartyId")
	public WebElement CounterPartyID;
	
	@FindBy(how = How.ID, using = "shortName")
	public WebElement ShortName;
	
	@FindBy(how = How.ID, using = "totalCapital")
	public WebElement TotalCapital;
	
	@FindBy(how = How.ID, using = "totalAssets")
	public WebElement TotalAssets;
	
	@FindBy(how = How.ID, using = "capitalAssetsAsOfDate")
	public WebElement CapitalAssetsAsOfDate;
	
	@FindBy(how = How.NAME, using = "addressLine")
	public WebElement AddressLine;
	
	@FindBy(how = How.NAME, using = "city")
	public WebElement City;
		
	@FindBy(how = How.CLASS_NAME, using = "fhlbDropDownNoMargin")
	public WebElement State;
	
	@FindBy(how = How.NAME, using = "zip")
	public WebElement Zip;
		
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_ContentPlaceHolder1_ReportViewer1_ctl05']/div/div[1]/table/tbody/tr/td[9]")
	public WebElement invoiceReportTable;
	
	
	
	
	
	
	
	
	// ===================== PAGE METHODS ======================

	public void AddCounertParty(ExtentTest test) throws InterruptedException, MyOwnException {

		try {
			
			Thread.sleep(2000);
			MyWebElement.clickOn(AddLegalEntity);
			
			Thread.sleep(2000);
			
			MyWebElement.enterText(CounterPartyName, lmaCounterPartyMap.get("CounterPartyName"));
			MyWebElement.enterText(CounterPartyID, lmaCounterPartyMap.get("CounterPartyID"));
			MyWebElement.enterText(ShortName, lmaCounterPartyMap.get("NameCode"));
			
			MyWebElement.enterText(CapitalAssetsAsOfDate, lmaCounterPartyMap.get("AssetsAsOfDate"));
			MyWebElement.enterText(TotalCapital, lmaCounterPartyMap.get("TotalCapital"));
			MyWebElement.enterText(TotalAssets, lmaCounterPartyMap.get("TotalAssets"));
			
			JavascriptExecutor je = (JavascriptExecutor) driver; 
			je.executeScript("arguments[0].scrollIntoView(true);",AddressLine);
			
			MyWebElement.enterText(AddressLine, lmaCounterPartyMap.get("Address"));			
			MyWebElement.enterText(City, lmaCounterPartyMap.get("City"));
			Thread.sleep(2000);
			MyWebElement.clickOn(State);
			
			Robot rb = new Robot();
			rb.keyPress(java.awt.event.KeyEvent.VK_DOWN);
			rb.keyRelease(java.awt.event.KeyEvent.VK_DOWN);
			rb.keyPress(java.awt.event.KeyEvent.VK_DOWN);
			rb.keyRelease(java.awt.event.KeyEvent.VK_DOWN);
			rb.keyPress(java.awt.event.KeyEvent.VK_DOWN);
			rb.keyRelease(java.awt.event.KeyEvent.VK_DOWN);
			rb.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			rb.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			MyWebElement.enterText(Zip, lmaCounterPartyMap.get("ZipCode"));
			
			
			ActionDriver.screenShot(System.getProperty("user.dir") + configProps.getProperty("results") + "_"
					+ testRunTimeStamp + "\\" + "3_SearchGroupPage.png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS", "Search Group page opened successfully",
					System.getProperty("user.dir") + configProps.getProperty("results") + "_" + testRunTimeStamp + "\\" + "3_SearchGroupPage.png");
			

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
		log.info("METHOD(searchGroup) EXECUTED SUCCESSFULLY");
		
	}	
	
	

}
