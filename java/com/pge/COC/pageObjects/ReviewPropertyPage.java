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
public class ReviewPropertyPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public ReviewPropertyPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}


// Add new property - Landlord Role
public ManagePropertiesPage reviewProrty(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblPptyAddressvalue"),5);
		/*COC.webAdaptor(Actions.waitForObjectToLoad, "lblPptyAddressvalue");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblPptybillinfo");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblPptyName");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblPptyNameValue");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblPptyAddress");
		COC.webAdaptor(Actions.waitForText, "lblPptyAddressvalue");
	
		
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblMasterAccntno");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblMasterAccntnoValue");
		COC.webAdaptor(Actions.waitForText, "lblAcntBillgName");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblAcntBillgNameValue");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblAccntMailingadd");
	
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblAccntMailingaddvalue");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblUnit");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblAddress");


		COC.webAdaptor(Actions.waitForObjectToLoad, "lblTermsAgg");
		*/COC.webAdaptor(Actions.click, "chkTermsAgg");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblName");
		checkElementExistence(driver,By.xpath("inputName"),5);
		COC.webAdaptor(Actions.setText, "inputName",testData.get("Name"));
	
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblTitle");
		checkElementExistence(driver,By.xpath("inputTitle"),5);
		COC.webAdaptor(Actions.setText, "inputTitle", testData.get("Title"));
		
		/*COC.webAdaptor(Actions.waitForObjectToLoad, "lblEmail");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblEmailValue");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lnkBack");
	
	*/	
		checkElementExistence(driver,By.xpath("btnSubmit"),5);
		//COC.webAdaptor(Actions.waitForObjectToLoad, "btnSubmit");
		COC.webAdaptor(Actions.click, "btnSubmit");
		Thread.sleep(5000);
		
	/*	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Selected New Property");
	*/  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		System.out.println(e.getMessage());
		e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
	 }
	return new ManagePropertiesPage(driver, localData);
}



}