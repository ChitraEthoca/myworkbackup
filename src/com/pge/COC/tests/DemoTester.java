/*package com.pge.COC.tests;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.framework.actionInterpreter.COC;
import com.automation.framework.core.BaseTest;
import com.automation.framework.core.COCDriverScript;
import com.automation.framework.core.Status;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.utilities.ObjectRead;
import com.opencsv.CSVReader;
import com.pge.COC.pageObjects.FinalSubmission;
import com.pge.COC.pageObjects.HomePage;
import com.pge.COC.pageObjects.SelectQuery;
import com.pge.COC.pageObjects.UsageFactorMaintenance;
import com.pge.COC.properties.SQMDProperties;

public class DemoTester extends BaseTestClass {
	
	private static String almID = "";
	ObjectRead objectRead;
	BaseTest base;
	SQMDProperties SQMDProperties;
	
	 *//**
	    * Author:Bikash Shaw
	    *//*
		@DataProvider(name = "testDataProvider")
		public Object[][] testDataProvider() throws DriverScriptException {
			base = new BaseTest(almID);
			return ObjectRead.getConsolidatedTestDataArray(this.getClass().getSimpleName(), "SPR85");
		}
		
		
		@Test(dataProvider = "testDataProvider")
		public void xml(Hashtable<String, String> testData, ITestContext context) throws Exception {
			try {
				super.initializeDriver(testData);
				//Navigate to URL and Maximize
				super.launchApplication(COCDriverScript.globalParamMap.get("url"));

				HashMap<String, String> localData = new HashMap<String, String>();
				HomePage homepage=new HomePage(driver,localData);
				FinalSubmission finalsubmission = new FinalSubmission(driver, localData);
				SelectQuery selectQuery = new SelectQuery(driver, localData);
				UsageFactorMaintenance usagefactormaintenance= new UsageFactorMaintenance(driver,localData);
				
				
				selectQuery.xmlValidationSendRequest(testData);
				
       			usagefactormaintenance.parseXML();
				
				usagefactormaintenance.copyFile("P:\\New folder\\auto.dlp", "P:\\New folder1\\auto.dlp");
				
				
				CSVReader reader = new CSVReader(new FileReader("P:\\New folder1\\Test.csv"));
				
				List<String[]> li=reader.readAll();
				System.out.println("Total rows which we have is "+li.size());
				
				Iterator<String[]>i1= li.iterator();
				
				
				while(i1.hasNext()){
				
					String[] str=i1.next();
					
					System.out.print(" Values are ");

					 for(int i=0;i<str.length;i++)
					{

					System.out.print(" "+str[i]);

					}
					 System.out.println("   ");
				}
				
		//		COC.webAdaptor(elementEnum, args);
				
				
				//copying multiple files
				

File source = new File("P:\\New folder\\");
System.out.println(source);
File dest = new File("\\\\go310\\Cis\\Data01\\CCB\\SM 1.0 Meter Deployment Test Output and Data Files\\TCOE - Transition Webex Recordings\\SQMD\\D2Test\\");
System.out.println(dest);
File[] files = source.listFiles(); 

for (int i=0; i < files.length; i++){

System.out.println(source+"\\"+files[i].getName());

String x=(source+"\\"+files[i].getName());
String y=(dest + "\\"+ files[i].getName());

File s=new File(x);
File d= new File(y);
FileUtils.copyFile(s, d);
}
				
				
				
				
				
				
				
				
				
				System.out.println("test");
				driver.navigate().to("https://www.google.com");
			}catch(Exception e){
				COCDriverScript.logMessage("Execution", "Error in execution", Status.FAIL);
				throw new Exception("Error in Execution");
			} finally {
				//Call teardown method
				 super.tearDown();
			}

					}
}
*/