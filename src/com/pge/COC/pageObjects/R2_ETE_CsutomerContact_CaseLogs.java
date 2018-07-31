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

public class R2_ETE_CsutomerContact_CaseLogs extends BasePage {
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

	@FindBy(xpath = "(//td[contains(text(),'Completed')])[position()=1]")
	private WebElement WIStatusFieldEle;

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

	@FindBy(xpath = "(//td[@class=\" dataCell  \"])[position()=6]")
	private WebElement CustContactLogEntryEle;
	
	@FindBy(xpath = "(//td[@class=\" dataCell  \"])[position()=6]")
	private WebElement CustContactLogEntryEleStep4;
	
	@FindBy(xpath = "(//td[@class=\" dataCell  \"])[position()=2]")
	private WebElement WIMainPageCCLOGTxtEle;
	@FindBy(xpath = "(//*[contains(text(),'RR-')])[position()=1]")
	private WebElement customerRelatedRecNamEle;

	@FindBy(xpath = "(//*[contains(text(),'CC LOG-')])[position()=1]")
	private WebElement customerContactLogNameEle;

	@FindBy(xpath = "//*[@name=\"view_cc_b_customer_contact\"]")
	private WebElement viewCCBCustomerContactEle;

	@FindBy(xpath = "//*[@name=\"view_cc_b_case\"]")
	private WebElement viewCCBCase;

	@FindBy(xpath = "(//*[@aria-labelledby=\"D_L_CC_LOG_CONTENT\"])[position()=1]//..")
	private WebElement CCLogStatementEle;
	@FindBy(xpath = "//*[contains(text(),'CC LOG-')]")
	private WebElement CCLOGNumberEle;
	@FindBy(xpath = "//*[contains(text(),'Complete')]")
	private WebElement CCLOGEntryTextEle;

	@FindBy(xpath = "//*[@title='Log']")
	private WebElement LogTabCCBele;

	@FindBy(xpath = "//*[@id=\"00No0000008cglf\"]")
	WebElement createCustContinCCBCheckBox;
	@FindBy(xpath = "//*[@id=\"00Nq0000000ZKwP\"]")
	WebElement addTOCCBCaseLOgCheckBox;

	public R2_ETE_CsutomerContact_CaseLogs(WebDriver driver, HashMap<String, String> localData) throws Exception {
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

	public R2_ETE_CsutomerContact_CaseLogs loginIntoApplication(Hashtable<String, String> testData) throws Exception {

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

	public R2_ETE_CsutomerContact_CaseLogs clickOnWorkItems() throws Exception {

		// driver.findElement(By.xpath("//*[@title=\"Home Tab -
		// Selected\"]")).click();

		GenericUtilities util = new GenericUtilities();

		WebElement homebutton = driver.findElement(By.xpath("//*[@href=\"/home/home.jsp\"]"));

		util.click(homebutton);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[contains(text(),'Work Items')]")).click();

		return this;
	}

	public R2_ETE_CsutomerContact_CaseLogs selectMyWI() throws Exception {
		Select dropdown = new Select(driver.findElement(By.id("fcf")));
		dropdown.selectByIndex(3);
		return this;
	}

	@SuppressWarnings("deprecation")
	public R2_ETE_CsutomerContact_CaseLogs navigateToWI_AddContact2(Hashtable<String, String> testData)
			throws Exception {

		GenericUtilities util = new GenericUtilities();
		// Step 2
		// Reporter.log("Step 2 started");

		util.customAlertByJS("Step 2 started- Opening work item");
		Reporter.log("Step 2 started");
		clickOnWorkItems();
		Reporter.log("Clicked on Work Items to get the WI lists");
		selectMyWI();
		String WIwithoutCustCOntact = BasePage.getTestDataValue("RTEWIWithNoCustContact");

		WebElement WIwithoutContact = driver
				.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact + "')]"));

		util.click(WIwithoutContact);

		Reporter.log("Case work item with no customer contact opened");

		// Step 3

		try {

			util.customAlertByJS(" Step 3 started");
			Reporter.log("Step 3 started");
            
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

			Reporter.log("Step 3 completed- Work item saved successfully");
			Reporter.log("Added as Billed as a Corrected Information");

		} catch (

		Exception e) {

			Reporter.log("Step 3 failed");

		}

		// Step 4

		util.customAlertByJS("Step 4 started");
		Reporter.log("Step 4 started");
		
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact11 = BasePage.getTestDataValue("RTEWIWithNoCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact11 + "')]"));

			util.click(WIwithoutContact1);

			driver.findElement(By.xpath("//*[@name=\"new_customer_contact\"]")).click();
			Thread.sleep(1000);
			enterValue(driver, customerContactInputEle, BasePage.getTestDataValue("CustomerContactID"));
			Select dropdown = new Select(driver.findElement(By.id("00Nq0000000ZKwf")));
			dropdown.selectByValue("Other");
			Select dropdown_1 = new Select(driver.findElement(By.id("00Nq0000000ZKwg")));
			dropdown_1.selectByValue("Other Customer Contact");
			Select soxReqInfoDropdown = new Select(soxReqInfoEle);
			soxReqInfoDropdown.selectByIndex(1);
			WebElement serviceAgreementID = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwe\"]"));
			util.sendKeys(serviceAgreementID, "12345");

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"00No0000008cgnn\"]"))
					.sendKeys(BasePage.getTestDataValue("AdditionalCommentsRTE"));
			driver.findElement(By.xpath("//*[@id=\"00Nq0000000iAXX\"]")).sendKeys("Service Point");

			WebElement addToCaseLogCheckBoxele = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ZKwa\"]"));

			if (addToCaseLogCheckBoxele.isSelected() == false) {

				util.click(addToCaseLogCheckBoxele);
			}
			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Thread.sleep(1000);

			Assert.assertTrue(CustContactLogEntryEle.isDisplayed());


			String CCLOGMessage = CustContactLogEntryEleStep4.getText();
			
			Assert.assertTrue(CCLOGMessage.contains("With ABAC"));
			Reporter.log("Added New Csutomer Contact via Midstream");
			Reporter.log("STep 4 passed");
			
		}

		catch (AssertionError e) {
			Reporter.log("Step 4 failed" + e.getLocalizedMessage());
		}

		catch (Exception e) {
			Reporter.log("Step 4 failed" + e.getLocalizedMessage());
		}

		// STep 5 started
		util.customAlertByJS("Step 5 started");
		Reporter.log("Step 5 started");
		
		try {
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact5 = BasePage.getTestDataValue("RTEWIWithNoCustContact");

			WebElement WIwithoutContact5 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact5 + "')]"));

			util.click(WIwithoutContact5);

			util.scrollIntoView(WIMainPageCCLOGTxtEle);
			util.highlighElement(WIMainPageCCLOGTxtEle);

			Assert.assertTrue(WIMainPageCCLOGTxtEle.isDisplayed());

			

			String CCLOGMessage = CustContactLogEntryEleStep4.getText();
			Assert.assertTrue(CCLOGMessage.contains("With ABAC"));

			Reporter.log("Case log is :" + WIMainPageCCLOGTxtEle.getText());
			Reporter.log("Step 5 passed");
			Reporter.log("Verified CCB Case Log creation");

		}

		catch (AssertionError e) {
			Reporter.log("Step 5 failed" + e.getLocalizedMessage());

		}

		catch (Exception e) {
			Reporter.log("Step 5 failed" + e.getLocalizedMessage());

		}

		// STep 6 started Adding SOX info

		util.customAlertByJS("Step 6 started");
		Reporter.log("Step 6 started");
		
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();

			String WIwithoutCustCOntact2 = BasePage.getTestDataValue("RTEWIWithNoCustContact");

			WebElement WIwithoutContact3 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact2 + "')]"));

			util.click(WIwithoutContact3);

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
			util.click(saveBtnEle);
			Thread.sleep(1000);
			Assert.assertTrue(WIsaveSuccessfullConfirmEle.isDisplayed());
			Reporter.log("ABAC information saved successfully");

			Thread.sleep(1000);

			Reporter.log(" Step 6 passed");
			Reporter.log("Updated As Billed As Corrected Information");

		}

		catch (AssertionError e) {

			Reporter.log("Step 6 failed" + e.getLocalizedMessage());
		} catch (Exception e) {

			Reporter.log("Step 6 failed" + e.getLocalizedMessage());
		}

		// Step 7 started- Verify added ABAC log in customer contact

		util.customAlertByJS("Step 7 started");
		Reporter.log("Step 7 started");
		
		try {
			clickOnWorkItems();
			selectMyWI();

			String WIwithoutCustCOntact2 = BasePage.getTestDataValue("RTEWIWithNoCustContact");

			WebElement WIwithoutContact3 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact2 + "')]"));

			util.click(WIwithoutContact3);
			util.click(customerRelatedRecNamEle);

			String CCLOGMessage = CustContactLogEntryEle.getText();

			String CCLOG = util.getText(CCLOGNumberEle);
			Reporter.log(" CCLOG Number is : " + CCLOG);

			String ABACLogEntry = CustContactLogEntryEle.getText();
			util.highlighElement(CustContactLogEntryEle);

			Assert.assertTrue(CustContactLogEntryEle.isDisplayed());
			Reporter.log(ABACLogEntry);

			util.highlighElement(CustContactLogEntryEle);

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

			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			driver.switchTo().frame("main");
			Thread.sleep(2000);
			driver.switchTo().frame("tabMenu");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//*[@title=\"Characteristics\"]")).click();

			Thread.sleep(1000);
			Reporter.log("Step 7 passed");
			Reporter.log("Verify Added ABAC Log on Csustomer Contact");
		}

		catch (AssertionError e) {

			Reporter.log("Step 7 failed" + e.getLocalizedMessage());
		}

		catch (Exception e) {
			Reporter.log("Step 7 failed" + e.getLocalizedMessage());
		}

		// Step 8

		
		util.customAlertByJS("Step 7 started");
		

		try {

			util.switchToTab(0);
			clickOnWorkItems();
			Reporter.log("Clicked on Work Items to get the WI lists");
			selectMyWI();
			String WIwithoutCustCOntact1 = BasePage.getTestDataValue("RTEWIWithNoCustContact");

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

			WebElement cancelReason = driver.findElement(By.xpath("//*[@id=\"00Nq0000000ii3D\"]"));
			util.selectByIndex(2, cancelReason);

			if (createCustContinCCBCheckBox.isSelected() == false) {

				util.click(createCustContinCCBCheckBox);
			}

			if (addTOCCBCaseLOgCheckBox.isSelected() == false) {

				util.click(addTOCCBCaseLOgCheckBox);
			}

			WebElement CustomerContactClass = driver.findElement(By.xpath("//*[@id=\"00No0000008cglg\"]"));

			util.selectByIndex(2, CustomerContactClass);

			WebElement CustContactType = driver.findElement(By.xpath("//*[@id=\"00No0000008cgll\"]"));

			util.selectByIndex(6, CustContactType);
			WebElement SOXReqInfo = driver.findElement(By.xpath("//*[@id=\"00Nq0000000iAXV\"]"));

			util.selectByIndex(1, SOXReqInfo);

			WebElement caseCLosureMethodDropdown = driver.findElement(By.xpath("//*[@id=\"00Nq0000000hucB\"]"));

			util.selectByIndex(1, caseCLosureMethodDropdown);

			WebElement WICloseSaveBtn = driver.findElement(By.xpath("//*[@value=\" Save \"]"));

			util.click(WICloseSaveBtn);

			Thread.sleep(2000);
			Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Work Item Detail')]")).isDisplayed());

			util.scrollIntoView(WIMainPageCCLOGTxtEle);
			util.highlighElement(WIMainPageCCLOGTxtEle);

			Assert.assertTrue(WIMainPageCCLOGTxtEle.isDisplayed());


			String CCLOGMessage = WIMainPageCCLOGTxtEle.getText();

			Assert.assertTrue(WIStatusFieldEle.isDisplayed());

			Assert.assertTrue(CCLOGMessage.contains("With ABAC"));
			Reporter.log("Case log is :" + WIMainPageCCLOGTxtEle.getText());

			Reporter.log("Step 8 passed - Work item closed successfully");
			

		} catch (AssertionError e) {
			Reporter.log("Step 8 failed" + e.getLocalizedMessage());

		} catch (Exception e) {

			Reporter.log(" Step 8 failed" + e.getLocalizedMessage());
		}

		// Step 9
		util.customAlertByJS(" Step 9 started");
		Reporter.log("Step 9 started");
		
		try {

			util.click(viewCCBCase);
			util.switchToTab(1);

			driver.switchTo().frame("main");
			driver.switchTo().frame("tabPage");
			String StatusinCCB = driver.findElement(By.xpath("//*[@for=\"CASE_TD_SUMMARY\"]")).getText();
			Assert.assertTrue(StatusinCCB.contains("Complete"));

			Reporter.log("Status is set as \"Complete\" in CC&B");

			Reporter.log("Step 9 passed");
			Reporter.log("Verifed Case on CC&B");
		} catch (AssertionError e) {
			Reporter.log("Step 9 failed" + e);

		}

		catch (Exception e) {
			Reporter.log("Step 9 failed" + e);

		}

		// step 10

		util.customAlertByJS("Step 10 started");
		Reporter.log("Step 10 started");
	
		try {
			driver.navigate().refresh();
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

			driver.switchTo().frame("CASE_LOG");

			// WebElement logDetailsatCCB =
			// driver.findElement(By.xpath("(//*[@id=\"CASE_LOG|0\"])[position()=4]"));

			WebElement logDetailsatCCB = driver.findElement(By.xpath("//*[@id=\"CASE_LOG:5$LOG_MSG_TXT\"]"));

			util.highlighElement(logDetailsatCCB);

			String caseLogText = logDetailsatCCB.getText();
			
			Assert.assertTrue(caseLogText.contains("Comments"));
			Assert.assertTrue(caseLogText.contains("ABAC"));
			Assert.assertTrue(caseLogText.contains(BasePage.getTestDataValue("RTEWIWithNoCustContact")));

			Reporter.log("CCB log text is  : " + logDetailsatCCB.getText());

			Reporter.log(" Step 10 passed");
			Reporter.log(" Caselog verification started");
		}

		catch (AssertionError e) {

			Reporter.log(" Step 10 failed" + e.getLocalizedMessage());
		}

		catch (Exception e) {

			Reporter.log(" Step 10 failed" + e.getLocalizedMessage());
		}

		// Step 11
		util.customAlertByJS("Step 11 started");
		Reporter.log("Step 11 started");
		
		try {

			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();
			String WIwithoutCustCOntact11 = BasePage.getTestDataValue("RTEWIWithNoCustContact");

			WebElement WIwithoutContact1 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact11 + "')]"));

			util.click(WIwithoutContact1);

			WebElement editRRlinkELe = driver.findElement(By.xpath("//a[contains(text(),'Edit')]"));

			util.click(editRRlinkELe);
			Select soxReqInfoDropdown = new Select(soxReqInfoEle);
			soxReqInfoDropdown.selectByIndex(5);

			driver.findElement(By.xpath("//*[@id=\"00Nq0000000iAXW\"]"))
					.sendKeys(BasePage.getTestDataValue("PremiseAddress"));

			driver.findElement(By.xpath("//*[@name=\"save\"]")).click();
			Thread.sleep(1000);

			Assert.assertTrue(CustContactLogEntryEle.isDisplayed());


			String CCLOGMessage = CustContactLogEntryEle.getText();

			Assert.assertTrue(CCLOGMessage.contains("With ABAC"));
			Reporter.log(CCLOGMessage);

			Reporter.log("Step 11 passed");
			Reporter.log("Comments are updated on Related Records");
		}

		catch (AssertionError e) {
			Reporter.log("Step 11 failed" + e.getLocalizedMessage());
		}

		catch (Exception e) {
			Reporter.log("Step 11 failed" + e.getLocalizedMessage());
		}

		// Step 12
		util.customAlertByJS(" Step 12 started");
		Reporter.log("Step 12 started");
		
		try {
			util.switchToTab(0);
			clickOnWorkItems();
			selectMyWI();

			String WIwithoutCustCOntact2 = BasePage.getTestDataValue("RTEWIWithNoCustContact");

			WebElement WIwithoutContact3 = driver
					.findElement(By.xpath("//*[contains(text(),'" + WIwithoutCustCOntact2 + "')]"));

			util.click(WIwithoutContact3);

			util.click(customerRelatedRecNamEle);

			String CCLOGMessage = CustContactLogEntryEle.getText();

			String CCLOG = util.getText(CCLOGNumberEle);
			Reporter.log(" CCLOG Number is : " + CCLOG);

			String ABACLogEntry = CustContactLogEntryEle.getText();
			util.highlighElement(CustContactLogEntryEle);

			Assert.assertTrue(CustContactLogEntryEle.isDisplayed());
			Reporter.log(ABACLogEntry);

			util.highlighElement(CustContactLogEntryEle);

			util.click(customerContactLogNameEle);

			WebElement CClogLogIDELe = driver.findElement(By.xpath("//*[@id=\"00Nq0000000jVKD_ileinner\"]"));

			util.scrollIntoView(CClogLogIDELe);

			String CClogLogID = CClogLogIDELe.getText();

			driver.navigate().back();
			viewCCBCustomerContactEle.click();
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
			
			WebElement CCBLogComment= driver.findElement(By.xpath("//*[@id=\"CC_LOG$CC_LOG_CONTENT\"]"));
			util.highlighElement(CCBLogComment);
			
			Reporter.log(CCBLogComment.getText());

			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			driver.switchTo().frame("main");
			Thread.sleep(2000);
			driver.switchTo().frame("tabMenu");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//*[@title=\"Characteristics\"]")).click();

			Thread.sleep(1000);
			Reporter.log("Step 12 passed");
			Reporter.log("CCB Csutomer Contact Comments is updated");

		} catch (AssertionError e) {
			Reporter.log("Step 12 failed" + e);

		}

		catch (Exception e) {
			Reporter.log("Step 12 failed" + e);

		}

		// Step 13

		util.customAlertByJS(" Step 13 started- logging out");
		Reporter.log("Step 13 started- logging out");
		util.switchToTab(0);
		WebElement logOutMenu = driver.findElement(By.xpath("//*[@id=\"userNav-arrow\"]"));
		util.click(logOutMenu);
		WebElement logoutButton = driver.findElement(By.xpath("//*[@title=\"Logout\"]"));
		util.click(logoutButton);
		Reporter.log(" logout done successfully");
		return this;

	}
}
