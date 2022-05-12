
package com.pss.dms.Initiation.DmsDoctSTPPositiveFlow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pss.dms.login.QMSLoginDetails;

public class ApprovalTrngNotifyListSTP extends QMSLoginDetails {

	@Test
	public void toDoDoctTrngApprovalSOP() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dmsApprovalId\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dmsDocTrainingAppPageId\"]/a")).click();
		Thread.sleep(1000);
		methodToSelectRecordToDoTrngApprovalSOP();
	}

	private void methodToSelectRecordToDoTrngApprovalSOP() throws InterruptedException {
		boolean isRecordSelectedTrngApprovalSOP = false;
		int count = 0;
		String doctNameTrngApprovalSOP = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST1");
		isRecordSelectedTrngApprovalSOP = selectRecordToDOTrngApproval(doctNameTrngApprovalSOP,
				isRecordSelectedTrngApprovalSOP, count);
		Thread.sleep(1000);
		if (isRecordSelectedTrngApprovalSOP) {
			driver.findElement(By.id("commentsInDocTrainingAppGrid"))
					.sendKeys(properties.getProperty("COMMENTS_TRAINING_NOTIFICATION"));
			Thread.sleep(1000);
			driver.findElement(By.id("appInDocTrngAppGrid")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
			Thread.sleep(1000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(1000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordToDOTrngApproval(String doctNameTrngApprovalSOP,
			boolean isRecordSelectedTrngApprovalSOP, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsDocumentTrngAppTable"));
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
					&& ((doctNameTrngApprovalSOP == null) || ("".equalsIgnoreCase(doctNameTrngApprovalSOP)))) {
				doctNameTrngApprovalSOP = driver
						.findElement(By.xpath("//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((doctNameTrngApprovalSOP == null) || ("".equalsIgnoreCase(doctNameTrngApprovalSOP))) {
				doctNameTrngApprovalSOP = driver
						.findElement(By.xpath("//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr/td[2]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By.xpath(
										"//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (doctNameTrngApprovalSOP.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
									.click();
							isRecordSelectedTrngApprovalSOP = true;
							break;
						}
					}
					if (isRecordSelectedTrngApprovalSOP) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[1]/td[2]"))
							.getText();
					if (doctNameTrngApprovalSOP.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[1]/td[2]"))
								.click();
						isRecordSelectedTrngApprovalSOP = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedTrngApprovalSOP) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsDocumentTrngAppTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedTrngApprovalSOP;
	}
}
