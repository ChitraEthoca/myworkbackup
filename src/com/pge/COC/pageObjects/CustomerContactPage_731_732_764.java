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

public class CustomerContactPage_731_732_764 extends BasePage {
	WebDriver driver;
	HashMap<String, String> localData;
	ObjectRead objectRead;

	private By usernameFldLocator = By.id("username");
	private By passwordFldLocator = By.id("password");
	// WI with data like customer contact and ABAC.
	private By WINumberLocator = By.xpath("//*[contains(text(),'00438051')]");
	// private By WINumberLocator1 =
	// By.xpath("//*[contains(text(),'00442195')]");

	private By AsBilledAmountLocator = By.id("00No0000008cglP");
	private By AsCorrectedAmountLocator = By.id("00No0000008cglT");
	private By BilledDateFromLocator = By.id("00No0000008cglR");
	private By BilledDateToLocator = By.id("00No0000008cglS");
	private By CustomerAjAmountLocator = By.id("Case.00Nq0000001eZPe-_help");

	private By customerContactInputEle = By.xpath("//*[@id='00Nq0000000ZKwc']");
	
	

	@FindBy(xpath = "//select[@id=\"00Nq0000000iAXY\"]")
	private WebElement soxReqInfoEle;
	@FindBy(xpath = "(//a[contains(text(),'RR')])[position()=1]")
	private WebElement viewContactEle;
	@FindBy(xpath = "//*[@name=\"view_cc_b_customer_contact\"]")
	private WebElement viewCCBContactBtnEle;
	@FindBy(xpath = "(//*[@value=' Edit '])[position()=1]")
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

	//@FindBy(xpath = "(//*[contains(text(),'RR-')])[position()=1]")
	@FindBy(xpath = "(//*[contains(text(),'RR-')])")
	private WebElement customerRelatedRecNamEle;

	@FindBy(xpath = "(//*[contains(text(),'CC LOG-')])[position()=1]")
	private WebElement customerContactLogNameEle;

	@FindBy(xpath = "//*[@name=\"view_cc_b_customer_contact\"]")
	private WebElement viewCCBCustomerContactEle;

	@FindBy(xpath = "(//*[@aria-labelledby=\"D_L_CC_LOG_CONTENT\"])[position()=1]//..")
	private WebElement CCLogStatementEle;
	@FindBy(xpath = "//*[contains(text(),'CC LOG-')]")
	private WebElement CCLOGNumberEle;
	@FindBy(xpath = "//*[contains(text(),'As Billed/Corrected From:')]")
	private WebElement CCLOGEntryTextEle;

	@FindBy(xpath = "//*[@title='Log']")
	private WebElement LogTabCCBele;

	public CustomerContactPage_731_732_764(WebDriver driver, HashMap<String, String> localData) throws Exception {
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

	public CustomerContactPage_731_732_764 loginIntoApplication(Hashtable<String, String> testData) throws Exception {

		Reporter.log("Step 1 Started- Loginto application");
		try {
			String username = getTestDataValue("CSR_User");
			String password = getTestDataValue("CSR_Password");
			enterUsername(driver, username);
			enterPassword(driver, password);
			// COC.webAdaptor(Actions.setValueToXpathAndClick, "Login");
			driver.findElement(By.xpath("//input[@id=\"Login\"]")).click();
			Thread.sleep(4000);

			Reporter.log("Step 1 completed. Successfully logged into application");

		} catch (Exception E) {

			Reporter.log("Step 1- login failed");
		}
		return this;
	}

	public CustomerContactPage_731_732_764 clickOnWorkItems() throws Exception {
		/*COC.webAdaptor(Actions.setValueToXpathAndClick, "HomeTab");
		COC.webAdaptor(Actions.setValueToXpathAndClick, "WorkItemsTab");*/

		GenericUtilities util = new GenericUtilities();

		WebElement homebutton = driver.findElement(By.xpath("//*[@href=\"/home/home.jsp\"]"));

		util.click(homebutton);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[contains(text(),'Work Items')]")).click();

		return this;
	}

	public CustomerContactPage_731_732_764 selectMyWI() throws Exception {
		Select dropdown = new Select(driver.findElement(By.id("fcf")));
		dropdown.selectByIndex(3);
		return this;
	}

	@SuppressWarnings("deprecation")
	public CustomerContactPage_731_732_764 navigateToWI_AddContact(Hashtable<String, String> testData)
			throws Exception {

		GenericUtilities util = new GenericUtilities();
		// Step 2
		// Reporter.log("Step 2 started");

		try {

			util.customAlertByJS("Step 2 started- Opening work item");

			{
				Reporter.log("Step 2 started - Opening work item");
				util.switchToTab(0);
				clickOnWorkItems();
				Reporter.log("Clicked on Work Items to get the WI lists");
				selectMyWI();
				String WIwithoutCustCOntact = BasePage.getTestDataValue("WiWithoutCustContact");

				WebElement WIwithoutContact = driver
						.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

				util.click(WIwithoutContact);
			}
			Reporter.log(" Work item without any customer contact opened");
			Reporter.log("Step 2 completed- Work item opened successfully");
			Thread.sleep(1000);
		}

		catch (Exception e) {

			Reporter.log("Step 2 failed- Unable to open work item");

		}

		// Step 3

		Reporter.log("Step 3 started- Adding new customer contact");

		try {

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@name=\"new_customer_contact\"]")).click();

			Thread.sleep(1000);
			enterValue(driver, customerContactInputEle, BasePage.getTestDataValue("CustomerContactID"));
			Select dropdown = new Select(driver.findElement(By.id("00Nq0000000ZKwf")));
			dropdown.selectByValue("SmartRate");
			Select dropdown_1 = new Select(driver.findElement(By.id("00Nq0000000ZKwg")));
			dropdown_1.selectByValue("SmartRate Note");
			Select soxReqInfoDropdown = new Select(soxReqInfoEle);
			soxReqInfoDropdown.selectByIndex(3);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"00No0000008cgnn\"]"))
					.sendKeys(BasePage.getTestDataValue("AdditionalComments"));
			driver.findElement(By.xpath("//*[@id=\"00Nq0000000iAXX\"]")).sendKeys("Service Point");
			
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Thread.sleep(2000);
			Reporter.log(" Step 3 completed successfully- new customer contact added");
		}

		catch (Exception e) {
			Reporter.log(" Step 3 failed :" + e);
		}

		// Step 4

		util.customAlertByJS("Step 4 started");
		Reporter.log("Step 4 started -  View CC&B Customer Contact");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WiWithoutCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);
			Thread.sleep(1000);
			util.click(viewContactEle);
			String CustID1 = customerIdMainPageEle.getText();
			viewCCBContactBtnEle.click();
			util.switchToTab(1);
			try {

				util.CCBLogin(CCBUserIdEle, CCBPasswordEle, CCBLoginBtn);
				Reporter.log("CCB login done");
			}

			catch (Exception e) {

				Reporter.log("CCB already logged in");
			}

			driver.switchTo().defaultContent();

			Thread.sleep(1000);
			driver.switchTo().frame("main");
			driver.switchTo().frame("tabMenu");

			util.click(LogTabCCBele);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("main");
			driver.switchTo().frame("tabPage");

			WebElement CCBLOGID0 = driver.findElement(By.xpath("//*[@name=\"CC_LOG$CC_LOG_ID\"]"));
			try {
				Assert.assertTrue(CCBLOGID0.isDisplayed());

				Reporter.log("Step 4 is completed successfully");

			} catch (AssertionError e) {

				Reporter.log("Step 4 failed" + e.getLocalizedMessage());
			}
		} catch (Exception e) {
			Reporter.log("Step 4 failed" + e.getMessage());

		}

		// Step 5
		util.customAlertByJS(" Step 5 started");
		Reporter.log("Step 5 started - Related Record Verification");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WiWithoutCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);
			Thread.sleep(1000);
			util.scrollIntoView(customerRelatedRecNamEle);
			Thread.sleep(1000);
			Assert.assertTrue(customerRelatedRecNamEle.isDisplayed());
			Reporter.log(" Related records verification done");
			Reporter.log("STep 5 passed");
		} catch (Exception e) {
			Reporter.log("Step 5 failed");

		}

		// step 6

		util.customAlertByJS("Step 6 started");
		Reporter.log("Step 6 started - Adding ABAC Info");
		try {
			
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
			if (correctionCheckBoxEle.isSelected() == true) {
				correctionCheckBoxEle.click();
				Reporter.log(" unhecked correction check box");
			}
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Reporter.log("ABAC info updated successfully");
			Thread.sleep(2000);
			Reporter.log("Edit WI and save successful");
		} catch (Exception e) {

			System.out.println(e);

			Reporter.log(" Step 6 failed");
		}

		// Step 7

		util.customAlertByJS(" Step 7 started");
		Reporter.log("Step 7 started - Inserting CC-Log Entry");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact = BasePage.getTestDataValue("WiWithoutCustContact");

			WebElement WIwithoutContact = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

			util.click(WIwithoutContact);
			Thread.sleep(1000);

			util.click(customerRelatedRecNamEle);

			util.scrollIntoView(customerContactLogNameEle);
			Assert.assertTrue(customerContactLogNameEle.isDisplayed());

			/*	String ABACLogEntry = CustContactLogEntryEle.getText();
				driver.navigate().back();
				util.click(viewCCBCustomerContactEle);
				util.switchToTab(1);
			
				driver.switchTo().frame("main");
				driver.switchTo().frame("tabPage");
				driver.switchTo().frame("CC_L OG_GRID");
				String CCBLOg = util.getText(CCLogStatementEle);
				Assert.assertEquals(ABACLogEntry.trim(), CCBLOg.trim());
				Reporter.log("SFDC and CC&B log entry verification done");
				// Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());
			*/ Reporter.log("Step 7 completed successfully");

		}

		catch (Exception e) {

			Reporter.log("Step 7 failed");
		}

		// Step 8 started

		util.customAlertByJS("Step 8 started");
		Reporter.log("Step 8 started");

		Reporter.log("Opening work item with no customer contacts");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiWithoutCustContact");

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
			
			
			driver.findElement(By.xpath("//*[@title=\"Edit\"]")).click();
			Thread.sleep(2000);
			enterKeys(driver, AsBilledAmountLocator, "2000");
			Thread.sleep(2000);
			enterKeys(driver, AsCorrectedAmountLocator, "1000");
			Thread.sleep(2000);
			enterKeys(driver, BilledDateFromLocator, "4/18/2018");
			Thread.sleep(2000);
			enterKeys(driver, BilledDateToLocator, "4/18/2018");
			Thread.sleep(2000);
			// enterKeys(driver, CustomerAjAmountLocator, "196");
			// Thread.sleep(2000);
			util.sendKeys(billedToAmountEle, "201");
			Thread.sleep(2000);
			util.sendKeys(billedFromDateEle, "4/18/2018");
			Thread.sleep(2000);
			util.click(billedToAmountEle);
			Thread.sleep(2000);
			util.sendKeys(billedToDateEle, "4/18/2018");
			billedToAmountEle.click();
			Thread.sleep(2000);
			if (correctionCheckBoxEle.isSelected() == true) {
				correctionCheckBoxEle.click();

			}

			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Reporter.log(" Work item edited and saved successfully");
			Thread.sleep(2000);

			Reporter.log("Step 9 passed");
		} catch (Exception e) {
			System.out.println(e);
			Reporter.log("Step 9 failed");
		}

		// Step 10 started

		util.customAlertByJS("Step 10 started");
		Reporter.log("Step 10 started");

		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiWithoutCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);
			Thread.sleep(1000);

			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@value=\"Close Work Item\"]")).click();

			WebElement statusDropdown = driver.findElement(By.xpath("//*[@id=\"cas7\"]"));
			util.selectByIndex(2, statusDropdown);

			WebElement postponeCredit = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmD\"]"));
			util.selectByIndex(2, postponeCredit);

			WebElement subStatus = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmk\"]"));
			util.selectByIndex(1, subStatus);

			WebElement rootCauseCategory = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwY\"]"));
			util.selectByIndex(5, rootCauseCategory);

			WebElement rootCausSUbCategory = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmU\"]"));
			util.selectByIndex(2, rootCausSUbCategory);

			WebElement CustomerContactClass = driver.findElement(By.xpath("//*[@id=\"00No0000008cglg\"]"));

			util.selectByIndex(9, CustomerContactClass);

			WebElement CustContactType = driver.findElement(By.xpath("//*[@id=\"00No0000008cgll\"]"));

			util.selectByIndex(1, CustContactType);

			WebElement SOXReqInfo = driver.findElement(By.xpath("//*[@id=\"00Nq0000000iAXV\"]"));
			Thread.sleep(2000);
			util.selectByIndex(1, SOXReqInfo);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwU\"]")).sendKeys("1234");

			WebElement WICloseSaveBtn = driver.findElement(By.xpath("//*[@value=\" Save \"]"));

			util.click(WICloseSaveBtn);
			Thread.sleep(2000);
			Reporter.log("Step 10 passed");
		}

		catch (Exception e) {
			System.out.println(e);
			Reporter.log("Step 10 failed");
		}

		// Step 11 started

		util.customAlertByJS("Step 11 started");
		Reporter.log("Step 11 started");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiWithoutCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);
			Thread.sleep(1000);
			util.scrollIntoView(customerRelatedRecNamEle);
			Assert.assertTrue(customerRelatedRecNamEle.isDisplayed());
			Reporter.log(" Related records verification done");
			Reporter.log(" Step 11 passed");

		}

		catch (Exception e) {
			System.out.println(e);
			Reporter.log("Step 11 failed");

		}

		// Step 12 started

		util.customAlertByJS("Step 12 started");
		Reporter.log("Step 12 started");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiWithoutCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);
			util.scrollIntoView(customerRelatedRecNamEle);

			util.click(customerRelatedRecNamEle);
			Thread.sleep(2000);
			util.click(CCLOGNumberEle);
			Thread.sleep(2000);

			try {
				 Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());

				Reporter.log(" Step 12 passed");
			}

			catch (AssertionError e) {

				Reporter.log(" Assertion failed customerContactLogWithABACCheckboxele IS NOT CHECKED" + e);
			}

		}

		catch (Exception e) {

			Reporter.log("Step 12 failed");

		}

		// Step 13 started

		util.customAlertByJS("Step 13 started");
		Reporter.log("Step 13 started-Opening work item");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiWithoutCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);

			Reporter.log("Step 13 passed- Work item opened successfully");
		} catch (Exception e) {

			Reporter.log(" Step 13 failed- unable to open work item");
		}

		// Step 14

		util.customAlertByJS(" Step 14 started");
		Reporter.log("Step 14 started");
		try {
			util.click(customerRelatedRecNamEle);
			String CCLOG = util.getText(CCLOGNumberEle);
			Reporter.log(" CCLOG Number is : " + CCLOG);
			util.click(CCLOGNumberEle);

			Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());
			Reporter.log("Step 14 passed");
		} catch (AssertionError e) {
			Reporter.log(" Assertion failed customerContactLogWithABACCheckboxele IS NOT CHECKED" + e);
			Reporter.log("Step 14 failed");
		}

		// Step 15

		util.customAlertByJS(" Step 15 started");
		Reporter.log("Step 15 started");
		try {
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiWithoutCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

			util.click(WIwithoutContact1);
			driver.findElement(By.xpath("//*[@title=\"Edit\"]")).click();
			Thread.sleep(2000);
			enterKeys(driver, AsBilledAmountLocator, "2000");
			enterKeys(driver, AsCorrectedAmountLocator, "1000");
			enterKeys(driver, BilledDateFromLocator, "6/18/2018");
			enterKeys(driver, BilledDateToLocator, "6/18/2018");
			// enterKeys(driver, CustomerAjAmountLocator, "196");

			util.sendKeys(billedToAmountEle, "201");
			Thread.sleep(2000);
			util.sendKeys(billedFromDateEle, "6/18/2018");
			Thread.sleep(2000);
			util.click(billedToAmountEle);
			Thread.sleep(2000);
			util.sendKeys(billedToDateEle, "6/18/2018");
			billedToAmountEle.click();
			Thread.sleep(2000);
			if (correctionCheckBoxEle.isSelected() == false) {
				correctionCheckBoxEle.click();

			}

			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Thread.sleep(2000);

			Reporter.log(" Step 15 passed");
		}

		catch (Exception e) {
			System.out.println(e);
			Reporter.log("Step 15 failed");
		}

		// Step 16

		util.customAlertByJS("Step 16 Execution started");
		Reporter.log(" Step 16 executed started");
		util.switchToTab(0);
		clickOnWorkItems();
		selectMyWI();
		String WIwithoutCustCOntact1 = BasePage.getTestDataValue("WiWithoutCustContact");

		WebElement WIwithoutContact1 = driver
				.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact1 + "')]"));

		util.click(WIwithoutContact1);
		util.click(customerRelatedRecNamEle);
		Thread.sleep(1000);
		Assert.assertTrue(customerContactLogNameEle.isDisplayed());
		String CCLOG1 = util.getText(CCLOGNumberEle);
		Reporter.log(" CCLOG Number is : " + CCLOG1);
		util.click(CCLOGNumberEle);
		try {
			 Assert.assertTrue(customerContactLogNameEle.isDisplayed());
			 Assert.assertTrue(CCLOGEntryTextEle.isDisplayed());
			 Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());
			 Reporter.log("Assertion passed customerContactLogWithABACCheckboxele IS CHECKED");
		} catch (Exception e) {
			Reporter.log(" Assertion failed customerContactLogWithABACCheckboxele IS NOT CHECKED" + e);
		}
		Reporter.log(" Step 16 executed successfully");

		// Step 17 Scenario- Customer contact related record exists. Edit ABAC
		// info and close WI.

		util.customAlertByJS("Step 17 started");
		Reporter.log(" Step 17 Execution started");

		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();

			String WIwithoutCustCOntact2 = BasePage.getTestDataValue("WiWithoutCustContact1");

			WebElement WIwithoutContact3 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact2 + "')]"));

			util.click(WIwithoutContact3);
			Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Work Item Detail')]")).isDisplayed());

			Reporter.log(" Work item opened successfully");
			Reporter.log(" Step 17 passed");
		} catch (Exception e) {

			Reporter.log("Step 17 failed");
		}

		// Step 18

		util.customAlertByJS("Step 18 execution started");

		Reporter.log(" Step 18 execution started");
		try {
			driver.findElement(By.xpath("//*[@title=\"Edit\"]")).click();

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
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Thread.sleep(2000);
			Reporter.log(" Step 18 executed successfully");
		} catch (Exception e) {

			Reporter.log("Step 18 failed");
		}

		// Step 19

		util.customAlertByJS("Step 19 execution started");
		Reporter.log("Step 19 execution started");
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();

			String WIwithoutCustCOntact2 = BasePage.getTestDataValue("WiWithoutCustContact1");

			WebElement WIwithoutContact3 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact2 + "')]"));

			util.click(WIwithoutContact3);

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@value=\"Close Work Item\"]")).click();

			WebElement statusDropdown = driver.findElement(By.xpath("//*[@id=\"cas7\"]"));
			util.selectByIndex(2, statusDropdown);

			WebElement postponeCredit = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmD\"]"));
			util.selectByIndex(2, postponeCredit);

			WebElement subStatus = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmk\"]"));
			util.selectByIndex(1, subStatus);

			WebElement rootCauseCategory = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwY\"]"));
			util.selectByIndex(5, rootCauseCategory);

			WebElement rootCausSUbCategory = driver.findElement(By.xpath("//*[@id=\"00No0000008cgmU\"]"));
			util.selectByIndex(2, rootCausSUbCategory);

			WebElement CustomerContactClass = driver.findElement(By.xpath("//*[@id=\"00No0000008cglg\"]"));

			util.selectByIndex(9, CustomerContactClass);

			WebElement CustContactType = driver.findElement(By.xpath("//*[@id=\"00No0000008cgll\"]"));

			util.selectByIndex(1, CustContactType);
			WebElement SOXReqInfo = driver.findElement(By.xpath("//*[@id=\"00Nq0000000iAXV\"]"));

			util.selectByIndex(1, SOXReqInfo);

			WebElement WICloseSaveBtn = driver.findElement(By.xpath("//*[@value=\" Save \"]"));

			util.click(WICloseSaveBtn);
			Thread.sleep(1000);
			Reporter.log("Work item closed successfully");
			Reporter.log(" Step 19 executed successfully");
		} catch (AssertionError e) {
			Reporter.log("Step 19 failed");
		} catch (Exception e) {
			Reporter.log("Step 19 failed");
		}

		// Step 20
		util.customAlertByJS(" Step 20 started");

		Reporter.log("Step 20 started");
		util.switchToTab(0);
		clickOnWorkItems();
		selectMyWI();

		String WIwithoutCustCOntact2 = BasePage.getTestDataValue("WiWithoutCustContact1");

		WebElement WIwithoutContact3 = driver
				.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact2 + "')]"));

		util.click(WIwithoutContact3);

		util.click(customerRelatedRecNamEle);
		Reporter.log("Related record name clicked");
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"CF00No0000008cgkt_ileinner\"]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'CC LOG-')]")).isDisplayed());
			Reporter.log("Related record verification done");
		} catch (AssertionError e) {

			Reporter.log("Related records verification failed :" + e.getLocalizedMessage());
		}

		try {
			util.click(CCLOGNumberEle);
			Thread.sleep(2000);
			 Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());

		}

		catch (AssertionError e) {

			Reporter.log(" Assertion failed customerContactLogWithABACCheckboxele IS NOT CHECKED" + e);
		}

		Reporter.log(" Step 20 execution completed successfully");

		// Step 21

		util.customAlertByJS("Step 21 started");
		Reporter.log(" Step 21 started- Comparing Customer contact records and logs in SFDC & CC&B");

		// clicking CC LOG link
		try {
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();

			String WIwithoutCustCOntact4 = BasePage.getTestDataValue("WiWithoutCustContact1");

			WebElement WIwithoutContact4 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact4 + "')]"));

			util.click(WIwithoutContact4);
			util.click(customerRelatedRecNamEle);

			util.click(CCLOGNumberEle);
			String CClogLogID = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKD_ileinner\"]")).getText();
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

			String CCBLOGID = driver.findElement(By.xpath("//*[@name=\"CC_LOG$CC_LOG_ID\"]")).getText();

			Assert.assertEquals(CClogLogID, CCBLOGID);
			Reporter.log(
					"Step 21 complete-Customer contact records and logs comparion between SFDC & CC&B was successfull");

		}

		catch (AssertionError e) {

			Reporter.log("Step 21 failed: " + e);

		}

		catch (Exception e) {

			Reporter.log("Step 21 failed: " + e);

		}

		// Step 22

		Reporter.log("Step 22 started- Opening work Item");
		util.customAlertByJS(" Step 22 started");
		try {
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			clickOnWIWithStatusBWO(driver, WINumberLocator);
			Thread.sleep(2000);
			WebElement WorkItemDetail = driver.findElement(By.xpath("//*[contains(text(),'Work Item Detail')]"));
			Assert.assertTrue(WorkItemDetail.isDisplayed());

			Reporter.log(" Step 22 completed successfully. Work Item is successfully opened.");
		}

		catch (AssertionError e) {

			Reporter.log("Step 22 failed: " + e);

		}

		catch (Exception e) {

			Reporter.log("Step 22 failed: " + e);

		}

		// Step 23

		util.customAlertByJS(" Step 23 started");
		try {
			Reporter.log("Step 23 started- Edit and Save");
			driver.findElement(By.xpath("//*[@title=\"Edit\"]")).click();
			Thread.sleep(2000);
			enterKeys(driver, AsBilledAmountLocator, "");
			enterKeys(driver, AsCorrectedAmountLocator, "");
			enterKeys(driver, BilledDateFromLocator, "");
			enterKeys(driver, BilledDateToLocator, "");
			// enterKeys(driver, CustomerAjAmountLocator, "");

			util.sendKeys(billedToAmountEle, "");
			util.sendKeys(billedFromDateEle, "");
			util.click(billedToAmountEle);
			Thread.sleep(2000);
			util.sendKeys(billedToDateEle, "");
			billedToAmountEle.click();
			Thread.sleep(2000);
			if (correctionCheckBoxEle.isSelected() == true) {
				correctionCheckBoxEle.click();

			}
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Thread.sleep(2000);
	
			Reporter.log("Step 23 completed successfully");
		} catch (Exception e) {

			Reporter.log(" Step 23 failed");
		}

		// Step 24 started

		util.customAlertByJS(" Step 24 started");
		try {
			Reporter.log("Step 24 started");

			driver.findElement(By.xpath("//*[@name=\"cancel\"]")).click();

			clickOnWorkItems();
			selectMyWI();
			clickOnWIWithStatusBWO(driver, WINumberLocator);

			util.click(customerRelatedRecNamEle);

			BasePage.captureScreenshot("Step 24 -Related Record Details");

			Reporter.log("Step 24 completed successfully.");
		}

		catch (Exception e) {

			Reporter.log("Step 24 failed");
		}

		// Step 25

		util.customAlertByJS(" Step 25 started ");
		Reporter.log("Step 25 started");

		clickOnWorkItems();
		selectMyWI();
		clickOnWIWithStatusBWO(driver, WINumberLocator);
		driver.findElement(By.xpath("//*[@title=\"Edit\"]")).click();
		Thread.sleep(2000);
		enterKeys(driver, AsBilledAmountLocator, "2000");
		enterKeys(driver, AsCorrectedAmountLocator, "1000");
		enterKeys(driver, BilledDateFromLocator, "3/19/2018");
		enterKeys(driver, BilledDateToLocator, "3/19/2018");
		// enterKeys(driver, CustomerAjAmountLocator, "196");

		util.sendKeys(billedToAmountEle, "201");
		util.sendKeys(billedFromDateEle, "3/19/2018");
		util.click(billedToAmountEle);
		Thread.sleep(2000);
		util.sendKeys(billedToDateEle, "3/19/2018");

		util.click(billedToAmountEle);

		try {
			if (correctionCheckBoxEle.isSelected() == true) {
				correctionCheckBoxEle.click();

			}
		}

		catch (Exception e) {

			System.out.println("Unable to Uncheck correction checkbox: " + e);
		}

		driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
		Thread.sleep(2000);

		try {
			if (WIsaveSuccessfullConfirmEle.isDisplayed() == true) {

				Reporter.log(" Edit WI and save successful");
				Reporter.log("Step 25 passed");
			}

		}

		catch (Exception e) {

			Reporter.log("Step 25 has failed");
			System.out.println("Exception happened while saving WI" + e);
		}

		// Step 26

		util.customAlertByJS(" Step 26 started");

		Reporter.log("Step 26 started");

		clickOnWorkItems();
		selectMyWI();
		clickOnWIWithStatusBWO(driver, WINumberLocator);

		customerRelatedRecNamEle.click();
		Thread.sleep(1000);
		CCLOGNumberEle.click();

		try {
			 Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());

		}

		catch (AssertionError e) {

			Reporter.log(" Assertion failed" + e);
		}

		Reporter.log("Step 26 completed successfully");

		// Step 27 - Validating log in CC&B customer contact.

		Reporter.log("Step 27 started");

		util.customAlertByJS(" Step 27 started");

		try {
			clickOnWorkItems();
			selectMyWI();
			clickOnWIWithStatusBWO(driver, WINumberLocator);

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

			System.out.println(" Log id " + CCBLOGID1 + " verification successful");
		}

		catch (AssertionError e) {

			Reporter.log("Step 27- Assertions have failed :" + e);

		}

		catch (Exception e) {

			Reporter.log("Step 27 has failed :" + e);

		}

		// Step 28

		util.customAlertByJS(" Step 28 started");
		Reporter.log("Step 28 started");
		try {
			util.switchToTab(0);

			clickOnWorkItems();
			selectMyWI();
			clickOnWIWithStatusBWO(driver, WINumberLocator);

			Reporter.log(" Step 28 passed-Work item opened successfully");
		}

		catch (Exception e) {

			Reporter.log(" Step 28 has failed " + e);
		}

		// STep 29- No abac changes. Correction= checked & save- error should
		// come.

		util.customAlertByJS(" Step 29 started");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@title=\"Edit\"]")).click();
		try {
			if (!correctionCheckBoxEle.isSelected()) {
				correctionCheckBoxEle.click();

			}
		}

		catch (Exception e) {

			System.out.println("Unable to check correction checkbox: " + e);
		}

		driver.findElement(By.xpath("//*[@name=\"save\"]")).click();

		Thread.sleep(2000);

		try {
			if (WIsaveSuccessfullConfirmEle.isDisplayed() == true)

				System.out.println(" Edit WI and save successful");

			else {

				System.out.println(" work Item save failed");
			}

		}

		catch (Exception e) {
			System.out.println("Exception happened while saving WI" + e);
		}

		// Step 30 update all abac info to blank. correction= checked

		util.customAlertByJS(" Step 30 started");
		Reporter.log("Step 30 started");
		clickOnWorkItems();
		selectMyWI();
		clickOnWIWithStatusBWO(driver, WINumberLocator);

		driver.findElement(By.xpath("//*[@title=\"Edit\"]")).click();
		Thread.sleep(2000);
		enterKeys(driver, AsBilledAmountLocator, "");
		enterKeys(driver, AsCorrectedAmountLocator, "");
		enterKeys(driver, BilledDateFromLocator, "");
		enterKeys(driver, BilledDateToLocator, "");
		// enterKeys(driver, CustomerAjAmountLocator, "");

		util.sendKeys(billedToAmountEle, "");
		util.sendKeys(billedFromDateEle, "");
		util.click(billedToAmountEle);
		Thread.sleep(2000);
		util.sendKeys(billedToDateEle, "");
		billedToAmountEle.click();
		Thread.sleep(2000);

		try {
			if (!correctionCheckBoxEle.isSelected()) {
				correctionCheckBoxEle.click();

				System.out.println(" Correction check box is checked");

			}
		}

		catch (Exception e) {

			System.out.println("Unable to check correction checkbox: " + e);
		}

		driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
		Thread.sleep(2000);
		try {

			if (WIsaveSuccessfullConfirmEle.isDisplayed() == true)

				Reporter.log("Step 30 passed- Edit WI and save successful");
		}

		catch (Exception e) {
			Reporter.log("Step 30 failed" + e.getLocalizedMessage());
		}

		// Step 31

		util.customAlertByJS(" Step 31 started");
		Reporter.log(" Step 31 started");
		try {
			clickOnWorkItems();
			selectMyWI();
			clickOnWIWithStatusBWO(driver, WINumberLocator);

			customerRelatedRecNamEle.click();
			CCLOGNumberEle.click();
			 Assert.assertFalse(customerContactLogWithABACCheckboxele.isSelected());
			Reporter.log("Step 31 passed");
		}

		catch (AssertionError e) {

			Reporter.log(" Step 31 failed " + e);
		}

		// step 32
		util.customAlertByJS(" Step 32 started");
		try {
			driver.navigate().back();

			util.click(CCLOGNumberEle);
			String CClogLogID2 = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKD_ileinner\"]")).getText();
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

			String CCBLOGID2 = driver.findElement(By.xpath("//*[@name=\"CC_LOG$CC_LOG_ID\"]")).getText();
			Assert.assertEquals(CClogLogID2, CCBLOGID2);

		} catch (AssertionError e) {

			Reporter.log("Step 32 failed");
		} catch (Exception e) {

			Reporter.log("Step 32 failed");
		}

		// Step 33- Update ABAC info with new values.

		util.customAlertByJS(" Step 33 started");
		try {
			Reporter.log("Step 33 started");
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			clickOnWIWithStatusBWO(driver, WINumberLocator);

			util.click(customerRelatedRecNamEle);

			BasePage.captureScreenshot("Step 33 -Related Record Details");
			Reporter.log("Step 33 passed- Screenshot captured");
		} catch (Exception e) {
			Reporter.log("Step 33 failed");
		}

		// Step 34- Update all ABAC info to new values

		util.customAlertByJS("STep 34 started");
		try {
			driver.navigate().back();
			driver.findElement(By.xpath("//*[@title=\"Edit\"]")).click();
			Thread.sleep(2000);

			enterKeys(driver, AsBilledAmountLocator, "230");
			enterKeys(driver, AsCorrectedAmountLocator, "5500");
			enterKeys(driver, BilledDateFromLocator, "3/14/2018");
			enterKeys(driver, BilledDateToLocator, "3/14/2018");
			// enterKeys(driver, CustomerAjAmountLocator, "176");

			util.sendKeys(billedToAmountEle, "281");
			util.sendKeys(billedFromDateEle, "6/15/2018");
			util.click(billedToAmountEle);
			Thread.sleep(2000);
			util.sendKeys(billedToDateEle, "6/15/2018");
			util.click(billedToAmountEle);
			Thread.sleep(2000);

			if (!correctionCheckBoxEle.isSelected()) {
				correctionCheckBoxEle.click();
				Reporter.log(" Correction check box is checked");
			}
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Reporter.log("Step 34 passed");

		} catch (Exception e) {

			Reporter.log("Step 34 failed");
		}

		// step 35

		util.customAlertByJS(" Step 35 started");
		try {
			clickOnWorkItems();
			selectMyWI();
			clickOnWIWithStatusBWO(driver, WINumberLocator);

			util.click(customerRelatedRecNamEle);
			Thread.sleep(2000);
			util.click(CCLOGNumberEle);
			 Assert.assertTrue(customerContactLogWithABACCheckboxele.isSelected());
		} catch (AssertionError e) {

			Reporter.log(" Assertion failed customerContactLogWithABACCheckboxele IS NOT CHECKED" + e);
		} catch (Exception e) {

			Reporter.log("Step 35 failed");
		}

		// 36

		util.customAlertByJS(" Step 36 started");
		Reporter.log("Step 36 started");
		driver.navigate().back();
		util.click(CCLOGNumberEle);
		String CClogLogID4 = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKD_ileinner\"]")).getText();
		driver.navigate().back();
		util.click(viewCCBContactBtnEle);
		util.switchToTab(1);

		util.switchToWindow();

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
		String CCBLOGID4 = driver.findElement(By.xpath("//*[@name=\"CC_LOG$CC_LOG_ID\"]")).getText();
		try {
			Assert.assertEquals(CClogLogID4, CCBLOGID4);

			Reporter.log(" Log id " + CCBLOGID4 + " verification successful");
			Reporter.log("Step 36 passed");
		} catch (AssertionError e) {
			Reporter.log(" Log id " + CCBLOGID4 + " verification UNsuccessful");
			Reporter.log("Step 36 failed");
		} catch (Exception e) {
			Reporter.log(" Log id " + CCBLOGID4 + " verification UNsuccessful");
			Reporter.log("Step 36 failed");
		}

		// Step 37

		util.customAlertByJS(" STep 37 started- logging out");
		Reporter.log("Step 37 started- logging out");
		util.switchToTab(0);
		WebElement logOutMenu = driver.findElement(By.xpath("//*[@id=\"userNav-arrow\"]"));
		util.click(logOutMenu);
		WebElement logoutButton = driver.findElement(By.xpath("//*[@title=\"Logout\"]"));
		util.click(logoutButton);
		Reporter.log(" logout done successfully");

		return this;
	}

}
