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
public class ForgotIDPWDPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public ForgotIDPWDPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//reset UserName
public PMPSignInPage resetID(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblForgotid"),5);
		
		COC.webAdaptor(Actions.waitForObjectToLoad, "noteForgotID");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblEmail");
		checkElementExistence(driver,By.xpath("inputEmail"),3);
		COC.webAdaptor(Actions.setText, "inputEmail",testData.get("Email"));
		COC.webAdaptor(Actions.waitForObjectToLoad, "btnSubmit");
		COC.webAdaptor(Actions.click, "btnSubmit");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		Thread.sleep(3000);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblUserNameReset");
		Thread.sleep(3000);
		COCDriverScript.logMessage("testStepPass", "Successfully Reset the UserName");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Failed to reset User Name");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	  }
	return new PMPSignInPage(driver, localData);
}

//Reset Password
public PMPSignInPage resetpwd(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblForgotPassword"),3);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblStep12");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblEnteryourName");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblUserName");
		COC.webAdaptor(Actions.waitForObjectToLoad, "inputUserName");
		COC.webAdaptor(Actions.setText, "inputUserName",testData.get("UserName"));
		COC.webAdaptor(Actions.waitForObjectToLoad, "btnSubmitID");
		COC.webAdaptor(Actions.click, "btnSubmitID");
		checkElementExistence(driver,By.xpath("lblAnswer"),3);
		COC.webAdaptor(Actions.setText, "inputAnswer",testData.get("SecurityQuestion"));
		COC.webAdaptor(Actions.waitForObjectToLoad, "btnSubmitfinal");
		checkElementExistence(driver,By.xpath("btnSubmitfinal"),3);
		COC.webAdaptor(Actions.click, "btnSubmitfinal");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		checkElementExistence(driver,By.xpath("lblConfirmPassword"),3);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblConfirmPassword");
		COCDriverScript.logMessage("testStepPass", "Successfully Reset the UserName");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Failed to reset User Name");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	  }
	return new PMPSignInPage(driver, localData);
}


}