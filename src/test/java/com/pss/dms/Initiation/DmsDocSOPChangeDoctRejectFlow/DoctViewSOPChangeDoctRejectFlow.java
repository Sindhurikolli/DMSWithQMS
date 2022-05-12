/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDocSOPChangeDoctRejectFlow;

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
public class DoctViewSOPChangeDoctRejectFlow extends QMSLoginDetails {

	public DoctViewSOPChangeDoctRejectFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoDoctViewSOPChangeDoctRejectFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsDocViewId")).click();
		Thread.sleep(1000);
		methodToDoSearchViewSOPChgDoctRejectFlow();
	}

	private void methodToDoSearchViewSOPChgDoctRejectFlow() throws InterruptedException {
		driver.findElement(By.id("dmsDocViewListSearchBtnId")).click();
		Thread.sleep(1000);
		String doctNameView = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST_CHG_DOCT_REJECT_FLOW");
		boolean isRecordSelected = false;
		int count = 0;
		String applicationWindow = driver.getWindowHandle();
		Thread.sleep(1000);
		isRecordSelected = selectDoctRecdViewSOPChgDoctRejectFlow(doctNameView, isRecordSelected, count);
		Thread.sleep(2000);
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

	private boolean selectDoctRecdViewSOPChgDoctRejectFlow(String doctNameView, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsDocViewTable"));
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
			if ((totalNoOfRecords > 1) && ((doctNameView == null) || ("".equalsIgnoreCase(doctNameView)))) {
				doctNameView = driver.findElement(By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((doctNameView == null) || ("".equalsIgnoreCase(doctNameView))) {
				doctNameView = driver.findElement(By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr/td[5]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(
										By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr[" + i + "]/td[5]"))
								.getText();// documentTypeName
						if (doctNameView.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr[" + i + "]/td[10]/img"))
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
							.findElement(By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr/td[5]")).getText();
					if (doctNameView.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr/td[10]/img"))
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
					table = driver.findElement(By.id("dmsDocViewTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
