package com.lma.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lma.accelarators.ActionDriver;
import com.lma.accelarators.Base;
import com.lma.utilities.MyOwnException;
import com.lma.utilities.Report;

public class LmaWorkFlow extends Base {

	private static final Logger log = LogManager.getLogger(LmaWorkFlow.class.getName());

	public void LaunchApplication() throws InterruptedException, MyOwnException {

		log.info("WORKFLOW(newTrackerListingProcess) EXECUTION STARTED SUCCESSFULLY");
		try {
			
			lmaPages.LoginPage().initiateBrowser();
			driver.navigate().to(configProps.getProperty("LMAUrl"));
			
			ActionDriver.screenShot(System.getProperty("user.dir") + configProps.getProperty("results") + "_"
					+ testRunTimeStamp + "\\" + "LmaHomePage.png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS", "Successfully logged into Health Choice",
					System.getProperty("user.dir") + configProps.getProperty("results") + "_" + testRunTimeStamp + "\\"
							+ "LmaHomePage.png");

		} catch (Exception exp) {
			log.error(exp.getMessage());
			Report.logTestCaseStatus(parentTestCase, "FAIL",
					"<font color=red><b>IU Is Not logged in Successfully</b></font><br />" + exp.getMessage()
							+ "<br />");
			throwException("UNABLE TO INITIATE THE login PROCESS \n" + exp.getMessage() + "\n");
		}
		log.info("WORKFLOW(newTrackerListingProcess) EXECUTED SUCCESSFULLY");
	}
	
	public void AddCounterParty() throws InterruptedException, MyOwnException {

		log.info("WORKFLOW(newTrackerListingProcess) EXECUTION STARTED SUCCESSFULLY");
		try {
			
			lmaPages.CounterParty().AddCounertParty(parentTestCase);
			driver.navigate().to(configProps.getProperty("LMAUrl"));
			
			ActionDriver.screenShot(System.getProperty("user.dir") + configProps.getProperty("results") + "_"
					+ testRunTimeStamp + "\\" + "LmaHomePage.png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS", "Successfully logged into Health Choice",
					System.getProperty("user.dir") + configProps.getProperty("results") + "_" + testRunTimeStamp + "\\"
							+ "LmaHomePage.png");

		} catch (Exception exp) {
			log.error(exp.getMessage());
			Report.logTestCaseStatus(parentTestCase, "FAIL",
					"<font color=red><b>IU Is Not logged in Successfully</b></font><br />" + exp.getMessage()
							+ "<br />");
			throwException("UNABLE TO INITIATE THE login PROCESS \n" + exp.getMessage() + "\n");
		}
		log.info("WORKFLOW(newTrackerListingProcess) EXECUTED SUCCESSFULLY");
	}

}
