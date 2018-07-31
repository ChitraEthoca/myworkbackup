package com.pge.COC.pageObjects;

import java.util.HashMap;
import java.util.Hashtable;

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

public class CustomerContactPage_760_763_768 extends BasePage {
	WebDriver driver;
	HashMap<String, String> localData;
	ObjectRead objectRead;

	private By usernameFldLocator = By.id("username");
	private By passwordFldLocator = By.id("password");
	// WI with data like customer contact and ABAC.

	private By AsBilledAmountLocator = By.id("00No0000008cglP");
	private By AsCorrectedAmountLocator = By.id("00No0000008cglT");
	private By BilledDateFromLocator = By.id("00No0000008cglR");
	private By BilledDateToLocator = By.id("00No0000008cglS");
	private By CustomerAjAmountLocator = By.id("Case.00Nq0000001eZPe-_help");

	private By customerContactInputEle = By.xpath("//*[@id='00Nq0000000ZKwc']");

	@FindBy(xpath = "//select[@id=\"00Nq0000000iAXY\"]")
	private WebElement soxReqInfoEle;
	// @FindBy(xpath = "(//a[contains(text(),'RR')])[position()=1]")
	@FindBy(xpath = "(//a[contains(text(),'RR')]")
	private WebElement viewContactEle;
	@FindBy(xpath = "//*[@name=\"view_cc_b_customer_contact\"]")
	private WebElement viewCCBContactBtnEle;
	// @FindBy(xpath = "(//*[@value=' Edit '])[position()=1]")
	@FindBy(xpath = "(//*[@value=' Edit ']")
	private WebElement editWIBtnEle;
	@FindBy(xpath = "//*[@name=\"save\"]")
	private WebElement saveBtnEle;

	@FindBy(xpath = "//*[contains(text(),'Work Items about to ')]")
	private WebElement WIsaveSuccessfullConfirmEle;
	@FindBy(xpath = "//*[@id=\"00Nq00000017pTa_chkbox\"]")
	private WebElement customerContactLogWithABACCheckboxele;

	// CCB login details
	@FindBy(xpath = "//*[@id=\"userId\"]")
	private WebElement CCBUserIdEle;
	@FindBy(xpath = "//input[@id=\"password\"]")
	private WebElement CCBPasswordEle;
	@FindBy(xpath = "//input[@id=\"loginButton\"]")
	private WebElement CCBLoginBtn;

	@FindBy(xpath = "//div[@id=\"00Nq0000000ZKwc_ileinner\"]")
	private WebElement customerIdMainPageEle;

	@FindBy(xpath = "//*[@id=\"PER_ID\"]")
	private WebElement personIDCCBScreenEle;

	@FindBy(xpath = "(//*[contains(text(),'Edit')])[position()=3]")
	private WebElement relatedRecordEditlinkEle;

	@FindBy(xpath = "//*[@id=\"00Nq00000017pTZ\"]")
	private WebElement correctionCheckBoxEle;

	// SOX information field locators.
	@FindBy(xpath = "//*[@id=\"00No0000008cglY\"]")
	private WebElement billedToAmountEle;
	@FindBy(xpath = "//*[@id=\"00No0000008cglX\"]")
	private WebElement billedFromDateEle;
	@FindBy(xpath = "//*[@id=\"00No0000008cglZ\"]")
	private WebElement billedToDateEle;

	@FindBy(xpath = "(//td[@class=\" dataCell  \"])[position()=1]")
	private WebElement CustContactLogEntryEle;

	@FindBy(xpath = "(//*[contains(text(),'RR-')])[position()=1]")
	private WebElement customerRelatedRecNamEle;

	@FindBy(xpath = "(//*[contains(text(),'CC LOG-')])[position()=1]")
	private WebElement customerContactLogNameEle;

	@FindBy(xpath = "//*[@name=\"view_cc_b_customer_contact\"]")
	private WebElement viewCCBCustomerContactEle;

	@FindBy(xpath = "(//*[@aria-labelledby=\"D_L_CC_LOG_CONTENT\"])[position()=1]//..")
	private WebElement CCLogStatementEle;
	@FindBy(xpath = "//*[contains(text(),'CC LOG-')]")
	private WebElement CCLOGNumberEle;
	@FindBy(xpath = "//*[contains(text(),'Complete')]")
	private WebElement CCLOGEntryTextEle;

	@FindBy(xpath = "//*[@title='Log']")
	private WebElement LogTabCCBele;

	public CustomerContactPage_760_763_768(WebDriver driver, HashMap<String, String> localData) throws Exception {
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

	public CustomerContactPage_760_763_768 loginIntoApplication(Hashtable<String, String> testData) throws Exception {

		Reporter.log("Step 1 Started- Loginto application");
		try {
			String username = getTestDataValue("CSR_User");
			String password = getTestDataValue("CSR_Password");
			enterUsername(driver, username);
			enterPassword(driver, password);
			driver.findElement(By.xpath("//*[@id=\"Login\"]")).click();
			// COC.webAdaptor(Actions.setValueToXpathAndClick, "LoginBtn");
			Thread.sleep(2000);

			Reporter.log("Step 1 completed. Successfully logged into application");

		} catch (Exception E) {
			System.out.println(E);
			Reporter.log("Step 1- login failed" + E);
		}
		return this;
	}

	public CustomerContactPage_760_763_768 clickOnWorkItems() throws Exception {

		// driver.findElement(By.xpath("//*[@title=\"Home Tab -
		// Selected\"]")).click();

		GenericUtilities util = new GenericUtilities();

		WebElement homebutton = driver.findElement(By.xpath("//*[@href=\"/home/home.jsp\"]"));

		util.click(homebutton);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[contains(text(),'Work Items')]")).click();

		return this;
	}

	public CustomerContactPage_760_763_768 selectMyWI() throws Exception {
		Select dropdown = new Select(driver.findElement(By.id("fcf")));
		dropdown.selectByIndex(3);
		return this;
	}

	@SuppressWarnings("deprecation")
	public CustomerContactPage_760_763_768 navigateToWI_AddContact1(Hashtable<String, String> testData)
			throws Exception {

		GenericUtilities util = new GenericUtilities();
		// Step 2
		// Reporter.log("Step 2 started");

		try {

			util.customAlertByJS("Step 2 started- Opening work item");
			Reporter.log("Step 2 started-Opening work item");
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WIWithCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);

			Reporter.log("Adding ABAC info");

			driver.findElement(By.xpath("//*[@title=\"Edit\"]")).click();
			Thread.sleep(2000);
			enterKeys(driver, AsBilledAmountLocator, "200");
			Thread.sleep(2000);
			enterKeys(driver, AsCorrectedAmountLocator, "100");
			Thread.sleep(2000);
			enterKeys(driver, BilledDateFromLocator, "6/15/2018");
			Thread.sleep(2000);
			enterKeys(driver, BilledDateToLocator, "6/18/2018");
			Thread.sleep(2000);
			// enterKeys(driver, CustomerAjAmountLocator, "190");
			Thread.sleep(2000);
			util.sendKeys(billedToAmountEle, "201");
			Thread.sleep(2000);
			util.sendKeys(billedFromDateEle, "6/14/2018");
			Thread.sleep(2000);
			util.click(billedToAmountEle);
			Thread.sleep(2000);
			util.sendKeys(billedToDateEle, "6/18/2018");
			Thread.sleep(2000);
			util.click(billedToAmountEle);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();

			Reporter.log("Step 2 completed- Work item saved successfully");

		} catch (

		Exception e) {

			Reporter.log("Step 2 failed");

		}

		// Step 3

		Reporter.log("Step 3 started- closing work item.");

		try {

			// util.switchToTab(0);
			// clickOnWorkItems();
			Reporter.log("Clicked on Work Items to get the WI lists");
			// selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WIWithCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@value=\"Close Work Item\"]")).click();

			WebElement statusDropdown = driver.findElement(By.xpath("//*[@id=\"cas7\"]"));
			util.selectByIndex(1, statusDropdown);

			WebElement postponeCredit = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmD\"]"));
			util.selectByIndex(2, postponeCredit);

			WebElement subStatus = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmk\"]"));
			util.selectByIndex(1, subStatus);

			WebElement rootCauseCategory = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwY\"]"));
			util.selectByIndex(3, rootCauseCategory);

			WebElement rootCausSUbCategory = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmU\"]"));
			util.selectByIndex(1, rootCausSUbCategory);

			WebElement cancelReason = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ii3D\"]"));
			util.selectByIndex(2, cancelReason);

			WebElement CustomerContactClass = driver.findElement(By.xpath("//*[@id=\"00No0000008cglg\"]"));

			util.selectByIndex(2, CustomerContactClass);

			WebElement CustContactType = driver.findElement(By.xpath("//*[@id=\"00No0000008cgll\"]"));

			util.selectByIndex(6, CustContactType);
			WebElement SOXReqInfo = driver.findElement(By.xpath("//*[@id=\"00Nq0000000iAXV\"]"));

			util.selectByIndex(1, SOXReqInfo);

			WebElement WICloseSaveBtn = driver.findElement(By.xpath("//*[@value=\" Save \"]"));

			util.click(WICloseSaveBtn);

			Thread.sleep(2000);
			Reporter.log("Step 3 passed - Work item closed successfully");
		}

		catch (Exception e) {
			Reporter.log(" Step 3 failed :" + e.getLocalizedMessage());
		}

		// Step 4

		util.customAlertByJS("Step 4 started");
		Reporter.log("Step 4 started");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WIWithCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);
			Thread.sleep(1000);
			util.scrollIntoView(customerRelatedRecNamEle);
			Thread.sleep(1000);
			Assert.assertTrue(customerRelatedRecNamEle.isDisplayed());
			Thread.sleep(1000);

			util.click(customerRelatedRecNamEle);

			util.scrollIntoView(customerContactLogNameEle);

			Assert.assertTrue(customerContactLogNameEle.isDisplayed());

			Reporter.log(" Related records verification done");
			Reporter.log("Step 4 passed");
			Reporter.log("Verify Customer Contact Related is Created");

		} catch (Exception e) {
			Reporter.log("Step 4 failed" + e.getMessage());

		}

		// Step 5
		util.customAlertByJS(" Step 5 started");
		Reporter.log("Step 5 started");
		try {

			String SFDCCustContactPersonID = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwc_ileinner\"]"))
					.getText();
			viewCCBCustomerContactEle.click();
			try {
				util.switchToTab(1);
				util.CCBLogin(CCBUserIdEle, CCBPasswordEle, CCBLoginBtn);
				Reporter.log("CCB logged in successfully");
			} catch (Exception e) {
			}

			driver.switchTo().defaultContent();
			driver.switchTo().frame("main");
			driver.switchTo().frame("tabPage");
			String CCBCustContactPersonID = driver.findElement(By.xpath("//*[@id=\"PER_ID\"]")).getText();
			util.switchToTab(0);
			driver.switchTo().frame("CC_LOG_GRID");
			Assert.assertEquals(SFDCCustContactPersonID.trim(), CCBCustContactPersonID.trim());
			Reporter.log("SFDC and CC&B contact entry verification done");

			Reporter.log("STep 5 passed");
			Reporter.log("Verified Csutomer Contact on CC&B");
		} catch (AssertionError e) {
			Reporter.log("Step 5 failed" + e);

		}

		catch (Exception e) {
			Reporter.log("Step 5 failed" + e);

		}
		util.switchToTab(0);
		// step 6

		util.customAlertByJS("Step 6 started");
		Reporter.log("Step 6 started");
		Reporter.log(" Caselog verification started");
		try {

			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WIWithCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);
			util.click(customerRelatedRecNamEle);
			Thread.sleep(1000);
			util.scrollIntoView(customerContactLogNameEle);
			Assert.assertTrue(customerContactLogNameEle.isDisplayed());
			String CCLOG1 = util.getText(CCLOGNumberEle);
			Reporter.log(" CCLOG Number is : " + CCLOG1);
			Reporter.log(" Step 6 passed");
			Reporter.log("Case Log is added to WI in SFDC");

		} catch (Exception e) {

			Reporter.log(" Step 6 failed" + e.getLocalizedMessage());
		}

		// Step 7

		util.customAlertByJS(" Step 7 started");
		Reporter.log("Step 7 started");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WIWithCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);
			Thread.sleep(1000);

			util.click(customerRelatedRecNamEle);

			util.scrollIntoView(customerContactLogNameEle);

			Assert.assertTrue(customerContactLogNameEle.isDisplayed());

			// String ABACLogEntry = CustContactLogEntryEle.getText();
			// driver.navigate().back();
			viewCCBCustomerContactEle.click();
			util.switchToTab(1);
			
			/*driver.switchTo().frame("main");
			driver.switchTo().frame("tabPage");
			driver.switchTo().frame("CC_L OG_GRID");
			String CCBLOg = util.getText(CCLogStatementEle);
			Assert.assertEquals(ABACLogEntry.trim(), CCBLOg.trim());*/
			
			Reporter.log("SFDC and CC&B log entry verification done");
			 Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());
			Reporter.log("Step 7 completed successfully");
			Reporter.log("Cae Log is added on CC&B");

		}

		catch (Exception e) {

			Reporter.log("Step 7 failed" + e.getLocalizedMessage());
		}

		// Step 8 started

		util.customAlertByJS("Step 8 started");
		Reporter.log("Step 8 started-Opening cancelled item with no customer contact");

		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiCanceledWithNOCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);

			Thread.sleep(1000);
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Work Item Detail')]")).isDisplayed());

			Reporter.log("Step 8 completed- Work item opened successfully");
			Thread.sleep(1000);
		}

		catch (Exception e) {

			Reporter.log("Step 8 failed- Unable to open work item");

		}

		// Step 9 started

		util.customAlertByJS("Step 9 started");
		Reporter.log("Step 9 started");
		try {

			driver.findElement(By.xpath("//*[@name=\"new_customer_contact\"]")).click();
			
			Thread.sleep(1000);
			enterValue(driver, customerContactInputEle, BasePage.getTestDataValue("CustomerContactID"));
			Select dropdown = new Select(driver.findElement(By.id("00Nq0000000ZKwf")));
			dropdown.selectByValue("SmartMeter Related");
			Select dropdown_1 = new Select(driver.findElement(By.id("00Nq0000000ZKwg")));
			dropdown_1.selectByValue("SMOO EMR Meters will remain at premise");
			Select soxReqInfoDropdown = new Select(soxReqInfoEle);
			soxReqInfoDropdown.selectByIndex(1);
			WebElement serviceAgreementID = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwe\"]"));
			util.sendKeys(serviceAgreementID, "12345");

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"00No0000008cgnn\"]"))
					.sendKeys(BasePage.getTestDataValue("AdditionalComments"));
			driver.findElement(By.xpath("//*[@id=\"00Nq0000000iAXX\"]")).sendKeys("Service Point");
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Thread.sleep(1000);

			String serviceAgreementID2 = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwe_ileinner\"]"))
					.getText();
			// Assert.assertTrue(serviceAgreementID2 == "12345"); 

			util.scrollIntoView(customerContactLogNameEle);
			Assert.assertTrue(customerContactLogNameEle.isDisplayed());
			Reporter.log(" Step 9 completed successfully- new customer contact added");

		}

		catch (AssertionError e) {
			Reporter.log("Step 9 failed" + e.getLocalizedMessage());
		}

		catch (Exception e) {
			Reporter.log("Step 9 failed" + e.getLocalizedMessage());
		}

		// Step 10 started

		util.customAlertByJS("Step 10 started");
		Reporter.log("Step 10 started");

		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WiCanceledWithNOCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);
			Thread.sleep(1000);

			util.click(customerRelatedRecNamEle);

			util.scrollIntoView(customerContactLogNameEle);

			Assert.assertTrue(customerContactLogNameEle.isDisplayed());

			String ABACLogEntry = CustContactLogEntryEle.getText();

			util.click(CustContactLogEntryEle);

			Thread.sleep(2000);

			WebElement CClogLogIDELe = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKD_ileinner\"]"));

			util.scrollIntoView(CClogLogIDELe);

			String CClogLogID = CClogLogIDELe.getText();

			driver.navigate().back();
			viewCCBCustomerContactEle.click();
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
			Thread.sleep(2000);
			driver.switchTo().frame("main");
			Thread.sleep(2000);
			driver.switchTo().frame("tabPage");
			Thread.sleep(2000);

			String CCBLOGID = driver.findElement(By.xpath("//*[@name=\"CC_LOG$CC_LOG_ID\"]")).getText();

			Assert.assertEquals(CClogLogID, CCBLOGID);

			Thread.sleep(2000);
			Reporter.log("Step 10 passed");
			Reporter.log("Viewed CC&B Customer Contact");
		}

		catch (AssertionError e) {

			Reporter.log("Step 10 failed" + e.getLocalizedMessage());
		}

		catch (Exception e) {
			
			Reporter.log("Step 10 failed" + e.getLocalizedMessage());
		}

		// Step 11 started

		util.customAlertByJS("Step 11 started");
		Reporter.log("Step 11 started");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiCanceledWithNOCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);

			WebElement editBtn = driver.findElement(By.xpath("//*[@title=\"Edit\"]"));
			util.click(editBtn);

			Thread.sleep(2000);
			enterKeys(driver, AsBilledAmountLocator, "2000");
			enterKeys(driver, AsCorrectedAmountLocator, "1000");
			enterKeys(driver, BilledDateFromLocator, "6/18/2018");
			enterKeys(driver, BilledDateToLocator, "6/18/2018");
			// enterKeys(driver, CustomerAjAmountLocator, "196");

			util.sendKeys(billedToAmountEle, "201");
			Thread.sleep(1000);
			util.sendKeys(billedFromDateEle, "6/18/2018");
			Thread.sleep(1000);
			util.click(billedToAmountEle);
			Thread.sleep(1000);
			util.sendKeys(billedToDateEle, "6/18/2018");
			billedToAmountEle.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Thread.sleep(2000);

			Reporter.log(" Step 11 passed");
			Reporter.log("Saved WI");

		}

		catch (Exception e) {
			System.out.println(e);
			Reporter.log("Step 11 failed" + e.getLocalizedMessage());

		}

		// Step 12 started

		util.customAlertByJS("Step 12 started");
		Reporter.log("Step 12 started");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WiCanceledWithNOCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);
			Thread.sleep(1000);

			util.click(customerRelatedRecNamEle);

			util.scrollIntoView(customerContactLogNameEle);

			Assert.assertTrue(customerContactLogNameEle.isDisplayed());

			String ABACLogEntry = CustContactLogEntryEle.getText();

			util.click(viewCCBCustomerContactEle);
			util.switchToTab(1);
			
			/*driver.switchTo().frame("main");
			driver.switchTo().frame("tabPage");
			driver.switchTo().frame("CC_LOG_GRID");
			String CCBLOg = util.getText(CCLogStatementEle);
			Assert.assertEquals(ABACLogEntry.trim(), CCBLOg.trim());*/
			
			Reporter.log("SFDC and CC&B log entry verification done");
			Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());
			Reporter.log("Step 12 completed successfully");

		} catch (AssertionError e) {

			Reporter.log("Step 12 failed" + e.getLocalizedMessage());

		} catch (Exception e) {

			Reporter.log("Step 12 failed" + e.getLocalizedMessage());

		}

		// Step 13 started

		util.customAlertByJS("Step 13 started");
		Reporter.log("Step 13 started-Opening work item");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WiCanceledWithNOCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);

			util.click(customerRelatedRecNamEle);
			String CCLOG = util.getText(CCLOGNumberEle);
			Reporter.log(" CCLOG Number is : " + CCLOG);
			util.click(CCLOGNumberEle);
			util.scrollIntoView(customerContactLogWithABACCheckboxele);
			Thread.sleep(2000);
			Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());

			Reporter.log("Step 13 passed");
		} catch (AssertionError e) {
			Reporter.log(" Assertion failed customerContactLogWithABACCheckboxele IS NOT CHECKED" + e);
			Reporter.log("Step 13 failed" + e.getLocalizedMessage());

		} catch (Exception e) {

			Reporter.log(" Step 13 failed" + e.getLocalizedMessage());
		}

		// Step 14
		// Requires work item with no customer contact but should have abac
		// info.

		util.customAlertByJS(" Step 14 started");
		Reporter.log("Step 14 started");
		Reporter.log("Completed WI");
		try {
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WICompletedRedirectWithNoCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);
			Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Work Item Detail')]")).isDisplayed());

			Reporter.log(" Work item opened successfully");
			Reporter.log(" Step 14 passed");
		} catch (Exception e) {

			Reporter.log("Step 14 failed" + e.getLocalizedMessage());
		}

		// Step 15

		util.customAlertByJS(" Step 15 started");
		Reporter.log("Step 15 started");
		try {

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@name=\"new_customer_contact\"]")).click();
			try {
				driver.switchTo().alert().accept();
			} catch (Exception e) {
				System.out.println("No alert found");
			}
			Thread.sleep(1000);
			WebElement addToCCBCaseLogCHeckBox = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwa\"]"));

			if (addToCCBCaseLogCHeckBox.isSelected() == false) {

				util.click(addToCCBCaseLogCHeckBox);
			}

			enterValue(driver, customerContactInputEle, BasePage.getTestDataValue("CustomerContactID"));
			Select dropdown = new Select(driver.findElement(By.id("00Nq0000000ZKwf")));
			dropdown.selectByValue("Billing Inquiry Note");
			Select dropdown_1 = new Select(driver.findElement(By.id("00Nq0000000ZKwg")));
			dropdown_1.selectByValue("SM COM Delayed Bill Letter");
			Select soxReqInfoDropdown = new Select(soxReqInfoEle);
			soxReqInfoDropdown.selectByIndex(2);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"00No0000008cgnn\"]"))
					.sendKeys(BasePage.getTestDataValue("AdditionalComments"));
			// driver.findElement(By.xpath("//*[@id=\"00Nq0000000iAXX\"]")).sendKeys("Service
			// Point");

			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WICompletedRedirectWithNoCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);
			Thread.sleep(1000);
			util.scrollIntoView(customerRelatedRecNamEle);
			Thread.sleep(1000);
			Assert.assertTrue(customerRelatedRecNamEle.isDisplayed());
			Thread.sleep(1000);

			util.click(customerRelatedRecNamEle);

			util.scrollIntoView(customerContactLogNameEle);

			Assert.assertTrue(customerContactLogNameEle.isDisplayed());
			Assert.assertTrue(CCLOGEntryTextEle.isDisplayed());
			Reporter.log(" Log entry verification done");

		} catch (AssertionError e) {

			Reporter.log(" Step 15 failed" + e.getLocalizedMessage());
		}

		catch (Exception e) {
			Reporter.log("Step 15 failed" + e.getLocalizedMessage());
		}

		// Step 16

		util.customAlertByJS("Step 16 Execution started");
		Reporter.log(" Step 16 executed started");
		Reporter.log("View CC&B Customer Contact");
		util.switchToTab(0);
		try {
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WICompletedRedirectWithNoCustContact");
			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);

			util.click(customerRelatedRecNamEle);
			Thread.sleep(2000);
			util.click(CCLOGNumberEle);
			String CClogLogID1 = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKD_ileinner\"]")).getText();
			driver.navigate().back();
			util.click(viewCCBContactBtnEle);
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
			Thread.sleep(2000);
			driver.switchTo().frame("main");
			Thread.sleep(2000);
			driver.switchTo().frame("tabPage");
			Thread.sleep(2000);

			String CCBLOGID1 = driver.findElement(By.xpath("//*[@name=\"CC_LOG$CC_LOG_ID\"]")).getText();

			Assert.assertEquals(CClogLogID1, CCBLOGID1);

			Reporter.log(" Log id " + CCBLOGID1 + " verification successful");
			Reporter.log("Step 16 passed");
		}

		catch (AssertionError e) {

			Reporter.log("Step 16- Assertions have failed :" + e.getLocalizedMessage());

		}

		catch (Exception e) {

			Reporter.log("Step 16 has failed :" + e.getLocalizedMessage());

		}

		// Step 17
		// Step 17 Scenario- Customer contact related record exists. Edit ABAC
		// info 

		util.customAlertByJS("Step 17 started");
		Reporter.log(" Step 17 Execution started");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();

			String WIwithoutCustCOntact2 = BasePage.getTestDataValue("WICompletedRedirectWithNoCustContact");

			WebElement WIwithoutContact3 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact2 + "')]"));

			util.click(WIwithoutContact3);
			Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Work Item Detail')]")).isDisplayed());

			Reporter.log(" Work item opened successfully");

			util.click(editWIBtnEle);
			Thread.sleep(2000);
			enterKeys(driver, AsBilledAmountLocator, "2000");
			enterKeys(driver, AsCorrectedAmountLocator, "1000");
			enterKeys(driver, BilledDateFromLocator, "6/18/2018");
			enterKeys(driver, BilledDateToLocator, "6/18/2018");
			// enterKeys(driver, CustomerAjAmountLocator, "196");

			util.sendKeys(billedToAmountEle, "201");
			util.sendKeys(billedFromDateEle, "6/18/2018");
			util.click(billedToAmountEle);
			Thread.sleep(2000);
			util.sendKeys(billedToDateEle, "6/18/2018");
			billedToAmountEle.click();
			Thread.sleep(1000);

			if (correctionCheckBoxEle.isSelected() == false) {
				correctionCheckBoxEle.click();

			}
			util.click(saveBtnEle);
			Thread.sleep(2000);
			Reporter.log("ABAC information saved successfully");
			Reporter.log(" Step 17 passed");
		} catch (AssertionError e) {

			Reporter.log("Step 17 failed" + e.getLocalizedMessage());
		} catch (Exception e) {

			Reporter.log("Step 17 failed" + e.getLocalizedMessage());
		}

		// Step 18

		util.customAlertByJS("Step 18 execution started");
		Reporter.log("Step 18 execution started");
		try {
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();

			String WIwithoutCustCOntact2 = BasePage.getTestDataValue("WICompletedRedirectWithNoCustContact");

			WebElement WIwithoutContact3 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact2 + "')]"));

			util.click(WIwithoutContact3);

			util.click(customerRelatedRecNamEle);
			Reporter.log("Related record name clicked");

			Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"CF00No0000008cgkt_ileinner\"]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'CC LOG-')]")).isDisplayed());
			Reporter.log("Related record verification done");
			// util.click(CCLOGNumberEle);
			Reporter.log(" Step 18 execution completed successfully");

		} catch (AssertionError e) {

			Reporter.log("Step 18 failed" + e.getLocalizedMessage());
		} catch (Exception e) {

			Reporter.log("Step 18 failed" + e.getLocalizedMessage());
		}

		// Step 19 started

		util.customAlertByJS("Step 19 started");

		try {
			Reporter.log("Step 19 started");
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WICompletedRedirectWithNoCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);

			util.click(customerRelatedRecNamEle);
			String CCLOG = util.getText(CCLOGNumberEle);
			Reporter.log(" CCLOG Number is : " + CCLOG);
			util.click(CCLOGNumberEle);

			util.scrollIntoView(customerContactLogWithABACCheckboxele);
			Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());

			Reporter.log("Step 19 passed");
		} catch (AssertionError e) {

			Reporter.log(" Assertion failed customerContactLogWithABACCheckboxele IS NOT CHECKED" + e);
			Reporter.log("Step 19 failed" + e.getLocalizedMessage());

		} catch (Exception e) {

			Reporter.log(" Step 19 failed" + e.getLocalizedMessage());
		}

		// Step 22 started- Error status to pending

		try {

			Reporter.log(" Step 22 started");
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WIWithErroredContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);

			WebElement editRREle = driver.findElement(By.xpath("//*[@class=\"actionLink\"]"));
			util.click(editRREle);
			WebElement RRElementStatudropDown = driver.findElement(By.xpath("//*[@id=\"00No0000008cgnu\"]"));
			util.selectByIndex(1, RRElementStatudropDown);
			WebElement saveRR = driver.findElement(By.xpath("//*[@name=\"save\"]"));
			util.click(saveRR);

			WebElement erorMessage = driver.findElement(By.xpath("//*[contains(text(),'Error: Invalid Data. ')]"));

			Assert.assertTrue(erorMessage.isDisplayed());
			Reporter.log("Step 22 passed");
		} catch (AssertionError e) {
			Reporter.log("Step 22 failed" + e.getLocalizedMessage());
		}

		// Step 23 started- Manual transition from Error status to Completed

		util.customAlertByJS("Step 23 started");

		Reporter.log("Step 23 started");
		try {

			WebElement RRElementStatudropDown = driver.findElement(By.xpath("//*[@id=\"00No0000008cgnu\"]"));
			util.selectByIndex(2, RRElementStatudropDown);
			WebElement saveRR = driver.findElement(By.xpath("//*[@name=\"save\"]"));

			util.click(saveRR);

			WebElement erorMessage = driver.findElement(By.xpath("//*[contains(text(),'Error: Invalid Data. ')]"));

			Assert.assertTrue(erorMessage.isDisplayed());

			Reporter.log("Step 23 passed");
		} catch (Exception e) {

			Reporter.log("Step 23 failed");
		}

		// Step 24 started- Manual transition from Error status to in progress

		util.customAlertByJS("Step 24 started");

		Reporter.log("Step 24 started");
		try {

			WebElement RRElementStatudropDown = driver.findElement(By.xpath("//*[@id=\"00No0000008cgnu\"]"));
			util.selectByIndex(3, RRElementStatudropDown);
			WebElement saveRR = driver.findElement(By.xpath("//*[@name=\"save\"]"));
			util.click(saveRR);

			Reporter.log("Step 24 passed");
		} catch (Exception e) {

			Reporter.log("Step 24 failed");
		}

		// Step 25 started
		util.customAlertByJS("Step 25 started");
		Reporter.log("Step 25 started");

		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WIWithErroredContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);

			WebElement editRREle = driver.findElement(By.xpath("//*[@class=\"actionLink\"]"));
			util.click(editRREle);

			WebElement customerContactPersonIDEle = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwc\"]"));
			util.sendKeys(customerContactPersonIDEle, "4506290234");

			WebElement saveRR = driver.findElement(By.xpath("//*[@name=\"save\"]"));
			util.click(saveRR);
			Thread.sleep(2000);
			driver.navigate().refresh();

			util.click(customerRelatedRecNamEle);
			Thread.sleep(2000);

			WebElement finalStatueEle = driver.findElement(By.xpath("//*[@id=\"00No0000008cgnu_ileinner\"]"));

			String status = finalStatueEle.getText();
			status.contains("Complete");

			Reporter.log("Step 25 passed");
		} catch (AssertionError e) {
			Reporter.log("Step 25 failed" + e.getLocalizedMessage());
		}

		return this;

	}
}
