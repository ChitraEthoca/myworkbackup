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
public class AssignstaffPage extends BasePage{
	WebDriver driver;
	HashMap<String,String> localData;
	ObjectRead objectRead;
public AssignstaffPage(WebDriver driver, HashMap<String,String> localData) throws Exception{
		this.driver=driver;
		this.localData=localData;
		this.waitForPageLoad(driver);		
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

//Assign Properties to Staff
public AssignstaffPage assignStaff(Hashtable<String, String> testData) throws Exception {
	try{
		
		checkElementExistence(driver,By.xpath("lnkBack"),3);
		COC.webAdaptor(Actions.click, "lnkBack");
		
		String UpdateProp = "Update properties";
		String updatepropvalue = driver.findElement(By.xpath("lnKAssignProperties")).getText();
		if(UpdateProp.equals(updatepropvalue)){
			checkElementExistence(driver,By.xpath("lnKAssignProperties"),4);
			COC.webAdaptor(Actions.click, "lnKAssignProperties");
			checkElementExistence(driver,By.xpath("chkboxSelect"),4);
			COC.webAdaptor(Actions.click, "chkboxSelect");
			checkElementExistence(driver,By.xpath("btnNext"),4);
			COC.webAdaptor(Actions.click, "btnNext");
			checkElementExistence(driver,By.xpath("btnSave"),4);
			COC.webAdaptor(Actions.click, "btnSave");
			COCDriverScript.logMessage("testStepPass", "Removed Properties from Staff Member");
		} else{
			COCDriverScript.logMessage("testStepPass", "Properties not assigned, Please assign Properties to Staff Member");
		}
		
		checkElementExistence(driver,By.xpath("lnKAssignProperties"),5);
		COC.webAdaptor(Actions.click, "lnKAssignProperties");
		
		
		checkElementExistence(driver,By.xpath("lblManageAssignProperties"),4);
		checkElementExistence(driver,By.xpath("lblStaffName"),0);
		checkElementExistence(driver,By.xpath("edtSearchPropertyName"),0);

		
		COC.webAdaptor(Actions.setText, "edtSearchPropertyName", testData.get("PropertyName"));
		checkElementExistence(driver,By.xpath("iconSearch"),2);
		COC.webAdaptor(Actions.click, "iconSearch");
		checkElementExistence(driver,By.xpath("chkboxSelect"),2);
		COC.webAdaptor(Actions.click, "chkboxSelect");
		checkElementExistence(driver,By.xpath("btnNext"),1);
		COC.webAdaptor(Actions.click, "btnNext");
		//click on back link
		checkElementExistence(driver,By.xpath("lnkBackUpdate"),3);
		COC.webAdaptor(Actions.click, "lnkBackUpdate");
		
		checkElementExistence(driver,By.xpath("lnKAssignProperties"),3);
		COC.webAdaptor(Actions.click, "lnKAssignProperties");
		
		checkElementExistence(driver,By.xpath("lblPropertyAdded"),3);
		checkElementExistence(driver,By.xpath("lblPropertyName"),0);
		checkElementExistence(driver,By.xpath("lblPropertyNameValue"),0);
		
		checkElementExistence(driver,By.xpath("lblpptyAddress"),0);
		checkElementExistence(driver,By.xpath("lblpptyAddressValue"),0);
		
		COC.webAdaptor(Actions.click, "btnSave");
		
		checkElementExistence(driver,By.xpath("lblSuccessfullyAdded"),4);
		COCDriverScript.logMessage("testStepPass", "Successfully Assigned Staff Member");
		
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		COCDriverScript.logMessage("testStepPass", "Clicked on Assign Properties Link");
	  }catch(Exception e){
		COCDriverScript.logMessage("testStepFail", "Element not found");
		//System.out.println(e.getMessage());
		//e.printStackTrace();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
		
	 }
	return new AssignstaffPage(driver, localData);
}

}