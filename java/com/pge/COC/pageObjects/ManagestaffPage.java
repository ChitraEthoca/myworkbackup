package com.pge.COC.pageObjects;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.actionInterpreter.COC.Actions;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.utilities.ObjectRead;
public class ManagestaffPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public ManagestaffPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Assign Properties to Staff
public AssignstaffPage clickAssignStaff(Hashtable<String, String> testData) throws Exception {
	try{
		
		checkElementExistence(driver,By.xpath("lblManageStaff"),3);
		checkElementExistence(driver,By.xpath("lblNotes"),0);
		checkElementExistence(driver,By.xpath("btnStaff"),0);
		checkElementExistence(driver,By.xpath("lnkResetPwd"),0);
		checkElementExistence(driver,By.xpath("iconRemoveStaff"),0);
		
		COC.webAdaptor(Actions.click, "lnkAssignProperties");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Clicked on Assign Properties Link");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new AssignstaffPage(driver, localData);
}

}