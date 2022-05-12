/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.DMSRegistration;

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Random;

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
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jeevan
 */
public class CreateWorkFlowForDms extends QMSLoginDetails {

	@Test
	public void toDoCreateWorkFlowDms() throws Exception {
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
		Thread.sleep(5000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,2000)");

		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='dmsWorkFlowPage.do']")));
		sno++;
		driver.findElement(By.cssSelector("a[href='dmsWorkFlowPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin menu", sno, false);
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(5000);
		methodToDoWorkFlowDms();

	}

	private void methodToDoWorkFlowDms() throws InterruptedException {
		Select DocumentType = new Select(driver.findElement(By.id("docTypeInWorkFlow")));
		DocumentType.selectByVisibleText(properties.getProperty("WorkFlowDocType"));
		Thread.sleep(2000);
		driver.findElement(By.id("dmsWorkFlowSel")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText(properties.getProperty("WFLocation"))).click();
		Thread.sleep(2000);
		driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("nameInCreateWorkflow")).sendKeys(properties.getProperty("WorkFlowName"));
		Thread.sleep(2000);
		driver.findElement(By.id("checkRev_1")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("diplayNameRev_1")).sendKeys("Department Reviewer");
		Thread.sleep(1000);
		driver.findElement(By.id("checkRev_2")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("diplayNameRev_2")).sendKeys("QA Reviewer");
		Thread.sleep(1000);
		driver.findElement(By.id("checkRev_3")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("diplayNameRev_3")).sendKeys("CFT Review");
		Thread.sleep(1000);
		driver.findElement(By.id("checkRev_4")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("diplayNameRev_4")).sendKeys("QA Approver");
		Thread.sleep(1000);
		driver.findElement(By.id("checkRev_5")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("diplayNameRev_5")).sendKeys("CQA Approver");
		Thread.sleep(1000);
		driver.findElement(By.id("appBySelBtnInDmsWorkFlow")).click();
		Thread.sleep(5000);
		boolean isRecordSelectedForSingleApprovalWF = false;
		int count = 0;
		String singleApprovalName = properties.getProperty("DMSTrainingApprover_E_CODE");
		driver.findElement(By.id("locationTreeForApproverSelect_1_span")).click();
		Thread.sleep(5000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#approversTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		isRecordSelectedForSingleApprovalWF = Helper.selectSingleApprovalRecord(driver, singleApprovalName,
				isRecordSelectedForSingleApprovalWF, count);
		Thread.sleep(1000);
		if (isRecordSelectedForSingleApprovalWF) {
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		}

		Thread.sleep(1000);
		driver.findElement(By.id("dmsWFSubmit")).click();
		Thread.sleep(1200);
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
		Thread.sleep(1000);
		driver.findElement(By.id("subBtnInValidateESign")).click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
		Thread.sleep(1000);
		driver.findElement(By.className("modal-btn")).click();
		Thread.sleep(1000);
	}

}
