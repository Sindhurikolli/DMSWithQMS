/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDocSOPChangeDoctRejectFlow;

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

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
public class RejectedQAApproverApprovalSOPDocChgRejFlow extends QMSLoginDetails {

	public RejectedQAApproverApprovalSOPDocChgRejFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoRejectedQAApproverApprovalSOPDocChgRejFlow()
			throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/"
					+ "RejectedQAApproverApprovalSOPDocChgRejFlow" + (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("RejectedQAApproverApprovalSOPDocChgRejFlow",
					"PSS-DMS-023", "Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(4000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME5"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			JavascriptExecutor jse5 = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.cssSelector("button.button.submit"));
			jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
			Thread.sleep(20000);
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
			Thread.sleep(8000);
			driver.findElement(By.id("myActivitiesInDMS")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,
					false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("changeDocumentId")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Document menu", sno,
					false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("dmsChangeDocRevPageId")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review sub menu", sno, false);
			Thread.sleep(16000);
			metodToDoQAApproverApprovalChgSOPDoctRejectFlow();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void metodToDoQAApproverApprovalChgSOPDoctRejectFlow() throws Exception {
		int count = 0;
		String documentQASOPApprover = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST_IN_REJECT_FLOW");
		boolean isRecordSelectedForQAApprover = false;
		isRecordSelectedForQAApprover = selectRecQAReviewApprovalSOPChgRejFlow(documentQASOPApprover,
				isRecordSelectedForQAApprover, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		if (isRecordSelectedForQAApprover) {
			String applicationWindow = driver.getWindowHandle();
			System.out.println("applicationWindow: " + applicationWindow);
			driver.findElement(By.id("continueBtnInChngDocRevForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on continue button", sno, false);
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
					Thread.sleep(1000);
					System.out.println("exists: " + exists);
					if (exists) {
						System.out.println("Coming to if condition");
						driver.findElement(By.id("approveBtnDocRevAnnotsWin")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approve button",
								sno, false);
						Thread.sleep(1000);
						driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
								"Enter the E-Signature password", sno, false);
						Thread.sleep(1000);
						driver.findElement(By.id("subBtnInValidateESign")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button",
								sno, false);
						Thread.sleep(4000);
						WebDriverWait wait = new WebDriverWait(driver, 70);
						wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
						Thread.sleep(4000);
						driver.findElement(By.className("modal-btn")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,
								false);
						Thread.sleep(4000);
						sno++;
						driver.findElement(By.className("username")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,
								false);
						Thread.sleep(5000);
						sno++;
						WebElement logout = driver.findElement(By.cssSelector("a[href='Logout.do']"));
						JavascriptExecutor jslogout = (JavascriptExecutor) driver;
						jslogout.executeScript("arguments[0].click();", logout);
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,
								true);
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
						driver.findElement(By.id("continueBtnInChngDocRevForm")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on continue button",
								sno, false);
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
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
										"Click on Approve button", sno, false);
								Thread.sleep(1000);
								driver.findElement(By.id("eSignPwdInWnd"))
										.sendKeys(properties.getProperty("ESIGN_PASSPWD"));
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
										"Enter the E-Signature password", sno, false);
								Thread.sleep(1000);
								driver.findElement(By.id("subBtnInValidateESign")).click();
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
										"Click on submit button", sno, false);
								Thread.sleep(4000);
								WebDriverWait wait = new WebDriverWait(driver, 70);
								wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
								Thread.sleep(1000);
								driver.findElement(By.className("modal-btn")).click();
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button",
										sno, false);
								Thread.sleep(4000);
								sno++;
								driver.findElement(By.className("username")).click();
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ",
										sno, false);
								Thread.sleep(5000);
								sno++;
								WebElement logout = driver.findElement(By.cssSelector("a[href='Logout.do']"));
								JavascriptExecutor jslogout = (JavascriptExecutor) driver;
								jslogout.executeScript("arguments[0].click();", logout);
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ",
										sno, true);
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

	private boolean selectRecQAReviewApprovalSOPChgRejFlow(String documentQASOPApprover,
			boolean isRecordSelectedForQAApprover, int count) throws InterruptedException {
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
					&& ((documentQASOPApprover == null) || ("".equalsIgnoreCase(documentQASOPApprover)))) {
				documentQASOPApprover = driver
						.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentQASOPApprover == null) || ("".equalsIgnoreCase(documentQASOPApprover))) {
				documentQASOPApprover = driver
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
						if (documentQASOPApprover.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
									.click();
							isRecordSelectedForQAApprover = true;
							break;
						}
					}
					if (isRecordSelectedForQAApprover) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (documentQASOPApprover.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelectedForQAApprover = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForQAApprover) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("changeDocReviewTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForQAApprover;
	}
}
