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
public class CreateTopicQuestions extends QMSLoginDetails {

	@Test
	public void toCeateTopicQuestions() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateTopicQuestions"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CreateTopicQuestions", "PSS-LMS-004", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.get(properties.getProperty("LMSURL"));
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
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class='badge text-center']")));

		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("createQuestionBank"));
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
//		driver.findElement(By.id("createQuestionBank")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Assign Item Questions", sno,
				false);
		Thread.sleep(4000);
		methodToCreateTopicQuestions();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

	}

	private void methodToCreateTopicQuestions() throws Exception {

		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable((By.id("itemSelBtnInItemQues"))));
		sno++;
		driver.findElement(By.id("itemSelBtnInItemQues")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		wait1.until(ExpectedConditions.elementToBeClickable((By.id("multi_item-tree_2_span"))));
		sno++;
		driver.findElement(By.id("multi_item-tree_2_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#MultiItemTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		String number = properties.getProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP");
		String name = number + "-" + properties.getProperty("LMSDocumentVersion");
		
		driver.findElement(By.id("itemName")).sendKeys(properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST"));
		driver.findElement(By.id("itemsSearchBtnInTopicSelection")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#MultiItemTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		int count = 0;
		boolean isRecordSelected = false;
		System.out.println(name);
		Thread.sleep(5000);
		isRecordSelected = selectRecordForTest(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			wait1.until(ExpectedConditions.elementToBeClickable((By.id("selBtnInItems"))));
			sno++;
			driver.findElement(By.id("selBtnInItems")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			float lmsVersionNumber = Float.parseFloat(properties.getProperty("LMSDocumentVersion"));
			if (lmsVersionNumber > 1.0) {
				driver.findElement(By.id("nameInItemQuestion")).sendKeys(properties.getProperty("ChangeDoc_QtionBank"));
				driver.findElement(By.id("nameInItemQuestion"))
						.sendKeys(properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST"));
			} else {
				driver.findElement(By.id("nameInItemQuestion"))
						.sendKeys(properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST"));
			}
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("keyWordInItermQues")).sendKeys(properties.getProperty("TOPIC_GROUP_NAME"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Keywords", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"trueFlaseQuesJtalbe\"]/div/div[3]/div[2]/span/span[2]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Question", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("questionInTFQWin")).sendKeys("QMS Means?");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Question", sno, false);
			Thread.sleep(2000);
			sno++;
			Select que = new Select(driver.findElement(By.id("answerInTFQWin")));
			Thread.sleep(2000);
			que.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Answer", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("marksInTFQWin")).sendKeys("5");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Marks", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selBtnInTrueFalseDailog")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"MultipleChoiceQuesJTable\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Question", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("questionInMCQWin")).sendKeys("DMS Means");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Question", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("choice1InMCQWin")).sendKeys("Document Management System");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Choice1", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("choice2InMCQWin")).sendKeys("Learning");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Choice2", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("choice3InMCQWin")).sendKeys("CALPM");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Choice3", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("choice4InMCQWin")).sendKeys("Quality");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Choice4", sno, false);
			Thread.sleep(2000);
			sno++;
			Select choice = new Select(driver.findElement(By.id("answerInMCQWin")));
			Thread.sleep(1000);
			choice.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Answer", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("marksInMCQWin")).sendKeys("3");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Marks", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selBtnInMulChoiceDialog")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"fillInTheBlanksJTable\"]/div/div[3]/div[2]/span/span[2]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Question", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("quesBeforeBlankInFITBQWin")).sendKeys("DMS");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Question Before Blank", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("quesAfterBlankInFITBQWin")).sendKeys("Means");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Question After Blank", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("answerInFITBQWin")).sendKeys("Document Management System");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Answer", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("marksInFITBQWin")).sendKeys("2");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Marks", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selBtnInFillBalnkDialog")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)");
			sno++;
			driver.findElement(By.id("subBtnInItemQueation")).click();
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
			WebDriverWait wait2 = new WebDriverWait(driver, 60);
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
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
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordForTest(int count, boolean isRecordSelected, String name) throws Exception {
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
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// Doc No
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// Doc No
			} // *[@id="MultiItemTableContainer"]/div/table/tbody/tr/td[3]
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String documentNumber = driver
								.findElement(By.xpath(
										"//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[" + i + "]/td[3]"))
								.getText();// Doc No
						if (name.equalsIgnoreCase(documentNumber)) {
							driver.findElement(By
									.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[" + i + "]/td[3]"))
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
							.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name.equalsIgnoreCase(documentNumber)) {
						driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Document approve
					Thread.sleep(4000);
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
							"#MultiItemTableContainer > div > div.jtable-busy-message[style='display: none;']")));
					Thread.sleep(2000);
					table = driver.findElement(By.id("MultiItemTableContainer"));// Document approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
