
package com.pss.dms.Initiation.DmsDocSOPChangeDoctApprovalFlow;

import com.pss.dms.HelperPackageDms.Helper;
import com.pss.dms.login.QMSLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewDoctSOPInitiateChgDoctPositiveFlow extends QMSLoginDetails {

	@Test
	public void toDoNewDoctSOPInitiateChgDoctPositiveFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"changeDocumentId\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dmsChangeDocReqPageId\"]/a")).click();
		Thread.sleep(1000);
		methodToSelectRecdNewDocumentInitiateSOPPositiveFlow();
	}

	private void methodToSelectRecdNewDocumentInitiateSOPPositiveFlow() throws InterruptedException {
		int count = 0;
		String documentNameSOPInitiate = properties
				.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST_CHG_DOCT_APPROVAL_FLOW");
		boolean isRecordSelectedDocumentInitiate = false;
		isRecordSelectedDocumentInitiate = Helper.selectDocumentInitiateRecord(driver, documentNameSOPInitiate,
				isRecordSelectedDocumentInitiate, count);
		Thread.sleep(1000);
		if (isRecordSelectedDocumentInitiate) {
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("documentNoOfDocumentUpload"))
					.sendKeys(properties.getProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP_CHG_APPROVAL_FLOW"));
			Thread.sleep(1000);
			driver.findElement(By.id("versionNoOfDocumentUpload"))
					.sendKeys(properties.getProperty("VERSION_NO_INITIATE_NEW_DOCT"));
			Thread.sleep(1000);
			driver.findElement(By.id("documentBrowseFileBtn"))
					.sendKeys(properties.getProperty("DOCT_INITIATE_DOCT_UPLOAD"));
			Thread.sleep(1400);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(2000);
			methodToAddFormatDoctInitiateSOPPositiveFlow();
		} else {
			System.out.println("Record is not Selected");
		}

	}

	private void methodToAddFormatDoctInitiateSOPPositiveFlow() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"newFormatsJtableInNewDocIntiate\"]/div/div[3]/div[2]/span/span[2]"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"newFormatsJtableInNewDocIntiate\"]/div/div[3]/div[1]")).click();// Just
																												// Clicking
																												// the
																												// header
																												// of
																												// the
																												// Format
																												// table
		Thread.sleep(1000);
		driver.findElement(By.id("createFormatFile1")).sendKeys(properties.getProperty("DOCT_INITIATE_DOCT_UPLOAD"));
		Thread.sleep(1000);
		driver.findElement(By.id("createFormatName1"))
				.sendKeys(properties.getProperty("DOCT_NAME_FORMAT_SOP_CHG_APPROVAL_FLOW"));
		Thread.sleep(1000);
		driver.findElement(By.linkText("Next")).click();
		Thread.sleep(2000);
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
	}
}
