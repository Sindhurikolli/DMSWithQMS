/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.DmsAdmin;

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
public class DmsDocumentTreeCreate extends QMSLoginDetails {

	@Test
	public void toDoDocumentTreeCreation() throws Exception {
		Thread.sleep(1000);
		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DmsDocumentTreeCreate"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("DmsDocumentTreeCreate", "PSS-DMS-012", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("DMSURL"));
		Thread.sleep(4000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));

		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("ADMIN_Password"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		JavascriptExecutor jse5 = (JavascriptExecutor) driver;
		WebElement element5 = driver.findElement(By.cssSelector("button.button.submit"));
		jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
		Thread.sleep(5000);
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
		Thread.sleep(5000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,2200)");

		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='DmsRolesRegForm.do']")));
		sno++;
		driver.findElement(By.cssSelector("a[href='DmsRolesRegForm.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin menu", sno, false);
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Document Tree", sno, false);
		sno++;
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create Button", sno, false);
		sno++;
		Thread.sleep(5000);
		methodToDoDocumentTreeCreationInDms();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
	}

	private void methodToDoDocumentTreeCreationInDms() throws Exception {
		boolean isRecordSelectedForUsers = false;
		String singleApprovalName = properties.getProperty("DMSTrainingApprover_E_CODE");
		int count = 0;
		driver.findElement(By.linkText(properties.getProperty("DocumentTreeLocation"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Document Tree Location", sno,
				false);
		sno++;
		Thread.sleep(5000);
		driver.findElement(By.id("folderNameInCreateDocTree"))
				.sendKeys(properties.getProperty("FOLDER_NAME_CREATE_DOCUMENT_TREE"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Folder Name", sno, false);
		sno++;
		Thread.sleep(1000);
		driver.findElement(By.id("folderShortNameInCreateDocTree"))
				.sendKeys(properties.getProperty("SHORT_NAME_CREATE_DOCUMENT_TREE"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Short Name", sno, false);
		sno++;
		Thread.sleep(1000);
		driver.findElement(By.id("selectApprovalInCreateDocTree")).click();
		Thread.sleep(1000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approval From Select Button", sno,
				false);
		sno++;
		driver.findElement(By.id("locationTreeForApproverSelect_1_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Location", sno, false);
		sno++;
		Thread.sleep(5000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#approversTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		isRecordSelectedForUsers = Helper.selectSingleApprovalRecord(driver, singleApprovalName,
				isRecordSelectedForUsers, count);
		Thread.sleep(1000);
		if (isRecordSelectedForUsers) {
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button", sno, false);
			sno++;
			Thread.sleep(1000);
			driver.findElement(By.id("descriptionInCreateDocTree"))
					.sendKeys(properties.getProperty("DESCRIPTION_CREATE_DOCUMENT_TREE"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description", sno, false);
			sno++;
			Thread.sleep(1000);
			driver.findElement(By.id("createInCreateDocTree")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create", sno, false);
			sno++;
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signatue", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(1000);
			sno++;
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(1000);

		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		}
		sno++;
		driver.findElement(By.className("username")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
	}

}
