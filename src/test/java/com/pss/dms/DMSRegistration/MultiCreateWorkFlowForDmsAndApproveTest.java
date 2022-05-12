package com.pss.dms.DMSRegistration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pss.dms.login.QMSLoginDetails;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultiCreateWorkFlowForDmsAndApproveTest {

	static File resultFile = new File("DMSWithQMS User Details for Automation.xlsx");

	public static DataFormatter formatter = new DataFormatter();

	@Test(dataProvider = "readExcelFile")
	public void toMultiUserCreation(String WorkFlowDocType, String WFLocation, String WorkFlowName) throws Throwable {

		Thread.sleep(5000);
		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
		properties.setProperty("WorkFlowDocType", WorkFlowDocType);
		properties.setProperty("WFLocation", WFLocation);
		properties.setProperty("WorkFlowName", WorkFlowName);
		properties.save();

		Thread.sleep(5000);
		QMSLoginDetails login = new QMSLoginDetails();
		login.setUp();
		CreateWorkFlowForDms wf = new CreateWorkFlowForDms();
		wf.toDoCreateWorkFlowDms();
		CreateWorkFlowForDmsApproval wfapproval = new CreateWorkFlowForDmsApproval();
		wfapproval.toDoDmsWorkFlowApprovalSOP();
		QMSLoginDetails.tearDown();

	}

	@DataProvider
	public static Object[][] readExcelFile() throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(resultFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("DMSWorkFlow");

		System.out.println(sh.getPhysicalNumberOfRows());
		System.out.println(sh.getRow(0).getPhysicalNumberOfCells());
		int RowNum = sh.getPhysicalNumberOfRows();
		int ColNum = sh.getRow(0).getPhysicalNumberOfCells();

		String[][] xlData = new String[RowNum - 1][ColNum];

		for (int i = 0; i < RowNum - 1; i++) {
			XSSFRow row = sh.getRow(i + 1);
			for (int j = 0; j < ColNum; j++) {
				if (row == null)
					xlData[i][j] = "";
				else {
					XSSFCell cell = row.getCell(j);
					if (cell == null)
						xlData[i][j] = "";
					else {
						String value = formatter.formatCellValue(cell);
						xlData[i][j] = value.trim();
					}
				}
			}
		}
		return xlData;
	}

}
