package com.lma.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.lma.accelarators.ActionDriver;

public class Report extends ActionDriver {

	private static final Logger log = LogManager.getLogger(Report.class.getName());

	public static ExtentReports extentReport;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest testCase;

	public static ExtentHtmlReporter initializeHTML(String reportName, boolean status) throws MyOwnException {

		log.info("INTEND TO INITIALIZE AN HTML REPORT");
		try {
			if (notEmpty(reportName)) {
				if (formatGiven(reportName) == true) {
					fileFormat = getFileFormat(reportName);
					if (fileFormatIs(".html")) {
						createInstanceOfHTMLReport(reportName);
						appendToExistingReport(status);
						setHTMLReportName("Test Case Summary");
						setHTMLReportTheme(Theme.DARK);
					} else {
						log.error("REPORT FORMAT IS INCORRECT, IT SHOULD BE .HTML");
						throwException("REPORT FORMAT IS INCORRECT, IT SHOULD BE .HTML" + "\n");
					}
				} else {
					log.error("REPORT FORMAT IS MISSING");
					throwException("REPORT FORMAT IS MISSING" + "\n");
				}
			} else {
				log.error("REPORT NAME SEEMS TO BE EMPTY");
				throwException("REPORT NAME SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO INITIALIZE HTML REPORT(" + reportName + ")\n" + e.getMessage());
			throwException("UNABLE TO INITIALIZE HTML REPORT(" + reportName + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY INITIALIZED AN HTML REPORT(" + reportName + ")");
		return htmlReporter;
	}

	private static void setHTMLReportName(String reportName) throws MyOwnException {

		log.info("INTEND TO SET A NAME FOR THE HTML REPORT ");
		try {
			htmlReporter.config().setReportName(reportName);
		} catch (Exception e) {
			log.error("UNABLE TO SET HTML REPORT NAME(" + reportName + ")");
			throwException("UNABLE TO SET HTML REPORT NAME(" + reportName + ")" + "\n");
		}
		log.info("HTML REPORT NAME(" + reportName + ") WAS SET SUCCESSFULLY");
	}

	private static void setHTMLReportTheme(Theme theme) throws MyOwnException {

		log.info("INTEND TO SET A THEME FOR THE HTML REPORT ");
		try {
			htmlReporter.config().setTheme(theme);
		} catch (Exception e) {
			log.error("UNABLE TO SET HTML REPORT THEME");
			throwException("UNABLE TO SET HTML REPORT THEME" + "\n");
		}
		log.info("HTML REPORT THEME WAS SET SUCCESSFULLY");
	}

	private static void appendToExistingReport(boolean status) throws MyOwnException {

		log.info("INTED TO ADD THE CONTENTS TO EXTENT REPORT BASED ON THE APPEND STATUS");
		try {
			htmlReporter.setAppendExisting(status);
		} catch (Exception e) {
			log.error("UNABLE TO ADD CONTENTS TO EXTENT REPORT BASED ON THE APPEND STATUS(" + status + ")");
			throwException("UNABLE TO ADD CONTENTS TO EXTENT REPORT BASED ON THE APPEND STATUS(" + status + ")" + "\n");
		}
		log.info("CONTENTS ARE ADDED TO EXTENT REPORT BASED ON THE APPEND STATUS(" + status + ")");
	}

	private static ExtentHtmlReporter createInstanceOfHTMLReport(String reportName) throws MyOwnException {

		log.info("INTEND TO CREATE AN INSTANCE OF HTML REPORT WITH NAME");
		try {
			htmlReporter = new ExtentHtmlReporter(reportName);
		} catch (Exception e) {
			log.error("UNABLE TO CREATE AN INSTANCE OF HTML REPORT WITH NAME(" + reportName + ")");
			throwException("UNABLE TO CREATE AN INSTANCE OF HTML REPORT WITH NAME(" + reportName + ")" + "\n");
		}
		log.info("SUCCESSFULLY CREATED AN INSTANCE OF HTML REPORT WITH NAME(" + reportName + ")");
		return htmlReporter;
	}

	private static ExtentReports createInstanceOfExtentReport() throws MyOwnException {

		log.info("INTEND TO CREATE AN INSTANCE OF EXTENT REPORT");
		try {
			extentReport = new ExtentReports();
		} catch (Exception e) {
			log.error("UNABLE TO CREATE AN INSTANCE OF EXTENT REPORT");
			throwException("UNABLE TO CREATE AN INSTANCE OF EXTENT REPORT" + "\n");
		}
		log.info("SUCCESSFULLY CREATED AN INSTANCE OF EXTENT REPORT");
		return extentReport;
	}

	private static void attachHTMLReport(ExtentHtmlReporter htmlReporter) throws MyOwnException {

		log.info("INTEND TO ATTACH HTML REPORT TO EXTENT REPORT");
		try {
			extentReport.attachReporter(htmlReporter);
		} catch (Exception e) {
			log.error("UNABLE TO ATTACH HTML REPORT TO EXTENT REPORT");
			throwException("UNABLE TO ATTACH HTML REPORT TO EXTENT REPORT" + "\n");
		}
		log.info("SUCCESSFULLY ATTACHED HTML REPORT TO EXTENT REPORT");
	}

	public static ExtentReports initialize(String reportName, boolean status) throws MyOwnException {

		log.info("INTEND TO CREATE AN INSTANCE OF EXTENT REPORT WITH NAME AND APPEND STATUS");
		htmlReporter = initializeHTML(reportName, status);
		extentReport = createInstanceOfExtentReport();
		try {
			if (htmlReporter != null) {
				attachHTMLReport(htmlReporter);
			} else {
				log.error("HTML REPORT INSTANCE SEEMS TO BE NULL");
				throwException("HTML REPORT INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO INITIALIZE TEST EXECUTION REPORT(" + reportName + ") WITH APPEND STATUS(" + status
					+ ")\n" + e.getMessage());
			throwException("UNABLE TO INITIALIZE TEST EXECUTION REPORT(" + reportName + ") WITH APPEND STATUS(" + status
					+ ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY CREATED AN INSTANCE OF EXTENT REPORT WITH NAME(" + reportName + ") AND APPEND STATUS("
				+ status + ")");
		return extentReport;
	}

	public static void recordSystemInfo(ExtentReports report, String key, String value) throws MyOwnException {

		log.info("INTEND TO ADD SYSTEM INFORMATION IN EXTENT REPORT");
		try {
			report.setSystemInfo(key, value);
		} catch (Exception e) {
			log.error("UNABLE TO ADD SYSTEM INFORMATION(" + key + " : " + value + ") IN EXTENT REPORT");
			throwException("UNABLE TO ADD SYSTEM INFORMATION(" + key + " : " + value + ") IN EXTENT REPORT" + "\n");
		}
		log.info("SUCCESSFULLY ADDED SYSTEM INFORMATION(" + key + " : " + value + ") IN EXTENT REPORT");
	}

	public static void writeContents(ExtentReports report) throws MyOwnException {

		log.info("INTEND TO WRITE ALL THE CONENTS INTO EXTENT REPORT");
		try {
			report.flush();
		} catch (Exception e) {
			log.error("UNABLE TO WRITE CONTENTS INTO EXTENT REPORT");
			throwException("UNABLE TO WRITE CONTENTS INTO EXTENT REPORT" + "\n");
		}
		log.info("SUCCESSFULY WROTE ALL THE CONENTS INTO EXTENT REPORT");
	}

	public static ExtentTest makeTestCaseEntry(ExtentReports report, String testCaseName) throws MyOwnException {

		log.info("INTEND TO CREATE AN INSTANCE OF TEST CASE WITH NAME IN EXTENT REPORT");
		try {
			if (notEmpty(testCaseName)) {
				testCase = report.createTest(testCaseName);
			} else {
				log.error("TEST CASE NAME SEEMS TO BE EMPTY");
				throwException("TEST CASE NAME SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO CREATE AN INSTANCE OF TEST CASE WITH NAME(" + testCaseName + ") IN EXTENT REPORT\n"
					+ e.getMessage());
			throwException("UNABLE TO CREATE AN INSTANCE OF TEST CASE WITH NAME(" + testCaseName
					+ ") IN EXTENT REPORT\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULY CREATED AN INSTANCE OF TEST CASE WITH NAME(" + testCaseName + ") IN EXTENT REPORT");
		return testCase;
	}

	public static ExtentTest makeTestCaseEntry(ExtentReports report, String testCaseName, String testCaseDesc)
			throws MyOwnException {

		log.info("INTEND TO CREATE AN INSTANCE OF TEST CASE WITH NAME AND DESCRIPTION IN EXTENT REPORT");
		try {
			if (notEmpty(testCaseName)) {
				if (notEmpty(testCaseDesc)) {
					testCase = report.createTest(testCaseName, testCaseDesc);
				} else {
					log.error("TEST DESCRIPTION SEEMS TO BE EMPTY");
					throwException("TEST DESCRIPTION SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("TEST CASE NAME SEEMS TO BE EMPTY");
				throwException("TEST CASE NAME SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO CREATE AN INSTANCE OF TEST CASE WITH NAME(" + testCaseName + ") AND DESCRIPTION("
					+ testCaseDesc + ") IN EXTENT REPORT\n" + e.getMessage());
			throwException("UNABLE TO CREATE AN INSTANCE OF TEST CASE WITH NAME(" + testCaseName + ") AND DESCRIPTION("
					+ testCaseDesc + ") IN EXTENT REPORT\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULY CREATED AN INSTANCE OF TEST CASE WITH NAME(" + testCaseName + ") AND DESCRIPTION("
				+ testCaseDesc + ") IN EXTENT REPORT");
		return testCase;
	}

	public static ExtentTest appendChildTestCase(ExtentTest parentTestCase, String childTestCaseName)
			throws MyOwnException {

		log.info("INTEND TO APPEND CHILD TEST CASE TO PARENT TEST CASE IN EXTENT REPORT");
		try {
			if (parentTestCase != null) {
				if (notEmpty(childTestCaseName)) {
					testCase = parentTestCase.createNode(childTestCaseName);
				} else {
					log.error("CHILD TEST CASE NAME SEEMS TO BE EMPTY");
					throwException("CHILD TEST CASE NAME SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("PARENT TEST CASE INSTANCE SEEMS TO BE NULL");
				throwException("PARENT TEST CASE INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO APPEND CHILD TEST CASE(" + childTestCaseName + ") TO PARENT(" + parentTestCase
					+ ") IN EXTENT REPORT\n" + e.getMessage());
			throwException("UNABLE TO APPEND CHILD TEST CASE(" + childTestCaseName + ") TO PARENT(" + parentTestCase
					+ ") IN EXTENT REPORT\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULY APPENDED CHILD TEST CASE(" + childTestCaseName + ") TO PARENT TEST CASE(" + parentTestCase
				+ ") IN EXTENT REPORT");
		return testCase;
	}

	public static ExtentTest appendChildTestCase(ExtentTest parentTestCase, String childTestCaseName,
			String childTestCaseDesc) throws MyOwnException {

		log.info("INTEND TO APPEND CHILD TEST CASE WITH NAME AND DESCRIPTION TO PARENT TEST CASE IN EXTENT REPORT");
		try {
			if (parentTestCase != null) {
				if (notEmpty(childTestCaseName)) {
					if (notEmpty(childTestCaseDesc)) {
						testCase = parentTestCase.createNode(childTestCaseName, childTestCaseDesc);
					} else {
						log.error("TEST DESCRIPTION SEEMS TO BE EMPTY");
						throwException("TEST DESCRIPTION SEEMS TO BE EMPTY" + "\n");
					}
				} else {
					log.error("CHILD TEST CASE NAME SEEMS TO BE EMPTY");
					throwException("CHILD TEST CASE NAME SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("PARENT TEST CASE INSTANCE SEEMS TO BE NULL");
				throwException("PARENT TEST CASE INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO APPEND CHILD TEST CASE WITH NAME(" + childTestCaseName + ") AND DESCRIPTION("
					+ childTestCaseDesc + ") TO PARENT TEST CASE(" + parentTestCase + ") IN EXTENT REPORT\n"
					+ e.getMessage());
			throwException("UNABLE TO APPEND CHILD TEST CASE WITH NAME(" + childTestCaseName + ") AND DESCRIPTION("
					+ childTestCaseDesc + ") TO PARENT TEST CASE(" + parentTestCase + ") IN EXTENT REPORT\n"
					+ e.getMessage() + "\n");
		}
		log.info("SUCCESSFULY APPENDED CHILD TEST CASE WITH NAME(" + childTestCaseName + ") AND DESCRIPTION("
				+ childTestCaseDesc + ") TO PARENT TEST CASE(" + parentTestCase + ") IN EXTENT REPORT");
		return testCase;
	}

	public static void assignCategoryToTestCase(ExtentTest test, String categoryName) throws MyOwnException {

		log.info("INTEND TO ASSIGN CATAGORY TO A TEST CASE IN EXTENT REPORT");
		try {
			test.assignCategory(categoryName);
		} catch (Exception e) {
			log.error("UNABLE TO ASSIGN THE CATAGORY(" + categoryName + ") TO A TEST CASE IN EXTENT REPORT");
			throwException("UNABLE TO ASSIGN THE CATAGORY(" + categoryName + ") TO A TEST CASE IN EXTENT REPORT\n"
					+ e.getMessage() + "\n");
		}
		log.info("SUCCESSFULY ASSIGNED THE CATAGORY(" + categoryName + ") TO A TEST CASE IN EXTENT REPORT");
	}

	public static void assignAuthorsToTestCase(ExtentTest test, String authorName) throws MyOwnException {

		log.info("INTEND TO ASSIGN AUTHOR TO A TEST CASE IN EXTENT REPORT");
		try {
			test.assignAuthor(authorName);
		} catch (Exception e) {
			log.error("UNABLE TO ASSIGN THE AUTHOR(" + authorName + ") TO A TEST CASE IN EXTENT REPORT");
			throwException("UNABLE TO ASSIGN THE AUTHOR(" + authorName + ") TO A TEST CASE IN EXTENT REPORT\n"
					+ e.getMessage() + "\n");
		}
		log.info("SUCCESSFULY ASSIGNED THE AUTHOR(" + authorName + ") TO A TEST CASE IN EXTENT REPORT");
	}

	public static void logTestCaseStatus(ExtentTest test, String logStatus, String logStatusDesc)
			throws MyOwnException {

		log.info("INTEND TO LOG TEST CASE STATUS WITH DESCRIPTION IN EXTENT REPORT");
		try {
			if (notEmpty(logStatus)) {
				if (notEmpty(logStatusDesc)) {
					if (logStatus.equalsIgnoreCase("PASS")) {
						test.log(Status.PASS, logStatusDesc);
					} else if (logStatus.equalsIgnoreCase("FAIL")) {
						test.log(Status.FAIL, logStatusDesc);
					} else if (logStatus.equalsIgnoreCase("SKIP")) {
						test.log(Status.SKIP, logStatusDesc);
					} else if (logStatus.equalsIgnoreCase("INFO")) {
						test.log(Status.INFO, logStatusDesc);
					} else if (logStatus.equalsIgnoreCase("ERROR")) {
						test.log(Status.ERROR, logStatusDesc);
					} else if (logStatus.equalsIgnoreCase("WARNING")) {
						test.log(Status.WARNING, logStatusDesc);
					} else {
						log.error("LOG STATUS SEEMS TO BE INCORRECT");
						throwException("LOG STATUS SEEMS TO BE INCORRECT" + "\n");
					}
				} else {
					log.error("LOG STATUS DESCRIPTION SEEMS TO BE EMPTY");
					throwException("LOG STATUS DESCRIPTION SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("LOG STATUS SEEMS TO BE EMPTY");
				throwException("LOG STATUS SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO LOG THE TEST CASE STATUS(" + logStatus + ") WITH DESCRIPTION(" + logStatusDesc
					+ ") IN EXTENT REPORT\n" + e.getMessage());
			throwException("UNABLE TO LOG THE TEST CASE STATUS(" + logStatus + ") WITH DESCRIPTION(" + logStatusDesc
					+ ") IN EXTENT REPORT\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULY LOGED THE STATUS(" + logStatus + ") WITH DESCRIPTION(" + logStatusDesc
				+ ") IN EXTENT REPORT");
	}

	public static void logTestCaseStatusWithSnapShot(ExtentTest test, String logStatus, String logStatusDesc,
			String imageNameWithPath) throws MyOwnException {

		log.info("INTEND TO LOG TEST CASE STATUS WITH DESCRIPTION AND SNAPSHOT IN EXTENT REPORT");
		try {
			if (notEmpty(logStatus)) {
				if (notEmpty(logStatusDesc)) {
					if (notEmpty(imageNameWithPath)) {
						if (filePresent(imageNameWithPath) == true) {
							if (logStatus.equalsIgnoreCase("PASS")) {
								test.log(Status.PASS, logStatusDesc).addScreenCaptureFromPath(imageNameWithPath);
							} else if (logStatus.equalsIgnoreCase("FAIL")) {
								test.log(Status.FAIL, logStatusDesc).addScreenCaptureFromPath(imageNameWithPath);
							} else if (logStatus.equalsIgnoreCase("SKIP")) {
								test.log(Status.SKIP, logStatusDesc).addScreenCaptureFromPath(imageNameWithPath);
							} else if (logStatus.equalsIgnoreCase("INFO")) {
								test.log(Status.INFO, logStatusDesc).addScreenCaptureFromPath(imageNameWithPath);
							} else if (logStatus.equalsIgnoreCase("ERROR")) {
								test.log(Status.ERROR, logStatusDesc).addScreenCaptureFromPath(imageNameWithPath);
							} else if (logStatus.equalsIgnoreCase("WARNING")) {
								test.log(Status.WARNING, logStatusDesc).addScreenCaptureFromPath(imageNameWithPath);
							} else {
								log.error("LOG STATUS SEEMS TO BE INCORRECT");
								throwException("LOG STATUS SEEMS TO BE INCORRECT" + "\n");
							}
						} else {
							log.error("IMAGE FILE DOES NOT EXIST");
							throwException("IMAGE FILE DOES NOT EXIST" + "\n");
						}
					} else {
						log.error("IMAGE PATH SEEMS TO BE EMPTY");
						throwException("IMAGE PATH SEEMS TO BE EMPTY" + "\n");
					}
				} else {
					log.error("LOG STATUS DESCRIPTION SEEMS TO BE EMPTY");
					throwException("LOG STATUS DESCRIPTION SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("LOG STATUS SEEMS TO BE EMPTY");
				throwException("LOG STATUS SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO LOG THE TEST CASE STATUS(" + logStatus + ") WITH DESCRIPTION(" + logStatusDesc
					+ ") AND SNAPSHOT(" + imageNameWithPath + ") IN EXTENT REPORT\n" + e.getMessage());
			throwException("UNABLE TO LOG THE TEST CASE STATUS(" + logStatus + ") WITH DESCRIPTION(" + logStatusDesc
					+ ") AND SNAPSHOT(" + imageNameWithPath + ") IN EXTENT REPORT\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULY LOGED THE STATUS(" + logStatus + ") WITH DESCRIPTION(" + logStatusDesc + ") AND SNAPSHOT("
				+ imageNameWithPath + ") IN EXTENT REPORT");
	}

}
