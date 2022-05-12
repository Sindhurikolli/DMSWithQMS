package com.pss.dms.Initiation.DmsDocSOPChangeDoctApprovalFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
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
//import com.pss.dms.login.QMSLoginDetailsCC;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

public class RegReviewerCC extends QMSLoginDetails {

	@Test
	public void toReviewCC() throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RegulatoryReviewCC"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("RegulatoryReviewCC", "PSS-QMS-005", "Pass");
			writer.setPageEvent(event);
			document.open();

			WebDriverWait wait1 = new WebDriverWait(driver, 460);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME6"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module = new Select(driver.findElement(By.id("qmsModule")));
			module.selectByIndex(2);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/input[1]")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			wait1.until(ExpectedConditions
					.presenceOfElementLocated(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")));
			driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module", sno,
					false);
//        sno++;
//        Thread.sleep(20000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
			sno++;
//        Thread.sleep(25000);
			wait1.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='ccRegulatoryReviewPage.do']")));
			driver.findElement(By.cssSelector("a[href='ccRegulatoryReviewPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on review menu", sno, false);
//        Thread.sleep(100000);
//        WebDriverWait wait1 = new WebDriverWait(driver, 460);
			wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(
					By.cssSelector("#changeControlRegulatoryReviewTableContainer > div > div.jtable-busy-message"))));
			methodToDoRegReviewChgControl();
//        Thread.sleep(30000);
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void methodToDoRegReviewChgControl() throws Exception {
		Thread.sleep(36000);
		int count = 0;
		boolean isRecordSelected = false;
//        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT"); 
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = DepartmentCode + "/" ;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String chgCtrlId = "/0104";
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
		String CCNumber = properties.getProperty("chgCtrlId");
//        String CCNumberToTrim = CCQMSLoginDetails.finalCCNumber;
//        String CCNumber = CCNumberToTrim.trim(); 
//        System.out.println("CC Number is coming........:"+CCNumber);
		sno++;
		Thread.sleep(4000);
		isRecordSelected = selectRecdRegReviewChgControl(CCNumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(5000);
		if (isRecordSelected) {
			sno++;
			driver.findElement(By.id("commentsInRegulatoryCcReview")).sendKeys(properties.getProperty("CC_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName Enter the comments",
					sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("suppDocInCcRegulatoryReviewWindow"))
					.sendKeys(properties.getProperty("Document-1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
			Thread.sleep(5000);
			// String windowHandleBefore = driver.getWindowHandle();
//            Thread.sleep(5000);
//            System.out.println("window handle before: "+windowHandleBefore);
//            driver.findElement(By.id("verifyDocButtonInRegulatoryCcReview")).click();
//            Thread.sleep(1000);
////         String windowHandleBefore = driver.getWindowHandle();
//            Thread.sleep(3000);
//           Set reportWindow = driver.getWindowHandles();
//            Iterator iterator = reportWindow.iterator();
//            while (iterator.hasNext()) {
//                String popReportHandleWindow = iterator.next().toString();
//                System.out.println("popReportHandleWindow: "+popReportHandleWindow);
//                if (!popReportHandleWindow.contains(windowHandleBefore)) {
//                    driver.switchTo().window(popReportHandleWindow);
//                    Thread.sleep(4000);
//                    driver.close();
//                    Thread.sleep(2000);
//                    driver.switchTo().window(windowHandleBefore);
//                    Thread.sleep(3000);
//                }
//            }
			sno++;
			driver.findElement(By.id("approveRegulatoryButtonInCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On SUBMIT button", sno, false);
			Thread.sleep(5000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(5000);
			driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, false);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecdRegReviewChgControl(String chgControlNumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeControlRegulatoryReviewTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"changeControlRegulatoryReviewTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber)))) {
				chgControlNumber = driver
						.findElement(By.xpath(
								"//*[@id=\"changeControlRegulatoryReviewTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber))) {
				chgControlNumber = driver
						.findElement(By.xpath(
								"//*[@id=\"changeControlRegulatoryReviewTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String CCNumberSequence = driver.findElement(
								By.xpath(".//*[@id='changeControlRegulatoryReviewTableContainer']/div/table/tbody/tr[ "
										+ i + "]/td[3]"))
								.getText();// documentTypeName
						if (chgControlNumber.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(By
									.xpath(".//*[@id='changeControlRegulatoryReviewTableContainer']/div/table/tbody/tr["
											+ i + "]/td[51]/button"))
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
							.findElement(By.xpath(
									".//*[@id='changeControlRegulatoryReviewTableContainer']/div/table/tbody/tr/td[3]"))
							.getText();
					if (chgControlNumber.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(By.xpath(
								".//*[@id='changeControlRegulatoryReviewTableContainer']/div/table/tbody/tr/td[51]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#changeControlRegulatoryReviewTableContainer> div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("changeControlRegulatoryReviewTableContainer"));// Document Tree
																										// approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
