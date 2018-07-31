package com.pge.COC.ReUsable.utility;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import com.automation.framework.exceptions.DriverScriptException;

public class RunTestNG {
	
	public static Map<String, String> testsRunnerMap;
	public static Map<String, String> testsResultsMap;
	public final static Logger logger = Logger.getLogger(RunTestNG.class);
	
	/**
	 * Gets the global config excel values.
	 *
	 * @param file
	 *            the file
	 * @return the global config map
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	public static void getTestsConfigMap(String file) throws DriverScriptException {
		testsRunnerMap = new HashMap<String, String>();
		testsResultsMap = new HashMap<String, String>();
		HSSFWorkbook wb = null;
		if (null != file) {
			try {
				FileInputStream fi = new FileInputStream(new File("Execution_Interface.xls"));
				//Get the workbook instance for XLS file
	            wb = new HSSFWorkbook(fi);
			} catch (EncryptedDocumentException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();
			}
			
			Sheet sh = wb.getSheetAt(0);
			int rows = sh.getPhysicalNumberOfRows();
				for (int row = 5; row < rows; row++) {
					Row testRow = sh.getRow(row);
					if(null != testRow){
						String paramName = testRow.getCell(0).toString();
						if(paramName.equalsIgnoreCase("EndOfSheet")){
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
	    	FileOutputStream fileOut = new FileOutputStream("Execution_Interface.xls");
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

	public static HSSFCellStyle applyPassStyle(String rowNumber) {
		HSSFWorkbook wb = null;
		HSSFCellStyle stylePass = null;
		try {
			FileInputStream fi = new FileInputStream(new File("Execution_Interface.xls"));
			//Get the workbook instance for XLS file
            wb = new HSSFWorkbook(fi);
            stylePass = passStyle(wb);
            Sheet sh = wb.getSheetAt(0);
            sh.getRow(Integer.parseInt(rowNumber)).createCell(5).setCellStyle(stylePass);
            sh.getRow(Integer.parseInt(rowNumber)).getCell(5).setCellValue("PASS");
            FileOutputStream out =
                    new FileOutputStream(new File("Execution_Interface.xls"));
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

	private static HSSFCellStyle passStyle(HSSFWorkbook wb) {
		HSSFCellStyle stylePass;
		stylePass = wb.createCellStyle();
		stylePass.setFillForegroundColor(HSSFColor.DARK_GREEN.index);
		stylePass.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return stylePass;
	}
	
	private static HSSFCellStyle failStyle(HSSFWorkbook wb) {
		HSSFCellStyle styleFail;
		styleFail = wb.createCellStyle();
		styleFail.setFillForegroundColor(HSSFColor.DARK_RED.index);
		styleFail.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return styleFail;
	}

	public static HSSFCellStyle applyFailStyle(String rowNumber) {
		HSSFWorkbook wb = null;
		HSSFCellStyle styleFail = null;
		try {
			FileInputStream fi = new FileInputStream(new File("Execution_Interface.xls"));
			//Get the workbook instance for XLS file
            wb = new HSSFWorkbook(fi);
            styleFail = failStyle(wb);
            Sheet sh = wb.getSheetAt(0);
            sh.getRow(Integer.parseInt(rowNumber)).createCell(5).setCellStyle(styleFail);
            sh.getRow(Integer.parseInt(rowNumber)).getCell(5).setCellValue("FAIL");
            
            FileOutputStream out =
                    new FileOutputStream(new File("Execution_Interface.xls"));
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
	}
	
	/**
	 * @param args
	 * @throws DriverScriptException
	 */
	public static void main(String[] args) throws DriverScriptException {
		RunTestNG.getTestsConfigMap("Execution_Interface.xls");
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new FileInputStream("Execution_Interface.xls"));
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ss");
		String reportHtML = "ExecutionReport_"+simpleDateFormat.format(new Date())+".html";
		
		for (int i = 1;i<=rows;i++){
		Row row = sh.getRow(i-1);
		if("EndOfSheet".equalsIgnoreCase(row.getCell(0).toString())){
			break;
		}
		switch (row.getCell(0).toString()) {
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
		}
		

		}
		
		test.setXmlClasses(classes) ;
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
		try {
			wb = WorkbookFactory.create(new FileInputStream("Execution_Interface.xls"));
			sh = wb.getSheetAt(0);
			CreationHelper createHelper = wb
				      .getCreationHelper();
			CellStyle hlinkstyle = wb.createCellStyle();
		      Font hlinkfont = wb.createFont();
		      hlinkfont.setUnderline(Font.U_SINGLE);
		      hlinkfont.setColor(HSSFColor.BLUE.index);
		      hlinkstyle.setFont(hlinkfont);
			org.apache.poi.ss.usermodel.Hyperlink link = (org.apache.poi.ss.usermodel.Hyperlink)createHelper
				      .createHyperlink(Hyperlink.LINK_FILE);
			link.setAddress(FileUtils.getFile("report//"+reportHtML).getAbsolutePath());
			sh.getRow(3).createCell(1).setCellValue("Report");
			sh.getRow(3).getCell(1).setHyperlink(link);
			sh.getRow(3).getCell(1).setCellStyle(hlinkstyle);
			FileOutputStream out =
                    new FileOutputStream(new File("Execution_Interface.xls"));
            wb.write(out);
            out.close();
			wb.close();
			Desktop.getDesktop().open(new File("Execution_Interface.xls"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}