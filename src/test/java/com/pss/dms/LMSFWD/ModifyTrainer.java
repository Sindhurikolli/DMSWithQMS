package com.pss.dms.LMSFWD;

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
public class ModifyTrainer extends QMSLoginDetails {

	@Test
	public void toModifyTrainer() throws Exception {
		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ModifyTrainer" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Modify Trainer", "PSS-LMS-005", "Pass");
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
		Thread.sleep(3000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.id("lmsModifyTrainer")));

		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Modify Trainer", sno, false);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("lmsModifyTrainer"));
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
//		driver.findElement(By.id("lmsModifyTrainer")).click();
		
		Thread.sleep(4000);
		methodToModifyTrainer();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodToModifyTrainer() throws Exception {

		
		driver.findElement(By.id("locSelBtnInEditTrainerLms")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		WebDriverWait wait1 = new WebDriverWait(driver, 240);
		wait1.until(ExpectedConditions.elementToBeClickable((By.id("locSelTree_2_span"))));
		sno++;
		driver.findElement(By.id("locSelTree_2_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("selBtnInLocSelWin")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("searchInEditTrainerLms")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		Thread.sleep(6000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#EditTrainerFormTable > div > div.jtable-busy-message[style='display: none;']")));
		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("TrainerEcode");
		isRecordSelected = selectRecordForTrainer(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			wait1.until(ExpectedConditions.elementToBeClickable((By.id("editInModifyTrainerLms"))));
			sno++;
			driver.findElement(By.id("editInModifyTrainerLms")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit", sno, false);
			sno++;
			Thread.sleep(3000);
			driver.findElement(By.id("selectItamDetailsInTrainerEditLms")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			wait1.until(ExpectedConditions.elementToBeClickable((By.id("multi_item-tree_2_span"))));
			sno++;
			driver.findElement(By.id("multi_item-tree_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			Thread.sleep(7000);
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#MultiItemTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			sno++;
			driver.findElement(By.id("itemName")).click();
			driver.findElement(By.id("itemName")).sendKeys(properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Item Name", sno, false);
			Thread.sleep(3000);
			
			sno++;
			driver.findElement(By.id("itemsSearchBtnInTopicSelection")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#MultiItemTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			int count1 = 0;
			boolean isRecordSelected1 = false;
			String number = properties.getProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP");
			String name1 = number + "-" + properties.getProperty("LMSDocumentVersion");
			isRecordSelected1 = selectRecordForTest(count1, isRecordSelected1, name1);
			if (isRecordSelected1) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.xpath("//*[@id=\"selBtnInItems\"]")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(2000);
				JavascriptExecutor jse1 = (JavascriptExecutor) driver;
				WebElement element1 = driver.findElement(By.id("submitInTrainerEditId"));
				jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("submitInTrainerEditId")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
						false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("subBtnInValidateESign")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
				//WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
				Thread.sleep(2000);
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK", sno, false);
				driver.findElement(By.className("modal-btn")).click();
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.className("username")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

			} else {
				System.out.println("Test is not Selected");
			}
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordForTest(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("MultiItemTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			System.out.println("hi");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println("total record" + totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// Doc No
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// Doc No
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentNumber = driver
								.findElement(By.xpath(
										"//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[" + i + "]/td[3]"))
								.getText();// Doc No
						if (name1.equalsIgnoreCase(documentNumber)) {
							driver.findElement(By
									.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[" + i + "]/td[3]"))
									.click();
							Thread.sleep(3000);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String documentNumber = driver
							.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name1.equalsIgnoreCase(documentNumber)) {
						driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						Thread.sleep(3000);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#MultiItemTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve
					// list
					Thread.sleep(6000);
					table = driver.findElement(By.id("MultiItemTableContainer"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
	}

	private boolean selectRecordForTrainer(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("EditTrainerFormTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"EditTrainerFormTable\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			System.out.println("hi");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println("total record" + totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver.findElement(By.xpath("//*[@id=\"EditTrainerFormTable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// Doc No
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"EditTrainerFormTable\"]/div/table/tbody/tr/td[3]"))
						.getText();// Doc No
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentNumber = driver
								.findElement(By
										.xpath("//*[@id=\"EditTrainerFormTable\"]/div/table/tbody/tr[" + i + "]/td[3]"))
								.getText();// Doc No
						if (name.equalsIgnoreCase(documentNumber)) {
							driver.findElement(
									By.xpath("//*[@id=\"EditTrainerFormTable\"]/div/table/tbody/tr[" + i + "]/td[3]"))
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
							.findElement(By.xpath("//*[@id=\"EditTrainerFormTable\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name.equalsIgnoreCase(documentNumber)) {
						driver.findElement(By.xpath("//*[@id=\"EditTrainerFormTable\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#EditTrainerFormTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve
					// list
					Thread.sleep(4000);
					table = driver.findElement(By.id("EditTrainerFormTable"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
