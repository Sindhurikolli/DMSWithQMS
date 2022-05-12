package com.pss.dms.DMSRegistration;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
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
import com.pss.qms.ExtentTestNGPkg.Utility;

////@Listeners(com.pss.dms.Listerners.TestListener.class)
public class ProductRegistration extends QMSLoginDetails {

	@Test
	public void ProductRegistrationMethod() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ProductRegistration"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("ProductRegistration", "PSS-DMS-012", "Pass");
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
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='productRegActivityPage.do']")));
//
//			JavascriptExecutor jse = (JavascriptExecutor) driver;
//			WebElement element = driver.findElement(By.xpath("//*[@id=\"dmsDocumentTraining\"]/div/div[1]/strong"));
//			 jse.executeScript("arguments[0].scrollIntoView(true);", element);

		sno++;
		driver.findElement(By.cssSelector("a[href='productRegActivityPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Product Registration menu", sno,
				false);
		Thread.sleep(2000);
		methodToSelectRecordTrngCompletedListSOP();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }

	private void methodToSelectRecordTrngCompletedListSOP() throws Exception {
		sno++;
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish button", sno, false);
		Thread.sleep(5000);
		sno++;
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		driver.findElement(By.id("productNameInMyActDMS")).sendKeys(properties.getProperty("Product_Name"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Product Name", sno, false);
		sno++;
		driver.findElement(By.id("productCodeInMyActDMS")).sendKeys(properties.getProperty("Product_Code"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Product Code", sno, false);
		sno++;
		Select ProductType = new Select(driver.findElement(By.id("productTypeInMyActDMS")));
		ProductType.selectByIndex(1);
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Product Type", sno, false);
		sno++;
		driver.findElement(By.id("genericNameInMyActDMS")).sendKeys(properties.getProperty("Generic_Name"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Generic Name", sno, false);
		sno++;
		driver.findElement(By.id("productStrengthInMyActDMS")).sendKeys(properties.getProperty("Product_Strength"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Product Strength", sno, false);
		sno++;
		driver.findElement(By.id("productCatgInMyActDMS")).sendKeys(properties.getProperty("Product_Category"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Product Category", sno, false);
		sno++;
		driver.findElement(By.id("marketInMyActDMS")).sendKeys(properties.getProperty("Market"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Market", sno, false);
		sno++;
		driver.findElement(By.id("customerInMyActDMS")).sendKeys(properties.getProperty("Customer"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Customer", sno, false);
		sno++;
		Select TypeofRecord = new Select(driver.findElement(By.id("typeOfRecInMyActDMS")));
		TypeofRecord.selectByIndex(1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Type of Record", sno, false);
		sno++;
		Thread.sleep(1000);
		driver.findElement(By.id("packSizeInMyActDMS")).sendKeys(properties.getProperty("PackSize"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter PackSize", sno, false);
		sno++;
		driver.findElement(By.id("packTypeInMyActDMS")).sendKeys(properties.getProperty("PackType"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter PackType", sno, false);
		sno++;
		driver.findElement(By.id("tradeNameInMyActDMS")).sendKeys(properties.getProperty("Trade_Name"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Trade Name", sno, false);
		sno++;
		driver.findElement(By.id("manfLicenseNoInMyActDMS")).sendKeys(properties.getProperty("MfgLicenceNo"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter mfgLicenceNo", sno, false);
		sno++;
		driver.findElement(By.id("manfTypeInMyActDMS")).sendKeys(properties.getProperty("MfgType"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter MfgType", sno, false);
		sno++;
		Select DomesticSelection = new Select(driver.findElement(By.id("domesticSelInMyActDMS")));
		DomesticSelection.selectByIndex(1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Domestic Selection", sno, false);
		sno++;
		driver.findElement(By.id("spclCatgNumInMyActDMS")).sendKeys(properties.getProperty("SplcategoryNumber"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter SplcategoryNumber", sno, false);
		sno++;
		driver.findElement(By.id("anyTypeSpclLicenseInMyActDMS")).sendKeys(properties.getProperty("anyspllicno"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter any type spl licence number", sno,
				false);
		sno++;
		driver.findElement(By.id("processStepOrStgRecInMyActDMS"))
				.sendKeys(properties.getProperty("ProcesssteporStageofRecord"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Process step or stage of record", sno,
				false);
		sno++;
		driver.findElement(By.id("pharmaCopoGradeInMyActDMS")).sendKeys(properties.getProperty("Pharmacopellagrade"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Pharmacopellagrade", sno, false);
		sno++;
		driver.findElement(By.id("pharmaCodeInMyActDMS")).sendKeys(properties.getProperty("Pharma_Code"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Pharma Code", sno, false);
		sno++;
		driver.findElement(By.id("storageInstInMyActDMS")).sendKeys(properties.getProperty("Storage_Instructions"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Storage Instructions", sno, false);
		sno++;
		driver.findElement(By.id("mpdOrMfrInMyActDMS")).sendKeys(properties.getProperty("MpdorMfr"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter MPD/MFR", sno, false);
		sno++;
		driver.findElement(By.id("anylMethodRefNoInMyActDMS")).sendKeys(properties.getProperty("AnalMethod"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Analytical Method", sno, false);
		sno++;
		driver.findElement(By.id("productDescInMyActDMS")).sendKeys(properties.getProperty("productdescription"));
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description", sno, false);
		sno++;
		driver.findElement(By.linkText("Submit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish button", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signatue", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(1000);
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
		Thread.sleep(1000);
		driver.findElement(By.className("modal-btn")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.className("username")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

	}

	private boolean selectRecordTrngCompletedSOP(String documentNameTrngCompleted,
			boolean isRecordSelectedTrngCompletedList, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("dmsTrainingCompletedListTable"));
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
					&& ((documentNameTrngCompleted == null) || ("".equalsIgnoreCase(documentNameTrngCompleted)))) {
				documentNameTrngCompleted = driver
						.findElement(By
								.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((documentNameTrngCompleted == null) || ("".equalsIgnoreCase(documentNameTrngCompleted))) {
				documentNameTrngCompleted = driver
						.findElement(
								By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr/td[3]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String doctNameInTrngCompleted = driver.findElement(
								By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (documentNameTrngCompleted.equalsIgnoreCase(doctNameInTrngCompleted)) {
							driver.findElement(
									By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr[ "
											+ i + " ]/td[3]"))
									.click();
							isRecordSelectedTrngCompletedList = true;
							break;
						}
					}
					if (isRecordSelectedTrngCompletedList) {
						break;
					}
				} else {
					String doctNameInTrngCompleted = driver
							.findElement(By.xpath(
									"//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr/td[3]"))
							.getText();
					if (documentNameTrngCompleted.equalsIgnoreCase(doctNameInTrngCompleted)) {
						driver.findElement(
								By.xpath("//*[@id=\"dmsTrainingCompletedListTable\"]/div/div[4]/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedTrngCompletedList = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedTrngCompletedList) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("dmsTrainingCompletedListTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedTrngCompletedList;
	}

	@AfterMethod
	public void testIT(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}

}
