/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.STPChgDoctWithOutCmtsPositiveFlow;

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
public class ChangeDoctRequestSTPWithPositiveFlow extends QMSLoginDetails {

	public ChangeDoctRequestSTPWithPositiveFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoChangeDoctRequestSTPWithPositiveFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("changeDocumentId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsChangeDocReqPageId")).click();
		Thread.sleep(1000);
		methodToDoSelectChangeDoctReqSTPChgApprovalFlow();
	}

	private void methodToDoSelectChangeDoctReqSTPChgApprovalFlow() throws InterruptedException {
		driver.findElement(By.linkText("Next")).click();
		Thread.sleep(1000);
		Select rolesDropDownList = Helper.getSelectInstance(driver, By.id("changeTypeInChangeDocReq"));
		List<WebElement> webElementList = rolesDropDownList.getOptions();
		if (webElementList.size() > 0) {
			rolesDropDownList.selectByIndex(Integer.parseInt("1"));// Minor
		} else {
			System.out.println("No options in Select(Roles - drop down list)");
		}
		Thread.sleep(1000);
		driver.findElement(By.id("selDocBtnInChangeDocReq")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("locationTreeForDocumentSelect_1_span")).click();
		Thread.sleep(1000);
		methodToSelectDoctChangeDoctReqSTPChgApprovalFlow();
	}

	private void methodToSelectDoctChangeDoctReqSTPChgApprovalFlow() throws InterruptedException {
		boolean isRecordSelected = false;
		int count = 0;
		String selectDmtNameForChgDoct = properties
				.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST_CHG_DOCT_APPROVAL_FLOW");
		isRecordSelected = selectRecordDoctChangeDoctReqSTPChgApprovalFlow(selectDmtNameForChgDoct, isRecordSelected,
				count);
		Thread.sleep(1000);
		if (isRecordSelected) {
			driver.findElement(By.id("selBtnInDocumentSelectDailog")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("changeControlNumberInChangeDocReq"))
					.sendKeys(properties.getProperty("CHANGE_CONTROL_NUM_CHG_REQ_STP"));
			Thread.sleep(1000);
			driver.findElement(By.id("reasonForRevisionInChangeDocReq"))
					.sendKeys(properties.getProperty("REASON_FOR_REVISION_CHG_REQ_STP"));
			Thread.sleep(1000);
			driver.findElement(By.id("rejOrTerminateCommentsInChangeDocReq"))
					.sendKeys(properties.getProperty("COMMENTS_CHG_REQ_STP"));
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
			System.out.println("Record is not Selected");
			driver.findElement(By.id("cancelBtnInDocumentSelectDailog")).click();
			Thread.sleep(1000);
		}
	}

	private boolean selectRecordDoctChangeDoctReqSTPChgApprovalFlow(String selectDmtNameForChgDoct,
			boolean isRecordSelected, int count) {
		WebElement table = driver.findElement(By.id("documentsTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if (/* (totalNoOfRecords > 1) && */((selectDmtNameForChgDoct == null)
					|| ("".equalsIgnoreCase(selectDmtNameForChgDoct)))) {
				selectDmtNameForChgDoct = driver
						.findElement(By.xpath("//*[@id=\"documentsTableContainer\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((selectDmtNameForChgDoct == null) || ("".equalsIgnoreCase(selectDmtNameForChgDoct))) {
				selectDmtNameForChgDoct = driver
						.findElement(By.xpath("//*[@id=\"documentsTableContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < perPageNoOfRecordsPresent) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By.xpath(
										"//*[@id=\"documentsTableContainer\"]/div/table/tbody/tr[" + i + "]/td[4]"))
								.getText();// documentTypeName
						if (selectDmtNameForChgDoct.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By
									.xpath("//*[@id=\"documentsTableContainer\"]/div/table/tbody/tr[" + i + "]/td[4]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"documentsTableContainer\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (selectDmtNameForChgDoct.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"documentsTableContainer\"]/div/table/tbody/tr/td[4]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;

				break;
			}
		}
		return isRecordSelected;
	}
}
