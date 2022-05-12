package com.pss.dms.LMSRejectionFlow;

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
import org.testng.Assert;
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

public class CreateNewDocumentSOPResubmit extends QMSLoginDetails {

	@Test
	public void toCreateNewDocumentSOPResubmit()
			throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateNewDocumentSOPResubmit"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CreateNewDocumentSOPResubmit", "PSS-DMS-003", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(4000);
//			WebDriverWait wait = new WebDriverWait(driver, 440);
		driver.get(properties.getProperty("LMSURL"));
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("LMSTrainingCoordinator"));

		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/button[1]")).click();
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
//			if(driver.findElement(By.id("NotificationTableContainer")).isDisplayed())
//			{
//				System.out.println("Notification Window Displayed");
//				Actions action = new Actions(driver);
//				action.sendKeys(Keys.ESCAPE).perform();
//			}
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='createDocument.do']")));

		driver.findElement(By.cssSelector("a[href='createDocument.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New Document", sno, false);
		Thread.sleep(5000);

		Thread.sleep(2000);

		driver.findElement(By.id("rejectedDocumentFormAction")).click();
		Thread.sleep(5000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#documentRejectdTable > div > div.jtable-busy-message[style='display: none;']")));
		methodToCreateDoctRequestSOP();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	}

	private void methodToCreateDoctRequestSOP() throws Exception {
		Thread.sleep(2000);

		boolean isRecordSelected = false;
		int count = 0;
		String documentNameReq = properties.getProperty("DocumentName");
		isRecordSelected = selectRecordDocname(documentNameReq, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		sno++;
		if (isRecordSelected) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(10000);
			sno++;
			driver.findElement(By.id("uploadFileBtnInCreateNewDocInLms"))
					.sendKeys(properties.getProperty("DocumentAttachment"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "upload Document", sno, false);
			sno++;
			Thread.sleep(1000);

			Thread.sleep(3000);
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
			System.out.println("Record ");
		}

	}

	private boolean selectRecordDocname(String documentNameReq, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("documentRejectdTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"documentRejectdTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
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
						.findElement(
								By.xpath("//*[@id=\"documentRejectdTable\"]/div/div[4]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((documentNameReq == null) || ("".equalsIgnoreCase(documentNameReq))) {
				documentNameReq = driver
						.findElement(By.xpath("//*[@id=\"documentRejectdTable\"]/div/div[4]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver.findElement(By.xpath(
								"//*[@id=\"documentRejectdTable\"]/div/div[4]/div/table/tbody/tr[" + i + "]/td[3]"))
								.getText();// documentTypeName
						if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"documentRejectdTable\"]/div/div[4]/div/table/tbody/tr[" + i + "]/td[3]"))
									.click();
							Thread.sleep(2000);

							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(
									By.xpath("//*[@id=\"documentRejectdTable\"]/div/div[4]/div/table/tbody/tr/td[3]"))
							.getText();
					if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(
								By.xpath("//*[@id=\"documentRejectdTable\"]/div/div[4]/div/table/tbody/tr/td[3]"))
								.click();
						Thread.sleep(2000);

						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#documentRejectdTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("documentRejectdTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelected;
	}

}
