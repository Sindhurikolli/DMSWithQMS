package com.pss.dms.DocumentRequestForDocumnetTypes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.testng.annotations.Test;

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

public class CreateNewDocumentRequest extends QMSLoginDetails {

	@Test
	public void toCreateDocumentRequestSOP() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateDocumentRequest"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CreateDocumentRequest", "PSS-DMS-003", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(4000);
//			WebDriverWait wait = new WebDriverWait(driver, 440);
		driver.get(properties.getProperty("DMSURL"));
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("DMSInitiator"));

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
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='dmsNewDocRequestPage.do']")));

		driver.findElement(By.cssSelector("a[href='dmsNewDocRequestPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New Request", sno, false);
		Thread.sleep(5000);

		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("newDocMenuListListId")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New Document menu", sno,false);
//        Thread.sleep(4000);
//        sno++;
//        driver.findElement(By.id("dmsNewDocReqPageId")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Request sub menu", sno,false);
//        Thread.sleep(4000);
//        sno++;
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a"));
//         jse.executeScript("arguments[0].scrollIntoView(true);", element);
//        driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno,false);
//        Thread.sleep(10000);
		methodToCreateDoctRequestSOP();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	}

	private void methodToCreateDoctRequestSOP() throws Exception {
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
		Thread.sleep(10000);
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
//        rolesDropDownList.selectByIndex(1);
		String DocumentType = properties.getProperty("DocumentType");
		rolesDropDownList.selectByVisibleText(DocumentType);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the document type", sno, false);
		Thread.sleep(1000);
//        if(DocumentType=="SOP")
		if (DocumentType.equalsIgnoreCase("SOP")) {
			methodToSelectSingleApprovalInDoctReqSOP();
		} else {
			methodToSelectSingleApprovalInDoctReqOtherThanSOP();

		}
		Thread.sleep(3000);
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
		sno++;
		driver.findElement(By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

	}

	private void methodToSelectSingleApprovalInDoctReqSOP() throws Exception {

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
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
			System.out.println("Approver Not Selected");
			sno++;
			driver.findElement(By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[2]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[14]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
			Assert.assertTrue(false);
		}

	}

	private void methodToSelectSingleApprovalInDoctReqOtherThanSOP() throws Exception {

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
//         driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno,false);
			Thread.sleep(1000);
			sno++;
		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[2]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"dmsHeaderNavBar\"]/ul/li[14]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
			Assert.assertTrue(false);
		}
		Thread.sleep(5000);
		driver.findElement(By.id("prodSelBtnInNewDocumentRequest")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Product Select Button ", sno,
				false);
		Thread.sleep(5000);
		sno++;
		int count1 = 0;
		boolean isRecordSelected = false;
		String ProductCode = properties.getProperty("ProductCode");
		isRecordSelected = selectRecdForProduct(ProductCode, isRecordSelected, count1);
		Thread.sleep(5000);
		if (isRecordSelected) {
			driver.findElement(By.id("selectBtnForDialogWindow")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button ", sno, false);
			Thread.sleep(5000);
			sno++;
		} else {
			System.out.println("Product Not Selected");
			Assert.assertTrue(false);
		}

	}

	private boolean selectRecdForProduct(String ProductCode, boolean isRecordSelected, int count1)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("productSelForBmrInNewDocReq"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"productSelForBmrInNewDocReq\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((ProductCode == null) || ("".equalsIgnoreCase(ProductCode)))) {
				ProductCode = driver
						.findElement(By.xpath("//*[@id=\"productSelForBmrInNewDocReq\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((ProductCode == null) || ("".equalsIgnoreCase(ProductCode))) {
				ProductCode = driver
						.findElement(By.xpath("//*[@id=\"productSelForBmrInNewDocReq\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType

			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String ProductNoSequence = driver
								.findElement(By.xpath(
										".//*[@id='productSelForBmrInNewDocReq']/div/table/tbody/tr[ " + i + "]/td[4]"))
								.getText();// documentTypeName
						if (ProductCode.equalsIgnoreCase(ProductNoSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"productSelForBmrInNewDocReq\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String ProductNoSequence = driver
							.findElement(By.xpath("//*[@id=\"productSelForBmrInNewDocReq\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (ProductCode.equalsIgnoreCase(ProductNoSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"productSelForBmrInNewDocReq\"]/div/table/tbody/tr/td[4]")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#productSelForBmrInNewDocReq > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("productSelForBmrInNewDocReq"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
