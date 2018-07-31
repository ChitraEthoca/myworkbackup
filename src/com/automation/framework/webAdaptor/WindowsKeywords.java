package com.automation.framework.webAdaptor;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.Window;
import com.automation.framework.utilities.ObjectRead;

public class WindowsKeywords implements Window {


	/**
	 * @name closeBrowser
	 * @description close current browser
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.closeBrowser
	 */
	public void closeBrowser(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Close Browser action is invoked");
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				COCDriverScript.driver.quit();
				COCDriverScript.logMessage("info","Close Browser successful");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		COCDriverScript.logMessage("info","Close Browser action is executed");

	}
	
	
	/**
	 * @name handleDownloadPopup
	 * @description handleDownload popup
	 * @param params
	 * @throws BrowserException
	 * @throws ElementFailException
	 * @usage Actions.closeBrowser
	 */
	public void handleDownloadPopup(HashMap<String, Object> params)
			throws BrowserException, ElementFailException  {
		COCDriverScript.logMessage("info","handleDownloadPopup action is invoked");
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);

				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);

				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				COCDriverScript.logMessage("info","handleDownloadPopup successful");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}catch (AWTException e) {
				COCDriverScript.logMessage("error","Error in Robot class" + e);
				throw new ElementFailException(new Throwable("Error in Robot class" + e));
			}
		}
		COCDriverScript.logMessage("info","handleDownloadPopup action is executed");

	}

	/**
	 * @name navigateToURL
	 * @description pass URL
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.navigateToURL, URL
	 */
	public void navigateToURL(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Navigate To URL action is invoked");
		String URL = (String) params.get("arg0");
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				COCDriverScript.logMessage("info","URl " + URL);
				if (URL != "" && URL != null) {
					if (URL.contains("http://") || URL.contains("https://")) {
						COCDriverScript.driver.get(URL);
						COCDriverScript.logMessage("info","Navigate To URL successful");
					} else {
						URL = "http://" + URL;
						COCDriverScript.driver.get(URL);
						COCDriverScript.logMessage("info","Navigate To URL successful");
					}
				} else {
					COCDriverScript.logMessage("info","INVALID URL");
					throw new ElementFailException(new Throwable("INVALID URL"));
				}
				COCDriverScript.logMessage("info","Navigate To URL successful");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		COCDriverScript.logMessage("info","Navigate To URL action is executed");
	}
	
	/**
	 * @name switchToFrameBasedOnWebElement
	 * @description Switch to frame of same window based on webelement
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void switchToFrameBasedOnWebElement(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Switch To Frame action is invoked");
		String locator = (String) params.get("arg0");
		By objLocator = ObjectRead.getObject(locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				WebElement element = COCDriverScript.driver.findElement(objLocator);
				COCDriverScript.driver.switchTo().frame(element);
				COCDriverScript.logMessage("info","Switch To Frame successful");
			}catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		COCDriverScript.logMessage("info","Switch To Frame action is executed");
	}
	
	/**
	 * @name switchToFrameBasedOnFrameNo
	 * @description Switch to frame of same window based on webelement
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void switchToFrameBasedOnFrameNo(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Switch To Frame action is invoked");
		String frameNoArg = (String) params.get("arg0");
		int frameNo = Integer.parseInt(frameNoArg);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				COCDriverScript.driver.switchTo().frame(frameNo);
				COCDriverScript.logMessage("info","Switch To Frame successful");
			}catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		COCDriverScript.logMessage("info","Switch To Frame action is executed");
	}

	
	/**
	 * @name switchBetweenWindows
	 * @description switch between the browser windows
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void switchBetweenWindows(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Switch Between Windows action is invoked");

		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				String handle = COCDriverScript.driver.getWindowHandle();
				Set<String> handles = COCDriverScript.driver.getWindowHandles();
				if (handles != null && handles.size() > 1) {
					for (String hnd : handles) {
						if (!hnd.equals(handle)) {
							COCDriverScript.driver.switchTo().window(hnd);
							COCDriverScript.logMessage("info","Switch Between Windows successful");
						}
					}
				} else {
					COCDriverScript.logMessage("error","No window found for switch");
					throw new ElementFailException(new Throwable("No window found for switch"));
				}
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		COCDriverScript.logMessage("info","Switch Between Windows action is executed");
	}

	/**
	 * @name switchToWindowTitle
	 * @description switch to window title
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	@SuppressWarnings("unused")
	public void switchToWindowTitle(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Switch To Window Title action is invoked");
		String URL = null;
		String inputParams = (String) params.get("arg0");
		String windowTitle = null;
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				if (windowTitle != null && windowTitle.length() > 0) {
					COCDriverScript.driver.switchTo().window(windowTitle);
					COCDriverScript.logMessage("info","Switch To Window Title successful");
				} else {
					COCDriverScript.logMessage("info","Invalid Window Title");
					throw new ElementFailException(new Throwable("Invalid Window Title"));
				}
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver");
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		COCDriverScript.logMessage("info","Switch To Window Title action is executed");
	}

	/**
	 * @name maximize
	 * @description maximise the window
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void maximize(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Maximize action is invoked");
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				COCDriverScript.driver.manage().window().maximize();
				COCDriverScript.logMessage("info","Maximize successful");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		COCDriverScript.logMessage("info","Maximize action is executed");
	}



	/**
	 * @name closeAllWindows
	 * @description close all window
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.closeAllWindow
	 */
	public void closeAllWindows(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Close All Browser action is invoked");
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				Set<String> handles = COCDriverScript.driver.getWindowHandles();
				if (handles != null && handles.size() > 0) {
					for (int i = handles.size() - 1; i >= 0; i--) {
						COCDriverScript.driver.switchTo().window(handles.toArray()[i].toString()).close();
					}
					COCDriverScript.logMessage("info","Close All Browser Successful");
				} else {
					COCDriverScript.logMessage("info","No Window Found To Close");
					throw new ElementFailException(new Throwable("No Window Found To Close"));
				}
				COCDriverScript.logMessage("info","Close All Browser successful");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		COCDriverScript.logMessage("info","Close All Browser action is executed");
	}

	/**
	 * @name switchToLastWindow
	 * @description Switch to last window
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions
	 */
	public void switchToLastWindow(HashMap<String, Object> params)
			throws BrowserException, ElementFailException, ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Switch To Last Window action is invoked");
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				Set<String> handles = COCDriverScript.driver.getWindowHandles();
				if (handles != null && handles.size() > 1) {
					COCDriverScript.driver.switchTo().window(handles.toArray()[handles.size() - 1].toString());
					COCDriverScript.logMessage("info","Switch To Last Window successful");
				} else {
					COCDriverScript.logMessage("info","No window found for switch");
					throw new ElementFailException(new Throwable("No window found for switch"));
				}
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		}
		COCDriverScript.logMessage("info","Switch To Last Window action is executed");
	}
}