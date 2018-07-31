package com.pge.COC.pageObjects;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
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
public class PMPSignInPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;

public PMPSignInPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}
// Validate UI Functionality on SignIn Page
public PMPSignInPage SPUIValidation(Hashtable<String, String> testData) throws Exception {
		try{
			//UI Validation on PMP SingIn Page
			checkElementExistence(driver,By.xpath("elePMPheader"),5);
			COC.webAdaptor(Actions.waitForObjectToLoad, "elePMPheader");
			COC.webAdaptor(Actions.waitForObjectToLoad, "iconUserID");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputUserName");
			COC.webAdaptor(Actions.waitForObjectToLoad, "iconPwd");
			COC.webAdaptor(Actions.waitForObjectToLoad, "btnSubmit");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lnkForgotUserName");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lnkforgotPwd");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lnkRegLandlord");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lnkRegPMPF");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lnkFooterContactUs");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lnkFooterPrivacy");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleFooternote");
			COC.webAdaptor(Actions.waitForObjectToLoad, "imgTwitter");
			COC.webAdaptor(Actions.waitForObjectToLoad, "imgFacebook");
			COC.webAdaptor(Actions.waitForObjectToLoad, "imgcurrents");
			COC.webAdaptor(Actions.waitForObjectToLoad, "imgPGELogo");
			COCDriverScript.logMessage("testStepPass", "Successfully Validated SignIn Page UI Elements");
		  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "UI Element not found on SignInPage");
		 }
		return new PMPSignInPage(driver, localData);
 }
// Click on Register Land lord Page & Navigate to LL Registration Page
public PMPRegstrLandLordPage navLLRegister(Hashtable<String, String> testData) throws Exception {
			try{
				checkElementExistence(driver,By.xpath("lnkRegLandlord"),5);
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
				COC.webAdaptor(Actions.click, "lnkRegLandlord");
				Thread.sleep(3000);
				COCDriverScript.logMessage("testStepPass", "Successfully clicked on Register Land Lord Link");
			  }catch(Exception e){
				  COCDriverScript.logMessage("testStepFail", "Failed to click on Register Land Lord Link");
				  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				  FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));	
				  System.out.println(e.getMessage());
				  e.printStackTrace();
				 Assert.fail(e.getMessage(), e);
			  }
			return new PMPRegstrLandLordPage(driver, localData);
	 }	
//Click on Register Property Management firm Page & Navigate to LL Registration Page
public RegstrPMFPage navPMFRegister(Hashtable<String, String> testData) throws Exception {
			try{
				checkElementExistence(driver,By.xpath("lnkRegPMPF"),2);
		//		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
				COC.webAdaptor(Actions.click, "lnkRegPMPF");
				COCDriverScript.logMessage("testStepPass", "Successfully clicked on Register PMF Link");
			  }catch(Exception e){
				  COCDriverScript.logMessage("testStepFail", "Failed to click on Register PMF Link");
				  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				   FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));	
			 }
			return new RegstrPMFPage(driver, localData);
	 }	
//Login to PMF Page
public ManagePropertiesPage Login(Hashtable<String, String> testData) throws Exception {
			try{
				checkElementExistence(driver,By.xpath("inputUserName"),6);
				COC.webAdaptor(Actions.waitForObjectToLoad, "inputUserName");
				COC.webAdaptor(Actions.setText, "inputUserName", testData.get("UserName"));
				checkElementExistence(driver,By.xpath("inputPassword"),1);
				COC.webAdaptor(Actions.setText, "inputPassword", testData.get("Password"));
				COC.webAdaptor(Actions.click, "btnSubmit");
				COCDriverScript.logMessage("testStepPass", "Successfully clicked on SignIn button");
			  }catch(Exception e){
				  COCDriverScript.logMessage("testStepFail", "Failed to click on SignIn button link");
			 }
			return new ManagePropertiesPage(driver, localData);
	 }
//Validate Error Messages - Login Page
public ManagePropertiesPage LoginError(Hashtable<String, String> testData) throws Exception {
			try{
		       /*Validate Error Message for Blank ID & Password*/
				checkElementExistence(driver,By.xpath("btnSubmit"),5);
				COC.webAdaptor(Actions.click, "btnSubmit");
				checkElementExistence(driver,By.xpath("lbLErrorMsgNoPwd"),1);
				String errorexptd = testData.get("ErrorMesgNoPassword");
				String actualErrr = driver.findElement(By.xpath(".//*[@id='loginForm']/div[3]")).getText();
				if (errorexptd.equals(actualErrr)){
					COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" + actualErrr);
				} else{
					COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected");
				}
				Thread.sleep(3000);
				
		/*Validate Error Message for Blank Password*/		
				COC.webAdaptor(Actions.waitForObjectToLoad, "inputUserName");
				COC.webAdaptor(Actions.setText, "inputUserName", testData.get("UserName"));
				COC.webAdaptor(Actions.waitForObjectToLoad, "btnSubmit");
				COC.webAdaptor(Actions.click, "btnSubmit");
				Thread.sleep(1000);
				COC.webAdaptor(Actions.waitForObjectToLoad, "lbLErrorMsgNoPwd");
				String errorexpected = testData.get("ErrorMesgNoPassword");
				String actualError = driver.findElement(By.xpath(".//*[@id='loginForm']/div[3]")).getText();
				if (errorexpected.equals(actualError)){
					COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" + actualError);
				} else{
					COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected");
				}
				Thread.sleep(3000);
				
		/*Validate Error Message for Invalid Password*/		
				Thread.sleep(2000);
				COC.webAdaptor(Actions.waitForObjectToLoad, "inputPassword");
				COC.webAdaptor(Actions.setText, "inputPassword", testData.get("WrongPassword"));
				Thread.sleep(3000);
				COC.webAdaptor(Actions.waitForObjectToLoad, "btnSubmit");
				COC.webAdaptor(Actions.click, "btnSubmit");
				Thread.sleep(2000);
				COC.webAdaptor(Actions.waitForObjectToLoad, "lbLErrorMsgNoPwd");
				String errexpected = testData.get("ErrorMesgWrongPassword");
				String actlError = driver.findElement(By.xpath(".//*[@id='loginForm']/div[4]")).getText();
				if (errexpected.equals(actlError)){
					COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" + actlError);
				} else{
					COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected");
				}
			  }catch(Exception e){
				  COCDriverScript.logMessage("testStepFail", "Failed to click on SignIn button link");
				  System.out.println(e.getMessage());
				  e.printStackTrace();
			  }
			return new ManagePropertiesPage(driver, localData);
	 }	

//click Forgot ID / Passowrd
public ForgotIDPWDPage forgotId(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("iconUserID"),3);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lnkForgotUserName");
		COC.webAdaptor(Actions.click, "lnkForgotUserName");
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Forgot UserId link");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Forgot id UI Element not found on SignInPage");
	 }
	return new ForgotIDPWDPage(driver, localData);
}
//Click on forgot Password
public ForgotIDPWDPage forgotpwd(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lnkforgotPwd"),3);
		COC.webAdaptor(Actions.click, "lnkforgotPwd");
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Forgot Password link");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Password id UI Element not found on SignInPage");
	 }
	return new ForgotIDPWDPage(driver, localData);
}

//Click on Register Land lord Page & Navigate to LL Registration Page
public ErrorMessagesPage navLLReg(Hashtable<String, String> testData) throws Exception {
			try{
				checkElementExistence(driver,By.xpath("lnkRegLandlord"),5);
				COC.webAdaptor(Actions.click, "lnkRegLandlord");
				COCDriverScript.logMessage("testStepPass", "Clicked on Register LandLord Link");
			  }catch(Exception e){
				  COCDriverScript.logMessage("testStepFail", "Failed to click on Register Land Lord Link");
			  }
			return new ErrorMessagesPage(driver, localData);
	 }


}