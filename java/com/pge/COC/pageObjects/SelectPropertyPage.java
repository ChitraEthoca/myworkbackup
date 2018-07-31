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
public class SelectPropertyPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public SelectPropertyPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}


// Add new property - Landlord Role
public SelectPropertyPage srchProperty(Hashtable<String, String> testData) throws Exception {
	try{
		checkElementExistence(driver,By.xpath("lnkBack"),5);
		COC.webAdaptor(Actions.waitForObjectToLoad, "lnkBack");
		COC.webAdaptor(Actions.waitForText, "lnkBack");
		COC.webAdaptor(Actions.click, "lnkBack");
		checkElementExistence(driver,By.xpath("btnAddnewPropty"),5);
		COC.webAdaptor(Actions.click, "btnAddnewPropty");
		COC.webAdaptor(Actions.waitForObjectToLoad, "lblStreetno");
		COC.webAdaptor(Actions.waitForText, "lblStreetno");
		checkElementExistence(driver,By.xpath("inputStreetno"),3);
		COC.webAdaptor(Actions.setText, "inputStreetno", testData.get("StreetNo"));
		
		COC.webAdaptor(Actions.waitForText, "lblStName");
		checkElementExistence(driver,By.xpath("inputStName"),3);
		COC.webAdaptor(Actions.setText, "inputStName", testData.get("StreetName"));
		
		COC.webAdaptor(Actions.waitForText, "lblZip");
		checkElementExistence(driver,By.xpath("inputZip"),3);
		COC.webAdaptor(Actions.setText, "inputZip", testData.get("Zip"));
		
		COC.webAdaptor(Actions.waitForText, "lblCity");
		checkElementExistence(driver,By.xpath("inputCity"),3);
		COC.webAdaptor(Actions.setText, "inputCity", testData.get("City"));
		
		checkElementExistence(driver,By.xpath("btnSearch"),3);
		COC.webAdaptor(Actions.click, "btnSearch");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Successfully clicked on Search button");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		System.out.println(e.getMessage());
		e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new SelectPropertyPage(driver, localData);
}




}