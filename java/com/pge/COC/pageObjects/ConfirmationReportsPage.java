package com.pge.COC.pageObjects;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.actionInterpreter.COC.Actions;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.utilities.ObjectRead;
public class ConfirmationReportsPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public ConfirmationReportsPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Click on Reports Button
public ReportsPage Confirmation(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblThankyouReports"),4);
		String expConfirmation = testData.get("Confirmation");
		checkElementExistence(driver,By.xpath("lblConfirmationNote"),4);
		COCDriverScript.logMessage("testStepPass", "Report Request Confirmation Page displayed");
		/*String actConfirmation = driver.findElement(By.xpath("lblConfirmationNote")).getText();
		if(expConfirmation.equals(actConfirmation)){
			COCDriverScript.logMessage("testStepPass", "Report Request Confirmation Page displayed");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		} else{
			COCDriverScript.logMessage("testStepFail", "Report Request Confirmation Page NOT displayed");
		}*/
		checkElementExistence(driver,By.xpath("lnkReports"),4);
		COC.webAdaptor(Actions.click, "lnkReports");
		
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new ReportsPage(driver, localData);
}


}