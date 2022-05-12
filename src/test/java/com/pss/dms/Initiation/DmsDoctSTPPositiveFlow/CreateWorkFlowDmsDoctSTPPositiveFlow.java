/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDoctSTPPositiveFlow;

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
public class CreateWorkFlowDmsDoctSTPPositiveFlow extends QMSLoginDetails {

	public CreateWorkFlowDmsDoctSTPPositiveFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoCreateWorkFlowDmsDoctSTPPositiveFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("workFlowTabIdInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
		Thread.sleep(1000);
		methodToDoWorkFlowDmsDoctSTPPositiveFlow();
	}

	private void methodToDoWorkFlowDmsDoctSTPPositiveFlow() throws InterruptedException {
		Select rolesDropDownList = Helper.getSelectInstance(driver, By.id("docTypeInWorkFlow"));
		List<WebElement> webElementList = rolesDropDownList.getOptions();
		if (webElementList.size() > 0) {
			rolesDropDownList.selectByIndex(Integer.parseInt(properties.getProperty("DOCT_TYPE_STP_WF_CREATION_DMS")));
		} else {
			System.out.println("No options in Select(Roles - drop down list)");
		}
		Thread.sleep(1000);
		driver.findElement(By.id("dmsWorkFlowSel")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("treeContainer_3_span")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("nameInCreateWorkflow"))
				.sendKeys(properties.getProperty("NAME_CREATION_WF_STP_DOCT_TYPE"));// WorkFlow Name
		Thread.sleep(1000);
		driver.findElement(By.id("checkRev_1")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("diplayNameRev_1"))
				.sendKeys(properties.getProperty("DISPLAY_NAME_WF_STP_DOCT_TYPE_DEPT_REVIEWER"));// Display Name For
																									// Dept Reviewer
		Thread.sleep(1000);
		driver.findElement(By.id("checkRevSignReq_1")).click();// Clicking Sign Required CheckBox
		Thread.sleep(1000);
		driver.findElement(By.id("checkRev_2")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("diplayNameRev_2"))
				.sendKeys(properties.getProperty("DISPLAY_NAME_WF_STP_DOCT_TYPE_QA_REVIEWER"));
		Thread.sleep(1000);
		driver.findElement(By.id("checkRevSignReq_2")).click();// Clicking Sign Required CheckBox
		Thread.sleep(1000);
		driver.findElement(By.id("checkRev_3")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("diplayNameRev_3"))
				.sendKeys(properties.getProperty("DISPLAY_NAME_WF_STP_DOCT_TYPE_CFT_REVIEW"));
		Thread.sleep(1000);
		driver.findElement(By.id("checkRevSignReq_3")).click();
		Thread.sleep(1000);
		methodToSelectRecordSingleApprovalSTP();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsWFSubmit")).click();
		Thread.sleep(1200);
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
		Thread.sleep(1000);
		driver.findElement(By.id("subBtnInValidateESign")).click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
		Thread.sleep(1000);
		driver.findElement(By.className("modal-btn")).click();
		Thread.sleep(1000);
	}

	private void methodToSelectRecordSingleApprovalSTP() throws InterruptedException {
		driver.findElement(By.id("appBySelBtnInDmsWorkFlow")).click();
		Thread.sleep(1000);
		boolean isRecordSelectedForSingleApprovalWF = false;
		int count = 0;
		String singleApprovalName = properties.getProperty("SINGLE_APPROVAL");
		driver.findElement(By.id("locationTreeForApproverSelect_1_span")).click();
		Thread.sleep(1000);
		isRecordSelectedForSingleApprovalWF = Helper.selectSingleApprovalRecord(driver, singleApprovalName,
				isRecordSelectedForSingleApprovalWF, count);
		Thread.sleep(1000);
		if (isRecordSelectedForSingleApprovalWF) {
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		}
	}
}
