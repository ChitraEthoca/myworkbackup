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
public class RequestReportsPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public RequestReportsPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Click on Reports Button
public ConfirmationReportsPage Requestreport(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblRequestReports"),4);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblReportnotes");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblPropertyName");
		checkElementExistence(driver,By.xpath("edtProperty"),4);
		COC.webAdaptor(Actions.setText, "edtProperty",testData.get("PropertyName"));
		checkElementExistence(driver,By.xpath("SelectPropertyName"),4);
		COC.webAdaptor(Actions.click, "SelectPropertyName");
		//Below Steps are optional 
		COC.webAdaptor(Actions.waitForObjectToLoad, "iconSearchProperty");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblServiceStartdate");
		COC.webAdaptor(Actions.click, "btnDate");
		checkElementExistence(driver,By.xpath("btnSelectDate"),3);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblPGEServiceStatus");
		COC.webAdaptor(Actions.click, "drpdownPGEServiceStatus");
		checkElementExistence(driver,By.xpath("selectStart"),3);
		COC.webAdaptor(Actions.click, "selectStart");
		
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblBilledto");
		COC.webAdaptor(Actions.click, "drpdownBilledTo");
		checkElementExistence(driver,By.xpath("selectLL"),3);
		COC.webAdaptor(Actions.click, "selectLL");
		
		checkElementExistence(driver,By.xpath("btnSubmit"),3);
		COC.webAdaptor(Actions.click, "btnSubmit");
	
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully Requested Report");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new ConfirmationReportsPage(driver, localData);
}


}