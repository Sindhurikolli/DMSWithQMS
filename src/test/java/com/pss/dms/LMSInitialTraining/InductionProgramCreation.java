package com.pss.dms.LMSInitialTraining;

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

public class InductionProgramCreation extends QMSLoginDetails {

	@Test
	public void createInductionProgram() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InductionProgram" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Induction Program", "PSS-LMS-002", "Pass");
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
//		WebElement element1 = driver.findElement(By.id("lmsInductProg"));
//		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		driver.findElement(By.id("lmsInductProg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Induction Program", sno, false);
		Thread.sleep(4000);
		methodToCreateInductionProgram();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodToCreateInductionProgram() throws Exception {

		sno++;
		driver.findElement(By.id("selUsersBtnInInductionProgram")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
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
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selDeptBtnInInductionProgram")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(8000);
			int count = 0;
			boolean isRecordSelected = false;
			String name = properties.getProperty("DeptforInduction");
			isRecordSelected = selectRecordForPlant(count, isRecordSelected, name);
			if (isRecordSelected) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(2000);
				JavascriptExecutor jse1 = (JavascriptExecutor) driver;
				WebElement element1 = driver.findElement(By.id("selectBtnInInductionProgramDeptWin"));
				jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("selectBtnInInductionProgramDeptWin")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.xpath("//*[@id=\"departmentListTable\"]/div/table/tbody/tr/td[4]/button"))
						.click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("Edit-messageToHODInLocationGridInLmsIndProgram"))
						.sendKeys(properties.getProperty("Message_ToHOD"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Message To HOD", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("EditDialogSaveButton")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Save", sno, false);
				Thread.sleep(2000);
				JavascriptExecutor jse11 = (JavascriptExecutor) driver;
				WebElement element11 = driver.findElement(By.id("submitBtnInInductionProgram"));
				jse11.executeScript("arguments[0].scrollIntoView(true);", element11);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("submitBtnInInductionProgram")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
				Thread.sleep(2000);
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password",
						sno, false);
				Thread.sleep(1000);
				sno++;
				driver.findElement(By.id("subBtnInValidateESign")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno,
						false);
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
				System.out.println("plant is not selected");
			}
		} else {
			System.out.println("User is not Selected");
		}

	}

	private boolean selectRecordForPlant(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("departmentDetailsTableInInduction"));// schedule plan approve table
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"schedulePlanApprovalTable\"]/div/div[4]/div[2]/span"))
//					.getText();// For Ex: Showing 1-1 of 1
//			String[] parts = a.split(" of ");
//			totalNoOfRecords = Integer.parseInt(parts[1].trim());
//		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver
						.findElement(
								By.xpath("//*[@id=\"departmentDetailsTableInInduction\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// schedule plan name
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver
						.findElement(
								By.xpath("//*[@id=\"departmentDetailsTableInInduction\"]/div/table/tbody/tr/td[2]"))
						.getText();// schedule plan name
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String schPlanName = driver.findElement(By.xpath(
								"//*[@id=\"departmentDetailsTableInInduction\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// schedule plan name
						if (name.contains(schPlanName)) {
							driver.findElement(
									By.xpath("//*[@id=\"departmentDetailsTableInInduction\"]/div/table/tbody/tr[ " + i
											+ " ]/td[2]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String schPlanName = driver
							.findElement(
									By.xpath("//*[@id=\"departmentDetailsTableInInduction\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name.contains(schPlanName)) {
						driver.findElement(
								By.xpath("//*[@id=\"departmentDetailsTableInInduction\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in schedule Plan
																						// approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("departmentDetailsTableInInduction"));// schedule Plan approve
																							// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}

			}
		}
		return isRecordSelected;
	}
}
