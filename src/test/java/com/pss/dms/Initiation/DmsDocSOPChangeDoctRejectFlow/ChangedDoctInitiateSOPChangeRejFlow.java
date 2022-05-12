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
public class ChangedDoctInitiateSOPChangeRejFlow extends QMSLoginDetails {

	@Test
	public void toChangedDoctInitiateSOPChangeRejFlow()
			throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ChangedDoctInitiateSOPChangeRejFlow"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("ChangedDoctInitiateSOPChangeRejFlow",
					"PSS-DMS-016", "Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME1"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/button[1]")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(12000);
			driver.findElement(By.id("myActivitiesInDMS")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,
					false);
			sno++;
			Thread.sleep(16000);
			driver.findElement(By.id("changeDocumentId")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change document menu menu",
					sno, false);
			sno++;
			Thread.sleep(16000);
			driver.findElement(By.id("dmsChangeDocInitPageId")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate  submenu", sno,
					false);
			Thread.sleep(10000);
			WebDriverWait wait1 = new WebDriverWait(driver, 460);
			wait1.until(ExpectedConditions.invisibilityOf(driver
					.findElement(By.cssSelector("#initiateChangeInitiateReqGridId > div > div.jtable-busy-message"))));
			methodToSelectRecordForChgDmtInitiate();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void methodToSelectRecordForChgDmtInitiate() throws Exception {
		boolean isRecordSelectedChgDmtInitiation = false;
		int count = 0;
		String documentName = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST_IN_REJECT_FLOW");
		isRecordSelectedChgDmtInitiation = selectRecordChgReqDtlsTableApprovalFlow(documentName,
				isRecordSelectedChgDmtInitiation, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		if (isRecordSelectedChgDmtInitiation) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.linkText("Next"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);

			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(1000);
			driver.findElement(By.id("docBrowseBtnInChangeDocInit"))
					.sendKeys(properties.getProperty("CHANGE_DOCT_UPLOAD_CHG_INITIATE"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Browse the document", sno, false);
			Thread.sleep(10000);
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(15000);
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(1000);
			driver.findElement(By.linkText("Finish")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish button", sno, false);
			Thread.sleep(1000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature pawword", sno,
					false);
			Thread.sleep(1000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(1000);
			WebDriverWait wait1 = new WebDriverWait(driver, 70);
			wait1.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[12]/a/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(5000);
			sno++;
			WebElement logout = driver.findElement(By.cssSelector("a[href='Logout.do']"));
			JavascriptExecutor jslogout = (JavascriptExecutor) driver;
			jslogout.executeScript("arguments[0].click();", logout);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordChgReqDtlsTableApprovalFlow(String documentName,
			boolean isRecordSelectedChgDmtInitiation, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("initiateChangeInitiateReqGridId"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {// *[@id="initiateChangeInitiateReqGridId"]/div/div[4]/table/tbody/tr/td[3]
			String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((documentName == null) || ("".equalsIgnoreCase(documentName)))) {
				documentName = driver
						.findElement(By.xpath(
								"//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((documentName == null) || ("".equalsIgnoreCase(documentName))) {
				documentName = driver
						.findElement(By
								.xpath("//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr/td[3]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver.findElement(
								By.xpath("//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr[" + i
										+ "]/td[3]"))
								.getText();// documentTypeName
						if (documentName.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr["
											+ i + "]/td[3]"))
									.click();
							isRecordSelectedChgDmtInitiation = true;
							break;
						}
					}
					if (isRecordSelectedChgDmtInitiation) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath(
									"//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr/td[3]"))
							.getText();
					if (documentName.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By
								.xpath("//*[@id=\"initiateChangeInitiateReqGridId\"]/div/div[4]/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedChgDmtInitiation = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedChgDmtInitiation) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("initiateChangeInitiateReqGridId"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedChgDmtInitiation;
	}
}
