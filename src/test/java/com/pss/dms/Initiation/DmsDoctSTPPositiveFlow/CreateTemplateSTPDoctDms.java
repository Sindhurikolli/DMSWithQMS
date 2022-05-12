
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

public class CreateTemplateSTPDoctDms extends QMSLoginDetails {

	@Test
	public void toDoCreateTemplateSTPDoctDms() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsTemplatePageId")).click();
		Thread.sleep(1000);
		methodToDoTemplateDmsSTP();
	}

	private void methodToDoTemplateDmsSTP() throws InterruptedException {
		driver.findElement(By.linkText("Next")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("templateFormatName"))
				.sendKeys(properties.getProperty("TEMPLATE_NAME_CREATE_DOCT_STP"));
		Thread.sleep(1000);
		driver.findElement(By.id("templateFormatDescription"))
				.sendKeys(properties.getProperty("TEMPLATE_DESCRIPTION_DOCT_STP"));
		Thread.sleep(1000);
		Select rolesDropDownList = Helper.getSelectInstance(driver, By.id("documentTypeOfTemplateFormat"));
		List<WebElement> webElementList = rolesDropDownList.getOptions();
		if (webElementList.size() > 0) {
			rolesDropDownList.selectByIndex(Integer.parseInt("2"));
		} else {
			System.out.println("No options in Select(Roles - drop down list)");
		}
		Thread.sleep(1000);// regWorkFlowSelect
		List<WebElement> list = driver.findElements(By.id("regWorkFlowSelect"));
		for (WebElement we : list) {
			System.out.println("we=" + we.getText());
			if (!"".equals(we.getText().trim())) {
//                driver.findElement(By.id("regWorkFlowSelect")).click();
				we.click();
				break;
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("treeContainer_3_span")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
		Thread.sleep(1000);
		methodToSelectSingleApprovalRecordInTemplateSTP();
	}

	private void methodToSelectSingleApprovalRecordInTemplateSTP() throws InterruptedException {
		boolean isRecordSelectedSingleApprovalTempSTP = false;
		int count = 0;
		driver.findElement(By.id("appBySelBtnInDmsRolesReg")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("locationTreeForApproverSelect_1_span")).click();
		Thread.sleep(1000);
		String userName = properties.getProperty("SINGLE_APPROVAL");
		isRecordSelectedSingleApprovalTempSTP = Helper.selectSingleApprovalRecord(driver, userName,
				isRecordSelectedSingleApprovalTempSTP, count);
		Thread.sleep(1000);
		if (isRecordSelectedSingleApprovalTempSTP) {
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("uploadBtn")).sendKeys(properties.getProperty("UPLOAD_DOCT_TEMPLATE_STP"));
			Thread.sleep(1000);
//            driver.findElement(By.id("uploadBtn")).click();
			driver.findElement(By.id("versionNoOfCreateTemplate"))
					.sendKeys(properties.getProperty("VERSION_TEMPLATE_DOCT_STP"));
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
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		}
	}
}
