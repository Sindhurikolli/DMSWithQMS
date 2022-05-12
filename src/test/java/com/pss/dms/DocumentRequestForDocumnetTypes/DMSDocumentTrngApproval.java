package com.pss.dms.DocumentRequestForDocumnetTypes;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
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

//@Listeners(com.pss.dms.Listerners.TestListener.class)
public class DMSDocumentTrngApproval extends QMSLoginDetails {

	@Test
	public void toDMSDocTrainingApproval() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DoctTrngApprovalSOP"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("DoctTrngApprovalSOP", "PSS-DMS-011", "Pass");
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
//			sno++;
		Thread.sleep(2000);
//        driver.findElement(By.id("myActivitiesInDMS")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        Thread.sleep(12000);
//        sno++;
//      driver.findElement(By.cssSelector("a[href='']")).click();
//      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approval menu", sno,false);
//      Thread.sleep(8000);
//      sno++;
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='dmsDocTrngAppPage.do']")));
		WebElement elem = driver.findElement(By.cssSelector("a[href='dmsDocTrngAppPage.do']"));
		JavascriptExecutor js5 = (JavascriptExecutor) driver;
		js5.executeScript("arguments[0].click();", elem);

//			driver.findElement(By.cssSelector("a[href='dmsNewDocRequestApp.do']")).click();
//    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on document training sub menu", sno,false);
//        Thread.sleep(4000);
//        driver.findElement(By.id("dmsApprovalId")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on document training sub menu", sno,false);
//            Thread.sleep(4000);
//            driver.findElement(By.cssSelector("a[href='dmsDocTrngAppPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on document training sub menu", sno,
				false);
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#dmsDocumentTrngAppTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(2000);
		methodToSelectRecordToDoTrngApprovalSOP();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }

	private void methodToSelectRecordToDoTrngApprovalSOP() throws Exception {
		sno++;
		boolean isRecordSelectedTrngApprovalSOP = false;
		int count = 0;
		String doctNameTrngApprovalSOP = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		isRecordSelectedTrngApprovalSOP = selectRecordToDOTrngApproval(doctNameTrngApprovalSOP,
				isRecordSelectedTrngApprovalSOP, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		sno++;
		if (isRecordSelectedTrngApprovalSOP) {
			driver.findElement(By.id("commentsInDocTrainingAppGrid"))
					.sendKeys(properties.getProperty("COMMENTS_TRAINING_NOTIFICATION"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("appInDocTrngAppGrid")).click();
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
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordToDOTrngApproval(String doctNameTrngApprovalSOP,
			boolean isRecordSelectedTrngApprovalSOP, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsDocumentTrngAppTable"));
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
					&& ((doctNameTrngApprovalSOP == null) || ("".equalsIgnoreCase(doctNameTrngApprovalSOP)))) {
				doctNameTrngApprovalSOP = driver
						.findElement(By.xpath("//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((doctNameTrngApprovalSOP == null) || ("".equalsIgnoreCase(doctNameTrngApprovalSOP))) {
				doctNameTrngApprovalSOP = driver
						.findElement(By.xpath("//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr/td[2]"))
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
										"//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (doctNameTrngApprovalSOP.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
									.click();
							isRecordSelectedTrngApprovalSOP = true;
							break;
						}
					}
					if (isRecordSelectedTrngApprovalSOP) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[1]/td[2]"))
							.getText();
					if (doctNameTrngApprovalSOP.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"dmsDocumentTrngAppTable\"]/div/table/tbody/tr[1]/td[2]"))
								.click();
						isRecordSelectedTrngApprovalSOP = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedTrngApprovalSOP) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsDocumentTrngAppTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedTrngApprovalSOP;
	}

}
