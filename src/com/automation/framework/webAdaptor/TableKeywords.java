package com.automation.framework.webAdaptor;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.interfaces.Table;
import com.automation.framework.pojs.TestStepResults;
import com.automation.framework.pojs.TestStepResults.ReturnValueTypes;
import com.automation.framework.utilities.ObjectRead;

public class TableKeywords implements Table {
	final static Logger logger = Logger.getLogger(TableKeywords.class);

	/**
	 * @name  getNumberOfRowOfTable
	 * @description get the count of rows
	 * @param params
	 * @return void
	 * @throws Exception
	 * @usage Actions
	 */
	public void getNumberOfRowOfTable(HashMap<String, Object> params) throws Exception {
		logger.info("Get number of rows of a table action is invoked");
		TestStepResults stepResults = new TestStepResults();
		WebElement element = null;
		String locator = (String) params.get("locator");
		;
		// locator = inputParams;
		logger.info("locator is " + locator);
		if (COCDriverScript.driver == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new Exception("Browser is not launched or Driver is failed to initiaize" + stepResults);
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead.getObject(locator));
				List<WebElement> rows_table = element.findElements(By.tagName("tr"));
				if (!rows_table.get(0).toString().contains("tr")) {
					failMessage("No Rows Found", stepResults, null);
				} else {
					int rows_count = rows_table.size();
					logger.info("Number of rows found in a table: " + rows_count);
					stepResults.setReturnValueType(ReturnValueTypes.INTEGER.toString());
					stepResults.setReturnValue(Integer.toString(rows_count));
					successMessage("no of rows returned successful", stepResults);
				}
			} catch (InvalidSelectorException e) {
				logger.error("Invalid xpath, verify the xpath syntax " + e.getMessage());
				throw new InvalidSelectorException("Invalid xpath, verify the xpath syntax " + stepResults + e);
			} catch (NoSuchElementException e) {
				logger.error("Invalid locator or locator not found " + e.getMessage());
				throw new NoSuchElementException("Invalid locator or locator not found " + stepResults + e);
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e.getMessage());
				throw new WebDriverException("Error in Webdriver" + stepResults + e);
			} catch (Exception e) {
				logger.error("Error in Get number of rows of a table operation" + e.getMessage());
				throw new Exception("Error in Get number of rows of a table operation" + stepResults + e);
			}
		} else {
			logger.error("Incorrect parameters or error in parameter");
			throw new Exception("Incorrect parameters or error in parameter" + stepResults);
		}
		logger.info("Get number of rows of a table action is executed");

	}

	/**
	 * @name  getNumberOfColumnOfTable
	 * @description get the count of columns
	 * @param params
	 * @return void
	 * @throws Exception
	 * @usage Actions
	 */
	public void getNumberOfColumnOfTable(HashMap<String, Object> params) throws Exception {
		logger.info("Get no of columns of a table action is invoked");
		TestStepResults stepResults = new TestStepResults();
		WebElement element = null;
		String locator = null;
		String inputParams = (String) params.get("locator");
		locator = inputParams;
		logger.info("locator is " + locator);
		if (COCDriverScript.driver == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new Exception("Browser is not launched or Driver is failed to initiaize" + stepResults);
		} else if (locator != null && locator.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead.getObject(locator));
				List<WebElement> col_table = element.findElements(By.tagName("th"));
				if (!col_table.contains("th")) {
					throw new Exception("No Columns Found" + stepResults);
				} else {
					int col_count = col_table.size();
					logger.info("Number of column in a table: " + col_count);
					stepResults.setReturnValueType(ReturnValueTypes.INTEGER.toString());
					stepResults.setReturnValue(Integer.toString(col_count));
					// return rows_count;
					successMessage("no of rows of a table returned successful", stepResults);
				}
			} catch (InvalidSelectorException e) {
				logger.error("Invalid xpath, verify the xpath syntax " + e.getMessage());
				throw new InvalidSelectorException(
						"Invalid xpath, verify the xpath syntax" + stepResults + e.getMessage());
			} catch (NoSuchElementException e) {
				logger.error("Invalid locator or locator not found " + e.getMessage());
				throw new NoSuchElementException(
						"Invalid locator or locator not found " + stepResults + e.getMessage());
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e.getMessage());
				throw new WebDriverException("Error in Webdriver " + stepResults + e.getMessage());
			} catch (Exception e) {
				logger.error("Error in Get no columns of a table  operation" + e.getMessage());
				throw new Exception("Error in Get no columns of a table  operation " + stepResults + e.getMessage());
			}
		} else {
			logger.error("Incorrect parameters or error in parameter");
			throw new Exception("Error in Get no columns of a table  operation " + stepResults);
		}
		logger.info("Get no columns of a table action is executed");

	}
	
	/**
	 * @name  getDataFromParticularCell
	 * @description get the data from the cell
	 * @param params
	 * @return void
	 * @throws Exception
	 * @usage Actions
	 */

	public void getDataFromParticularCell(HashMap<String, Object> params) throws Exception {
		logger.info("Get data  from particular cell action is invoked");
		TestStepResults stepResults = new TestStepResults();
		WebElement element = null;
		String className = null;
		int row = 0;
		int col = 0;
		String inputParams = (String) params.get("locator");

		if (inputParams.contains("\\$")) {
			String[] inputs = inputParams.split("\\$");
			className = inputs[0] + "=" + inputs[1];
			row = Integer.parseInt(inputs[2]);
			col = Integer.parseInt(inputs[3]);
		} else if (inputParams.contains("##")) {
			String[] inputs = inputParams.split("##");
			className = inputs[0];
			row = Integer.parseInt(inputs[1]);
			col = Integer.parseInt(inputs[2]);
		} else {
			failMessage("Data not in standard format", stepResults, null);

		}
		logger.info("locator is " + className);
		if (COCDriverScript.driver == null) {
			logger.error("Browser is not launched or Driver is failed to initiaize");
			throw new Exception("Browser is not launched or Driver is failed to initiaize");
		} else if (className != null && className.length() > 0) {
			try {
				element = COCDriverScript.driver.findElement(ObjectRead.getObject(className));
				String outer_body = "tr:nth-child(" + row + ") td:nth-child(" + col + ")";
				if (!className.startsWith(".")) {
					className = ".".concat(className);
				}
				String class_name_mod = className.replaceAll(" ", ".");
				class_name_mod = class_name_mod + " ".concat(outer_body);
				element = COCDriverScript.driver.findElement(By.cssSelector(class_name_mod));
				String getText = element.getText();
				logger.info("Data of a particular cell " + getText);
				stepResults.setReturnValueType(ReturnValueTypes.STRING.toString());
				stepResults.setReturnValue(getText);
				successMessage("Get data from a particular cell returned successfully", stepResults);

			} catch (InvalidSelectorException e) {
				logger.error("Invalid xpath, verify the xpath syntax " + e.getMessage());
				throw new InvalidSelectorException("Invalid xpath, verify the xpath syntax " + stepResults + e);
			} catch (NoSuchElementException e) {
				logger.error("Invalid locator or locator not found " + e.getMessage());
				throw new NoSuchElementException("Invalid locator or locator not found " + stepResults + e);
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e.getMessage());
				throw new WebDriverException("Error in Webdriver" + stepResults + e);
			} catch (Exception e) {
				logger.error("Error in Get data  from particular cell  operation" + e.getMessage());
				throw new Exception("Error in Get data  from particular cell  operation" + stepResults + e);
			}
		} else {
			logger.error("Incorrect parameters or error in parameter");
			throw new Exception("Incorrect parameters or error in parameter" + stepResults);
		}
		logger.info("Get data from a particular cell action is executed");

	}

	/**
	 * @name  pagination
	 * @description get the data from the cell
	 * @param input
	 * @param tableXpath
	 * @param driver
	 * @param nextButtonXpath
	 * @return void
	 * @usage Actions
	 */
	public void pagination(String input, String tableXpath, String nextButtonXpath, WebDriver driver) {
		String inputText = input;
		boolean found = false;
		boolean breakLoop = false;
		while (!found && !breakLoop) {
			try {
				WebElement tableElement = COCDriverScript.driver.findElement(By.xpath(tableXpath));
				try {
					WebElement webElement = tableElement
							.findElement(By.xpath("//a[contains(text()," + inputText + ")]"));
					if (inputText.equals(webElement.getText())) {
						found = true;
						breakLoop = true;
						break;
					}
				} catch (NoSuchElementException e) {
				}
			} catch (NoSuchElementException e) {
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				WebElement webElement1 = COCDriverScript.driver.findElement(By.xpath(nextButtonXpath));
				if (!found) {
					webElement1.click();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (NoSuchElementException e) {
				breakLoop = true;
			} catch (WebDriverException e) {
			} catch (Exception e) {
			}
		}
	}
/**
 * @name successMessage
 * @param msg
 * @param stepResults
 */
	private void successMessage(String msg, TestStepResults stepResults) {
		stepResults.setStatus("Pass");
		stepResults.setComment(msg);
		stepResults.setErrMsg("");
	}
/**
 * @name failMessage
 * @param msg
 * @param stepResults
 * @param e
 */
	private void failMessage(String msg, TestStepResults stepResults, Exception e) {
		stepResults.setStatus("fail");
		stepResults.setComment(msg);
		stepResults.setErrMsg(msg);
		if (e != null) {
			stepResults.setErrMsg(msg + " " + e.getMessage());
		}
	}

}
