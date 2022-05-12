package com.pss.dms.LMSRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.schedule.Helper;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

//@Listeners(com.pss.dms.Listerners.TestListener.class)
public class AdhocReSchedule extends QMSLoginDetails {

	@Test
	public void toAdhocSchedule() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateAdhocSchedule"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Adhoc Schedule", "PSS-LMS-001", "Pass");
		writer.setPageEvent(event);
		document.open();

		Thread.sleep(1000);
		driver.get(properties.getProperty("LMSURL"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("LMSTrainingCoordinator"));
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
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.id("adhocSchedule")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("adhocSchedule"));
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
//		driver.findElement(By.id("adhocSchedule")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Adhoc Schedule", sno, false);
		Thread.sleep(4000);
		driver.findElement(By.id("rejectAdhoc")).click();
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#SchedulePlansRejFormTable1 > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(2000);
		methodToCreateAdhocSchedule();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodToCreateAdhocSchedule() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("ADHOC_SCHEDULE_NAME");
		isRecordSelected = SelectRecordForSchedule(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,1000)");
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Next", sno, false);
			Thread.sleep(3000);
			jse.executeScript("window.scrollBy(0,1000)");
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Next", sno, false);
			Thread.sleep(3000);

			driver.findElement(By.xpath("//*[@id=\"userInAdhocScheduleFormTable\"]/div/div[3]/div[2]/span/span"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add User", sno, false);
			WebDriverWait wait1 = new WebDriverWait(driver, 240);
			wait1.until(ExpectedConditions.elementToBeClickable(By.id("mulitUserSelTree_2_span")));
			sno++;
			driver.findElement(By.id("mulitUserSelTree_2_span")).click();// Selecting Organization node
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			Thread.sleep(7000);
			WebDriverWait wait = new WebDriverWait(driver, 240);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#multiUserTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			sno++;
			driver.findElement(By.id("empCodeInSC")).click();
			driver.findElement(By.id("empCodeInSC")).sendKeys(properties.getProperty("User_ECode"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter User_ECode", sno, false);
			Thread.sleep(3000);
			
			sno++;
			driver.findElement(By.id("usersSearchBtnId")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			Thread.sleep(5000);
			
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#multiUserTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			
			int count1 = 0;
			boolean isRecordSelectedForSelectingMultiUsers = false;
			String multiUsersInSchedules = properties.getProperty("User_ECode");
			isRecordSelectedForSelectingMultiUsers = Helper.selectingMultiUsersRecordForSchedules(driver,
					multiUsersInSchedules, isRecordSelectedForSelectingMultiUsers, count1);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selBtnInMultiUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			jse.executeScript("window.scrollBy(0,1000)");
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Next", sno, false);
			Thread.sleep(3000);

			jse.executeScript("window.scrollBy(0,1000)");
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Next", sno, false);
			Thread.sleep(3000);
			driver.findElement(By.id("startDateInAdhocSchedule")).click();
			Thread.sleep(1000);
			driver.findElement(By.className("ui-datepicker-today")).click();
			Thread.sleep(3000);
			jse.executeScript("window.scrollBy(0,1000)");
			driver.findElement(By.linkText("Next")).click();
			Thread.sleep(5000);
			driver.findElement(
					By.xpath("//*[@id=\"stageWiseUserDetailInAdhocSch\"]/div/table/thead/tr/th[1]/div/input")).click();
			Thread.sleep(3000);
			jse.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(1000);
			driver.findElement(By.linkText("Finish")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[49]/div[3]/div/button[1]/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes", sno, false);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			
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
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean SelectRecordForSchedule(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("SchedulePlansRejFormTable1"));// schedule plan approve table
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		// int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"SchedulePlansRejFormTable1\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//			String[] parts = a.split(" of ");
//			totalNoOfRecords = Integer.parseInt(parts[1].trim());
//		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver
						.findElement(By.xpath("//*[@id=\"SchedulePlansRejFormTable1\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// schedule plan name
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"SchedulePlansRejFormTable1\"]/div/table/tbody/tr/td[2]"))
						.getText();// schedule plan name
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			// while (noOfRecordsChecked < totalNoOfRecords) {
			if (totalNoOfRecords > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String schPlanName = driver
							.findElement(By.xpath(
									"//*[@id=\"SchedulePlansRejFormTable1\"]/div/table/tbody/tr[" + i + "]/td[2]"))
							.getText();// schedule plan name
					if (name.contains(schPlanName)) {
						driver.findElement(By
								.xpath("//*[@id=\"SchedulePlansRejFormTable1\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
//					if (isRecordSelected) {
//						break;
//					}
			} else {
				String schPlanName = driver
						.findElement(By.xpath("//*[@id=\"SchedulePlansRejFormTable1\"]/div/table/tbody/tr/td[2]"))
						.getText();
				if (name.contains(schPlanName)) {
					driver.findElement(By.xpath("//*[@id=\"SchedulePlansRejFormTable1\"]/div/table/tbody/tr/td[2]"))
							.click();
					isRecordSelected = true;
					// break;
				}
			}
			// noOfRecordsChecked += perPageNoOfRecordsPresent;
//				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//					driver.findElement(By.className("jtable-page-number-next")).click();// next page in schedule Plan
//																						// approve list
//					Thread.sleep(3000);
//					table = driver.findElement(By.id("SchedulePlansRejFormTable1"));// schedule Plan approve table
//					tableBody = table.findElement(By.tagName("tbody"));
//					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//				}

			// }
		}
		return isRecordSelected;
	}

}
