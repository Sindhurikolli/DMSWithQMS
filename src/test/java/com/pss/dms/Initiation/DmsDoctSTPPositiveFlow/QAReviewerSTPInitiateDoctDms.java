package com.pss.dms.Initiation.DmsDoctSTPPositiveFlow;

import com.pss.dms.login.QMSLoginDetails;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QAReviewerSTPInitiateDoctDms extends QMSLoginDetails {

	@Test
	public void toDoQAReviewerSOP() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dmsNewDocRevPageId\"]/a")).click();
		Thread.sleep(1000);
		metodToDoQAReviewerSOP();
	}

	private void metodToDoQAReviewerSOP() throws InterruptedException {
		int count = 0;
		String documentDeptSOPReview = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST1");
		boolean isRecordSelectedForQAReview = false;
		isRecordSelectedForQAReview = selectRecordQAReviewStage(documentDeptSOPReview, isRecordSelectedForQAReview,
				count);
		Thread.sleep(1000);
		if (isRecordSelectedForQAReview) {
			String applicationWindow = driver.getWindowHandle();
			System.out.println("applicationWindow: " + applicationWindow);
			driver.findElement(By.id("continueBtnInNewDocRevForm")).click();
			Thread.sleep(9000);
			Set<String> pdfWindow = driver.getWindowHandles();
			for (String pdfWindows : pdfWindow) {
				System.out.println("pdfWindows: " + pdfWindows);
				if (!applicationWindow.equalsIgnoreCase(pdfWindows)) {
					driver.switchTo().window(pdfWindows);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
					boolean exists = driver.findElements(By.id("approveBtnDocRevAnnotsWin")).size() != 0;
					System.out.println("Size: " + driver.findElements(By.id("approveBtnDocRevAnnotsWin")).size());
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					System.out.println("exists: " + exists);
					if (exists) {
						Thread.sleep(1000);
						driver.findElement(By.id("approveBtnDocRevAnnotsWin")).click();
						Thread.sleep(1000);
						driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
						Thread.sleep(1000);
						driver.findElement(By.id("subBtnInValidateESign")).click();
						Thread.sleep(1000);
						WebDriverWait wait = new WebDriverWait(driver, 70);
						wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
						Thread.sleep(1000);
						driver.findElement(By.className("modal-btn")).click();
						Thread.sleep(4000);
						break;
					} else {
						System.out.println("Coming to else condition");
						Thread.sleep(1000);
						driver.close();
						Thread.sleep(2000);
						driver.switchTo().window(applicationWindow);
						Thread.sleep(2000);
						WebDriverWait wait2 = new WebDriverWait(driver, 70);
						wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("continueBtnInNewDocRevForm")));
						Thread.sleep(1000);
						driver.findElement(By.id("continueBtnInNewDocRevForm")).click();
						Thread.sleep(9000);
						Set<String> pdf = driver.getWindowHandles();
						for (String pdfs : pdf) {
							System.out.println("pdfs: " + pdfs);
							if (!applicationWindow.equalsIgnoreCase(pdfs)) {
								Thread.sleep(1000);
								driver.switchTo().window(pdfs);
								WebDriverWait wait1 = new WebDriverWait(driver, 200);
								wait1.until(ExpectedConditions
										.presenceOfElementLocated(By.id("approveBtnDocRevAnnotsWin")));
								Thread.sleep(1000);
								driver.findElement(By.id("approveBtnDocRevAnnotsWin")).click();
								Thread.sleep(1000);
								driver.findElement(By.id("eSignPwdInWnd"))
										.sendKeys(properties.getProperty("E_SignPassword"));
								Thread.sleep(1000);
								driver.findElement(By.id("subBtnInValidateESign")).click();
								Thread.sleep(1000);
								WebDriverWait wait = new WebDriverWait(driver, 70);
								wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
								Thread.sleep(1000);
								driver.findElement(By.className("modal-btn")).click();
								Thread.sleep(4000);
								break;
							}
						}
						break;
					}
				}
			}
			Thread.sleep(1000);
			driver.switchTo().window(applicationWindow);
			Thread.sleep(1000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordQAReviewStage(String documentDeptSOPReview, boolean isRecordSelectedForQAReview,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsNewDocReviewTable"));
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
					&& ((documentDeptSOPReview == null) || ("".equalsIgnoreCase(documentDeptSOPReview)))) {
				documentDeptSOPReview = driver
						.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// documentType
			} else if ((documentDeptSOPReview == null) || ("".equalsIgnoreCase(documentDeptSOPReview))) {
				documentDeptSOPReview = driver
						.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr/td[1]")).getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By
										.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[1]"))
								.getText();// documentTypeName
						if (documentDeptSOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[1]"))
									.click();
							isRecordSelectedForQAReview = true;
							break;
						}
					}
					if (isRecordSelectedForQAReview) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr/td[1]"))
							.getText();
					if (documentDeptSOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr/td[1]"))
								.click();
						isRecordSelectedForQAReview = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForQAReview) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsNewDocReviewTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedForQAReview;
	}
}
