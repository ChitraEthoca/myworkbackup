package com.pge.COC.pageObjects;

import java.io.File;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.actionInterpreter.COC.Actions;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.core.Status;
import com.automation.framework.utilities.ObjectRead;

public class HomePage extends BasePage {
	WebDriver driver;
	HashMap<String, String> localData;
	ObjectRead objectRead;

	public HomePage(WebDriver driver, HashMap<String, String> localData) throws Exception {
		this.driver = driver;
		this.localData = localData;
		this.waitForPageLoad(driver);
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}
	/*
	 * Method name: ClickOnMenu Method Description: To click on desired Menu
	 * Item Creation Date:12/20/2017 
	 * Created by:Bikash Shaw
	 */

	public HomePage ClickOnMenu(String menuOption) throws Exception {
        COCDriverScript.logMessage("Method Start", "ClickOnMenu Method Starts", Status.INFO);
		try {
			COC.webAdaptor(Actions.setValueToXpathAndClick, "Menu_Click", menuOption);
			COC.webAdaptor(Actions.wait, "wait.low");
			COCDriverScript.logMessage("Click", "Clicked On Desired Menu: "+menuOption, Status.PASS);

		} catch (Exception e) {
			COCDriverScript.logMessage("Click", "Failed to Click On Desired Menu", Status.FAIL);
			throw new Exception("Failed to Click On Desired Menu");
		}

		 COCDriverScript.logMessage("Method End", "ClickOnMenu Method Ends", Status.INFO);
		return this;
	}

}
