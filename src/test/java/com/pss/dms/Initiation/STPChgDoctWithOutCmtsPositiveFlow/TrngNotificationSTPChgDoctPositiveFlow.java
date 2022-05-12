/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.STPChgDoctWithOutCmtsPositiveFlow;

import com.pss.dms.HelperPackageDms.Helper;
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
public class TrngNotificationSTPChgDoctPositiveFlow extends QMSLoginDetails {

	public TrngNotificationSTPChgDoctPositiveFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoTrngNotificationSTPChgDoctPositiveFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("trngNotficationId")).click();
		Thread.sleep(1000);
		methodToSelectRecordTrainingNotificationListSTPChgDoctPositive();
		Thread.sleep(1000);

	}

	private void methodToSelectRecordTrainingNotificationListSTPChgDoctPositive() throws InterruptedException {
		boolean isRecordSelectedTrainingNotification = false;
		int count = 0;
		String documentNameInNotification = properties
				.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST_CHG_DOCT_APPROVAL_FLOW");
		isRecordSelectedTrainingNotification = selectRecordTrngNotificationSTPChgDoctPositive(
				documentNameInNotification, isRecordSelectedTrainingNotification, count);
		Thread.sleep(1000);
		if (isRecordSelectedTrainingNotification) {
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("isTrngReqInTrainingRequiredForm")).click();
			Thread.sleep(1000);
			methodToSelectRecordSingleApprovalTrngNotificationSTPChgDoct();
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordTrngNotificationSTPChgDoctPositive(String documentNameInNotification,
			boolean isRecordSelectedTrainingNotification, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsTrngNotfListTable"));
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
					&& ((documentNameInNotification == null) || ("".equalsIgnoreCase(documentNameInNotification)))) {
				documentNameInNotification = driver
						.findElement(By.xpath("//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentNameInNotification == null) || ("".equalsIgnoreCase(documentNameInNotification))) {
				documentNameInNotification = driver
						.findElement(By.xpath("//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr/td[2]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String doctNameInApproval = driver
								.findElement(By.xpath(
										"//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// documentTypeName
						if (documentNameInNotification.equalsIgnoreCase(doctNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr[" + i + "]/td[2]"))
									.click();
							isRecordSelectedTrainingNotification = true;
							break;
						}
					}
					if (isRecordSelectedTrainingNotification) {
						break;
					}
				} else {
					String doctNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr/td[2]"))
							.getText();
					if (documentNameInNotification.equalsIgnoreCase(doctNameInApproval)) {
						driver.findElement(
								By.xpath("//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr/td[2]")).click();
						isRecordSelectedTrainingNotification = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedTrainingNotification) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsTrngNotfListTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedTrainingNotification;
	}

	private void methodToSelectRecordSingleApprovalTrngNotificationSTPChgDoct() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"hiddenIdForAppOrCmnts\"]/div[1]/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("locationTreeForApproverSelect_1_span")).click();
		Thread.sleep(1000);
		int count = 0;
		boolean isRecordSelectedSingleAppvlTrng = false;
		String selectSingleApprovalName = properties.getProperty("SINGLE_APPROVAL");
		isRecordSelectedSingleAppvlTrng = Helper.selectSingleApprovalRecord(driver, selectSingleApprovalName,
				isRecordSelectedSingleAppvlTrng, count);
		Thread.sleep(1000);
		if (isRecordSelectedSingleAppvlTrng) {
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("commentsInTrainingRequiredForm"))
					.sendKeys(properties.getProperty("COMMENTS_TRAINING_NOTIFICATION"));
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
			Thread.sleep(1000);
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		}
	}
}
