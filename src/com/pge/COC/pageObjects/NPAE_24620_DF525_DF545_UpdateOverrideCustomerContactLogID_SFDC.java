package com.pge.COC.pageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.automation.framework.utilities.ObjectRead;
import com.pge.COC.ReUsable.utility.GenericUtilities;

/**
 * 
 * @author Chitra
 * 
 *         This test case needs data as below- 
 *         1. A Work item with existing related record and CCB log and should have close work item button
 *         enabled. i.e was never closed earlier -  update value of WiWithCustContactAbacInfo in testDataWFM file for this.
 *         2. Another work item which related contact and CCB log . Update value of CaseWIwithExistingRelatedCCB in TestDataWFM sheet.
 *         3.1 -Will use the same record used in step 2.
 *         3.2 Another Work item with no related record and no CCB log.
 *
 */

public class NPAE_24620_DF525_DF545_UpdateOverrideCustomerContactLogID_SFDC extends BasePage {
	WebDriver driver;
	HashMap<String, String> localData;
	ObjectRead objectRead;

	private By usernameFldLocator = By.id("username");
	private By passwordFldLocator = By.id("password");
	private By customerContactInputEle = By.xpath("//*[@id='00Nq0000000ZKwc']");
	@FindBy(xpath = "//*[@name=\"view_cc_b_case\"]")
	private WebElement viewCCBCase;

	@FindBy(xpath = "//*[@id=\"00No0000008cgnu\"]")
	private WebElement RRstatusDropDownEle;

	@FindBy(xpath = "//select[@id=\"00Nq0000000iAXY\"]")
	private WebElement soxReqInfoEle;

	@FindBy(xpath = "//*[contains(text(),'Work Items about to ')]")
	private WebElement WIsaveSuccessfullConfirmEle;

	@FindBy(xpath = "//*[@id=\"00No0000008cgll\"]")
	private WebElement custContactTypeDropdown;

	@FindBy(xpath = "//a[contains(text(),'RR')]")
	private WebElement viewContactEle;

	@FindBy(xpath = "//*[@id=\"00Nq0000000ZKwa\"]")
	private WebElement overrideCCBCheckBoxEle;

	@FindBy(xpath = "//*[@class=\"pbError\"]")
	private WebElement generalErrorMessage;
	@FindBy(xpath = "//*[@name=\"cancel\"]")
	private WebElement cancelEditCCBlogBtn;

	// CCB login details
	@FindBy(xpath = "//*[@id=\"userId\"]")
	private WebElement CCBUserIdEle;
	@FindBy(xpath = "//input[@id=\"password\"]")
	private WebElement CCBPasswordEle;
	@FindBy(xpath = "//input[@id=\"loginButton\"]")
	private WebElement CCBLoginBtn;

	@FindBy(xpath = "//*[@id=\"00No0000008cglY\"]")
	private WebElement billedToAmountEle;
	@FindBy(xpath = "//*[@id=\"00No0000008cglX\"]")
	private WebElement billedFromDateEle;
	@FindBy(xpath = "//*[@id=\"00No0000008cglZ\"]")
	private WebElement billedToDateEle;

	@FindBy(xpath = "(//td[@class=\" dataCell  \"])[position()=1]")
	private WebElement CustContactLogEntryEle;

	@FindBy(xpath = "//*[contains(text(),'CC LOG-')]")
	private WebElement customerContactLogNameEle;

	@FindBy(xpath = "//*[@name=\"view_cc_b_customer_contact\"]")
	private WebElement viewCCBCustomerContactEle;

	@FindBy(xpath = "(//*[@aria-labelledby=\"D_L_CC_LOG_CONTENT\"])[position()=1]//..")
	private WebElement CCLogStatementEle;
	@FindBy(xpath = "//*[contains(text(),'Complete')]")
	private WebElement CCLOGEntryTextEle;

	@FindBy(xpath = "//*[@title='Log']")
	private WebElement LogTabCCBele;
	@FindBy(xpath = "//*[@id=\"00Nq0000000jVKE\"]")
	private WebElement cclogStatusDropdownEle;

	@FindBy(xpath = "//*[@id=\"ValidationFormulaList_title\"]")
	private WebElement validationRules;

	@FindBy(xpath = "(//*[contains(text(),'Cannot update status to Complete or Pending')]/following-sibling::td)/..")
	private WebElement cannotUpdateStatusToCompleteCheckboxele;

	@FindBy(xpath = "(//*[contains(text(),'Customer Contact Log cannot be saved if Log Entry is blank.')]/following-sibling::td)[position()=1]")
	private WebElement userCannotSaveBlankLogEntryCheckboxele;

	@FindBy(xpath = "(//*[contains(text(),'Log cannot be edited if the Status is Complete')]/following-sibling::td)[position()=1]")
	private WebElement cannotEditLogIfStatusIsComplete;

	@FindBy(xpath = "(//*[contains(text(),'You do not have the permission to update the status.')]/following-sibling::td)[position()=1]")
	private WebElement cannotUpdateStatus;

	@FindBy(xpath = "(//*[contains(text(),'If Log has errored, Please set Status to In-Progress to resend')]/following-sibling::td)[position()=1]")
	private WebElement userCanOnlyChangeStatusUponError;

	@FindBy(xpath = "//*[@class=\"errorMsg\"]")
	private WebElement cclogStatusUpdateErrorMsg;

	@FindBy(xpath = "//*[contains(text(),'Related Records')]")
	private WebElement customerRelatedRecNamEle;

	@FindBy(xpath = "//*[contains(text(),'CC LOG-')]")
	private WebElement CCLOGNumberEle;

	@FindBy(xpath = "//*[@id=\"00Nq0000001NYg6_ileinner\"]")
	private WebElement currentCCBLogEntry;

	@FindBy(xpath = "//*[@id=\"00Nq0000001NYg8\"]")
	private WebElement overrideCCBCheckBox;

	@FindBy(xpath = "//*[contains(text(),\"Error Message\")]")
	private WebElement errorMessage;

	@FindBy(xpath = "//*[@title=\"Edit\"]")
	private WebElement editButton;

	@FindBy(xpath = "//*[@name=\"save\"]")
	private WebElement cclogSaveButton;

	@FindBy(xpath = "//*[contains(text(),'Customer Contact Log History')]")
	private WebElement customerContactLogistory;

	@FindBy(xpath = "//*[@name=\"view_cc_b_customer_contact\"]")
	private WebElement viewCCBContactBtnEle;

	@FindBy(xpath = "//*[@id=\"00Nq0000000ZKwP\"]")
	WebElement addTOCCBCaseLOgCheckBox;

	@FindBy(xpath = "//*[@id=\"00No0000008cglf\"]")
	WebElement createCustContinCCBCheckBox;

	public NPAE_24620_DF525_DF545_UpdateOverrideCustomerContactLogID_SFDC(WebDriver driver,
			HashMap<String, String> localData) throws Exception {
		this.driver = driver;
		this.localData = localData;
		this.waitForPageLoad(driver);
		objectRead = new ObjectRead(this.getClass().getSimpleName());
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(WebDriver driver, String userName) {
		enterValue(driver, usernameFldLocator, userName);
	}

	public void enterPassword(WebDriver driver, String password) {
		enterValue(driver, passwordFldLocator, password);
	}

	public void clickOnWIWithStatusBWO(WebDriver driver, By loc) {
		driver.findElement(loc).click();
	}

	public void clickOnWorkItems() throws Exception {

		GenericUtilities util = new GenericUtilities();

		WebElement homebutton = driver.findElement(By.xpath("//*[@href=\"/home/home.jsp\"]"));

		util.click(homebutton);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[contains(text(),'Work Items')]")).click();

	}

	public void selectMyWI() throws Exception {
		Select dropdown = new Select(driver.findElement(By.id("fcf")));
		dropdown.selectByIndex(3);
	}

	public NPAE_24620_DF525_DF545_UpdateOverrideCustomerContactLogID_SFDC loginIntoApplicationasCSR() throws Exception {

		Reporter.log("Step 1 Started- Application Login as Admin");
		try {
			String username = getTestDataValue("CSR_User1");
			String password = getTestDataValue("CSR_Password1");
			enterUsername(driver, username);
			enterPassword(driver, password);
			driver.findElement(By.xpath("//input[@id=\"Login\"]")).click();
			Thread.sleep(2000);

			Reporter.log("Successfully logged into application as an Admin");

		} catch (Exception E) {

			Reporter.log("Step 1- login failed");
		}
		return this;
	}

	public NPAE_24620_DF525_DF545_UpdateOverrideCustomerContactLogID_SFDC ccLogValidationRuleCheck() throws Exception {

		GenericUtilities util = new GenericUtilities();

		try {
			WebElement homebutton = driver.findElement(By.xpath("//*[@href=\"/home/home.jsp\"]"));

			util.click(homebutton);
			Thread.sleep(1000);

			WebElement navMenu = driver.findElement(By.xpath("//*[@id=\"userNav-arrow\"]"));
			util.click(navMenu);
			WebElement setupButton = driver.findElement(By.xpath("//*[@title=\"Setup\"]"));
			util.click(setupButton);
			WebElement createButton = driver.findElement(By.xpath("//*[@id=\"DevTools_font\"]"));
			util.click(createButton);
			WebElement objectButton = driver.findElement(By.xpath("//*[@id=\"CustomObjects_font\"]"));
			util.click(objectButton);
			WebElement ccLogLink = driver.findElement(By.xpath("//*[contains(text(),\"Customer Contact Log\")]"));
			util.click(ccLogLink);
			util.scrollIntoView(validationRules);
			Thread.sleep(2000);
			// The Active checkbox is Checked
			util.highlighElement(cannotUpdateStatusToCompleteCheckboxele);
			Assert.assertTrue(cannotUpdateStatusToCompleteCheckboxele.isEnabled());
			Thread.sleep(2000);
			util.highlighElement(userCannotSaveBlankLogEntryCheckboxele);
			Assert.assertTrue(userCannotSaveBlankLogEntryCheckboxele.isEnabled());

			Reporter.log("Assertion passed cannotUpdateStatusToCompleteCheckboxele IS CHECKED");
			Reporter.log("Assertion passed userCannotSaveBlankLogEntryCheckboxele IS CHECKED");

			// The Active checkbox is UnChecked
			Thread.sleep(2000);
			Assert.assertFalse(cannotEditLogIfStatusIsComplete.isSelected());
			Thread.sleep(2000);
			Assert.assertFalse(cannotUpdateStatus.isSelected());
			Thread.sleep(2000);
			Assert.assertFalse(userCanOnlyChangeStatusUponError.isSelected());
			Reporter.log("Step 1 - Customer Contact Log Validation Rule Check in SFDC Completed");

		} catch (Exception e) {

			e.printStackTrace();

			Reporter.log(" Assertion failed cannotUpdateStatusToCompleteCheckboxele IS NOT CHECKED" + e);
			Reporter.log(" Assertion failed userCannotSaveBlankLogEntryCheckboxele IS NOT CHECKED" + e);
			Reporter.log(" Assertion failed cannotEditLogIfStatusIsComplete IS  CHECKED" + e);
			Reporter.log(" Assertion failed cannotUpdateStatus IS  CHECKED" + e);
			Reporter.log(" Assertion failed userCanOnlyChangeStatusUponError IS  CHECKED" + e);
			Reporter.log(" Validation Rules checkbox verification done");
			Reporter.log("Step 1 passed");
		}

		// Step 2
		Reporter.log("Step 2 started");
		util.customAlertByJS("Step 2 started");

		try {

			Thread.sleep(1000);
			WebElement objectButton = driver.findElement(By.xpath("//*[@id=\"CustomObjects_font\"]"));
			util.click(objectButton);
			util.scrollIntoView(customerRelatedRecNamEle);
			Thread.sleep(1000);
			Assert.assertTrue(customerRelatedRecNamEle.isDisplayed());
			util.click(customerRelatedRecNamEle);
			util.scrollIntoView(validationRules);
			Thread.sleep(1000);
			WebElement ccLogLink = driver
					.findElement(By.xpath("//*[contains(text(),\"Must_uncheck_Add_to_CCB_Case_Log_on_Edit\")]"));
			util.click(ccLogLink);

			WebElement errorFormulaEle = driver.findElement(By.xpath("//*[contains(text(),'AND')]"));

			util.highlighElement(errorFormulaEle);

			Assert.assertTrue(errorFormulaEle.isDisplayed());

			Thread.sleep(1000);
			Reporter.log("Step 2 passed - Validation of Updated Error Condition Formula");

		} catch (AssertionError e) {
			Reporter.log(" Step 2 failed :" + e);
		} catch (Exception e) {
			Reporter.log(" Step 2 failed :" + e);
		}

		// Step 3
		try {

			util.customAlertByJS("Step 3 started");
			Reporter.log("Step 3 started- logging out as an Admin");
			util.switchToTab(0);
			WebElement logOutMenu = driver.findElement(By.xpath("//*[@id=\"userNav-arrow\"]"));
			util.click(logOutMenu);
			WebElement logoutButton = driver.findElement(By.xpath("//*[@title=\"Logout\"]"));
			util.click(logoutButton);

			Reporter.log("Application Login as CSR");

			String username = getTestDataValue("CSR_User");
			String password = getTestDataValue("CSR_Password");
			enterUsername(driver, username);
			enterPassword(driver, password);
			driver.findElement(By.xpath("//input[@id=\"Login\"]")).click();
			Thread.sleep(3000);

			Reporter.log("Step 3 completed. Successfully logged into application as CSR");

		} catch (Exception E) {

			Reporter.log("Step 3- login failed");
		}

		// Step 4
		try {

			util.customAlertByJS("Step 4 started- Opening work item");

			{
				Reporter.log("Step 4 started - Clicked on Work Items to get the WI lists");
				util.switchToTab(0);
				clickOnWorkItems();
				selectMyWI();
				String WIwithoutCustCOntact = BasePage.getTestDataValue("WiWithCustContactAbacInfo");

				WebElement WIwithContactAbac = driver
						.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

				util.click(WIwithContactAbac);
			}
			Reporter.log(" Work item with customer contact and ABAC Info opened");
			Reporter.log("Step 4 completed- Work item opened successfully");

			Thread.sleep(1000);
		}

		catch (Exception e) {

			Reporter.log("Step 4 failed- Unable to open work item");

		}

		// Step 5
		try {
			util.customAlertByJS("Step 5 started- Opening Related Record");

			Reporter.log("Step 5 started");
			Thread.sleep(1000);
			util.click(viewContactEle);

			try {
				Assert.assertTrue(errorMessage.isDisplayed());

				Reporter.log("Step 5 - Error Message Validation completed successfully");

			} catch (AssertionError e) {

				Reporter.log("Step 5 failed" + e.getLocalizedMessage());
			}
		} catch (Exception e) {

			Reporter.log("Step 5 failed" + e.getMessage());
			Reporter.log("Step 5 failed- Unable to click Related Record");

		}

		// Step 6
		util.customAlertByJS("Step 6 started- Customer Contact Log new Fields Verification");
		Reporter.log("Step 6 started");
		try {
			util.scrollIntoView(CCLOGNumberEle);
			Thread.sleep(1000);
			util.click(CCLOGNumberEle);
			Assert.assertTrue(currentCCBLogEntry.isDisplayed());
			Reporter.log("Current CC&B Log Entry field displayed");
			WebElement overrideCheckbox = driver.findElement(By.xpath("//*[@id=\"00Nq0000001NYg8_chkbox\"]"));
			Assert.assertTrue(overrideCheckbox.isDisplayed());
			Reporter.log("Override CC&B CheckBox is displayed");
			Assert.assertTrue(errorMessage.isDisplayed());
			Reporter.log("Error Message Field is displayed");
			Assert.assertTrue(editButton.isDisplayed());
			Reporter.log("Edit Button is added at the top");
			Assert.assertTrue(customerContactLogistory.isDisplayed());
			Reporter.log("Customer Contact Log History is displayed");

			Reporter.log("Step 6 passed");
		}

		catch (AssertionError e) {

			Reporter.log("Step 6 passed" + e.getLocalizedMessage());
		}

		catch (Exception e) {

			Reporter.log("Step 6 passed" + e.getLocalizedMessage());
		}

		// Step 7 Started

		util.customAlertByJS("Step 7 started");
		Reporter.log("Step 7 - Log entries are different and the Override CC&B not Checked");

		try {
			util.click(editButton);

			WebElement logEntryField = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKC\"]"));

			String logText = logEntryField.getText();

			util.sendKeys(logEntryField, logText.concat(" Updated logs while Testing"));

			WebElement overrideCCBCheckboxEle = driver.findElement(By.xpath("//*[@id=\"00Nq0000001NYg8\"]"));

			if (overrideCCBCheckboxEle.isSelected() == true) {

				overrideCCBCheckboxEle.click();
			}

			WebElement saveBUtton = driver.findElement(By.xpath("//*[@name=\"save\"]"));
			util.click(saveBUtton);

			WebElement errorMessage = driver.findElement(By.xpath("//*[@class=\"errorMsg\"]"));

			Assert.assertTrue(errorMessage.isDisplayed());

			WebElement cancelButton = driver.findElement(By.xpath("//*[@name=\"cancel\"]"));
			util.click(cancelButton);
			Reporter.log("Step 7 passed");
		} catch (Exception e) {

			Reporter.log("Step 7 failed" + e.getLocalizedMessage());
		}

		// Step 8 started

		util.customAlertByJS("Step 8 started");
		Reporter.log("Step 8- Customer Contact Log characters exceeds");

		try {
			util.click(editButton);

			WebElement logEntryField = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKC\"]"));

			String logText = logEntryField.getText();

			util.sendKeys(logEntryField, logText.concat(logText));

			WebElement overrideCCBCheckboxEle = driver.findElement(By.xpath("//*[@id=\"00Nq0000001NYg8\"]"));

			if (overrideCCBCheckboxEle.isSelected() == false) {

				overrideCCBCheckboxEle.click();
			}

			WebElement saveBUtton = driver.findElement(By.xpath("//*[@name=\"save\"]"));
			util.click(saveBUtton);

			//
			util.click(editButton);

			WebElement logEntryField1 = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKC\"]"));

			util.sendKeys(logEntryField1, "New log entry while testing");

			WebElement overrideCCBCheckboxEle1 = driver.findElement(By.xpath("//*[@id=\"00Nq0000001NYg8\"]"));

			if (overrideCCBCheckboxEle1.isSelected() == false) {

				overrideCCBCheckboxEle1.click();
			}

			WebElement saveBUtton1 = driver.findElement(By.xpath("//*[@name=\"save\"]"));
			util.click(saveBUtton1);
			driver.navigate().refresh();

			String logMessage = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKC_ileinner\"]")).getText();
           // Below assertion will pass only when you give correct value of SFDCID in testDataWFM file.
		    Assert.assertTrue(logMessage.contains(BasePage.getTestDataValue("SFDCID")));

			Reporter.log("Step 8 passed");

		} catch (AssertionError e) {

			Reporter.log("Step 8 failed" + e.getLocalizedMessage());
		} catch (Exception e) {

			Reporter.log("Step 8 failed" + e.getLocalizedMessage());
		}
		// Step 9 started

		util.customAlertByJS("Step 9 started");
		Reporter.log("Step 9 - Customer Contact Log entry with Special characters and Carriage return");

		try {
			util.click(editButton);

			WebElement logEntryField = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKC\"]"));

			util.sendKeys(logEntryField, "A $ Change the TIN to the Primary ID A");

			WebElement overrideCCBCheckboxEle = driver.findElement(By.xpath("//*[@id=\"00Nq0000001NYg8\"]"));

			if (overrideCCBCheckboxEle.isSelected() == false) {

				overrideCCBCheckboxEle.click();
			}

			WebElement saveBUtton = driver.findElement(By.xpath("//*[@name=\"save\"]"));

			util.click(saveBUtton);
			driver.navigate().refresh();
			String logMessage = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKC_ileinner\"]")).getText();
			String status = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKE_ileinner\"]")).getText();

			Assert.assertTrue(logMessage.contains("A $ Change the TIN to the Primary ID A"));
			Assert.assertTrue(status.contains("Complete"));

			Reporter.log("Step 9 passed");
		} catch (AssertionError e) {

			Reporter.log("Step 9 failed" + e.getLocalizedMessage());
		} catch (Exception e) {

			Reporter.log("Step 9 failed" + e.getLocalizedMessage());
		}

		// Step 10
		try {
			util.customAlertByJS("Step 10 started- Opening Related Record");

			Reporter.log("Step 10 started-Verifying logs");
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WiWithCustContactAbacInfo");

			WebElement WIwithContactAbac = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithContactAbac);

			util.click(viewContactEle);

			Thread.sleep(1000);

			util.scrollIntoView(customerContactLogNameEle);

			Assert.assertTrue(customerContactLogNameEle.isDisplayed());

			String ABACLogEntry = CustContactLogEntryEle.getText();
			Thread.sleep(2000);

			util.click(customerContactLogNameEle);

			WebElement CClogLogIDELe = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKD_ileinner\"]"));

			util.scrollIntoView(CClogLogIDELe);

			String CClogLogID = CClogLogIDELe.getText();

			driver.navigate().back();
			viewCCBCustomerContactEle.click();

			try {
				util.switchToTab(1);
				util.CCBLogin(CCBUserIdEle, CCBPasswordEle, CCBLoginBtn);
				Reporter.log("CCB logged in successfully");
			} catch (Exception e) {
			}

			util.switchToTab(1);
			driver.switchTo().frame("main");
			driver.switchTo().frame("tabPage");
			driver.switchTo().frame("CC_LOG_GRID");
			String CCBLOg = util.getText(CCLogStatementEle);

			Assert.assertEquals(ABACLogEntry.trim(), CCBLOg.trim());

			Reporter.log("SFDC and CC&B log entry verification done");
			util.switchToTab(1);
			driver.switchTo().defaultContent();

			Thread.sleep(2000);
			driver.switchTo().frame("main");
			Thread.sleep(2000);
			driver.switchTo().frame("tabMenu");
			Thread.sleep(2000);

			util.click(LogTabCCBele);
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			driver.switchTo().frame("main");
			Thread.sleep(1000);
			driver.switchTo().frame("tabPage");
			Thread.sleep(1000);

			String CCBLOGID = driver.findElement(By.xpath("//*[@name=\"CC_LOG$CC_LOG_ID\"]")).getText();

			Assert.assertEquals(CClogLogID, CCBLOGID);

			Reporter.log("Step 10 passed");
		}

		catch (AssertionError e) {
			Reporter.log("Step 10- Assertions have failed :" + e.getLocalizedMessage());
		}

		catch (Exception e) {
			Reporter.log("Step 10 has failed :" + e.getLocalizedMessage());
		}
		// Step 11

		util.customAlertByJS("Step 11 started");
		Reporter.log("Step 11 started");
		try {
			util.switchToTab(0);
			util.click(customerContactLogNameEle);
			util.click(editButton);

			util.selectByIndex(4, cclogStatusDropdownEle);

			util.click(cclogSaveButton);

			// selecting status= complete, override checkbox= unchecked and
			// saving.
			util.click(editButton);

			util.selectByIndex(3, cclogStatusDropdownEle);

			if (overrideCCBCheckBox.isSelected() == true) {

				overrideCCBCheckBox.click();
			}

			util.click(cclogSaveButton);
			Assert.assertTrue(cclogStatusUpdateErrorMsg.isDisplayed());
			util.click(cancelEditCCBlogBtn);

			// selecting status= complete, override checkbox= checked and
			// saving.
			util.click(editButton);
			util.selectByIndex(3, cclogStatusDropdownEle);

			if (overrideCCBCheckBox.isSelected() == false) {

				overrideCCBCheckBox.click();
			}

			util.click(cclogSaveButton);
			Assert.assertTrue(cclogStatusUpdateErrorMsg.isDisplayed());
			util.click(cancelEditCCBlogBtn);

			// selecting status= pending, override checkbox= checked and saving.
			util.click(editButton);

			util.selectByIndex(1, cclogStatusDropdownEle);

			if (overrideCCBCheckBox.isSelected() == false) {

				overrideCCBCheckBox.click();
			}

			util.click(cclogSaveButton);
			Assert.assertTrue(cclogStatusUpdateErrorMsg.isDisplayed());
			util.click(cancelEditCCBlogBtn);

			// selecting status= pending, override checkbox= unchecked and
			// saving.
			util.click(editButton);

			util.selectByIndex(1, cclogStatusDropdownEle);

			if (overrideCCBCheckBox.isSelected() == true) {

				overrideCCBCheckBox.click();
			}

			util.click(cclogSaveButton);
			Assert.assertTrue(cclogStatusUpdateErrorMsg.isDisplayed());
			Reporter.log("Step 11 passed");
		}

		catch (AssertionError e) {
			Reporter.log("Step 11 failed " + e.getLocalizedMessage());
		} catch (Exception e) {
			Reporter.log("Step 11 failed " + e.getLocalizedMessage());
		}

		// Step 12 started
		util.customAlertByJS("Step 12 started");
		Reporter.log("St1p 12 started : Empty CClog and cave");
		try {

			util.click(cancelEditCCBlogBtn);
			util.click(editButton);

			WebElement logEntryField = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKC\"]"));

			util.sendKeys(logEntryField, "");
			if (overrideCCBCheckBox.isSelected() == true) {

				overrideCCBCheckBox.click();
			}

			util.click(cclogSaveButton);

			Assert.assertTrue(cclogStatusUpdateErrorMsg.isDisplayed());

			if (overrideCCBCheckBox.isSelected() == false) {

				overrideCCBCheckBox.click();
			}

			util.click(cclogSaveButton);
			Assert.assertTrue(cclogStatusUpdateErrorMsg.isDisplayed());
			Reporter.log("Step 12 passed");
		} catch (AssertionError e) {
			Reporter.log("Step 12 failed" + e.getLocalizedMessage());
		} catch (Exception e) {
			Reporter.log("Step 12 failed" + e.getLocalizedMessage());

		}

		// Step 13- RR Page has existing Related CC&B ID

		util.customAlertByJS("Step 13 started");
		try {
			Reporter.log("Step 13 started");
			Reporter.log("Validation rules for Case Record Type - CaseWIwithExistingRelatedCCB");
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact3 = BasePage.getTestDataValue("WiWithCustContactAbacInfo");

			WebElement WIwithContactAbac = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact3 + "')]"));

			util.click(WIwithContactAbac);

			util.click(viewContactEle);
			util.click(editButton);
			util.selectByIndex(4, soxReqInfoEle);

			WebElement ccbCheckboxEle = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwa\"]"));

			if (ccbCheckboxEle.isSelected() == false) {

				util.click(ccbCheckboxEle);
			}

			util.click(cclogSaveButton);
			Thread.sleep(1000);
			Assert.assertTrue(generalErrorMessage.isDisplayed());
			Reporter.log("Step 13 passed");
		}

		catch (AssertionError e) {
			Reporter.log("Step 13 failed" + e.getLocalizedMessage());
		} catch (Exception e) {
			Reporter.log("Step 13 failed" + e.getLocalizedMessage());
		}

		// Step 14 started

		util.customAlertByJS("Step 14 started");
		try {

			Reporter.log("Step 14 started- Closing work item");

			util.switchToTab(0);
			clickOnWorkItems();
			Reporter.log("Clicked on Work Items to get the WI lists");
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiWithCustContactAbacInfo");

			WebElement WIwithoutContactEle = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContactEle);

			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[@value=\"Close Work Item\"]")).click();

			WebElement statusDropdown = driver.findElement(By.xpath("//*[@id=\"cas7\"]"));
			util.selectByIndex(2, statusDropdown);

			WebElement postponeCredit = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmD\"]"));
			util.selectByIndex(2, postponeCredit);

			WebElement subStatus = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmk\"]"));
			util.selectByIndex(1, subStatus);

			WebElement rootCauseCategory = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwY\"]"));
			util.selectByIndex(3, rootCauseCategory);

			WebElement rootCausSUbCategory = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmU\"]"));
			util.selectByIndex(1, rootCausSUbCategory);

			// WebElement cancelReason =
			// driver.findElement(By.xpath("//*[@id=\"00Nq0000000ii3D\"]"));
			// util.selectByIndex(2, cancelReason);

			/*WebElement caseClosureMethod = driver.findElement(By.xpath("//*[@id=\"00Nq0000000hucB\"]"));
			util.selectByIndex(1, caseClosureMethod);*/
			/*
				if (addTOCCBCaseLOgCheckBox.isSelected() == false) {
			
					util.click(addTOCCBCaseLOgCheckBox);
				}*/
			if (createCustContinCCBCheckBox.isSelected() == false) {

				util.click(createCustContinCCBCheckBox);
			}
			// util.selectByIndex(2, custContactTypeDropdown);

			util.click(cclogSaveButton);

			Reporter.log("Step 14 passed");
		} catch (AssertionError e) {

			Reporter.log("Step 14 failed" + e.getLocalizedMessage());
		} catch (Exception e) {
			Reporter.log("Step 14 failed" + e.getLocalizedMessage());
		}

		// Step 15
		util.customAlertByJS("Step 15 started");
		Reporter.log("Step 15 started");
		Reporter.log(
				"User is able to override a customer contact log directly in Salesforce when the log is Errored & Customer Contact Log id exists");

		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			// pass a WI with existing RR and CCB case log.
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("CaseWIwithExistingRelatedCCB");

			WebElement WIwithoutContactEle = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContactEle);
			util.click(viewContactEle);

			util.click(customerContactLogNameEle);
			util.click(editButton);

			util.selectByIndex(4, cclogStatusDropdownEle);

			util.click(cclogSaveButton);
			driver.navigate().refresh();

			String status = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKE_ileinner\"]")).getText();
			Assert.assertTrue(status.contains("Errored"));

			String ccbCuctContID = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKB_ileinner\"]")).getText();
			Assert.assertNotNull(ccbCuctContID);

			Reporter.log("Step 15 passed");
		} catch (AssertionError e) {
			Reporter.log("Step 15 failed" + e.getLocalizedMessage());
		} catch (Exception e) {
			Reporter.log("Step 15 failed" + e.getLocalizedMessage());
		}

		// Step 16

		util.customAlertByJS("Step 16 started");
		Reporter.log("Step 16 started");

		try {
			util.click(editButton);

			WebElement logEntryField = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKC\"]"));

			String logText = logEntryField.getText();

			util.sendKeys(logEntryField, logText.concat(" Updated logs while Testing"));

			WebElement overrideCCBCheckboxEle = driver.findElement(By.xpath("//*[@id=\"00Nq0000001NYg8\"]"));

			if (overrideCCBCheckboxEle.isSelected() == false) {

				overrideCCBCheckboxEle.click();
			}

			WebElement saveBUtton = driver.findElement(By.xpath("//*[@name=\"save\"]"));
			util.click(saveBUtton);
			driver.navigate().refresh();
			String status = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKE_ileinner\"]")).getText();

			// Assert.assertTrue(status.contains("Complete"));
			// status verification as complete in cc&b.

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("CaseWIwithExistingRelatedCCB");

			WebElement WIwithoutContactEle = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContactEle);

			util.click(viewCCBCase);
			util.switchToTab(1);

			driver.switchTo().frame("main");
			driver.switchTo().frame("tabPage");
			String StatusinCCB = driver.findElement(By.xpath("//*[@for=\"CASE_TD_SUMMARY\"]")).getText();
			// Assert.assertTrue(StatusinCCB.contains("Complete"));

			Reporter.log("Status is set as \"Complete\" in CC&B");

			Reporter.log("Step 16 passed- Status=Complete");

		} catch (AssertionError e) {

			Reporter.log("Step 16 failed" + e.getLocalizedMessage());
		} catch (Exception e) {

			Reporter.log("Step 16 failed" + e.getLocalizedMessage());
		}

		// STep 17 started

		util.customAlertByJS("Step 17 started");
		Reporter.log("Step 17 started- Email notification to be sent on errored contacts");

		// 17.1. RR where Related CC&B id does not exist.Erroring an existing RR
		// customer contact.

		try {
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			// pass WI with complete RR and CCB

			String WIwithoutCustCOntact11 = BasePage.getTestDataValue("CaseWIwithExistingRelatedCCB");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact11 + "')]"));

			util.click(WIwithoutContact1);
			util.click(viewContactEle);
			util.click(editButton);
			Select dropdown = new Select(driver.findElement(By.id("00Nq0000000ZKwf")));
			dropdown.selectByValue("Premise");
			Select soxReqInfoDropdown = new Select(soxReqInfoEle);
			soxReqInfoDropdown.selectByIndex(4);
			WebElement premiseID = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwd\"]"));
			util.sendKeys(premiseID, "1010101010");

			// util.selectByIndex(4, RRstatusDropDownEle);
			util.selectByIndex(3, RRstatusDropDownEle);
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.navigate().refresh();

			String statusMsg = driver.findElement(By.xpath("//*[@id=\"00No0000008cgnu_ileinner\"]")).getText();
			Assert.assertTrue(statusMsg.contains("Errored"));
			Reporter.log("Step 17.1 passed");

			// 17.2. Erroring a CCb log

			util.click(customerContactLogNameEle);
			util.click(editButton);
			util.selectByIndex(4, cclogStatusDropdownEle);

			util.click(cclogSaveButton);
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.navigate().refresh();

			String status1 = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKE_ileinner\"]")).getText();
			Assert.assertTrue(status1.contains("Errored"));
			Reporter.log("Step 17.2 passed");

			// step 17.3 needs a Wi without any RR.Erroring a new RR while
			// creating it.

			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("FreshWiWithNORelatedRecord");

			WebElement WIwithoutContactEle = driver.findElement(By.xpath("//*[contains(text(),'00440260')]"));

			util.click(WIwithoutContactEle);

			driver.findElement(By.xpath("//*[@name=\"new_customer_contact\"]")).click();

			enterValue(driver, customerContactInputEle, BasePage.getTestDataValue("CustomerContactID"));
			Select dropdown1 = new Select(driver.findElement(By.id("00Nq0000000ZKwf")));
			dropdown1.selectByValue("Premise");
			// Select dropdown_2 = new
			// Select(driver.findElement(By.id("00Nq0000000ZKwg")));
			Select soxReqInfoDropdown1 = new Select(soxReqInfoEle);
			soxReqInfoDropdown1.selectByIndex(4);
			WebElement premiseID1 = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwd\"]"));
			util.sendKeys(premiseID1, "1010101010");

			util.selectByIndex(4, RRstatusDropDownEle);

			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();

			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.navigate().refresh();

			String statusMsg1 = driver.findElement(By.xpath("//*[@id=\"00No0000008cgnu_ileinner\"]")).getText();
			Assert.assertTrue(statusMsg1.contains("Errored"));
			Reporter.log("Step 17.3 passed. Record status is Errored");

			// 17.4 erroring ccb log

			util.click(customerContactLogNameEle);
			util.click(editButton);
			util.selectByIndex(4, cclogStatusDropdownEle);
			util.click(cclogSaveButton);
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.navigate().refresh();

			String status11 = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKE_ileinner\"]")).getText();
			Assert.assertTrue(status11.contains("Errored"));

			Reporter.log("Step 17.4 passed");
			Reporter.log("Step 17 passed");
		} catch (AssertionError e) {

			Reporter.log("Step 17 failed" + e.getLocalizedMessage());
		} catch (Exception e) {

			Reporter.log("Step 17 failed" + e.getLocalizedMessage());
		}

		// Step 18

		util.customAlertByJS(" Step 18 started- logging out");
		Reporter.log("Step 18 started- logging out");
		util.switchToTab(0);
		WebElement logOutMenu = driver.findElement(By.xpath("//*[@id=\"userNav-arrow\"]"));
		util.click(logOutMenu);
		WebElement logoutButton = driver.findElement(By.xpath("//*[@title=\"Logout\"]"));
		util.click(logoutButton);
		Reporter.log(" logout done successfully");
		return this;

	}
}
