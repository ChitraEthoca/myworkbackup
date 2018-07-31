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
import com.pge.COC.pageObjects.R2_ETE_CsutomerContact_CaseLogs;

public class R2_ETE_CustomerContact_CaseLogsTest extends BaseTestClass {

	private static String almID = "";
	ObjectRead objectRead;
	BaseTest base;

	@DataProvider(name = "testDataProvider")
	public Object[][] testDataProvider() throws DriverScriptException {
		base = new BaseTest(almID);
		return ObjectRead.getConsolidatedTestDataArray(this.getClass().getSimpleName(), "SFDC");
	}

	@Test(dataProvider = "testDataProvider")
	public void checkCustomerContact(Hashtable<String, String> testData, ITestContext context) throws Exception {
		try {
			super.initializeDriver(testData);
			super.launchApplication(COCDriverScript.globalParamMap.get("url_sfdc"));
			HashMap<String, String> localData = new HashMap<String, String>();
			
			R2_ETE_CsutomerContact_CaseLogs contactPage = new R2_ETE_CsutomerContact_CaseLogs(driver, localData);
			
			contactPage.loginIntoApplication(testData);
			
			contactPage.navigateToWI_AddContact2(testData); 
		

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
