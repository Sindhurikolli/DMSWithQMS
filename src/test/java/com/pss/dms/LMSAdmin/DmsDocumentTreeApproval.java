/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.LMSAdmin;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

/**
 *
 * @author Jeevan
 */
public class DmsDocumentTreeApproval extends QMSLoginDetails {

	@Test
	public void toDoApprovalDmsDocumentTree() throws Exception {
		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DmsDocumentTreeApproval"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("DmsDocumentTreeApproval", "PSS-DMS-004", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("DMSURL"));
		Thread.sleep(4000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("DMSQAHod"));

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
		Thread.sleep(20000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		sno++;
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,2000)");
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ApproveDocumentTreeForm.do']")));
		driver.findElement(By.cssSelector("a[href='ApproveDocumentTreeForm.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approval menu", sno, false);
		Thread.sleep(4000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#approvalDocumentTreeTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(1000);
		methodToSelectRecordForApprovalDoctTree();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodToSelectRecordForApprovalDoctTree() throws Exception {
		boolean isRecordSelectedForApprovalDocTree = false;
		int count = 0;
		String documentTreeName = properties.getProperty("FOLDER_NAME_CREATE_DOCUMENT_TREE");
		isRecordSelectedForApprovalDocTree = selectRecordForApprovalDoctTree(documentTreeName,
				isRecordSelectedForApprovalDocTree, count);
		Thread.sleep(1000);
		if (isRecordSelectedForApprovalDocTree) {
			driver.findElement(By.id("commentsInDocTreeAppr"))
					.sendKeys(properties.getProperty("COMMENTS_DOCUMENT_TREE_APPROVAL"));
			Thread.sleep(1000);
			driver.findElement(By.id("approveInApprovalDocType")).click();
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
			Thread.sleep(2000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(1000);
			sno++;
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(1000);
		} else {
			System.out.println("Record is not Selected");
		}
		sno++;
		driver.findElement(By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
	}

	private boolean selectRecordForApprovalDoctTree(String documentTreeName, boolean isRecordSelectedForApprovalDocTree,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("approvalDocumentTreeTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"approvalDocumentTreeTable\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((documentTreeName == null) || ("".equalsIgnoreCase(documentTreeName)))) {
				documentTreeName = driver
						.findElement(By.xpath("//*[@id=\"approvalDocumentTreeTable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentTreeName
			} else if ((documentTreeName == null) || ("".equalsIgnoreCase(documentTreeName))) {
				documentTreeName = driver
						.findElement(By.xpath("//*[@id=\"approvalDocumentTreeTable\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentTreeName
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentTreeNameApproval = driver
								.findElement(By.xpath(
										"//*[@id=\"approvalDocumentTreeTable\"]/div/table/tbody/tr[" + i + "]/td[3]"))
								.getText();// documentTreeName
						if (documentTreeName.equalsIgnoreCase(documentTreeNameApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"approvalDocumentTreeTable\"]/div/table/tbody/tr[" + i + "]/td[3]"))
									.click();
							isRecordSelectedForApprovalDocTree = true;
							break;
						}
					}
					if (isRecordSelectedForApprovalDocTree) {
						break;
					}
				} else {
					String documentTreeNameApproval = driver
							.findElement(By.xpath("//*[@id=\"approvalDocumentTreeTable\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (documentTreeName.equalsIgnoreCase(documentTreeNameApproval)) {
						driver.findElement(By.xpath("//*[@id=\"approvalDocumentTreeTable\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForApprovalDocTree = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForApprovalDocTree) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("approvalDocumentTreeTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForApprovalDocTree;
	}

}
