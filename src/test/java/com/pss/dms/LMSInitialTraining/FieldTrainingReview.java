package com.pss.dms.LMSInitialTraining;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

public class FieldTrainingReview extends QMSLoginDetails {

	@Test
	public void reviewFieldTraining() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "FieldTrainingReview"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Field Training Review", "PSS-LMS-014", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.get(properties.getProperty("LMSURL"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("LMSReviewer"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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
		Thread.sleep(3000);
//		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//		WebElement element1 = driver.findElement(By.id("fieldTrainingReview"));
//		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		driver.findElement(By.id("fieldTrainingReview")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Field Training Review", sno,
				false);
		Thread.sleep(4000);
		methodForFieldTrainingReview();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodForFieldTrainingReview() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("InitialTrainingName");
		isRecordSelected = selectRecordForFieldTrainingReview(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("commentsInLmsFldTngReviewForm"))
					.sendKeys(properties.getProperty("Approval_Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("approveInLmsFldTngReviewForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approve", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(2000);

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK", sno, false);
			driver.findElement(By.className("modal-btn")).click();// Modal Ok button
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		} else {
			System.out.println("Record is not Selected ");
		}
	}

	private boolean selectRecordForFieldTrainingReview(int count, boolean isRecordSelected, String name)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("lmsFieldTrainingReviewGridStore"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"lmsFieldTrainingReviewGridStore\"]/div/div[4]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
			String[] parts = a.split(" of ");
			System.out.println("hi");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println("total record" + totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver
						.findElement(
								By.xpath("//*[@id=\"lmsFieldTrainingReviewGridStore\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// Doc No
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver
						.findElement(By.xpath("//*[@id=\"lmsFieldTrainingReviewGridStore\"]/div/table/tbody/tr/td[2]"))
						.getText();// Doc
				// No
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentNumber = driver.findElement(By.xpath(
								"//*[@id=\"lmsFieldTrainingReviewGridStore\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// Doc No
						if (name.contains(documentNumber)) {
							Thread.sleep(2000);
							driver.findElement(
									By.xpath("//*[@id=\"lmsFieldTrainingReviewGridStore\"]/div/table/tbody/tr[" + i
											+ "]/td[1]/input"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String documentNumber = driver
							.findElement(
									By.xpath("//*[@id=\"lmsFieldTrainingReviewGridStore\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name.contains(documentNumber)) {
						Thread.sleep(2000);
						driver.findElement(
								By.xpath("//*[@id=\"lmsFieldTrainingReviewGridStore\"]/div/table/tbody/tr/td[1]/input"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#lmsFieldTrainingReviewGridStore > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve
					// list
					Thread.sleep(4000);
					table = driver.findElement(By.id("lmsFieldTrainingReviewGridStore"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
