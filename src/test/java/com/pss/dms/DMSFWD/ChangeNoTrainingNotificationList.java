package com.pss.dms.DMSFWD;

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
import com.pss.dms.HelperPackageDms.Helper;

import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

////@Listeners(com.pss.dms.Listerners.TestListener.class)
public class ChangeNoTrainingNotificationList extends QMSLoginDetails {

	@Test
	public void toChangeNoTrainingNotificationList()
			throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ChangeNoTrainingNotificationList"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("ChangeNoTrainingNotificationList", "PSS-DMS-010",
				"Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("DMSURL"));
		Thread.sleep(4000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("DMSDoc_Controller"));

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

		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='dmsTrngNotfListPage.do']")));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.cssSelector("a[href='dmsTrngNotfListPage.do']"));
		jse.executeScript("arguments[0].click();", element);
		sno++;
//        driver.findElement(By.cssSelector("a[href='dmsTrngNotfListPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Training notification list menu",
				sno, false);
		Thread.sleep(1000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#dmsTrngNotfListTable > div > div.jtable-busy-message[style='display: none;']")));
		todoChangeNoTrainingNotificationList();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }

	private void todoChangeNoTrainingNotificationList() throws Exception {
		sno++;
		boolean isRecordSelectedTrainingNotification = false;
		int count1 = 0;
		String documentNameInNotification = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		isRecordSelectedTrainingNotification = selectRecordTrngNotificationSOP(documentNameInNotification,
				isRecordSelectedTrainingNotification, count1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		sno++;
		if (isRecordSelectedTrainingNotification) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.linkText("Next"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("isTrngReqInTrainingRequiredForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Is training required checkbox",
					sno, false);
			sno++;
			Thread.sleep(4000);
			boolean isRecordSelectedSingleApprovalDoctReqSOP = false;
			int count = 0;
			driver.findElement(By.cssSelector("#hiddenIdForAppOrCmnts > div.row.form-group.col-sm-12 > button"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno, false);
			Thread.sleep(4000);
			driver.findElement(By.id("locationTreeForApproverSelect_2_span")).click();
			Thread.sleep(4000);
			sno++;
			String userName = properties.getProperty("DMSTrainingApprover_E_CODE");
			isRecordSelectedSingleApprovalDoctReqSOP = Helper.selectSingleApprovalRecord(driver, userName,
					isRecordSelectedSingleApprovalDoctReqSOP, count);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Approve from record", sno,
					false);
			Thread.sleep(4000);
			sno++;
			if (isRecordSelectedSingleApprovalDoctReqSOP = true) {
				driver.findElement(By.id("selBtnInAppSelectDailog")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno,
						false);
				sno++;
				driver.findElement(By.id("commentsInTrainingRequiredForm"))
						.sendKeys(properties.getProperty("COMMENTS_TRAINING_NOTIFICATION"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
				Thread.sleep(1000);
				driver.findElement(By.linkText("Finish")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on  FINISH button", sno,
						false);
				sno++;
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password",
						sno, false);
				Thread.sleep(1000);
				sno++;
				driver.findElement(By.id("subBtnInValidateESign")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno,
						false);
				Thread.sleep(1000);
				WebDriverWait wait = new WebDriverWait(driver, 70);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
				Thread.sleep(1000);
				driver.findElement(By.className("modal-btn")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
				Thread.sleep(4000);
				sno++;
				driver.findElement(By.className("username")).click();
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
	}

	private boolean selectRecordTrngNotificationSOP(String documentNameInNotification,
			boolean isRecordSelectedTrainingNotification, int count1) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsTrngNotfListTable"));
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
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1)
					&& ((documentNameInNotification == null) || ("".equalsIgnoreCase(documentNameInNotification)))) {
				documentNameInNotification = driver
						.findElement(By.xpath("//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentNameInNotification == null) || ("".equalsIgnoreCase(documentNameInNotification))) {
				documentNameInNotification = driver
						.findElement(By.xpath("//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr/td[2]"))
						.getText();// documentType
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String doctNameInApproval = driver.findElement(By.xpath(
								"//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (documentNameInNotification.equalsIgnoreCase(doctNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr[ " + i + " ]/td[2]"))
									.click();
							isRecordSelectedTrainingNotification = true;
							break;
						}
					}
					if (isRecordSelectedTrainingNotification) {
						break;
					}
				} else {
					String doctNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr/td[2]"))
							.getText();
					if (documentNameInNotification.equalsIgnoreCase(doctNameInApproval)) {
						driver.findElement(
								By.xpath("//*[@id=\"dmsTrngNotfListTable\"]/div/div[4]/table/tbody/tr/td[2]")).click();
						isRecordSelectedTrainingNotification = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedTrainingNotification) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsTrngNotfListTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedTrainingNotification;
	}

}
