/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.STPChgDoctWithOutCmtsPositiveFlow;

import com.pss.dms.login.QMSLoginDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jeevan
 */
public class ApprovalDoctRequestSTPWithChgDoctPositiveFlow extends QMSLoginDetails {

	public ApprovalDoctRequestSTPWithChgDoctPositiveFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoApprovalDoctRequestSTPWithChgDoctPositiveFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsApprovalId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsNewDocReqAppPageId")).click();
		Thread.sleep(1000);
		methodToDoDocmtApprovalRequestWithChgDoctSTP();

	}

	private void methodToDoDocmtApprovalRequestWithChgDoctSTP() throws InterruptedException {
		boolean isRecordSelectedForApprovalReq = false;
		int count = 0;
		String documentNameReq = properties.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST_CHG_DOCT_APPROVAL_FLOW");
		isRecordSelectedForApprovalReq = selectRecordDocReqApprovalSTPWithChgDoct(documentNameReq,
				isRecordSelectedForApprovalReq, count);
		Thread.sleep(1000);
		if (isRecordSelectedForApprovalReq) {
			driver.findElement(By.id("newDocRequestAppComments"))
					.sendKeys(properties.getProperty("COMMENTS_NEW_DOCT_REQ_APPROVAL"));
			Thread.sleep(1000);
			driver.findElement(By.id("approveBtnInnewDocRequestApp")).click();
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

	private boolean selectRecordDocReqApprovalSTPWithChgDoct(String documentNameReq,
			boolean isRecordSelectedForApprovalReq, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("newDocRequestApprovalTable"));
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
			if ((totalNoOfRecords > 1) && ((documentNameReq == null) || ("".equalsIgnoreCase(documentNameReq)))) {
				documentNameReq = driver
						.findElement(By.xpath("//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentNameReq == null) || ("".equalsIgnoreCase(documentNameReq))) {
				documentNameReq = driver
						.findElement(By.xpath("//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr/td[2]"))
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
										"//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// documentTypeName
						if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
									.click();
							isRecordSelectedForApprovalReq = true;
							break;
						}
					}
					if (isRecordSelectedForApprovalReq) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelectedForApprovalReq = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForApprovalReq) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("newDocRequestApprovalTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedForApprovalReq;
	}
}
