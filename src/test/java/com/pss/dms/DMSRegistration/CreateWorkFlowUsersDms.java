/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.DMSRegistration;

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

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jeevan
 */
public class CreateWorkFlowUsersDms extends QMSLoginDetails {

	@Test
	public void toDoCreateWorkFlowDms() throws Exception {
		Thread.sleep(1000);
		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateWorkFlowUsersDms"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CreateWorkFlowUsersDms", "PSS-DMS-012", "Pass");
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
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='dmsWorkFlowUsersPage.do']")));
		sno++;
		driver.findElement(By.cssSelector("a[href='dmsWorkFlowUsersPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin menu", sno, false);
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}

		Thread.sleep(5000);
		methodToDoDmsWorkFlowCreation();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodToDoDmsWorkFlowCreation() throws Exception {
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Location", sno, false);
		driver.findElement(By.id("regWorkFlowSelect")).click();
		Thread.sleep(3000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select Location", sno, false);
		driver.findElement(By.id("treeContainer_2_span")).click();
		Thread.sleep(3000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select Button", sno, false);
		driver.findElement(By.id("selectBtnInDocumentTreeSelect")).click();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
//         jse.executeScript("window.scrollBy(0,250)");
		String DhodE_Codes = properties.getProperty("DmsdepartmentreviewerE_Codes");
		String DhodEcodes[] = DhodE_Codes.split(",");
		for (int i = 0; i < DhodEcodes.length; i++) {
			driver.findElement(By.id("workFlowUserBrowse1Button")).click();
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on browse Button", sno, false);
			Thread.sleep(3000);
			driver.findElement(By.id("locationTreeForApproverMultiSelect_2_a")).click();
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Location", sno, false);
			Thread.sleep(1000);
			String DhodEcode = DhodEcodes[i].trim();
			boolean isrecordSelectedForUser = false;
			int count3 = 0;
			isrecordSelectedForUser = selectingTheUser(DhodEcode, isrecordSelectedForUser, count3);
			Thread.sleep(1000);
			if (isrecordSelectedForUser) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select Button", sno,
						false);
				driver.findElement(By.id("selectBrowse1Button")).click();
				Thread.sleep(3000);
//         if(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")).isDisplayed())
//         {
//         Actions action = new Actions(driver);
//         action.sendKeys(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")), Keys.ENTER).build().perform();
//    	Thread.sleep(2000);
//         }
			} else {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on cancel Button", sno,
						false);
				driver.findElement(By.id("cancelBrowse1Button")).click();
				System.out.println("E-Code -" + DhodEcode + "Not Selected");
			}
		}
		Thread.sleep(3000);
		String QMSCoordinatorE_Codes = properties.getProperty("DmsQMSCoordinatorE_Codes");
		String QMSCoordinatorEcodes[] = QMSCoordinatorE_Codes.split(",");
		for (int i = 0; i < QMSCoordinatorEcodes.length; i++) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on browse Button", sno, false);
			driver.findElement(By.id("workFlowUserBrowse2Button")).click();
			Thread.sleep(3000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on location Button", sno, false);
			driver.findElement(By.id("locationTreeForApproverMultiSelect_2_a")).click();
			Thread.sleep(1000);
			String QMSCoordinatorEcode = QMSCoordinatorEcodes[i].trim();
			boolean isrecordSelectedForUser = false;
			int count3 = 0;
			isrecordSelectedForUser = selectingTheUser(QMSCoordinatorEcode, isrecordSelectedForUser, count3);
			Thread.sleep(1000);
			if (isrecordSelectedForUser) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select Button", sno,
						false);
				driver.findElement(By.id("selectBrowse1Button")).click();
				Thread.sleep(1000);
//         if(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")).isDisplayed())
//         {
//         Actions action = new Actions(driver);
//         action.sendKeys(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")), Keys.ENTER).build().perform();
//    	Thread.sleep(2000);
//         }
			} else {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on cancel Button", sno,
						false);
				driver.findElement(By.id("cancelBrowse1Button")).click();
				System.out.println("E-Code -" + QMSCoordinatorEcode + "Not Selected");
			}
		}

		jse.executeScript("window.scrollBy(0,250)");
		Thread.sleep(3000);
		String CFTE_Codes = properties.getProperty("DmsCFTReviewE_Codes");
		String CFTECodes[] = CFTE_Codes.split(",");
		for (int i = 0; i < CFTECodes.length; i++) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on browse Button", sno, false);
			driver.findElement(By.id("workFlowUserBrowse3Button")).click();
			Thread.sleep(3000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on location Button", sno, false);
			driver.findElement(By.id("locationTreeForApproverMultiSelect_2_a")).click();
			Thread.sleep(1000);
			String CFTEcode = CFTECodes[i].trim();
			boolean isrecordSelectedForUser = false;
			int count3 = 0;
			isrecordSelectedForUser = selectingTheUser(CFTEcode, isrecordSelectedForUser, count3);
			Thread.sleep(1000);
			if (isrecordSelectedForUser) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select Button", sno,
						false);
				driver.findElement(By.id("selectBrowse1Button")).click();
				Thread.sleep(1000);
//         if(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")).isDisplayed())
//         {
//         Actions action = new Actions(driver);
//         action.sendKeys(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")), Keys.ENTER).build().perform();
//    	Thread.sleep(2000);
//         }
			} else {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on cancel Button", sno,
						false);
				driver.findElement(By.id("cancelBrowse1Button")).click();
				System.out.println("E-Code -" + CFTEcode + "Not Selected");
			}
		}

		Thread.sleep(3000);
		String QAHODE_Codes = properties.getProperty("DmsQAHODE_Codes");
		String QAHODECodes[] = QAHODE_Codes.split(",");
		for (int i = 0; i < QAHODECodes.length; i++) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on browse Button", sno, false);
			driver.findElement(By.id("workFlowUserBrowse4Button")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("locationTreeForApproverMultiSelect_2_a")).click();
			Thread.sleep(1000);
			String QAHODECode = QAHODECodes[i].trim();
			boolean isrecordSelectedForUser = false;
			int count3 = 0;
			isrecordSelectedForUser = selectingTheUser(QAHODECode, isrecordSelectedForUser, count3);
			Thread.sleep(1000);
			if (isrecordSelectedForUser) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select Button", sno,
						false);
				driver.findElement(By.id("selectBrowse1Button")).click();
				Thread.sleep(1000);
//         if(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")).isDisplayed())
//         {
//         Actions action = new Actions(driver);
//         action.sendKeys(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")), Keys.ENTER).build().perform();
//    	Thread.sleep(2000);
//         }
			} else {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on cancel Button", sno,
						false);
				driver.findElement(By.id("cancelBrowse1Button")).click();
				System.out.println("E-Code -" + QAHODECode + "Not Selected");
			}
		}

		jse.executeScript("window.scrollBy(0,300)");
		Thread.sleep(3000);
		String CQAE_Codes = properties.getProperty("CQAApproverE_Codes");
		String CQAECodes[] = CQAE_Codes.split(",");
		for (int i = 0; i < CQAECodes.length; i++) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on browse Button", sno, false);
			driver.findElement(By.id("workFlowUserBrowse5Button")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("locationTreeForApproverMultiSelect_2_a")).click();
			Thread.sleep(1000);
			String CQAECode = CQAECodes[i].trim();
			boolean isrecordSelectedForUser = false;
			int count3 = 0;
			isrecordSelectedForUser = selectingTheUser(CQAECode, isrecordSelectedForUser, count3);
			Thread.sleep(1000);
			if (isrecordSelectedForUser) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select Button", sno,
						false);
				driver.findElement(By.id("selectBrowse1Button")).click();
				Thread.sleep(1000);
//         if(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")).isDisplayed())
//         {
//         Actions action = new Actions(driver);
//         action.sendKeys(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")), Keys.ENTER).build().perform();
//    	Thread.sleep(2000);
//         }
			} else {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on cancel Button", sno,
						false);
				driver.findElement(By.id("cancelBrowse1Button")).click();
				System.out.println("E-Code -" + CQAECode + "Not Selected");
			}
		}
		jse.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit Button", sno, false);
		driver.findElement(By.id("dmsWfSubBtnId")).click();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "enter E-sign Passwprd", sno, false);
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
		Thread.sleep(1000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit Button", sno, false);
		driver.findElement(By.id("subBtnInValidateESign")).click();
		Thread.sleep(3000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK Button", sno, false);
		driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.className("username")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

	}

	private boolean selectingTheUser(String DhodEcode, boolean isrecordSelectedForUser, int count3)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("approversTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
			if (((DhodEcode == null) || ("".equalsIgnoreCase(DhodEcode)))) {
				DhodEcode = driver
						.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((DhodEcode == null) || ("".equalsIgnoreCase(DhodEcode))) {
				DhodEcode = driver
						.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType

			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DhodEcodeSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
//                       System.out.println("DhodEcodeSequence: "+DhodEcodeSequence);
						if (DhodEcode.equalsIgnoreCase(DhodEcodeSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
									.click();
							isrecordSelectedForUser = true;
							break;
						}
					}
					if (isrecordSelectedForUser) {
						break;
					}

				} else {
					String DhodEcodeSequence = driver
							.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (DhodEcode.equalsIgnoreCase(DhodEcodeSequence)) {
						driver.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[4]"))
								.click();
						isrecordSelectedForUser = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isrecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#approversTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("approversTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isrecordSelectedForUser;
	}

}
