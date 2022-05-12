package com.pss.dms.CCRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;
import com.pss.qms.ExtentTestNGPkg.Utility;

////@Listeners(com.pss.dms.Listerners.TestListener.class)
public class PostApprovalActionInitiateWith10ActionItemsand100Documents extends QMSLoginDetails {
	@Test
	public void toPostApprovalActionInitiateWith10ActionItemsand100Documents()
			throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/"
				+ "PostApprovalActionInitiateWith10ActionItemsand100Documents" + (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent(
				"PostApprovalActionInitiateWith10ActionItemsand100Documents", "PSS-QMS-0100", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("QMSLoginUrl"));
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CCQMScoordinator"));

		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		module.selectByIndex(2);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='loginform']/div[7]/button[1]")).click();

		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(
				sno + "." + "Enter the username, password Select Change Control Module and click on login button"
						+ Utilities.prepareSSNumber(sno),
				font));
		document.add(im);

		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		Thread.sleep(5000);
//        driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module",sno,false);
//        sno++;
//        Thread.sleep(12000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(16000);
		wait1.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ccPostAppActionsInitiatePage.do']")));
		driver.findElement(By.cssSelector("a[href='ccPostAppActionsInitiatePage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Post Approval Actions menu", sno,
				false);
		Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("#ccPostApprovalInitiateTable > div > div.jtable-busy-message[style='display: none;']")));
		methodToDoPostApprovalActionInitiateWith10ActionItemsand100Documents();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}

//}

	private void methodToDoPostApprovalActionInitiateWith10ActionItemsand100Documents() throws Exception {
//	    	Thread.sleep(22000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
		String CCNumber = properties.getProperty("chgCtrlId");
		Thread.sleep(4000);
		isRecordSelected = selectRecdPostApprovalActionInitiateWith10ActionItemsand100Documents(CCNumber,
				isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(5000);
		sno++;
		if (isRecordSelected) {

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);

			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(5000);

			// ac1

			for (int i = 1; i <= 5; i++) {
				if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
					driver.findElement(By.id("sessionStay")).click();
				}
				sno++;
				driver.findElement(By
						.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]"))
						.click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,
						false);
				Thread.sleep(3000);
				sno++;
				if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
					driver.findElement(By.id("sessionStay")).click();
				}
				driver.findElement(By.id("ccActionItemDepartment")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,
						false);
				Thread.sleep(3000);
				if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
					driver.findElement(By.id("sessionStay")).click();
				}
				driver.findElement(By.id("treeContainer_2_switch")).click();
				Thread.sleep(3000);
				// driver.findElement(By.id("treeContainer_3_span")).click();
				if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
					driver.findElement(By.id("sessionStay")).click();
				}
				driver.findElement(By.linkText(properties.getProperty("Location"))).click();
				Thread.sleep(3000);
				sno++;
				if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
					driver.findElement(By.id("sessionStay")).click();
				}
				driver.findElement(By.id("selectBtnInLocSelect")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
				Thread.sleep(3000);
				String aIdsc = "This is Action " + i + " Description";
				// driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_1"));
				if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
					driver.findElement(By.id("sessionStay")).click();
				}
				driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(aIdsc);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description",
						sno, false);
				Thread.sleep(3000);
				if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
					driver.findElement(By.id("sessionStay")).click();
				}
				driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
				Thread.sleep(3000);

				for (int j = 1; j <= 10; j++) {
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}

					if (!driver.findElement(By.id("addNewDocInCheckInCCRevInQaPrim")).isSelected())
						driver.findElement(By.id("addNewDocInCheckInCCRevInQaPrim")).click();

					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,
							false);
					Thread.sleep(3000);
					JavascriptExecutor scl = ((JavascriptExecutor) driver);
					scl.executeScript("window.scrollBy(0,300)");
//	 	           if(!driver.findElement(By.id("//*[@id=\"docDetailsTableNewInCcRev\"]/div/div[3]/div[2]/span[1]/span[2")).isDisplayed()); 
//	 	        	  JavascriptExecutor scl = ((JavascriptExecutor) driver);
//	 				    scl.executeScript("window.scrollBy(0,300)");
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}
					driver.findElement(
							By.xpath("//*[@id=\"docDetailsTableNewInCcRev\"]/div/div[3]/div[2]/span[1]/span[2]"))
							.click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New document button",
							sno, false);
					Thread.sleep(3000);
					String alphabet = "fghij";
					String s = RandomStringUtils.random(5, alphabet);

//	 	            String docName = "Sop Document_"+i+"_"+j;
					String docName = "SOP_" + s + "_" + i + "_" + j;
					sno++;
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}
					driver.findElement(By.id("docNameInAddNewDocDlg")).sendKeys(docName);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document name", sno,
							false);
					Thread.sleep(3000);
					sno++;
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}
					Select docType = new Select(driver.findElement(By.id("docTypeInAddNewDocDlg")));
					docType.selectByVisibleText(properties.getProperty("DocumentType"));
					Thread.sleep(2000);
					sno++;
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}
					driver.findElement(By.id("selectBtnForNewDocSelect")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,
							false);
					Thread.sleep(5000);
					sno++;
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}
					driver.findElement(By.id("docTreeInCCReview_2_switch")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,
							false);
					Thread.sleep(5000);
					sno++;
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}
					driver.findElement(By.id("docTreeInCCReview_3_span")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,
							false);
					Thread.sleep(3000);
					sno++;
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}
					driver.findElement(By.id("selBtnInCCRevDocTree")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,
							false);
					Thread.sleep(3000);
					sno++;
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}
					driver.findElement(
							By.xpath("//*[@id=\"existedActionItemDetailsInNewDocSelDialog\"]/div/table/tbody/tr[" + i
									+ " ]/td[4]"))
							.click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,
							false);
					Thread.sleep(3000);
					if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
						driver.findElement(By.id("sessionStay")).click();
					}
					driver.findElement(By.id("addDocBtnInAddNewDocDlg")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,
							false);
					Thread.sleep(3000);

				}
			}

//ac2

//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("ccActionItemDepartment")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_2_switch")).click();
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_3_span")).click();
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnInLocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_2"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            
////ac3
//	            
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("ccActionItemDepartment")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_2_switch")).click();
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_3_span")).click();
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnInLocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_3"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);       
//	            
////ac4
//	            
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("ccActionItemDepartment")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_2_switch")).click();
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_3_span")).click();
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnInLocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_4"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);         
//	            
////ac5
//	            
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("ccActionItemDepartment")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_2_switch")).click();
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_3_span")).click();
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnInLocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_5"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);            
////ac6
//	            
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("ccActionItemDepartment")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_2_switch")).click();
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_3_span")).click();
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnInLocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_6"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);            
//	            
////ac7
//	            
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("ccActionItemDepartment")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_2_switch")).click();
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_3_span")).click();
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnInLocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_7"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);          
//	            
//	            
////ac8
//	            
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("ccActionItemDepartment")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_2_switch")).click();
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_3_span")).click();
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnInLocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_8"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);            
//	            
////ac9
//	            
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("ccActionItemDepartment")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_2_switch")).click();
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_3_span")).click();
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnInLocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_9"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);            
//	            
//	            
////ac10
//	            
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("ccActionItemDepartment")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_2_switch")).click();
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("treeContainer_3_span")).click();
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnInLocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ACTION_ITEM_DESC_10"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);   
//	            
//	            
//	  //Doc1          
//	            
//	            driver.findElement(By.id("addNewDocInCheckInCCRevInQaPrim")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.xpath("//*[@id=\"docDetailsTableNewInCcRev\"]/div/div[3]/div[2]/span[1]/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New document button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("docNameInAddNewDocDlg")).sendKeys(properties.getProperty("DOCUMENT_NAME_1"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document name", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            Select docType = new Select(driver.findElement(By.id("docTypeInAddNewDocDlg")));
//	            docType.selectByVisibleText(properties.getProperty("DocumentType")); 
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnForNewDocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("docTreeInCCReview_2_switch")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("docTreeInCCReview_3_span")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selBtnInCCRevDocTree")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"existedActionItemDetailsInNewDocSelDialog\"]/div/table/tbody/tr[1]/td[4]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addDocBtnInAddNewDocDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            
//	            
// //Doc 2        
//	            
//	            driver.findElement(By.id("addNewDocInCheckInCCRevInQaPrim")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.xpath("//*[@id=\"docDetailsTableNewInCcRev\"]/div/div[3]/div[2]/span[1]/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New document button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("docNameInAddNewDocDlg")).sendKeys(properties.getProperty("DOCUMENT_NAME_1"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document name", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	           // Select docType = new Select(driver.findElement(By.id("docTypeInAddNewDocDlg")));
//	            docType.selectByVisibleText(properties.getProperty("DocumentType")); 
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnForNewDocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("docTreeInCCReview_2_switch")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("docTreeInCCReview_3_span")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selBtnInCCRevDocTree")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"existedActionItemDetailsInNewDocSelDialog\"]/div/table/tbody/tr[1]/td[4]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addDocBtnInAddNewDocDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            
//	            
////Doc 3        
//	            
//	            driver.findElement(By.id("addNewDocInCheckInCCRevInQaPrim")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.xpath("//*[@id=\"docDetailsTableNewInCcRev\"]/div/div[3]/div[2]/span[1]/span[2]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on New document button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("docNameInAddNewDocDlg")).sendKeys(properties.getProperty("DOCUMENT_NAME_1"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document name", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	           // Select docType = new Select(driver.findElement(By.id("docTypeInAddNewDocDlg")));
//	            docType.selectByVisibleText(properties.getProperty("DocumentType")); 
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("selectBtnForNewDocSelect")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("docTreeInCCReview_2_switch")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("docTreeInCCReview_3_span")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.id("selBtnInCCRevDocTree")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"existedActionItemDetailsInNewDocSelDialog\"]/div/table/tbody/tr[2]/td[4]")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addDocBtnInAddNewDocDlg")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//	            Thread.sleep(5000);
//    

			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on SUBMIT button", sno, false);
			Thread.sleep(5000);
			sno++;
//	            WebElement element7 = driver.findElement(By.id("eSignPwdInWnd"));
//	            js.executeScript("arguments[0].click();", element7);

			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(5000);
			sno++;
//	            WebElement element8 = driver.findElement(By.id("subBtnInValidateESign"));
//	            js.executeScript("arguments[0].click();", element8);

			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(5000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(5000);
//	            WebElement element9 = driver.findElement(By.id("modal-btn"));
//	            js.executeScript("arguments[0].click();", element9);

			driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdPostApprovalActionInitiateWith10ActionItemsand100Documents(String CCNumber,
			boolean isRecordSelected, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("ccPostApprovalInitiateTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"ccPostApprovalInitiateTable\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber)))) {
				CCNumber = driver
						.findElement(By.xpath("//*[@id=\"ccPostApprovalInitiateTable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber))) {
				CCNumber = driver
						.findElement(By.xpath("//*[@id=\"ccPostApprovalInitiateTable\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String CCNumberSequence = driver
								.findElement(By.xpath(
										".//*[@id='ccPostApprovalInitiateTable']/div/table/tbody/tr[ " + i + "]/td[6]"))
								.getText();// documentTypeName
						if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(By.xpath(
									".//*[@id='ccPostApprovalInitiateTable']/div/table/tbody/tr[ " + i + "]/td[6]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String CCNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"ccPostApprovalInitiateTable\"]/div/table/tbody/tr/td[6]"))
							.getText();
					if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"ccPostApprovalInitiateTable\"]/div/table/tbody/tr/td[6]")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#ccPostApprovalInitiateTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("ccPostApprovalInitiateTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	private boolean selectRecdForChngDoc(String ChngDocRec, boolean isRecordSelectedForChngDoc, int count1)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("documentsWidowDetialsTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((ChngDocRec == null) || ("".equalsIgnoreCase(ChngDocRec)))) {
				ChngDocRec = driver
						.findElement(By
								.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((ChngDocRec == null) || ("".equalsIgnoreCase(ChngDocRec))) {
				ChngDocRec = driver
						.findElement(By
								.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType

			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String CCNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (ChngDocRec.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[ " + i
											+ " ]/td[3]"))
									.click();
							isRecordSelectedForChngDoc = true;
							break;
						}
					}
					if (isRecordSelectedForChngDoc) {
						break;
					}
				} else {
					String CCNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
							.getText();
					if (ChngDocRec.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(By
								.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
								.click();
						isRecordSelectedForChngDoc = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForChngDoc) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#documentsWidowDetialsTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("documentsWidowDetialsTableContainer"));// Document Tree approve
																								// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForChngDoc;
	}

	private boolean selectActionItem(String aIDescription, boolean isRecordSelected100, int count1)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("vendorDetialsTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;

		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((aIDescription == null) || ("".equalsIgnoreCase(aIDescription)))) {
				aIDescription = driver
						.findElement(By.xpath("//*[@id=\"vendorDetialsTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((aIDescription == null) || ("".equalsIgnoreCase(aIDescription))) {
				aIDescription = driver
						.findElement(By.xpath("//*[@id=\"vendorDetialsTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType

			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
//		        while (noOfRecordsChecked < totalNoOfRecords) {
			if (totalNoOfRecords > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String aIDescriptionSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"vendorDetialsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
							.getText();// documentTypeName
					if (aIDescription.equalsIgnoreCase(aIDescriptionSequence)) {
						driver.findElement(By
								.xpath("//*[@id=\"vendorDetialsTableContainer\"]/div/table/tbody/tr[" + i + " ]/td[5]"))
								.click();
						isRecordSelected100 = true;
						break;
					}
				}
//		                if (isRecordSelected100) {
//		                    break;
//		                }
			} else {
				String aIDescriptionSequence = driver
						.findElement(By.xpath("//*[@id=\"vendorDetialsTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();
				if (aIDescription.equalsIgnoreCase(aIDescriptionSequence)) {
					driver.findElement(By.xpath("//*[@id=\"vendorDetialsTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
							.click();
					isRecordSelected100 = true;
//		                    break;
				}
			}
//		            noOfRecordsChecked += perPageNoOfRecordsPresent;
//		            if ((!isRecordSelected100) && (noOfRecordsChecked < totalNoOfRecords)) {
//		                driver.findElement(By.cssSelector("#vendorsTableContainerInVendorEvaluation > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//		                Thread.sleep(40000);
//		                table = driver.findElement(By.id("vendorsTableContainerInVendorEvaluation"));//Document Tree approve table
//		                tableBody = table.findElement(By.tagName("tbody"));
//		                perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//		            }
//		        }
		}
		return isRecordSelected100;
	}

	@AfterMethod
	public void testIT(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}

}
