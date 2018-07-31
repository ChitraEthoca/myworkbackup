package com.pge.COC.tests;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.actionInterpreter.COC.Actions;
import com.automation.framework.core.BaseTest;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.utilities.ObjectRead;
import com.pge.COC.ReUsable.utility.TestListener;
import com.pge.COC.pageObjects.PMPSignInPage;
@Listeners(TestListener.class)
public class Report  extends BaseTestClass {
	private static String almID = "3825";
	ObjectRead objectRead;
	BaseTest base;
@DataProvider(name = "testDataProvider")
	public Object[][] testDataProvider() throws DriverScriptException {
		base = new BaseTest(almID);
		return ObjectRead.getTestDataArray(this.getClass().getSimpleName());
	}
@Test(dataProvider="testDataProvider")
	public void Test1(Hashtable<String, String>  testData, ITestContext context) throws Exception {
		try{
			super.initializeDriver(testData, context);
// Navigate to URL 		
			driver.navigate().to(COCDriverScript.globalParamMap.get("url"));
			COCDriverScript.driver = driver;
			String browser = testData.get("Browser");
			COCDriverScript.logMessage("testStepPass", "Browser Launched" + "Browser Type =" +browser);
			COC.webAdaptor(Actions.maximize);
			HashMap<String, String> localData = new HashMap<String, String>();
//	Launch the SignIn Page & Maximize
			PMPSignInPage sp = new PMPSignInPage(driver,localData);
//	SignIn to PMP Page
			sp.Login(testData)
//  Generate Report & Download Report
			.clickReport(testData)
			.clickRequestreport(testData)
			.Requestreport(testData)
			.Confirmation(testData)
			.ViewReport(testData);
		}	
		catch(Exception e){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("Screenshots//" + this.getClass().getSimpleName() + "_" +System.currentTimeMillis() +".png"));
			System.out.println(e.getMessage());
			e.printStackTrace();
			Assert.fail(e.getMessage(), e);
			Assert.fail("Error executing Test...TestCaseName: "+this.getClass().getSimpleName()+"...Iteration:'" + testData.get("Iteration") + "'");
		}
		finally{	
//teardown method
			super.tearDown();
		}
	}	
}