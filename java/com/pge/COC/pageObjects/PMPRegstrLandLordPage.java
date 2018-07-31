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
public class PMPRegstrLandLordPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public PMPRegstrLandLordPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}
// Landlord Registration
public ManagePropertiesPage LLRegister(Hashtable<String, String> testData) throws Exception {
		try{
			//UI Validation on Landlord Registration Page
			checkElementExistence(driver,By.xpath("imgPGELogo"),6);
		/*	COC.webAdaptor(Actions.waitForObjectToLoad, "eleRegistrationPMP");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleCreateonlineNote");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleUserNameinfo");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleNameorComp");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleAccountNo");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleNotesEnterAccnt");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleEmail");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleConfEmail");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleCreateUserName");
			COC.webAdaptor(Actions.waitForObjectToLoad, "ele5to20Chars");
			COC.webAdaptor(Actions.waitForObjectToLoad, "elePassword");
			COC.webAdaptor(Actions.waitForObjectToLoad, "elePasCaseSensitive");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleConfirmPasswrd");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleSecQuestions");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleyourAnswer");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleVerification");
			COC.webAdaptor(Actions.waitForObjectToLoad, "elePleaseprovideinput");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleIagreeTermsofuse");*/
			//Registration Functionality Validation
			//set the Name from datafile
			checkElementExistence(driver,By.xpath("inputName"),5);
			COC.webAdaptor(Actions.setText,"inputName",testData.get("Name"));
			//Set the Account number from datafile
			checkElementExistence(driver,By.xpath("inputAccountNo"),3);
			COC.webAdaptor(Actions.setText,"inputAccountNo",testData.get("AccountNo"));
			//Set the Email Id from datafile
			
			checkElementExistence(driver,By.xpath("inputEmail"),3);
			COC.webAdaptor(Actions.setText,"inputEmail",testData.get("Email"));
			//Set the Email Id from datafile
		 
			checkElementExistence(driver,By.xpath("inputConfEmail"),3);
			COC.webAdaptor(Actions.setText,"inputConfEmail",testData.get("Email"));
			//Set the User Name from Datafile
	 
			checkElementExistence(driver,By.xpath("inputUsername"),3);
			COC.webAdaptor(Actions.setText,"inputUsername",testData.get("UserName"));
			//Set the Password from Datafile
			 
			checkElementExistence(driver,By.xpath("inputPassword"),3);
			COC.webAdaptor(Actions.setText,"inputPassword",testData.get("Password"));
			//Set the reenter Password from Datafile
			checkElementExistence(driver,By.xpath("inputConfPassword"),3);
			COC.webAdaptor(Actions.setText,"inputConfPassword",testData.get("Password"));
			//Select the Security Question from the list based on Input file data
			
			checkElementExistence(driver,By.xpath("drpdwnSecQuest"),3);
			COC.webAdaptor(Actions.click, "drpdwnSecQuest");
			COC.webAdaptor(Actions.waitForObjectToLoad, "selectquestion");
			checkElementExistence(driver,By.xpath("selectquestion"),4);
			COC.webAdaptor(Actions.click, "selectquestion");
			
			//Set Security Question Answer from the data file
			checkElementExistence(driver,By.xpath("inputAnswer"),2);
			COC.webAdaptor(Actions.setText,"inputAnswer",testData.get("SecurityAnswer"));
			//Select Verification mode Phone or Meter id from list
			String verifimode = testData.get("Verification");
			
			checkElementExistence(driver,By.xpath("inputAnswer"),2);
			COC.webAdaptor(Actions.click, "drpdwnChoosetype");
			if (verifimode.equals("Phone Number")){
				COC.webAdaptor(Actions.waitForObjectToLoad, "selectPhone");
				checkElementExistence(driver,By.xpath("selectPhone"),2);
				COC.webAdaptor(Actions.click, "selectPhone");
				checkElementExistence(driver,By.xpath("inputPhone"),2);
				COC.webAdaptor(Actions.setText,"inputPhone",testData.get("PhoneNum"));
			} else {
				checkElementExistence(driver,By.xpath("selectMeter"),2);
				COC.webAdaptor(Actions.waitForObjectToLoad, "selectMeter");
				checkElementExistence(driver,By.xpath("selectMeter"),2);
				COC.webAdaptor(Actions.click, "selectMeter");
				COC.webAdaptor(Actions.waitForObjectToLoad, "inputMeterid");
				checkElementExistence(driver,By.xpath("inputMeterid"),2);
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
			checkElementExistence(driver,By.xpath("btnNext"),3);
			COC.webAdaptor(Actions.waitForObjectToLoad, "btnNext");
			COC.webAdaptor(Actions.click, "btnNext");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));	
			COCDriverScript.logMessage("testStepPass", "Successfully Registered New Landlord");
		 
		  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "Failed to Register New Landlord");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			
		 }
		return new ManagePropertiesPage(driver, localData);
 }
}
