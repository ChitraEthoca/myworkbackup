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
public class PMAuthReviewPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public PMAuthReviewPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Create PMA - Property Management Access
public PMAuthConfirmPage PMAReview(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblReviewAgg"),3);
		checkElementExistence(driver,By.xpath("lblProperties"),0);
		checkElementExistence(driver,By.xpath("lblAccntNo"),0);
		checkElementExistence(driver,By.xpath("lblPropertyname"),0);
		checkElementExistence(driver,By.xpath("lblAddress"),0);
		checkElementExistence(driver,By.xpath("lblAccntNoValue"),0);
		checkElementExistence(driver,By.xpath("lblPropertynameValue"),0);
		checkElementExistence(driver,By.xpath("lblAddressValue"),0);
		checkElementExistence(driver,By.xpath("btnEdtPMI"),0);
		checkElementExistence(driver,By.xpath("btnEdtAuthType"),0);
		//Edit  functionality
		COC.webAdaptor(Actions.click, "btnEdtPMI");
		checkElementExistence(driver,By.xpath("nextEdit1"),2);
		COC.webAdaptor(Actions.click, "nextEdit1");
		checkElementExistence(driver,By.xpath("nextEdit2"),2);
		COC.webAdaptor(Actions.click, "nextEdit2");
		checkElementExistence(driver,By.xpath("btnEdtAuthType"),2);
		COC.webAdaptor(Actions.click, "btnEdtAuthType");
		checkElementExistence(driver,By.xpath("nextEdit2"),2);
		COC.webAdaptor(Actions.click, "nextEdit2");
		checkElementExistence(driver,By.xpath("chkAggrement"),2);
		COC.webAdaptor(Actions.click, "chkAggrement");
		
		
		checkElementExistence(driver,By.xpath("edtName"),1);
		COC.webAdaptor(Actions.setText, "edtName", testData.get("Name"));
		checkElementExistence(driver,By.xpath("lblTitle"),1);
		COC.webAdaptor(Actions.setText, "lblTitle", testData.get("Title"));
		
		checkElementExistence(driver,By.xpath("btnCancel"),0);
		checkElementExistence(driver,By.xpath("btnCancel"),0);
		checkElementExistence(driver,By.xpath("btnSubmit"),0);
		COC.webAdaptor(Actions.click, "btnSubmit");
	
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
	return new PMAuthConfirmPage(driver, localData);
}

}