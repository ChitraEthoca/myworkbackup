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
public class ConfirmAddStaffPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public ConfirmAddStaffPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Confirmation - Added new Staff
public ManagePropertiesPage confirmAddstaff(Hashtable<String, String> testData) throws Exception {
		try{
			checkElementExistence(driver,By.xpath("lblblManageStafflPMP"),3);
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblConfirmationaddedStaff");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblNoproperties");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblNoteManage");
			COC.webAdaptor(Actions.waitForObjectToLoad, "btnAddstaff");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblStaff");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "srchStaff");
			COC.webAdaptor(Actions.waitForObjectToLoad, "iconDeleteUser");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblLastNameFirstnme");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblEmail");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblAction");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblUserName");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblUserEmail");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lnkResetpassword");
			
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			COCDriverScript.logMessage("testStepPass", "Successfully clicked on Signout link");
		  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "Failed to click on Signout link");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			System.out.println(e.getMessage());
			e.printStackTrace();
		 }
		return new ManagePropertiesPage(driver, localData);
}


}
