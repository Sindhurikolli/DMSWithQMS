/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDocsSTPChangeDoctRejectFlow;

import com.pss.dms.HelperPackageDms.Helper;
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

/**
 *
 * @author Jeevan
 */
public class CreateDoctReqSTPChgDoctRejectFlow extends QMSLoginDetails {

	public CreateDoctReqSTPChgDoctRejectFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoCreateDoctReqSTPChgDoctRejectFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("newDocMenuListListId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsNewDocReqPageId")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Next")).click();
		Thread.sleep(1000);
		metodToCreateDoctRequestSTPChgeDoctRejFlow();

	}

	private void metodToCreateDoctRequestSTPChgeDoctRejFlow() throws InterruptedException {
		driver.findElement(By.id("documentNameInNewDocRequestPage"))
				.sendKeys(properties.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST_CHG_DOCT_REJECT_FLOW"));
		Thread.sleep(1000);
		driver.findElement(By.id("keyWordsInNewDocRequestPage"))
				.sendKeys(properties.getProperty("KEYWORDS_DOCUMENT_REQUEST"));
		Thread.sleep(1000);
		Select rolesDropDownList = Helper.getSelectInstance(driver, By.id("docTypeInDmsNewDocRequestPage"));
		List<WebElement> webElementList = rolesDropDownList.getOptions();
		if (webElementList.size() > 0) {
			rolesDropDownList.selectByIndex(Integer.parseInt("2"));
		} else {
			System.out.println("No options in Select(Roles - drop down list)");
		}
		Thread.sleep(1000);
		methodToSelectCreateDoctReqSTPChgDoctRejFlow();
	}

	private void methodToSelectCreateDoctReqSTPChgDoctRejFlow() throws InterruptedException {
		driver.findElement(By.id("selectRepToInCreateUser")).click();
		Thread.sleep(1000);
		boolean isRecordSelectedSingleApprovalDoctReqChgDocSTPRejectFlow = false;
		driver.findElement(By.id("locationTreeForApproverSelect_1_span")).click();
		Thread.sleep(1000);
		int count = 0;
		String userSingleApprovalName = properties.getProperty("SINGLE_APPROVAL");
		isRecordSelectedSingleApprovalDoctReqChgDocSTPRejectFlow = Helper.selectSingleApprovalRecord(driver,
				userSingleApprovalName, isRecordSelectedSingleApprovalDoctReqChgDocSTPRejectFlow, count);
		Thread.sleep(1000);
		if (isRecordSelectedSingleApprovalDoctReqChgDocSTPRejectFlow) {
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("locSelBtnInNewDocumentRequest")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("treeContainer_2_switch")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("treeContainer_3_span")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
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
