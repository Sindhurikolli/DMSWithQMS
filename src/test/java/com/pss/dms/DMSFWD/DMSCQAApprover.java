package com.pss.dms.DMSFWD;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;
import com.pss.qms.ExtentTestNGPkg.Utility;

////@Listeners(com.pss.dms.Listerners.TestListener.class)
public class DMSCQAApprover extends QMSLoginDetails {
	@Test
	public void toDMSCQAApprover() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DMSCQAApprover" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("DMSCQAApprover", "PSS-DMS-006", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("DMSURL"));
		Thread.sleep(4000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("DMSCQAApprover"));
		Thread.sleep(2000);
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
		Thread.sleep(5000);
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 240);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='dmsNewDocReviewPage.do']")));
		driver.findElement(By.cssSelector("a[href='dmsNewDocReviewPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review menu", sno, false);
		Thread.sleep(4000);
		/*
		 * if(driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
		 * System.out.println("Notification Window Displayed"); Actions action = new
		 * Actions(driver); action.sendKeys(Keys.ESCAPE).perform(); }
		 */
		// Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#dmsNewDocReviewTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		todoDMSCQAApprover();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }

	private void todoDMSCQAApprover() throws Exception {
		Thread.sleep(4000);
		sno++;
		int count = 0;
		String documentDeptSOPReview = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		boolean isRecordSelectedForDeptReview = false;
		isRecordSelectedForDeptReview = selectRecordDeptReviewStage(documentDeptSOPReview,
				isRecordSelectedForDeptReview, count);
		Thread.sleep(1000);
		if (isRecordSelectedForDeptReview) {
			String applicationWindow = driver.getWindowHandle();
			System.out.println("applicationWindow: " + applicationWindow);
			driver.findElement(By.id("continueBtnInNewDocRevForm")).click();
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
			Set<String> pdfWindow = driver.getWindowHandles();
			for (String pdfWindows : pdfWindow) {
				System.out.println("pdfWindows: " + pdfWindows);
				if (!applicationWindow.equalsIgnoreCase(pdfWindows)) {
					driver.switchTo().window(pdfWindows);
					Thread.sleep(1000);
					sno++;
					driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
					boolean exists = driver.findElements(By.id("approveBtnDocRevAnnotsWin")).size() != 0;
					System.out.println("Size: " + driver.findElements(By.id("approveBtnDocRevAnnotsWin")).size());
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					System.out.println("exists: " + exists);
					if (exists) {
						System.out.println("Coming to if condition");
						Thread.sleep(5000);
						WebDriverWait wait1 = new WebDriverWait(driver, 70);
						wait1.until(ExpectedConditions.elementToBeClickable(By.id("approveBtnDocRevAnnotsWin")));
						sno++;
						JavascriptExecutor js = (JavascriptExecutor) driver;
						WebElement approveButton = driver.findElement(By.id("approveBtnDocRevAnnotsWin"));
						js.executeScript("arguments[0].scrollIntoView();", approveButton);
						Thread.sleep(2000);
						driver.findElement(By.id("approveBtnDocRevAnnotsWin")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approve button",
								sno, false);
						Thread.sleep(3000);
						sno++;
						driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
								"Enter the E-Signature password", sno, false);
						Thread.sleep(1000);
						sno++;
						driver.findElement(By.id("subBtnInValidateESign")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button",
								sno, false);
						Thread.sleep(1000);
						sno++;
						WebDriverWait wait = new WebDriverWait(driver, 70);
						wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modal-window")));
						Thread.sleep(1000);
						driver.findElement(By.id("modal-window")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,
								false);
						Thread.sleep(1000);
						sno++;
//                		driver.findElement( By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[14]/a/span")).click();
//                		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
//                		Thread.sleep(5000);
//                		sno++;
//                		driver.findElement(By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[14]/ul/li[3]/a")).click();
//                		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
						break;

					} else {
						System.out.println("Coming to else condition");
						Thread.sleep(1000);
						driver.close();
						Thread.sleep(2000);
						driver.switchTo().window(applicationWindow);
						Thread.sleep(2000);
						WebDriverWait wait2 = new WebDriverWait(driver, 70);
						wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("continueBtnInNewDocRevForm")));
						Thread.sleep(1000);
						sno++;
						driver.findElement(By.id("continueBtnInNewDocRevForm")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on continue button",
								sno, false);
						Thread.sleep(9000);
						Set<String> pdf = driver.getWindowHandles();
						for (String pdfs : pdf) {
							System.out.println("pdfs: " + pdfs);
							if (!applicationWindow.equalsIgnoreCase(pdfs)) {
								Thread.sleep(1000);
								driver.switchTo().window(pdfs);
								WebDriverWait wait1 = new WebDriverWait(driver, 200);
								wait1.until(ExpectedConditions
										.presenceOfElementLocated(By.id("approveBtnDocRevAnnotsWin")));
								Thread.sleep(1000);
								sno++;
								driver.findElement(By.id("approveBtnDocRevAnnotsWin")).click();
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
										"Click on Approve button", sno, false);
								Thread.sleep(1000);
								sno++;
								driver.findElement(By.id("eSignPwdInWnd"))
										.sendKeys(properties.getProperty("E_SignPassword"));
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
										"Enter the E-Signature", sno, false);
								Thread.sleep(1000);
								sno++;
								driver.findElement(By.id("subBtnInValidateESign")).click();
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
										"Click on Submit button", sno, false);
								Thread.sleep(1000);
								sno++;
								WebDriverWait wait = new WebDriverWait(driver, 70);
								wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
								Thread.sleep(1000);
								driver.findElement(By.className("modal-btn")).click();
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button",
										sno, false);
								Thread.sleep(4000);
								sno++;
//                        		driver.findElement(By.className("username")).click();
//                        		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
//                        		Thread.sleep(5000);
//                        		sno++;
//                        		driver.findElement(By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[14]/ul/li[3]/a")).click();
//                        		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
								break;
							}
						}
						break;
					}
				}
			}
			Thread.sleep(1000);
			driver.switchTo().window(applicationWindow);
			Thread.sleep(1000);
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}

	}

	private boolean selectRecordDeptReviewStage(String documentDeptSOPReview, boolean isRecordSelectedForDeptReview,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsNewDocReviewTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {// *[@id="dmsNewDocReviewTable"]/div/table/tbody/tr[2]/td[1]
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
					&& ((documentDeptSOPReview == null) || ("".equalsIgnoreCase(documentDeptSOPReview)))) {
				documentDeptSOPReview = driver
						.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// documentType
			} else if ((documentDeptSOPReview == null) || ("".equalsIgnoreCase(documentDeptSOPReview))) {
				documentDeptSOPReview = driver
						.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr/td[1]")).getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By
										.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[1]"))
								.getText();// documentTypeName
						if (documentDeptSOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[1]"))
									.click();
							isRecordSelectedForDeptReview = true;
							break;
						}
					}
					if (isRecordSelectedForDeptReview) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr/td[1]"))
							.getText();
					if (documentDeptSOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"dmsNewDocReviewTable\"]/div/table/tbody/tr/td[1]"))
								.click();
						isRecordSelectedForDeptReview = true;
						break;
						// *[@id="dmsNewDocReviewTable"]/div/table/tbody/tr/td[1]
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForDeptReview) && (noOfRecordsChecked < totalNoOfRecords)) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					WebElement nextPage = driver.findElement(By.className("jtable-page-number-next"));
					js.executeScript("arguments[0].scrollIntoView()", nextPage);
					Thread.sleep(2000);
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsNewDocReviewTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedForDeptReview;
	}

	@AfterMethod
	public void testIT(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}

}
