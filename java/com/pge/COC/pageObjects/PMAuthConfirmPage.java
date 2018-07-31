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
public class PMAuthConfirmPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public PMAuthConfirmPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Create PMA - Property Management Access
public ManagePropertiesPage PMAConfirm(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lblThankyou"),3);
		checkElementExistence(driver,By.xpath("note"),0);
		checkElementExistence(driver,By.xpath("lblAssignedAccnt"),0);
		checkElementExistence(driver,By.xpath("lblAccnt"),0);
		checkElementExistence(driver,By.xpath("lblProperty"),0);
		checkElementExistence(driver,By.xpath("lblAddress"),0);
		checkElementExistence(driver,By.xpath("lblAccntValue"),0);
		checkElementExistence(driver,By.xpath("lblPropertyValue"),0);
		checkElementExistence(driver,By.xpath("lblAddressValue"),0);
		checkElementExistence(driver,By.xpath("lblIdoHereby"),0);
		checkElementExistence(driver,By.xpath("lblPMI"),0);
		checkElementExistence(driver,By.xpath("lblCompnyName"),0);
		checkElementExistence(driver,By.xpath("lblCompnyNameValue"),0);
		checkElementExistence(driver,By.xpath("lblAddressPpty"),0);
		checkElementExistence(driver,By.xpath("lblAddressPptyValue"),0);
		
		checkElementExistence(driver,By.xpath("lblPhonenum"),0);
		checkElementExistence(driver,By.xpath("lblPhonenumValue"),0);
		checkElementExistence(driver,By.xpath("lblTermsAggre"),0);
		checkElementExistence(driver,By.xpath("btnPrint"),0);
		checkElementExistence(driver,By.xpath("imgPDF"),0);
		
		COC.webAdaptor(Actions.click, "lnkReturnManageProperty");
		
	
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
	return new ManagePropertiesPage(driver, localData);
}

}