package com.pss.dms.LMSFWD;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

//@Listeners(com.pss.dms.Listerners.TestListener.class)
public class CreateTopic extends QMSLoginDetails {

	@Test
	public void toCreateTopic() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateTopic" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Topic", "PSS-LMS-003", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("LMSURL"));
		Thread.sleep(1000);
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
		Thread.sleep(5000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.id("createItem")));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create Topic", sno, false);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("createItem"));
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
//		driver.findElement(By.id("createItem")).click();
		
		Thread.sleep(4000);
		methodToCreateTopic();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodToCreateTopic() throws Exception {

		WebDriverWait wait1 = new WebDriverWait(driver, 240);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("maskingId")));
		wait1.until(ExpectedConditions.elementToBeClickable((By.id("locSelBtnInItermReg"))));
		sno++;
		driver.findElement(By.id("locSelBtnInItermReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		wait1.until(ExpectedConditions.elementToBeClickable((By.id("locSelTree_2_span"))));
		sno++;
		driver.findElement(By.id("locSelTree_2_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		wait1.until(ExpectedConditions.elementToBeClickable((By.id("selBtnInLocSelWin"))));
		sno++;
		driver.findElement(By.id("selBtnInLocSelWin")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		
		sno++;
		driver.findElement(By.id("docNameInItemReg")).click();
		driver.findElement(By.id("docNameInItemReg")).sendKeys(properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Document name", sno, false);
		Thread.sleep(3000);
		
		sno++;
		driver.findElement(By.id("searchBtnInItermReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		Thread.sleep(5000);
		WebDriverWait wai = new WebDriverWait(driver, 60);
		wai.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#newDocDetailsTable > div > div.jtable-busy-message[style='display: none;']")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.className("jtable-page-info"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
		String versionNumber = "0";
		String ChangeDocVerNo = getVersionNumber(count, isRecordSelected, name, versionNumber);
		System.out.println(ChangeDocVerNo);
		Thread.sleep(1000);
		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src/test/java/com/pss/dms/properties/DMSProperties.properties");
		properties.setProperty("LMSDocumentVersion", ChangeDocVerNo);
		properties.save();
		Thread.sleep(2000);
		isRecordSelected = selectRecordForDocument(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Select a Record and Click on Create Topic", sno, false);
			Thread.sleep(3000);

			sno++;
			driver.findElement(By.id("readDurationInItemRegWin")).sendKeys("2");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reading Duration", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInItermRegWindow")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys((String) properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK", sno, false);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(2000);
			sno++;
			// driver.findElement(By.className("username")).click();
			Actions action = new Actions(driver);
			WebElement eUsername = driver.findElement(By.className("username"));
			action.moveToElement(eUsername);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
			Thread.sleep(3000);
			sno++;

			// driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordForDocument(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("newDocDetailsTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver.findElement(By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// Doc No
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr/td[4]"))
						.getText();// Doc No
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentNumber = driver
								.findElement(
										By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr[" + i + "]/td[4]"))
								.getText();// Doc No
						if (name.equalsIgnoreCase(documentNumber)) {
							driver.findElement(By.xpath(
									"//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr[" + i + "]/td[14]/button"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String documentNumber = driver
							.findElement(By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (name.equalsIgnoreCase(documentNumber)) {
						driver.findElement(By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr/td[14]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(8000);
					table = driver.findElement(By.id("newDocDetailsTable"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	private String getVersionNumber(int count, boolean isRecordSelected, String name, String versionNumber)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("newDocDetailsTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver.findElement(By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// Doc No
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr/td[4]"))
						.getText();// Doc No
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentNumber = driver
								.findElement(
										By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr[" + i + "]/td[4]"))
								.getText();// Doc No
						if (name.equalsIgnoreCase(documentNumber)) {
							versionNumber = driver
									.findElement(By.xpath(
											"//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr[" + i + "]/td[9]"))
									.getText();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String documentNumber = driver
							.findElement(By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (name.equalsIgnoreCase(documentNumber)) {
						versionNumber = driver
								.findElement(By.xpath("//*[@id=\"newDocDetailsTable\"]/div/table/tbody/tr/td[9]"))
								.getText();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
																						// list
					Thread.sleep(8000);
					table = driver.findElement(By.id("newDocDetailsTable"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return versionNumber;
	}

}
