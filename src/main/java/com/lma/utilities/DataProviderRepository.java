package com.lma.utilities;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import com.lma.accelarators.ActionDriver;

public class DataProviderRepository extends ActionDriver {

	private static final Logger log = LogManager.getLogger(DataProviderRepository.class.getName());

	@DataProvider(name = "LmaTestData")
	public Object[][] dataProviderMethod(Method method) throws MyOwnException {
		
		System.out.println("inside DP 1");

		log.info("INTEND TO READ THE TEST DATA USING DATAPROVIDER FROM EXCEL WORKBOOK");
		Object[][] testDataArray = null;
		try {
			if (method.getName().equals("AddCounterParty")) {
				System.out.println("inside DP 2");
				testDataArray = Excel.getTestDataAsTwoDimesionalObjectArray(
						System.getProperty("user.dir") + configProps.getProperty("TestDatapath"),
						configProps.getProperty("DataProviderExcelName"),
						configProps.getProperty("DataProviderSheetName"));
				
				System.out.println("inside DP 3");
			}
		} catch (Exception exp) {
			log.error(exp.getMessage());
			throwException("UNABLE TO EXECUTE DATA PROVIDER SECTION FROM THE METHOD dataProviderMethod\n"
					+ exp.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY READ THE TEST DATA FOR (" + method
				+ ") SECURITY TYPE USING DATAPROVIDER FROM EXCEL WORKBOOK");
		return testDataArray;
	}

}
