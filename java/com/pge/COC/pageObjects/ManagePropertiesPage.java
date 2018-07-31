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
public class ManagePropertiesPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public ManagePropertiesPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}
//Click on Signout  link
public PMPLogoutPage Logout(Hashtable<String, String> testData) throws Exception {
		try{
			
			String ManageProperty = "Manage Properties";
			String User = testData.get("UserName");
			checkElementExistence(driver,By.xpath("lnkUserIcon"),5);
			String Mp= driver.findElement(By.xpath(".//*[@id='landlord-property-div']/div/h1")).getText();
			Assert.assertEquals(ManageProperty, Mp, "Manage Property Header displayed");
			checkElementExistence(driver,By.xpath("lnkUserIcon"),3);
			COC.webAdaptor(Actions.click, "lnkUserIcon");
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleUserName");
			checkElementExistence(driver,By.xpath("lnkSignOut"),2);
			COC.webAdaptor(Actions.click, "lnkSignOut");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			COCDriverScript.logMessage("testStepPass", "Successfully clicked on Signout link" + "Logged As =" +User);
		  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "Failed to click on Signout link");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		 }
		return new PMPLogoutPage(driver, localData);
 }
//ADD Staff
public ReviewAddStaffPage addstaff(Hashtable<String, String> testData) throws Exception {
		try{
			checkElementExistence(driver,By.xpath("lnkManagestaff"),6);
			COC.webAdaptor(Actions.click, "lnkManagestaff");
			try{
				checkElementExistence(driver,By.xpath("btnAddStaff"),5);
				COC.webAdaptor(Actions.click, "btnAddStaff");
			}catch (Exception e){
				COC.webAdaptor(Actions.click, "btnAddStaff1");
				//COC.webAdaptor(Actions.click, "btnAddStaff");
			}
			/*COC.webAdaptor(Actions.waitForObjectToLoad, "lnkBack");
			COC.webAdaptor(Actions.click, "lnkBack");
		
			try{
				COC.webAdaptor(Actions.waitForObjectToLoad, "lblManagestaff");
				COC.webAdaptor(Actions.waitForObjectToLoad, "btnAddStaff");
				COC.webAdaptor(Actions.click, "btnAddStaff");
				
				}catch (Exception e){
				 COC.webAdaptor(Actions.click, "btnAddStaff1");
				
				}*/
			/*COC.webAdaptor(Actions.waitForObjectToLoad, "lblPMPPortal");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblAdStaff");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblStepadd1");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblstepadd2");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblnotestaffmem");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblstaffinformation");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblFirstName");
	*/		checkElementExistence(driver,By.xpath("edtFirstName"),5);
			COC.webAdaptor(Actions.waitForObjectToLoad, "edtFirstName");
			checkElementExistence(driver,By.xpath("edtFirstName"),2);
			COC.webAdaptor(Actions.setText, "edtFirstName", testData.get("FirstName"));
	
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblLastName");
			checkElementExistence(driver,By.xpath("edtLastName"),2);
			COC.webAdaptor(Actions.setText, "edtLastName", testData.get("LastName"));
		
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblStaffEmail");
			checkElementExistence(driver,By.xpath("edtStaffEmail"),1);
			COC.webAdaptor(Actions.setText, "edtStaffEmail", testData.get("Email"));
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblbConfEmail");
			checkElementExistence(driver,By.xpath("edtConfEmail"),1);
			COC.webAdaptor(Actions.setText, "edtConfEmail", testData.get("Email"));
		
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblStaffuserName");
			checkElementExistence(driver,By.xpath("edtStaffUserName"),2);
			COC.webAdaptor(Actions.setText, "edtStaffUserName", testData.get("StaffUserName"));
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblUseridNote");
		
			COC.webAdaptor(Actions.waitForObjectToLoad, "btnNext");
			COC.webAdaptor(Actions.click, "btnNext");
			Thread.sleep(3000);
	/*		
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			COCDriverScript.logMessage("testStepPass", "Successfully clicked on Signout link");
	*/	  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "Failed to click on Signout link");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		 }
		return new ReviewAddStaffPage(driver, localData);
}

//Delete Staff
public ManagePropertiesPage deletestaff(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("iconDeletestaff"),3);
		COC.webAdaptor(Actions.click, "iconDeletestaff");
		checkElementExistence(driver,By.xpath("iconWarning"),3);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblWanttodelete");
		COC.webAdaptor(Actions.waitForObjectToLoad, "btnNo");
		COC.webAdaptor(Actions.waitForObjectToLoad, "btnYes");
		COC.webAdaptor(Actions.click, "btnYes");
		checkElementExistence(driver,By.xpath("lblnoStaffmem"),3);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lnkManageProperties");
		COC.webAdaptor(Actions.click, "lnkManageProperties");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Signout link");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Failed to click on Signout link");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		System.out.println(e.getMessage());
		e.printStackTrace();
		Assert.fail(e.getMessage(), e);
	 }
	return new ManagePropertiesPage(driver, localData);
}

// Navigate to Add new property - Landlord Role
public AddPropertyPage AddProperty(Hashtable<String, String> testData) throws Exception {
	try{
		Thread.sleep(3000);
		checkElementExistence(driver,By.xpath("btnAddAdditionalPprty"),6);
		COC.webAdaptor(Actions.click, "btnAddAdditionalPprty");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Add New Property Button");
	  }catch(Exception e){
		  Thread.sleep(3000);
		COC.webAdaptor(Actions.click, "btnAddnewProperty");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new AddPropertyPage(driver, localData);
}

//Confirmation - Successfully Added new Property
public ManagePropertiesPage Confirmation(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblSuccessAddedMesg"),6);
		/*COC.webAdaptor(Actions.waitForObjectToLoad, "iconYes");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully Added New Property");
	 */ }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		System.out.println(e.getMessage());
		e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new ManagePropertiesPage(driver, localData);
}

// Click on Remove Property link
public PropertyDetailsPage removeProperty(Hashtable<String, String> testData) throws Exception {
	try{
		//COC.webAdaptor(Actions.waitForObjectToLoad, "inputSearchPrpty");
		//COC.webAdaptor(Actions.waitForText, "inputSearchPrpty");
	/*	COC.webAdaptor(Actions.setText, "inputSearchPrpty", testData.get("PropertyName"));
		COC.webAdaptor(Actions.waitForObjectToLoad, "iconSearch");
		COC.webAdaptor(Actions.click, "iconSearch");*/
		checkElementExistence(driver,By.xpath("lnkProperty"),3);
		COC.webAdaptor(Actions.click, "lnkProperty");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Add New Property Button");
	  }catch(Exception e){
		COC.webAdaptor(Actions.click, "btnAddAdditionalPprty");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new PropertyDetailsPage(driver, localData);
}

//Confirmation - Successfully Removed Property
public ManagePropertiesPage Confirm(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblRemovedProperty"),3);
	
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully Removed Property");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		System.out.println(e.getMessage());
		e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
	 }
	return new ManagePropertiesPage(driver, localData);
}

//Create PMA - Property Management Access
public PMAuthorizationPage createPMA(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("drpdownAccess"),3);
		COC.webAdaptor(Actions.click, "drpdownAccess");
		checkElementExistence(driver,By.xpath("drpdownAccess"),2);
		COC.webAdaptor(Actions.click, "SelectPMF");
		
		checkElementExistence(driver,By.xpath("inputSearchPrpty"),1);
		COC.webAdaptor(Actions.setText, "inputSearchPrpty",testData.get("PropertyName"));
		COC.webAdaptor(Actions.click, "iconSearch");
		
		checkElementExistence(driver,By.xpath("chkProperty"),1);

		COC.webAdaptor(Actions.click, "chkProperty");
		WebElement Ele = driver.findElement(By.xpath("btnAuthorize"));
		if (Ele.isEnabled()){
			COC.webAdaptor(Actions.click, "btnAuthorize");
		} else
		{
			COCDriverScript.logMessage("testStepFail", "Element disabled" + Ele);
		}
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully Added New Property");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		System.out.println(e.getMessage());
		e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new PMAuthorizationPage(driver, localData);
}

//Create ECM = Energy management company
public PMAuthorizationPage createECM(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("drpdownAccess"),3);
		COC.webAdaptor(Actions.click, "drpdownAccess");
		checkElementExistence(driver,By.xpath("drpdownAccess"),2);
		COC.webAdaptor(Actions.click, "SelectPMF");
		
		// Input - Property Name
		checkElementExistence(driver,By.xpath("inputSearchPrpty"),1);
		COC.webAdaptor(Actions.setText, "inputSearchPrpty",testData.get("PropertyName"));
		COC.webAdaptor(Actions.click, "iconSearch");
		WebElement Property  = driver.findElement(By.xpath("chkProperty"));
		if (Property.isDisplayed()){
			COCDriverScript.logMessage("testStepFail", "Element Found" + Property);
		} else{
			COCDriverScript.logMessage("testStepPASS", "Element disabled" + Property);
		}
		
		checkElementExistence(driver,By.xpath("drpdownAccess"),3);
		COC.webAdaptor(Actions.click, "drpdownAccess");
		checkElementExistence(driver,By.xpath("drpdownAccess"),2);
		COC.webAdaptor(Actions.click, "SelectEMC");
		
		checkElementExistence(driver,By.xpath("chkProperty"),1);
		
		COC.webAdaptor(Actions.click, "chkProperty");
		WebElement Ele = driver.findElement(By.xpath("btnAuthorize"));
		if (Ele.isEnabled()){
			COC.webAdaptor(Actions.click, "btnAuthorize");
		} else
		{
			COCDriverScript.logMessage("testStepFail", "Element disabled" + Ele);
		}
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully Added New Property");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		System.out.println(e.getMessage());
		e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new PMAuthorizationPage(driver, localData);
}

//Cancel PMA - ECM
public PropertyDetailsPage cancelPMA(Hashtable<String, String> testData) throws Exception {
	try{
		
		checkElementExistence(driver,By.xpath("inputSearchPrpty"),1);
		COC.webAdaptor(Actions.setText, "inputSearchPrpty",testData.get("PropertyName"));
		COC.webAdaptor(Actions.click, "iconSearch");
		
		checkElementExistence(driver,By.xpath("lnkPropertyName"),2);
		COC.webAdaptor(Actions.click, "lnkPropertyName");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully Added New Property");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		System.out.println(e.getMessage());
		e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new PropertyDetailsPage(driver, localData);
}

//Click on Report link
public ReportsPage clickReport(Hashtable<String, String> testData) throws Exception {
	try{
		
		checkElementExistence(driver,By.xpath("lnkReport"),3);
		COC.webAdaptor(Actions.click, "lnkReport");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Clicked on Report link");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new ReportsPage(driver, localData);
}

//Click On MyProfile Page
public MyProfilePage clickMyProfile(Hashtable<String, String> testData) throws Exception {
	try{
		
		checkElementExistence(driver,By.xpath("lnkMyProfile"),3);
		COC.webAdaptor(Actions.click, "lnkMyProfile");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Clicked on My Profile link");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new MyProfilePage(driver, localData);
}

//Click On Manage Staff button
public ManagestaffPage clickManageStaff(Hashtable<String, String> testData) throws Exception {
	try{
		
		checkElementExistence(driver,By.xpath("lnkmanagStaff"),3);
		COC.webAdaptor(Actions.click, "lnkmanagStaff");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Clicked on Manage Staff");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new ManagestaffPage(driver, localData);
}

//Manage Staff Property
public PropertyDetailsPage ValdProprtyStaff(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lnkStaffProperty"),5);
		COC.webAdaptor(Actions.click, "lnkStaffProperty");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Manage Property Page displayed, Clicked on Property Name");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new PropertyDetailsPage(driver, localData);
}


}