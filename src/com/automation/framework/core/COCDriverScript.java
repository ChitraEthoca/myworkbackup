package com.automation.framework.core;

/**
 * @author manoj
 *
 */
import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.utilities.XLUtil;
import com.pge.COC.pageObjects.BasePage;

public class COCDriverScript {

	public final static Logger logger = Logger.getLogger(COCDriverScript.class);
	public static WebDriver driver;
	public static XLUtil xlUtil;
	public static Map<String, String> globalParamMap;
	public static Map<String, String> logMap;
	private static String alMID;
	public static Map<String, Hashtable<String, String>> businessActionsMap;
	public static Map<String, String> mapClassName;
	public static String testDataFile;
	static boolean failedScreenShot = true;
	static boolean passedScreenShot = true;

	/**
	 * @param propertyUtil
	 * @throws IOException
	 */

	public COCDriverScript(String almID) throws IOException {
		COCDriverScript.alMID = almID;
		mapClassName = new HashMap<String, String>();
	}

	/**
	 * Sets the driver.
	 *
	 * @param browserType
	 *            the new driver
	 * @throws DriverScriptException
	 */
	private void setDriver(String browserType) throws DriverScriptException {
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "set driver method: Start");
		}

		switch (browserType) {
		case "chrome":
			initChromeDriver();
			break;
		case "firefox":
			initFirefoxDriver();
			break;
		case "IERemote":
			initIERemote();
			break;

		case "IELocal":
			initIELocal();
			break;
		default:
			COCDriverScript.logMessage("info",
					"browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			initFirefoxDriver();
		}
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "set driver method: End");
		}
	}

	/**
	 * Initializes the IE Local driver initialization.
	 */
	private void initIELocal() {
		String driverPath = globalParamMap.get("driverPath");
		System.setProperty("webdriver.ie.driver", driverPath);
		DesiredCapabilities capIElocal = DesiredCapabilities.internetExplorer();
		capIElocal.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capIElocal.setCapability("requireWindowFocus", true);
		driver = new InternetExplorerDriver(capIElocal);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	/**
	 * Initializes the IE remote driver initialization.
	 *
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	private void initIERemote() throws DriverScriptException {
		DesiredCapabilities capIERemote = DesiredCapabilities.chrome();
		capIERemote.setBrowserName("internet explorer");
		capIERemote.setPlatform(Platform.WINDOWS);
		capIERemote.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// cap.setJavascriptEnabled(true);
		capIERemote.setCapability("requireWindowFocus", true);
		String Node = "http://localhost:5555/wd/hub";
		try {
			driver = new RemoteWebDriver(new URL(Node), capIERemote);
		} catch (MalformedURLException e) {
			throw new DriverScriptException(
					"Error in launching IE as remote webdriver. Please verify whether connection is established between node and hub");
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	/**
	 * Initializes the chrome driver.
	 * 
	 * @throws DriverScriptException
	 */
	private void initChromeDriver() throws DriverScriptException {
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "initChromeDriver method: Start");
		}
		String driverPath = globalParamMap.get("driverPath");
		if (driverPath != null) {
			COCDriverScript.logMessage("info", "Launching google chrome with new profile..");
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else {
			COCDriverScript.logMessage("error",
					"chrome driver path is not configured in Property file to launch the chrome Browser");
			throw new DriverScriptException(
					"chrome driver path is not configured in Property file to launch the chrome Browser");
		}
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "initChromeDriver method: End");
		}
	}

	/**
	 * Initializes the Firefox driver.
	 */
	private void initFirefoxDriver() {
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "initFirefoxDriver method: Start");
		}
		COCDriverScript.logMessage("info", "Launching Firefox browser..");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "initChromeDriver method: Start");
		}
	}

	/*
	 * // Firefox - 47 & above private void initFirefoxDriver() throws
	 * DriverScriptException { if (logger.isDebugEnabled()) {
	 * COCDriverScript.logMessage("info", "initFirefoxDriver method: Start"); }
	 * String driverPath = globalParamMap.get("driverPath"); if (driverPath !=
	 * null) { COCDriverScript.logMessage("info",
	 * "Launching firefox with new profile..");
	 * System.setProperty("webdriver.firefox.driver", driverPath); driver = new
	 * FirefoxDriver(); driver.manage().window().maximize(); } else {
	 * COCDriverScript.logMessage("error",
	 * "Firefox driver path is not configured in Property file to launch the chrome Browser"
	 * ); throw new DriverScriptException(
	 * "Firefox driver path is not configured in Property file to launch the chrome Browser"
	 * ); } if (logger.isDebugEnabled()) { COCDriverScript.logMessage("info",
	 * "initChromeDriver method: End"); } }
	 */

	/**
	 * Launch application.
	 *
	 * @param propertyUtil
	 *            the property util
	 * @throws DriverScriptException
	 */
	public void launchApplication() throws DriverScriptException {

		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "launchApplication method: Start");
		}
		String url = globalParamMap.get("url");
		if (url != null) {
			COCDriverScript.logMessage("info", "Loading URL" + url + " in browser");
			driver.navigate().to(url);
		} else {
			throw new DriverScriptException("URL is not configured in Property file");
		}
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "launchApplication method: Start");
		}
	}

	/**
	 * Initialize test base setup.
	 *
	 * @param propertyUtil
	 *            the property util
	 * @throws DriverScriptException
	 */
	public void initializeTestBaseSetup() throws DriverScriptException {
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "initializeTestBaseSetup method: Start");
		}
		String browser = globalParamMap.get("browser");
		if (browser != null) {
			setDriver(browser);
		} else {
			throw new DriverScriptException("Browser is not configured in Property file");
		}
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "initializeTestBaseSetup method: Start");
		}
	}

	/**
	 * Gets the global config excel values.
	 *
	 * @param file
	 *            the file
	 * @return the global config map
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	public void getGlobalConfigMap(String file) throws DriverScriptException {
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "getGlobalConfigMap method: Start");
		}
		xlUtil = new XLUtil();
		globalParamMap = new HashMap<String, String>();
		if (null != file) {
			COCDriverScript.logMessage("info", "Opening GlobalParameter workbook...");
			xlUtil.openWorkBook(new File(file));
			if (xlUtil.isSheetExists("GlobalParameter")) {
				COCDriverScript.logMessage("info", "GlobalParameter sheet exists");
				String globalParameterSheetName = "GlobalParameter";
				int totalRowCntGlobalParameter = xlUtil.getTotalRowCount(globalParameterSheetName);
				for (int row = 1; row <= totalRowCntGlobalParameter; row++) {
					String globalParamName = xlUtil.getCellValue(globalParameterSheetName, row, 0);
					String globalParamValue = xlUtil.getCellValue(globalParameterSheetName, row, 1);
					globalParamMap.put(globalParamName, globalParamValue);
					COCDriverScript.logMessage("info", globalParamName + "  " + globalParamValue);
				}
			} else {
				COCDriverScript.logMessage("error", "GlobalParameter sheet does not exists");
				throw new DriverScriptException("GlobalParameter sheet does not exists");
			}
		}
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info", "getGlobalConfigMap method: end");
		}
	}

	/**
	 * Gets the field values by business action.
	 *
	 * @param columnCount
	 *            the column count
	 * @param businessComponentValue
	 *            the business component value
	 * @return the field values by business action
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	public static Hashtable<String, String> getFieldValuesByBusinessAction(String businessComponentValue,
			String functionalityName) throws DriverScriptException {
		Hashtable<String, String> fieldValues = businessActionsMap.get(businessComponentValue + functionalityName);
		if (fieldValues != null) {
			COCDriverScript.logMessage("info", "Combination of businessComponentValue " + businessComponentValue
					+ " and functionalityName " + functionalityName + " for almID " + alMID + " exists");
			return fieldValues;
		}
		COCDriverScript.logMessage("error", "Combination of businessComponentValue " + businessComponentValue
				+ " and functionalityName " + functionalityName + " for almID " + alMID + " doesnot exists");
		throw new DriverScriptException("Combination of businessComponentValue " + businessComponentValue
				+ " and functionalityName " + functionalityName + " for almID " + alMID + " doesnot exists");
	}

	/**
	 * Log messages.
	 *
	 * @param logType
	 *            info/error
	 * @param msg
	 *            the log message
	 */
	public static void logMessage(String logType, String msg) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];
		String packageName = element.getClassName();
		String className;
		if (mapClassName.get(packageName) == null) {
			className = getClassname(element.getClassName());
			mapClassName.put(packageName, className);
		} else {
			className = mapClassName.get(packageName);
		}
		// <![CDATA[2016-01-25 06:06:29 testStepDone $$$$$
		// BusinessComponentsFactory : login $$$$$ pge_ecm_tst is successfully
		// selected in the reporsitory type dropdown<br/>]]>
		if (logType.toLowerCase().contains("fail")) {
			// if(msg.contains(".png")){
			// Reporter.log(dateFormat.format(date)+" " + logType + msg +
			// "######<br/>");
			Reporter.log(dateFormat.format(date) + " " + logType + " $$$$$ " + className + " : "
					+ element.getMethodName() + " $$$$$ " + msg + "<br/>");
			msg = msg.replace("$$$$$", "").replace("#####", "");

			// logger.error(dateFormat.format(date)+ " " + logType + msg);
			logger.error(dateFormat.format(date) + " " + logType + " : " + className + " : " + element.getMethodName()
					+ " - " + msg);
			// }
		} else if (logType.toLowerCase().contains("pass") || logType.toLowerCase().contains("done")) {
			Reporter.log(dateFormat.format(date) + " " + logType + " $$$$$ " + className + " : "
					+ element.getMethodName() + " $$$$$ " + msg + "<br/>");
			logger.info(dateFormat.format(date) + " " + logType + " : " + className + " : " + element.getMethodName()
					+ " - " + msg);
		} else if (logType.toLowerCase().contains("info")) {
			logger.info(dateFormat.format(date) + " " + logType + " : " + className + " : " + element.getMethodName()
					+ " - " + msg);
		} else if (logType.toLowerCase().contains("error")) {
			logger.error(dateFormat.format(date) + " " + logType + " : " + className + " : " + element.getMethodName()
					+ " - " + msg);
		}
	}

	/**
	 * Log messages.
	 *
	 * @param logType
	 *            info/error
	 * @param msg
	 *            the log message
	 */
	public static void logMessage(String logType, String msg, String sFileName) {

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			StackTraceElement element = stackTrace[2];
			String packageName = element.getClassName();
			String className;
			if (mapClassName.get(packageName) == null) {
				className = getClassname(element.getClassName());
				mapClassName.put(packageName, className);
			} else {
				className = mapClassName.get(packageName);
			}

			BasePage oBP = new BasePage();
			// screenshot = screenshot.replace("Screenshots//",
			// "../Screenshots/");

			String ssFilePath = oBP.captureScreenshot(sFileName).replace("Screenshots//", "../Screenshots/");

			// <![CDATA[2016-01-25 06:06:29 testStepDone $$$$$
			// BusinessComponentsFactory : login $$$$$ pge_ecm_tst is
			// successfully selected in the reporsitory type dropdown<br/>]]>
			if (logType.toLowerCase().contains("failwithss")) {
				// if(msg.contains(".png")){
				// Reporter.log(dateFormat.format(date)+" " + logType + msg +
				// "######<br/>");
				Reporter.log(dateFormat.format(date) + " " + logType + " $$$$$ " + className + " : "
						+ element.getMethodName() + " $$$$$ " + msg + " $$$$$ " + ssFilePath /* + "<br/>" */);
				msg = msg.replace("$$$$$", "").replace("#####", "");

				// logger.error(dateFormat.format(date)+ " " + logType + msg);
				logger.error(dateFormat.format(date) + " " + logType + " : " + className + " : "
						+ element.getMethodName() + " - " + msg);
				// }
			} else if (logType.toLowerCase().contains("fail")) {
				// if(msg.contains(".png")){
				// Reporter.log(dateFormat.format(date)+" " + logType + msg +
				// "######<br/>");
				Reporter.log(dateFormat.format(date) + " " + logType + " $$$$$ " + className + " : "
						+ element.getMethodName() + " $$$$$ "
						+ msg /* + "<br/>" */);
				msg = msg.replace("$$$$$", "").replace("#####", "");

				// logger.error(dateFormat.format(date)+ " " + logType + msg);
				logger.error(dateFormat.format(date) + " " + logType + " : " + className + " : "
						+ element.getMethodName() + " - " + msg);
				// }
			} else if (logType.toLowerCase().contains("passwithss")) {
				Reporter.log(dateFormat.format(date) + " " + logType + " $$$$$ " + className + " : "
						+ element.getMethodName() + " $$$$$ " + msg + " $$$$$ " + ssFilePath /* + "<br/>" */);
				logger.info(dateFormat.format(date) + " " + logType + " : " + className + " : "
						+ element.getMethodName() + " - " + msg);
			} else if (logType.toLowerCase().contains("pass") || logType.toLowerCase().contains("done")) {
				Reporter.log(dateFormat.format(date) + " " + logType + " $$$$$ " + className + " : "
						+ element.getMethodName() + " $$$$$ "
						+ msg /* + " $$$$$ " + screenshot + "<br/>" */);
				logger.info(dateFormat.format(date) + " " + logType + " : " + className + " : "
						+ element.getMethodName() + " - " + msg);
			} else if ("info".equalsIgnoreCase(logType)) {
				logger.info(dateFormat.format(date) + " " + logType + " : " + className + " : "
						+ element.getMethodName() + " - " + msg);
			} else if ("error".equalsIgnoreCase(logType)) {
				logger.error(dateFormat.format(date) + " " + logType + " : " + className + " : "
						+ element.getMethodName() + " - " + msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage(), e);
		}
	}

	/**
	 * Gets the classname.
	 *
	 * @param className
	 *            the class name
	 * @return the classname
	 */
	public static String getClassname(String className) {
		int counter = 0;
		for (int i = className.length() - 1; i > 0; i--) {
			if (className.charAt(i) == '.') {
				break;
			}
			counter = counter + 1;
		}
		className = className.substring(className.length() - counter, className.length());
		return className;
	}

	public static void close() {
		driver.quit();
		COCDriverScript.logMessage("info", "Driver Quit");
	}

	/**
	 * 
	 * @param smallMessage
	 * @param detailedMessage
	 * @param logType
	 *            {@link Status}
	 */
	public static void logMessage(String smallMessage, String detailedMessage, Status logType) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM,yy HH:mm:ss");
		Date date = new Date();
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];
		String className = getClassname(element.getClassName());
		String screenShotPath = "";

		if (logType.equals(Status.FAIL)) {
			if (failedScreenShot)
				screenShotPath = takeExecutionScreenshot(driver);

			Reporter.log("##Dt## " + dateFormat.format(date) + " ##LgTyp## " + logType + " ##Class## " + className
					+ " ##Method## " + element.getMethodName() + " ##sMsg## " + smallMessage + " ##dMsg## "
					+ detailedMessage + " ##SS## " + screenShotPath + "##End##" + "<br/>");
			logger.error(dateFormat.format(date) + " Steps Failed on: Class Name: " + className + " Method: "
					+ element.getMethodName() + " :: " + smallMessage + " :- " + detailedMessage);

			// Assert.fail(detailedMessage);

		}

		else if (logType.equals(Status.PASS)) {
			if (passedScreenShot)
				screenShotPath = takeExecutionScreenshot(driver);

			Reporter.log("##Dt## " + dateFormat.format(date) + " ##LgTyp## " + logType + " ##Class## " + className
					+ " ##Method## " + element.getMethodName() + " ##sMsg## " + smallMessage + " ##dMsg## "
					+ detailedMessage + " ##SS## " + screenShotPath + "##End##" + "<br/>");
			logger.info(dateFormat.format(date) + " Steps Passed on: Class Name: " + className + " Method: "
					+ element.getMethodName() + " :: " + smallMessage + " :- " + detailedMessage);
		}

		else if (logType.equals(Status.DBFAIL)) {

			Reporter.log("##Dt## " + dateFormat.format(date) + " ##LgTyp## " + logType + " ##Class## " + className
					+ " ##Method## " + element.getMethodName() + " ##sMsg## " + smallMessage + " ##dMsg## "
					+ detailedMessage + " ##SS## " + screenShotPath + "##End##" + "<br/>");
			logger.error(dateFormat.format(date) + " Steps Failed on: Class Name: " + className + " Method: "
					+ element.getMethodName() + " :: " + smallMessage + " :- " + detailedMessage);

			// Assert.fail(detailedMessage);
		}

		else if (logType.equals(Status.DBPASS)) {

			Reporter.log("##Dt## " + dateFormat.format(date) + " ##LgTyp## " + logType + " ##Class## " + className
					+ " ##Method## " + element.getMethodName() + " ##sMsg## " + smallMessage + " ##dMsg## "
					+ detailedMessage + " ##SS## " + screenShotPath + "##End##" + "<br/>");
			logger.info(dateFormat.format(date) + " Steps Passed on: Class Name: " + className + " Method: "
					+ element.getMethodName() + " :: " + smallMessage + " :- " + detailedMessage);
		}

		else if (logType.equals(Status.DONE)) {
			Reporter.log("##Dt## " + dateFormat.format(date) + " ##LgTyp## " + logType + " ##Class## " + className
					+ " ##Method## " + element.getMethodName() + " ##sMsg## " + smallMessage + " ##dMsg## "
					+ detailedMessage + " ##SS## " + screenShotPath + "##End##" + "<br/>");
			logger.info(dateFormat.format(date) + " DONE:     " + smallMessage + " :- " + detailedMessage);
		}

		else if ((logType.equals(Status.INFO))) {
			Reporter.log("##Dt## " + dateFormat.format(date) + " ##LgTyp## " + logType + " ##Class## " + className
					+ " ##Method## " + element.getMethodName() + " ##sMsg## " + smallMessage + " ##dMsg## "
					+ detailedMessage + " ##SS## " + screenShotPath + "##End##" + "<br/>");
			logger.info(dateFormat.format(date) + " INFO:     " + smallMessage + " :- " + detailedMessage);
		}

		else if ((logType.equals(Status.ERROR))) {
			Reporter.log("##Dt## " + dateFormat.format(date) + " ##LgTyp## " + logType + " ##Class## " + className
					+ " ##Method## " + element.getMethodName() + " ##sMsg## " + smallMessage + " ##dMsg## "
					+ detailedMessage + " ##SS## " + screenShotPath + "##End##" + "<br/>");
			logger.error(dateFormat.format(date) + " Steps Error on: Class Name: " + className + " Method: "
					+ element.getMethodName() + " :: " + smallMessage + " :- " + detailedMessage);
		}

		else if ((logType.equals(Status.WARNING))) {
			Reporter.log("##Dt## " + dateFormat.format(date) + " ##LgTyp## " + logType + " ##Class## " + className
					+ " ##Method## " + element.getMethodName() + " ##sMsg## " + smallMessage + " ##dMsg## "
					+ detailedMessage + " ##SS## " + screenShotPath + "##End##" + "<br/>");
			logger.error(dateFormat.format(date) + " Steps WARNING on: Class Name: " + className + " Method: "
					+ element.getMethodName() + " :: " + smallMessage + " :- " + detailedMessage);
		}
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public static String takeExecutionScreenshot(WebDriver driver) {

		String reportFolderName = System.getProperty("ReportFolderName");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		Random rand = new Random();
		int random_No = rand.nextInt(99999) + 1;
		String fileName = (dateFormat.format(date)) + "" + random_No + ".png";

		File newScreenShot = new File("./report/" + reportFolderName + "/SS/" + fileName);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, newScreenShot);
		} catch (IOException e) {

		}

		return "SS/" + fileName;
	}

}