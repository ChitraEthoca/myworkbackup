package com.pge.COC.pageObjects;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.actionInterpreter.COC.Actions;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.utilities.ObjectRead;
public class PropertyDetailsPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public PropertyDetailsPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Click on Cancel ISA link
public RemovePropertyPage cancelISA(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblPropertydetails"),4);
		
		COC.webAdaptor(Actions.waitForText, "lblPropertydetails");
		COC.webAdaptor(Actions.click, "lnkCancelISA");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Cancel ISA Link");
	  }catch(Exception e){
		  COCDriverScript.logMessage("testStepFAIL", "FAILED to click on Cancel ISA Link");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new RemovePropertyPage(driver, localData);
}

//Click on Cancel ECM PMA
public RemovePropertyPage clickCancelECMPMA(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lnkCancelEMCPMA"),3);
		COC.webAdaptor(Actions.click, "lnkCancelEMCPMA");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Cancel ISA Link");
	  }catch(Exception e){
		  COCDriverScript.logMessage("testStepFAIL", "FAILED to click on Cancel ISA Link");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new RemovePropertyPage(driver, localData);
}

//Click on Cancel PMF PMA
public RemovePropertyPage clickCancelPMFPMA(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lnkCancelPMFPMA"),3);
		COC.webAdaptor(Actions.click, "lnkCancelPMFPMA");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Cancel ISA Link");
	  }catch(Exception e){
		  COCDriverScript.logMessage("testStepFAIL", "FAILED to click on Cancel ISA Link");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new RemovePropertyPage(driver, localData);
}

//Property Details - Staff
public RemovePropertyPage StaffPropertyDetails(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblPropertydetailsStaff"),6);
		checkElementExistence(driver,By.xpath("lblPropertyName"),1);
		checkElementExistence(driver,By.xpath("lblPropertyNameValue"),1);
		checkElementExistence(driver,By.xpath("lblPropertyAddressValue"),1);
		checkElementExistence(driver,By.xpath("lblMasterAccount"),1);
		checkElementExistence(driver,By.xpath("lblMasterAccountValue"),1);
		checkElementExistence(driver,By.xpath("lblLandlord"),1);
		checkElementExistence(driver,By.xpath("lblLandlordValue"),1);
		COCDriverScript.logMessage("testStepPass", "Property details Page displayed");

		checkElementExistence(driver,By.xpath("lnkSST"),5);
		COC.webAdaptor(Actions.click, "lnkSST");
		
		// Validate Popup Page - SST
		Set<String> ParendWindow = driver.getWindowHandles();
		String subWindowHandler = null;
		
		Set<String> ChildWindow = driver.getWindowHandles();
		Iterator<String> iterator = ChildWindow.iterator();
		while (iterator.hasNext()){
			subWindowHandler = iterator.next();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.switchTo().window(subWindowHandler);
		String expctSST = "Start or Stop Service";
		checkElementExistence(driver,By.xpath(".//*[@id='contentHeader']/h1"),6);
		/*String actSST = driver.findElement(By.xpath(".//*[@id='contentHeader']/h1")).getText();
		if(expctSST.equals(actSST)){
			COCDriverScript.logMessage("testStepPass", "Strat Stop Page Displayed");
		} else{
			COCDriverScript.logMessage("testStepFail", "Strat Stop Page NOT Displayed");
		}		*/
	
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", " Clicked on Stop Services Link, SST Page displayed");
	  }catch(Exception e){
		  COCDriverScript.logMessage("testStepFAIL", "Element not found on Property details page, SST Page not displayed");
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		  Assert.fail(e.getMessage(), e);
		  Assert.fail("Error executing Test...TestCaseName: "+this.getClass().getSimpleName()+"...Iteration:'" + testData.get("Iteration") + "'");
	 }
	return new RemovePropertyPage(driver, localData);
}

}