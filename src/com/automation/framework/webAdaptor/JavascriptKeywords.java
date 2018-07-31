package com.automation.framework.webAdaptor;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.JavaScript;
import com.automation.framework.utilities.ObjectRead;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;

public class JavascriptKeywords implements JavaScript {

	final static Logger logger = Logger.getLogger(JavascriptKeywords.class);

	/**
	 * @name scrollAndClick
	 * @description scroll to the element and perform click operation on it
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @usage Actions
	 */
	public void scrollAndClick(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "Scroll And Click action is invoked");
		String locator = (String) params.get("arg0");
		WebElement element = null;
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead
						.getObject(locator));
				((JavascriptExecutor) COCDriverScript.driver).executeScript(
						"arguments[0].scrollIntoView(true);", element);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator
							+ " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info", locator
								+ " element is displayed");
						element.click();
						COCDriverScript.logMessage("info", locator
								+ " scrollandclick successful");
					} else {
						logger.error(locator
								+ "element found, but its not displayed");
						throw new ElementFailException(new Throwable(locator
								+ "element found, but its not displayed"));
					}
				} else {
					logger.error(locator + "element found, but its disabled");
					throw new ElementFailException(new Throwable(locator
							+ "element found, but its disabled"));
				}
			} catch (ElementNotVisibleException e) {
				logger.error(locator
						+ "Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, COCDriverScript.driver, e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		COCDriverScript
				.logMessage("info", "Scroll And Click action is executed");

	}

	/**
	 * @name scrollToBottom
	 * @description scroll to bottom of the page
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void scrollToBottom(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "Scroll To Bottom action is invoked");
		if (COCDriverScript.driver == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else {
			try {
				((JavascriptExecutor) COCDriverScript.driver).executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight, document.body.scrollHeight,document.documentElement.clientHeight));");
				COCDriverScript.logMessage("info", "Scroll To Bottom successful");
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		COCDriverScript
				.logMessage("info", "Scroll To Bottom action is executed");

	}

	/**
	 * @name handleAlert
	 * @description handle the pop up to accept or cancel
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void handleAlert(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "Handle Alert action is invoked");
		if (COCDriverScript.driver == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else {
			try {
				COCDriverScript.driver.switchTo().alert().accept();
				COCDriverScript.logMessage("info", "Handle Alert successful");
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		COCDriverScript.logMessage("info", "Handle Alert action is executed");

	}

	/**
	 * @name injectJsOnNotVisible
	 * @param element
	 * @description injected element is not visible
	 * @param driver
	 * @param ElementNotVisibleException
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	private void injectJsOnNotVisible(WebElement element, WebDriver driver,
			ElementNotVisibleException e) throws BrowserException,
			ElementFailException, ObjectNameNotFoundException {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) COCDriverScript.driver;
		if ((Boolean) javascriptExecutor.executeScript("arguments[0].click();",
				element)) {
			COCDriverScript.logMessage("info",
					"Click Successful on triggering javscript");
		} else {
			throw new ElementFailException(new Throwable(
					"Unable to click after triggering javscript"));
		}
	}

	/**
	 * @name scrollAndClick
	 * @description scroll to the element and perform click operation on it
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @usage Actions
	 */
	public void scrollAndClickBasedOnTags(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info",
				"scrollAndClickBasedOnTags action is invoked");
		String locator1 = (String) params.get("arg0");
		By objLocator1 = ObjectRead.getObject(locator1);
		String locator2 = (String) params.get("arg1");
		String xpathValue = (String) params.get("arg2");
		By objLocator2 = ObjectRead.getObject(locator2, xpathValue);
		WebElement element = null;
		if (COCDriverScript.driver == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator1 != null && locator1.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator1);
				COCDriverScript.logMessage("info",
						"Scrolling down to find element " + locator2);
				for (int i = 0; i < 500; i++) {

					try {
						COCDriverScript.driver.findElement(objLocator2).click();
						COCDriverScript.logMessage("info", locator2
								+ " is clicked");
						break;
					} catch (NoSuchElementException ex) {
						element.sendKeys(Keys.PAGE_DOWN);
					}
				}
			} catch (ElementNotVisibleException e) {
				logger.error("Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, COCDriverScript.driver, e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		COCDriverScript.logMessage("info",
				"scrollAndClickBasedOnTags action is executed");

	}
	/**
	 * @name scrollByKeyDownAndClick
	 * @description scroll to the element and perform click operation on it
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @usage Actions
	 */
	public void scrollByKeyDownAndClick(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info",
				"scrollByKeyDownAndClick action is invoked");
		String locator1 = (String) params.get("arg0");
		By objLocator1 = ObjectRead.getObject(locator1);
		String locator2 = (String) params.get("arg1");
		String xpathValue = (String) params.get("arg2");
		By objLocator2 = ObjectRead.getObject(locator2, xpathValue);
		WebElement element = null;
		if (COCDriverScript.driver == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new BrowserException(new Throwable(
					"Browser is not launched or Driver is failed to initiaize"));
		} else if (locator1 != null && locator1.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator1);
				COCDriverScript.logMessage("info",
						"Scrolling down to find element " + locator2);
				for (int i = 0; i < 500; i++) {

					try {
						if(COCDriverScript.driver.findElement(objLocator2).isDisplayed()){
						COCDriverScript.driver.findElement(objLocator2).click();
						COCDriverScript.logMessage("info", locator2
								+ " is clicked");
						break;
					}else{
						element.sendKeys(Keys.ARROW_DOWN);
					}
					} catch (NoSuchElementException ex) {
						element.sendKeys(Keys.ARROW_DOWN);
					}
				}
			} catch (ElementNotVisibleException e) {
				logger.error("Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, COCDriverScript.driver, e);
			} catch (JavaScriptException e) {
				logger.error("Error in JavascriptExecutor" + e);
				throw new ElementFailException(new Throwable(
						"Error in JavascriptExecutor" + e));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		COCDriverScript.logMessage("info",
				"scrollByKeyDownAndClick action is executed");

	}

}
