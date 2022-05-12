package com.pss.dms.Initiation.DmsDocsSOPTerminateDocumentRejectionFlow;

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
import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

public class CCInitiation extends QMSLoginDetails {

	@Test
	public void toCCInitiation() throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CCInitiation" + (new Random().nextInt())
					+ ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CCInitiation", "PSS-QMS-010", "Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(4000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME1"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
			Thread.sleep(5000);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

			// driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[5]/button[1]")).click();
			driver.findElement(By.cssSelector("#loginform > div:nth-child(5) > button:nth-child(1)")).click();

			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(15000);
			driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module", sno,
					false);
			sno++;
			Thread.sleep(20000);
			driver.findElement(By.id("myActivitiesInCC")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,
					false);
			sno++;
			Thread.sleep(20000);
			driver.findElement(By.cssSelector("#ccMyActivitiesListMenuId > ul > li:nth-child(1) > a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate menu", sno, false);
			Thread.sleep(5000);
			toInitiateRecord();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void toInitiateRecord() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.id("ccShortDescriptionInCcInit")).sendKeys(properties.getProperty("CC_300"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter change control short description",
				sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"reasonJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("existMethodInReasonDlg")).sendKeys(properties.getProperty("CC_1999"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter existing method procedure", sno,
				false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("propChangeInReasonDlg")).sendKeys(properties.getProperty("CC_1999"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter proposed change", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("changeReasonInReasonDlg")).sendKeys(properties.getProperty("CC_1999"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter reason for change", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addBtnInReasonsAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
		Thread.sleep(5000);
		sno++;
		WebElement element = driver
				.findElement(By.xpath("//*[@id=\"attatchmentsJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
//        driver.findElement(By.xpath("//*[@id=\"attatchmentsJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();                                      
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("attachmentFileInCCInit_1")).sendKeys(properties.getProperty("Document-1"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate menu", sno, false);
		Thread.sleep(5000);
		sno++;
		Select changetype = new Select(driver.findElement(By.id("ccChangeTypeInCcInit")));
		changetype.selectByIndex(2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the change type", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("implementationPlanInCcInit")).sendKeys(properties.getProperty("CC_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the implementation plan details", sno,
				false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("safetyAspectsOfPropChngInCcInit")).sendKeys(properties.getProperty("CC_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the implementation plan details", sno,
				false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse1 = ((JavascriptExecutor) driver);
		jse1.executeScript("window.scrollBy(0,2500)");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#productChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on product check box", sno, false);
		Thread.sleep(3000);
		Thread.sleep(5000);
		JavascriptExecutor jse2 = ((JavascriptExecutor) driver);
		jse2.executeScript("window.scrollBy(0,1000)");
		sno++;
		driver.findElement(By.id("continueInitiateBtn")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Continue button", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addProductsInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add product", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("productNameInAddProd")).sendKeys(properties.getProperty("PRODUCT_NAME"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the product name", sno, false);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"productDetialsTableContainer\"]/div/table/tbody/tr[1]/td[2]")).click();

		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Product", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("addBtnInProductAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("submitBtnInCCInitWin")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(4000);
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")))
				.sendKeys(properties.getProperty("ESIGN_PASSPWD"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the e-signature", sno, false);
		Thread.sleep(8000);
		sno++;
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(15000);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
		sno++;
		Thread.sleep(5000);
		driver.findElement(By.className("username")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		sno++;
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
	}

}
