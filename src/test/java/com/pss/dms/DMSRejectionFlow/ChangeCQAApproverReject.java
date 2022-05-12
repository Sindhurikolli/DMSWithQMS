package com.pss.dms.DMSRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;
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
public class ChangeCQAApproverReject extends QMSLoginDetails {

	@Test
	public void toChangeCQAApproverReject() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ChangeCQAApproverReject"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("ChangeCQAApproverReject", "PSS-DMS-023", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("DMSURL"));
		Thread.sleep(4000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("DMSCQAApprover"));

		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		JavascriptExecutor jse5 = (JavascriptExecutor) driver;
		WebElement element5 = driver.findElement(By.cssSelector("button.button.submit"));
		jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
		Thread.sleep(2000);
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
		sno++;
		WebDriverWait wait1 = new WebDriverWait(driver, 60);

		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='changeDocReviewPage.do']")));

		driver.findElement(By.cssSelector("a[href='changeDocReviewPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review menu", sno, false);
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#changeDocReviewTable > div > div.jtable-busy-message[style='display: none;']")));
		todoChangeCQAApproverReject();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}

//    }

	private void todoChangeCQAApproverReject() throws Exception {
		int count = 0;
		String documentDeptSOPReview = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		boolean isRecordSelectedForDeptReview = false;
		isRecordSelectedForDeptReview = selectRecordDeptReviewStageSOPRejectFlow(documentDeptSOPReview,
				isRecordSelectedForDeptReview, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(10000);
		if (isRecordSelectedForDeptReview) {
			String applicationWindow = driver.getWindowHandle();
			System.out.println("applicationWindow: " + applicationWindow);
			driver.findElement(By.id("continueBtnInChngDocRevForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Continue button", sno, false);
			Thread.sleep(14000);
			Set<String> pdfWindow = driver.getWindowHandles();
			for (String pdfWindows : pdfWindow) {
				System.out.println("pdfWindows: " + pdfWindows);
				if (!applicationWindow.equalsIgnoreCase(pdfWindows)) {
					driver.switchTo().window(pdfWindows);
					Thread.sleep(10000);
					driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
					boolean exists = driver.findElements(By.id("approveBtnDocRevAnnotsWin")).size() != 0;
					System.out.println("Size: " + driver.findElements(By.id("approveBtnDocRevAnnotsWin")).size());
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approve button", sno,
							false);
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					System.out.println("exists: " + exists);
					if (exists) {
						System.out.println("Coming to if condition");
						Thread.sleep(1000);
						driver.findElement(By
								.cssSelector("#annotationsToolbar > div.flowpaper_bttnComment.flowpaper_tblabelbutton"))
								.click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create note", sno,
								false);
						Thread.sleep(1000);
						driver.findElement(
								By.cssSelector("#toolbar_documentViewer_annotations_flowpaper_notetypeselector_point"))
								.click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Point", sno,
								false);
						Thread.sleep(1000);
						driver.findElement(By.cssSelector("#page_0_documentViewer_canvasOverlay")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on document page",
								sno, false);
						Thread.sleep(3000);
						if (driver.findElement(By.className("flowpaper_note_container")).isDisplayed()) {
							driver.findElement(By.className("flowpaper_note_textarea"))
									.sendKeys(properties.getProperty("COMMENTSRevert").toString());
							document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments",
									sno, false);
							Thread.sleep(1000);
						}
						// Selecting StageWise DropDown
						Select stagesDownList = Helper.getSelectInstance(driver, By.id("stagesOfLCToMove"));
						List<WebElement> webElementList = stagesDownList.getOptions();
						if (webElementList.size() > 0) {
//                            stagesDownList.selectByIndex(Integer.parseInt("4"));//Initiator
							stagesDownList.selectByVisibleText("Initiator");
							document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
									"Select the user from drop down list", sno, false);
						} else {
							System.out.println("No options in Select(Roles - drop down list)");
						}
						Thread.sleep(1000);
						driver.findElement(By.id("rejectBtnDocRevAnnotsWin")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on reject button",
								sno, false);
						Thread.sleep(1000);
						driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
								"Click on E-Signature password", sno, false);
						Thread.sleep(1000);
						driver.findElement(By.id("subBtnInValidateESign")).click();
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button",
								sno, false);
						Thread.sleep(1000);
						WebDriverWait wait = new WebDriverWait(driver, 70);
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
						Thread.sleep(1000);
						document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,
								false);
						sno++;
						driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();

//                        Thread.sleep(4000);
//                                             
//                   		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[14]/a/span")).click();
//                   		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
//                   		Thread.sleep(5000);
//                   		sno++;
//                   		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[14]/ul/li[3]/a")).click();
//                   		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
						break;
					} else {
						System.out.println("Coming to else condition");
						Thread.sleep(1000);
						driver.close();
						Thread.sleep(2000);
						driver.switchTo().window(applicationWindow);
						Thread.sleep(2000);
						WebDriverWait wait2 = new WebDriverWait(driver, 70);
						wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("continueBtnInChngDocRevForm")));
						Thread.sleep(1000);
						driver.findElement(By.id("continueBtnInChngDocRevForm")).click();
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
								driver.findElement(By.id("approveBtnDocRevAnnotsWin")).click();
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
										"Click on approve button", sno, false);
								Thread.sleep(1000);
								driver.findElement(By.id("eSignPwdInWnd"))
										.sendKeys(properties.getProperty("E_SignPassword"));
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
										"Enter the E-Signature password", sno, false);
								Thread.sleep(1000);
								driver.findElement(By.id("subBtnInValidateESign")).click();
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
										"Click on submit button", sno, false);
								Thread.sleep(1000);
								WebDriverWait wait = new WebDriverWait(driver, 70);
								wait.until(ExpectedConditions.presenceOfElementLocated(
										By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
								Thread.sleep(1000);
								document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button",
										sno, false);
								sno++;
								driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();

//                                Thread.sleep(4000);
//                                                     
//                           		driver.findElement(By.className("username")).click();
//                           		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
//                           		Thread.sleep(5000);
//                           		sno++;
//                           		WebElement logout = driver.findElement(By.cssSelector("a[href='Logout.do']"));
//		JavascriptExecutor jslogout = (JavascriptExecutor)driver;
//		jslogout.executeScript("arguments[0].click();", logout);
//                           		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
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

	private boolean selectRecordDeptReviewStageSOPRejectFlow(String documentDeptSOPReview,
			boolean isRecordSelectedForDeptReview, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeDocReviewTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {// *[@id="changeDocReviewTable"]/div/table/tbody/tr[2]/td[1]
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
						.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentDeptSOPReview == null) || ("".equalsIgnoreCase(documentDeptSOPReview))) {
				documentDeptSOPReview = driver
						.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr/td[2]")).getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By
										.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// documentTypeName
						if (documentDeptSOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr[" + i + "]/td[2]"))
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
							.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (documentDeptSOPReview.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"changeDocReviewTable\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelectedForDeptReview = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForDeptReview) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("changeDocReviewTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForDeptReview;
	}

}
