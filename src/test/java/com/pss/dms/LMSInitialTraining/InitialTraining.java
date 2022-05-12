package com.pss.dms.LMSInitialTraining;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.schedule.Helper;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

public class InitialTraining extends QMSLoginDetails {

	@Test
	public void createInitialTraining() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/"
				+ "InitialTrainingModifyWithoutWebandWithoutQuestions" + (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent(
				"Initial Training Without Web Based and Without Question Paper", "PSS-LMS-005", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.get(properties.getProperty("LMSURL"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("LMSTrainingCoordinator"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/button[1]")).click();
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
		Thread.sleep(3000);
//		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//		WebElement element1 = driver.findElement(By.id("inductionSchedule"));
//		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		driver.findElement(By.id("inductionSchedule")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initial Training", sno, false);
		Thread.sleep(4000);
		methodToCreateInitialTrainingWithoutClassAndWithoutQuestions();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodToCreateInitialTrainingWithoutClassAndWithoutQuestions() throws Exception {

		sno++;
		driver.findElement(By.linkText("Next")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"userInInitTrainingTable\"]/div/div[3]/div[2]/span/span/b")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add User", sno, false);
		WebDriverWait wait = new WebDriverWait(driver, 240);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("mulitUserSelTree_2_span")));
		sno++;
		driver.findElement(By.id("mulitUserSelTree_2_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(10000);
		int count1 = 0;
		boolean isRecordSelectedForSelectingMultiUsers = false;
		String multiUsersInSchedules = properties.getProperty("InitialTrainingEcode");
		isRecordSelectedForSelectingMultiUsers = Helper.selectingMultiUsersRecordForSchedules(driver,
				multiUsersInSchedules, isRecordSelectedForSelectingMultiUsers, count1);
		if (isRecordSelectedForSelectingMultiUsers) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selBtnInMultiUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("nameInInitTraining")).sendKeys(properties.getProperty("InitialTrainingName"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selectLocIdInPlanedSchedule")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("locSelTree_2_span")));
			sno++;
			driver.findElement(By.id("locSelTree_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selBtnInLocSelWin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selectWorkFlowInPlanedSchedule")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("workFlowTree_2_span")));
			sno++;
			driver.findElement(By.id("workFlowTree_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			Thread.sleep(6000);
			int count = 0;
			boolean recSelectedForWF = false;
			String workFlowName = properties.getProperty("WORKFLOW_NAMEForUser");
			recSelectedForWF = Helper.selectRecordForWorkFlowForSchedules(driver, workFlowName, recSelectedForWF,
					count);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selBtnInWFlowSelWin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			Select training = new Select(driver.findElement(By.id("traingCategoryInInitTraining")));
			Thread.sleep(1000);
			training.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Training Category", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"ItemInInitTrainingFormTable\"]/div/div[3]/div[2]/span/span/b"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Topic", sno, false);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("multi_item-tree_2_span")));
			sno++;
			driver.findElement(By.id("multi_item-tree_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			Thread.sleep(10000);
			int count2 = 0;
			boolean isRecordSelectedForMultiItems = false;
			String number = properties.getProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP");
			String name1 = number + "-" + properties.getProperty("LMSDocumentVersion");
			String multiDocNoForItem = name1;
			isRecordSelectedForMultiItems = selectMultiItemDetails(multiDocNoForItem, isRecordSelectedForMultiItems,
					count2);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selBtnInItems")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[@id=\"ItemOrCurriculaTableInInitTraining\"]/div/table/tbody/tr/td[9]/button")));
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"ItemOrCurriculaTableInInitTraining\"]/div/table/tbody/tr/td[9]/button"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit", sno, false);
			Thread.sleep(2000);
			sno++;
//			Select classRoom = new Select(driver.findElement(By.id("Edit-classRoomBasedOptionInitTraining")));
//			Thread.sleep(1000);
//			classRoom.selectByVisibleText("Yes");
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Class Room Yes/NO", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			Select questions = new Select(driver.findElement(By.id("Edit-quesPaperReqOptionInInitTraining")));
//			Thread.sleep(1000);
//			questions.selectByVisibleText("YES");
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Questions Yes/NO", sno, false);
//			Thread.sleep(2000);
//			sno++;
			driver.findElement(By.id("EditDialogSaveButton")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Save", sno, false);
			Thread.sleep(2000);
			sno++;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			driver.findElement(By.id("fieldTrainingFromDateInInitTraining")).sendKeys(sdf.format(date));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on From Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("fieldTrainingToDateInInitTraining")).sendKeys(sdf.format(date));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on End Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"stageWiseUserDetailInInitialSchedule\"]/div/table/thead/tr/th[1]/div/input"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Workflow stage", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.linkText("Finish")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.xpath("/html/body/div[49]/div[3]/div/button[1]/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes", sno, false);
			Thread.sleep(2000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(1000);
			sno++;
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK", sno, false);
			driver.findElement(By.className("modal-btn")).click();// Modal Ok button
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		} else {
			System.out.println("User is not Selected");
		}
	}

	private boolean selectMultiItemDetails(String multiDocNoForItem, boolean isRecordSelectedForMultiItems, int count2)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("MultiItemTableContainer"));// schedule plan approve table
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((multiDocNoForItem == null) || ("".equalsIgnoreCase(multiDocNoForItem)))) {
				multiDocNoForItem = driver
						.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// schedule plan name
			} else if ((multiDocNoForItem == null) || ("".equalsIgnoreCase(multiDocNoForItem))) {
				multiDocNoForItem = driver
						.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// schedule plan name
			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String schPlanName = driver
								.findElement(By.xpath(
										"//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[" + i + "]/td[3]"))
								.getText();// schedule plan name
						if (multiDocNoForItem.contains(schPlanName)) {
							driver.findElement(By.xpath(
									"//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForMultiItems = true;
							break;
						}
					}
					if (isRecordSelectedForMultiItems) {
						break;
					}
				} else {
					String schPlanName = driver
							.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (multiDocNoForItem.contains(schPlanName)) {
						driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForMultiItems = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForMultiItems) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in schedule Plan
																						// approve list
					Thread.sleep(6000);
					table = driver.findElement(By.id("MultiItemTableContainer"));// schedule Plan approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}

			}
		}
		return isRecordSelectedForMultiItems;
	}
}
