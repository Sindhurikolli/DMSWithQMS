package com.pss.dms.CCRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

////@Listeners(com.pss.dms.Listerners.TestListener.class)
public class ReInitiateDocument extends QMSLoginDetails {

	@Test
	public void toReInitiateDocument() throws Exception {

//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ReInitiateDocument "
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("ReInitiateDocument ", "PSS-QMS-020", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("QMSLoginUrl"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CCInitiator"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByIndex(2);
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id='loginform']/div[7]/button[1]")).click();
		Thread.sleep(5000);
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(
				sno + "." + "Enter the username, password Select Change Control Module and click on login button"
						+ Utilities.prepareSSNumber(sno),
				font));
		document.add(im);

		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		Thread.sleep(4000);
//        driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module",sno,false);
//        sno++;
//        Thread.sleep(12000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(4000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='ccRejectedPage.do']")));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reverted menu", sno, false);
		sno++;
		driver.findElement(By.cssSelector("a[href='ccRejectedPage.do']")).click();
		
		Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#changeControlRejTableContainer > div > div.jtable-busy-message[style='display: none;']")));

		todoReInitiateDocument();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
	}

//	}

	private void todoReInitiateDocument() throws Exception {

		Thread.sleep(5000);
		/*
		 * JavascriptExecutor jse = (JavascriptExecutor)driver;
		 * jse.executeScript("window.scrollBy(0,2000)");
		 */
		
		int count = 0;
		boolean isRecordSelected = false;
//        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT"); 
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = DepartmentCode + "/" ;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String chgCtrlId = "/0105";
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
		String CCNumber = properties.getProperty("chgCtrlId");
//        String ChangeControlNoToTrim = CCQMSLoginDetails.finalCCNumber;
//        String chgControlNumber = ChangeControlNoToTrim.trim(); 
//        System.out.println("ChangeControl Number is coming........:"+chgControlNumber);
		Thread.sleep(3000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record and Click on View", sno, false);
		sno++;
		isRecordSelected = selectRecdReReviewChgControl(CCNumber, isRecordSelected, count);
		
		Thread.sleep(1000);
		if (isRecordSelected) {
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
//			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#viewChangeControlRejDetailsWindow > div > div.jtable-busy-message[style='display: none;']")));

			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#ccRejectReasonsTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(5000);
			
//			if (driver.findElement(By.id("addDocumentsInCcInit")).isDisplayed()) {
//				driver.findElement(By.id("addDocumentsInCcInit")).click();
//				 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add document", sno,false);
//			      sno++;
//			      Thread.sleep(2000);
//				Thread.sleep(5000);
//				driver.findElement(By.linkText(properties.getProperty("SourceLocation"))).click();
//				Thread.sleep(5000);
//				 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Location", sno,false);
//			      sno++;
//			      Thread.sleep(2000);
//				driver.findElement(
//						By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[2]/td[2]")).click();
//				Thread.sleep(3000);
//				 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select document", sno,false);
//			      sno++;
//			      Thread.sleep(2000);
//				driver.findElement(By.id("addBtnInDocumentAdd")).click();
//				 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Button", sno,false);
//			      sno++;
//			      Thread.sleep(2000);
//			} else {
////		         driver.findElement(By.cssSelector("#documentJtableInCCInit > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text")).click();
////		         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add document", sno,false);
////		        Thread.sleep(5000);
////		        sno++;
////		         driver.findElement(By.id("docTitleInCCDocDlg")).sendKeys(properties.getProperty("Document_Name"));;
////		         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document name", sno,false);
////		        Thread.sleep(5000);
////		        sno++;
////		         driver.findElement(By.id("addDetailsInCCDocDlg")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
////		         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the additional details", sno,false);
////		        Thread.sleep(5000);
////		        sno++;
//////		         driver.findElement(By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
//////		        Thread.sleep(5000);
////		        driver.findElement(By.id("addBtnInCCDocDlg")).click();
////		        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno,false);
////		        Thread.sleep(5000);
////		        sno++;
//				System.out.println("manual entry of source");
//			}
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#attatchmentsJtableInCCRejEditId > div > div.jtable-busy-message[style='display: none;']")));

//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("div.jtable-busy-message")));
			// driver.findElement(By.id("commentsInHodCcReview")).sendKeys(properties.getProperty("COMMENTS"));
//            Thread.sleep(1000);
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			jse1.executeScript("window.scrollBy(0,4000)");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"othersJTableInCcRej\"]/div/table/tbody/tr/td[4]/button")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit at Others", sno, false);
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.id("othersDetailsInCCOthersRejDlg")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear text", sno, false);
			sno++;
			driver.findElement(By.id("othersDetailsInCCOthersRejDlg")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Add Others", sno, false);
			sno++;
			jse1.executeScript("window.scrollBy(0,50)");
			driver.findElement(By.id("addBtnInCCOthersRejDlg")).click();
			Thread.sleep(5000);
//            driver.findElement(By.xpath(".//*[@id='deptReviewUserDetailsInRej']/div/table/tbody/tr[2]/td[1]/input")).click();
//            Thread.sleep(1000);
//            driver.findElement(By.xpath(".//*[@id='qaReviewUserDetailsInRej']/div/table/tbody/tr[2]/td[1]/input")).click();
//            Thread.sleep(1000);
			
			driver.findElement(By.id("commentsForReinitiate")).sendKeys(properties.getProperty("CC_300"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(5000);
			sno++;
			JavascriptExecutor jse2 = (JavascriptExecutor) driver;
			WebElement element2 = driver.findElement(By.id("subBtnInCcRejRev"));
			jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
//            Thread.sleep(4000);
			driver.findElement(By.id("subBtnInCcRejRev")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(1000);
//            driver.findElement(By.id("approveInHodCcReview")).click();
//            Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(3000);
			sno++;

			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdReReviewChgControl(String CCNumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeControlRejTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
//			JavascriptExecutor jse = (JavascriptExecutor) driver;
//			WebElement element = driver
//					.findElement(By.xpath("//*[@id=\"changeControlRejTableContainer\"]/div/div[4]/div[2]/span"));
//			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			String a = driver
					.findElement(By.xpath("//*[@id=\"changeControlRejTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber)))) {
				CCNumber = driver
						.findElement(
								By.xpath("//*[@id=\"changeControlRejTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber))) {
				CCNumber = driver
						.findElement(By.xpath("//*[@id=\"changeControlRejTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//						WebElement element1 = driver.findElement(By.xpath(
//								".//*[@id='changeControlRejTableContainer']/div/table/tbody/tr[ \" + i + \"]/td[2]"));
//						jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
						String CCNumberSequence = driver.findElement(By.xpath(
								".//*[@id='changeControlRejTableContainer']/div/table/tbody/tr[ " + i + "]/td[2]"))
								.getText();// documentTypeName
						if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(
									By.xpath(".//*[@id='changeControlRejTableContainer']/div/table/tbody/tr[ " + i
											+ " ]/td[34]/button"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String CCNumberSequence = driver
							.findElement(
									By.xpath("//*[@id=\"changeControlRejTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(By
								.xpath("//*[@id=\"changeControlRejTableContainer\"]/div/table/tbody/tr/td[34]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//					JavascriptExecutor jse2 = (JavascriptExecutor) driver;
//					WebElement element2 = driver.findElement(By.cssSelector(
//							"#changeControlRejTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//					jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
					driver.findElement(By.cssSelector(
							"#changeControlRejTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("changeControlRejTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
