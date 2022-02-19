package com.lma.accelarators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.lma.utilities.MyOwnException;
import com.google.common.base.Splitter;

public class ActionDriver extends Base {

	private static final Logger log = LogManager.getLogger(ActionDriver.class.getName());

	static File file;
	static FileInputStream fis;
	static Workbook wb;
	static Sheet sh;
	static Row row;
	static Cell cell;


	public static Map<String, String> splitToMap(String data) throws MyOwnException {

		log.info("METHOD(splitToMap) EXECUTION STARTED SUCCESSFULLY");
		Map<String, String> splitMap = null;
		try {
			splitMap = Splitter.on("\n").withKeyValueSeparator("~").split(data);
		} catch (Exception exp) {
			log.error(exp.getMessage());
			throwException("UNABLE TO SPLIT THE GIVEN STRING INTO A MAP FROM THE METHOD splitToMap.\n"
					+ exp.getMessage() + "\n");
		}
		log.info("METHOD(splitToMap) EXECUTED SUCCESSFULLY");
		return splitMap;
	}

	public static void screenShot(String fileName) throws MyOwnException {

		log.info("METHOD(screenShot) EXECUTION STARTED SUCCESSFULLY");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException exp) {
			log.error(exp.getMessage());
			throwException("UNABLE TO TAKE THE SCREEN SHOT OF THE SCREEN FROM THE METHOD screenShot.\n"
					+ exp.getMessage() + "\n");
		}
		log.info("METHOD(screenShot) EXECUTED SUCCESSFULLY");
	}

	public static String getDate(int i) {

		String date = null;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, i);
		date = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
		return date;

	}

	
	
}
