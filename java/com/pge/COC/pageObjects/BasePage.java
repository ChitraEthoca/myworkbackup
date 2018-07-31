package com.pge.COC.pageObjects;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.sun.org.apache.bcel.internal.generic.Select;
public class BasePage {
public boolean waitForPageLoad(WebDriver driver){
		return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	}
public void waitForText(WebDriver driver, String searchText, int waitTime, int scroll){
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
			Map<Object, Object> params = new HashMap<>();
			params.put("content", searchText);    // The text we're looking for
			params.put("dpi", "300");           // Optional DPI parameter
			params.put("scrolling", "scroll");  // Add the scroll and search
			params.put("next", "SWIPE_UP");     // Next is mandatory if using scroll and search
			// Can also use customized swipe like:
			// "SWIPE=(50%,80%),(50%,60%);WAIT=1000"
			params.put("maxscroll", scroll);       // Not mandatory, default is 5
			params.put("threshold", "95");     // Adding threshold
			((RemoteWebDriver) driver).executeScript(findCommand, params); // Calling the script
		}
	}
public void pressEnter() throws AWTException{
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
	}
public void waitForElementVisible(WebDriver driver, By by, int waitTime, int scroll){
		if ((driver instanceof FirefoxDriver)|(driver instanceof InternetExplorerDriver)|(driver instanceof ChromeDriver)){
			WebDriverWait wait = new WebDriverWait(driver, waitTime);   
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}	catch (Exception e){
				Reporter.log("Element was not visible in "+waitTime+" sec");
				throw e;
			}
		}
	}			
public void waitForElementClickable(WebDriver driver, By by, int waitTime, int scroll){
		if ((driver instanceof FirefoxDriver)|(driver instanceof InternetExplorerDriver)|(driver instanceof ChromeDriver)){
			WebDriverWait wait = new WebDriverWait(driver, waitTime);   
			try{
				wait.until(ExpectedConditions.elementToBeClickable(by));
			}	catch (Exception e){
				Reporter.log("Element was not clickable in "+waitTime+" sec");
				throw e;
			}
		}
	}
public void scrollAndFindTextMobile(WebDriver driver, String searchText){
		String findCommand = "mobile:text:find"; // The proprietary function call
		Map<Object, Object> params = new HashMap<>();
		params.put("content", searchText);    // The text we're looking for
		params.put("dpi", "300");           // Optional DPI parameter
		params.put("scrolling", "scroll");  // Add the scroll and search
		params.put("next", "SWIPE_UP");     // Next is mandatory if using scroll and search
		// Can also use customized swipe like:
		// "SWIPE=(50%,80%),(50%,60%);WAIT=1000"
		params.put("maxscroll", "5");       // Not mandatory, default is 5
		params.put("threshold", "95");     // Adding threshold
		((RemoteWebDriver) driver).executeScript(findCommand, params); // Calling the script
	}
public boolean checkElementExistence(WebDriver driver, By xpath, long waitTime){
		boolean ret = false;
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		try {
			driver.findElement(xpath);
			ret = true;
		} catch (NoSuchElementException e) {
			ret = false;
		}
		return ret;
	}

}