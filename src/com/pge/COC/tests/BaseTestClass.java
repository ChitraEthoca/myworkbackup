package com.pge.COC.tests;

import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.actionInterpreter.COC.Actions;
import com.automation.framework.core.BaseTest;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.core.Status;
import com.automation.framework.exceptions.WebAdaptorException;
import com.perfectomobile.selenium.util.EclipseConnector;
import com.pge.COC.ReUsable.utility.ExcelTestData;
import com.pge.COC.perfecto.PerfectoLabUtils;

public class BaseTestClass {
	protected static WebDriver driver;
	String url;
	String Exe_Medium;
	String OS;
	String deviceName;

	public void initializeDriver(Hashtable<String, String> data) throws Exception {
		String Browser;
		String mobileBrowser;
		if (COCDriverScript.globalParamMap.get("Exe_Medium").equals("")) {
			Exe_Medium = data.get("Exe_Medium");
			Browser = data.get("Browser");
			mobileBrowser = data.get("mobileBrowser");
			deviceName = data.get("deviceName");
			OS = data.get("OS");
		} else {
			Exe_Medium = COCDriverScript.globalParamMap.get("Exe_Medium");
			Browser = COCDriverScript.globalParamMap.get("browser");
			mobileBrowser = COCDriverScript.globalParamMap.get("mobileBrowser");
			deviceName = COCDriverScript.globalParamMap.get("deviceName");
			OS = COCDriverScript.globalParamMap.get("OS");
		}

		try {
			if (Exe_Medium.equals("Web")) {
				if (Browser.trim().toLowerCase().equals("firefox")) {
					System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
					driver = new FirefoxDriver();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				} else if (Browser.trim().toLowerCase().equals("ie")) {
					DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
					ieCapabilities.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					ieCapabilities.setJavascriptEnabled(true);
					System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");// driver
																								// path
																								// here
					driver = new InternetExplorerDriver(ieCapabilities);
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				} else if (Browser.trim().toLowerCase().equals("chrome")) {
					System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");// driver
																								// path
																								// here
																								// C:\\Rule24\\drivers\\
					String downloadFilepath = System.getProperty("user.dir") + "\\Downloads";
				/*	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadFilepath);
					chromePrefs.put("safebrowsing.enabled", false);
					ChromeOptions args = new ChromeOptions();
					args.addArguments("disable-extensions","safebrowsing-disable-extension-blacklist","safebrowsing-disable-download-protection");
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", chromePrefs);
					DesiredCapabilities cap = DesiredCapabilities.chrome();
					cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					cap.setCapability(ChromeOptions.CAPABILITY, options);
					cap.setCapability(ChromeOptions.CAPABILITY, args);
				
					driver = new ChromeDriver(cap);
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			        chromePrefs.put("profile.default_content_settings.popups", 0);
			        chromePrefs.put("download.default_directory", downloadFilepath);
			        chromePrefs.put("safebrowsing.enabled", "true"); 
			        ChromeOptions options = new ChromeOptions();
			        options.setExperimentalOption("prefs", chromePrefs);
			        DesiredCapabilities cap = DesiredCapabilities.chrome();
			        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			        cap.setCapability(ChromeOptions.CAPABILITY, options);
			        driver = new ChromeDriver(cap);*/
					
					//    String downloadFilepath = dir;
						HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				        chromePrefs.put("profile.default_content_settings.popups", 0);
				        chromePrefs.put("download.default_directory", downloadFilepath);
				        chromePrefs.put("safebrowsing.enabled", "true"); 
				        ChromeOptions options = new ChromeOptions();
				        options.setExperimentalOption("prefs", chromePrefs);
				        options.addArguments("disable-infobars");// Will disable message "Chrome is being controlled by.."
				        DesiredCapabilities cap = DesiredCapabilities.chrome();
				        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				        cap.setCapability(ChromeOptions.CAPABILITY, options);
				        driver = new ChromeDriver(cap);
					
					//driver = new ChromeDriver();
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				}
				// Code for other browsers go here

			} else if (Exe_Medium.equals("Mobile")) {
				DesiredCapabilities capabilities = new DesiredCapabilities(mobileBrowser, "", Platform.ANY);
				String host = COCDriverScript.globalParamMap.get("host");
				capabilities.setCapability("user", COCDriverScript.globalParamMap.get("userName"));
				capabilities.setCapability("password", COCDriverScript.globalParamMap.get("password"));
				capabilities.setCapability("deviceName", deviceName);

				PerfectoLabUtils.setExecutionIdCapability(capabilities, host);
				Thread.sleep(5000);
				setExecutionIdCapability(capabilities, host);

				driver = new RemoteWebDriver(new URL("http://" + host + "/nexperience/perfectomobile/wd/hub"),
						capabilities);
				Thread.sleep(5000);
			}

			// if (data.get("Exe_Medium").equalsIgnoreCase("WEB")) {
			// COCDriverScript.logMessage("testStepPass",
			// data.get("Browser") + " browser launched in " +
			// data.get("Exe_Medium") + " medium");
			// } else if (data.get("Exe_Medium").equalsIgnoreCase("MOBILE")) {
			// COCDriverScript.logMessage("testStepPass",
			// data.get("mobileBrowser") + " browser launched on "
			// + data.get("Browser") + " device in " + data.get("Exe_Medium") +
			// " medium");
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cleanUpDevice() throws InterruptedException {
		if (OS.equals("iOS")) {
			// This part of the code is not yet ready
			// Safari cleanup iOS devices

			// defining variables
			HashMap<String, Object> params = new HashMap<String, Object>();
			String osVer;
			// switchToContext("NATIVE_APP");
			// get os version:
			params.put("property", "osVersion");
			osVer = (String) ((RemoteWebDriver) driver).executeScript("mobile:handset:info", params);
			// Launch Settings Application on it's main page
			params.clear();
			params.put("name", "Settings");
			try {
				((RemoteWebDriver) driver).executeScript("mobile:application:close", params);
			} catch (Exception e) {
			}

			((RemoteWebDriver) driver).executeScript("mobile:application:open", params);
			sleep(1000);
			// Scroll to Top
			params.clear();
			params.put("location", "20%,1%");
			((RemoteWebDriver) driver).executeScript("mobile:touch:tap", params);
			// click Safari:
			params.clear();
			params.put("content", "Safari");
			params.put("scrolling", "scroll");
			params.put("levels.high", "100");
			params.put("next", "SWIPE=(20%,70%),(20%,30%);WAIT=2000");
			params.put("screen.width", "50%");
			params.put("screen.top", "0%");
			params.put("screen.left", "0%");
			params.put("screen.height", "100%");
			((RemoteWebDriver) driver).executeScript("mobile:text:select", params);
			// swipe to bottom:
			params.clear();
			params.put("start", "50%,90%");
			params.put("end", "50%,10%");
			for (int i = 0; i < 3; i++) {
				((RemoteWebDriver) driver).executeScript("mobile:touch:swipe", params);
			}
			// clear Cache
			params.clear();
			params.put("value", "//*[starts-with(text(),'Clear History')]");
			params.put("framework", "perfectoMobile");
			((RemoteWebDriver) driver).executeScript("mobile:application.element:click", params);
			params.put("value",
					"//*[(@class='UIAButton' or @class='UIATableCell') and starts-with(@label,'Clear') and @isvisible='true']");
			((RemoteWebDriver) driver).executeScript("mobile:application.element:click", params);
			// below version 8 need to clear also data:
			if (!osVer.startsWith("8.")) {
				try {
					params.put("value", "//*[starts-with(text(),'Clear Cookies')]");
					((RemoteWebDriver) driver).executeScript("mobile:application.element:click", params);
					params.put("value",
							"//*[(@class='UIAButton' or @class='UIATableCell') and starts-with(@label,'Clear') and @isvisible='true']");
					((RemoteWebDriver) driver).executeScript("mobile:application.element:click", params);
				} catch (Exception e) {
					// do nothing

				}
			}

		}
		// Code for other Android goes here
		if (OS.equals("Android")) {
			Map<String, Object> params1 = new HashMap<>();
			params1.put("name", "Chrome");
			((JavascriptExecutor) driver).executeScript("mobile:application:clean", params1);

			Map<String, Object> params2 = new HashMap<>();
			params2.put("automation", "chrome");
			((JavascriptExecutor) driver).executeScript("mobile:browser:open", params2);

			Map<String, Object> params3 = new HashMap<>();
			params3.put("label", "CONTINUE");
			((JavascriptExecutor) driver).executeScript("mobile:button-text:click", params3);

			Thread.sleep(2000);

			Map<String, Object> params4 = new HashMap<>();
			params4.put("label", "NO THANKS");
			((JavascriptExecutor) driver).executeScript("mobile:button-text:click", params4);

		}

	}

	public BaseTestClass() {
		/*
		 * Global_data = new ExcelTestData("DataSheets//Global_Params.xlsx",
		 * "Global_Params"); Global_data.rowNo=1; // getGlobalData();
		 */ try {
			BaseTest base = new BaseTest("");
			url = COCDriverScript.globalParamMap.get("Url");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object[][] init(String excelName, String sheetName) throws Exception {
		ExcelTestData testData;
		Object[][] obj = null;
		// Create and Excel Data object to pass to the page objects
		testData = new ExcelTestData(excelName, sheetName);
		int noOfExeRows = 0;
		int rowCount = testData.getRowCount();
		for (int i = 1; i <= rowCount; i++) {
			testData.setRowNo(i);
			if (testData.getCellValue("Execute").equals("Y")) {
				noOfExeRows++;
			} else {
				Reporter.log("Skipped iteration no:'" + testData.rowNo + "'");
			}
		}
		if (noOfExeRows == 0) {
			Reporter.log("No iterations are marked for execution");
		} else {

			obj = new Object[noOfExeRows][1];
			int j = 0;
			for (int i = 1; i <= rowCount; i++) {
				testData.setRowNo(i);

				if (testData.getCellValue("Execute").equals("Y")) {
					ExcelTestData temp = new ExcelTestData(excelName, sheetName);
					temp.rowNo = i;
					obj[j][0] = temp;
					j++;
				}
			}

			// for (int i = 0; i < noOfExeRows; i++) {
			// ExcelTestData temp=new ExcelTestData(excelName,sheetName);
			// temp.rowNo=i+1;
			// obj[i][0] = temp;
			// }
		}
		return obj;
	}

	@AfterMethod
	public void tearDown() {
		/*
		 * if (!(driver instanceof FirefoxDriver) & !(driver instanceof
		 * InternetExplorerDriver) & !(driver instanceof ChromeDriver) ){
		 * MobileDriver mobDriver = new MobileDriver(); IMobileDevice device
		 * =mobDriver.getDevice(deviceName); device.close(); }
		 */
		// driver.close();
		//driver.quit();
	}

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	private static void setExecutionIdCapability(DesiredCapabilities capabilities, String host)
			throws /* IOException */ Exception {
		try {
			EclipseConnector connector = new EclipseConnector();
			String eclipseHost = connector.getHost();
			if ((eclipseHost == null) || (eclipseHost.equalsIgnoreCase(host))) {
				String executionId = connector.getExecutionId();
				capabilities.setCapability(EclipseConnector.ECLIPSE_EXECUTION_ID, executionId);
			}
		} catch (/* IOException */ Exception e) {
			System.out.println("Error occured in setExecutionIdCapability method. Error : " + e);
		}
	}
	
	/*
	 * Launch Application
	 */

	public void launchApplication(String url) throws WebAdaptorException {
		driver.navigate().to(url);
		COCDriverScript.driver = driver;
		COC.webAdaptor(Actions.maximize);
		COCDriverScript.logMessage("Application Launch", "Launch Application: " + url,Status.PASS);

	}
	
	
	
	
	
	
}
