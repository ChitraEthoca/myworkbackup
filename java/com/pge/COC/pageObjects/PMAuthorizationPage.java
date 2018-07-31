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
public class PMAuthorizationPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public PMAuthorizationPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Create PMA - Property Management Access
public PMAuthReviewPage pmauth(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblPMA"),3);
		checkElementExistence(driver,By.xpath("lblText"),0);
		checkElementExistence(driver,By.xpath("lblAccntNum"),0);
		checkElementExistence(driver,By.xpath("lblPropertyName"),0);
		checkElementExistence(driver,By.xpath("lblMainAddress"),0);
		checkElementExistence(driver,By.xpath("lblAccntNumValue"),0);
		checkElementExistence(driver,By.xpath("lblPropertyNameValue"),0);
		checkElementExistence(driver,By.xpath("lblMainAddressValue"),0);
		checkElementExistence(driver,By.xpath("lnkBack"),0);
		COC.webAdaptor(Actions.click, "btnNext");
		
		checkElementExistence(driver,By.xpath("listPMFName"),3);
		COC.webAdaptor(Actions.setText, "listPMFName", testData.get("PMFName"));
		checkElementExistence(driver,By.xpath("SelectPMF"),1);
		COC.webAdaptor(Actions.click, "SelectPMF");
		checkElementExistence(driver,By.xpath("lblAddr"),0);
		checkElementExistence(driver,By.xpath("lblPhone"),0);
		COC.webAdaptor(Actions.click, "btnPMANext");
		
		checkElementExistence(driver,By.xpath("lblAuthType"),3);
		checkElementExistence(driver,By.xpath("lblPMF"),0);
		checkElementExistence(driver,By.xpath("lblAuthTime"),0);
		
		String access = testData.get("AccessLevel");
		if (access.equals("Indefinite")) {
			COC.webAdaptor(Actions.click, "radioIndefinite");
		} else if(access.equals("Limited")) {
			COC.webAdaptor(Actions.click, "radioLimited");
			checkElementExistence(driver,By.xpath("btnCalendar"),1);
			COC.webAdaptor(Actions.click, "btnCalendar");
			checkElementExistence(driver,By.xpath("selectDate"),1);
			COC.webAdaptor(Actions.click, "selectDate");
			
		} else{
			COCDriverScript.logMessage("testStepFail", "Element not found" +access);
		}
		
		checkElementExistence(driver,By.xpath("lblAuthorize"),1);
		checkElementExistence(driver,By.xpath("chkRecievePayingPGEBills"),0);
		checkElementExistence(driver,By.xpath("chkRecieveAcessPGEBills"),0);
		checkElementExistence(driver,By.xpath("chkMakeChangestoPGE"),0);
		
		checkElementExistence(driver,By.xpath("btnNextButton"),1);
		COC.webAdaptor(Actions.click, "btnNextButton");
	
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
	return new PMAuthReviewPage(driver, localData);
}

//ECM Authorization
public PMAuthReviewPage ECMAuth(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblPMA"),3);
		checkElementExistence(driver,By.xpath("lblText"),0);
		checkElementExistence(driver,By.xpath("lblAccntNum"),0);
		checkElementExistence(driver,By.xpath("lblPropertyName"),0);
		checkElementExistence(driver,By.xpath("lblMainAddress"),0);
		checkElementExistence(driver,By.xpath("lblAccntNumValue"),0);
		checkElementExistence(driver,By.xpath("lblPropertyNameValue"),0);
		checkElementExistence(driver,By.xpath("lblMainAddressValue"),0);
		checkElementExistence(driver,By.xpath("lnkBack"),0);
		COC.webAdaptor(Actions.click, "btnNext");
		
		checkElementExistence(driver,By.xpath("listPMFName"),3);
		COC.webAdaptor(Actions.setText, "listPMFName", testData.get("ECMName"));
		checkElementExistence(driver,By.xpath("SelectPMF"),1);
		COC.webAdaptor(Actions.click, "SelectPMF");
		checkElementExistence(driver,By.xpath("lblAddr"),0);
		checkElementExistence(driver,By.xpath("lblPhone"),0);
		COC.webAdaptor(Actions.click, "btnPMANext");
		
		checkElementExistence(driver,By.xpath("lblAuthType"),3);
		checkElementExistence(driver,By.xpath("lblPMF"),0);
		checkElementExistence(driver,By.xpath("lblAuthTime"),0);
		
		String access = testData.get("AccessLevel");
		if (access.equals("Indefinite")) {
			COC.webAdaptor(Actions.click, "radioIndefinite");
		} else if(access.equals("Limited")) {
			COC.webAdaptor(Actions.click, "radioLimited");
			checkElementExistence(driver,By.xpath("btnCalendar"),1);
			COC.webAdaptor(Actions.click, "btnCalendar");
			checkElementExistence(driver,By.xpath("selectDate"),1);
			COC.webAdaptor(Actions.click, "selectDate");
			
		} else{
			COCDriverScript.logMessage("testStepFail", "Element not found" +access);
		}
		
		checkElementExistence(driver,By.xpath("lblAuthorize"),1);
		checkElementExistence(driver,By.xpath("chkRecievePayingPGEBills"),0);
		checkElementExistence(driver,By.xpath("chkRecieveAcessPGEBills"),0);
		checkElementExistence(driver,By.xpath("chkMakeChangestoPGE"),0);
		
		checkElementExistence(driver,By.xpath("btnNextButton"),1);
		COC.webAdaptor(Actions.click, "btnNextButton");
	
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
	return new PMAuthReviewPage(driver, localData);
}


}