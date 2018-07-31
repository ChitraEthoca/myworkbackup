package com.pge.COC.pageObjects;

import static org.testng.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.actionInterpreter.COC.Actions;
import com.automation.framework.utilities.ObjectRead;
import com.pge.COC.ReUsable.utility.GenericUtilities;

public class WIAssignmentPage extends BasePage {
	WebDriver driver;
	HashMap<String, String> localData;
	ObjectRead objectRead;

	private By usernameFldLocator = By.id("username");
	private By passwordFldLocator = By.id("password");
	private By addUserFldLocator = By.xpath("//input[@id='CF00No0000008cgp3']");
	private By addWorkHourFldLocator = By.xpath("//input[@id='CF00Nq0000000ii4a']");
	private By searchFldLocator = By.xpath("//input[@type='search']");
	private By limitFldLocator = By.xpath("(//label[text()='Limit']/..)/following-sibling::td/input");
	private By checkbox = By.xpath("//input[@type='checkbox']");
	private By editTxtLocator = By.xpath("//td[@class='actionColumn']/a[text()='Cls']/preceding-sibling::a");
	private By leadScoreFldLocator = By.id("00No0000008cgp1_ileinner");
	private By editUserSkillLocator = By.xpath("//a[contains(@title,'Edit - Record 1')]");
	private By userSkillRuleLocator = By.id("CF00No0000008cgox");
	private By userHistoryOperationLocator = By.xpath("//td[text()='Update']");

	public void enterUsername(WebDriver driver, String userName) {
		enterValue(driver, usernameFldLocator, userName);
	}

	public void enterPassword(WebDriver driver, String password) {
		enterValue(driver, passwordFldLocator, password);
	}

	public WIAssignmentPage(WebDriver driver, HashMap<String, String> localData) throws Exception {
		this.driver = driver;
		this.localData = localData;
		this.waitForPageLoad(driver);
		objectRead = new ObjectRead(this.getClass().getSimpleName());
	}

	public WIAssignmentPage loginIntoApplication(Hashtable<String, String> testData) throws Exception {

		String username = getTestDataValue("CSR_User1");
		String password = getTestDataValue("CSR_Password1");
		enterUsername(driver, username);
		enterPassword(driver, password);
		driver.findElement(By.xpath("//*[@id=\"Login\"]")).click();
		Thread.sleep(5000);
		return this;
	}

	public WIAssignmentPage logoutFromApplication(Hashtable<String, String> testData) throws Exception {
		COC.webAdaptor(Actions.setValueToXpathAndClick, "NavLabel");
		COC.webAdaptor(Actions.setValueToXpathAndClick, "Logout");
		Boolean iselementpresent = driver.findElements(usernameFldLocator).size() != 0;
		iselementpresent.equals(true);
		return this;
	}

	public WIAssignmentPage clickOnWorkItemAssignmentTab(Hashtable<String, String> testData) throws Exception {

		COC.webAdaptor(Actions.setValueToXpathAndClick, "WIALink");
		Thread.sleep(5000);
		return this;
	}

	public WIAssignmentPage navigateToAddNewWIAssignment(Hashtable<String, String> testData) throws Exception {

		COC.webAdaptor(Actions.setValueToXpathAndClick, "NewBtn");
		waitForElementVisible(driver, addUserFldLocator, 120, 2);
		enterValue(driver, addUserFldLocator, getTestDataValue("User Without CSR"));
		enterValue(driver, addWorkHourFldLocator, getTestDataValue("Work Time"));
		COC.webAdaptor(Actions.setValueToXpathAndClick, "SaveBtn");
		return this;

	}

	public WIAssignmentPage addViewUserAssignment(Hashtable<String, String> testData) throws Exception {

		// COC.webAdaptor(Actions.setValueToXpathAndClick, "WISpecific");
		waitForElementVisible(driver, By.id("phSearchInput"), 120, 2);
		COC.webAdaptor(Actions.setValueToXpathAndClick, "viewaddUserSkills");
		enterValue(driver, searchFldLocator, "NEM");
		enterValue(driver, searchFldLocator, "DA CCA");
		enterValue(driver, searchFldLocator, "UUT Webform");
		enterValue(driver, searchFldLocator, "NEM Webform");
		enterValue(driver, searchFldLocator, "Landlord Case");
		enterValue(driver, searchFldLocator, "DAASU Case");
		enterValue(driver, searchFldLocator, "Billing 101");
		selectAllCheckbox(driver, checkbox);
		COC.webAdaptor(Actions.clear, "searchFld");
		enterValue(driver, searchFldLocator, "QAS 9 Webform");
		COC.webAdaptor(Actions.clear, "searchFld");
		enterValue(driver, searchFldLocator, "UUT Webform");
		COC.webAdaptor(Actions.selectCheckBox, "Checkbox");
		COC.webAdaptor(Actions.clear, "searchFld");
		enterValue(driver, searchFldLocator, "NEM WF Error - Auto VMX");
		COC.webAdaptor(Actions.selectCheckBox, "Checkbox");
		COC.webAdaptor(Actions.clear, "searchFld");
		enterValue(driver, searchFldLocator, "New Account Setup Webform");
		COC.webAdaptor(Actions.selectCheckBox, "Checkbox");
		COC.webAdaptor(Actions.setValueToXpathAndClick, "SaveUserSkills");
		waitForElementVisible(driver, searchFldLocator, 120, 2);
		COC.webAdaptor(Actions.setValueToXpathAndClick, "ReturnBtn");
		Thread.sleep(5000);
		return this;
	}

	public WIAssignmentPage updateLimitAndCheckAvailability(Hashtable<String, String> testData) throws Exception {

		waitForElementVisible(driver, By.id("phSearchInput"), 120, 2);
		COC.webAdaptor(Actions.setValueToXpathAndClick, "Edit");
		waitForElementVisible(driver, limitFldLocator, 120, 2);
		COC.webAdaptor(Actions.clear, "limitFldLocator");
		enterValue(driver, limitFldLocator, "6");
		// COC.webAdaptor(Actions.isCheckboxChecked, "CheckboxAvailability");
		COC.webAdaptor(Actions.setValueToXpathAndClick, "CheckboxAvailability");
		COC.webAdaptor(Actions.setValueToXpathAndClick, "SaveBtn");
		Thread.sleep(5000);
		return this;
	}

	public WIAssignmentPage changeStatusForUserWI(Hashtable<String, String> testData) throws Exception {
		clickOnEditLink(driver, editTxtLocator);
		return this;
	}

	public WIAssignmentPage verifyUnassignWI(Hashtable<String, String> testData) throws Exception {

		// COC.webAdaptor(Actions.setValueToXpathAndClick, "WISpecific");
		waitForElementVisible(driver, By.id("phSearchInput"), 120, 2);

		COC.webAdaptor(Actions.setValueToXpathAndClick, "UnassignWorkItem");
		Thread.sleep(5000);
		handleAlert(driver);
		Thread.sleep(3000);
		checkAvailability(driver, limitFldLocator);
		COC.webAdaptor(Actions.setValueToXpathAndClick, "UnassignWorkItem");
		Thread.sleep(5000);
		handleAlert(driver);
		WebElement element = driver.findElement(leadScoreFldLocator);
		String before = element.getText();
		Thread.sleep(3000);
		checkAvailability(driver, limitFldLocator);
		WebElement element1 = driver.findElement(leadScoreFldLocator);
		String after = element1.getText();
		Reporter.log("Load Score Before" + after);
		Reporter.log("Load Score After" + before);
		assertNotEquals(before, after);
		return this;
	}

	public WIAssignmentPage editUserSkill(Hashtable<String, String> testData) throws Exception {
		GenericUtilities util = new GenericUtilities();
		
		WebElement userSkillHistory = driver.findElement(By.xpath("//*[contains(text(),'Logged in User')]"));
		util.scrollIntoView(userSkillHistory);
		clickOnEditSkillLink(driver, editUserSkillLocator);
		enterValue(driver, userSkillRuleLocator, "UUT Webform");
		COC.webAdaptor(Actions.setValueToXpathAndClick, "SaveBtn");
		WebElement userSkillHistory1 = driver.findElement(By.xpath("//*[contains(text(),'Logged in User')]"));
		util.scrollIntoView(userSkillHistory1);
		Boolean iselementpresent = driver.findElements(userHistoryOperationLocator).size() != 0;
		iselementpresent.equals(true);
		Reporter.log("Verified Update Operation in User Skill History");
		return this;
	}

}
