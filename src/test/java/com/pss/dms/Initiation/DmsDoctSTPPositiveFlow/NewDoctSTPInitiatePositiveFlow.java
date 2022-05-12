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

public class NewDoctSTPInitiatePositiveFlow extends QMSLoginDetails {

	@Test
	public void toDoDoctTrngApprovalSOP() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"newDocMenuListListId\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dmsNewDocInitPageId\"]/a")).click();
		Thread.sleep(1000);
		methodToSelectRecordToDoTrngApprovalSOP();
	}

	private void methodToSelectRecordToDoTrngApprovalSOP() throws InterruptedException {
		boolean isRecordSelectedTrngApprovalSOP = false;
		int count = 0;
		String doctNameTrngApprovalSOP = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST1");
		Thread.sleep(1000);
		isRecordSelectedTrngApprovalSOP = selectRecordToDOTrngApproval(doctNameTrngApprovalSOP,
				isRecordSelectedTrngApprovalSOP, count);
		Thread.sleep(1000);
		if (isRecordSelectedTrngApprovalSOP) {
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("documentNoOfDocumentUpload"))
					.sendKeys(properties.getProperty("DOCT_NO_INITIATE_NEW_DOCT_STP"));
			Thread.sleep(1000);
			driver.findElement(By.id("versionNoOfDocumentUpload"))
					.sendKeys(properties.getProperty("VERSION_NO_INITIATE_NEW_DOCT"));
			Thread.sleep(1000);
			driver.findElement(By.id("documentBrowseFileBtn"))
					.sendKeys(properties.getProperty("DOCT_INITIATE_DOCT_UPLOAD"));
			Thread.sleep(1000);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"newFormatsJtableInNewDocIntiate\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			Thread.sleep(3000);
			driver.findElement(By.id("createFormatName1")).sendKeys(properties.getProperty("CREATE_FORMATNAME"));
			Thread.sleep(1000);
			driver.findElement(By.id("createFormatNumber1")).sendKeys(properties.getProperty("CREATE_FORMAT_NUMBER"));
			Thread.sleep(1000);
			driver.findElement(By.id("createFormatVersion1")).sendKeys(properties.getProperty("CREATE_FORMAT_VERSION"));
			Thread.sleep(1000);
			driver.findElement(By.id("createFormatFile1"))
					.sendKeys(properties.getProperty("DOCT_INITIATE_DOCT_UPLOAD"));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"newAnnxrJtableInNewDocIntiate\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			Thread.sleep(3000);
			driver.findElement(By.id("newDocInitCreAnnexureName1")).sendKeys(properties.getProperty("ANNEXURE_NAME"));
			Thread.sleep(1000);
			driver.findElement(By.id("newDocInitCreAnnexureNumber1"))
					.sendKeys(properties.getProperty("ANNEXURE_NUMBER"));
			Thread.sleep(1000);
			driver.findElement(By.id("createAnnexureVersion1")).sendKeys(properties.getProperty("ANNEXURE_VERSION"));
			Thread.sleep(1000);
			driver.findElement(By.id("newDocInitCreAnnexureFile1"))
					.sendKeys(properties.getProperty("DOCT_INITIATE_DOCT_UPLOAD"));
			Thread.sleep(1000);
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText("Finish")).click();
			Thread.sleep(3000);
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

	private boolean selectRecordToDOTrngApproval(String doctNameTrngApprovalSOP,
			boolean isRecordSelectedTrngApprovalSOP, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("newDocIntiateTable"));
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
			if ((totalNoOfRecords > 1)
					&& ((doctNameTrngApprovalSOP == null) || ("".equalsIgnoreCase(doctNameTrngApprovalSOP)))) {
				doctNameTrngApprovalSOP = driver
						.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/table/tbody/tr[1]/td[2]")).getText();// documentType
			} else if ((doctNameTrngApprovalSOP == null) || ("".equalsIgnoreCase(doctNameTrngApprovalSOP))) {
				doctNameTrngApprovalSOP = driver
						.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/table/tbody/tr/td[2]")).getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By
										.xpath("//*[@id=\"newDocIntiateTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (doctNameTrngApprovalSOP.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"newDocIntiateTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
									.click();
							isRecordSelectedTrngApprovalSOP = true;
							break;
						}
					}
					if (isRecordSelectedTrngApprovalSOP) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/div[4]/table/tbody/tr/td[2]"))
							.getText();
					if (doctNameTrngApprovalSOP.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/div[4]/table/tbody/tr/td[2]"))
								.click();
						isRecordSelectedTrngApprovalSOP = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedTrngApprovalSOP) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("newDocIntiateTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedTrngApprovalSOP;
	}
}
