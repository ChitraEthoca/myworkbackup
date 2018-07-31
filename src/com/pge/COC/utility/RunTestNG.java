package com.pge.COC.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.TestDataNotFoundException;

public class RunTestNG {

	public static Map<String, String> testsRunnerMap;
	public static Map<String, String> testsResultsMap;
	public final static Logger logger = Logger.getLogger(RunTestNG.class);
	public static String  folderName="";
	/**
	 * Gets the global config excel values.
	 *
	 * @param file
	 *            the file
	 * @return the global config map
	 * 
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	public static void getTestsConfigMap(String file) throws DriverScriptException {
		testsRunnerMap = new HashMap<String, String>();
		testsResultsMap = new HashMap<String, String>();
		XSSFWorkbook wb = null;
		if (null != file) {
			try {
				//FileInputStream fi = new FileInputStream(new File("./testData/testData.xlsx"));
				FileInputStream fi = new FileInputStream(new File(file));
				//Get the workbook instance for XLS file
				wb = new XSSFWorkbook(fi);
			} catch (EncryptedDocumentException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();
			}
			
			Sheet sh = null;
			int rows = 0;
			
			for (int allModuleIterator = 0; allModuleIterator <= 2; allModuleIterator++){
				sh = wb.getSheetAt(allModuleIterator);
				rows = sh.getLastRowNum();
				
				for (int row = 3; row <= rows-1; row++) {
					
					Row testRow = sh.getRow(row);
					if(null != testRow){
						String paramName = testRow.getCell(3).toString();
						String executableFlag = testRow.getCell(0).toString();
						System.out.println(executableFlag);
						if(executableFlag.contains("End")){
							break;
						}
						
						String paramValue = String.valueOf(row);
						testsRunnerMap.put(paramName, paramValue);
						System.out.println(paramName+"****"+paramValue);
					}
				}
			}
		}
		try {
			// Write the output to a file
			//FileOutputStream fileOut = new FileOutputStream("./testData/testData.xlsx");
			//wb.write(fileOut);
			//fileOut.close();
			wb.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
		}
	}
	
	public static void modifyHTML(String filePath, String oldString, String newString){
		try {
			File fileToBeModified = new File(filePath);
			String oldContent = "";
			BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) 
			{
		        oldContent = oldContent + line + System.lineSeparator();
		        line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(oldString, newString);
			FileWriter writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
			reader.close();
			writer.close();
		}
		catch (Exception e) {
			System.out.println("Error while modifying HTML for SS : " + e);
		}
	}

	/**
	 * @param args
	 * @throws DriverScriptException
	 * @throws TestDataNotFoundException 
	 */
	public static void main(String[] args) throws DriverScriptException, TestDataNotFoundException {
		
		/*String sExecutionModule = null;
		
		XSSFWorkbook globalWB = null;
		if (null != "./config/GlobalParameter.xlsx") {
			try {
				FileInputStream globalFI = new FileInputStream(new File("./config/GlobalParameter.xlsx"));
				//Get the workbook instance for XLS file
				globalWB = new XSSFWorkbook(globalFI);
			} catch (EncryptedDocumentException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();
			}

			Sheet globalSH = globalWB.getSheetAt(0);
			int globalRows = globalSH.getPhysicalNumberOfRows();
			for (int globalRowIterator = 1; globalRowIterator < globalRows-1; globalRowIterator++) {
				Row globalTestRow = globalSH.getRow(globalRowIterator);
				if(null != globalTestRow){
					String globalVariable_Key = globalTestRow.getCell(0).toString();
					String globalVariable_Value = globalTestRow.getCell(1).toString();
					if(globalVariable_Key.equalsIgnoreCase("End")){
						break;
					} else if (globalVariable_Key.equalsIgnoreCase("ESA_Module")){
						sExecutionModule = globalVariable_Value;
						System.out.println("Execution Module is selected as : " + sExecutionModule);
						break;
					}
				}
			}
		}*/
		
		RunTestNG.getTestsConfigMap("./testData/testData.xlsx");
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new FileInputStream("./testData/testData.xlsx"));
		} catch (EncryptedDocumentException e) {e.printStackTrace();
		} catch (InvalidFormatException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}

		XmlSuite suite = new XmlSuite();
		XmlTest test = new XmlTest(suite);
		List<XmlClass> classes = new ArrayList<XmlClass>();
		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss");
		String reportHtML = "Summary"+".html";
		
		Sheet sh = null;
		Row row = null;
		
		/*if (sExecutionModule.equalsIgnoreCase("PRODUCT & MEASURE")){
			sh = wb.getSheetAt(0);
		} else if (sExecutionModule.equalsIgnoreCase("CUSTOMER ACQUISITION")){
			sh = wb.getSheetAt(1);
		} else if (sExecutionModule.equalsIgnoreCase("WORKFLOW")){
			sh = wb.getSheetAt(2);
		} else if (sExecutionModule.equalsIgnoreCase("ESA WEBFORM")){
			sh = wb.getSheetAt(3);
		} else if (sExecutionModule.equalsIgnoreCase("SPRINT1_E2E")){
			sh = wb.getSheetAt(4);
		} else {
			throw new TestDataNotFoundException("ESA_Module is not selected properly in GlobalParameter.xlsx file");
		}*/
		
		for (int allModuleIterator = 0; allModuleIterator <=2; allModuleIterator++){
			sh = wb.getSheetAt(allModuleIterator);
			
			int rows = sh.getPhysicalNumberOfRows();
			
			int col_exeFlag=0;
			int col_Iteration=0;
			
			if (allModuleIterator == 0){
				row = sh.getRow(0);
				suite.setName(row.getCell(1).toString());
				
				row = sh.getRow(1);
				test.setName(row.getCell(1).toString());
			}
			
			row = sh.getRow(2);
			
			for (int j = 0; j <row.getLastCellNum(); j++) {
				if (row.getCell(j).toString().equalsIgnoreCase("Execute")){
					col_exeFlag = j;
					break;
				}
			}
			
			for (int z = 0; z <row.getLastCellNum(); z++) {
				if (row.getCell(z).toString().equalsIgnoreCase("Iteration")){
					col_Iteration = z;
					break;
				}
			}
			
			for (int i = 1;i<=rows;i++){
				Row currentRow = sh.getRow(i-1);
				if("End".equalsIgnoreCase(currentRow.getCell(0).toString())){
					break;
				}
				
				if (currentRow.getCell(col_exeFlag).toString().equals("Y")) {
					int iTotalIteration = Integer.parseInt(currentRow.getCell(col_Iteration).toString());
					for (int i_noOfIteration=1;i_noOfIteration<=iTotalIteration;i_noOfIteration++){
						classes.add(new XmlClass("com.pge.COC.tests."+currentRow.getCell(3).toString()));
					}
				}
			}
		}
		
		//suite.setName("Regression");
		//test.setName("ESA/EPO Re-Platform");
		test.setXmlClasses(classes);
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		try {
			wb.close();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		createReportFolderStructure();
		tng.run();
		
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			
			InputStream inputStream= new FileInputStream( new File("test-output\\\\testng-results.xml"));
			Reader reader = new InputStreamReader(inputStream,"UTF-8");
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(is);
			TransformerFactory transformerFactory =	TransformerFactory.newInstance();
			Transformer transformer1 = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult( new File("test-output\\\\testng-results.xml"));
			transformer1.transform(source, result);
			Source xslDoc = new StreamSource("config\\\\ExecutionReport.xsl");
			Source xmlDoc = new StreamSource("test-output\\\\testng-results.xml");
			
			OutputStream htmlFile = new FileOutputStream(new File("report//"+folderName+"//"+reportHtML));
			Transformer transformer = tFactory.newTransformer(xslDoc);
			transformer.transform(xmlDoc, new StreamResult(htmlFile));
			
			Thread.sleep(2000);
			
			//modifyHTML("report//"+reportHtML,".png%0A",".png");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static void createReportFolderStructure()
	{
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		folderName = (dateFormat.format(date));
		File dir1 = new File("./report/"+folderName);
		dir1.mkdir();
		
		File dir2 = new File("./report/"+folderName+"/SS");
		dir2.mkdir();
		System.setProperty("ReportFolderName", folderName);
	}
	
}