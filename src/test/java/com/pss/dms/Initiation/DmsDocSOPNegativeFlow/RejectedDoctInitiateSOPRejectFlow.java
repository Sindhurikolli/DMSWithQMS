/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDocSOPNegativeFlow;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.dms.login.QMSLoginDetails;

import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jeevan
 */
public class RejectedDoctInitiateSOPRejectFlow extends QMSLoginDetails {

	public RejectedDoctInitiateSOPRejectFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoNewDoctSOPInitiateRejectFlow()
			throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RejectedDoctInitiateSOPRejectFlow"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("RejectedDoctInitiateSOPRejectFlow", "PSS-DMS-019",
					"Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(4000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME1"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			JavascriptExecutor jse5 = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.cssSelector("button.button.submit"));
			jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("button.button.submit")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(20000);
			driver.findElement(By.cssSelector("a[href='dmsNewDocInitiatePage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate sub menu", sno,
					false);
			Thread.sleep(10000);
			sno++;
			driver.findElement(By.id("rejectDmsNewDocIntiateFormAction")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on Resubmitted rejected radio button", sno, false);
			Thread.sleep(10000);
			methodToDoRejectedDoctInitiateSOPRejectFlow();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void methodToDoRejectedDoctInitiateSOPRejectFlow() throws Exception {
		sno++;
		int count = 0;
		String documentDeptSOPReview = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		boolean isRecordSelectedForDeptReview = false;
		isRecordSelectedForDeptReview = selectRecordRejectedInitiateDoctSOP(documentDeptSOPReview,
				isRecordSelectedForDeptReview, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		System.out.println("isRecordSelectedForDeptReview: " + isRecordSelectedForDeptReview);
		if (isRecordSelectedForDeptReview) {
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.linkText("Next"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);

			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("versionNoOfDocumentUpload"))
					.sendKeys(properties.getProperty("VERSION_NO_INITIATE_NEW_DOCT"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Enter the version number", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("documentBrowseFileBtn"))
					.sendKeys(properties.getProperty("UPLOAD_DOCT_TEMPLATE_SOP"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(1400);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("reasonForModifyInRejectedEditDocumentform"))
					.sendKeys(properties.getProperty("REASON_MODIFY_REJECTED_DOCT_INITIATE_CMTS"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter thereason for modify", sno,
					false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.linkText("Finish")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(1000);
			sno++;
			WebDriverWait wait1 = new WebDriverWait(driver, 70);
			wait1.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[14]/a/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[14]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordRejectedInitiateDoctSOP(String documentDeptSOPReview,
			boolean isRecordSelectedForDeptReview, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("newDocRejectTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"newDocRejectTable\"]/div/div[5]/div[2]/span")).getText();// For
																														// Ex:
																														// Showing
																														// 1-1
																														// of
																														// 1
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
						.findElement(By.xpath("//*[@id=\"newDocRejectTable\"]/div/div[4]/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentDeptSOPReview == null) || ("".equalsIgnoreCase(documentDeptSOPReview))) {
				documentDeptSOPReview = driver
						.findElement(By.xpath("//*[@id=\"newDocRejectTable\"]/div/div[4]/table/tbody/tr/td[2]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By.xpath(
										"//*[@id=\"newDocRejectTable\"]/div/div[4]/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// documentTypeName
						if (documentDeptSOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By
									.xpath("//*[@id=\"newDocRejectTable\"]/div/div[4]/table/tbody/tr[" + i + "]/td[2]"))
									.click();
							isRecordSelectedForDeptReview = true;
							break;
						}
					}
					if (isRecordSelectedForDeptReview) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"newDocRejectTable\"]/div/div[4]/table/tbody/tr/td[2]"))
							.getText();
					if (documentDeptSOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"newDocRejectTable\"]/div/div[4]/table/tbody/tr/td[2]"))
								.click();
						isRecordSelectedForDeptReview = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForDeptReview) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#newDocRejectTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("newDocRejectTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedForDeptReview;
	}
}
