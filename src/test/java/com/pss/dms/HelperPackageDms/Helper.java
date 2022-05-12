/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.HelperPackageDms;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Jeevan
 */
public class Helper {

	public Helper() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	public static Select getSelectInstance(WebDriver driver, By byObjForDropDownList) {
		WebElement webElementForSelect = driver.findElement(byObjForDropDownList);
		Select dropDownList = new Select(webElementForSelect);
		return dropDownList;
	}

	public static void scrollElement(WebDriver driver, By byObj) throws InterruptedException {

		WebElement element = driver.findElement(byObj);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(5000);
	}

	public static void clickElement(WebDriver driver, By byObj) throws InterruptedException {

		WebElement element = driver.findElement(byObj);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(1000);
	}
	
	
	public static void actionClickElement(WebDriver driver, By byObj) {

		WebElement element = driver.findElement(byObj);
		Actions action = new Actions(driver);
		action.moveToElement(element).click(element);
		action.perform();
	}

	// Single Approval(Document Tree & Document Type & WorkFlow Users)
	public static boolean selectSingleApprovalRecord(WebDriver driver, String selectingUserSingleApproval,
			boolean isRecordSelected, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("approversTableContainer"));// Single Selection User Table
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String a = driver.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/div[4]/div[2]/span"))
					.getText().toString();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			totalNoOfRecords = Integer.parseInt(parts[1].trim());
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1)
					&& ((selectingUserSingleApproval == null) || ("".equalsIgnoreCase(selectingUserSingleApproval)))) {
				selectingUserSingleApproval = driver
						.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();
			} else if (selectingUserSingleApproval == null || ("".equalsIgnoreCase(selectingUserSingleApproval))) {
				selectingUserSingleApproval = driver
						.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();
			}

			++count;
		}

		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String selectingUser = driver
								.findElement(By.xpath(
										"//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();
						if (selectingUserSingleApproval.equalsIgnoreCase(selectingUser)) {
							driver.findElement(By.xpath(
									"//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
									.click();
							isRecordSelected = true;
							break;
						}
						if (isRecordSelected) {
							break;
						}
					}
				} else {
					String selectingUser = driver
							.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (selectingUserSingleApproval.equalsIgnoreCase(selectingUser)) {
						driver.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[4]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#approversTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in single approval
					Thread.sleep(3000);
					table = driver.findElement(By.id("approversTableContainer"));// single approval table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	// Selecting New Document Initiate Record
	public static boolean selectDocumentInitiateRecord(WebDriver driver, String documentName, boolean isRecordSelected,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("newDocIntiateTable"));// Single Selection User Table
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			totalNoOfRecords = Integer.parseInt(parts[1].trim());
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((documentName == null) || ("".equalsIgnoreCase(documentName)))) {
				documentName = driver
						.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/div[4]/table/tbody/tr[1]/td[2]"))
						.getText();
			} else if (documentName == null || ("".equalsIgnoreCase(documentName))) {
				documentName = driver
						.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/div[4]/table/tbody/tr/td[2]"))
						.getText();
			}

			++count;
		}

		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String selectingUser = driver
								.findElement(By.xpath(
										"//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();
						if (documentName.equalsIgnoreCase(selectingUser)) {
							driver.findElement(By.xpath(
									"//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
									.click();
							isRecordSelected = true;
							break;
						}
						if (isRecordSelected) {
							break;
						}
					}
				} else {
					String selectingUser = driver
							.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (documentName.equalsIgnoreCase(selectingUser)) {
						driver.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in single approval
					Thread.sleep(3000);
					table = driver.findElement(By.id("newDocIntiateTable"));// single approval table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelected;
	}

}
