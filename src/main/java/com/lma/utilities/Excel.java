package com.lma.utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.lma.accelarators.Base;

public class Excel extends Base {

	private static final Logger log = LogManager.getLogger(Excel.class.getName());

	private static Workbook wb;
	private static Sheet sh;
	private static Row row;
	private static Cell cell;
	private static int rowCount;
	private static int colCount;
	private static String[][] excelStringArray;
	private static List<String> excelList;
	private static HashMap<Integer, String> excelMap;
	private static DataFormatter objDefaultFormat;
	private static FormulaEvaluator objFormulaEvaluator;

	public static Workbook getToFile(String filePath, String excelFileName) throws MyOwnException {

		log.info("INTEND TO READ THE SPECIFIED EXCEL FILE");
		try {
			if (notEmpty(excelFileName)) {
				if (formatGiven(excelFileName) == true) {
					fileFormat = getFileFormat(excelFileName);
					if (fileFormatIs(".xlsx") || fileFormatIs(".xls")) {
						file = createFileInstance(filePath + excelFileName);
						fs = readFile(file);
						wb = createWorkBookInstance(fs);
					} else {
						log.error("FILE FORMAT SEEMS TO BE INCORRECT");
						throwException("FILE FORMAT SEEMS TO BE INCORRECT" + "\n");
					}
				} else {
					log.error("FILE FORMAT IS MISSING");
					throwException("FILE FORMAT IS MISSING" + "\n");
				}
			} else {
				log.error("FILE NAME SEEMS TO BE EMPTY");
				throwException("FILE NAME SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO READ THE EXCEL FILE(" + excelFileName + ")\n" + e.getMessage());
			throwException("UNABLE TO READ THE EXCEL FILE(" + excelFileName + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY READ THE EXCEL FILE(" + excelFileName + ")");
		return wb;
	}

	public static Sheet getToSheet(String filePath, String excelFileName, String sheetName) throws MyOwnException {

		log.info("INTEND TO READ THE SPECIFIED EXCEL SHEET");
		try {
			wb = getToFile(filePath, excelFileName);
			if (notEmpty(sheetName)) {
				if (sheetPresent(sheetName)) {
					sh = wb.getSheet(sheetName);
				} else {
					log.error("SHEET NAME SEEMS TO BE INCORRECT");
					throwException("SHEET NAME SEEMS TO BE INCORRECT" + "\n");
				}
			} else {
				log.error("SHEET NAME SEEMS TO BE EMPTY");
				throwException("SHEET NAME SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO READ THE EXCEL SHEET(" + sheetName + ")\n" + e.getMessage());
			throwException("UNABLE TO READ THE EXCEL SHEET(" + sheetName + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY READ THE EXCEL SHEET(" + sheetName + ")");
		return sh;
	}

	private static Workbook createWorkBookInstance(FileInputStream fs) throws MyOwnException {

		log.info("INTEND TO CREATE AN WORKBOOK INSTANCE BASED ON THE EXCEL FILE FORMAT");
		try {
			if (fileFormatIs(".xlsx")) {
				wb = new XSSFWorkbook(fs);
				log.info("SUCCESSFULLY CREATED AN INSTANCE OF XSSFWORKBOOK CLASS");
			} else if (fileFormatIs(".xls")) {
				wb = new HSSFWorkbook(fs);
				log.info("SUCCESSFULLY CREATED AN INSTANCE OF HSSFWORKBOOK CLASS");
			} else {
				log.error("FILE FORMAT SEEMS TO BE INCORRECT");
				throwException("FILE FORMAT SEEMS TO BE INCORRECT" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO CREATE WORKBOOK INSTANCE\n" + e.getMessage());
			throwException("UNABLE TO CREATE WORKBOOK INSTANCE\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY CREATED AN WORKBOOK INSTANCE BASED ON THE EXCEL FILE FORMAT");
		return wb;
	}

	private static boolean sheetPresent(String sheetName) {

		log.info("INTEND TO VERIFY WHETHER THE SPECIFIED EXCEL SHEET IS PRESENT OR NOT");
		int sheets = wb.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			String tempName = wb.getSheetAt(i).getSheetName();
			if (tempName.equalsIgnoreCase(sheetName)) {
				log.info("(" + sheetName + ") IS PRESENT");
				result = true;
				break;
			} else {
				log.error("(" + sheetName + ") IS NOT PRESENT");
				result = false;
			}
		}
		log.info("SUCCESSFULLY VERIFIED WHETHER THE EXCEL SHEET(" + sheetName + ") IS PRESENT OR NOT");
		return result;
	}

	private static String getCellValue(Cell cell) throws MyOwnException {

		log.info("INTEND TO READ THE CELL VALUE");
		String cellValue = "";
		try {
			objDefaultFormat = new DataFormatter();
			if (fileFormatIs(".xlsx")) {
				objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
				log.info("SUCCESSFULLY CREATED AN INSTANCE OF XSSFFORMULAEVALUATOR");
			} else if (fileFormatIs(".xls")) {
				objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) wb);
				log.info("SUCCESSFULLY CREATED AN INSTANCE OF HSSFFORMULAEVALUATOR");
			} else {
				log.error("FILE FORMAT SEEMS TO BE INCORRECT");
				throwException("FILE FORMAT SEEMS TO BE INCORRECT" + "\n");
			}
			objFormulaEvaluator.evaluate(cell);
			cellValue = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
		} catch (Exception e) {
			log.error("UNABLE TO READ CELL VALUE\n" + e.getMessage());
			throwException("UNABLE TO READ CELL VALUE\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY READ THE CELL VALUE(" + cellValue + ")");
		return cellValue;
	}

	public static Object[][] getTestDataAsTwoDimesionalObjectArray(String filePath, String excelFileName,
			String sheetName) throws MyOwnException {

		log.info("INTEND TO READ THE TEST DATA AS TWO DIMENSIONAL OBJECT ARRAY");
		Object[][] excelObjectArray = null;
		try {
			sh = getToSheet(filePath, excelFileName, sheetName);
			if (sh.getRow(0) != null) {
				rowCount = sh.getLastRowNum();
				if (rowCount != 0) {
					colCount = sh.getRow(0).getLastCellNum();
					excelObjectArray = new String[rowCount][colCount];
					int k = 0;
					String cellValue = "";
					for (int i = 1; i <= rowCount; i++, k++) {
						row = sh.getRow(i);
						int l = 0;
						for (int j = 0; j < colCount; j++, l++) {
							if (row.getCell(j) != null) {
								cell = row.getCell(j);
								cellValue = getCellValue(cell);
								excelObjectArray[k][l] = cellValue;
							} else {
								cellValue = "";
								excelObjectArray[k][l] = cellValue;
							}
						}
					}
				} else {
					log.error("TEST DATA NOT GIVEN (FIRST ROW IS ALWAYS CONSIDERED AS COLUMN HEADINGS)");
					throwException("TEST DATA NOT GIVEN (FIRST ROW IS ALWAYS CONSIDERED AS COLUMN HEADINGS)" + "\n");
				}
			} else {
				log.error("FILE SEEMS TO BE EMPTY");
				throwException("FILE SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO READ THE TEST DATA AS TWO DIMENSION OBJECT ARRAY FROM EXCEL SHEET(" + sheetName
					+ ") INSIDE EXCEL FILE(" + excelFileName + ")\n" + e.getMessage());
			throwException("UNABLE TO READ THE TEST DATA AS TWO DIMENSION OBJECT ARRAY FROM EXCEL SHEET(" + sheetName
					+ ") INSIDE EXCEL FILE(" + excelFileName + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY READ THE TEST DATA AS TWO DIMENSIONAL OBJECT ARRAY FROM THE EXCEL SHEET(" + sheetName
				+ ") IN THE EXCEL FILE(" + excelFileName + ") UNDER THE LOCATION(" + filePath + ")");
		return excelObjectArray;
	}

	public static String[][] getTestDataAsTwoDimesionalStringArray(String filePath, String excelFileName,
			String sheetName) throws MyOwnException {

		log.info("INTEND TO READ THE TEST DATA AS TWO DIMENSIONAL STRING ARRAY");
		try {
			sh = getToSheet(filePath, excelFileName, sheetName);
			if (sh.getRow(0) != null) {
				rowCount = sh.getPhysicalNumberOfRows();
				if (rowCount != 0) {
					colCount = sh.getRow(0).getLastCellNum();
					excelStringArray = new String[rowCount][colCount];
					int k = 0;
					String cellValue = "";
					for (int i = 0; i < rowCount; i++, k++) {
						row = sh.getRow(i);
						int l = 0;
						for (int j = 0; j < colCount; j++, l++) {
							if (row.getCell(j) != null) {
								cell = row.getCell(j);
								cellValue = getCellValue(cell);
								excelStringArray[k][l] = cellValue;
							} else {
								cellValue = "";
								excelStringArray[k][l] = cellValue;
							}
						}
					}
				} else {
					log.error("TEST DATA NOT GIVEN (FIRST ROW IS ALWAYS CONSIDERED AS COLUMN HEADINGS)");
					throwException("TEST DATA NOT GIVEN (FIRST ROW IS ALWAYS CONSIDERED AS COLUMN HEADINGS)" + "\n");
				}
			} else {
				log.error("FILE SEEMS TO BE EMPTY");
				throwException("FILE SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO READ THE TEST DATA AS TWO DIMENSION STRING ARRAY FROM EXCEL SHEET(" + sheetName
					+ ") INSIDE EXCEL FILE(" + excelFileName + ")\n" + e.getMessage());
			throwException("UNABLE TO READ THE TEST DATA AS TWO DIMENSION STRING ARRAY FROM EXCEL SHEET(" + sheetName
					+ ") INSIDE EXCEL FILE(" + excelFileName + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY READ THE TEST DATA AS TWO DIMENSIONAL STRING ARRAY FROM THE EXCEL SHEET(" + sheetName
				+ ") IN THE EXCEL FILE(" + excelFileName + ") UNDER THE LOCATION(" + filePath + ")");
		return excelStringArray;
	}

	public static List<String> getTestDataAsList(String filePath, String excelFileName, String sheetName)
			throws MyOwnException {

		log.info("INTEND TO READ THE TEST DATA AS LIST");
		try {
			sh = getToSheet(filePath, excelFileName, sheetName);
			if (sh.getRow(0) != null) {
				rowCount = sh.getLastRowNum();
				if (rowCount != 0) {
					colCount = sh.getRow(0).getLastCellNum();
					excelList = new ArrayList<String>();
					String cellValue = "";
					for (int i = 1; i <= rowCount; i++) {
						row = sh.getRow(i);
						for (int j = 0; j < colCount; j++) {
							if (row.getCell(j) != null) {
								cell = row.getCell(j);
								cellValue = getCellValue(cell);
								excelList.add(cellValue);
							} else {
								cellValue = "";
								excelList.add(cellValue);
							}
						}
					}
				} else {
					log.error("TEST DATA NOT GIVEN (FIRST ROW IS ALWAYS CONSIDERED AS COLUMN HEADINGS)");
					throwException("TEST DATA NOT GIVEN (FIRST ROW IS ALWAYS CONSIDERED AS COLUMN HEADINGS)" + "\n");
				}
			} else {
				log.error("FILE SEEMS TO BE EMPTY");
				throwException("FILE SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO READ THE TEST DATA AS A LIST FROM EXCEL SHEET(" + sheetName + ") INSIDE EXCEL FILE("
					+ excelFileName + ")\n" + e.getMessage());
			throwException("UNABLE TO READ THE TEST DATA AS A LIST FROM EXCEL SHEET(" + sheetName
					+ ") INSIDE EXCEL FILE(" + excelFileName + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY READ THE TEST DATA AS LIST FROM THE EXCEL SHEET(" + sheetName + ") IN THE EXCEL FILE("
				+ excelFileName + ") UNDER THE LOCATION(" + filePath + ")");
		return excelList;
	}

	public static HashMap<Integer, String> getTestDataAsHashMap(String filePath, String excelFileName, String sheetName)
			throws MyOwnException {

		log.info("INTEND TO READ THE TEST DATA AS HASHMAP");
		try {
			sh = getToSheet(filePath, excelFileName, sheetName);
			if (sh.getRow(0) != null) {
				rowCount = sh.getLastRowNum();
				if (rowCount != 0) {
					colCount = sh.getRow(0).getLastCellNum();
					excelMap = new HashMap<Integer, String>();
					int counter = 0;
					String cellValue = "";
					for (int i = 1; i <= rowCount; i++) {
						row = sh.getRow(i);
						for (int j = 0; j < colCount; j++) {
							if (row.getCell(j) != null) {
								cell = row.getCell(j);
								cellValue = getCellValue(cell);
								excelMap.put(counter, cellValue);
								counter++;
							} else {
								cellValue = "";
								excelMap.put(counter, cellValue);
								counter++;
							}
						}
					}
				} else {
					log.error("TEST DATA NOT GIVEN (FIRST ROW IS ALWAYS CONSIDERED AS COLUMN HEADINGS)");
					throwException("TEST DATA NOT GIVEN (FIRST ROW IS ALWAYS CONSIDERED AS COLUMN HEADINGS)" + "\n");
				}
			} else {
				log.error("FILE SEEMS TO BE EMPTY");
				throwException("FILE SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			log.error("UNABLE TO READ THE TEST DATA AS A HASHMAP FROM EXCEL SHEET(" + sheetName + ") INSIDE EXCEL FILE("
					+ excelFileName + ")\n" + e.getMessage());
			throwException("UNABLE TO READ THE TEST DATA AS A HASHMAP FROM EXCEL SHEET(" + sheetName
					+ ") INSIDE EXCEL FILE(" + excelFileName + ")\n" + e.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY READ THE TEST DATA AS HASHMAP FROM THE EXCEL SHEET(" + sheetName + ") IN THE EXCEL FILE("
				+ excelFileName + ") UNDER THE LOCATION(" + filePath + ")");
		return excelMap;
	}

}