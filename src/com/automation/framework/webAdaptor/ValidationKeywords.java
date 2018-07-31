package com.automation.framework.webAdaptor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.BrowserException;
import com.automation.framework.exceptions.ElementFailException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.interfaces.Validation;
import com.automation.framework.utilities.ObjectRead;

public class ValidationKeywords implements Validation {
	
	/**
	 * @name verifyBrokenLink
	 * @description verify the broken lines
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyBrokenLink(HashMap<String, Object> params)
                  throws BrowserException, ObjectNameNotFoundException,
                  ElementFailException {
            COCDriverScript.logMessage("info","verifyBrokenLink is invoked");
            // List<WebElement> element = null;
            String locator = (String) params.get("arg0");
            By objLocator = ObjectRead.getObject(locator);
            // for rest Service
            COCDriverScript.logMessage("info","locator is " + locator);
            if (COCDriverScript.driver == null) {
                COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
                  throw new BrowserException(
                              new Throwable(
                                          "Browser is not launched or Driver is failed to initialize"));
            } else if (locator != null && locator.length() > 0) {
                  try {
                        String linkName = COCDriverScript.driver.findElement(objLocator).getAttribute("href");
                              URL url = new URL(linkName);
                              HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                              connection.setRequestMethod("GET");
                              connection.connect();

                              int code = connection.getResponseCode();
                            
                              if (code==200)
                              {
                                COCDriverScript.logMessage("info",locator +" and "+linkName +" link is working fine");
                              }else{
                                  COCDriverScript.logMessage("error",linkName +" link is broken");
                                          throw new ElementFailException(new Throwable(linkName +" link is broken"));
                              }

                  } catch (MalformedURLException | ProtocolException e) {
                      COCDriverScript.logMessage("error","Error in the Link Url" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in the Link Url "+e));
                                    } catch (InvalidSelectorException e) {
                      COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
                        throw new ElementFailException(new Throwable(
                                    "Invalid xpath, verify the xpath syntax: " + locator));
                  } catch (NoSuchElementException e) {
                      COCDriverScript.logMessage("error","Invalid locator or locator not found:," + locator+" ,"+locator+" locator link does not exists in the screen to verify broken link");
                        throw new ElementFailException(new Throwable("Invalid locator or locator not found:," + locator+" ,"+locator+" locator link does not exists in the screen to verify broken link"));
                  } catch (WebDriverException e) {
                      COCDriverScript.logMessage("error","Error in Webdriver" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in Webdriver " + e));
                  } catch (IOException e) {
                      COCDriverScript.logMessage("error","Error in the Link Url" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in the Link Url "+e));
                  }
            } else {
                COCDriverScript.logMessage("error","Incorrect parameters or error while parsing parameter");
                  throw new ElementFailException(new Throwable(
                              "Incorrect parameters or error while parsing parameter"));
            }
            COCDriverScript.logMessage("info","verifyBrokenLink action is executed");
      }

/**
	 * @name setValueToXpathAndverifyElementText
	 * @description set Value To Xpath And verifyElementText
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void setValueToXpathAndverifyElementText(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Verify Element Text action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		
		String inputext = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator,inputext);
		String expectedText = (String) params.get("arg2");
		
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				COCDriverScript.logMessage("info","Expected text is " + expectedText);
				String actualText = null;
				//Modified by injecting javascript
				/*element = D2DriverScript.driver.findElement(objLocator);
				actualText = element.getText();*/
				
				element= COCDriverScript.driver.findElement(objLocator);
				JavascriptExecutor executor = (JavascriptExecutor) COCDriverScript.driver;
                actualText  = (String) executor.executeScript("return arguments[0].innerText",element);
                COCDriverScript.logMessage("info","expected value=" + expectedText
						+ " and actual value=" + actualText);
                
				if (!(actualText.equals(expectedText))) {
					COCDriverScript.logMessage("error","Expected value for a locator "+locator+" " + expectedText
							+" and actual value is " + actualText);
					throw new ElementFailException(new Throwable("Expected value for a locator "+locator+" " + expectedText
							+" and actual value is " + actualText));
				}
				COCDriverScript.logMessage("info","setValueToXpathAndverifyElementText is successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Verify Element Text action is executed");

	}
	/**
	 * @name verifyElementText
	 * @description verifyElementText
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.VerifyElementText , XpathOfTargetElement ,ValueWhichYouWantToCompare
	 */
	public void verifyElementText(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Verify Element Text action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String expectedText = (String) params.get("arg1");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				COCDriverScript.logMessage("info","Expected text is " + expectedText);
				String actualText = null;
				element = COCDriverScript.driver.findElement(ObjectRead
						.getObject(locator));
				actualText = element.getText().trim();
				COCDriverScript.logMessage("info","Actual text is " + actualText);
				if (!(actualText.equalsIgnoreCase(expectedText))) {
					COCDriverScript.logMessage("error","expected value= " + expectedText
							+ " and actual value is " + actualText);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedText
									+ " and actual value is " + actualText));
				}
				COCDriverScript.logMessage("info","verify element text is successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","Verify Element Text action is executed");

	}
	


	/**
	 * @name verifyBrokenLinkByLinkName
	 * @description verify the broken lines
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyBrokenLinkByLinkName(HashMap<String, Object> params)
                  throws BrowserException, ObjectNameNotFoundException,
                  ElementFailException {
            COCDriverScript.logMessage("info","verifyBrokenLinkByLinkName is invoked");
            // List<WebElement> element = null;
            String linkName = (String) params.get("arg0");
            
            // for rest Service
            COCDriverScript.logMessage("info","Link is " + linkName);
            if (COCDriverScript.driver == null) {
                COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
                  throw new BrowserException(
                              new Throwable(
                                          "Browser is not launched or Driver is failed to initialize"));
            } 
                  try {
                              URL url = new URL(linkName);
                              HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                              connection.setRequestMethod("GET");
                              connection.connect();

                              int code = connection.getResponseCode();
                            
                              if (code==200)
                              {
                                COCDriverScript.logMessage("info",linkName +" link is working fine");
                              }else{
                                  COCDriverScript.logMessage("error",linkName +" link is broken");
                                          throw new ElementFailException(new Throwable(linkName +" link is broken"));
                              }

                  } catch (MalformedURLException | ProtocolException e) {
                      COCDriverScript.logMessage("error","Error in the Link Url" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in the Link Url "+e));
                  } catch (WebDriverException e) {
                      COCDriverScript.logMessage("error","Error in Webdriver" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in Webdriver " + e));
                  } catch (IOException e) {
                      COCDriverScript.logMessage("error","Error in the Link Url" + e);
                        throw new ElementFailException(new Throwable(
                                    "Error in the Link Url "+e));
                  }
            
            COCDriverScript.logMessage("info","verifyBrokenLinkByLinkName action is executed");
      }


	/**
	 * @name verifyPageTitle
	 * @description verify the page title
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyPageTitle(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Verify Page Title action is invoked");
		
		String expectedTitle = (String) params.get("arg0");
		COCDriverScript.logMessage("info","Expected Title is "+expectedTitle);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				String actualTitle = COCDriverScript.driver.getTitle();
				COCDriverScript.logMessage("info","Page Title is "+actualTitle);
				if (!(actualTitle.equals(expectedTitle))) {
					throw new ElementFailException(new Throwable(
							"Page title mismatch"));
				}
				COCDriverScript.logMessage("info","Verify Page Title action is successful");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		COCDriverScript.logMessage("info","Verify Page Title action is executed");

	}
	
	/**
	 * @name verifyPageTitle
	 * @description verify the page title
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void setValueToXpathAndverifyElementContainsText(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","setValueToXpathAndverifyElementContainsText action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		
		String inputext = (String) params.get("arg1");
		By objLocator = ObjectRead.getObject(locator,inputext);
		String expectedText = (String) params.get("arg2");
		
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				COCDriverScript.logMessage("info","Expected text is " + expectedText);
				String actualText = null;
				element = COCDriverScript.driver.findElement(objLocator);
				actualText = element.getText();
                COCDriverScript.logMessage("info","expected value=" + expectedText
						+ " and actual value=" + actualText);
                
				if (!(actualText.equals(expectedText))) {
					COCDriverScript.logMessage("error","expected value= " + expectedText
							+ " and actual value is " + actualText);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedText
									+ " and actual value is " + actualText));
				}
				COCDriverScript.logMessage("info","setValueToXpathAndverifyElementText is successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","setValueToXpathAndverifyElementContainsText action is executed");

	}
	

	
	/**
	 * @name verifyTextUsingJavascript
	 * @description verifyTextUsingJavascript
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.VerifyElementText , XpathOfTargetElement ,ValueWhichYouWantToCompare
	 */
	public void verifyTextUsingJavascript(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","verifyElementContainsText action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String expectedText = (String) params.get("arg1");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				COCDriverScript.logMessage("info","Expected text is " + expectedText);
				String actualText = null;
				element = COCDriverScript.driver.findElement(ObjectRead
						.getObject(locator));
				actualText = element.getText();
				COCDriverScript.logMessage("info","Actual text is " + actualText);
				if (!(actualText.contains(expectedText))) {
					COCDriverScript.logMessage("error","expected value= " + expectedText
							+ " and actual value is " + actualText);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedText
									+ " and actual value is " + actualText));
				}
				COCDriverScript.logMessage("info","verify element text contains is successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","verifyElementContainsText action is executed");

	}
	
	/**
	 * @name verifyElementContainsText
	 * @description verifyElementText
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage Actions.VerifyElementText , XpathOfTargetElement ,ValueWhichYouWantToCompare
	 */
	public void verifyElementContainsText(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","verifyElementContainsText action is invoked");
		WebElement element = null;
		String locator = (String) params.get("arg0");
		String expectedText = (String) params.get("arg1");
		COCDriverScript.logMessage("info","locator is " + locator);
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null && locator.length() > 0) {
			try {
				COCDriverScript.logMessage("info","Expected text is " + expectedText);
				String actualText = null;
				element = COCDriverScript.driver.findElement(ObjectRead
						.getObject(locator));
				actualText = element.getText();
				COCDriverScript.logMessage("info","Actual text is " + actualText);
				if (!(actualText.contains(expectedText))) {
					COCDriverScript.logMessage("error","expected value= " + expectedText
							+ " and actual value is " + actualText);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedText
									+ " and actual value is " + actualText));
				}
				COCDriverScript.logMessage("info","verify element text contains is successful");
			} catch (InvalidSelectorException e) {
				COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid xpath, verify the xpath syntax "+locator));
			} catch (NoSuchElementException e) {
				COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
				throw new ElementFailException(new Throwable(
						"Invalid locator or locator not found: " + locator));
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		} else {
			COCDriverScript.logMessage("error","Incorrect parameters or error in parameter");
			throw new ElementFailException(new Throwable(
					"Incorrect parameters or error in parameter"));
		}
		COCDriverScript.logMessage("info","verifyElementContainsText action is executed");

	}
	
	/**
	 * @name setValueToXpathAndVerifyElementTextUsingJavascript
	 * @description verify element text using javascript by passing xpath value
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void setValueToXpathAndVerifyElementTextUsingJavascript(HashMap<String, Object> params)
              throws BrowserException, ObjectNameNotFoundException,
              ElementFailException {
				COCDriverScript.logMessage("info","stValueToXpathAndVerifyElementTextUsingJavascript action is invoked");
				WebElement element = null;
				String locator = (String) params.get("arg0");
				String xapthValue = (String) params.get("arg1");
				By objLocator = ObjectRead.getObject(locator,xapthValue);
				String expectedText = (String) params.get("arg2");
				COCDriverScript.logMessage("info","locator is " + locator);
				if (COCDriverScript.driver == null) {
		              COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
		              throw new BrowserException(new Throwable(
		            		  "Browser is not launched or Driver is failed to initialize"));
				} else if (locator != null && locator.length() > 0) {
		              try {
                              String actualText = null;
                              COCDriverScript.logMessage("info","Expected Text= " + expectedText);
                              element= COCDriverScript.driver.findElement(objLocator);
                              JavascriptExecutor executor = (JavascriptExecutor)COCDriverScript.driver;
                              actualText = (String) executor.executeScript("return arguments[0].value", element);
                              COCDriverScript.logMessage("info","Text from the given locator is actualText=" + actualText);
                             
                              if (actualText != null && actualText!= "") {                                             
                          
                            	  COCDriverScript.logMessage("info","setValueToXpathAndVerifyElementTextUsingJavascript is successful");
                                          if (!(actualText.equals(expectedText))) {
                            					COCDriverScript.logMessage("error","expected value= " + expectedText
                            							+ " and actual value is " + actualText);
                            					throw new ElementFailException(new Throwable(
                            							"expected value= " + expectedText
                            									+ " and actual value is " + actualText));
                            				}
                                          
                              } else {
                                          COCDriverScript.logMessage("error","Element found, but  Element Text Not Found");
                                          throw new ElementFailException(
                                                      "Element found, but  Element Text Not Found");
                              }
                              
                             
              				
		              } catch (InvalidSelectorException e) {
		                              COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
		                              throw new ElementFailException(new Throwable(
		                                                              "Invalid xpath, verify the xpath syntax: " + locator));
		              } catch (NoSuchElementException e) {
		                              COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
		                              throw new ElementFailException(new Throwable(
		                                                              "Invalid locator or locator not found: " + locator));
		              } catch (WebDriverException e) {
		                              COCDriverScript.logMessage("error","Error in Webdriver" + e);
		                              throw new ElementFailException(new Throwable(
		                                                              "Error in Webdriver " + e));
		              }
				} else {
				              COCDriverScript.logMessage("error","Incorrect parameters or error while parsing parameter");
				              throw new ElementFailException(new Throwable(
				                                              "Incorrect parameters or error while parsing parameter"));
				}
				COCDriverScript.logMessage("info","stValueToXpathAndVerifyElementTextUsingJavascript action is executed");
		
		}
	
	/**
	 * @name verifyElementTextUsingJavascript
	 * @description verify element text using javascript
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyElementTextUsingJavascript(HashMap<String, Object> params)
              throws BrowserException, ObjectNameNotFoundException,
              ElementFailException {
				COCDriverScript.logMessage("info","verifyElementTextUsingJavascript action is invoked");
				WebElement element = null;
				String locator = (String) params.get("arg0");
				By objLocator = ObjectRead.getObject(locator);
				String expectedText = (String) params.get("arg1");
				COCDriverScript.logMessage("info","locator is " + locator);
				if (COCDriverScript.driver == null) {
		              COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
		              throw new BrowserException(new Throwable(
		            		  "Browser is not launched or Driver is failed to initialize"));
				} else if (locator != null && locator.length() > 0) {
		              try {
                              String actualText = null;
                              COCDriverScript.logMessage("info","Expected Text= " + expectedText);
                              element= COCDriverScript.driver.findElement(objLocator);
                              JavascriptExecutor executor = (JavascriptExecutor)COCDriverScript.driver;
                              actualText = (String) executor.executeScript("return arguments[0].value", element);
                              COCDriverScript.logMessage("info","Text from the given locator is actualText=" + actualText);
                             
                              if (actualText != null && actualText!= "") {                                             
                          
                            	  COCDriverScript.logMessage("info","verifyElementTextUsingJavascript successful");
                                          if (!(actualText.equals(expectedText))) {
                            					COCDriverScript.logMessage("error","expected value= " + expectedText
                            							+ " and actual value is " + actualText);
                            					throw new ElementFailException(new Throwable(
                            							"expected value= " + expectedText
                            									+ " and actual value is " + actualText));
                            				}
                                          
                              } else {
                                          COCDriverScript.logMessage("error","Element found, but  Element Text Not Found");
                                          throw new ElementFailException(
                                                      "Element found, but  Element Text Not Found");
                              }
                              
                             
              				
		              } catch (InvalidSelectorException e) {
		                              COCDriverScript.logMessage("error","Invalid xpath, verify the xpath syntax " + locator);
		                              throw new ElementFailException(new Throwable(
		                                                              "Invalid xpath, verify the xpath syntax: " + locator));
		              } catch (NoSuchElementException e) {
		                              COCDriverScript.logMessage("error","Invalid locator or locator not found " + locator);
		                              throw new ElementFailException(new Throwable(
		                                                              "Invalid locator or locator not found: " + locator));
		              } catch (WebDriverException e) {
		                              COCDriverScript.logMessage("error","Error in Webdriver" + e);
		                              throw new ElementFailException(new Throwable(
		                                                              "Error in Webdriver " + e));
		              }
				} else {
				              COCDriverScript.logMessage("error","Incorrect parameters or error while parsing parameter");
				              throw new ElementFailException(new Throwable(
				                                              "Incorrect parameters or error while parsing parameter"));
				}
				COCDriverScript.logMessage("info","verifyElementTextUsingJavascript action is executed");
		
		}
	  
	/**
	 * @name verifyPageURL
	 * @description Verifies page tiltle with the expected input URL
	 * @param params
	 * @throws BrowserException
	 * @throws ObjectNameNotFoundException
	 * @throws ElementFailException
	 * @usage action
	 */
	public void verifyPageURL(HashMap<String, Object> params)
			throws BrowserException, ElementFailException,
			ObjectNameNotFoundException {
		COCDriverScript.logMessage("info","Verify Page URL  action is invoked");
		String expectedURL = null;
		String inputParams = (String) params.get("arg0");
		expectedURL = inputParams;
		if (COCDriverScript.driver == null) {
			COCDriverScript.logMessage("error","Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(
					new Throwable(
							"Browser is not launched or Driver is failed to initialize"));
		} else {
			try {
				COCDriverScript.logMessage("info","Expected URL is " + expectedURL);
				String actualURL = null;
				actualURL = COCDriverScript.driver.getCurrentUrl();
				COCDriverScript.logMessage("info","Actual URL is " + actualURL);
				if (!(actualURL.equals(expectedURL))) {
					COCDriverScript.logMessage("error","expected value= " + expectedURL
							+ " and actual value is " + actualURL);
					throw new ElementFailException(new Throwable(
							"expected value= " + expectedURL
									+ " and actual value is " + actualURL));
				}
				COCDriverScript.logMessage("info","verifyPageURL is successful");
			} catch (WebDriverException e) {
				COCDriverScript.logMessage("error","Error in Webdriver" + e);
				throw new ElementFailException(new Throwable(
						"Error in Webdriver " + e));
			}
		}
		COCDriverScript.logMessage("info","Verify Page URL action is executed");

	}
}