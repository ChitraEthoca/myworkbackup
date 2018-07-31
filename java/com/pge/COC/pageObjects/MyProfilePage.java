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
public class MyProfilePage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public MyProfilePage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}
//My Profile Update - STAFF ROLE
public MyProfilePage myprofile(Hashtable<String, String> testData) throws Exception {
	try{
		
		checkElementExistence(driver,By.xpath("lnkback"),4);
		COC.webAdaptor(Actions.click, "lnkback");
		
		checkElementExistence(driver,By.xpath("lnkMyProfile"),4);
		COC.webAdaptor(Actions.click, "lnkMyProfile");
		
		checkElementExistence(driver,By.xpath("lblEdtProfile"),3);
		checkElementExistence(driver,By.xpath("lblName"),0);
		checkElementExistence(driver,By.xpath("lblNameValue"),0);
		
		checkElementExistence(driver,By.xpath("lblEmail"),0);
		checkElementExistence(driver,By.xpath("edtEmail"),0);
		checkElementExistence(driver,By.xpath("lblUserName"),0);
		checkElementExistence(driver,By.xpath("lblUserNameValue"),0);
		COC.webAdaptor(Actions.click, "chkbxChangePwd");
		checkElementExistence(driver,By.xpath("lblChangePassword"),0);
		
		checkElementExistence(driver,By.xpath("lblCurrentPassword"),0);
		checkElementExistence(driver,By.xpath("edtCurrentPwd"),0);
		COC.webAdaptor(Actions.setText, "edtCurrentPwd", testData.get("Password"));
		checkElementExistence(driver,By.xpath("lblChangePwd"),0);
		COC.webAdaptor(Actions.setText, "edtChangePwd", testData.get("NewPassword"));
		checkElementExistence(driver,By.xpath("lblConfChangePwd"),0);
		COC.webAdaptor(Actions.setText, "edtChangeConfPwd", testData.get("NewPassword"));
		
		
		checkElementExistence(driver,By.xpath("lblPasswordsecquestion"),0);
		COC.webAdaptor(Actions.click, "drpdwnSecquestion");
		checkElementExistence(driver,By.xpath("selectSecquestion"),0);
		COC.webAdaptor(Actions.click, "selectSecquestion");
		
		checkElementExistence(driver,By.xpath("lblyouranswer"),0);
		COC.webAdaptor(Actions.click, "btnSave");
		COCDriverScript.logMessage("testStepPass", "Submitted Staff Profile");
		
		checkElementExistence(driver,By.xpath("lblConfirmation"),4);
		COCDriverScript.logMessage("testStepPass", "Sucessfully Updated Staff Profile");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Updated Staff Profile");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new MyProfilePage(driver, localData);
}

//My Profile Update - PMF ROLE
public MyProfilePage myprofilePMF(Hashtable<String, String> testData) throws Exception {
	try{
		//Click on back link
		checkElementExistence(driver,By.xpath("lnkback"),4);
		COC.webAdaptor(Actions.click, "lnkback");
		//Click on Myprofile Header
		checkElementExistence(driver,By.xpath("lnkMyProfile"),4);
		COC.webAdaptor(Actions.click, "lnkMyProfile");
		//Validate MyProfile Page for PMF
		checkElementExistence(driver,By.xpath("lblEdtProfile"),3);
		checkElementExistence(driver,By.xpath("lblTaxid"),0);
		checkElementExistence(driver,By.xpath("lblTaxidValue"),0);
		checkElementExistence(driver,By.xpath("lblUserName"),0);
		checkElementExistence(driver,By.xpath("chkbxChangePwdPMF"),0);
		COC.webAdaptor(Actions.click, "chkbxChangePwdPMF");
		checkElementExistence(driver,By.xpath("lblCurrentPasswordPMF"),0);
		
		COC.webAdaptor(Actions.setText, "edtCurrentpwdPMF", testData.get("Password"));
		checkElementExistence(driver,By.xpath("lblPasswordPMF"),0);
		COC.webAdaptor(Actions.setText, "edtChangePwdPMF", testData.get("NewPassword"));
		checkElementExistence(driver,By.xpath("lblPMFpwdnote"),0);
		checkElementExistence(driver,By.xpath("lblConfPwdPMF"),0);
		COC.webAdaptor(Actions.setText, "edtConfPwdPMF", testData.get("NewPassword"));
		checkElementExistence(driver,By.xpath("btnSavePMF"),2);
		COC.webAdaptor(Actions.click, "btnSavePMF");
		COCDriverScript.logMessage("testStepPass", "Updated PMF Profile");
		
		checkElementExistence(driver,By.xpath("lblConfirmationPMF"),4);
		
		String expConfirmation = testData.get("Confirmation");
		String actConfirmation = driver.findElement(By.xpath("lblConfirmationPMF")).getText();
		if(expConfirmation.equals(actConfirmation)){
			COCDriverScript.logMessage("testStepPass", "Sucessfully Updated PMF Profile" + actConfirmation);
		} else{
			COCDriverScript.logMessage("testStepFail", "Confirmation Message not displayed as expected" + actConfirmation);
		}
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new MyProfilePage(driver, localData);
}

//My Profile Update - LandLord ROLE
public MyProfilePage myprofileLL(Hashtable<String, String> testData) throws Exception {
	try{
		// Click on back link
		checkElementExistence(driver,By.xpath("lnkback"),4);
		COC.webAdaptor(Actions.click, "lnkback");
		//Click on MyProfile header
		checkElementExistence(driver,By.xpath("lnkMyProfile"),4);
		COC.webAdaptor(Actions.click, "lnkMyProfile");
		//Validate MyProfile Page
		checkElementExistence(driver,By.xpath("lblEdtProfile"),3);
		checkElementExistence(driver,By.xpath("lblNameLL"),0);
		checkElementExistence(driver,By.xpath("lblNameLLValue"),0);
		
		checkElementExistence(driver,By.xpath("lblEmailLL"),0);
		checkElementExistence(driver,By.xpath("lblEmailLLValue"),0);
		
		String ExpUserName = testData.get("UserName");
		String ActUserName = driver.findElement(By.xpath("lblUserNameValueLL")).getText();
		if(ExpUserName.equals(ActUserName)){
			COCDriverScript.logMessage("testStepPass", "UserName Matched with Actual" +ActUserName);
		} else{
			COCDriverScript.logMessage("testStepFail", "UserName Not Matched with Actual" +ActUserName);
		}
			

		checkElementExistence(driver,By.xpath("chkbxChangePwdLL"),1);
		COC.webAdaptor(Actions.click, "chkbxChangePwdLL");
		checkElementExistence(driver,By.xpath("edtCurrentPwdLL"),2);
		COC.webAdaptor(Actions.setText, "edtCurrentPwdLL", testData.get("Password"));
		checkElementExistence(driver,By.xpath("edtNewPwdLL"),0);
		COC.webAdaptor(Actions.setText, "edtNewPwdLL", testData.get("NewPassword"));
		checkElementExistence(driver,By.xpath("edtConfpwdLL"),0);
		COC.webAdaptor(Actions.setText, "edtConfpwdLL", testData.get("NewPassword"));
		checkElementExistence(driver,By.xpath("lblPwdsecquestionLL"),0);
		checkElementExistence(driver,By.xpath("btnSaveLL"),2);
		COC.webAdaptor(Actions.click, "btnSaveLL");
		COCDriverScript.logMessage("testStepPass", "Submitted LandLord Profile");
		
		//Validate Confirmation Message
		checkElementExistence(driver,By.xpath("lblConfirmationLL"),4);
		String expConfirmation = testData.get("Confirmation");
		String actConfirmation = driver.findElement(By.xpath("lblConfirmationLL")).getText();
		if(expConfirmation.equals(actConfirmation)){
			COCDriverScript.logMessage("testStepPass", "Sucessfully Updated LandLord Profile" + actConfirmation);
		} else{
			COCDriverScript.logMessage("testStepFail", "Confirmation Message not displayed as expected" + actConfirmation);
		}
		
		//Capture ScreenShot
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new MyProfilePage(driver, localData);
}


}