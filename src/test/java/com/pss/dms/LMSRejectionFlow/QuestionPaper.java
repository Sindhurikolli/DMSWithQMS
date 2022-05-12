package com.pss.dms.LMSRejectionFlow;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class QuestionPaper extends QMSLoginDetails {

	@Test
	public void toQuestionPaper() throws Throwable {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QuestionPaper" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Question Paper", "PSS-LMS-007", "Pass");
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
		Thread.sleep(5000);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("window.scrollBy(0,100)");
//		WebElement element1 = driver.findElement(By.xpath("//*[@id=\"reportsTabInCal\"]/div/div[1]/strong"));
//		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		driver.findElement(By.id("questionPaperListCount")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Question Paper", sno, false);
		Thread.sleep(4000);
		methodToAnswerQuestions();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodToAnswerQuestions() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("ADHOC_SCHEDULE_NAME");
		isRecordSelected = selectRecord(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record and Click on View", sno,
					false);
			Thread.sleep(4000);
			WebDriverWait wait1 = new WebDriverWait(driver, 240);
			wait1.until(ExpectedConditions.elementToBeClickable((By.id("submitButtonInQuestionPaper"))));
			try {
				if (driver.findElement(By.id("TFQ1AnswerInQuestionVeiwForm")).isDisplayed()) {
					sno++;
					Select ques1 = new Select(driver.findElement(By.id("TFQ1AnswerInQuestionVeiwForm")));
					Thread.sleep(1000);
					ques1.selectByIndex(1);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select True/False", sno, false);
				}
			} catch (Exception e) {
				System.out.println("True or False Question is not Assigned to the User");

			}

			try {
				if (driver.findElement(By.id("MCQ1ChoiceRadioButton1")).isEnabled()) {
					Thread.sleep(2000);
					JavascriptExecutor jse11 = (JavascriptExecutor) driver;
					WebElement element11 = driver.findElement(By.id("MCQ1ChoiceRadioButton1"));
					jse11.executeScript("arguments[0].scrollIntoView(true);", element11);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.id("MCQ1ChoiceRadioButton1")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Multiple Choice", sno,
							false);
					Thread.sleep(2000);
				}
			} catch (Exception e) {
				System.out.println("Multiple Choice Question is not Assigned to the User");

			}
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.id("submitButtonInQuestionPaper"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(2000);
			try {
				if (driver.findElement(By.id("FIB1InQuestionVeiwForm2")).isDisplayed()) {
					sno++;
					driver.findElement(By.id("FIB1InQuestionVeiwForm2")).sendKeys("Document Management System");
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
							"Enter Answer For Fill in the Blanks", sno, false);
				}
			} catch (Exception e) {
				System.out.println("Fill in the Blank Question is not Assigned to the User");

			}
			Thread.sleep(2000);
//			driver.findElement(By.id("FIB2InQuestionVeiwForm2")).sendKeys("Modi");
//			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("submitButtonInQuestionPaper")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK", sno, false);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();

			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);
		}
	}

	private boolean selectRecord(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("questionPaperJtable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"questionPaperJtable\"]/div/div[4]/div[2]/span"))
					.getText();
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
				name = driver.findElement(By.xpath("//*[@id=\"questionPaperJtable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// Doc No
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"questionPaperJtable\"]/div/table/tbody/tr/td[3]"))
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
								.findElement(By.xpath(
										"//*[@id=\"questionPaperJtable\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// Doc No
						if (name.contains(documentNumber)) {
							driver.findElement(By.xpath(
									"//*[@id=\"questionPaperJtable\"]/div/table/tbody/tr[" + i + "]/td[11]/button"))
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
							.findElement(By.xpath("//*[@id=\"questionPaperJtable\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (name.contains(documentNumber)) {
						driver.findElement(
								By.xpath("//*[@id=\"questionPaperJtable\"]/div/table/tbody/tr/td[11]/button")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#questionPaperJtable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve
					// list
					Thread.sleep(4000);
					table = driver.findElement(By.id("questionPaperJtable"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
