/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.Initiation.DmsSTPDoctWithCmtsNegativeFlow;

import com.pss.dms.HelperPackageDms.Helper;
import com.pss.dms.login.QMSLoginDetails;

import org.openqa.selenium.By;
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
public class DoctInitiateSTPNegativeFlow extends QMSLoginDetails {

	public DoctInitiateSTPNegativeFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoDoctInitiateSTPNegativeFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("newDocMenuListListId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsNewDocInitPageId")).click();
		Thread.sleep(1000);
		methodToSelectRecdNewDmtInitiateSTPNegativeFlow();

	}

	private void methodToSelectRecdNewDmtInitiateSTPNegativeFlow() throws InterruptedException {
		int count = 0;
		String documentNameSTPInitiate = properties.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST_REJECT_FLOW");
		boolean isRecordSelectedDocumentInitiate = false;
		isRecordSelectedDocumentInitiate = Helper.selectDocumentInitiateRecord(driver, documentNameSTPInitiate,
				isRecordSelectedDocumentInitiate, count);
		Thread.sleep(1000);
		if (isRecordSelectedDocumentInitiate) {
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("documentNoOfDocumentUpload"))
					.sendKeys(properties.getProperty("DOCT_NO_INITIATE_NEW_DOCT_STP_REJECT_FLOW"));
			Thread.sleep(1000);
			driver.findElement(By.id("versionNoOfDocumentUpload"))
					.sendKeys(properties.getProperty("VERSION_NO_INITIATE_NEW_DOCT"));
			Thread.sleep(1000);
			driver.findElement(By.id("documentBrowseFileBtn"))
					.sendKeys(properties.getProperty("DOCT_INITIATE_DOCT_UPLOAD"));
			Thread.sleep(1400);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(2000);
			methodToAddFormatDoctInitiateSTPNegativeFlow();
		}
	}

	private void methodToAddFormatDoctInitiateSTPNegativeFlow() throws InterruptedException {
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
				.sendKeys(properties.getProperty("DOCT_NAME_FORMAT_STP_REJECT_FLOW"));
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
