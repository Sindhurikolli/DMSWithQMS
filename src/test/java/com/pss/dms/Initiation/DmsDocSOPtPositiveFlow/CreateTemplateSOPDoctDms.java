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
public class CreateTemplateSOPDoctDms extends QMSLoginDetails {

	public CreateTemplateSOPDoctDms() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoCreateTemplateDms() throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateTemplateSOPDoctDms"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CreateTemplateSOPDoctDms", "PSS-DMS-001", "Pass");
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
//        WebDriverWait w =new WebDriverWait(driver,10);
//        w.until(ExpectedConditions.visibilityOfElementLocated(By.id("myActivitiesInCapa")));
//        driver.findElement(By.cssSelector("a[href='dmsNewDocRequestPage.do']")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        Thread.sleep(4000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.cssSelector("a[href='dmsTemplateReg.do']"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);

			driver.findElement(By.cssSelector("a[href='dmsTemplateReg.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,
					false);
			Thread.sleep(20000);
			methodToDoTemplateDms();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void methodToDoTemplateDms() throws Exception {
		Thread.sleep(4000);
		sno++;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.linkText("Next"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);

		driver.findElement(By.linkText("Next")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
		sno++;
		Thread.sleep(1000);
		driver.findElement(By.id("templateFormatName"))
				.sendKeys(properties.getProperty("TEMPLATE_NAME_CREATE_DOCT_SOP"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the template name", sno, false);
		sno++;
		Thread.sleep(1000);
		driver.findElement(By.id("templateFormatDescription"))
				.sendKeys(properties.getProperty("TEMPLATE_DESCRIPTION_DOCT_SOP"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the template description", sno,
				false);
		sno++;
		Thread.sleep(1000);
		Select rolesDropDownList = Helper.getSelectInstance(driver, By.id("documentTypeOfTemplateFormat"));
		List<WebElement> webElementList = rolesDropDownList.getOptions();
		if (webElementList.size() > 0) {
			rolesDropDownList.selectByIndex(Integer.parseInt("1"));

		} else {
			System.out.println("No options in Select(Roles - drop down list)");
		}
		Thread.sleep(1000);// regWorkFlowSelect
		List<WebElement> list = driver.findElements(By.id("regWorkFlowSelect"));
		for (WebElement we : list) {
			System.out.println("we=" + we.getText());
			if (!"".equals(we.getText().trim())) {
//                driver.findElement(By.id("regWorkFlowSelect")).click();
				we.click();
				break;
			}
		}
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the document type", sno, false);
		Thread.sleep(1000);
		driver.findElement(By.id("treeContainer_2_span")).click();
		Thread.sleep(1000);
//        driver.findElement(By.id("treeContainer_3_span")).click();
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
		Thread.sleep(1000);
		methodToSelectSingleApprovalRecordInTemplateSOP();

	}

	private void methodToSelectSingleApprovalRecordInTemplateSOP() throws Exception {
		sno++;
		boolean isRecordSelectedSingleApprovalTempSOP = false;
		int count = 0;
		driver.findElement(By.id("appBySelBtnInDmsRolesReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approve from select button", sno,
				false);
		Thread.sleep(1000);
		driver.findElement(By.id("locationTreeForApproverSelect_2_span")).click();
		Thread.sleep(1000);
		sno++;
		String userName = properties.getProperty("DMSTrainingApprover_E_CODE");
		isRecordSelectedSingleApprovalTempSOP = Helper.selectSingleApprovalRecord(driver, userName,
				isRecordSelectedSingleApprovalTempSOP, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Approve from record", sno,
				false);
		Thread.sleep(1000);
		if (isRecordSelectedSingleApprovalTempSOP) {
			sno++;
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("uploadBtn")).sendKeys(properties.getProperty("UPLOAD_DOCT_TEMPLATE_SOP"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
			Thread.sleep(1000);
//            driver.findElement(By.id("uploadBtn")).click();
			sno++;
			driver.findElement(By.id("versionNoOfCreateTemplate"))
					.sendKeys(properties.getProperty("VERSION_TEMPLATE_DOCT_SOP"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the version", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.linkText("Finish")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on FINISH button", sno, false);
			Thread.sleep(1000);
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
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		}
	}
}
