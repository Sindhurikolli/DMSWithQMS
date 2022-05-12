package com.pss.dms.LMSRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

public class TrainingAttendence extends QMSLoginDetails {

	@Test
	public void toTrainingAttendence() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "TrainingAttendance"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Training Attendance", "PSS-LMS-006", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("LMSURL"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("LMSTrainer"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/button[1]")).click();
		Thread.sleep(5000);
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
//		Thread.sleep(3000);
//		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//		WebElement element1 = driver.findElement(By.cssSelector("#reportsTabInCal > div > div.panel-heading.text-center.headerText.workFlow > strong"));
//		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(4000);
		driver.findElement(By.id("trainingAttendance")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Training Attendance", sno, false);
		sno++;
		Thread.sleep(4000);
		todoTrainingAttendence();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void todoTrainingAttendence() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("ADHOC_SCHEDULE_NAME");		
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record and Click on Enter", sno, false);
		sno++;
		isRecordSelected = selectRecord(count, isRecordSelected, name);
		if (isRecordSelected) {
			
			Thread.sleep(8000);
			int count1 = 0;
			boolean isRecordSelected1 = false;
			String name1 = properties.getProperty("SelectRecordForUser");
			isRecordSelected1 = selectRecordForUser(count1, isRecordSelected1, name1);			
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select user", sno, false);
			sno++;
			Thread.sleep(3000);
//			driver.findElement(By.xpath("//*[@id=\"attendanceDetailsJtable\"]/div/table/tbody/tr/td[1]/input")).click();
//			Thread.sleep(2000);
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -2);
			
			driver.findElement(By.id("sessionFromTimeInTrainingAttendanceDialog"))
					.sendKeys(String.valueOf(dateFormat.format(cal.getTime())));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter From Time", sno, false);
			Thread.sleep(1000);
			cal.add(Calendar.MINUTE, 2);
			sno++;
			driver.findElement(By.id("sessionToTimeInTrainingAttendanceDialog"))
					.sendKeys(String.valueOf(dateFormat.format(cal.getTime())));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter To Time", sno, false);
			Thread.sleep(1300);
			sno++;
			driver.findElement(By.id("CommentsInTrainingAttendanceDialog"))
					.sendKeys(properties.getProperty("COMMENTS_IN_TRAINING_ATTENDANCE"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("submitButtonInTrainingAttendanceDialog")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK", sno, false);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordForUser(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("trainingAttendaceJtable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"trainingAttendaceJtable\"]/div/div[4]/div[2]/span"))
//					.getText();// For
//			// Ex:
//			// Showing
//			// 1-1
//			// of
//			// 1
//			String[] parts = a.split(" of ");
//			System.out.println("hi");
//			try {
//				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//				System.out.println("total record" + totalNoOfRecords);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"attendanceDetailsJtable\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// Doc No
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"attendanceDetailsJtable\"]/div/table/tbody/tr/td[5]"))
						.getText();// Doc
				// No
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentNumber = driver
								.findElement(By.xpath(
										"//*[@id=\"attendanceDetailsJtable\"]/div/table/tbody/tr[" + i + "]/td[5]"))
								.getText();// Doc No
						if (name1.contains(documentNumber)) {
							driver.findElement(By.xpath(
									"//*[@id=\"attendanceDetailsJtable\"]/div/table/tbody/tr[" + i + "]/td[1]/input"))
									.click();
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String documentNumber = driver
							.findElement(By.xpath("//*[@id=\"attendanceDetailsJtable\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (name1.contains(documentNumber)) {
						driver.findElement(
								By.xpath("//*[@id=\"attendanceDetailsJtable\"]/div/table/tbody/tr/td[1]/input"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#trainingAttendaceJtable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve
					// list
					Thread.sleep(4000);
					table = driver.findElement(By.id("trainingAttendaceJtable"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
	}

	private boolean selectRecord(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("trainingAttendaceJtable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"trainingAttendaceJtable\"]/div/div[4]/div[2]/span"))
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
				name = driver.findElement(By.xpath("//*[@id=\"trainingAttendaceJtable\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// Doc No
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"trainingAttendaceJtable\"]/div/table/tbody/tr/td[5]"))
						.getText();// Doc
				// No
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentNumber = driver
								.findElement(By.xpath(
										"//*[@id=\"trainingAttendaceJtable\"]/div/table/tbody/tr[" + i + "]/td[5]"))
								.getText();// Doc No
						if (name.contains(documentNumber)) {
							driver.findElement(By.xpath(
									"//*[@id=\"trainingAttendaceJtable\"]/div/table/tbody/tr[" + i + "]/td[14]/button"))
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
							.findElement(By.xpath("//*[@id=\"trainingAttendaceJtable\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (name.contains(documentNumber)) {
						driver.findElement(
								By.xpath("//*[@id=\"trainingAttendaceJtable\"]/div/table/tbody/tr/td[14]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#trainingAttendaceJtable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve
					// list
					Thread.sleep(4000);
					table = driver.findElement(By.id("trainingAttendaceJtable"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
