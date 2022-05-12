
package com.pss.dms.Initiation.DmsDoctSTPPositiveFlow;

import com.pss.dms.login.QMSLoginDetails;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateDocumentRequestSTP extends QMSLoginDetails {

	@Test
	public void toCreateDocumentRequestSTP() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"newDocMenuListListId\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dmsNewDocReqPageId\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Next")).click();
		Thread.sleep(1000);
		methodToCreateDoctRequestSOP();
	}

	private void methodToCreateDoctRequestSOP() throws InterruptedException {
		driver.findElement(By.id("documentNameInNewDocRequestPage"))
				.sendKeys(properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST1"));
		Thread.sleep(1000);
		driver.findElement(By.id("keyWordsInNewDocRequestPage"))
				.sendKeys(properties.getProperty("KEYWORDS_DOCUMENT_REQUEST"));
		Thread.sleep(1000);
		Select rolesDropDownList = new Select(driver.findElement(By.id("docTypeInDmsNewDocRequestPage")));
		rolesDropDownList.selectByIndex(2);
		Thread.sleep(1000);
		methodToSelectSingleApprovalInDoctReqSOP();

	}

	private void methodToSelectSingleApprovalInDoctReqSOP() throws InterruptedException {
		driver.findElement(By.id("selectRepToInCreateUser")).click();
		Thread.sleep(1000);
		boolean isRecordSelectedSingleApprovalDoctReqSOP = false;
		Thread.sleep(3000);
		driver.findElement(By.id("locationTreeForApproverSelect_2_switch")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("locationTreeForApproverSelect_3_span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[2]/td[2]")).click();
		Thread.sleep(2000);
		if (isRecordSelectedSingleApprovalDoctReqSOP = true) {
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("locSelBtnInNewDocumentRequest")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("treeContainer_2_switch")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("treeContainer_3_span")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Finish")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
			Thread.sleep(2000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(2000);
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(2000);
		}

	}
}
