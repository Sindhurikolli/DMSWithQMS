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
//import com.pss.qms.login.QMSLoginDetailsCC;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

////@Listeners(com.pss.dms.Listerners.TestListener.class)
public class ActionItemPerformAfterReject extends QMSLoginDetails {

	@Test
	public void toActionItemPerformAfterReject() throws Exception {

//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ActionItemPerformAfterRejectAtApprove"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("ActionItemPerformAfterRejectAtApprove", "PSS-QMS-050",
				"Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("QMSLoginUrl"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CCActionItemOwner"));
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
		Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module",sno,false);
//        sno++;
//        Thread.sleep(12000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(16000);
//        driver.findElement(By.cssSelector("#ccActItemListMenuId > a > b")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Action Item Implementation menu", sno,false);
//        sno++;
//        Thread.sleep(16000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ccActItemRejectPage.do']")));

		driver.findElement(By.cssSelector("a[href='ccActItemRejectPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Revert submenu", sno, false);
		Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#changeControlActionItemRejectTable > div > div.jtable-busy-message[style='display: none;']")));
		methodToDoActionItemPerform();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
	}

//	}

	private void methodToDoActionItemPerform() throws Exception {

		Thread.sleep(5000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = DepartmentCode + "-";
//        Date date = new Date() ;
//        String sdf = new SimpleDateFormat("YY").format(date);
		String CCNumber = properties.getProperty("ACTION_ITEM_chgCtrlId");
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//        String CCNumberToTrim = CCQMSLoginDetails.finalCCNumber;
//        String CCNumber = CCNumberToTrim.trim(); 
//        System.out.println("CC Number is coming........:"+CCNumber);
		Thread.sleep(4000);
		isRecordSelected = selectRecdActionItemPerform(CCNumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(5000);
		if (isRecordSelected) {
			sno++;
			driver.findElement(By.id("commentsInCCActItmReject")).sendKeys(properties.getProperty("CC_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(3000);
			sno++;
//           JavascriptExecutor scl = ((JavascriptExecutor) driver);
//            scl.executeScript("window.scrollBy(0,500)");
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInRejectAI\"]/div/div[3]/div[2]/span/span[2]/b"))
//					.click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
//			Thread.sleep(3000);
//			JavascriptExecutor scl = ((JavascriptExecutor) driver);
//			scl.executeScript("window.scrollBy(0,500)");
//			Thread.sleep(2000);
//			sno++;
//			driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-3"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
//			Thread.sleep(2000);
			JavascriptExecutor scl1 = ((JavascriptExecutor) driver);
			scl1.executeScript("window.scrollBy(0,500)");
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("submitBtnInCcActItmRej")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On SUBMIT button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit button", sno, false);
			Thread.sleep(3000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdActionItemPerform(String CCNumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeControlActionItemRejectTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"changeControlActionItemRejectTable\"]/div/div[4]/div[2]/span"))
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
								By.xpath("//*[@id=\"changeControlActionItemRejectTable\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			} else if ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber))) {
				CCNumber = driver
						.findElement(
								By.xpath("//*[@id=\"changeControlActionItemRejectTable\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String CCNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"changeControlActionItemRejectTable\"]/div/table/tbody/tr[ " + i
										+ " ]/td[4]"))
								.getText();// documentTypeName
//                        String CCString="";
//                        String separateCCNumber[] = CCNumberSequence.split("/");
//                        System.out.println("separateCCNumber: "+separateCCNumber[0]);
//                        String CCNo = separateCCNumber[0]; 
//                        Thread.sleep(2000);
//                        CCNo = CCNo.trim();
//                        System.out.println("CCNo: "+CCNo);
//                        Thread.sleep(5000);
						if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"changeControlActionItemRejectTable\"]/div/table/tbody/tr[ " + i
											+ " ]/td[4]"))
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
							.findElement(By
									.xpath("//*[@id=\"changeControlActionItemRejectTable\"]/div/table/tbody/tr/td[4]"))
							.getText();
//                    String CCString="";
//                    String separateCCNumber[] = CCNumberSequence.split("/");
//                    System.out.println("separateCCNumber: "+separateCCNumber[0]);
//                    String CCNo = separateCCNumber[0]; 
//                    Thread.sleep(2000);
//                    CCNo = CCNo.trim();
//                    System.out.println("CCNo: "+CCNo);
//                    Thread.sleep(5000);
					if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"changeControlActionItemRejectTable\"]/div/table/tbody/tr/td[4]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#changeControlActionItemRejectTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("changeControlActionItemRejectTable"));// Document Tree approve
																							// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
