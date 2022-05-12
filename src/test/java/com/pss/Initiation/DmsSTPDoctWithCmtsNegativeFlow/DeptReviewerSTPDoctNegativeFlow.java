/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.Initiation.DmsSTPDoctWithCmtsNegativeFlow;

import com.pss.dms.login.QMSLoginDetails;
import java.util.Set;
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
public class DeptReviewerSTPDoctNegativeFlow extends QMSLoginDetails {

	public DeptReviewerSTPDoctNegativeFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoDeptReviewerSTPDoctNegativeFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("newDocMenuListListId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsNewDocRevPageId")).click();
		methodToDoDeptReviewerSTPNegativeFlow();
	}

	private void methodToDoDeptReviewerSTPNegativeFlow() throws InterruptedException {
		int count = 0;
		String documentDeptSTPReview = properties.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST_REJECT_FLOW");
		boolean isRecordSelectedForDeptReview = false;
		isRecordSelectedForDeptReview = selectRecordDeptReviewStageDeptSTP(documentDeptSTPReview,
				isRecordSelectedForDeptReview, count);
		Thread.sleep(1000);
		if (isRecordSelectedForDeptReview) {
			String applicationWindow = driver.getWindowHandle();
			System.out.println("applicationWindow: " + applicationWindow);
			driver.findElement(By.xpath("//*[@id=\"continueBtnInNewDocRevForm\"]")).click();
			Thread.sleep(9000);
			Set<String> pdfWindow = driver.getWindowHandles();
			for (String pdfWindows : pdfWindow) {
				System.out.println("pdfWindows: " + pdfWindows);
				if (!applicationWindow.equalsIgnoreCase(pdfWindows)) {
					driver.switchTo().window(pdfWindows);
					WebDriverWait wait1 = new WebDriverWait(driver, 200);
					wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("approveBtnDocRevAnnotsWin")));
					Thread.sleep(1000);
					driver.findElement(By.id("approveBtnDocRevAnnotsWin")).click();
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
					break;
				}
			}
			Thread.sleep(1000);
			driver.switchTo().window(applicationWindow);
			Thread.sleep(1000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordDeptReviewStageDeptSTP(String documentDeptSTPReview,
			boolean isRecordSelectedForDeptReview, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsNewDocReviewTable"));
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
					&& ((documentDeptSTPReview == null) || ("".equalsIgnoreCase(documentDeptSTPReview)))) {
				documentDeptSTPReview = driver
						.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// documentType
			} else if ((documentDeptSTPReview == null) || ("".equalsIgnoreCase(documentDeptSTPReview))) {
				documentDeptSTPReview = driver
						.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr/td[1]")).getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By
										.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[1]"))
								.getText();// documentTypeName
						if (documentDeptSTPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[1]"))
									.click();
							isRecordSelectedForDeptReview = true;
							break;
						}
					}
					if (isRecordSelectedForDeptReview) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr/td[1]"))
							.getText();
					if (documentDeptSTPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr/td[1]"))
								.click();
						isRecordSelectedForDeptReview = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForDeptReview) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsNewDocReviewTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedForDeptReview;
	}
}
