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

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jeevan
 */
public class TemplateRegistration extends QMSLoginDetails {

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
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='dmsTemplateReg.do']")));
		sno++;
		driver.findElement(By.cssSelector("a[href='dmsTemplateReg.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin menu", sno, false);
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}

		Thread.sleep(5000);
		methodToDoTemplateCreationInDms();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
	}

	private void methodToDoTemplateCreationInDms() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		driver.findElement(By.linkText("Next")).click();
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(1000);
		driver.findElement(By.id("templateFormatName")).sendKeys(properties.getProperty("Template_Name"));
		Thread.sleep(1000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Template Name", sno, false);
		driver.findElement(By.id("templateFormatDescription")).sendKeys(properties.getProperty("Template_Description"));
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Template Description", sno, false);
		Thread.sleep(1000);

		Select rolesDropDownList = new Select(driver.findElement(By.id("documentTypeOfTemplateFormat")));
		rolesDropDownList.selectByVisibleText(properties.getProperty("DocType_Template"));
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Doc Type", sno, false);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"maskingId-p-1\"]/div[2]/div[2]/div[2]/button")).click();
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Select button at Location", sno,
				false);
		Thread.sleep(5000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(properties.getProperty("Template_Location"))).click();
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Template Location", sno, false);
		Thread.sleep(1000);
		driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
		Thread.sleep(1000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Select button at Approver", sno,
				false);
		driver.findElement(By.id("appBySelBtnInDmsRolesReg")).click();
		Thread.sleep(5000);
		boolean isRecordSelectedSingleApprovalTempSTP = false;
		int count = 0;

		Thread.sleep(1000);
		driver.findElement(By.id("locationTreeForApproverSelect_1_span")).click();
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Select Location", sno, false);
		Thread.sleep(5000);
		String userName = properties.getProperty("DMSTrainingApprover_E_CODE");
		isRecordSelectedSingleApprovalTempSTP = Helper.selectSingleApprovalRecord(driver, userName, isRecordSelectedSingleApprovalTempSTP, count);
		Thread.sleep(1000);
		if (isRecordSelectedSingleApprovalTempSTP) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select approver", sno, false);
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Select button", sno, false);
			Thread.sleep(1000);
			driver.findElement(By.id("uploadBtn")).sendKeys(properties.getProperty("UPLOAD_DOCT_TEMPLATE"));
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "upload document", sno, false);
			Thread.sleep(1000);
//              driver.findElement(By.id("uploadBtn")).click();
			driver.findElement(By.id("versionNoOfCreateTemplate"))
					.sendKeys(properties.getProperty("VERSION_TEMPLATE_DOCT"));
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Version", sno, false);
			Thread.sleep(2000);
			driver.findElement(By.linkText("Finish")).click();
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Finish", sno, false);
			Thread.sleep(1000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-sign Password", sno, false);
			Thread.sleep(1000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "submit E-sign", sno, false);
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(1000);
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		}
		driver.findElement(By.className("username")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
	}
}
