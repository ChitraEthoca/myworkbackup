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
public class AddPropertyPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public AddPropertyPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

// Add new property - Landlord Role
public ReviewPropertyPage srchProperty(Hashtable<String, String> testData) throws Exception {
	try{
		Thread.sleep(3000);
		checkElementExistence(driver,By.xpath("lblAddPrpty"),5);
		/*COC.webAdaptor(Actions.waitForObjectToLoad, "lnkBack");
		COC.webAdaptor(Actions.click, "lnkBack");
		checkElementExistence(driver,By.xpath("btnAddnewPropty"),4);
		COC.webAdaptor(Actions.click, "btnAddnewPropty");
		checkElementExistence(driver,By.xpath("lblAddPrpty"),1);

		COC.webAdaptor(Actions.waitForObjectToLoad, "lblStreetno");
		*/checkElementExistence(driver,By.xpath("inputStreetno"),2);
		COC.webAdaptor(Actions.setText, "inputStreetno", testData.get("StreetNo"));
		
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblStName");
		checkElementExistence(driver,By.xpath("inputStName"),2);
		COC.webAdaptor(Actions.setText, "inputStName", testData.get("StreetName"));
	
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblCity");
		checkElementExistence(driver,By.xpath("inputCity"),2);
		COC.webAdaptor(Actions.setText, "inputCity", testData.get("City"));
		Thread.sleep(3000);
		checkElementExistence(driver,By.xpath("btnSearch"),2);
		COC.webAdaptor(Actions.waitForObjectToLoad, "btnSearch");
		COC.webAdaptor(Actions.click, "btnSearch");
		Thread.sleep(3000);
		
			try{
				checkElementExistence(driver,By.xpath("btnFindUnit"),5);
				COC.webAdaptor(Actions.waitForObjectToLoad, "btnFindUnit");
				COC.webAdaptor(Actions.click, "btnFindUnit");
				checkElementExistence(driver,By.xpath("chkUnitall"),5);
				COC.webAdaptor(Actions.click, "chkUnitall");
			}catch(Exception e){
				checkElementExistence(driver,By.xpath("chkUnitall"),5);
				COC.webAdaptor(Actions.click, "chkUnitall");
			}
			Thread.sleep(3000);
			checkElementExistence(driver,By.xpath("lblPptyBillinginfo"),5);
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblNamethisbuild");
			COC.webAdaptor(Actions.setText, "inputPptName",testData.get("Name"));
			/*COC.webAdaptor(Actions.waitForObjectToLoad, "lblPPTNAME");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lbllimitchard");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblMasteraccntno");
			*/COC.webAdaptor(Actions.click, "dropdownChoose");
			checkElementExistence(driver,By.xpath("selectaccount"),2);
			COC.webAdaptor(Actions.click, "selectaccount");
			COC.webAdaptor(Actions.click, "btnNext");
			Thread.sleep(3000);
			
		/*File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Selected New Property");*/
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		System.out.println(e.getMessage());
		e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new ReviewPropertyPage(driver, localData);
}

// Add Property & Billing information Account - 

//Add new property - Landlord Role
public ManagePropertiesPage AddAccnt(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblAddPrpty"),5);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblStreetno");
		checkElementExistence(driver,By.xpath("inputStreetno"),3);
		COC.webAdaptor(Actions.setText, "inputStreetno", testData.get("StreetNo"));
		
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblStName");
		checkElementExistence(driver,By.xpath("inputStName"),3);
		COC.webAdaptor(Actions.setText, "inputStName", testData.get("StreetName"));
	
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblCity");
		checkElementExistence(driver,By.xpath("inputCity"),3);
		COC.webAdaptor(Actions.setText, "inputCity", testData.get("City"));
		
		checkElementExistence(driver,By.xpath("btnSearch"),5);
		COC.webAdaptor(Actions.click, "btnSearch");
		checkElementExistence(driver,By.xpath("lnkAddAccount"),5);
		COC.webAdaptor(Actions.click, "lnkAddAccount");
		checkElementExistence(driver,By.xpath("lblAddAccount"),3);
			
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblPptyBillinginfo");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblNamethisbuild");
		COC.webAdaptor(Actions.setText, "inputAccount",testData.get("AccountNo"));
		String Account = testData.get("AccountNo");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblAccntVerfication");
		COC.webAdaptor(Actions.click, "dropdown");
		String VerificationType = testData.get("VerficationType");
		if(VerificationType.equals("PhoneNumber")){
			COC.webAdaptor(Actions.click, "drodpwnchoosePhone");
			checkElementExistence(driver,By.xpath("inputPhonenum"),4);
			COC.webAdaptor(Actions.setText, "inputPhonenum",testData.get("PhoneNumber"));
		}else{
			COC.webAdaptor(Actions.click, "drodpwnchooseMeter");
			checkElementExistence(driver,By.xpath("inputMeter"),4);
			COC.webAdaptor(Actions.setText, "inputMeter",testData.get("MeterID"));
			}
			
		checkElementExistence(driver,By.xpath("btnNextAddAccnt"),4);
			COC.webAdaptor(Actions.click, "btnNextAddAccnt");
			checkElementExistence(driver,By.xpath("lblConfirmAccount"),4);
			COC.webAdaptor(Actions.click, "btnBack");
			checkElementExistence(driver,By.xpath("btnNextAddAccnt"),4);
			COC.webAdaptor(Actions.click, "btnNextAddAccnt");
			checkElementExistence(driver,By.xpath("lblConfirmAccount"),4);
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblAccountno");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblAccountnoValue");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblServiceadd");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblServiceaddvalue");
			COC.webAdaptor(Actions.click, "btnADD");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			
			COCDriverScript.logMessage("testStepPass", "Successfully Added New Account" + "Account Number =" +Account);
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		System.out.println(e.getMessage());
		e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new ManagePropertiesPage(driver, localData);
}

}