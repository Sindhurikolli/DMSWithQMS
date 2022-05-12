/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.DmsAdmin;

import com.pss.dms.HelperPackageDms.Helper;
import com.pss.dms.login.QMSLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class DmsDocumentTypeCreation extends QMSLoginDetails {

	public DmsDocumentTypeCreation() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoDmsDocumentTypeCreation() throws InterruptedException {
		Thread.sleep(1200);
//        driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
//        Thread.sleep(1000);
		driver.findElement(By.id("adminTabId")).click();
		Thread.sleep(5000);

		methodToDoDmsDocumentTypeCreation();
	}

	private void methodToDoDmsDocumentTypeCreation() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]/a")).click();
		Thread.sleep(1000);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
		Thread.sleep(3000);
		driver.findElement(By.linkText("Next")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("documentTypeNameInCreateDocType"))
				.sendKeys(properties.getProperty("DOCUMENT_TYPE_CREATION_NAME"));
		Thread.sleep(5000);
		driver.findElement(By.id("fullFormInCreateDocType"))
				.sendKeys(properties.getProperty("FULL_FORM_DOCT_TYPE_CREATION"));
		Thread.sleep(5000);
		driver.findElement(By.id("documentTypeShortFormInCreateDocType"))
				.sendKeys(properties.getProperty("DOCUMENT_TYPE_CREATION_NAME"));
		Thread.sleep(5000);
		methodToSelectRecordForSingleApproval();
	}

	private void methodToSelectRecordForSingleApproval() throws InterruptedException {
		boolean isRecordSelectedForSingleApproval = false;
		driver.findElement(By.id("selectRepToInCreateUser")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("locationTreeForApproverSelect_1_span")).click();
		Thread.sleep(1000);
		int count = 0;
		String singleApprovalName = properties.getProperty("SINGLE_APPROVAL");
		isRecordSelectedForSingleApproval = Helper.selectSingleApprovalRecord(driver, singleApprovalName,
				isRecordSelectedForSingleApproval, count);
		Thread.sleep(1000);
		if (isRecordSelectedForSingleApproval) {
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
			driver.findElement(By.linkText("Finish")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
			Thread.sleep(1000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(1000);
		} else {
			System.out.println("Record is not Selected");
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
		}
	}
}
