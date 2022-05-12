/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDocsSTPChangeDoctRejectFlow;

import com.pss.dms.HelperPackageDms.Helper;
import com.pss.dms.login.QMSLoginDetails;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
public class ChangedDoctSTPRejectingQAReviewerFlow extends QMSLoginDetails {

	public ChangedDoctSTPRejectingQAReviewerFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoChangedDoctSTPRejectingQAReviewerFlow() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("changeDocumentId")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dmsChangeDocRevPageId")).click();
		Thread.sleep(1000);
		methodToSelectRecordQAReviewSTPRejectedRejFlow();

	}

	private void methodToSelectRecordQAReviewSTPRejectedRejFlow() throws InterruptedException {
		int count = 0;
		String documentQASOPReview = properties.getProperty("DOCUMENT_NAME_STP_DOCT_REQUEST_CHG_DOCT_REJECT_FLOW");
		boolean isRecordSelectedForQAReview = false;
		isRecordSelectedForQAReview = selectRecordQAReviewForRejectingChgDmt(documentQASOPReview,
				isRecordSelectedForQAReview, count);
		Thread.sleep(1000);
		if (isRecordSelectedForQAReview) {
			String applicationWindow = driver.getWindowHandle();
			System.out.println("applicationWindow: " + applicationWindow);
			driver.findElement(By.id("continueBtnInChngDocRevForm")).click();
			Thread.sleep(9000);
			Set<String> pdfWindow = driver.getWindowHandles();
			for (String pdfWindows : pdfWindow) {
				System.out.println("pdfWindows: " + pdfWindows);
				if (!applicationWindow.equalsIgnoreCase(pdfWindows)) {
					Thread.sleep(1000);
					driver.switchTo().window(pdfWindows);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
					boolean exists = driver.findElements(By.id("approveBtnDocRevAnnotsWin")).size() != 0;
					System.out.println("Size: " + driver.findElements(By.id("approveBtnDocRevAnnotsWin")).size());
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					// driver.findElement(By.id("approveBtnDocRevAnnotsWin")).click();
					System.out.println("exists: " + exists);
					if (exists) {
						System.out.println("Coming to if condition");
						Thread.sleep(1000);
						driver.findElement(By
								.cssSelector("#annotationsToolbar > div.flowpaper_bttnComment.flowpaper_tblabelbutton"))
								.click();
						Thread.sleep(1000);
						driver.findElement(
								By.cssSelector("#toolbar_documentViewer_annotations_flowpaper_notetypeselector_point"))
								.click();
						Thread.sleep(1000);
						driver.findElement(By.cssSelector("#page_0_documentViewer_canvasOverlay")).click();
						Thread.sleep(3000);
						if (driver.findElement(By.className("flowpaper_note_container")).isDisplayed()) {
							driver.findElement(By.className("flowpaper_note_textarea"))
									.sendKeys(properties.getProperty("COMMENTS_NOTE_DOCT_SOP_REJECT_FLOW").toString());
							Thread.sleep(1000);
						}
						// Selecting StageWise DropDown
						Select stagesDownList = Helper.getSelectInstance(driver, By.id("stagesOfLCToMove"));
						List<WebElement> webElementList = stagesDownList.getOptions();
						if (webElementList.size() > 0) {
							stagesDownList.selectByIndex(Integer.parseInt("2"));// Initiator
						} else {
							System.out.println("No options in Select(Roles - drop down list)");
						}
						Thread.sleep(1000);
						driver.findElement(By.id("rejectBtnDocRevAnnotsWin")).click();
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
						wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("continueBtnInChngDocRevForm")));
						Thread.sleep(1000);
						driver.findElement(By.id("continueBtnInChngDocRevForm")).click();
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
								driver.findElement(By.cssSelector(
										"#annotationsToolbar > div.flowpaper_bttnComment.flowpaper_tblabelbutton"))
										.click();
								Thread.sleep(1000);
								driver.findElement(By.cssSelector(
										"#toolbar_documentViewer_annotations_flowpaper_notetypeselector_point"))
										.click();
								Thread.sleep(1000);
								driver.findElement(By.cssSelector("#page_0_documentViewer_canvasOverlay")).click();
								Thread.sleep(3000);
								if (driver.findElement(By.className("flowpaper_note_container")).isDisplayed()) {
									driver.findElement(By.className("flowpaper_note_textarea")).sendKeys(
											properties.getProperty("COMMENTS_NOTE_DOCT_SOP_REJECT_FLOW").toString());
									Thread.sleep(1000);
								}
								// Selecting StageWise DropDown
								Select stagesDownList = Helper.getSelectInstance(driver, By.id("stagesOfLCToMove"));
								List<WebElement> webElementList = stagesDownList.getOptions();
								if (webElementList.size() > 0) {
									stagesDownList.selectByIndex(Integer.parseInt("2"));// Initiator
								} else {
									System.out.println("No options in Select(Roles - drop down list)");
								}
								Thread.sleep(1000);
								driver.findElement(By.id("rejectBtnDocRevAnnotsWin")).click();
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

	private boolean selectRecordQAReviewForRejectingChgDmt(String documentQASOPReview,
			boolean isRecordSelectedForQAReview, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeDocReviewTable"));
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
					&& ((documentQASOPReview == null) || ("".equalsIgnoreCase(documentQASOPReview)))) {
				documentQASOPReview = driver
						.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentQASOPReview == null) || ("".equalsIgnoreCase(documentQASOPReview))) {
				documentQASOPReview = driver
						.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr/td[2]")).getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By
										.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// documentTypeName
						if (documentQASOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
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
							.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (documentQASOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr/td[2]"))
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
					table = driver.findElement(By.id("changeDocReviewTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForQAReview;
	}
}
