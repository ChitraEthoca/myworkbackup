package com.pge.COC.ReUsable.utility;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class PageHandling {
	public boolean searchTextInPage(WebDriver driver, String searchText, int scroll){
		boolean ret = false;
		//Wait for the element to be displayed for 30 seconds
		if (!(driver instanceof FirefoxDriver) & !(driver instanceof InternetExplorerDriver) & !(driver instanceof ChromeDriver)){
			WebDriverWait wait = new WebDriverWait(driver, 30);   
			try{
				//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),"+searchText+")]")));
				//					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+searchText+"')]")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+searchText+"') /*||contains(text(),'"+searchText.toLowerCase()+"') ]*/")));

				//	if (driver.findElements(By.xpath("//*[contains(text(),"+searchText+")]")).size()>0) {
				Reporter.log(driver.getTitle() + " page loaded sucessfully");
				ret=true;
			}
			//				} else {
			catch (Exception e){
				Reporter.log(driver.getTitle() + " page did not load sucessfully in 30 sec");
				ret = false;
			}
		}
		else {
			RemoteWebDriver rwd = (RemoteWebDriver) driver;
			//			String deviceName = ((RemoteWebDriver) driver).getCapabilities().getCapability("deviceName").toString();
			//			MobileDriver mobDriver = new MobileDriver();
			//			IMobileWebDriver webDriver =mobDriver.getDevice(deviceName).getVisualDriver();
			//			webDriver.manageMobile().scrollingOptions().setScroll(true);
			//			webDriver.manageMobile().scrollingOptions().setNext("SWIPE_UP");
			Boolean isPresent=false;
			if (scroll>0){
				String findCommand = "mobile:text:find"; // The proprietary function call
				Map<String, String> params = new HashMap<>();
				params.put("content", searchText);    // The text we're looking for
				//			params.put("dpi", "300");           // Optional DPI parameter
				params.put("scrolling", "scroll");  // Add the scroll and search
				params.put("next", "SWIPE_UP");     // Next is mandatory if using scroll and search
				// Can also use customized swipe like:
				// "SWIPE=(50%,80%),(50%,60%);WAIT=1000"
				params.put("maxscroll", scroll+"");       // Not mandatory, default is 5
				params.put("threshold", "75");     // Adding threshold

				String temp = rwd.executeScript(findCommand, params).toString();

				if (temp.equals("true")){
					isPresent = true;
				}
			}
			else {
				switchToContext(rwd, "VISUAL");
				try{
					rwd.findElement(By.linkText(searchText));
					isPresent = true;
				} catch (Exception e){
					isPresent = false;
				}
				switchToContext(rwd, "WEBVIEW"); 
			}
			if (isPresent) {
				Reporter.log("Text:'"+ searchText + "' found in  page");
				ret = true;
			} else{
				Reporter.log("Text:'"+ searchText + "' not found in  page");
				ret = false;
			}
		}
		return ret;
	}
	private static void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}

	public boolean pageLoad(WebDriver driver, String searchText, int waitTime, int scroll){
		boolean ret = false;
		if (!(driver instanceof FirefoxDriver) & !(driver instanceof InternetExplorerDriver) & !(driver instanceof ChromeDriver)){
			WebDriverWait wait = new WebDriverWait(driver, waitTime);   
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+searchText+"')|contains(text(),'"+searchText.toLowerCase()+"')]")));
				Reporter.log(driver.getTitle() + " page loaded sucessfully");
				ret=true;
			}	catch (Exception e){
				Reporter.log(driver.getTitle() + " page did not load sucessfully in "+waitTime+" sec");
				ret = false;
			}
		}
		return ret;
	}

}





