package com.pge.COC.ReUsable.utility;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelTestData {
	public String workbookName="";
	public String sheetName="";
	public int rowNo=-1;
	private Sheet sheet;

	public ExcelTestData(String workbookName, String sheetName){
		setWorkbookName(workbookName);
		setSheetName(sheetName);
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new FileInputStream(workbookName));
		} catch (EncryptedDocumentException e) {e.printStackTrace();
		} catch (InvalidFormatException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}
		this.sheet = wb.getSheet(sheetName);
	}
	
	public void setWorkbookName(String name){
		this.workbookName = name;
	}
	
	public void setSheetName(String sheetname){
		this.sheetName= sheetname;
	}

	//Get cell value from Excel Spreadsheets based on the Column header name
	public String getCellValue(String paramName) throws Exception{
		Row row = sheet.getRow(0);
		String searchString = paramName;
		int headerColNo = -1;
		for (int cn=0; cn<row.getLastCellNum(); cn++) {
			Cell c = row.getCell(cn);
			if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) {
				// Can't be this cell - it's empty
				continue;
			}
			if (c.getCellType() == Cell.CELL_TYPE_STRING) {
				String text = c.getStringCellValue();
				if (searchString.equals(text)) {
					headerColNo = cn;
					break;
				}
			}
		}
		if (headerColNo == -1) {
			throw new Exception("The Workbook:'"+ workbookName + "' does not contain the '" + paramName + "' as a parameter");
		}
		if (rowNo<0){
			throw new Exception("Row number is not set");
		}
		if ((sheet.getRow(rowNo).getCell(headerColNo)==null)||(sheet.getRow(rowNo).getCell(headerColNo).getCellType()==Cell.CELL_TYPE_BLANK)){
			return "";
		}else{
		return sheet.getRow(rowNo).getCell(headerColNo).getStringCellValue();
		}
	}


	public void setRowNo(int rowNum){
		rowNo=rowNum;
	}

	public int getRowCount(){
		int rows = sheet.getPhysicalNumberOfRows();
		int rowCount = 0;
		for (int i = 0; i < rows-1; i++) {
			if (sheet.getRow(i).getCell(0).getCellType()!=Cell.CELL_TYPE_BLANK){
				if (sheet.getRow(i).getCell(0).getCellType() != Cell.CELL_TYPE_STRING ||
						sheet.getRow(i).getCell(0).getStringCellValue().length() > 0) {
						rowCount++;
		            }
				
			}
		}
		return rowCount;
	}

	//Get the value of the Cell using Row and Column numbers
	public String getCellValue(int rowNumber, int colNumber) {
		String ret="";
		if ((sheet.getRow(rowNumber).getCell(colNumber)==null)||(sheet.getRow(rowNumber).getCell(colNumber).getCellType()==Cell.CELL_TYPE_BLANK)){
			ret= "";
		}
		else {
			ret= sheet.getRow(rowNumber).getCell(colNumber).getStringCellValue();
		}
		return ret;
	}
	public int getColCount(){
		return sheet.getRow(0).getLastCellNum();
	}
}
