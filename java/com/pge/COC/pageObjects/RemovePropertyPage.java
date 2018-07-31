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
public class RemovePropertyPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public RemovePropertyPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Click on Cancel ISA link
public ManagePropertiesPage removePropty(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblRemoveProperty"),5);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblRemoveProperty");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblReviewISA");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblAggreement");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lnkBack");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblTermsAggreement");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblPropertyBillngInfo");
		COC.webAdaptor(Actions.click, "btnCancel");
		checkElementExistence(driver,By.xpath("btnNo"),5);
		COC.webAdaptor(Actions.waitForObjectToLoad, "btnNo");
		COC.webAdaptor(Actions.click, "btnNo");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblPropertyBillngInfo");
		COC.webAdaptor(Actions.click, "btnCancel");
		checkElementExistence(driver,By.xpath("btnYes"),5);
		COC.webAdaptor(Actions.waitForObjectToLoad, "btnYes");
		COC.webAdaptor(Actions.click, "btnYes");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Cancel ISA Link");
	  }catch(Exception e){
		  COCDriverScript.logMessage("testStepFAIL", "FAILED to click on Cancel ISA Link");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new ManagePropertiesPage(driver, localData);
}

//Cancel PMA - ECM

//Click on Cancel ISA link
public ManagePropertiesPage reviewCancelECMPMA(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("btnCancelAuthorization"),3);
		COC.webAdaptor(Actions.click, "btnCancelAuthorization");
		
		checkElementExistence(driver,By.xpath("btNoPMA"),2);
		checkElementExistence(driver,By.xpath("lblCancelPMA"),0);
		COC.webAdaptor(Actions.click, "btNoPMA");
		
		checkElementExistence(driver,By.xpath("btnCancelAuthorization"),3);
		COC.webAdaptor(Actions.click, "btnCancelAuthorization");
		
		checkElementExistence(driver,By.xpath("btnYesPMA"),2);
		COC.webAdaptor(Actions.click, "btnYesPMA");
		
		checkElementExistence(driver,By.xpath("lblConfirmation"),2);
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Cancel ISA Link");
	  }catch(Exception e){
		  COCDriverScript.logMessage("testStepFAIL", "FAILED to click on Cancel ISA Link");
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new ManagePropertiesPage(driver, localData);
}


}