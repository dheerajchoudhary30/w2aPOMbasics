package com.w2a.utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {

	public String path = null;
	public FileInputStream fis = null;
	public FileOutputStream fos = null;

	private XSSFWorkbook wb = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public ExcelReader(String path) {

		this.path = path;
		try {
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheetAt(0);
			fis.close();
		} catch (Exception e) {

		}
	}


	// Get Row Count

	public int getRowCount(String sheetName) {

		int index = wb.getSheetIndex(sheetName);

		int rowCount;
		if(index==-1) {
			return 0;
		} else {

			sheet = wb.getSheetAt(index);
			rowCount = sheet.getLastRowNum()+1;
		}

		return rowCount;
	}

	// Get Column Count

	public int getColumnCount(String sheetName) {

		int rowCount=0;

		if(isSheetExists(sheetName)==false) {

		}else {

			int index = wb.getSheetIndex(sheetName);

			if(index==-1) {
				return 0;
			} else {

				sheet = wb.getSheetAt(index);
				row   =  sheet.getRow(0);
				if(row==null) {
					return -1;
				}
				rowCount = row.getLastCellNum();
			}
		}
		return rowCount;

	}


	public boolean isSheetExists(String sheetName) {
		int index = wb.getSheetIndex(sheetName);

		if(index==-1) {
			index=wb.getSheetIndex(sheetName.toUpperCase());
			if(index==-1) {
				return false;
			} else {
				return true;
			}
		}


		return true;

	}


	public String getCellData(String sheetName, int rowNum, int colNum) {
		// TODO Auto-generated method stub

		if (rowNum<0) {
			return "";
		}
		int index = wb.getSheetIndex(sheetName);
		if(index==-1) {
			return "";
		}

		sheet = wb.getSheetAt(index);
		row = sheet.getRow(rowNum-1);

		if(row==null) {
			return "";
		}

		cell = row.getCell(colNum);

		if(cell==null) {
			return "";
		}

		if(cell.getCellTypeEnum()==CellType.STRING) {
			return cell.getStringCellValue() ;
		} else if (cell.getCellTypeEnum()==CellType.NUMERIC) {

			return String.valueOf(cell.getNumericCellValue());

		} else if(cell.getCellTypeEnum()==CellType.BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if(cell.getCellTypeEnum()==CellType.BLANK) {
			return "";
		}

		else return "";


	}

}
