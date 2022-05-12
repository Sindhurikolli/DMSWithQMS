package com.pss.dms.LMSRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

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

public class DocumentRequestApproval extends QMSLoginDetails {

	@Test
	public void toDocumentRequestApproval() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DocmtApprovalRequestSOP"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("DocmtApprovalRequestSOP", "PSS-DMS-004", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("DMSURL"));
		Thread.sleep(4000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("DMSQAHod"));

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
		Thread.sleep(20000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
//        w.until(ExpectedConditions.visibilityOfElementLocated(By.id("myActivitiesInCapa")));
//        driver.findElement(By.id("myActivitiesInDMS")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        Thread.sleep(4000);
		sno++;
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='dmsNewDocRequestApp.do']")));
		driver.findElement(By.cssSelector("a[href='dmsNewDocRequestApp.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approval menu", sno, false);
//        Thread.sleep(20000);
//        sno++;
//        driver.findElement(By.id("dmsNewDocReqAppPageId")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New document request Submenu", sno,false);
		Thread.sleep(4000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#newDocRequestApprovalTable > div > div.jtable-busy-message[style='display: none;']")));
		todoDocumentRequestApproval();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	}

	private void todoDocumentRequestApproval() throws Exception {
		Thread.sleep(1000);
		sno++;
		boolean isRecordSelectedForApprovalReq = false;
		int count = 0;
		String documentNameReq = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		isRecordSelectedForApprovalReq = selectRecordDocReqApproval(documentNameReq, isRecordSelectedForApprovalReq,
				count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		sno++;
		if (isRecordSelectedForApprovalReq) {
			driver.findElement(By.id("newDocRequestAppComments"))
					.sendKeys(properties.getProperty("COMMENTS_NEW_DOCT_REQ_APPROVAL"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("approveBtnInnewDocRequestApp")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approve button", sno, false);
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
			driver.findElement(By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[2]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordDocReqApproval(String documentNameReq, boolean isRecordSelectedForApprovalReq,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("newDocRequestApprovalTable"));
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
			if ((totalNoOfRecords > 1) && ((documentNameReq == null) || ("".equalsIgnoreCase(documentNameReq)))) {
				documentNameReq = driver
						.findElement(By.xpath("//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentNameReq == null) || ("".equalsIgnoreCase(documentNameReq))) {
				documentNameReq = driver
						.findElement(By.xpath("//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr/td[2]"))
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
										"//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// documentTypeName
						if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
									.click();
							isRecordSelectedForApprovalReq = true;
							break;
						}
					}
					if (isRecordSelectedForApprovalReq) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"newDocRequestApprovalTable\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelectedForApprovalReq = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForApprovalReq) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("newDocRequestApprovalTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedForApprovalReq;
	}

}
