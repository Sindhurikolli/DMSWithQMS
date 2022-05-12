/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.LMSAdmin;

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
import com.pss.qms.ExtentTestNGPkg.Utility;

import org.apache.commons.lang.ArrayUtils;
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
import static org.testng.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jeevan
 */
public class DmsUserGroup extends QMSLoginDetails {

	@Test
	public void DmsUserGroupMethod() throws Exception {
		Thread.sleep(1000);
		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DmsUserGroup" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("DmsUserGroup", "PSS-DMS-012", "Pass");
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
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='DmsRolesRegForm.do']")));
		sno++;
		driver.findElement(By.cssSelector("a[href='DmsRolesRegForm.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin menu", sno, false);
		Thread.sleep(2000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		driver.findElement(By.cssSelector("a[href='CreateUserGroup.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Document Tree", sno, false);
		sno++;
		Thread.sleep(5000);
		todoDmsUserGroupMethod();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
	}

	private void todoDmsUserGroupMethod() throws Exception {

		WebElement element5 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		JavascriptExecutor js5 = (JavascriptExecutor) driver;
		js5.executeScript("arguments[0].click();", element5);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.id("nameInDmsGroupRegForm")).sendKeys(properties.getProperty("DMSUserGroupName"));
		Thread.sleep(2000);
		driver.findElement(By.id("descInDmsGroupRegForm")).sendKeys(properties.getProperty("DMSUserGroupDescription"));
		Thread.sleep(2000);
		driver.findElement(By.id("locSelInCreateUserGrp")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText(properties.getProperty("DMSUserGroupLocation"))).click();
		Thread.sleep(2000);
		driver.findElement(By.id("selectBtnInLocSelect")).click();
		Thread.sleep(2000);
		String UsersE_Codes = properties.getProperty("DmsUserGroupUsers");
		String[] Userarray = UsersE_Codes.split(",");
		for (int i = 0; i < Userarray.length; i++) {

			Thread.sleep(4000);
			String DmsUser = Userarray[i].trim();
			System.out.println(DmsUser);
			Select Usertoselect = new Select(driver.findElement(By.id("bootstrap-duallistbox-nonselected-list_")));
			List<WebElement> allOptionstoselect = Usertoselect.getOptions();
			int jSize = allOptionstoselect.size();
			String[] arrrolestoselect = new String[jSize];
			Thread.sleep(3000);
			for (int j = 0; j < jSize; j++) {
				arrrolestoselect[j] = allOptionstoselect.get(j).getText();
			}
			if (ArrayUtils.contains(arrrolestoselect, DmsUser)) {
				Usertoselect.selectByVisibleText(DmsUser);
				Thread.sleep(2000);
			}

		}
		WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

		Thread.sleep(1000);
//            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
		sno++;
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signatue", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(1000);
		sno++;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
		Thread.sleep(1000);
		driver.findElement(By.className("modal-btn")).click();
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.className("username")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
	}

	private boolean selectingTheUser(String UserEcode, boolean recordSelectedForUser, int count3)
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
			if (((UserEcode == null) || ("".equalsIgnoreCase(UserEcode)))) {
				UserEcode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((UserEcode == null) || ("".equalsIgnoreCase(UserEcode))) {
				UserEcode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]"))
						.getText();// documentType

			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String UserEcodeSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();// documentTypeName
						System.out.println("UserEcodeSequence: " + UserEcodeSequence);
						if (UserEcode.equalsIgnoreCase(UserEcodeSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
									.click();
							recordSelectedForUser = true;
							break;
						}
					}

				} else {
					String UserEcodeSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (UserEcode.equalsIgnoreCase(UserEcodeSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]"))
								.click();
						recordSelectedForUser = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!recordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
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
		return recordSelectedForUser;
	}

	@AfterMethod
	public void testIT(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
