package com.pss.dms.DMSRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.dms.HelperPackageDms.Helper;

import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

public class CreateDocumentRequestResubmit extends QMSLoginDetails {

	@Test
	public void toCreateDocumentRequestResubmit()
			throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateDocumentRequestSOP"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CreateDocumentRequestSOP", "PSS-DMS-003", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(4000);
//			WebDriverWait wait = new WebDriverWait(driver, 440);
		driver.get(properties.getProperty("DMSURL"));
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("DMSInitiator"));

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
		document.add(new Paragraph(
				sno + "." + "Enter the username, password and click on login button" + Utilities.prepareSSNumber(sno),
				font));
		document.add(im);

		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		Thread.sleep(2000);

		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='dmsNewDocRequestPage.do']")));

		driver.findElement(By.cssSelector("a[href='dmsNewDocRequestPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New Request", sno, false);
		Thread.sleep(2000);

		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(2000);
		driver.findElement(By.id("rejectedNewDocumentFormAction")).click();
		Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#newDocumentRejectTable > div > div.jtable-busy-message[style='display: none;']")));
		todoCreateDocumentRequestResubmit();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	}

	private void todoCreateDocumentRequestResubmit() throws Exception {
		Thread.sleep(1000);
		sno++;
		boolean isRecordSelectedForDocumentRequest = false;
		int count = 0;
		String documentNameReq = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		isRecordSelectedForDocumentRequest = selectRecordDocReqApproval(documentNameReq,
				isRecordSelectedForDocumentRequest, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		sno++;
		if (isRecordSelectedForDocumentRequest) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			driver.findElement(By.id("commentsInRejNewDocRequestPage")).sendKeys(properties.getProperty("ReSubmitted"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.linkText("Finish")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish button", sno, false);
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
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordDocReqApproval(String documentNameReq, boolean isRecordSelectedForDocumentRequest,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("newDocumentRejectTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//        int totalNoOfRecords = 0;
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            try {
//                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((documentNameReq == null) || ("".equalsIgnoreCase(documentNameReq)))) {
				documentNameReq = driver
						.findElement(By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentNameReq == null) || ("".equalsIgnoreCase(documentNameReq))) {
				documentNameReq = driver
						.findElement(By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr/td[2]"))
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
										"//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// documentTypeName
						if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
									.click();
							isRecordSelectedForDocumentRequest = true;
							break;
						}
					}
					if (isRecordSelectedForDocumentRequest) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelectedForDocumentRequest = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForDocumentRequest) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("newDocumentRejectTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedForDocumentRequest;
	}

}
