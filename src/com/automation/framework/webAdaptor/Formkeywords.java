package com.automation.framework.webAdaptor;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.FormElements;
import com.automation.framework.utilities.ObjectRead;

public class Formkeywords implements FormElements {
	final static Logger logger = Logger.getLogger(Formkeywords.class);

	/**
	 * @name selectCheckBox
	 * @description Select the checkbox element
	 * @usage Actions.selectCheckBox , XpathOfTargetCheckBox
	 * @param params
	 *  @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 * @return void
	 */
	public void selectCheckBox(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info"," Select Checkbox action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead.getObject(locator));
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info",locator + " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info",locator+ " element is displayed");
						if (element.getAttribute("type").toLowerCase().equals("checkbox")) {
							if (!element.isSelected()) {
								COCDriverScript.logMessage("info",locator +" CheckBox selected");
								element.click();
								COCDriverScript.logMessage("info",locator + " Checkbox select successfull");
							} else {
								COCDriverScript.logMessage("info",locator +" Check Box is Already selected");
								throw new ElementFailException(new Throwable("Check Box is Already selected"));
							}
						} else {
							COCDriverScript.logMessage("error",locator +"element Found,but  not a check box");
							throw new ElementFailException(new Throwable(locator +" element Found,but  not a check box"));
						}
					} else {
						COCDriverScript.logMessage("error",locator + "element found, but its not displayed");
						throw new ElementFailException(new Throwable(locator +" element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error",locator+ " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator +" element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error","Locator found and not visible to perform operation ");
				throw new ElementFailException(new Throwable("Locator found and not visible to perform operation "));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Select Checkbox action is executed");

	}

	/**
	 * @name unSelectCheckBox
	 * @description uncheck check box
	 * @usage Actions.UnSelectCheckBox , XpathOfTargetCheckBox
	 * @param params
	 * @return void
	 *  @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void unSelectCheckBox(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info"," Uncheck Checkbox action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead.getObject(locator));
				if (element.getAttribute("type").toLowerCase().equals("checkbox")) {
					if (element.isSelected()) {
						COCDriverScript.logMessage("info",locator+" CheckBox unchecked");
						element.click();
						COCDriverScript.logMessage("info",locator+" Checkbox unchecked successful");
					} else {
						COCDriverScript.logMessage("info",locator+" Check Box is Already unchecked");
						throw new ElementFailException(locator +" Check Box is Already unchecked");
					}
				} else {
					COCDriverScript.logMessage("info",locator +" element Found,but  not a check box");
					throw new ElementFailException(new Throwable(locator +" element Found,but  not a check box"));
				}

			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Uncheck Checkbox action is executed");

	}

	/**
	 * @name isCheckboxChecked
	 * @description checks checkbox is Checked  
	 * @usage Actions.isCheckboxChecked, XpathOfTargetCheckBox
	 * @param params
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isCheckboxChecked(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info"," Is Checkbox Checked action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead.getObject(locator));
				boolean actualValue = element.isSelected();
				if (actualValue) {
					COCDriverScript.logMessage("info","Check box is selected");
				} else {
					COCDriverScript.logMessage("info","Check box is not selected");
					throw new ElementFailException("CheckBox is not selected");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error","Locator found and not visible to perform operation ");
				throw new ElementFailException(new Throwable("Locator found and not visible to perform operation "));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Is_Checkbox Checked action is executed");

	}

	/**
	 * @name isRadioButtonSelected
	 * @description passxpath of radio button 
	 * @usage Actions.isRadioButtonSelected, XpathOfTargetRadioElement
	 * @param params
	 * @return void
	 *  @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void isRadioButtonSelected(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info"," Is RadioButton Selected action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		COCDriverScript.logMessage("info","locator is " + locator);
		//boolean ret = false;
		
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead.getObject(locator));
				if (element.isSelected()) {
					COCDriverScript.logMessage("info","Radio Button is Selected");
					COCDriverScript.logMessage("info","Is Radio button perform successful");
					//ret = true;
				} else {
					COCDriverScript.logMessage("info","Radio Button is not selected");
					//throw new ElementFailException("Radio Button is not selected");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Is RadioButton Selected action is executed");
		//return ret;
	}


	/**
	 * @name selectValueFromDropdown
	 * @description Select value from dropdown 
	 * @param params
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	@SuppressWarnings("unused")
	public void selectValueFromDropdown(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info"," Select Value From Dropdown action is invoked");
		String locator = (String) params.get("arg0");
		String text = (String) params.get("arg1");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				Select dropdown = new Select(COCDriverScript.driver.findElement(ObjectRead.getObject(locator)));
				if (dropdown != null) {
					COCDriverScript.logMessage("info","Dropdown Found");
					dropdown.selectByVisibleText(text);
					COCDriverScript.logMessage("info","Select Value From Dropdown successful");
				} else {
					COCDriverScript.logMessage("error","Dropdown not found");
					throw new ElementFailException("Dropdown not found");
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Select Value From Dropdown action is executed");

	}

	/**
	 * @name setMandatoryTextToElement
	 * @description Select text to element 
	 * @param params
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setMandatoryTextToElement(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info","Set Mandatory Text To element action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String text = (String) params.get("params");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead.getObject(locator));
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info",locator + " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info",locator+ " element is displayed");
						if (text != null && text != "") {
							element.clear();
							element.sendKeys(text);
							COCDriverScript.logMessage("info",locator+"Set Text successful");
						} else {
							COCDriverScript.logMessage("info","given Text : " + text);
							COCDriverScript.logMessage("error",locator+" element found and text is invalid");
							throw new ElementFailException(new Throwable(locator+" element found and text is invalid"));
						}
					} else {
						COCDriverScript.logMessage("error","element found, but its not displayed");
						throw new ElementFailException(new Throwable("element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error","element found, but its disabled");
					throw new ElementFailException(new Throwable("element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + e);
				throw new ElementFailException("Invalid locator or locator not found  " + e);
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error","Locator found and not visible to perform operation");
				throw new ElementFailException(new Throwable("Locator found and not visible to perform operation "));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Set Mandatory Text To element action is executed");

	}

	/**
	 * @name setOptionalTextToElement
	 * @description Select optional text to element 
	 * @param params
	 * @return void
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @throws BrowserException
	 */
	public void setOptionalTextToElement(HashMap<String, Object> params)
			throws ObjectNameNotFoundException, ElementFailException, BrowserException {
		COCDriverScript.logMessage("info","Set Optional Text To element action is invoked");
		WebElement element = null;
		String text = (String) params.get("arg1");
		String locator = (String) params.get("arg0");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead.getObject(locator));
				if (element.isEnabled()) {
					COCDriverScript.logMessage("info",locator + " element is enabled");
					if (element.isDisplayed()) {
						COCDriverScript.logMessage("info",locator+ " element is displayed");

						element.clear();
						COCDriverScript.logMessage("info",locator+ " is cleared " );
						element.sendKeys(text);
						COCDriverScript.logMessage("info",text +" is passed in to "+locator+" element" );
					
					} else {
						COCDriverScript.logMessage("error",locator+ " element found, but its not displayed");
						throw new ElementFailException(new Throwable(locator+ " element found, but its not displayed"));
					}
				} else {
					COCDriverScript.logMessage("error",locator+ " element found, but its disabled");
					throw new ElementFailException(new Throwable(locator+ " element found, but its disabled"));
				}
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable("Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + e);
				throw new ElementFailException(new Throwable("Invalid locator or locator not found "+locator));
			} catch (ElementNotVisibleException e) {
				COCDriverScript.logMessage("error",locator+" found and not visible to perform operation");
				throw new ElementFailException(new Throwable(locator+" found and not visible to perform operation "));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable("Error in Webdriver "+e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable("Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Set Optional Text To element action is executed");

	}

}
