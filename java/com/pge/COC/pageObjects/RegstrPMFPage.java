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
public class RegstrPMFPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public RegstrPMFPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}
// Landlord Registration
public ManagePropertiesPage pmfRegister(Hashtable<String, String> testData) throws Exception {
		try{
			//UI Validation on Landlord Registration Page
			checkElementExistence(driver,By.xpath("eleUserInformation"),4);
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleNameofCompany");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputUserName");
			checkElementExistence(driver,By.xpath("inputUserName"),2);
			COC.webAdaptor(Actions.setText,"inputUserName",testData.get("Name"));
			checkElementExistence(driver,By.xpath("eleTaxID"),3);
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleTaxID");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputTaxid");
			checkElementExistence(driver,By.xpath("inputTaxid"),1);
			COC.webAdaptor(Actions.setText,"inputTaxid",testData.get("TAXID"));
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleAddress");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputAddress");
			checkElementExistence(driver,By.xpath("inputAddress"),3);
			COC.webAdaptor(Actions.setText,"inputAddress",testData.get("Address"));
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleAddress2");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleCity");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputCity");
			checkElementExistence(driver,By.xpath("inputCity"),1);
			COC.webAdaptor(Actions.setText,"inputCity",testData.get("City"));
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "drpdownState");
			checkElementExistence(driver,By.xpath("drpdownState"),1);
			COC.webAdaptor(Actions.click, "drpdownState");
			checkElementExistence(driver,By.xpath("selectCA"),5);
			COC.webAdaptor(Actions.click, "selectCA");
			checkElementExistence(driver,By.xpath("eleZipCode"),2);
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleZipCode");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputZIPCode");
			checkElementExistence(driver,By.xpath("inputZIPCode"),1);
			COC.webAdaptor(Actions.setText, "inputZIPCode", testData.get("Zip"));
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "elePhone");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputPhone");
			checkElementExistence(driver,By.xpath("inputPhone"),2);
			COC.webAdaptor(Actions.setText, "inputPhone",testData.get("PhoneNum"));
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleEmail");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputEmail");
			checkElementExistence(driver,By.xpath("inputEmail"),3);
			COC.webAdaptor(Actions.setText, "inputEmail",testData.get("Email"));
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleConfirmEmail");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputConfirmEmail");
			checkElementExistence(driver,By.xpath("inputConfirmEmail"),1);
			COC.webAdaptor(Actions.setText, "inputConfirmEmail",testData.get("Email"));
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleCreateUserName");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputCreateUserName");
			checkElementExistence(driver,By.xpath("inputCreateUserName"),3);
			COC.webAdaptor(Actions.setText, "inputCreateUserName",testData.get("UserName"));
			COC.webAdaptor(Actions.waitForObjectToLoad, "elecharecters");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "elePassword");
			checkElementExistence(driver,By.xpath("inputPassword"),3);
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputPassword");
			checkElementExistence(driver,By.xpath("inputPassword"),3);
			COC.webAdaptor(Actions.setText, "inputPassword",testData.get("Password"));
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleCasesensitive");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleConfirmPassword");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputConfirmPassword");
			checkElementExistence(driver,By.xpath("inputConfirmPassword"),3);
			COC.webAdaptor(Actions.setText, "inputConfirmPassword",testData.get("Password"));
			checkElementExistence(driver,By.xpath("elePasswordquestion"),3);
			COC.webAdaptor(Actions.waitForObjectToLoad, "elePasswordquestion");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lstPasswordquestion");
			COC.webAdaptor(Actions.click, "drpdownquestions");
			COC.webAdaptor(Actions.waitForObjectToLoad, "selectQuestion");
			checkElementExistence(driver,By.xpath("selectQuestion"),3);
			COC.webAdaptor(Actions.click, "selectQuestion");
			checkElementExistence(driver,By.xpath("eleYouranswer"),3);
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleYouranswer");
			COC.webAdaptor(Actions.waitForObjectToLoad, "inputAnwser");
			checkElementExistence(driver,By.xpath("inputAnwser"),3);
			COC.webAdaptor(Actions.setText, "inputAnwser", testData.get("SecurityAnswer"));
			COC.webAdaptor(Actions.waitForObjectToLoad, "chkboxAgree");
			checkElementExistence(driver,By.xpath("chkboxAgree"),3);
			COC.webAdaptor(Actions.click, "chkboxAgree");
			//COC.webAdaptor(Actions.waitForObjectToLoad, "eleIagreeterms");
			//Validate Terms of Use Page navigation
			checkElementExistence(driver,By.xpath("btnNext"),5);
			COC.webAdaptor(Actions.click, "btnNext");
			Thread.sleep(3000);
		//	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));	
		//	COCDriverScript.logMessage("testStepPass", "Successfully Registered New Landlord");
		// */
		  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "Failed to Register New Landlord");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			
		 }
		return new ManagePropertiesPage(driver, localData);
 }

}
