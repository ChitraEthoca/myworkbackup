package com.automation.framework.webAdaptor;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.WaitEvent;
import com.automation.framework.utilities.ObjectRead;

public class WaitKeywords implements WaitEvent {

	/**
	 * @name implicitWait
	 * @description wait for specified amount of time
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void implicitWait(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Wait action is invoked");
		String seconds = (String) params.get("arg0");
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (seconds != null && seconds == "") {
			try {
				int time = Integer.parseInt(seconds);
				time = time * 1000;
				COCDriverScript.logMessage("info","Implicit wait time is  " + seconds+" seconds");
				COCDriverScript.driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
				COCDriverScript.logMessage("info","Wait Action Successful");
			} catch (NumberFormatException e) {
				COCDriverScript.logMessage("error","Error in NumberFormat" + e);
				throw new ElementFailException(new Throwable("Error in parsing time unit "));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Wait action is executed");

	}


	/**
	 * @name wait
	 * @description Explicit wait. Halts the execution for the amount of time provide as a input such as wait.low/wait.medium/wait.high
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.wait,"wait.high/low/medium"
	 */
	public void wait(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Wait action is invoked");
		try {
			String waitTime = (String) params.get("arg0");
			int time = Integer.parseInt(COCDriverScript.globalParamMap.get(waitTime));
			time = time * 1000;
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				COCDriverScript.logMessage("error","Error in NumberFormat" + e);
				throw new ElementFailException("Unable to wait");
			}
			COCDriverScript.logMessage("info","Wait Action Successful");
		} catch (NumberFormatException e) {
			COCDriverScript.logMessage("error","Error in NumberFormat" + e);
			throw new ElementFailException(new Throwable("Error in parsing time unit"));
		} catch (WebDriverException e) {
			COCDriverScript.logMessage("error","Error in Webdriver" + e);
			throw new ElementFailException(new Throwable("Error in Webdriver "));
		}
		COCDriverScript.logMessage("info","Wait action is executed");

	}

	/**
	 * @name waitUntilElementVisible
	 * @description wait till element is visible
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void waitUntilElementVisible(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Wait Until Element Visible action is invoked");
		String locator = (String) params.get("arg0");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				WebDriverWait wait = new WebDriverWait(COCDriverScript.driver, 30);
				wait.until(ExpectedConditions
						.visibilityOf(COCDriverScript.driver.findElement(ObjectRead.getObject(locator))));
				COCDriverScript.logMessage("info",locator+" wait Until Element Visible successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: "+locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info","Wait Until Element Visible action is executed");

	}

	/**
	 * @name waitUntilElementClickable
	 * @description wait till element is Clickable.
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void waitUntilElementClickable(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Wait Until Element Clickable action is invoked");
		String locator = (String) params.get("arg0");

		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				WebDriverWait wait = new WebDriverWait(COCDriverScript.driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(ObjectRead.getObject(locator)));

				COCDriverScript.logMessage("info",locator+" wait Until Element Clickable successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " +locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax: "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found: "+locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		COCDriverScript.logMessage("info","Wait Until Element Clickable action is executed");

	}

}
