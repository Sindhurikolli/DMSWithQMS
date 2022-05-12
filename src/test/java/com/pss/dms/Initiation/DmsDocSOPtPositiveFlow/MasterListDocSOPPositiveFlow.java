
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

public class MasterListDocSOPPositiveFlow extends QMSLoginDetails {

	@Test
	public void toDoMasterListDocSOPPositiveFlow()
			throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "MasterListDocSOPPositiveFlow"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("MasterListDocSOPPositiveFlow", "PSS-DMS-012",
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
			driver.findElement(By.id("masterListTabIdInDMS")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Master list Tab", sno, false);
			Thread.sleep(20000);
			methodToViewMasterListDoctSOPPositiveFlow();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void methodToViewMasterListDoctSOPPositiveFlow() throws Exception {
		sno++;
		driver.findElement(By.id("tree_2_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
		Thread.sleep(10000);
		boolean isRecordSelected = false;
		int count = 0;
		String doctName = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST1");
		String applicationWindow = driver.getWindowHandle();
		Thread.sleep(1000);
		sno++;
		isRecordSelected = selectRecordToViewMasterListDoctSOP(doctName, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		if (isRecordSelected) {
			Set<String> pdfWindow = driver.getWindowHandles();
			for (String pdfs : pdfWindow) {
				if (!applicationWindow.equalsIgnoreCase(pdfs)) {
					driver.switchTo().window(pdfs);
					Thread.sleep(4000);
					driver.close();
					// driver.findElement(By.id("CloseBtnDocRejAnnotsViewWin")).click();
					Thread.sleep(1000);
					driver.switchTo().window(applicationWindow);
					Thread.sleep(4000);
					sno++;
					driver.findElement(By.id("CloseBtnDocRejAnnotsViewWin")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Close button", sno,
							false);
					Thread.sleep(4000);
					sno++;
					driver.findElement(By.className("username")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,
							false);
					Thread.sleep(5000);
					sno++;
					driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[13]/ul/li[3]/a")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
				}
			}
		}
	}

	private boolean selectRecordToViewMasterListDoctSOP(String doctName, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("documentListForMasterListContainer"));
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
			if ((totalNoOfRecords > 1) && ((doctName == null) || ("".equalsIgnoreCase(doctName)))) {
				doctName = driver
						.findElement(By.xpath(
								"//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((doctName == null) || ("".equalsIgnoreCase(doctName))) {
				doctName = driver
						.findElement(By.xpath(
								"//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr/td[4]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver.findElement(
								By.xpath("//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr["
										+ i + "]/td[4]"))
								.getText();// documentTypeName
						if (doctName.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By
									.xpath("//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr["
											+ i + "]/td[12]/button"))
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
							.findElement(By.xpath(
									"//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr/td[4]"))
							.getText();
					if (doctName.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(By.xpath(
								"//*[@id=\"documentListForMasterListContainer\"]/div/div[4]/table/tbody/tr/td[12]/button"))
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
					table = driver.findElement(By.id("documentListForMasterListContainer"));// Document Tree approve
																							// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelected;
	}

}
