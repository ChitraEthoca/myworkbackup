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

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.actionInterpreter.COC.Actions;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.utilities.ObjectRead;

public class ErrorMessagesPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
	private String errAccntNo;
	private String errEmailid;
	private String errConfEmail;
	private String errUserName;
	private String errConfPassword;
	private String errSecqans;
	private String errSelection;
	private String errPassword;
	
public ErrorMessagesPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

// Landlord Registration
public ErrorMessagesPage LLErrorMessage(Hashtable<String, String> dataErrMsg) throws Exception {
		
		String errExpCompName = dataErrMsg.get("eRRCompany");
		String errExpAccnt = dataErrMsg.get("eRRAccount");
		String errExpEmail = dataErrMsg.get("eRREmail");
		String errExpConfEmail = dataErrMsg.get("eRRConfEmail");
		String errExpUserName = dataErrMsg.get("eRRUserName");
		String errExppassword = dataErrMsg.get("eRRPasswrd");
		String errExpConfPwd = dataErrMsg.get("eRRConfPasswrd");
		String errExpSecqest = dataErrMsg.get("eRRSecque");
		String errExpsecqanswr = dataErrMsg.get("errSecAnswr");
		String errExpVerific = dataErrMsg.get("eRRVerfic");
		
		// All cells blank -
		try{
			checkElementExistence(driver,By.xpath("btnNext"),5);
			COC.webAdaptor(Actions.click, "btnNext");
			WebElement errExpComName = driver.findElement(By.xpath(errExpCompName));
			if (errExpCompName.equals(errExpComName)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errExpComName);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errExpComName);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			// Validate Expected Account Error Message
			WebElement errActAccnt = driver.findElement(By.xpath(errAccntNo));
			if (errExpAccnt.equals(errActAccnt)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errActAccnt);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errActAccnt);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			//Validate Email Error Message
			WebElement errEmail = driver.findElement(By.xpath(errEmailid));
			if (errExpEmail.equals(errEmail)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errEmail);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errEmail);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			
			//Validate Confirmation Email Error Message
			WebElement errConEmail = driver.findElement(By.xpath(errConfEmail));
			if (errExpConfEmail.equals(errConEmail)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errConEmail);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errConEmail);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			//Validate Confirmation Email Error Message
			WebElement errActUName = driver.findElement(By.xpath(errUserName));
			if (errExpUserName.equals(errActUName)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errActUName);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errActUName);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			//Validate Password Error Message
			WebElement errActpwd = driver.findElement(By.xpath(errPassword));
			if (errExppassword.equals(errActpwd)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errActpwd);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errActpwd);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			//Validate Confirm Password Error Message
			WebElement errConpwd = driver.findElement(By.xpath(errConfPassword));
			if (errExpConfPwd.equals(errConpwd)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errConpwd);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errConpwd);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			
			//Validate Security Question Error Message
			WebElement errSecquest = driver.findElement(By.xpath(errExpSecqest));
			if (errExpSecqest.equals(errSecquest)){

				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errSecquest);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errSecquest);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			
			//Validate Security Answer Error Message
			WebElement errSecquans = driver.findElement(By.xpath(errSecqans));
			if (errExpsecqanswr.equals(errSecquans)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errSecquans);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errSecquans);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			
			//Validate Verification Error Message
			WebElement errSelect = driver.findElement(By.xpath(errSelection));
			if (errExpVerific.equals(errSelect)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +errSelect);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message Not displayed as expected" +errSelect);
			}
			COC.webAdaptor(Actions.implicitWait, "10");
			
		
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));	
			COCDriverScript.logMessage("testStepPass", "Successfully Registered New Landlord");
		 
		  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "element not found");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		 }
		return new ErrorMessagesPage(driver, localData);
  }


//Landlord Registration
public ErrorMessagesPage EnterInputs(Hashtable<String, String> testData) throws Exception {
		// Wrong Account No
		try{
			COC.webAdaptor(Actions.implicitWait, "10");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputName");
			COC.webAdaptor(Actions.setText,"inputName",testData.get("Name"));
			//Set the Account number from datafile
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputAccountNo");
			Thread.sleep(1000);
			COC.webAdaptor(Actions.setText,"inputAccountNo",testData.get("AccountNo"));
			//Set the Email Id from datafile
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputEmail");
			Thread.sleep(1000);
			COC.webAdaptor(Actions.setText,"inputEmail",testData.get("Email"));
			//Set the Email Id from datafile
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputConfEmail");
			Thread.sleep(1000);
			COC.webAdaptor(Actions.setText,"inputConfEmail",testData.get("Email"));
			//Set the User Name from Datafile
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputUsername");
			Thread.sleep(1000);
			COC.webAdaptor(Actions.setText,"inputUsername",testData.get("UserName"));
			//Set the Password from Datafile
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputPassword");
			Thread.sleep(1000);
			COC.webAdaptor(Actions.setText,"inputPassword",testData.get("Password"));
			//Set the reenter Password from Datafile
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputConfPassword");
			COC.webAdaptor(Actions.setText,"inputConfPassword",testData.get("Password"));
			//Select the Security Question from the list based on Input file data
			COC.webAdaptor(Actions.waitForObjectToLoad, "drpdwnSecQuest");
			Thread.sleep(1000);
			COC.webAdaptor(Actions.click, "drpdwnSecQuest");
			COC.webAdaptor(Actions.waitForObjectToLoad, "selectquestion");
			Thread.sleep(1000);
			COC.webAdaptor(Actions.click, "selectquestion");
			
			//Set Security Question Answer from the data file
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputAnswer");
			COC.webAdaptor(Actions.setText,"inputAnswer",testData.get("SecurityAnswer"));
			//Select Verification mode Phone or Meter id from list
			String verifimode = testData.get("Verification");
			COC.webAdaptor(Actions.waitForObjectToLoad, "drpdwnChoosetype");
			Thread.sleep(1000);
			COC.webAdaptor(Actions.click, "drpdwnChoosetype");
			if (verifimode.equals("Phone Number")){
				COC.webAdaptor(Actions.waitForObjectToLoad, "selectPhone");
				Thread.sleep(1000);
				COC.webAdaptor(Actions.click, "selectPhone");
				Thread.sleep(1000);
				COC.webAdaptor(Actions.waitForObjectToLoad, "inputPhone");
				COC.webAdaptor(Actions.setText,"inputPhone",testData.get("PhoneNum"));
			} else {
				Thread.sleep(1000);
				COC.webAdaptor(Actions.waitForObjectToLoad, "selectMeter");
				Thread.sleep(1000);
				COC.webAdaptor(Actions.click, "selectMeter");
				COC.webAdaptor(Actions.waitForObjectToLoad, "inputMeterid");
				Thread.sleep(1000);
				COC.webAdaptor(Actions.setText,"inputMeterid",testData.get("MeterID"));
			}
			//Click on Checkbox - Agree terms and Conditions
			COC.webAdaptor(Actions.waitForObjectToLoad, "chkboxAggree");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleIagreeTermsofuse");
			COC.webAdaptor(Actions.click, "chkboxAggree");
			//Validate Terms of Use Page navigation
			COC.webAdaptor(Actions.waitForObjectToLoad, "lnkTermsofUse");
			//COC.webAdaptor(Actions.click, "lnkTermsofUse");
			//Validate Terms of Use Page navigation
			Thread.sleep(1000);
			COC.webAdaptor(Actions.waitForObjectToLoad, "btnNext");
			Thread.sleep(1000);
			COC.webAdaptor(Actions.click, "btnNext");
			Thread.sleep(3000);
			
			String expActError = testData.get("AccountError");
			WebElement actualError = driver.findElement(By.xpath(errAccntNo));
			
			if(actualError.equals(expActError)){
				COCDriverScript.logMessage("testStepPass", "Error Message displayed as expected" +actualError);
			} else{
				COCDriverScript.logMessage("testStepFail", "Error Message not displayed as expected" +actualError);
			}
			
			
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));	
			COCDriverScript.logMessage("testStepPass", "Successfully Registered New Landlord");
		 
		  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "Failed to Register New Landlord");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		 }
		return new ErrorMessagesPage(driver, localData);
}


}
