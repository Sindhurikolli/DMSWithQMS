package com.pss.dms.DMSRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
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
import com.pss.qms.ExtentTestNGPkg.Utility;

////@Listeners(com.pss.dms.Listerners.TestListener.class)
public class TrainingCompletedList extends QMSLoginDetails {

	@Test
	public void toTrainingCompletedList() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "TrngCompletedListSOP"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("TrngCompletedListSOP", "PSS-DMS-012", "Pass");
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
//			sno++;
//			Thread.sleep(20000);
//        driver.findElement(By.cssSelector("a[href='dmsNewDocRequestPage.do']")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);

		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='dmsTrngCmpltdListPage.do']")));
//
//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollBy(0,250)");
//			WebElement element = driver.findElement(By.xpath("//*[@id=\"dmsDocumentTraining\"]/div/div[1]/strong"));
//			 jse.executeScript("arguments[0].scrollIntoView(true);", element);

		sno++;
		driver.findElement(By.cssSelector("a[href='dmsTrngCmpltdListPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Training completed list menu", sno,
				false);
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#dmsTrainingCompletedListTable > div > div.jtable-busy-message[style='display: none;']")));

		todoTrainingCompletedList();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }

	private void todoTrainingCompletedList() throws Exception {
		sno++;
		boolean isRecordSelectedTrngCompletedList = false;
		int count = 0;
		String documentNameTrngCompleted = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		isRecordSelectedTrngCompletedList = selectRecordTrngCompletedSOP(documentNameTrngCompleted,
				isRecordSelectedTrngCompletedList, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		sno++;
		if (isRecordSelectedTrngCompletedList) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.linkText("Next"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);

			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(1000);
			Date date = new Date();
			String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
			Thread.sleep(3000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Effective from and Select Date from Calender",
					sno, false);
			sno++;
			Thread.sleep(1000);
			driver.findElement(By.id("effectiveFromInTrainingCompltdForm")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("ui-datepicker-today")).click();
			Thread.sleep(2000);						
			driver.findElement(By.id("commentsInTrainingCompltdForm"))
					.sendKeys(properties.getProperty("COMMENTS_TRAINING_NOTIFICATION"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"deptIssGridInTrngCompleted\"]/div/div[3]/div[2]/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("selLocBtnInDeptIssuanceTable")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("treeContainer_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("selectBtnInLocSelect")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"addUSerGroupDetailsInTrngCmpltdListWin\"]/div[2]/button")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on click here button", sno,
					false);
			Thread.sleep(5000);
			sno++;
			boolean isRecordSelectedUG = false;
			int count1 = 0;
			String Usergroup = properties.getProperty("DMSUserGroupName");
			isRecordSelectedUG = selectRecordUG(Usergroup, isRecordSelectedUG, count1);
			if (isRecordSelectedUG) {
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the user Group", sno, false);
				Thread.sleep(1000);
				sno++;
				driver.findElement(By.xpath("/html/body/div[16]/div[3]/div/button[1]/span")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno,
						false);
				Thread.sleep(1000);
				sno++;
			} else {
				System.out.println("User Group Not Selected");
			}

			driver.findElement(By.id("totalNoOfCopiesInDeptPrintOfTrngCompleted"))
					.sendKeys(properties.getProperty("NUMBER_OF_COPIES_SOP"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the no.of copies", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.xpath("/html/body/div[13]/div[3]/div/button[1]/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(1000);
			Helper.scrollElement(driver, By.linkText("Finish"));
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.linkText("Finish")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signatue", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(1000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordTrngCompletedSOP(String documentNameTrngCompleted,
			boolean isRecordSelectedTrngCompletedList, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsTrainingCompletedListTable"));
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
					&& ((documentNameTrngCompleted == null) || ("".equalsIgnoreCase(documentNameTrngCompleted)))) {
				documentNameTrngCompleted = driver
						.findElement(By
								.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((documentNameTrngCompleted == null) || ("".equalsIgnoreCase(documentNameTrngCompleted))) {
				documentNameTrngCompleted = driver
						.findElement(
								By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr/td[3]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String doctNameInTrngCompleted = driver.findElement(
								By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (documentNameTrngCompleted.equalsIgnoreCase(doctNameInTrngCompleted)) {
							driver.findElement(
									By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr[ "
											+ i + " ]/td[3]"))
									.click();
							isRecordSelectedTrngCompletedList = true;
							break;
						}
					}
					if (isRecordSelectedTrngCompletedList) {
						break;
					}
				} else {
					String doctNameInTrngCompleted = driver
							.findElement(By.xpath(
									"//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr/td[3]"))
							.getText();
					if (documentNameTrngCompleted.equalsIgnoreCase(doctNameInTrngCompleted)) {
						driver.findElement(
								By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedTrngCompletedList = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedTrngCompletedList) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsTrainingCompletedListTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedTrngCompletedList;
	}

	private boolean selectRecordUG(String documentNameTrngCompleted, boolean isRecordSelectedUG, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("userGroupsTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
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
			if ((totalNoOfRecords > 1)
					&& ((documentNameTrngCompleted == null) || ("".equalsIgnoreCase(documentNameTrngCompleted)))) {
				documentNameTrngCompleted = driver
						.findElement(By.xpath("//*[@id=\"userGroupsTable\"]/div/div[4]/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentNameTrngCompleted == null) || ("".equalsIgnoreCase(documentNameTrngCompleted))) {
				documentNameTrngCompleted = driver
						.findElement(By.xpath("//*[@id=\"userGroupsTable\"]/div/div[4]/table/tbody/tr/td[2]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String doctNameInTrngCompleted = driver
								.findElement(By.xpath(
										"//*[@id=\"userGroupsTable\"]/div/div[4]/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (documentNameTrngCompleted.equalsIgnoreCase(doctNameInTrngCompleted)) {
							driver.findElement(By
									.xpath("//*[@id=\"userGroupsTable\"]/div/div[4]/table/tbody/tr[ " + i + " ]/td[2]"))
									.click();
							isRecordSelectedUG = true;
							break;
						}
					}
					if (isRecordSelectedUG) {
						break;
					}
				} else {
					String doctNameInTrngCompleted = driver
							.findElement(By.xpath("//*[@id=\"userGroupsTable\"]/div/div[4]/table/tbody/tr/td[2]"))
							.getText();
					if (documentNameTrngCompleted.equalsIgnoreCase(doctNameInTrngCompleted)) {
						driver.findElement(By.xpath("//*[@id=\"userGroupsTable\"]/div/div[4]/table/tbody/tr/td[2]"))
								.click();
						isRecordSelectedUG = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedUG) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("userGroupsTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedUG;
	}

	@AfterMethod
	public void testIT(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}

}
