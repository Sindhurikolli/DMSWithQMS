/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDoctSTPPositiveFlow;

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
public class ApprovalWorkFlowSTPDoctDms extends QMSLoginDetails {

	public ApprovalWorkFlowSTPDoctDms() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoApprovalWorkFlowSTPDoctDms() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsApprovalId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsWorkFlowPageId")).click();
		Thread.sleep(1000);
		selectRecordForWFSTPDoctDms();

	}

	private void selectRecordForWFSTPDoctDms() throws InterruptedException {
		boolean isRecordSelectedWFApprovalSOP = false;
		int count = 0;
		String workFlowName = properties.getProperty("NAME_CREATION_WF_STP_DOCT_TYPE");
		isRecordSelectedWFApprovalSOP = selectRecordWFApprovalSTPDoctDms(workFlowName, isRecordSelectedWFApprovalSOP,
				count);
		Thread.sleep(1000);
		if (isRecordSelectedWFApprovalSOP) {
			driver.findElement(By.id("commentsInWFApprovalWin"))
					.sendKeys(properties.getProperty("COMMENTS_WF_APPROVAL"));
			Thread.sleep(1000);
			driver.findElement(By.id("appBtnInWorkFlowAppWin")).click();
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
		}
	}

	private boolean selectRecordWFApprovalSTPDoctDms(String workFlowName, boolean isRecordSelectedWFApprovalSOP,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsWorkFlowAppTable"));
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
			if ((totalNoOfRecords > 1) && ((workFlowName == null) || ("".equalsIgnoreCase(workFlowName)))) {
				workFlowName = driver
						.findElement(By.xpath("//*[@id=\"dmsWorkFlowAppTable\"]/div/table/tbody/tr[1]/td[7]"))
						.getText();// documentType
			} else if ((workFlowName == null) || ("".equalsIgnoreCase(workFlowName))) {
				workFlowName = driver.findElement(By.xpath("//*[@id=\"dmsWorkFlowAppTable\"]/div/table/tbody/tr/td[7]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String workFlowNameInApproval = driver
								.findElement(By
										.xpath("//*[@id=\"dmsWorkFlowAppTable\"]/div/table/tbody/tr[" + i + "]/td[7]"))
								.getText();// documentTypeName
						if (workFlowName.equalsIgnoreCase(workFlowNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"dmsWorkFlowAppTable\"]/div/table/tbody/tr[" + i + "]/td[11]/button"))
									.click();
							isRecordSelectedWFApprovalSOP = true;
							break;
						}
					}
					if (isRecordSelectedWFApprovalSOP) {
						break;
					}
				} else {
					String workFlowNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsWorkFlowAppTable\"]/div/table/tbody/tr/td[7]"))
							.getText();
					if (workFlowName.equalsIgnoreCase(workFlowNameInApproval)) {
						driver.findElement(
								By.xpath("//*[@id=\"dmsWorkFlowAppTable\"]/div/table/tbody/tr/td[11]/button")).click();
						isRecordSelectedWFApprovalSOP = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedWFApprovalSOP) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsWorkFlowAppTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedWFApprovalSOP;
	}
}
