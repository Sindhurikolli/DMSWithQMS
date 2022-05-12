/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.STPChgDoctWithOutCmtsPositiveFlow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pss.dms.login.QMSLoginDetails;

/**
 *
 * @author Jeevan
 */
public class ChangedDoctInitiateSTPApprovalFlow extends QMSLoginDetails {

	public ChangedDoctInitiateSTPApprovalFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoChangedDoctInitiateSTPApprovalFlow() throws InterruptedException {
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("changeDocumentId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsChangeDocInitPageId")).click();
		Thread.sleep(1000);
		methodToSelectRecordSTPForChgDmtInitiateApprovalFlow();
		Thread.sleep(1000);

	}

	private void methodToSelectRecordSTPForChgDmtInitiateApprovalFlow() throws InterruptedException {
		boolean isRecordSelectedChgDmtInitiation = false;
		int count = 0;
		String documentName = properties.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST_CHG_DOCT_APPROVAL_FLOW");
		isRecordSelectedChgDmtInitiation = selectRecordSTPChgReqDtlsTableApprovalFlow(documentName,
				isRecordSelectedChgDmtInitiation, count);
		Thread.sleep(1000);
		if (isRecordSelectedChgDmtInitiation) {
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("docBrowseBtnInChangeDocInit"))
					.sendKeys(properties.getProperty("CHANGE_DOCT_UPLOAD_CHG_INITIATE"));
			Thread.sleep(1000);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(5000);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(1000);
			driver.findElement(By.linkText("Finish")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
			Thread.sleep(1000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(4000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordSTPChgReqDtlsTableApprovalFlow(String documentName,
			boolean isRecordSelectedChgDmtInitiation, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("initiateChangeInitiateReqGridId"));
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
			if ((totalNoOfRecords > 1) && ((documentName == null) || ("".equalsIgnoreCase(documentName)))) {
				documentName = driver
						.findElement(By.xpath(
								"//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((documentName == null) || ("".equalsIgnoreCase(documentName))) {
				documentName = driver
						.findElement(By
								.xpath("//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr/td[3]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver.findElement(
								By.xpath("//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr[" + i
										+ "]/td[3]"))
								.getText();// documentTypeName
						if (documentName.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr["
											+ i + "]/td[3]"))
									.click();
							isRecordSelectedChgDmtInitiation = true;
							break;
						}
					}
					if (isRecordSelectedChgDmtInitiation) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath(
									"//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr/td[3]"))
							.getText();
					if (documentName.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By
								.xpath("//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedChgDmtInitiation = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedChgDmtInitiation) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("initiateChangeInitiateReqGridId"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedChgDmtInitiation;
	}
}
