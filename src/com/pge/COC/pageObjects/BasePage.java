package com.pge.COC.pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.actionInterpreter.COC.Actions;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.automation.framework.utilities.ObjectRead;

//import com.sun.org.apache.bcel.internal.generic.Select;
public class BasePage {

	static String path;
	protected final static Logger log = Logger.getLogger(BasePage.class.getName());

	public boolean waitForPageLoad(WebDriver driver) {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}

	public void waitForText(WebDriver driver, String searchText, int waitTime, int scroll) {
		if ((driver instanceof FirefoxDriver) | (driver instanceof InternetExplorerDriver)
				| (driver instanceof ChromeDriver)) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + searchText
						+ "')]|//*[contains(text(),'" + searchText.toLowerCase() + "')]")));
			} catch (Exception e) {
				Reporter.log(driver.getTitle() + " page did not display the page in " + waitTime + " sec");
				throw e;
			}
		} else {
			String findCommand = "mobile:text:find"; // The proprietary function
														// call
			Map<Object, Object> params = new HashMap<>();
			params.put("content", searchText); // The text we're looking for
			params.put("dpi", "300"); // Optional DPI parameter
			params.put("scrolling", "scroll"); // Add the scroll and search
			params.put("next", "SWIPE_UP"); // Next is mandatory if using scroll
											// and search
			// Can also use customized swipe like:
			// "SWIPE=(50%,80%),(50%,60%);WAIT=1000"
			params.put("maxscroll", scroll); // Not mandatory, default is 5
			params.put("threshold", "95"); // Adding threshold
			((RemoteWebDriver) driver).executeScript(findCommand, params); // Calling
																			// the
																			// script
		}
	}

	public void pressEnter() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
	}

	public void waitForElementVisible(WebDriver driver, By by, int waitTime, int scroll) {
		if ((driver instanceof FirefoxDriver) | (driver instanceof InternetExplorerDriver)
				| (driver instanceof ChromeDriver)) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			} catch (Exception e) {
				Reporter.log("Element was not visible in " + waitTime + " sec");
				throw e;
			}
		}
	}

	public void waitForElementClickable(WebDriver driver, By by, int waitTime, int scroll) {
		if ((driver instanceof FirefoxDriver) | (driver instanceof InternetExplorerDriver)
				| (driver instanceof ChromeDriver)) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(by));
			} catch (Exception e) {
				Reporter.log("Element was not clickable in " + waitTime + " sec");
				throw e;
			}
		}
	}

	public void selectAllCheckbox(WebDriver driver, By locator) throws WebAdaptorException {
		List<WebElement> element = driver.findElements(locator);

		int count = element.size();
		for (int i = 0; i < count; i++) {
			element.get(i).click();
		}

	}

	public void checkAvailability(WebDriver driver, By limitFldLocator) throws WebAdaptorException {
		COC.webAdaptor(Actions.setValueToXpathAndClick, "Edit");
		waitForElementVisible(driver, limitFldLocator, 120, 2);
		COC.webAdaptor(Actions.setValueToXpathAndClick, "CheckboxAvailability");
		COC.webAdaptor(Actions.setValueToXpathAndClick, "SaveBtn");
	}

	public void handleAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();

		} catch (NoAlertPresentException e) {
			// That's fine.
		}
	}

	public static void switchToWindow(WebDriver driver) {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		ArrayList<String> ids = new ArrayList<String>();

		while (itr.hasNext()) {

			ids.add(itr.next());
		}

		driver.switchTo().window(ids.get(1));

	}

	public void enterKeys(WebDriver driver, By loc, String txt) throws Exception {
		waitForElementVisible(driver, loc, 60, 2);
		driver.findElement(loc).clear();
		driver.findElement(loc).sendKeys(txt);
		Thread.sleep(1000);
	}

	public void clickOnEditLink(WebDriver driver, By locator) throws WebAdaptorException, InterruptedException {

		List<WebElement> element1 = driver.findElements(locator);

		int count = element1.size();
		for (int i = 0; i < count; i++) {
			try {
				List<WebElement> element = driver.findElements(locator);
				Thread.sleep(10000);
				String txt = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
				element.get(i).click();
				Select dropdown = new Select(driver.findElement(By.id("cas7")));
				if (i < 4) {
					dropdown.selectByIndex(i);
				} else {
					int j = 2;
					dropdown.selectByIndex(j);
				}
				COC.webAdaptor(Actions.setValueToXpathAndClick, "SaveBtn");
				String loc = "//span[text()='@variable']";
				loc = loc.replaceAll("@variable", txt);
				driver.findElement(By.xpath(loc)).click();
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void clickOnEditSkillLink(WebDriver driver, By locator) throws InterruptedException {
		WebElement element = driver.findElement(locator);
		element.click();
	}

	public void scrollAndFindTextMobile(WebDriver driver, String searchText) {
		String findCommand = "mobile:text:find"; // The proprietary function
													// call
		Map<Object, Object> params = new HashMap<>();
		params.put("content", searchText); // The text we're looking for
		params.put("dpi", "300"); // Optional DPI parameter
		params.put("scrolling", "scroll"); // Add the scroll and search
		params.put("next", "SWIPE_UP"); // Next is mandatory if using scroll and
										// search
		// Can also use customized swipe like:
		// "SWIPE=(50%,80%),(50%,60%);WAIT=1000"
		params.put("maxscroll", "5"); // Not mandatory, default is 5
		params.put("threshold", "95"); // Adding threshold
		((RemoteWebDriver) driver).executeScript(findCommand, params); // Calling
																		// the
																		// script
	}

	public boolean checkElementExistenceOld(WebDriver driver, By locatorType, long waitTime) {
		boolean ret = false;
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		try {
			driver.findElement(locatorType);
			ret = true;
		} catch (NoSuchElementException e) {
			ret = false;
		}
		return ret;
	}

	public boolean checkElementExistence(WebDriver driver, String locatorRealName, long waitTime)
			throws NoSuchElementException, ObjectNameNotFoundException {
		boolean ret = false;
		By locator = ObjectRead.getObject(locatorRealName);

		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

		try {
			COCDriverScript.logMessage("info", "locator is " + locator);
			WebElement element = driver.findElement(locator);
			if (element.isDisplayed()) {
				ret = true;
			} else {
				ret = false;
			}
		} catch (NoSuchElementException e) {
			ret = false;
		}
		return ret;
	}

	public static String captureScreenshot(String ssFileName) throws Exception {

		String screenshotFilePath = null;

		try {
			screenshotFilePath = "Screenshots//" + ssFileName + "_" + System.currentTimeMillis() + ".png";
			File scrFile = ((TakesScreenshot) COCDriverScript.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(screenshotFilePath));
		} catch (Exception e) {
			System.out.println("Error occured while capturing screenshot - " + e);
		}
		return screenshotFilePath;
	}

	public String getElementText(WebDriver driver, String locatorRealName) throws Exception {

		String sElementText = null;
		By locator = ObjectRead.getObject(locatorRealName);

		try {
			COCDriverScript.logMessage("info", "locator is " + locator);
			WebElement element = driver.findElement(locator);
			if (element.isDisplayed()) {
				sElementText = element.getText().trim();
			}
		} catch (NoSuchElementException e) {
			//
		}
		return sElementText;
	}

	public String getValueFromPropertyFile(String filePath, String key) {
		String keyValue = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filePath));
			keyValue = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return keyValue;
	}

	public void enterValue(WebDriver driver, By locator, String value) {
		waitForElementVisible(driver, locator, 120, 2);
		waitForElementClickable(driver, locator, 120, 2);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(value);
	}

	public static String getRootPath() {
		return System.getProperty("user.dir");

	}

	public static String getTestDataValue(String TestDataName) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		path = getRootPath();
		FileInputStream fileLocation = new FileInputStream(new File(path + "/testData/TestDataWFM.xlsx"));
		XSSFWorkbook objWorkBook = new XSSFWorkbook(fileLocation);
		XSSFSheet objSheet = objWorkBook.getSheetAt(0);
		int rowNum = objSheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			int colNum = objSheet.getRow(i).getLastCellNum();
			for (int j = 0; j < colNum; j++) {
				map.put(objSheet.getRow(i).getCell(0).getStringCellValue(),
						objSheet.getRow(i).getCell(1).getStringCellValue());
			}
		}
		String TestDataValue = map.get(TestDataName);
		objWorkBook.close();
		fileLocation.close();
		return TestDataValue;
	}

	public void writeJSErrorInLogFile(String filePath, LogEntries logEntries, String pageUrl) throws IOException {
		File f = new File(filePath);
		if (f.exists()) {
			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(
					"=========================================================================================================================\n");
			bw.append("Page URL : " + pageUrl + "\n");
			for (LogEntry entry : logEntries) {
				try {
					bw.append(
							new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage() + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bw.append(
					"\n=========================================================================================================================");
			bw.close();
		}

	}

}