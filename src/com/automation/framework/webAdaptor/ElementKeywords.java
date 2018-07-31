package com.automation.framework.webAdaptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.Element;
import com.automation.framework.utilities.ObjectRead;
import com.google.common.base.Function;

public class ElementKeywords implements Element {
	

	/**
	 * @name Click
	 * @description click on element
	 * @param arg1
	 * @return void
	 * @usage Actions.click, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void click(HashMap<String, Object> params) throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "Click keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator + " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info", locator + " element is displayed");
						element.click();
						COCDriverScript.logMessage("info", locator + " is clicked");
					} else {
						COCDriverScript.logMessage("error", locator + " element found, but its disabled ,please try clickUsingJavascript action to perform click operation");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its disabled ,please try clickUsingJavascript action to perform click operation"));
					}
				} else {
					COCDriverScript.logMessage("error", locator + " element found, but its disabled ,please use clickUsingJavascript action to perform click operation");
					throw new ElementFailException(new Throwable(locator + " element found, but its disabled ,please use clickUsingJavascript action to perform click operation"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation"));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, e);
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		COCDriverScript.logMessage("info", "Click keyword is executed");
	}


	
	
	
	/**
	 * @name isElementNotDisplayed
	 * @description verifies whether element exists and not displayed in the screen
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementDisplayed, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isElementNotDisplayed(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "isElementNotDisplayed keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its  displayed"));
						
					} else {
						COCDriverScript.logMessage("info", locator + " element is displayed");
					}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "isElementNotDisplayed keyword is executed");
	}

	



/**
	 * @name setValueToXpathAndElementNotDisplayed
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then validates whether element is displayed or not
	 * @param arg1
	 * @return void
	 * @usage Actions.setValueToXpathAndClick, xpath ,ValueTobePlacedInXpath
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndElementNotDisplayed(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "setValueToXpathAndElementNotDisplayed keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isDisplayed()) {
					COCDriverScript.logMessage("error", locator + " element found, but its not displayed");
					throw new ElementFailException(
							new Throwable(locator + " element found, but its not displayed"));
					
				} else {
					COCDriverScript.logMessage("info", locator + " element is not displayed");
				}
		} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, e);
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		COCDriverScript.logMessage("info", "setValueToXpathAndElementNotDisplayed keyword is executed");

	}

	
	
	/**
	 * @name elementCount
	 * @description click on element
	 * @param arg1
	 * @return void
	 * @usage Actions.click, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void elementCount(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "elementCount keyword is invoked"); // Ritesh
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				int size  = COCDriverScript.driver.findElements(objLocator).size();
				COCDriverScript.logMessage("info", "Count of "+locator+" locator is "+size);
				 COC.mapValues.put(locator,String.valueOf(size));
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		COCDriverScript.logMessage("info", "elementCount keyword is executed");
	}
	


	/**
	 * @name send Keyboard keys 
	 * @description to provide input value to the textbox element
	 * @param arg1
	 * @return void
	 * @usage Actions.sendKeyboardActions, XpathOfTargetElement, Value
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void sendKeyboardActions(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info", "sendKeyboardActions keyboard is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		String inputext = (String) params.get("arg1");
		// for rest Service
		COCDriverScript.logMessage("info", "locator is " + locator);
		COCDriverScript.logMessage("info", "inputext is " + inputext);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				element.sendKeys(Keys.valueOf(inputext));
				COCDriverScript.logMessage("info", inputext + " is passed in to " + locator + " element");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "sendKeyboardActions keyword is executed");

	}

	
	/**
	 * @name setXpathAndElementCount
	 * @description click on element
	 * @param arg1
	 * @return void
	 * @usage Actions.click, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setXpathAndElementCount(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "setXpathAndElementCount keyword is invoked"); // Ritesh
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator,xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				int size  = COCDriverScript.driver.findElements(objLocator).size();
				COCDriverScript.logMessage("info", "Count of "+locator+" locator is "+size);
				COC.mapValues.put(locator,String.valueOf(size));
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		COCDriverScript.logMessage("info", "setXpathAndElementCount keyword is executed");
	}
	
	
	/**
	 * @name clickUsingJavascript
	 * @description click on element using javascript
	 * @param arg1
	 * @return void
	 * @usage Actions.click, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void clickUsingJavascript(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "clickUsingJavascript keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator + " element is enabled");
						 ((JavascriptExecutor)COCDriverScript.driver).executeScript("arguments[0].click();", element);
						COCDriverScript.logMessage("info", locator + " is clicked");
				} else {
					COCDriverScript.logMessage("error", locator + " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation"));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, e);
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		COCDriverScript.logMessage("info", "clickUsingJavascript keyword is executed");
	}

	/**
	 * @name setText
	 * @description to provide input value to the textbox element
	 * @param arg1
	 * @return void
	 * @usage Actions.setText, XpathOfTargetElement, Value
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setText(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info", "Set element Text keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		String inputext = (String) params.get("arg1");
		// for rest Service
		COCDriverScript.logMessage("info", "locator is " + locator);
		COCDriverScript.logMessage("info", "inputext is " + inputext);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				element.clear();
				COCDriverScript.logMessage("info", locator + " is cleared ");
				element.sendKeys(inputext);
				COCDriverScript.logMessage("info", inputext + " is passed in to " + locator + " element");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "Set element Text keyword is executed");

	}


	/**
	 * @name getText
	 * @description Extract text from element and save it to HashMap
	 * @param arg1
	 * @return void
	 * @usage Actions.getText, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void getText(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "Get element Text keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.getText() != null && element.getText() != "") {
					String value = element.getText();
					COC.mapValues.put(locator, value);
					COCDriverScript.logMessage("info", "Extracted text is " + value);
					COCDriverScript.logMessage("info", "Get element Text successful and saved in to map");
				} else {
					COCDriverScript.logMessage("error",
							"element found and element text is empty. Please use getTextUsingJavascript keyword to perform this action");
					throw new ElementFailException(
							"element found and element text is empty. Please use getTextUsingJavascript keyword to perform this action");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "Get element Text keyword is executed");

	}

	/**
	 * @name
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then extract and save text of
	 *              the element
	 * @param arg1
	 * @return void
	 * @usage Actions.setValueToXpathAndClick, xpath ,ValueTobePlacedInXpath
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndGetText(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "setValueToXpathAndGetText keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.getText() != null && element.getText() != "") {
					String value = element.getText();
					COC.mapValues.put(locator, value);
					COCDriverScript.logMessage("info", "Extracted text is " + value);
					COCDriverScript.logMessage("info", "Get element Text successful");
				} else {
					COCDriverScript.logMessage("error", "element found and element text is empty");
					throw new ElementFailException("element found and element text is empty");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setValueToXpathAndGetText keyword is executed");

	}

	/**
	 * @name doubleClick
	 * @description double click on an element
	 * @param arg1
	 * @return void
	 * @usage Actions.doubleClick, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void doubleClick(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException { // Ritesh
		COCDriverScript.logMessage("info", "Double Click keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(COCDriverScript.driver);
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator + " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info", locator + " element is displayed");
						action.doubleClick(element).perform();
						COCDriverScript.logMessage("info", locator + " is double clicked");
						COCDriverScript.logMessage("info", "Double Click successful");
					} else {
						COCDriverScript.logMessage("error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error", locator + " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " element found, but its not displayed"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation"));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error", locator+" found and not visible to perform operation");
				throw new ElementFailException(locator+" found and not visible to perform operation");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "Double Click keyword is executed");
	}

	/**
	 * @name setValueToXpathAndDoubleClick
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then double click on the
	 *              element
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndDoubleClick(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "setValueToXpathAndDoubleClick keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(COCDriverScript.driver);
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator + " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info", locator + " element is displayed");
						action.doubleClick(element).perform();
						COCDriverScript.logMessage("info", " Double Click on " + locator + " is  successful");
					} else {
						COCDriverScript.logMessage("error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error", locator + " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " element found, but its not displayed"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation"));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error", locator+" found and not visible to perform operation");
				throw new ElementFailException(locator+" found and not visible to perform operation");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setValueToXpathAndDoubleClick keyword is executed");
	}

	/**
	 * @name setValueToXpathAndRightClick
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then right click on the
	 *              element
	 * @param arg1
	 * @return void
	 * @usage Actions.setValueToXpathAndRightClick, xpath
	 *        ,ValueTobePlacedInXpath
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndRightClick(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "setValueToXpathAndRightClick keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		// for rest Service
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(COCDriverScript.driver);
				element = COCDriverScript.driver.findElement(objLocator);
				action.contextClick(element).perform();
				COCDriverScript.logMessage("info", "Right Click on " + locator + " is successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform Right click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform double click operation"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}

		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setValueToXpathAndRightClick keyword is executed");
	}

	/**
	 * @name rightClick
	 * @description Right click on the element
	 * @param arg1
	 * @return void
	 * @usage Actions.rightClick, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void rightClick(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "Right Click keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		// for rest Service
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(COCDriverScript.driver);
				element = COCDriverScript.driver.findElement(objLocator);
				action.contextClick(element).perform();
				COCDriverScript.logMessage("info", "Right Click " + locator + " is successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform Right click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform Right click operation"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}

		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "Right Click keyword is executed");
	}

	/**
	 * @name IsElementExist
	 * @description verifies whether element exists in the screen
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementExist, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void IsElementExist(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "IsElementExist keyword is invoked");
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				COCDriverScript.driver.findElement(objLocator);
				COCDriverScript.logMessage("info", locator + " exists ");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen ");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen "));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "IsElementExist keyword is executed");
	}

	/**
	 * @name isElementNotExists
	 * @description verifies whether element does not exists in the screen
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isElementNotExists(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "isElementNotExists keyword is invoked");
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				COCDriverScript.driver.findElement(objLocator);
				COCDriverScript.logMessage("error", locator+" locator exists in the screen");
				throw new ElementFailException(new Throwable(locator+" locator exists in the screen"));
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("info", locator + " exists");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "isElementNotExists keyword is executed");
	}
	
	
	/**
	 * @name setValueToXpathAndIsElementNotExists
	 * @description verifies whether element does not exists in the screen
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndIsElementNotExists(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "setValueToXpathAndIsElementNotExists keyword is invoked");
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				COCDriverScript.driver.findElement(objLocator);
				COCDriverScript.logMessage("error", locator+" locator exists in the screen");
				throw new ElementFailException(new Throwable(locator+" locator exists in the screen"));
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("info", locator + " exists");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setValueToXpathAndIsElementNotExists keyword is executed");
	}


	/**
	 * @name isElementDisplayed
	 * @description verifies whether element exists and displayed in the screen
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementDisplayed, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isElementDisplayed(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "isElementDisplayed keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator + " locator is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info", locator + " locator is displayed");
					} else {
						COCDriverScript.logMessage("error", locator + " locator found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error", locator + " locator found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " locator found, but its not displayed"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "Is element Displayed keyword is executed");
	}

	/**
	 * @name setValueToXpathAndElementDisabled
	 * @description verifies whether element exists and disabled
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementDisplayed, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndElementDisabled(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "setValueToXpathAndElementDisabled keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("error", locator + " element is enabled");
					throw new ElementFailException(
							new Throwable( locator + " element is enabled"));
				}
				else {
					COCDriverScript.logMessage("info", locator + " element is disabled");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to verify locator is enabled");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to verify locator is enabled"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setValueToXpathAndElementDisabled keyword is executed");
	}

	
	
	/**
	 * @name isElementDisabled
	 * @description verifies whether element exists and disabled
	 * @param arg1
	 * @return void
	 * @usage Actions.isElementDisplayed, XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isElementDisabled(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "isElementDisabled keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("error", locator + " element is enabled");
					throw new ElementFailException(
							new Throwable( locator + " element is enabled"));
				}
				else {
					COCDriverScript.logMessage("info", locator + " element is disabled");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to verify locator is enabled");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to verify locator is enabled"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "isElementDisabled keyword is executed");
	}

	
	/**
	 * @name setValueToXpathAndgetElementAttributeValue
	 * @description Extracts attribute value and saved in the map to retrieve
	 *              the value for further execution
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndGetElementAttributeValue(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "setValueToXpathAndGetElementAttributeValue keyword is invoked");
		WebElement element = null;
		String attribute = null;

		String locator = (String) params.get("arg0");
		attribute = (String) params.get("arg2");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);

				if (element.getAttribute(attribute) != null && element.getAttribute(attribute) != "") {
					String value = element.getAttribute(attribute);
					COC.mapValues.put(locator, value);
					COCDriverScript.logMessage("info", "Get element Attribute Value successful");
				} else {
					COCDriverScript.logMessage("info", locator + " element found and unable to get attribute value");
					throw new ElementFailException(locator + " element found and unable to get attribute value");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to read the locator attribute "+attribute);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to read the locator attribute "+attribute));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setValueToXpathAndGetElementAttributeValue keyword is executed");

	}
	
	
	/**
	 * @name getElementAttributeValue
	 * @description Extracts attribute value and saved in the map to retrieve
	 *              the value for further execution
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void getElementAttributeValue(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "Get element Attribute Value keyword is invoked");
		WebElement element = null;
		String attribute = null;

		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		attribute = (String) params.get("arg1");
		// for rest Service

		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);

				if (element.getAttribute(attribute) != null && element.getAttribute(attribute) != "") {
					String value = element.getAttribute(attribute);
					COC.mapValues.put(locator, value);
					COCDriverScript.logMessage("info", "Get element Attribute Value successful");
				} else {
					COCDriverScript.logMessage("info", locator + " element found and unable to get attribute value");
					throw new ElementFailException(locator + " element found and unable to get attribute value");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to read the locator attribute "+attribute);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to read the locator attribute "+attribute));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "Get element Attribute Value keyword is executed");

	}


	/**
	 * @name clear
	 * @description clears old data of the textbox element
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @usage Actions.clear, XpathOfTargetElement
	 */
	public void clear(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "Clear keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				element.clear();
				COCDriverScript.logMessage("info", locator + " clear is successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to clear the textbox");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to clear the textbox"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "Clear keyword is executed");

	}

	/**
	 * @name mouseHover
	 * @description mouse hover on the element
	 * @param arg1
	 * @return void
	 * @usage Actions.mouseHover , XpathOfTargetElement
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void mouseHover(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "Mouse Hover keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Actions action = new Actions(COCDriverScript.driver);
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator + " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info", locator + " element is displayed");
						action.moveToElement(element).build().perform();
						COCDriverScript.logMessage("info", "Mouse Hover on " + locator + " is successful");
					} else {
						COCDriverScript.logMessage("error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error", locator + " element found, but its disabled");
					throw new ElementFailException(
							new Throwable(locator + " element found, but its disabled" + locator));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to mousehover");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to mousehover"));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error", "Locator found and not visible to perform operation ");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "Mouse Hover keyword is executed");

	}

	/**
	 * @name injectJsOnNotVisible
	 * @param arg1
	 * @return void
	 * @throws ElementFailException
	 */
	private void injectJsOnNotVisible(WebElement element, ElementNotVisibleException e) throws ElementFailException {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) COCDriverScript.driver;
		if ((Boolean) javascriptExecutor.executeScript("arguments[0].click();", element)) {
			COCDriverScript.logMessage("info", "Click Successful on triggering javscript");
		} else {
			throw new ElementFailException(new Throwable("Unable to  perform  click opearation"));
		}
	}

	/**
	 * @name setValueToXpathAndClick
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then click on the element
	 * @param arg1
	 * @return void
	 * @usage Actions.setValueToXpathAndClick, xpath ,ValueTobePlacedInXpath
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndClick(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "setValueToXpathAndClick keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator + " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info", locator + " element is displayed");
						element.click();
					} else {
						COCDriverScript.logMessage("error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error", locator + " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation "));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, e);
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		COCDriverScript.logMessage("info", "setValueToXpathAndClick keyword is executed");

	}
	
	/**Ratikant
	 * 
	 */
	public void setValueToXpathAndGetCssValue(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "setValueToXpathAndClick keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = COCDriverScript.driver.findElement(objLocator);
				
						String color=element.getCssValue((String) params.get("arg2"));
						COC.mapValues.put(locator, color);
					
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation "));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, e);
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		COCDriverScript.logMessage("info", "setValueToXpathAndClick keyword is executed");

	}
	
	/**
	 * @name setValueToXpathAndClickUsingJavascript
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then click on the element
	 * @param arg1
	 * @return void
	 * @usage Actions.setValueToXpathAndClick, xpath ,ValueTobePlacedInXpath
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setValueToXpathAndClickUsingJavascript(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "setValueToXpathAndClick keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {

				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator + " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info", locator + " element is displayed");
						((JavascriptExecutor)COCDriverScript.driver).executeScript("arguments[0].click();", element);
						COCDriverScript.logMessage("info", locator + " element is clicked successfully");
					} else {
						COCDriverScript.logMessage("error", locator + " element found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error", locator + " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to perform click operation "));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, e);
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		COCDriverScript.logMessage("info", "setValueToXpathAndClick keyword is executed");

	}

	/**
	 * @name waitForObjectToLoad
	 * @description waits till the element is loaded. Max wait time is wait.high
	 *              value provided in Globalprameters sheet
	 * @param arg1
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.waitForObjectToLoad , XpathOfTargetElement
	 */
	public void waitForObjectToLoad(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "waitForObjectToLoad action is invoked");
		String locator = (String) params.get("arg0");
		final By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		int maxWait = Integer.parseInt(COCDriverScript.globalParamMap.get("wait.high"));
		COCDriverScript.logMessage("info", "Maximum of " + maxWait + " seconds wait will be applied to search for "+locator);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(COCDriverScript.driver).withTimeout(maxWait, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		try {
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return COCDriverScript.driver.findElement(objLocator);
				}
			});
			COCDriverScript.logMessage("info", "locator is " + locator);
		} catch (TimeoutException ex) {
			COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen");
			throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen"));
		}
		COCDriverScript.logMessage("info", "waitForObjectToLoad keyword is invoked");
	}

	/**
	 * @name setXpathAndwaitForObjectToLoad
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and waits till the element is
	 *              loaded. Max wait time is wait.high value provided in
	 *              Globalprameters sheet
	 * @param params
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @description set xpath and wait till object load
	 */

	public void setXpathAndwaitForObjectToLoad(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "setXpathAndwaitForObjectToLoad keyword is invoked");
		String locator = (String) params.get("arg0");
		String xpathValue = null;
		if (params.get("arg1") != null) {
			xpathValue = (String) params.get("arg1");
		}
		final By objLocator = ObjectRead.getObject(locator, xpathValue);
		int maxWait = Integer.parseInt(COCDriverScript.globalParamMap.get("wait.high"));
		COCDriverScript.logMessage("info", "Maximum of " + maxWait + " seconds wait will be applied to search for the ");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(COCDriverScript.driver).withTimeout(maxWait, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		try {
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return COCDriverScript.driver.findElement(objLocator);
				}
			});
		} catch (TimeoutException ex) {
			COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen");
			throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen"));
		}

		COCDriverScript.logMessage("info", "setXpathAndwaitForObjectToLoad keyword is invoked");
	}

		/**
	 * xpath must contain $$$value$$$.$$$value$$$ will be replaced by the actual
	 * value passed and then set the value in the text box
	 * 
	 * @name setValueToXpathAndSetText
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then set the value in the
	 *              text box
	 * @param params
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @return void
	 */
	public void setValueToXpathAndSetText(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info", "setValueToXpathAndSetText keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		String inputext = (String) params.get("arg2");
		// for rest Service
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				element.clear();
				COCDriverScript.logMessage("info", locator + " is cleared ");
				element.sendKeys(inputext);
				COCDriverScript.logMessage("info", inputext + " is passed in to " + locator + " element");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setValueToXpathAndSetText keyword is executed");

	}
	
	/**
     * @name setTextUsingJavaScript
     * @description to provide input value to the textbox element
     * @param arg1
     * @return void
     * @usage Actions.setText, XpathOfTargetElement, Value
     * @throws ObjectNameNotFoundException
     * @throws ElementFailException
     * @throws BrowserException
     */
     public void setTextUsingJavaScript(HashMap<String, Object> params)
                   throws ObjectNameNotFoundException, ElementFailException, BrowserException {
            COCDriverScript.logMessage("info", "Set element Text keyword is invoked");
            WebElement element = null;
            String locator = (String) params.get("arg0");
            By objLocator = ObjectRead.getObject(locator);
            String inputext = (String) params.get("arg1");
            // for rest Service
            COCDriverScript.logMessage("info", "locator is " + locator);
            COCDriverScript.logMessage("info", "inputext is " + inputext);
            if (COCDriverScript.driver == null) {
                   COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
                   throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
            } else if (locator != null && locator.length() > 0) {
                   try {
                         element = COCDriverScript.driver.findElement(objLocator);
                         COCDriverScript.logMessage("info", locator + " is cleared ");
                         ((JavascriptExecutor) COCDriverScript.driver).executeScript("arguments[0].value = '"+inputext+"';",element);
                         COCDriverScript.logMessage("info", inputext + " is passed in to " + locator + " element");
                   } catch (InvalidSelectorException e) {
                         COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
                         throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
                   } catch (NoSuchElementException e) {
                         COCDriverScript.logMessage("error",  "Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext);
                         throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext));
                   } catch (WebDriverException e) {
                         COCDriverScript.logMessage("error", "Error in Webdriver" + e);
                         throw new ElementFailException(new Throwable("Error in Webdriver " + e));
                   }
            } else {
                   COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
                   throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
            }
            COCDriverScript.logMessage("info", "Set element Text keyword is executed");

     }

	/**
	 * xpath must contain $$$value$$$.$$$value$$$ will be replaced by the actual
	 * value passed and then set the value in the text box
	 * 
	 * @name setValueToXpathAndSetTextUsingJavascript
	 * @description xpath must contain $$$value$$$.$$$value$$$ will be replaced
	 *              by the actual value passed and then set the value in the
	 *              text box
	 * @param params
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @return void
	 */
	public void setValueToXpathAndSetTextUsingJavascript(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info", "setValueToXpathAndSetText keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		String inputext = (String) params.get("arg2");
		// for rest Service
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				COCDriverScript.logMessage("info", locator + " is cleared ");
				Actions act = new Actions(COCDriverScript.driver);
		        act.keyUp(Keys.SHIFT).sendKeys(element, inputext).keyDown(Keys.SHIFT)
		                .build().perform();
				COCDriverScript.logMessage("info", inputext + " is passed in to " + locator + " element");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator textbox does not exists in the screen to send input parameters "+inputext));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setValueToXpathAndSetText keyword is executed");

	}

	/**
	 * @name setvalueToXpathAndIsElementExist * @description xpath must contain
	 *       $$$value$$$.$$$value$$$ will be replaced by the actual value passed
	 *       and then to verify whether element exists.
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @return void
	 */
	public void setvalueToXpathAndIsElementExist(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "setvalueToXpathAndIsElementExist keyword is invoked");
		// List<WebElement> element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		// for rest Service
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				COCDriverScript.driver.findElement(objLocator);
				COCDriverScript.logMessage("info", locator + " exists ");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setvalueToXpathAndIsElementExist Exist keyword is executed");
	}

	/**
	 * @name getTextUsingJavascript
	 * @description Extract the element text using javascript
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @return void
	 * @description Extract text from element and save it
	 */
	public void getTextUsingJavascript(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "getTextUsingJavascript keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				String value = null;
				element = COCDriverScript.driver.findElement(objLocator);
				JavascriptExecutor executor = (JavascriptExecutor) COCDriverScript.driver;
				value = (String) executor.executeScript("return arguments[0].value", element);

				if (value != null && value != "") {

					COC.mapValues.put(locator, value);
					COCDriverScript.logMessage("info", "Extracted text is " + value);
					COCDriverScript.logMessage("info", "Get element Text successful and saved in to map");
				} else {
					COCDriverScript.logMessage("error", "element found, but  element Text Not Found");
					throw new ElementFailException("element found, but  element Text Not Found");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "getTextUsingJavascript is executed");

	}
	
	
	/**
	 * @name setValueToXpathAndGetTextUsingJavascript
	 * @description Extract the element text using javascript
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @return void
	 * @description Extract text from element and save it
	 */
	public void setValueToXpathAndGetTextUsingJavascript(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "setValueToXpathAndGetTextUsingJavascript keyword is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				String value = null;
				element = COCDriverScript.driver.findElement(objLocator);
				JavascriptExecutor executor = (JavascriptExecutor) COCDriverScript.driver;
				value = (String) executor.executeScript("return arguments[0].value", element);

				if (value != null && value != "") {

					COC.mapValues.put(locator, value);
					COCDriverScript.logMessage("info", "Extracted text is " + value);
					COCDriverScript.logMessage("info", "Get element Text successful and saved in to map");
				} else {
					COCDriverScript.logMessage("error", "element found, but  element Text Not Found");
					throw new ElementFailException("element found, but  element Text Not Found");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text");
				throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator does not exists in the screen to fetch text"));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "setValueToXpathAndGetTextUsingJavascript is executed");

	}
	
	
	/**
	 * @name getElementsCount
	 * @description Get the count of elements 
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @return void
	 * @description Extract text from element and save it
	 */
	public void getElementsCount(HashMap<String, Object> params)
			throws BrowserException, ObjectNameNotFoundException, ElementFailException {
		COCDriverScript.logMessage("info", "getElementsCount keyword is invoked");
		List<WebElement> element  = null;
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				String value = null;
				element = COCDriverScript.driver.findElements(objLocator);
				JavascriptExecutor executor = (JavascriptExecutor) COCDriverScript.driver;
				value = (String) executor.executeScript("return arguments[0].value", element.size());


					COC.mapValues.put(locator, value);
					COCDriverScript.logMessage("info", "Count of elements is " + value);
					COCDriverScript.logMessage("info", "getElementsCount successful and saved in to map");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info", "getElementsCount is executed");

	}
	
	
	//WebDriver driver, String searchText, int waitTime, int scroll
	public void waitForText(HashMap<String, Object> params){
		
		WebDriver driver =COCDriverScript.driver;
		 String searchText=(String) params.get("arg0");
		 String waitTimeString=(String) params.get("arg1");
		 String scrollString=(String) params.get("arg2");
		 
		 int waitTime = Integer.parseInt(waitTimeString);
		 int scroll=Integer.parseInt(scrollString);
		 
		if ((driver instanceof FirefoxDriver)|(driver instanceof InternetExplorerDriver)|(driver instanceof ChromeDriver)){
			WebDriverWait wait = new WebDriverWait(driver, waitTime);   
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+searchText+"')]|//*[contains(text(),'"+searchText.toLowerCase()+"')]")));
			}	catch (Exception e){
				Reporter.log(driver.getTitle() + " page did not display the page in "+waitTime+" sec");
				throw e;
			}
		}else{
			String findCommand = "mobile:text:find"; // The proprietary function call
			Map<Object, Object> params1 = new HashMap<>();
			params1.put("content", searchText);    // The text we're looking for
			params1.put("dpi", "300");           // Optional DPI parameter
			params1.put("scrolling", "scroll");  // Add the scroll and search
			params1.put("next", "SWIPE_UP");     // Next is mandatory if using scroll and search
			// Can also use customized swipe like:
			// "SWIPE=(50%,80%),(50%,60%);WAIT=1000"
			params1.put("maxscroll", scroll);       // Not mandatory, default is 5
			params1.put("threshold", "95");     // Adding threshold
			((RemoteWebDriver) driver).executeScript(findCommand, params1); // Calling the script
		}
	}
	public void setValueToXpathAndElementDisplayed(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info", "setValueToXpathAndElementNotDisplayed keyword is invoked"); // Ritesh
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String xpathValue = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator, xpathValue);
		COCDriverScript.logMessage("info", "locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error", "Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(objLocator);
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info", locator + " locator is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info", locator + " locator is displayed");
					} else {
						COCDriverScript.logMessage("error", locator + " locator found, but its not displayed");
						throw new ElementFailException(
								new Throwable(locator + " element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error", locator + " locator found, but its disabled");
					throw new ElementFailException(new Throwable(locator + " locator found, but its not displayed"));
				}
		} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error", "Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error", "Invalid locator or locator not found:" + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: " + locator));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error",
						"Locator found and not visible to perform operation , Injecting javascript to perform click");
				injectJsOnNotVisible(element, e);
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error", "Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error", "Incorrect parameters or error while parsing parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));

		}
		COCDriverScript.logMessage("info", "setValueToXpathAndElementNotDisplayed keyword is executed");

	}
}