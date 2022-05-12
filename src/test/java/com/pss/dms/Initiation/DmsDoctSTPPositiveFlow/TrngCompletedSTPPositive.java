
package com.pss.dms.Initiation.DmsDoctSTPPositiveFlow;

import com.pss.dms.login.QMSLoginDetails;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrngCompletedSTPPositive extends QMSLoginDetails {

	@Test
	public void toDoTrngCompletedSTPPositive() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"trngCmpltdListId\"]/a")).click();
		Thread.sleep(1000);
		methodToSelectRecordTrngCompletedListSTP();
	}

	private void methodToSelectRecordTrngCompletedListSTP() throws InterruptedException {
		boolean isRecordSelectedTrngCompletedList = false;
		int count = 0;
		String documentNameTrngCompleted = properties.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST1");
		isRecordSelectedTrngCompletedList = selectRecordTrngCompletedSTP(documentNameTrngCompleted,
				isRecordSelectedTrngCompletedList, count);
		Thread.sleep(1000);
		if (isRecordSelectedTrngCompletedList) {
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(1000);
			Date date = new Date();
			String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
			driver.findElement(By.id("effectiveFromInTrainingCompltdForm")).sendKeys(todaysDate);
			Thread.sleep(3000);
			driver.findElement(By.id("commentsInTrainingCompltdForm")).sendKeys(properties.getProperty("COMMENTS"));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"deptIssGridInTrngCompleted\"]/div/div[3]/div[2]/span/span[1]"))
					.click();
			Thread.sleep(3000);
			driver.findElement(By.id("selLocBtnInDeptIssuanceTable")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("treeContainer_2_switch")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("treeContainer_3_ico")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("selectBtnInLocSelect")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"addUSerGroupDetailsInTrngCmpltdListWin\"]/div[2]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"userGroupsTable\"]/div/div[4]/table/tbody/tr[1]/td[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div[17]/div[3]/div/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("totalNoOfCopiesInDeptPrintOfTrngCompleted"))
					.sendKeys(properties.getProperty("NUMBER_OF_COPIES_SOP"));
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div[14]/div[3]/div/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText("Finish")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
			Thread.sleep(3000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(3000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(3000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordTrngCompletedSTP(String documentNameTrngCompleted,
			boolean isRecordSelectedTrngCompletedList, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsTrainingCompletedListTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1)
					&& ((documentNameTrngCompleted == null) || ("".equalsIgnoreCase(documentNameTrngCompleted)))) {
				documentNameTrngCompleted = driver
						.findElement(By
								.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((documentNameTrngCompleted == null) || ("".equalsIgnoreCase(documentNameTrngCompleted))) {
				documentNameTrngCompleted = driver
						.findElement(
								By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr/td[3]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String doctNameInTrngCompleted = driver.findElement(
								By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr[" + i
										+ "]/td[3]"))
								.getText();// documentTypeName
						if (documentNameTrngCompleted.equalsIgnoreCase(doctNameInTrngCompleted)) {
							driver.findElement(
									By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr[" + i
											+ "]/td[3]"))
									.click();
							isRecordSelectedTrngCompletedList = true;
							break;
						}
					}
					if (isRecordSelectedTrngCompletedList) {
						break;
					}
				} else {
					String doctNameInTrngCompleted = driver
							.findElement(By.xpath(
									"//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr/td[3]"))
							.getText();
					if (documentNameTrngCompleted.equalsIgnoreCase(doctNameInTrngCompleted)) {
						driver.findElement(
								By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedTrngCompletedList = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedTrngCompletedList) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsTrainingCompletedListTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedTrngCompletedList;
	}
}
