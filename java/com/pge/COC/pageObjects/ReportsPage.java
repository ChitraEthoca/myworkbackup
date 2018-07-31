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
public class ReportsPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public ReportsPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Click on Reports Button
public RequestReportsPage clickRequestreport(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblReports"),3);
		COC.webAdaptor(Actions.click, "btnReports");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Report Page displayed, Clicked on Request report button");
	  }catch(Exception e){
		  COC.webAdaptor(Actions.click, "btnRequestReport");
		  COCDriverScript.logMessage("testStepPass", "Report Page displayed, Clicked on Request report button");
	 }
	return new RequestReportsPage(driver, localData);
}

// Validate Reports
//Click on Reports Button
public RequestReportsPage ViewReport(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblReportsNotes"),5);
		checkElementExistence(driver,By.xpath("lblReportsNotes"),1);
		checkElementExistence(driver,By.xpath("lblReportName"),1);
		checkElementExistence(driver,By.xpath("lblDateTime"),1);
		checkElementExistence(driver,By.xpath("lblExpirationTime"),1);
		checkElementExistence(driver,By.xpath("lblDateTimeValue"),1);
		checkElementExistence(driver,By.xpath("lblExpirationTimeValue"),1);
		COC.webAdaptor(Actions.click, "lnkReportdownload");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Report Page displayed");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new RequestReportsPage(driver, localData);
}


}