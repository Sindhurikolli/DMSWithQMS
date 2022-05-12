package com.pss.dms.Initiation.DmsDocSOPtPositiveFlow;

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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewDocumentSOPInitiate extends QMSLoginDetails {

	@Test
	public void toDoDoctTrngApprovalSOP() throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "NewDocumentSOPInitiate"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("NewDocumentSOPInitiate", "PSS-DMS-005", "Pass");
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
//        driver.findElement(By.id("myActivitiesInDMS")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        Thread.sleep(4000);
//        sno++;
//        driver.findElement(By.id("newDocMenuListListId")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New Document menu", sno,false);
//        Thread.sleep(4000);
//        sno++;
			driver.findElement(By.cssSelector("a[href='dmsNewDocInitiatePage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate sub menu", sno,
					false);
			Thread.sleep(10000);
			methodToSelectRecordApproveTemplateSOP();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void methodToSelectRecordApproveTemplateSOP() throws Exception {
		boolean isRecordSelectedTrngApprovalSOP = false;
		int count = 0;
		String doctNameTrngApprovalSOP = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		Thread.sleep(1000);
		sno++;
		isRecordSelectedTrngApprovalSOP = selectRecordToDOTrngApproval(doctNameTrngApprovalSOP,
				isRecordSelectedTrngApprovalSOP, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		sno++;
		if (isRecordSelectedTrngApprovalSOP) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.linkText("Next"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);

			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("documentNoOfDocumentUpload"))
					.sendKeys(properties.getProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document number", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("versionNoOfDocumentUpload"))
					.sendKeys(properties.getProperty("VERSION_NO_INITIATE_NEW_DOCT"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the version number", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("documentBrowseFileBtn"))
					.sendKeys(properties.getProperty("UPLOAD_DOCT_TEMPLATE_SOP"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"newFormatsJtableInNewDocIntiate\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new formats", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("createFormatName1")).sendKeys(properties.getProperty("CREATE_FORMATNAME"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the format name", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("createFormatNumber1")).sendKeys(properties.getProperty("CREATE_FORMAT_NUMBER"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the format number", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("createFormatVersion1")).sendKeys(properties.getProperty("CREATE_FORMAT_VERSION"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the version number", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("createFormatFile1")).sendKeys(properties.getProperty("UPLOAD_DOCT_TEMPLATE_SOP"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"newAnnxrJtableInNewDocIntiate\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Annexures", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("newDocInitCreAnnexureName1")).sendKeys(properties.getProperty("ANNEXURE_NAME"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Annexure name", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("newDocInitCreAnnexureNumber1"))
					.sendKeys(properties.getProperty("ANNEXURE_NUMBER"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter theAnnexure number", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("createAnnexureVersion1")).sendKeys(properties.getProperty("ANNEXURE_VERSION"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Annexure version", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("newDocInitCreAnnexureFile1"))
					.sendKeys(properties.getProperty("UPLOAD_DOCT_TEMPLATE_SOP"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.linkText("Finish")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(1000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			sno++;
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
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordToDOTrngApproval(String doctNameTrngApprovalSOP,
			boolean isRecordSelectedTrngApprovalSOP, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("newDocIntiateTable"));
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
						.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/table/tbody/tr[1]/td[2]")).getText();// documentType
			} else if ((doctNameTrngApprovalSOP == null) || ("".equalsIgnoreCase(doctNameTrngApprovalSOP))) {
				doctNameTrngApprovalSOP = driver
						.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/table/tbody/tr/td[2]")).getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(By.xpath(
										"//*[@id=\"newDocIntiateTable\"]/div/div[4]/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
						if (doctNameTrngApprovalSOP.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"newDocIntiateTable\"]/div/div[4]/table/tbody/tr[ " + i + " ]/td[4]"))
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
							.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/div[4]/table/tbody/tr/td[4]"))
							.getText();
					if (doctNameTrngApprovalSOP.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"newDocIntiateTable\"]/div/div[4]/table/tbody/tr/td[4]"))
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
					table = driver.findElement(By.id("newDocIntiateTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedTrngApprovalSOP;
	}
}
