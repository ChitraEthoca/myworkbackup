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
public class ReviewAddStaffPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public ReviewAddStaffPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Review Staff
public ConfirmAddStaffPage reviewAddstaff(Hashtable<String, String> testData) throws Exception {
		try{
			
			checkElementExistence(driver,By.xpath("lblPMP"),5);
			/*COC.webAdaptor(Actions.waitForObjectToLoad, "lblPMP");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblAddStaff");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblConfirmation");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblReviewnote");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblStaffinformation");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblName");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblNameValue");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblStaffEmail");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblStaffEmailValue");
			
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblUserName");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lblUserNameValue");
			COC.webAdaptor(Actions.waitForObjectToLoad, "lnkBack");
			COC.webAdaptor(Actions.click, "lnkBack");
			checkElementExistence(driver,By.xpath("btnNext"),5);
	*/		/*COC.webAdaptor(Actions.waitForObjectToLoad, "btnNext");
			COC.webAdaptor(Actions.click, "btnNext");*/
			checkElementExistence(driver,By.xpath("btnSave"),3);
			COC.webAdaptor(Actions.waitForObjectToLoad, "btnSave");
			COC.webAdaptor(Actions.click, "btnSave");
			Thread.sleep(3000);
		/*	
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			COCDriverScript.logMessage("testStepPass", "Successfully clicked on Signout link");
		*/  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "Failed to click on Signout link");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			
		 }
		return new ConfirmAddStaffPage(driver, localData);
}


}
