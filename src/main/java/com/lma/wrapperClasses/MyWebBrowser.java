package com.lma.wrapperClasses;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.lma.accelarators.ActionDriver;
import com.lma.utilities.MyOwnException;

public class MyWebBrowser extends ActionDriver {

	private static final Logger log = LogManager.getLogger(MyWebBrowser.class.getName());

	static Alert alert;

	public static void close(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO CLOSE BROWSER SESSION");
		try {
			if (ldriver != null) {
				ldriver.close();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO CLOSE THE CURRENT BROWSER SESSION\n" + e.getMessage());
			throwException("UNABLE TO CLOSE THE CURRENT BROWSER SESSION\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY CLOSED THE CURRENT BROWSER SESSION");
	}

	public static void quit(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO QUIT THE BROWSER SESSIONS");
		try {
			if (ldriver != null) {
				ldriver.quit();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO CLOSE ALL THE ACTIVE BROWSER SESSIONS\n" + e.getMessage());
			throwException("UNABLE TO CLOSE ALL THE ACTIVE BROWSER SESSIONS\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY CLOSED ALL THE ACTIVE BROWSER SESSIONS");
	}

	public static WebElement locateElementBy(WebDriver ldriver, String locatorName, String locatorValue)
			throws MyOwnException {

		log.info("INTEND TO FIND A WEBELEMENT IN CURRENT BROWSER SESSION");
		WebElement element = null;
		try {
			if (ldriver != null) {
				if (notEmpty(locatorName)) {
					if (notEmpty(locatorValue)) {
						if (locatorName.equalsIgnoreCase("ID")) {
							element = ldriver.findElement(By.id(locatorValue));
						} else if (locatorName.equalsIgnoreCase("NAME")) {
							element = ldriver.findElement(By.name(locatorValue));
						} else if (locatorName.equalsIgnoreCase("CLASS NAME")) {
							element = ldriver.findElement(By.className(locatorValue));
						} else if (locatorName.equalsIgnoreCase("LINK TEXT")) {
							element = ldriver.findElement(By.linkText(locatorValue));
						} else if (locatorName.equalsIgnoreCase("PARTIAL LINK TEXT")) {
							element = ldriver.findElement(By.partialLinkText(locatorValue));
						} else if (locatorName.equalsIgnoreCase("TAG NAME")) {
							element = ldriver.findElement(By.tagName(locatorValue));
						} else if (locatorName.equalsIgnoreCase("XPATH")) {
							element = ldriver.findElement(By.xpath(locatorValue));
						} else if (locatorName.equalsIgnoreCase("CSS")) {
							element = ldriver.findElement(By.cssSelector(locatorValue));
						} else {
							log.error("LOCATOR TYPE(" + locatorName + ") SEEMS TO BE INCORRECT");
							throwException("LOCATOR TYPE(" + locatorName + ") SEEMS TO BE INCORRECT" + "\n");
						}
					} else {
						log.error("LOCATOR VALUE SEEMS TO BE EMPTY");
						throwException("LOCATOR VALUE SEEMS TO BE EMPTY" + "\n");
					}
				} else {
					log.error("LOCATOR TYPE SEEMS TO BE EMPTY");
					throwException("LOCATOR TYPE SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO LOCATE A WEBELEMENT USING SPECIFIED ATTRIBUTE(" + locatorName + ") AND VALUE("
					+ locatorValue + ")\n" + e.getMessage());
			throwException("UNABLE TO LOCATE A WEBELEMENT USING SPECIFIED ATTRIBUTE(" + locatorName + ") AND VALUE("
					+ locatorValue + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY LOCATED A WEBELEMENT USING SPECIFIED ATTRIBUTE(" + locatorName + ") AND VALUE("
				+ locatorValue + ")");
		return element;
	}

	public static List<WebElement> locateElementsBy(WebDriver ldriver, String locatorName, String locatorValue)
			throws MyOwnException {

		log.info("INTEND TO FIND A LIST OF WEBELEMENTS IN CURRENT BROWSER SESSION");
		List<WebElement> elements = null;
		try {
			if (ldriver != null) {
				if (notEmpty(locatorName)) {
					if (notEmpty(locatorValue)) {
						if (locatorName.equalsIgnoreCase("ID")) {
							elements = ldriver.findElements(By.id(locatorValue));
						} else if (locatorName.equalsIgnoreCase("NAME")) {
							elements = ldriver.findElements(By.name(locatorValue));
						} else if (locatorName.equalsIgnoreCase("CLASS NAME")) {
							elements = ldriver.findElements(By.className(locatorValue));
						} else if (locatorName.equalsIgnoreCase("LINK TEXT")) {
							elements = ldriver.findElements(By.linkText(locatorValue));
						} else if (locatorName.equalsIgnoreCase("PARTIAL LINK TEXT")) {
							elements = ldriver.findElements(By.partialLinkText(locatorValue));
						} else if (locatorName.equalsIgnoreCase("TAG NAME")) {
							elements = ldriver.findElements(By.tagName(locatorValue));
						} else if (locatorName.equalsIgnoreCase("XPATH")) {
							elements = ldriver.findElements(By.xpath(locatorValue));
						} else if (locatorName.equalsIgnoreCase("CSS")) {
							elements = ldriver.findElements(By.cssSelector(locatorValue));
						} else {
							log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
							throwException("LOCATOR TYPE(" + locatorName + ") SEEMS TO BE INCORRECT" + "\n");
						}
					} else {
						log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
						throwException("LOCATOR VALUE SEEMS TO BE EMPTY" + "\n");
					}
				} else {
					log.error("LOCATOR TYPE SEEMS TO BE EMPTY");
					throwException("LOCATOR TYPE SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO LOCATE A LIST OF WEBELEMENTS USING SPECIFIED ATTRIBUTE (" + locatorName
					+ ") AND VALUE(" + locatorValue + ")\n" + e.getMessage());
			throwException("UNABLE TO LOCATE A LIST OF WEBELEMENTS USING SPECIFIED ATTRIBUTE(" + locatorName
					+ ") AND VALUE(" + locatorValue + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY LOCATED A LIST OF WEBELEMENTS USING SPECIFIED ATTRIBUTE(" + locatorName + ") AND VALUE("
				+ locatorValue + ")");
		return elements;
	}

	public static void openUrl(WebDriver ldriver, String url) throws MyOwnException {

		log.info("INTEND TO GET TO THE SPECIFIED URL IN CURRENT BROWSER SESSION");
		try {
			if (ldriver != null) {
				if (notEmpty(url)) {
					ldriver.get(url);
				} else {
					log.error("URL SEEMS TO BE EMPTY");
					throwException("URL SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO OPEN THE SPECIFIED URL(" + url + ")\n" + e.getMessage());
			throwException("UNABLE TO OPEN THE SPECIFIED URL(" + url + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY OPENED THE SPECIFIED URL(" + url + ") IN CURRENT BROWSER SESSION");
	}

	public static String getPageTitle(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO GET THE PAGE TITLE FROM CURRENT BROWSER SESSION");
		String title = "";
		try {
			if (ldriver != null) {
				title = ldriver.getTitle();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO GET THE PAGE TITLE FROM CURRENT BROWSER SESSION\n" + e.getMessage());
			throwException("UNABLE TO GET THE PAGE TITLE FROM CURRENT BROWSER SESSION\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY GOT THE PAGE TITLE(" + title + ") FROM CURRENT BROWSER SESSION");
		return title;
	}

	public static void maximize(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO MAXIMIZE CURRENT BROWSER SESSION");
		try {
			if (ldriver != null) {
				ldriver.manage().window().maximize();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO MAXIMIZE CURRENT BROWSER SESSION\n" + e.getMessage());
			throwException("UNABLE TO MAXIMIZE CURRENT BROWSER SESSION\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY MAXIMIZED CURRENT BROWSER SESSION");
	}

	public static String getCurrentURL(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO GET THE CURRENT URL FROM BROWSER SESSION");
		String url = "";
		try {
			if (ldriver != null) {
				url = ldriver.getCurrentUrl();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO GET THE URL FROM CURRENT BROWSER SESSION\n" + e.getMessage());
			throwException("UNABLE TO GET THE URL FROM CURRENT BROWSER SESSION\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY GOT THE URL(" + url + ") FROM CURRENT BROWSER SESSION");
		return url;
	}

	public void getFullScreen(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO GET THE FULL SCREEN VIEW OF BROWSER SESSION");
		try {
			if (ldriver != null) {
				ldriver.manage().window().fullscreen();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO CHANGE THE CURRENT BROWSER SESSION TO FULL SCREEN VIEW\n" + e.getMessage());
			throwException(
					"UNABLE TO CHANGE THE CURRENT BROWSER SESSION TO FULL SCREEN VIEW\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY CHANGED THE CURRENT BROWSER SESSION TO FULL SCREEN VIEW");
	}

	public static void navigateToUrl(WebDriver ldriver, String url) throws MyOwnException {

		log.info("INTEND TO NAVIGATE TO THE SPECIFIED URL IN CURRENT BROWSER SESSION");
		try {
			if (ldriver != null) {
				if (notEmpty(url)) {
					ldriver.navigate().to(url);
				} else {
					log.error("URL SEEMS TO BE EMPTY");
					throwException("URL SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO NAVIGATE TO THE SPECIFIED URL(" + url + ")\n" + e.getMessage());
			throwException("UNABLE TO NAVIGATE TO THE SPECIFIED URL(" + url + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY NAVIGATED TO SPCIFIED URL(" + url + ")");
	}

	public static void navigateForward(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO NAVIGATE FORWARD IN CURRENT BROWSER SESSION");
		try {
			if (ldriver != null) {
				ldriver.navigate().forward();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO NAVIGATE FORWARD IN THE CURRENT BROWSER SESSION\n" + e.getMessage());
			throwException("UNABLE TO NAVIGATE FORWARD IN THE CURRENT BROWSER SESSION\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY NAVIGATED FORWARD IN THE CURRENT BROWSER SESSION");
	}

	public static void navigateBackward(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO NAVIGATE BACKWARD IN CURRENT BROWSER SESSION");
		try {
			if (ldriver != null) {
				ldriver.navigate().back();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO NAVIGATE BACKWARD IN THE CURRENT BROWSER SESSION\n" + e.getMessage());
			throwException("UNABLE TO NAVIGATE BACKWARD IN THE CURRENT BROWSER SESSION\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY NAVIGATED BACKWARD IN THE CURRENT BROWSER SESSION");
	}

	public static void refresh(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO REFRESH CURRENT BROWSER SESSION");
		try {
			if (ldriver != null) {
				ldriver.navigate().refresh();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO REFRESH CURRENT BROWSER SESSION\n" + e.getMessage());
			throwException("UNABLE TO REFRESH CURRENT BROWSER SESSION\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY REFRESHED CURRENT BROWSER SESSION");
	}

	public static String getCurrentWindowHandle(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO GET THE WINDOW HANDLE FROM CURRENT BROWSER SESSION");
		String handle = "";
		try {
			if (ldriver != null) {
				handle = ldriver.getWindowHandle();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO GET THE WINDOW HANDLE FROM CURRENT BROWSER SESSION\n" + e.getMessage());
			throwException("UNABLE TO GET THE WINDOW HANDLE FROM CURRENT BROWSER SESSION\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY GOT THE WINDOW HANDLE(" + handle + ") FROM CURRENT BROWSER SESSION");
		return handle;
	}

	public Set<String> getAllWindowHandles(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO GET THE WINDOW HANDLES FROM ALL ACTIVE BROWSER SESSIONS");
		Set<String> handles = null;
		try {
			if (ldriver != null) {
				handles = ldriver.getWindowHandles();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO GET THE WINDOW HANDLES FROM ALL ACTIVE BROWSER SESSIONS\n" + e.getMessage());
			throwException(
					"UNABLE TO GET THE WINDOW HANDLES FROM ALL ACTIVE BROWSER SESSIONS\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY GOT THE WINDOW HANDLES(" + handles + ") FROM ALL ACTIVE BROWSER SESSIONS");
		return handles;
	}

	public static void switchToWindow(WebDriver ldriver, String handle) throws MyOwnException {

		log.info("INTEND TO SWITCH TO BROWSER WINDOW USING WINDOW HANDLE");
		try {
			if (ldriver != null) {
				if (notEmpty(handle)) {
					ldriver.switchTo().window(handle);
				} else {
					log.error("WINDOW HANDLE SEEMS TO BE EMPTY");
					throwException("WINDOW HANDLE SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO SWITCH TO BROWSER WINDOW USING HANDLE(" + handle + ")\n" + e.getMessage());
			throwException("UNABLE TO SWITCH TO BROWSER WINDOW USING HANDLE(" + handle + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY SWITCHED TO BROWSER WINDOW USING HANDLE(" + handle + ")");
	}

	public void switchtoNewBrowserWindow(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO SWITCH TO NEW BROWSER WINDOW");
		try {
			String currentWindowHandle = getCurrentWindowHandle(ldriver);
			Set<String> allWindowHandles = getAllWindowHandles(ldriver);

			for (String handle : allWindowHandles) {
				if (!currentWindowHandle.equalsIgnoreCase(handle)) {
					switchToWindow(ldriver, handle);
					log.info("SUCCESSFULLY SWITCH TO NEW BROWSER WINDOW WITH HANDLE(" + handle + ")");
				}
			}
		} catch (Exception e) {
			log.error("UNABLE TO SWITCH TO NEW BROWSER WINDOW\n" + e.getMessage());
			throwException("UNABLE TO SWITCH TO NEW BROWSER WINDOW\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY SWITCHED TO NEW BROWSER WINDOW");
	}

	public void switchToDefaultWindow(WebDriver ldriver) {

		log.info("INTEND TO SWITCH TO DEFAULT BROWSER WINDOW");
		try {
			if (ldriver != null) {
				ldriver.switchTo().defaultContent();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO SWITCH TO THE DEFAULT BROWSER WINDOW\n" + e.getMessage());
			System.out.println("UNABLE TO SWITCH TO THE DEFAULT BROWSER WINDOW\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY SWITCHED TO THE DEFAULT BROWSER WINDOW");
	}

	public boolean isAlertPresent(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO VERIFY WHETHER AN ALERT WINDOW IS PRESENT OR NOT");
		alert = null;
		try {
			if (ldriver != null) {
				alert = ldriver.switchTo().alert();
				log.info("ALERT IS PRESENT AND SWITCHED TO IT");
				result = true;
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (NoAlertPresentException Ex) {
			log.error("ALERT IS NOT PRESENT");
			result = false;
		}
		log.info("SUCCESSFULLY VERIFIED THE PRESENCE OF AN ALERT WINDOW AND SWITCHED TO IT");
		return result;
	}

	public void acceptAlert(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO ACCEPT THE ALERT WINDOW");
		try {
			if (ldriver != null) {
				if (isAlertPresent(ldriver) == true) {
					alert.accept();
				} else {
					log.error("ALERT WINDOW NOT PRESENT");
					throwException("ALERT WINDOW NOT PRESENT" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO ACCEPT THE ALERT WINDOW\n" + e.getMessage());
			throwException("UNABLE TO ACCEPT THE ALERT WINDOW\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY ACCEPTED THE ALERT WINDOW");
	}

	public void dismissAlert(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO DISMISS THE ALERT WINDOW");
		try {
			if (ldriver != null) {
				if (isAlertPresent(ldriver) == true) {
					alert.dismiss();
				} else {
					log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
					throwException("ALERT WINDOW NOT PRESENT" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO DISMISS THE ALERT WINDOW\n" + e.getMessage());
			throwException("UNABLE TO DISMISS THE ALERT WINDOW\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY DISMISSED THE ALERT WINDOW");
	}

	public String getTextFromAlert(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO GET TEXT FROM THE ALERT WINDOW");
		String alertText = "";
		try {
			if (ldriver != null) {
				if (isAlertPresent(ldriver) == true) {
					alertText = alert.getText();
				} else {
					log.error("ALERT WINDOW NOT PRESENT");
					throwException("ALERT WINDOW NOT PRESENT" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO GET TEXT FROM THE ALERT WINDOW\n" + e.getMessage());
			throwException("UNABLE TO GET TEXT FROM THE ALERT WINDOW\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY GOT TEXT(" + alertText + ") FROM THE ALERT WINDOW");
		return alertText;
	}

	public void enterTextInsideAlert(WebDriver ldriver, String text) throws MyOwnException {

		log.info("INTEND TO ENTER TEXT INSIDE THE ALERT WINDOW");
		try {
			if (ldriver != null) {
				if (isAlertPresent(ldriver) == true) {
					if (notEmpty(text)) {
						alert.sendKeys(text);
					} else {
						log.error("TEXT TO BE ENTERED SEEMS TO BE EMPTY");
						throwException("TEXT TO BE ENTERED SEEMS TO BE EMPTY" + "\n");
					}
				} else {
					log.error("ALERT WINDOW NOT PRESENT");
					throwException("ALERT WINDOW NOT PRESENT" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO ENTER TEXT INSIDE THE ALERT WINDOW\n" + e.getMessage());
			throwException("UNABLE TO ENTER TEXT INSIDE THE ALERT WINDOW\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY ENTERED TEXT(" + text + ") INSIDE THE ALERT WINDOW");
	}

	public void switchToFrameByIdOrName(WebDriver ldriver, String value) throws MyOwnException {

		log.info("INTEND TO SWITCH TO DESIERED FRAME USING ID (OR) NAME ATTRIBUTE");
		try {
			if (ldriver != null) {
				if (notEmpty(value)) {
					ldriver.switchTo().frame(value);
				} else {
					log.error("LOCATOR VALUE SEEMS TO BE EMPTY");
					throwException("LOCATOR VALUE SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO SWITCH TO DESIERED FRAME USING ID (OR) NAME ATTRIBUTE USING VALUE(" + value + ")\n"
					+ e.getMessage());
			throwException("UNABLE TO SWITCH TO DESIERED FRAME USING ID (OR) NAME ATTRIBUTE USING VALUE(" + value
					+ ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY SWITCHED TO DESIERED FRAME USING ID (OR) NAME ATTRIBUTE USING VALUE(" + value + ")");
	}

	public void switchToFrameByIndex(WebDriver ldriver, int index) throws MyOwnException {

		log.info("INTEND TO SWITCH TO DESIERED FRAME USING INDEX");
		try {
			if (ldriver != null) {
				ldriver.switchTo().frame(index);
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO SWITCH TO DESIERED FRAME USING INDEX\n" + e.getMessage());
			throwException("UNABLE TO SWITCH TO DESIERED FRAME USING INDEX\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY SWITCHED TO DESIERED FRAME USING INDEX(" + index + ")");
	}

	public void switchToFrameByElement(WebDriver ldriver, WebElement frameElement) throws MyOwnException {

		log.info("INTEND TO SWITCH TO DESIERED FRAME USING WEBELEMENT");
		try {
			if (ldriver != null) {
				if (frameElement != null) {
					if (MyWebElement.isDisplayedOrNot(frameElement) == true) {
						ldriver.switchTo().frame(frameElement);
					} else {
						log.error("WEBELEMENT INSTANCE SEEMS TO BE NULL");
						throwException("WEBELEMENT INSTANCE SEEMS TO BE NULL" + "\n");
					}
				} else {
					log.error("WEBELEMENT(" + frameElement + ") IS NOT DISPLYED ON THE WEBPAGE");
					throwException("WEBELEMENT(" + frameElement + ") IS NOT DISPLYED ON THE WEBPAGE" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO SWITCH TO DESIERED FRAME USING WEBELEMENT(" + frameElement + ")\n" + e.getMessage());
			throwException("UNABLE TO SWITCH TO DESIERED FRAME USING WEBELEMENT(" + frameElement + ")\n"
					+ e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY SWITCHED TO DESIERED FRAME USING WEBELEMENT(" + frameElement + ")");
	}

	public void switchToParentFrame(WebDriver ldriver) throws MyOwnException {

		log.info("INTEND TO SWITCH TO PARENT FRAME");
		try {
			if (ldriver != null) {
				ldriver.switchTo().parentFrame();
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO SWITCH TO PARENT FRAME\n" + e.getMessage());
			throwException("UNABLE TO SWITCH TO PARENT FRAME\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY SWITCHED TO PARENT FRAME");
	}

	public void switchToChildFrame(WebDriver ldriver, String frame1, String frame2) throws MyOwnException {

		log.info("INTEND TO SWITCH TO CHILD FRAME FROM PARENT FRAME");
		try {
			if (ldriver != null) {
				if (notEmpty(frame1)) {
					if (notEmpty(frame2)) {
						ldriver.switchTo().frame(frame1).switchTo().frame(frame2);
					} else {
						log.error("LOCATOR VALUE OF CHILD FRAME SEEMS TO BE EMPTY");
						throwException("LOCATOR VALUE OF CHILD FRAME SEEMS TO BE EMPTY" + "\n");
					}
				} else {
					log.error("LOCATOR VALUE OF PARENT FRAME SEEMS TO BE EMPTY");
					throwException("LOCATOR VALUE OF PARENT FRAME SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO SWITCH TO DESIERED CHILD FRAME(" + frame2 + ") FROM PARENT FRAME(" + frame1 + ")\n"
					+ e.getMessage());
			throwException("UNABLE TO SWITCH TO DESIERED CHILD FRAME(" + frame2 + ") FROM PARENT FRAME(" + frame1
					+ ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY SWITCHED TO CHILD FRAME(" + frame2 + ") FROM PARENT FRAME(" + frame1 + ")");
	}

	public void switchToChildFrame(WebDriver ldriver, WebElement frame1, WebElement frame2) throws MyOwnException {

		log.info("INTEND TO SWITCH TO CHILD FRAME FROM PARENT FRAME");
		try {
			if (ldriver != null) {
				if (frame1 != null) {
					if (frame2 != null) {
						ldriver.switchTo().frame(frame1).switchTo().frame(frame2);
					} else {
						log.error("WEBELEMENT INSTANCE SEEMS TO BE NULL");
						throwException("WEBELEMENT INSTANCE SEEMS TO BE NULL" + "\n");
					}
				} else {
					log.error("WEBELEMENT INSTANCE SEEMS TO BE NULL");
					throwException("WEBELEMENT INSTANCE SEEMS TO BE NULL" + "\n");
				}
			} else {
				log.error("WEBDRIVER INSTANCE SEEMS TO BE NULL");
				throwException("WEBDRIVER INSTANCE SEEMS TO BE NULL" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO SWITCH TO DESIERED CHILD FRAME(" + frame2 + ") FROM PARENT FRAME(" + frame1 + ")\n"
					+ e.getMessage());
			throwException("UNABLE TO SWITCH TO DESIERED CHILD FRAME(" + frame2 + ") FROM PARENT FRAME(" + frame1
					+ ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY SWITCHED TO CHILD FRAME(" + frame2 + ") FROM PARENT FRAME(" + frame1 + ")");
	}

	public boolean isElementPresent(WebDriver ldriver, String locatorName, String locatorValue) throws MyOwnException {

		log.info("INTEND TO CHECK WHETHER A WEBELEMENT IS PRESENT OR NOT IN CURRENT BROWSER SESSION");
		try {
			if (notEmpty(locatorName)) {
				if (notEmpty(locatorValue)) {
					List<WebElement> elementList = locateElementsBy(ldriver, locatorName, locatorValue);
					int size = elementList.size();
					if (size > 0) {
						log.info("WEBELEMENT PRESENT");
						result = true;
					} else {
						log.error("WEBELEMENT NOT PRESENT");
						result = false;
					}
				} else {
					log.error("LOCATOR VALUE SEEMS TO BE EMPTY");
					throwException("LOCATOR VALUE SEEMS TO BE EMPTY" + "\n");
				}
			} else {
				log.error("LOCATOR TYPE SEEMS TO BE EMPTY");
				throwException("LOCATOR TYPE SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO CHECK WHETHER A WEBELEMENT IS PRESENT OR NOT USING ATTRIBUTE(" + locatorName
					+ ") AND VALUE(" + locatorValue + ")\n" + e.getMessage());
			throwException("UNABLE TO CHECK WHETHER A WEBELEMENT IS PRESENT OR NOT USING ATTRIBUTE(" + locatorName
					+ ") AND VALUE(" + locatorValue + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY VERIFIED WHETHER A WEBELEMEN IS PRESENT OR NOT USING ATTRIBUTE(" + locatorName
				+ ") AND VALUE(" + locatorValue + ")");
		return result;
	}

}