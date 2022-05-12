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
public class TemplateApprovalSTPDoctDms extends QMSLoginDetails {

	public TemplateApprovalSTPDoctDms() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void toDoTemplateApprovalSTPDoctDms() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsApprovalId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsTemplateAppPageId")).click();
		Thread.sleep(1000);
		methodToSelectRecordApproveTemplateSTP();

	}

	private void methodToSelectRecordApproveTemplateSTP() throws InterruptedException {
		boolean isRecordSelectedApproveTemplateSTP = false;
		int count = 0;
		String templateName = properties.getProperty("TEMPLATE_NAME_CREATE_DOCT_STP");
		isRecordSelectedApproveTemplateSTP = selectRecordApproveTemplateSTP(templateName,
				isRecordSelectedApproveTemplateSTP, count);
		Thread.sleep(1000);
		if (isRecordSelectedApproveTemplateSTP) {
			driver.findElement(By.id("commentsInViewTemplatesApproval"))
					.sendKeys(properties.getProperty("COMMENTS_TEMPLATE_APPROVAL"));
			Thread.sleep(1000);
			driver.findElement(By.id("appBtnInTemplateAppWin")).click();
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

	private boolean selectRecordApproveTemplateSTP(String templateName, boolean isRecordSelectedApproveTemplateSTP,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsTemplateAppTable"));
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
			if ((totalNoOfRecords > 1) && ((templateName == null) || ("".equalsIgnoreCase(templateName)))) {
				templateName = driver
						.findElement(By.xpath("//*[@id=\"dmsTemplateAppTable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((templateName == null) || ("".equalsIgnoreCase(templateName))) {
				templateName = driver.findElement(By.xpath("//*[@id=\"dmsTemplateAppTable\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String templateNameInApproval = driver
								.findElement(By
										.xpath("//*[@id=\"dmsTemplateAppTable\"]/div/table/tbody/tr[" + i + "]/td[3]"))
								.getText();// documentTypeName
						if (templateName.equalsIgnoreCase(templateNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"dmsTemplateAppTable\"]/div/table/tbody/tr[" + i + "]/td[7]/button"))
									.click();
							isRecordSelectedApproveTemplateSTP = true;
							break;
						}
					}
					if (isRecordSelectedApproveTemplateSTP) {
						break;
					}
				} else {
					String templateNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsTemplateAppTable\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (templateName.equalsIgnoreCase(templateNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"dmsTemplateAppTable\"]/div/table/tbody/tr/td[7]/button"))
								.click();
						isRecordSelectedApproveTemplateSTP = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedApproveTemplateSTP) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsTemplateAppTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedApproveTemplateSTP;
	}

}
