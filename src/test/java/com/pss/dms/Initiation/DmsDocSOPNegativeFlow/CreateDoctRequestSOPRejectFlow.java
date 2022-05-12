/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.Initiation.DmsDocSOPNegativeFlow;

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

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
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
public class CreateDoctRequestSOPRejectFlow extends QMSLoginDetails {

	public CreateDoctRequestSOPRejectFlow() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoCreateDoctRequestSOPRejectFlow()
			throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateDoctRequestSOPRejectFlow"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CreateDoctRequestSOPRejectFlow", "PSS-DMS-014",
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
//	        sno++;
//	        driver.findElement(By.id("newDocMenuListListId")).click();
//	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New Document menu", sno,false);
//	        Thread.sleep(4000);
//	        sno++;
//	        driver.findElement(By.id("dmsNewDocReqPageId")).click();
//	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Request sub menu", sno,false);
//	        Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(10000);
			methodToCreateDoctRequestSOP();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void methodToCreateDoctRequestSOP() throws Exception {
		sno++;
		driver.findElement(By.id("documentNameInNewDocRequestPage"))
				.sendKeys(properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document name", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.id("keyWordsInNewDocRequestPage"))
				.sendKeys(properties.getProperty("KEYWORDS_DOCUMENT_REQUEST"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the keywords", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("locSelBtnInNewDocumentRequest")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno, false);
		Thread.sleep(3000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("treeContainer_3_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno, false);
		Thread.sleep(2000);
		sno++;
		Select rolesDropDownList = new Select(driver.findElement(By.id("docTypeInDmsNewDocRequestPage")));
		rolesDropDownList.selectByIndex(1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the document type", sno, false);
		Thread.sleep(1000);
		methodToSelectSingleApprovalInDoctReqSOP();

	}

	private void methodToSelectSingleApprovalInDoctReqSOP() throws Exception {
		sno++;
		Thread.sleep(4000);
		boolean isRecordSelectedSingleApprovalDoctReqSOP = false;
		int count = 0;
		driver.findElement(By.id("selectRepToInCreateUser")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno, false);
		Thread.sleep(4000);
		driver.findElement(By.id("locationTreeForApproverSelect_2_span")).click();
		Thread.sleep(4000);
		sno++;
		String userName = properties.getProperty("DMSTrainingApprover_E_CODE");
		isRecordSelectedSingleApprovalDoctReqSOP = Helper.selectSingleApprovalRecord(driver, userName,
				isRecordSelectedSingleApprovalDoctReqSOP, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Approve from record", sno,
				false);
		Thread.sleep(4000);
		sno++;
		if (isRecordSelectedSingleApprovalDoctReqSOP = true) {
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno, false);
//            driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno,false);
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
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(5000);
			sno++;
			WebElement logout = driver.findElement(By.cssSelector("a[href='Logout.do']"));
			JavascriptExecutor jslogout = (JavascriptExecutor) driver;
			jslogout.executeScript("arguments[0].click();", logout);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		}

	}
}
