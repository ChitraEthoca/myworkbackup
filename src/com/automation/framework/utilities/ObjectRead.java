package com.automation.framework.utilities;

/**
 * @author manoj
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;

public class ObjectRead {

	public String alMID;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ObjectRead.class);
	private static Map<String, JSONObject> mapObject = new HashMap<String, JSONObject>();

	/**
	 * @param almID
	 * @throws DriverScriptException 
	 * @throws JSONException
	 */
	public ObjectRead(String sheetName) throws DriverScriptException {
		getMapFromPageObject(sheetName);
	}

	/**
	 * Gets the object.
	 *
	 * @param realName the real name
	 * @return the object
	 * @throws ObjectNameNotFoundException the object name not found exception
	 */
	public static By getObject(String realName)
			throws ObjectNameNotFoundException {
		if (LOG.isDebugEnabled()) {
			COCDriverScript.logMessage("info","openWorkBook(File) - start"); //$NON-NLS-1$
		}
		return getObject(realName,null);

	}

	/**
	 * Gets the object.
	 *
	 * @param realName
	 *            the real name
	 * @return the object
	 * @throws ObjectNameNotFoundException
	 */
	public static By getObject(String realName, String xpathValue)
			throws ObjectNameNotFoundException {
		if (LOG.isDebugEnabled()) {
			COCDriverScript.logMessage("info","openWorkBook(File) - start"); //$NON-NLS-1$
		}
		String locatorType;
		String objectPath;

		JSONObject object = (JSONObject) mapObject.get(realName);
		COCDriverScript.logMessage("info","object mapping..");
		if (object != null) {
			try {
				locatorType = object.getString("idType");
				objectPath = object.getString("symbolicName");
				if (xpathValue != null) {
					if (objectPath.contains("$$$")) {
						String[] strs = objectPath.split(Pattern.quote("$$$"));
						objectPath = strs[0] + xpathValue + strs[2];

					} else {
						COCDriverScript.logMessage("error","No $$$ symbol to take input value for xpath");
						throw new ObjectNameNotFoundException(
								new Throwable(
										"No $$$ symbol to handle input value for xpath"));
					}
				}
				COCDriverScript.logMessage("info",realName+"'s object type is "+locatorType+" and object is " + objectPath);
			} catch (JSONException e) {
				COCDriverScript.logMessage("error","Error in handling Json");
				throw new ObjectNameNotFoundException(new Throwable(realName
						+ " Object values may not be provided "));

			}
			switch (locatorType.toLowerCase()) {
			case "xpath":
				COCDriverScript.logMessage("info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					COCDriverScript.logMessage("info","getObject() - end"); //$NON-NLS-1$
				}
				return By.xpath(objectPath);
			case "css":
				COCDriverScript.logMessage("info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					COCDriverScript.logMessage("info","getObject() - end"); //$NON-NLS-1$
				}
				return By.cssSelector(objectPath);
			case "id":
				COCDriverScript.logMessage("info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					COCDriverScript.logMessage("info","getObject() - end"); //$NON-NLS-1$
				}
				return By.id(objectPath);
			case "name":
				COCDriverScript.logMessage("info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					COCDriverScript.logMessage("info","getObject() - end"); //$NON-NLS-1$
				}
				return By.name(objectPath);
			case "classname":
				COCDriverScript.logMessage("info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					COCDriverScript.logMessage("info","getObject() - end"); //$NON-NLS-1$
				}
				return By.className(objectPath);
			case "tagname":
				COCDriverScript.logMessage("info","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					COCDriverScript.logMessage("info","getObject() - end"); //$NON-NLS-1$
				}
				return By.tagName(objectPath);
			case "linktext":
				COCDriverScript.logMessage("info","object mapping..");
				if (LOG.isDebugEnabled()) {
					COCDriverScript.logMessage("info","getObject() - end"); //$NON-NLS-1$
				}
				return By.linkText(objectPath);
			default:
				COCDriverScript.logMessage("error","returning objectpath..");
				if (LOG.isDebugEnabled()) {
					COCDriverScript.logMessage("info","getObject() - end"); //$NON-NLS-1$
				}
				throw new ObjectNameNotFoundException(new Throwable(locatorType+"Invalid locator type"));

			}
		} else {
			COCDriverScript.logMessage("error",realName
					+ " real name doesnot exists in object Repository");
			if (LOG.isDebugEnabled()) {
				COCDriverScript.logMessage("info","getObject() - end"); //$NON-NLS-1$
			}
			throw new ObjectNameNotFoundException(new Throwable(realName
					+ " real name doesnot exists in object Repository"));
		}

	}


	/**
	 * Gets the map object.
	 *
	 * @return the map object
	 * @throws DriverScriptException 
	 */
	public void getMapFromPageObject(String sheetName) throws DriverScriptException {
		if (LOG.isDebugEnabled()) {
			COCDriverScript.logMessage("info","getMapFromPageObject() - start"); //$NON-NLS-1$
		}
		COCDriverScript.logMessage("info","Getting Map object");
		try {
			XLUtil xlUtil = new XLUtil();
			xlUtil.openWorkBook(new File(COCDriverScript.globalParamMap.get("objectRepository")+ "ObjectRepository.xlsx"));
			int totalRowCnt = COCDriverScript.xlUtil.getTotalRowCount("ObjectRepository")+1;
			for (int row = 1; row < totalRowCnt; row++) {
				String slNo = COCDriverScript.xlUtil.getCellValue("ObjectRepository",row, 0);
				if ("EndOfSheet".equals(slNo)) {
					break;
				}
				String pageName = COCDriverScript.xlUtil.getCellValue("ObjectRepository", row, 1);
				
				if (pageName.equalsIgnoreCase(sheetName)){
					String realName = COCDriverScript.xlUtil.getCellValue("ObjectRepository", row, 2);
					String idType = COCDriverScript.xlUtil.getCellValue("ObjectRepository",row, 3);
					String symbolicName = COCDriverScript.xlUtil.getCellValue("ObjectRepository", row, 4);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("symbolicName", symbolicName);
					jsonObject.put("idType", idType);
					mapObject.put(realName, jsonObject);
				}
			}
		} catch (JSONException e) {
			COCDriverScript.logMessage("error","getMapFromPageObject()"+e); //$NON-NLS-1$
			e.printStackTrace();
		}
		if (LOG.isDebugEnabled()) {
			COCDriverScript.logMessage("info","getMapObject() - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Gets the test data array.
	 *
	 * @param testDatafile the test datafile
	 * @return the test data array
	 * @throws DriverScriptException 
	 */
	public static Object[][] getTestDataArray(String testCaseName, String testDatafile) throws DriverScriptException{   
		Object[][] testDataArray = null;
		Hashtable<String, String> hashtable = null;
		XLUtil xlUtil = new XLUtil();
		String execute;
		String sheetName = "testData";
		COCDriverScript.testDataFile = testDatafile;
		try{
			String fileName =  searchFile(new File(COCDriverScript.globalParamMap.get("testDataPath")),testDatafile+".xlsx");
			if(fileName!=null){ 	
				xlUtil.openWorkBook(new File(fileName));
				if (xlUtil.isSheetExists(sheetName)) {
					COCDriverScript.logMessage("info",sheetName+" sheet exists in object Repository workbook");
					int cj,ci;
					int startRow = 1;
					int startCol = 1;
					boolean isSkipped = false;
					int totalExecutableRows = xlUtil.getTotalRowCountWithExecuteFlag(testCaseName,sheetName);
					int totalRows = xlUtil.getTotalRowCount(sheetName)+1;
					int totalCols = xlUtil.getTotalColumnsInRow(sheetName);
					testDataArray=new Object[totalExecutableRows][1];
					ci=0;
					for(int i=startRow; i < totalRows;i++,ci++){
						cj=0;
						execute = xlUtil.getCellValue(sheetName,
								i, 0);
						if ("EndOfSheet".equals(execute)) {
							break;
						}
						hashtable = new Hashtable<String, String>();
						for(int j=startCol; j < totalCols;j++,cj++){
							if(0 == cj
									&& !xlUtil.getCellValue(sheetName, i, j-1).equalsIgnoreCase("Y")) {
								isSkipped = true;
								ci=ci-1;
								break;
							} 
							hashtable.put(xlUtil.getCellValue(sheetName, 0, j), xlUtil.getCellValue(sheetName, i, j));
						}
						if(isSkipped){
							COCDriverScript.logMessage("info","Row "+(i+1)+" had been skipped");
							isSkipped = !isSkipped;
						} else{
							testDataArray[ci][0] = hashtable;
							COCDriverScript.logMessage("info",testDataArray[ci][0].toString());
						}
					}
				}
				COCDriverScript.logMessage("info", "Checking for the existence of the Business Components");
				if(xlUtil.isSheetExists("businesscomponents")){
					COCDriverScript.logMessage("info", "Business Components exists and parsing the Business component sheet.");
					getBusinessComponents(xlUtil);
					COCDriverScript.logMessage("info", "parsing the Business component sheet ends");
				}
			}else{
				COCDriverScript.logMessage("error",  testDatafile+ " file does not exists");
				throw new DriverScriptException(new Throwable(testDatafile+ " file does not exists"));
			}
		}catch(DriverScriptException exx){
			throw new DriverScriptException(new Throwable(exx.getCause()));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return(testDataArray);
	}

	/**
	 * Gets the test data array from a consolidated testData file
	 *
	 * @param testData sheet name
	 * @return the test data array
	 * @throws DriverScriptException 
	 * @author k2dj
	 */
	public static Object[][] getConsolidatedTestDataArray(String testCaseName, String testDataSheetName) throws DriverScriptException{   
		Object[][] testDataArray = null;
		Hashtable<String, String> hashtable = null;
		XLUtil xlUtil = new XLUtil();
		String execute;
		String sheetName = null;
		
		try{
			String fileName =  searchFile(new File(COCDriverScript.globalParamMap.get("testDataPath")),"//testData.xlsx");
			
			if(fileName!=null){
				xlUtil.openWorkBook(new File(fileName));
				File file=new File(COCDriverScript.globalParamMap.get("testDataPath")+"//testData.xlsx");
				FileInputStream fi=new FileInputStream(file);
				XSSFWorkbook wb=new XSSFWorkbook(fi);
				
				int totalCols=wb.getSheet(testDataSheetName).getRow(2).getLastCellNum();
//				System.out.println("Total Columns= "+totalCols);
//				for(int i=0;i<totalCols;i++)
//				{
//					System.out.println(i+": "+wb.getSheet(testDataSheetName).getRow(2).getCell(i).getStringCellValue());
//				}
				if (xlUtil.isSheetExists(testDataSheetName)) {
					COCDriverScript.logMessage("info",testDataSheetName+" sheet exists in testData.xlsx file");
					int cj,ci;
					int startRow = 1;
					int startCol = 1;
					boolean isSkipped = false;
					int totalExecutableRows = xlUtil.getTotalRowCountWithExecuteFlag(testCaseName,testDataSheetName);
					int totalRows = xlUtil.getTotalRowCount(testDataSheetName)+1;
					//int totalCols = xlUtil.getTotalColumnsInRow(testDataSheetName);
					
					
					testDataArray=new Object[totalExecutableRows][1];
					ci=0;
					for(int i=startRow; i < totalRows;i++,ci++){
						cj=0;
						execute = xlUtil.getCellValue(testDataSheetName,i, 0);
						if ("EndOfSheet".equals(execute)) {
							break;
						}
						hashtable = new Hashtable<String, String>();
						for(int j=startCol; j < totalCols;j++,cj++){
							if((xlUtil.getCellValue(testDataSheetName, i, 0).equalsIgnoreCase("Y") && xlUtil.getCellValue(testDataSheetName, i, 3).equalsIgnoreCase(testCaseName))) {
//								System.out.println(xlUtil.getCellValue(testDataSheetName, 2, j)+":"+xlUtil.getCellValue(testDataSheetName, i, j));
								hashtable.put(xlUtil.getCellValue(testDataSheetName, 2, j), xlUtil.getCellValue(testDataSheetName, i, j));
							}
							else {
								isSkipped = true;
								ci=ci-1;
								break;
							}
						}
						if(isSkipped){
							COCDriverScript.logMessage("info","Row "+(i+1)+" had been skipped");
							isSkipped = !isSkipped;
						} else{
							testDataArray[ci][0] = hashtable;
							COCDriverScript.logMessage("info",testDataArray[ci][0].toString());
						}
					}
				}
				else {
					COCDriverScript.logMessage("error", "Provided worksheet in testData.xlsx file does not exist");
					throw new DriverScriptException(new Throwable("Provided worksheet in testData.xlsx file does not exist"));
				}
			}else{
				COCDriverScript.logMessage("error", "testData.xlsx file does not exist");
				throw new DriverScriptException(new Throwable("testData.xlsx file does not exist"));
			}
		}catch(DriverScriptException exx){
			throw new DriverScriptException(new Throwable(exx.getCause()));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return(testDataArray);
	}
	
	/**
	 * Sets the data into testData file
	 *
	 * @param testCaseName
	 * @param testData sheet name
	 * @param cellValue
	 * @return void
	 * @throws DriverScriptException 
	 * @author k2dj
	 */
	public void putValueIntoTestDataSheet(String testCaseName, String testDataSheetName, String cellValue, String colHeader, String Exe_Medium, String Browser) throws DriverScriptException{   
		//Object[][] testDataArray = null;
		//Hashtable<String, String> hashtable = null;
		XLUtil xlUtil = new XLUtil();
		String execute;
		String sheetName = null;
		
		try{
			String fileName =  searchFile(new File(COCDriverScript.globalParamMap.get("testDataPath")),"testData.xlsx");
			
			if(fileName!=null){
				xlUtil.openWorkBook(new File(fileName));
				COCDriverScript.logMessage("info", "testData.xlsx file exists");
				if (xlUtil.isSheetExists(testDataSheetName)) {
					COCDriverScript.logMessage("info",testDataSheetName+" sheet exists in testData.xlsx file");
					int cj,ci;
					int startRow = 1;
					int startCol = 1;
					boolean isSkipped = false;
					int totalExecutableRows = xlUtil.getTotalRowCountWithExecuteFlag(testCaseName,testDataSheetName);
					int totalRows = xlUtil.getTotalRowCount(testDataSheetName)+1;
					int totalCols = xlUtil.getTotalColumnsInRow(testDataSheetName);
					int iRequiredColIndex = xlUtil.getColumnIndex(testDataSheetName, colHeader);
					//testDataArray=new Object[totalExecutableRows][1];
					ci=0;
					for(int i=startRow; i < totalRows;i++,ci++){
						cj=0;
						execute = xlUtil.getCellValue(testDataSheetName,i, 0);
						if ("EndOfSheet".equals(execute)) {
							break;
						}
						//hashtable = new Hashtable<String, String>();
						for(int j=startCol; j < totalCols;j++,cj++){
							if((xlUtil.getCellValue(testDataSheetName, i, 0).equalsIgnoreCase("Y") && xlUtil.getCellValue(testDataSheetName, i, 3).equalsIgnoreCase(testCaseName) && xlUtil.getCellValue(testDataSheetName, i, 4).equalsIgnoreCase(Exe_Medium) && xlUtil.getCellValue(testDataSheetName, i, 5).equalsIgnoreCase(Browser))) {
								//xlUtil.setCellValue(testDataSheetName, cellValue, i, 41);
								int iColIndex = xlUtil.getColumnIndex(testDataSheetName, colHeader);
								xlUtil.setCellValue(testDataSheetName, cellValue, i, iColIndex);
								break;
								//hashtable.put(xlUtil.getCellValue(testDataSheetName, 0, j), xlUtil.getCellValue(testDataSheetName, i, j));
							}
							/*else {
								isSkipped = true;
								ci=ci-1;
								break;
							}*/
						}
						/*if(isSkipped){
							COCDriverScript.logMessage("info","Row "+(i+1)+" had been skipped");
							isSkipped = !isSkipped;
						} else{
							testDataArray[ci][0] = hashtable;
							COCDriverScript.logMessage("info",testDataArray[ci][0].toString());
						}*/
					}
				}
				else {
					COCDriverScript.logMessage("error", "Provided worksheet in testData.xlsx file does not exist");
					throw new DriverScriptException(new Throwable("Provided worksheet in testData.xlsx file does not exist"));
				}
				xlUtil.saveWorkbook(new File(fileName));
			}else{
				COCDriverScript.logMessage("error", "testData.xlsx file does not exist");
				throw new DriverScriptException(new Throwable("testData.xlsx file does not exist"));
			}
		}catch(DriverScriptException exx){
			throw new DriverScriptException(new Throwable(exx.getCause()));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		//return(testDataArray);
	}
	
	/**
	 * Gets the test data sheet name corresponding to a test case name.
	 *
	 * @return the sheet name corresponding to a test case name
	 * @throws Exception
	 * @author k2dj
	 */
	public String getSheetName(String TestCaseName) throws Exception {

		//Object[][] testDataArray = null;
		//Hashtable<String, String> hashtable = null;
		XLUtil xlUtil = new XLUtil();
		//String execute;
		String sheetName = "TCMappingSheet";
		String mappedSheetName = null;
		//COCDriverScript.testDataFile = testDatafile;
		try{
			String fileName =  searchFile(new File(COCDriverScript.globalParamMap.get("testDataPath")),"testData.xlsx");
			if(fileName!=null){
				xlUtil.openWorkBook(new File(fileName));
				
				if (xlUtil.isSheetExists(sheetName)) {
					COCDriverScript.logMessage("info",sheetName+" sheet exists in testData.xlsx file");
					//int cj,ci;
					int startRow = 1;
					//int startCol = 1;
					//boolean isSkipped = false;
					//int totalExecutableRows = xlUtil.getTotalRowCountWithExecuteFlag(sheetName);
					int totalRows = xlUtil.getTotalRowCount(sheetName)+1;
					//int totalCols = xlUtil.getTotalColumnsInRow(sheetName);
					//testDataArray=new Object[totalExecutableRows][1];
					//ci=0;
					for(int i=startRow; i < totalRows;i++){
						//cj=0;
						/*execute = xlUtil.getCellValue(sheetName,
								i, 0);
						if ("EndOfSheet".equals(execute)) {
							break;
						}*/
						//hashtable = new Hashtable<String, String>();
						if (xlUtil.getCellValue(sheetName, i, 1).equalsIgnoreCase(TestCaseName)) {
							mappedSheetName = xlUtil.getCellValue(sheetName, i, 2);
						}
						/*for(int j=startCol; j < totalCols;j++,cj++){
							if(0 == cj
									&& !xlUtil.getCellValue(sheetName, i, j-1).equalsIgnoreCase("Y")) {
								isSkipped = true;
								ci=ci-1;
								break;
							} 
							hashtable.put(xlUtil.getCellValue(sheetName, 0, j), xlUtil.getCellValue(sheetName, i, j));
						}
						if(isSkipped){
							COCDriverScript.logMessage("info","Row "+(i+1)+" had been skipped");
							isSkipped = !isSkipped;
						} else{
							testDataArray[ci][0] = hashtable;
							COCDriverScript.logMessage("info",testDataArray[ci][0].toString());
						}*/
					}
				}
				else {
					COCDriverScript.logMessage("error", "Provided worksheet in testData.xlsx file does not exist");
					throw new DriverScriptException(new Throwable("Provided worksheet in testData.xlsx file does not exist"));
				}
				/*COCDriverScript.logMessage("info", "Checking for the existence of the Business Components");
				if(xlUtil.isSheetExists("businesscomponents")){
					COCDriverScript.logMessage("info", "Business Components exists and parsing the Business component sheet.");
					getBusinessComponents(xlUtil);
					COCDriverScript.logMessage("info", "parsing the Business component sheet ends");
				}*/
			}else{
				COCDriverScript.logMessage("error", "testData.xlsx file does not exist");
				throw new DriverScriptException(new Throwable("testData.xlsx file does not exist"));
			}
		}catch(DriverScriptException exx){
			throw new DriverScriptException(new Throwable(exx.getCause()));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return mappedSheetName;
	}

	/**
	 * Gets the business action by almid.
	 *
	 * @return the business action by almid
	 * @throws DriverScriptException
	 *             the driver script exception
	 */
	public static void getBusinessComponents(XLUtil xlUtil) throws DriverScriptException {

		COCDriverScript.businessActionsMap = new HashMap<String, Hashtable<String, String>>();
		COCDriverScript.logMessage("info", "Opening Business Component workbook...");
		String businessComponentSheetName = "businesscomponents";
		if (xlUtil.isSheetExists(businessComponentSheetName)) {
			int totalRowCntLocalParameter = xlUtil.getTotalRowCount(businessComponentSheetName);
			for (int row = 1; row <= totalRowCntLocalParameter; row++) {
				Hashtable<String, String> fieldValues = new Hashtable<String, String>();
				String functionalityName = xlUtil.getCellValue(businessComponentSheetName, row, 1);
				String businessAction = xlUtil.getCellValue(businessComponentSheetName, row, 0);
				if (businessAction.equals("EndofSheet")) {
					COCDriverScript.logMessage("info", "End of business component sheet...");
					break;
				}
				COCDriverScript.logMessage("info", " Business Component Name"+businessAction+" Functionality Name "+functionalityName);
				int columnCount = xlUtil.getTotalColumnCount(businessComponentSheetName,row);
				try {
					for (int colNo = 2; colNo < columnCount + 2; colNo++) {
						String cellValue = xlUtil.getCellValue(businessComponentSheetName, row, colNo);
						if (cellValue != null && cellValue.length() != 0) {
							int catchEqual = 0;
							for (int strItr = 0; strItr < cellValue.length(); strItr++) {
								if(cellValue.charAt(strItr)=='='){
									catchEqual=strItr;
								}
							}
							String key = cellValue.substring(0,catchEqual);
							String value =cellValue.substring(catchEqual+1,cellValue.length());
							COCDriverScript.logMessage("info", " Map key "+key+" Map value "+value);
							fieldValues.put(key, value);
						}
					}
				} catch (IndexOutOfBoundsException ex) {
					COCDriverScript.logMessage("info", " Incorrect values / format is  provided for "
							+ " functionality name " + functionalityName + " Business Component name" + businessAction);
					throw new DriverScriptException(" Incorrect values / format is  provided for " 
							+ " functionality name " + functionalityName + " Business Component name" + businessAction);
				}
				COCDriverScript.businessActionsMap.put(businessAction + functionalityName, fieldValues);
			}
		} else {
			COCDriverScript.logMessage("error", businessComponentSheetName + " sheet does not exists");
			throw new DriverScriptException(businessComponentSheetName + " sheet does not exists");
		}
		COCDriverScript.logMessage("info", "getBusinessActionByALMID method: end");
	}




	public static String searchFile(File folder,String fileNameToSearch) throws DriverScriptException {

		if (folder.isDirectory()) {
			File absoluteFile = folder.getAbsoluteFile();
			COCDriverScript.logMessage("info","Searching directory ... " + absoluteFile);
			String file  = null;
			File checkFile = new File(absoluteFile.getPath(),fileNameToSearch);
			if(checkFile.exists()){
				return checkFile.getAbsolutePath().toString();	
			}
			else{
				for (int i = 0; i < folder.listFiles().length; i++) {
					File recurssiveFolder = folder.listFiles()[i];

					if (recurssiveFolder.isDirectory()) {
						COCDriverScript.logMessage("info","Searching directory ... " + recurssiveFolder);
						File temp = new File(recurssiveFolder.getAbsoluteFile().getPath(),fileNameToSearch);
						COCDriverScript.logMessage("info",temp + " file");
						if(temp.exists()){
							COCDriverScript.logMessage("info","File exists");
							file = temp.getAbsolutePath().toString();
							return file;
						}
					}
				}
			}
			return file;
		}else{
			COCDriverScript.logMessage("error",folder + " folder does not exists");
			throw new DriverScriptException(folder+" folder does not exists");
		}
	}
	
	
	/**
	 * get the login use details based on the profile name
	 *
	 * @param testCaseName
	 * @param testData sheet name
	 * @param cellValue
	 * @return void
	 * @throws DriverScriptException 
	 * @author l5mq
	 */
	public String getUserDetails(String profileName, String testCaseName,String globalParamerSheetName ) throws DriverScriptException{
		Hashtable<String, String> hashtable = null;
		XLUtil xlUtil = new XLUtil();
		String execute;
		//String sheetName = null;
		String userName=null;
		
		try{
			System.out.println("* *:"+COCDriverScript.globalParamMap.get("salesUserLevelPath"));			
			String fileName =  searchFile(new File(COCDriverScript.globalParamMap.get("salesUserLevelPath")),"UserProfileDetails.xlsx");
			
			if(fileName!=null){
				xlUtil.openWorkBook(new File(fileName));
				COCDriverScript.logMessage("info", "GlobalParameter_ProfileNames.xlsx file exists");
				
				if (xlUtil.isSheetExists(globalParamerSheetName)) {
					COCDriverScript.logMessage("info",globalParamerSheetName+" sheet exists in UserProfileDetails.xlsx file");
					//int cj,ci;
					int startRow = 1;
					int startCol = 1;					
					//boolean isSkipped = false;
					//int totalExecutableRows = xlUtil.getTotalRowCountWithExecuteFlag(testCaseName,globalParamerSheetName);
					int totalRows = xlUtil.getTotalRowCount(globalParamerSheetName)+1;
					int totalCols = xlUtil.getTotalColumnsInRow(globalParamerSheetName);				
					//ci=0;
					for(int i=startRow; i < totalRows;i++/*,ci++*/){
						//cj=0;
						execute = xlUtil.getCellValue(globalParamerSheetName,i, 0);
						if ("EndOfSheet".equals(execute)) {
							break;
						}
						hashtable = new Hashtable<String, String>();
						for(int j=startCol; j < totalCols;j++/*,cj++*/){
							if((xlUtil.getCellValue(globalParamerSheetName, i, 1)!=null && xlUtil.getCellValue(globalParamerSheetName, i, 1)!="")) {								
								if(profileName.equalsIgnoreCase(xlUtil.getCellValue(globalParamerSheetName, i, 0))){
								userName=xlUtil.getCellValue(globalParamerSheetName, i, 1);
									break;
								}													
								break;								
							}							
						}						
					}
				}
				else {
					COCDriverScript.logMessage("error", "Provided worksheet in UserProfileDetails.xlsx file does not exist");
					throw new DriverScriptException(new Throwable("Provided worksheet in UserProfileDetails.xlsx file does not exist"));
				}
				xlUtil.saveWorkbook(new File(fileName));
			}else{
				COCDriverScript.logMessage("error", "UserProfileDetails.xlsx file does not exist");
				throw new DriverScriptException(new Throwable("UserProfileDetails.xlsx file does not exist"));
			}
		}catch(DriverScriptException exx){
			throw new DriverScriptException(new Throwable(exx.getCause()));
		}

		catch (Exception e) {
			e.printStackTrace();
		}		
		return(userName);
}

}