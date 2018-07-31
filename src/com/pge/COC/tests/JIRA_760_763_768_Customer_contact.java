package com.pge.COC.tests;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.framework.core.BaseTest;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.core.Status;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.utilities.ObjectRead;
import com.pge.COC.pageObjects.CustomerContactPage_760_763_768;

public class JIRA_760_763_768_Customer_contact extends BaseTestClass {

	private static String almID = "";
	ObjectRead objectRead;
	BaseTest base;

	@DataProvider(name = "testDataProvider")
	public Object[][] testDataProvider() throws DriverScriptException {
		base = new BaseTest(almID);
		return ObjectRead.getConsolidatedTestDataArray(this.getClass().getSimpleName(), "SFDC");
	}

	@Test(dataProvider = "testDataProvider", enabled=true)
	public void checkCustomerContact1(Hashtable<String, String> testData, ITestContext context) throws Exception {
		try {
			super.initializeDriver(testData);
			super.launchApplication(COCDriverScript.globalParamMap.get("url_sfdc"));
			HashMap<String, String> localData = new HashMap<String, String>();
			
			CustomerContactPage_760_763_768 contactPage1 = new CustomerContactPage_760_763_768(driver, localData);
			contactPage1.loginIntoApplication(testData);
			
			contactPage1.navigateToWI_AddContact1(testData); 
		

		} catch (Exception e) {
			COCDriverScript.logMessage("testStepFail", e.getMessage(), Status.FAIL);
			
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			
			// Call tear down method
			
			super.tearDown();
		}

	}
}
