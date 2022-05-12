/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDocsSTPChangeDoctRejectFlow;

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
public class RejectedChangeDoctSTPInitiateFlow extends QMSLoginDetails {

	public RejectedChangeDoctSTPInitiateFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toRejectedChangeDoctSTPInitiateFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("changeDocumentId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsChangeDocInitPageId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("rejectSelectionInChangeDocReq")).click();
		Thread.sleep(1000);
		methodToDoRejectedChangedDoctInitiateChgRejFlowSTP();

	}

	private void methodToDoRejectedChangedDoctInitiateChgRejFlowSTP() throws InterruptedException {
		boolean isRecordSelected = false;
		int count = 0;
		String documentName = properties.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST_CHG_DOCT_REJECT_FLOW");
		Thread.sleep(1000);
		isRecordSelected = selectRecordRejectedChangedDoctInitiateSTPChgRejFlow(documentName, isRecordSelected, count);
		Thread.sleep(1000);
		if (isRecordSelected) {
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("docBrowseBtnInChangeDocInit"))
					.sendKeys(properties.getProperty("DOCT_INITIATE_DOCT_UPLOAD"));
			Thread.sleep(1500);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(5000);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("reasonForModifyInRejectedEditDocumentform"))
					.sendKeys(properties.getProperty("COMTS_CHG_DOCT_INITIATE_REJ_FLOW"));
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
			Thread.sleep(2000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordRejectedChangedDoctInitiateSTPChgRejFlow(String documentName, boolean isRecordSelected,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("rejectedChangeDocInitiateReqGridId"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));// *[@id="rejectedChangeDocInitiateReqGridId"]/div/div[4]/table/tbody/tr/td[3]
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"rejectedChangeDocInitiateReqGridId\"]/div/div[5]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
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
								"//*[@id=\"rejectedChangeDocInitiateReqGridId\"]/div/div[4]/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((documentName == null) || ("".equalsIgnoreCase(documentName))) {
				documentName = driver
						.findElement(By.xpath(
								"//*[@id=\"rejectedChangeDocInitiateReqGridId\"]/div/div[4]/table/tbody/tr/td[3]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver.findElement(
								By.xpath("//*[@id=\"rejectedChangeDocInitiateReqGridId\"]/div/div[4]/table/tbody/tr["
										+ i + "]/td[3]"))
								.getText();// documentTypeName
						if (documentName.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By
									.xpath("//*[@id=\"rejectedChangeDocInitiateReqGridId\"]/div/div[4]/table/tbody/tr["
											+ i + "]/td[3]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath(
									"//*[@id=\"rejectedChangeDocInitiateReqGridId\"]/div/div[4]/table/tbody/tr/td[3]"))
							.getText();
					if (documentName.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath(
								"//*[@id=\"rejectedChangeDocInitiateReqGridId\"]/div/div[4]/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#rejectedChangeDocInitiateReqGridId > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("rejectedChangeDocInitiateReqGridId"));// Document Tree approve
																							// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
