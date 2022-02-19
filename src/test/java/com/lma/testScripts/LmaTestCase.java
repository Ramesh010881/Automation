package com.lma.testScripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.lma.utilities.DataProviderRepository;
import com.lma.utilities.Report;
import com.lma.utilities.MyOwnException;
import com.lma.accelarators.ActionDriver;
import com.lma.pageObjects.LmaWorkFlow;

public class LmaTestCase extends LmaWorkFlow {

	private static final Logger log = LogManager.getLogger(LmaTestCase.class.getName());
	//(dataProvider = "LmaTestData", dataProviderClass = DataProviderRepository.class)
	
	@Test
	public void AddCounterParty(/* String AddCounterParty */)
			throws InterruptedException, MyOwnException {

		log.info("METHOD(AddCounterParty) EXECUTION STARTED SUCCESSFULLY");
		try {
			
			
			//RetrievingMapData(AddCounterParty);
			//System.out.println("data.."+lmaCounterPartyMap.get("CounterPartyName"));
			parentTestCase = Report.makeTestCaseEntry(report, "LMA_POC_Automation");
			Report.assignCategoryToTestCase(parentTestCase, "TC1");
			System.out.println("before lunahc");
			LaunchApplication();

		} catch (Exception exp) {
			log.error(exp.getMessage());
			throwException("Login SUCCESSFUL\n" + exp.getMessage());
		}
		log.info("METHOD(IU_TC1) EXECUTED SUCCESSFULLY");
	}

	@AfterMethod(alwaysRun = true)
	public void afterExecutionOfEachTest(Method method, ITestContext ctx, ITestResult status)
			throws InterruptedException, IOException, MyOwnException {
		
		log.info("AFTER METHOD EXECUTION STARTED SUCCESSFULLY");
		try {
			
			if (ITestResult.SUCCESS == status.getStatus()) {
				/*Report.logTestCaseStatus(parentTestCase, "PASS",
						"IU_TC1 Is Successful");*/

			} else if (ITestResult.FAILURE == status.getStatus()) {
				ActionDriver.screenShot(System.getProperty("user.dir") + configProps.getProperty("results") + "_"
						+ testRunTimeStamp + "\\" + "1_Login Failed.png");
				Report.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
						"Listing Process And IPO Process Is Not Successful",
						System.getProperty("user.dir") + configProps.getProperty("results") + "_" + testRunTimeStamp
						+ "\\" + "1_Login Failed.png");
			} else if (ITestResult.SKIP == status.getStatus()) {
				Report.logTestCaseStatus(parentTestCase, "SKIP", "Test Case Is Skipped");
			}
			Report.writeContents(report);
			
			//driver.quit();
			
		} catch (Exception exp) {
			log.error(exp.getMessage());
			Report.logTestCaseStatus(parentTestCase, "FAIL",
					"<font color=red><b>After Method Is Not Successful</b></font><br />" + exp.getMessage() + "<br />");
			throwException("AFTER METHOD IS NOT SUCCESSFUL\n" + exp.getMessage());
		}
		log.info("AFTER METHOD EXECUTED SUCCESSFULLY");
	}

	private void RetrievingMapData(String AddCounterParty) throws MyOwnException {
		
		log.info("METHOD(RetrievingMapData) EXECUTION STARTED SUCCESSFULLY");
		try {
			if (!AddCounterParty.isEmpty()) {
				lmaCounterPartyMap = ActionDriver.splitToMap(AddCounterParty);
			}
		} catch (Exception exp) {
			log.error(exp.getMessage());
			throwException("UNABLE TO FORM MAPS WITH THE TEST DATA RETRIEVED FROM FROM THE GIVEN STRINGS PARAMETERS\n"
					+ exp.getMessage() + "\n");
		}
		log.info("METHOD(RetrievingMapData) EXECUTED SUCCESSFULLY");
	}

}
