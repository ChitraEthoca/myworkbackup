package com.pge.COC.tests;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.framework.core.BaseTest;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.core.Status;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.utilities.ObjectRead;
import com.pge.COC.pageObjects.WIAssignmentPage;

public class R2_ETE_WIAssignmentPage extends BaseTestClass {
	
	private static String almID = "";
	ObjectRead objectRead;
	BaseTest base;

	@DataProvider(name = "testDataProvider")
	public Object[][] testDataProvider() throws DriverScriptException {
		base = new BaseTest(almID);
		return ObjectRead.getConsolidatedTestDataArray(this.getClass().getSimpleName(), "SFDC");
	}
	
	@Test(dataProvider = "testDataProvider")	
	public void CheckE2EWIAssignment(Hashtable<String, String> testData, ITestContext context) throws Exception {
		try{
			super.initializeDriver(testData);
			super.launchApplication(COCDriverScript.globalParamMap.get("url_sfdc"));
			HashMap<String, String> localData = new HashMap<String, String>();
			
			WIAssignmentPage assignmentPage = new WIAssignmentPage(driver, localData);
			assignmentPage.loginIntoApplication(testData);
			Reporter.log("Logged into SFDC Application");
			assignmentPage.clickOnWorkItemAssignmentTab(testData);
			Reporter.log("Clicked on Work Item Assignment tab");
			assignmentPage.navigateToAddNewWIAssignment(testData);
			Reporter.log("Navigate to add new WI Assignment");
			assignmentPage.addViewUserAssignment(testData);
			Reporter.log("Added User Skill lists");
			assignmentPage.updateLimitAndCheckAvailability(testData);
			Reporter.log("Updated Limit and Check Availability");
			assignmentPage.changeStatusForUserWI(testData);
			Reporter.log("Changed the status for User WI");
			assignmentPage.verifyUnassignWI(testData);
			Reporter.log("Verified Unassign WI");
			assignmentPage.editUserSkill(testData);
			Reporter.log("Edited User Skill and verified in the Histories");
			assignmentPage.logoutFromApplication(testData);
			Reporter.log("Verified Logged Out Successfully");
			
			
		}catch(Exception e){
			COCDriverScript.logMessage("testStepFail", e.getMessage(), Status.FAIL);
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		} finally {
			//Call teardown method
			 super.tearDown();
		}
		
	}

}
