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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
public class LmsPrivileges extends QMSLoginDetails {

	@Test
	public void toDoLmsPrivileges() throws Exception {
		Thread.sleep(1000);
		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "LmsPrivileges" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("LmsPrivileges", "PSS-DMS-012", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("LMSURL"));
		Thread.sleep(4000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));

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
// if(driver.findElement(By.id("NotificationTableContainer")).isDisplayed())
//	{
//		System.out.println("Notification Window Displayed");
//		Actions action = new Actions(driver);
//		action.sendKeys(Keys.ESCAPE).perform();
//	}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,2000)");

// WebDriverWait wait1 = new WebDriverWait(driver, 60);
// wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='AdminAssignRolesPage.do']")));    
		sno++;
		driver.findElement(By.xpath("//*[@id=\"schedulerTabInCal\"]/div/div[2]/a[1]/div")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin menu", sno, false);
		Thread.sleep(2000);
//if(driver.findElement(By.id("NotificationTableContainer")).isDisplayed())
//	{
//		System.out.println("Notification Window Displayed");
//		Actions action = new Actions(driver);
//		action.sendKeys(Keys.ESCAPE).perform();
//	}
		driver.findElement(By.cssSelector("a[href='PrivilegesAdminPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Privileges", sno, false);
		sno++;
		Thread.sleep(3000);
		methodToDoLmsPrivileges();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
	}

	private void methodToDoLmsPrivileges() throws Exception {
		driver.findElement(By.id("selectUserByPrivilegesAdminForm")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("userSelTree_1_span")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("firstName")).sendKeys(properties.getProperty("LMS_First_Name"));
		driver.findElement(By.id("lastName")).sendKeys(properties.getProperty("LMS_Last_Name"));
		Thread.sleep(2000);
		driver.findElement(By.id("usersSearchBtnId")).click();
		boolean isRecordSelectedForUsers = false;
		String singleApprovalName = properties.getProperty("LMS_First_Name");
		String lastName = properties.getProperty("LMS_Last_Name");
		int count = 0;
		Thread.sleep(1000);
		isRecordSelectedForUsers = selectuserforprivilage(singleApprovalName, isRecordSelectedForUsers, count,
				lastName);
		Thread.sleep(1000);
		if (isRecordSelectedForUsers) {
			driver.findElement(By.id("selBtnInUsers")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button", sno, false);
			sno++;
			Thread.sleep(5000);
			String AccessLocations = properties.getProperty("AccessLocation");
			String[] Accessarray = AccessLocations.split(",");
			for (int i = 0; i < Accessarray.length; i++) {
				driver.findElement(By.id("selectAccessLevelInPrivilegesAdmin")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,
						false);
				sno++;
				Thread.sleep(3000);
				driver.findElement(By.id("locSelTree_2_switch")).click();
				Thread.sleep(3000);
				String Location = Accessarray[i].trim();
				driver.findElement(By.linkText(Location)).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Folder", sno, false);
				sno++;
				Thread.sleep(3000);
				driver.findElement(By.id("selBtnInLocSelWin")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button", sno,
						false);
				sno++;
				Thread.sleep(5000);
//          if(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")).isDisplayed())
//          {
//           	String AlertMessage = driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText();
//           	System.out.println(AlertMessage + "-"+ Location);
//           	Thread.sleep(1000);
//           	Actions action = new Actions(driver);
//            action.sendKeys(driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[3]")), Keys.ENTER).build().perform();
//       	Thread.sleep(2000);
//           
//           }

			}
			Thread.sleep(3000);
			WebElement submit = driver.findElement(By.id("submitInPrivilegesAdmin"));
			JavascriptExecutor jslogout = (JavascriptExecutor) driver;
			jslogout.executeScript("arguments[0].click();", submit);

//            driver.findElement(By.id("submitInPrivilegesAdmin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			sno++;
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
//            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signatue", sno,false);
//            Thread.sleep(1000);
//            sno++;
//            driver.findElement(By.id("subBtnInValidateESign")).click();
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno,false);
//            Thread.sleep(1000);
//            sno++;
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(1000);

		} else {
			driver.findElement(By.id("cancelBtnInAppSelectDailog")).click();
			Thread.sleep(1000);
		}
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[3]/a/span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[3]/ul/li[4]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
	}

	private boolean selectuserforprivilage(String singleApprovalName, boolean isRecordSelectedForUsers, int count,
			String lastName) throws InterruptedException {
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
			} // *[@id="usersTableContainer"]/div/table/tbody/tr[1]/td[2]
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((singleApprovalName == null) || ("".equalsIgnoreCase(singleApprovalName)))
					&& ((lastName == null) || ("".equalsIgnoreCase(lastName)))) {
				singleApprovalName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// singleApprovalName
				lastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// lastName
			} else if ((singleApprovalName == null) || ("".equalsIgnoreCase(singleApprovalName))) {
				singleApprovalName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();// singleApprovalName
				lastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// lastName
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String singleApprovalNameApproval = driver
								.findElement(By
										.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[1]"))
								.getText();// singleApprovalName
						String lastNameInApproval = driver
								.findElement(By
										.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// lastName
						if ((singleApprovalName.equalsIgnoreCase(singleApprovalNameApproval))
								&& (lastName.equalsIgnoreCase(lastNameInApproval))) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[1]"))
									.click();
							isRecordSelectedForUsers = true;
							break;
						}
					}
					if (isRecordSelectedForUsers) {
						break;
					}
				} else {
					String singleApprovalNameApproval = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]"))
							.getText();
					String lastNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();// lastName
					if ((singleApprovalName.equalsIgnoreCase(singleApprovalNameApproval))
							&& (lastName.equalsIgnoreCase(lastNameInApproval))) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]"))
								.click();
						isRecordSelectedForUsers = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForUsers) && (noOfRecordsChecked < totalNoOfRecords)) {
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
		return isRecordSelectedForUsers;
	}

}
