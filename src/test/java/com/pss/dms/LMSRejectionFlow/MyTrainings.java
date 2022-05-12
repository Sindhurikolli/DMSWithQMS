package com.pss.dms.LMSRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

public class MyTrainings extends QMSLoginDetails {

	@Test
	public void toMyTrainings() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "MyTrainings" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("MY TRAININGS", "PSS-LMS-005", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("LMSURL"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("LMSUser"));
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
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("window.scrollBy(0,300)");
//		WebElement element1 = driver.findElement(
//				By.cssSelector("#reportsTabInCal > div > div.panel-heading.text-center.headerText.workFlow > strong"));
//		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		driver.findElement(By.id("trainingsList")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Trainings", sno, false);
		Thread.sleep(4000);
		methodForExternamMyTraining();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodForExternamMyTraining() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("ADHOC_SCHEDULE_NAME");
		isRecordSelected = selectRecord(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record ", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("trainingMaterialInMytrainings")).click();
			Thread.sleep(3000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on ", sno, false);

			String mainWindow = driver.getWindowHandle();
			System.out.println(driver.getTitle());
			System.out.println("main window is:" + mainWindow);
			// Thread.sleep(120000);
			Thread.sleep(10000);
			Set<String> set = driver.getWindowHandles();
			Iterator<String> itr = set.iterator();
			while (itr.hasNext()) {
				String childWindow = itr.next();

				System.out.println("child window is:" + childWindow);
				if (!mainWindow.equals(childWindow)) {

					driver.switchTo().window(childWindow);

					Thread.sleep(10000);
					// driver.close();
					sno++;
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on isCompleted?", sno,
							false); // driver.findElement //
					// driver.close();
//			Actions action = new Actions(driver); 
					try {
						if (driver.findElement(By.id("buttonInMyTrainings")).isDisplayed()) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							WebElement ele = driver.findElement(By.id("buttonInMyTrainings"));
							js.executeScript("arguments[0].click();", ele);
						}
					} catch (Exception e) {
						System.out.println("Button is not working");
					}
					Thread.sleep(10000);
//		    action.moveToElement(ele).click(); // WebDriverWait wait = new

					Thread.sleep(3000);
				}
			}

			driver.switchTo().window(mainWindow);

			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("closeItemDialogBoxInMyTrainings")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Close", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		}
	}

	private boolean selectRecord(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("LMSMyTrainingsJtable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"LMSMyTrainingsJtable\"]/div/div[4]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
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
				name = driver.findElement(By.xpath("//*[@id=\"LMSMyTrainingsJtable\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// Doc No
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"LMSMyTrainingsJtable\"]/div/table/tbody/tr/td[4]"))
						.getText();// Doc
				// No
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentNumber = driver
								.findElement(By
										.xpath("//*[@id=\"LMSMyTrainingsJtable\"]/div/table/tbody/tr[" + i + "]/td[4]"))
								.getText();// Doc No
						if (name.contains(documentNumber)) {
							Thread.sleep(2000);
//							driver.findElement(By.xpath(
//									"//*[@id=\"LMSMyTrainingsJtable\"]/div/table/tbody/tr[" + i + "]/td[19]/button"))
//									.click();
//							Thread.sleep(2000);
//							driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
							driver.findElement(By.xpath(
									"//*[@id=\"LMSMyTrainingsJtable\"]/div/table/tbody/tr[" + i + "]/td[14]/button"))
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
							.findElement(By.xpath("//*[@id=\"LMSMyTrainingsJtable\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (name.contains(documentNumber)) {
						Thread.sleep(2000);
//						driver.findElement(
//								By.xpath("//*[@id=\"LMSMyTrainingsJtable\"]/div/table/tbody/tr/td[19]/button")).click();
//						Thread.sleep(2000);
//						driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
						driver.findElement(
								By.xpath("//*[@id=\"LMSMyTrainingsJtable\"]/div/table/tbody/tr/td[14]/button")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#LMSMyTrainingsJtable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve
					// list
					Thread.sleep(4000);
					table = driver.findElement(By.id("LMSMyTrainingsJtable"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
