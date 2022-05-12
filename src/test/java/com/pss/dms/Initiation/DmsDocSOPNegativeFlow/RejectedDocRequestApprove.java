package com.pss.dms.Initiation.DmsDocSOPNegativeFlow;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class RejectedDocRequestApprove extends QMSLoginDetails {

	public RejectedDocRequestApprove() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoRejectedDocRequestApprove() throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RejectedDocRequestApprove"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CreateDoctRequestSOPRejectFlow", "PSS-DMS-016",
					"Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(4000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME1"));

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
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(20000);
			driver.findElement(By.cssSelector("a[href='dmsNewDocRequestPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,
					false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("rejectedNewDocumentFormAction")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on Resubmit-Rejected radio button", sno, false);
			Thread.sleep(4000);
//        sno++;
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a"));
//         jse.executeScript("arguments[0].scrollIntoView(true);", element);
//        driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno,false);
//        Thread.sleep(10000);
			methodToRejectedDocRequestApprove();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void methodToRejectedDocRequestApprove() throws Exception {
		Thread.sleep(8000);
		boolean isRecordSelectedForApprovalReq = false;
		int count = 0;
		String documentNameReq = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		isRecordSelectedForApprovalReq = selectRecordDocReqSOPRejectFlow(documentNameReq,
				isRecordSelectedForApprovalReq, count);
		Thread.sleep(10000);
		if (isRecordSelectedForApprovalReq) {

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("commentsInRejNewDocRequestPage")).sendKeys(properties.getProperty("COMMENTS"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(4000);
			Thread.sleep(1000);
			sno++;
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
			driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[14]/a/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[14]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		}

	}

	private boolean selectRecordDocReqSOPRejectFlow(String documentNameReq, boolean isRecordSelectedForApprovalReq,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("newDocumentRejectTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
//	        int noOfRecordsChecked = 0;
//	        if (perPageNoOfRecordsPresent > 0) {
//	            String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
//	            String[] parts = a.split(" of ");
//	            try {
//	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//	            } catch (Exception e) {
//	                System.out.println(e.getMessage());
//	            }
//	        }
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((documentNameReq == null) || ("".equalsIgnoreCase(documentNameReq)))) {
				documentNameReq = driver
						.findElement(By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((documentNameReq == null) || ("".equalsIgnoreCase(documentNameReq))) {
				documentNameReq = driver
						.findElement(By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
//	            while (noOfRecordsChecked < totalNoOfRecords) {
			if (totalNoOfRecords > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String newDoctReqNameInApproval = driver
							.findElement(By
									.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
							.getText();// documentTypeName
					if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(
								By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.click();
						isRecordSelectedForApprovalReq = true;
						break;
					}
				}
//	                    if (isRecordSelectedForApprovalReq) {
//	                        break;
//	                    }
			} else {
				String newDoctReqNameInApproval = driver
						.findElement(By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr/td[2]"))
						.getText();
				if (documentNameReq.equalsIgnoreCase(newDoctReqNameInApproval)) {
					driver.findElement(By.xpath("//*[@id=\"newDocumentRejectTable\"]/div/table/tbody/tr/td[2]"))
							.click();
					isRecordSelectedForApprovalReq = true;
//	                        break;
				}
			}
//	                noOfRecordsChecked += perPageNoOfRecordsPresent;
//	                if ((!isRecordSelectedForApprovalReq) && (noOfRecordsChecked < totalNoOfRecords)) {
//	                    driver.findElement(By.className("jtable-page-number-next")).click();//next page in Document approve list
//	                    Thread.sleep(3000);
//	                    table = driver.findElement(By.id("newDocRequestApprovalTable"));//Document Tree approve table
//	                    tableBody = table.findElement(By.tagName("tbody"));
//	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//	                }
//	            }
		}

		return isRecordSelectedForApprovalReq;
	}

}
