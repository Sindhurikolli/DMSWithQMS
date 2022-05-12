/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDocSOPNegativeFlow;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pss.dms.login.QMSLoginDetails;

/**
 *
 * @author Jeevan
 */
public class MasterDoctListSOPRejectFlow extends QMSLoginDetails {

	public MasterDoctListSOPRejectFlow() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void toDoMasterDoctListSOPRejectFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("masterListTabIdInDMS")).click();
		Thread.sleep(1000);
		methodToViewMasterListDoctSOPRejectFlow();

	}

	private void methodToViewMasterListDoctSOPRejectFlow() throws InterruptedException {
		driver.findElement(By.id("tree_1_span")).click();
		Thread.sleep(1000);
		boolean isRecordSelected = false;
		int count = 0;
		String doctName = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		String applicationWindow = driver.getWindowHandle();
		Thread.sleep(1000);
		isRecordSelected = selectRecordToViewMasterListDoctSOPRejectFlow(doctName, isRecordSelected, count);
		Thread.sleep(1000);
		if (isRecordSelected) {
			Set<String> pdfWindow = driver.getWindowHandles();
			for (String pdfs : pdfWindow) {
				if (!applicationWindow.equalsIgnoreCase(pdfs)) {
					driver.switchTo().window(pdfs);
					Thread.sleep(4000);
					driver.close();
					Thread.sleep(1000);
					driver.switchTo().window(applicationWindow);
					Thread.sleep(1000);
					break;
				}
			}
		}
	}

	private boolean selectRecordToViewMasterListDoctSOPRejectFlow(String doctName, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("documentListForMasterListContainer"));
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
			if ((totalNoOfRecords > 1) && ((doctName == null) || ("".equalsIgnoreCase(doctName)))) {
				doctName = driver
						.findElement(By.xpath(
								"//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((doctName == null) || ("".equalsIgnoreCase(doctName))) {
				doctName = driver
						.findElement(By.xpath(
								"//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr/td[4]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver.findElement(
								By.xpath("//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr["
										+ i + "]/td[4]"))
								.getText();// documentTypeName
						if (doctName.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By
									.xpath("//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr["
											+ i + "]/td[12]/button"))
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
							.findElement(By.xpath(
									"//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr/td[4]"))
							.getText();
					if (doctName.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath(
								"//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr/td[12]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("documentListForMasterListContainer"));// Document Tree approve
																							// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
