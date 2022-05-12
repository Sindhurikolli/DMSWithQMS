/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jeevan
 */
public class DocumentViewSOPPositiveFlow extends QMSLoginDetails {

	public DocumentViewSOPPositiveFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoDocumentViewSOPPositiveFlow()
			throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DocumentViewSOPPositiveFlow"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("DocumentViewSOPPositiveFlow", "PSS-DMS-013",
					"Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(4000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME3"));

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
			Thread.sleep(8000);
			driver.findElement(By.id("myActivitiesInDMS")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MY Activities Tab", sno,
					false);
			sno++;
			Thread.sleep(8000);
			driver.findElement(By.id("dmsDocViewId")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Document view menu", sno,
					false);
			Thread.sleep(20000);
			methodToDoSearchViewSOPDoctPositiveFlow();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void methodToDoSearchViewSOPDoctPositiveFlow() throws Exception {
		sno++;
		driver.findElement(By.id("dmsDocViewListSearchBtnId")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on search button", sno, false);
		Thread.sleep(1000);
		sno++;
		String doctNameView = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		boolean isRecordSelected = false;
		int count = 0;
		String applicationWindow = driver.getWindowHandle();
		Thread.sleep(10000);
		isRecordSelected = selectDoctRecdViewSOPDoctPositiveFlow(doctNameView, isRecordSelected, count);
		Thread.sleep(20000);
		if (isRecordSelected) {
			Set<String> pdfWindow = driver.getWindowHandles();
			for (String pdfs : pdfWindow) {
				if (!applicationWindow.equalsIgnoreCase(pdfs)) {
					driver.switchTo().window(pdfs);
					Thread.sleep(4000);
					driver.close();
					Thread.sleep(1000);
					driver.switchTo().window(applicationWindow);
					Thread.sleep(1000);
					sno++;
					driver.findElement(By.className("username")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,
							false);
					Thread.sleep(5000);
					sno++;
					WebElement logout = driver.findElement(By.cssSelector("a[href='Logout.do']"));
					JavascriptExecutor jslogout = (JavascriptExecutor) driver;
					jslogout.executeScript("arguments[0].click();", logout);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
					break;
				}
			}
		}
	}

	private boolean selectDoctRecdViewSOPDoctPositiveFlow(String doctNameView, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsDocViewTable"));
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
			if ((totalNoOfRecords > 1) && ((doctNameView == null) || ("".equalsIgnoreCase(doctNameView)))) {
				doctNameView = driver.findElement(By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((doctNameView == null) || ("".equalsIgnoreCase(doctNameView))) {
				doctNameView = driver.findElement(By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr/td[5]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver
								.findElement(
										By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr[" + i + "]/td[5]"))
								.getText();// documentTypeName
						if (doctNameView.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(
									By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr[" + i + "]/td[10]/img"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr/td[5]")).getText();
					if (doctNameView.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath("//*[@id=\"dmsDocViewTable\"]/div/table/tbody/tr/td[10]/img"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsDocViewTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
