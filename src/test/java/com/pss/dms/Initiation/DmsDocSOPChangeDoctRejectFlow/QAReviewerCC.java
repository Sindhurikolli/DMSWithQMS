package com.pss.dms.Initiation.DmsDocSOPChangeDoctRejectFlow;

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

public class QAReviewerCC extends QMSLoginDetails {

	@Test
	public void toQAReviewerCC() throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewerCC" + (new Random().nextInt())
					+ ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReviewerCC", "PSS-QMS-014", "Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME3"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[5]/button[1]")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(40000);
			driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module", sno,
					false);
			sno++;
			Thread.sleep(15000);
			driver.findElement(By.id("myActivitiesInCC")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,
					false);
			sno++;
			Thread.sleep(20000);
			driver.findElement(By.cssSelector("#ccMyActivitiesListMenuId > ul > li:nth-child(1) > a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review menu", sno, false);
//        Thread.sleep(20000);
			WebDriverWait wait1 = new WebDriverWait(driver, 420);
			wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(
					By.cssSelector("#changeControlReviewTableContainer > div > div.jtable-busy-message"))));
			methodToDoDeptReviewChgControl();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void methodToDoDeptReviewChgControl() throws Exception {
		Thread.sleep(16000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver
				.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/div[4]/div[2]/span"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
//        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT");
		String CCNumber = properties.getProperty("chgCtrlId");
		Thread.sleep(4000);
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
		isRecordSelected = selectRecdDeptReviewChgControlDoctPositiveFlow(CCNumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate menu", sno, false);
		Thread.sleep(5000);
		if (isRecordSelected) {
			Thread.sleep(5000);
			sno++;
			Select ImpactOnProduct = new Select(driver.findElement(By.id("impOnProdQualityInQaPrimaryCcReview")));
			ImpactOnProduct.selectByIndex(2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Impact  On product quality",
					sno, false);
			Thread.sleep(2000);
			sno++;
			Select ImpactOnQualitySystem = new Select(
					driver.findElement(By.id("impOnQualSysOrProcInQaPrimaryCcReview")));
			ImpactOnQualitySystem.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Impact on quality system",
					sno, false);
			Thread.sleep(2000);
			sno++;
			Select CategoryofChange = new Select(driver.findElement(By.id("cateOfChangeInQaPrimaryCcReview")));
			CategoryofChange.selectByIndex(3);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Category of change", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("addNewDocInCheckInCCRevInQaPrim")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add document", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"docDetailsTableNewInCcRev\"]/div/div[3]/div[2]/span[2]/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on change document", sno, false);
			Thread.sleep(32000);
			sno++;
			driver.findElement(By.id("locTreeForAddDocuments_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select the location", sno, false);
			Thread.sleep(24000);
			WebDriverWait wait1 = new WebDriverWait(driver, 420);
			wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(
					By.cssSelector("#documentsWidowDetialsTableContainer > div > div.jtable-busy-message"))));
			int count1 = 0;
			boolean isRecordSelectedForChngDoc = false;
			String ChngDocNo = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
			isRecordSelected = selectRecdForChngDoc(ChngDocNo, isRecordSelectedForChngDoc, count1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("addBtnInDocumentAdd")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			sno++;
			driver.findElement(By.id("addActionItemCheckInQaPrimaryCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add ActionItem", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("selectOwnerInCcQaPriReview1")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			Thread.sleep(5000);
			int count2 = 0;
			boolean isRecordSelectedForActionItem = false;
			String AIOwner = properties.getProperty("CCActionItemOwnerE-Code");
			isRecordSelectedForActionItem = selectRecdForAI(AIOwner, isRecordSelectedForActionItem, count2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("usersSelBtnInCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno, false);
			sno++;
			driver.findElement(By.id("selectAppInCcQaPriReview1")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			Thread.sleep(5000);
			int count3 = 0;
			boolean isRecordSelectedForActionItemApprover = false;
			String AIApprover = properties.getProperty("CCActionItemReviewerE_Code");
			isRecordSelectedForActionItemApprover = selectRecdForAIApp(AIApprover,
					isRecordSelectedForActionItemApprover, count3);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("usersSelBtnInCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("actItmDueDateInCcQaReview1")).sendKeys(properties.getProperty("AI_TARGET_DAYS"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("actItmDescInCcQaReview1")).sendKeys(properties.getProperty("CC_300"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"docDetailsTableNewInCcRev\"]/div/table/tbody/tr/td[11]/button"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Link Action item button", sno,
					false);
			Thread.sleep(4000);
			sno++;
			Select AIfromDropDown = new Select(driver.findElement(By.id("actItemsInCcRevDocDlg")));
			AIfromDropDown.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Action item from drop down", sno,
					false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("addActItemBtnInAddActItmDlg")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);

			sno++;
			driver.findElement(By.id("reviewCompletedQaPrimaryCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on QAApprover radio button", sno,
					false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("commentsQaPrimaryCcReview")).sendKeys(properties.getProperty("CC_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("approveQaPrimaryCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approve button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(5000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(5000);
			driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecdForAIApp(String AIApprover, boolean isRecordSelectedForActionItemApprover, int count3)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("usersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
			if ((totalNoOfRecords > 1) && ((AIApprover == null) || ("".equalsIgnoreCase(AIApprover)))) {
				AIApprover = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			} else if ((AIApprover == null) || ("".equalsIgnoreCase(AIApprover))) {
				AIApprover = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String CCNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (AIApprover.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]td[3]"))
									.click();
							isRecordSelectedForActionItemApprover = true;
							break;
						}
					}
					if (isRecordSelectedForActionItemApprover) {
						break;
					}
				} else {
					String CCNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (AIApprover.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForActionItemApprover = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForActionItemApprover) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForActionItemApprover;
	}

	private boolean selectRecdForAI(String AIOwner, boolean isRecordSelectedForActionItem, int count2)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("usersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((AIOwner == null) || ("".equalsIgnoreCase(AIOwner)))) {
				AIOwner = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			} else if ((AIOwner == null) || ("".equalsIgnoreCase(AIOwner))) {
				AIOwner = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String CCNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (AIOwner.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]td[3]"))
									.click();
							isRecordSelectedForActionItem = true;
							break;
						}
					}
					if (isRecordSelectedForActionItem) {
						break;
					}
				} else {
					String CCNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (AIOwner.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForActionItem = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForActionItem) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForActionItem;
	}

	private boolean selectRecdForChngDoc(String chngDocNo, boolean isRecordSelectedForChngDoc, int count1)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("documentsWidowDetialsTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((chngDocNo == null) || ("".equalsIgnoreCase(chngDocNo)))) {
				chngDocNo = driver
						.findElement(By
								.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((chngDocNo == null) || ("".equalsIgnoreCase(chngDocNo))) {
				chngDocNo = driver
						.findElement(By
								.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType

			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String CCNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (chngDocNo.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[ " + i
											+ " ]/td[3]"))
									.click();
							isRecordSelectedForChngDoc = true;
							break;
						}
					}
					if (isRecordSelectedForChngDoc) {
						break;
					}
				} else {
					String CCNumberSequence = driver
							.findElement(By
									.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (chngDocNo.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForChngDoc = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForChngDoc) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#documentsWidowDetialsTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("documentsWidowDetialsTableContainer"));// Document Tree approve
																								// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForChngDoc;
	}

	private boolean selectRecdDeptReviewChgControlDoctPositiveFlow(String CCNumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeControlReviewTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/div[4]/div[2]/span"))
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
								By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber))) {
				CCNumber = driver
						.findElement(
								By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String CCNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[ " + i
											+ " ]/td[49]/button"))
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
									By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[49]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#changeControlReviewTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("changeControlReviewTableContainer"));// Document Tree approve
																							// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
