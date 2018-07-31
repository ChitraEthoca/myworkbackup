package com.pge.COC.utility;

//import java.awt.Color;
//import java.awt.Desktop;
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
//import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.sl.usermodel.Hyperlink;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CreationHelper;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import com.automation.framework.exceptions.DriverScriptException;

public class RunTestNG_Old {

	public static Map<String, String> testsRunnerMap;
	public static Map<String, String> testsResultsMap;
	public final static Logger logger = Logger.getLogger(RunTestNG_Old.class);

	/**
	 * Gets the global config excel values
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
				FileInputStream fi = new FileInputStream(new File("./testData/testData.xlsx"));
				//Get the workbook instance for XLS file
				wb = new XSSFWorkbook(fi);
			} catch (EncryptedDocumentException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();
			}

			Sheet sh = wb.getSheetAt(0);
			int rows = sh.getPhysicalNumberOfRows();
			for (int row = 1; row < rows-1; row++) {
				Row testRow = sh.getRow(row);
				if(null != testRow){
					String paramName = testRow.getCell(3).toString();
					String executableFlag = testRow.getCell(0).toString();
					if(executableFlag.equalsIgnoreCase("End")){
						break;
					}
					
					String paramValue = String.valueOf(row);
					testsRunnerMap.put(paramName, paramValue);
					System.out.println(paramName+"****"+paramValue);
				}
			}
		}
		try {
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream("./testData/testData.xlsx");
			wb.write(fileOut);
			fileOut.close();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
		}
	}

	/*public static XSSFCellStyle applyPassStyle(String rowNumber) {
		XSSFWorkbook wb = null;
		XSSFCellStyle stylePass = null;
		try {
			FileInputStream fi = new FileInputStream(new File("./testData/testData.xlsx"));
			//Get the workbook instance for XLS file
			wb = new XSSFWorkbook(fi);
			stylePass = passStyle(wb);
			Sheet sh = wb.getSheetAt(0);
			sh.getRow(Integer.parseInt(rowNumber)).createCell(5).setCellStyle(stylePass);
			sh.getRow(Integer.parseInt(rowNumber)).getCell(5).setCellValue("PASS");
			FileOutputStream out =
					new FileOutputStream(new File("./testData/testData.xlsx"));
			wb.write(out);
			out.close();
		} catch (EncryptedDocumentException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		} finally{
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return stylePass;
	}

	private static XSSFCellStyle passStyle(XSSFWorkbook wb) {
		XSSFCellStyle stylePass;
		stylePass = wb.createCellStyle();
		stylePass.setFillForegroundColor(IndexedColors.DARK_GREEN.index);
		stylePass.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		return stylePass;
	}

	private static XSSFCellStyle failStyle(XSSFWorkbook wb) {
		XSSFCellStyle styleFail;
		styleFail = wb.createCellStyle();
		styleFail.setFillForegroundColor(IndexedColors.DARK_RED.index);
		styleFail.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		return styleFail;
	}

	public static XSSFCellStyle applyFailStyle(String rowNumber) {
		XSSFWorkbook wb = null;
		XSSFCellStyle styleFail = null;
		try {
			FileInputStream fi = new FileInputStream(new File("./testData/testData.xlsx"));
			//Get the workbook instance for XLS file
			wb = new XSSFWorkbook(fi);
			styleFail = failStyle(wb);
			Sheet sh = wb.getSheetAt(0);
			sh.getRow(Integer.parseInt(rowNumber)).createCell(5).setCellStyle(styleFail);
			sh.getRow(Integer.parseInt(rowNumber)).getCell(5).setCellValue("FAIL");

			FileOutputStream out =
					new FileOutputStream(new File("./testData/testData.xlsx"));
			wb.write(out);
			out.close();
		} catch (EncryptedDocumentException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		} finally{
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return styleFail;
	}*/
	
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
	 */
	public static void main(String[] args) throws DriverScriptException {
		RunTestNG_Old.getTestsConfigMap("./testData/testData.xlsx");
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
		Sheet sh = wb.getSheetAt(0);
		int rows = sh.getPhysicalNumberOfRows();
		int col_exeFlag=0;
		int col_Iteration=0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss");
		String reportHtML = "ExecutionReport_"+simpleDateFormat.format(new Date())+".html";
		
		Row row = sh.getRow(0);
		
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
			if("End".equalsIgnoreCase(row.getCell(0).toString())){
				break;
			}
			
			if (currentRow.getCell(col_exeFlag).toString().equals("Y")) {
				int iTotalIteration = Integer.parseInt(currentRow.getCell(col_Iteration).toString());
				for (int i_noOfIteration=1;i_noOfIteration<=iTotalIteration;i_noOfIteration++){
					classes.add(new XmlClass("com.pge.COC.tests."+currentRow.getCell(3).toString()));
				}
			}
			
			/*switch (row.getCell(0).toString()) {
			case "JDK Installation Directory":
				break;
			case "Suite Name":
				suite.setName(row.getCell(1).toString());
				break;
			case "Test Name":
				test.setName(row.getCell(1).toString());
				break;
			case "Automation Script Name":
				for (int j = 0; j <row.getLastCellNum(); j++) {
					switch (row.getCell(j).toString()) {
					case "ALM_ID":
						break;
					case "Manual Test Case Name":
						break;
					case "Test Case Description":
						break;
					case "Execute(Y/N)":
						col_exeFlag = j;
						break;
					default:
						break;
					}
				}
				break;
			default:
				if (row.getCell(col_exeFlag).toString().equals("Y")) {
					classes.add(new XmlClass("com.pge.COC.tests."+row.getCell(0).toString()));	
				}
				break;
			}*/


		}
		
		suite.setName("Regression");
		test.setName("EV Site Host Tool");
		test.setXmlClasses(classes);
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		try {
			wb.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tng.run();
		/*try {
			wb = WorkbookFactory.create(new FileInputStream("./testData/testData.xlsx"));
			//XSSFColor color_Blue = new XSSFColor(Color.BLUE);
			sh = wb.getSheetAt(0);
			CreationHelper createHelper = wb.getCreationHelper();
			CellStyle hlinkstyle = wb.createCellStyle();
			Font hlinkfont = wb.createFont();
			hlinkfont.setUnderline(Font.U_SINGLE);
			//hlinkfont.setColor(XSSFColor.BLUE.index);
			hlinkfont.setColor(IndexedColors.BLUE.index);
			//hlinkfont.setColor(Color.BLUE);
			hlinkstyle.setFont(hlinkfont);
			org.apache.poi.ss.usermodel.Hyperlink link = (org.apache.poi.ss.usermodel.Hyperlink)createHelper.createHyperlink(Hyperlink.LINK_FILE);
			System.out.println(FileUtils.getFile("report//"+reportHtML).getAbsolutePath());
			link.setAddress(FileUtils.getFile("report//"+reportHtML).getAbsolutePath());
			sh.getRow(3).createCell(1).setCellValue("Report");
			sh.getRow(3).getCell(1).setHyperlink(link);
			sh.getRow(3).getCell(1).setCellStyle(hlinkstyle);
			FileOutputStream out = new FileOutputStream(new File("./testData/testData.xlsx"));
			wb.write(out);
			out.close();
			wb.close();
			Desktop.getDesktop().open(new File("./testData/testData.xlsx"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();

			//File file = new File("test-output\\\\testng-results.xml");
			InputStream inputStream= new FileInputStream( new File("test-output\\\\testng-results.xml"));
			Reader reader = new InputStreamReader(inputStream,"UTF-8");
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(is);
			TransformerFactory transformerFactory =
					TransformerFactory.newInstance();
			Transformer transformer1 =
					transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result =
					new StreamResult( new File("test-output\\\\testng-results.xml"));
			transformer1.transform(source, result);
			Source xslDoc = new StreamSource("config\\\\ExecutionReport.xsl");
			Source xmlDoc = new StreamSource("test-output\\\\testng-results.xml");

			//			String outputFileName =D2DriverScript.globalParamMap.get("report");
			OutputStream htmlFile = new FileOutputStream(new File("report//"+reportHtML));
			Transformer transformer = tFactory.newTransformer(xslDoc);
			transformer.transform(xmlDoc, new StreamResult(htmlFile));
			
			Thread.sleep(2000);
			
			modifyHTML("report//"+reportHtML,".png%0A",".png");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}