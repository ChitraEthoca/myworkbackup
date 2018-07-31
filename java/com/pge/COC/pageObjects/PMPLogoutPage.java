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
public class PMPLogoutPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public PMPLogoutPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}
// Landlord Registration
public PMPLogoutPage UILogoutPage(Hashtable<String, String> testData) throws Exception {
		try{
			//UI Validation on Landlord Registration Page
			checkElementExistence(driver,By.xpath("eleLogout"),4);
			COC.webAdaptor(Actions.waitForObjectToLoad, "eleYouhaveloggedout");
			COC.webAdaptor(Actions.waitForObjectToLoad, "imgPGELogo");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
			COCDriverScript.logMessage("testStepPass", "Logout Page displayed as expected");
		  }catch(Exception e){
			COCDriverScript.logMessage("testStepFail", "Logout Page NOT displayed as expected");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		 }
		return new PMPLogoutPage(driver, localData);
 }

}
