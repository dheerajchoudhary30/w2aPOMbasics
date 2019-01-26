package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import com.w2a.base.Page;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class Utilities extends Page {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}

    @DataProvider(name = "dp")
    public Object[][] getData(Method m) {

        String sheetName = m.getName();

        System.out.println("sheetName: "  + sheetName );
        int rowCount = excel.getRowCount(sheetName);
        System.out.println("rowCount: " + rowCount);
        int colCount = excel.getColumnCount(sheetName);
        System.out.println("colCount: " + colCount);

        Object[][] customerData = new Object[rowCount - 1][1];
        Hashtable<String, String> datatable = null;

        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {

            datatable = new Hashtable<String, String>();

            for (int colNum = 0; colNum < colCount; colNum++) {

                datatable.put(excel.getCellData(sheetName, 1, colNum), excel.getCellData(sheetName, rowNum, colNum));

                customerData[rowNum - 2][0] = datatable;

            }

        }

        return customerData;

    }

	public static boolean isTestRunnable(String testname, ExcelReader excel) {

		String sheetName = "test_Suite";

		int RowCount = excel.getRowCount(sheetName);

		for (int rNum = 2; rNum <= RowCount; rNum++) {

			String testCaseName = excel.getCellData(sheetName, rNum, 0);

			if (testCaseName.equalsIgnoreCase(testname)) {
				String runMode = excel.getCellData(sheetName, rNum, 1);
				if (runMode.equalsIgnoreCase("Y")) {
					return true;
				} else {
					return false;
				}
			}
		}

		return false;

	}

	public static void shouldTestRun(String testname, ExcelReader excel) {

		if (!isTestRunnable(testname, excel)) {
			throw new SkipException("Skipping the test " + testname.toUpperCase());
		}

	}
	
}
