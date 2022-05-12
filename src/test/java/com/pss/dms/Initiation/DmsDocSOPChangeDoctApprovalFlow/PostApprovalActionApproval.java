package com.pss.dms.Initiation.DmsDocSOPChangeDoctApprovalFlow;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.dms.login.QMSLoginDetails;
//import com.pss.qms.login.QMSLoginDetailsCC;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;

public class PostApprovalActionApproval extends QMSLoginDetails {

	@Test
	public void toPostApprovalActionApproval() throws InterruptedException, IOException, DocumentException, Exception {
		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PostApprovalActionApproval"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("PostApprovalActionApproval", "PSS-QMS-012",
					"Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(4000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME3"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
			Select module = new Select(driver.findElement(By.id("qmsModule")));
			module.selectByIndex(2);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/input[1]")).click();

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
			Thread.sleep(12000);
//        driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module",sno,false);
//        sno++;
//        Thread.sleep(10000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(16000);
			driver.findElement(By.cssSelector("a[href='ccPostAppActionsApprovePage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Post Approval Actions menu",
					sno, false);
//        sno++;
//        Thread.sleep(16000);
//        driver.findElement(By.cssSelector("#ccPostAppActionsListId > ul > li:nth-child(2) > a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on approval submenu", sno,false);
//        Thread.sleep(100000);
			WebDriverWait wait1 = new WebDriverWait(driver, 460);
			wait1.until(ExpectedConditions.invisibilityOf(
					driver.findElement(By.cssSelector("#ccPostActionsApprovalTable > div > div.jtable-busy-message"))));
			methodToDoPostApprovalActionApproval();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void methodToDoPostApprovalActionApproval() throws Exception {
//    	Thread.sleep(210000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
////        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = DepartmentCode + "/" ;
//        Date date = new Date() ;
//        String sdf = new SimpleDateFormat("YY").format(date);
		String CCNumber = properties.getProperty("chgCtrlId");
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//        String CCNumberToTrim = CCQMSLoginDetails.finalCCNumber;
//        String CCNumber = CCNumberToTrim.trim(); 
//        System.out.println("CC Number is coming........:"+CCNumber);
		Thread.sleep(10000);
		isRecordSelected = selectRecdPostApprovalActionApproval(CCNumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(10000);
		if (isRecordSelected) {
			sno++;
//        	Robot robot = new Robot();
//        	for (int i = 0; i < 1; i++)
//        	{ 
//        	robot.keyPress(KeyEvent.VK_CONTROL);
//        	     robot.keyPress(KeyEvent.VK_SUBTRACT);
//        	     robot.keyRelease(KeyEvent.VK_SUBTRACT);
//        	     robot.keyRelease(KeyEvent.VK_CONTROL);
//        	    }
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);

//        	  WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
//        	  JavascriptExecutor js = (JavascriptExecutor)driver;
//        	  js.executeScript("arguments[0].click();", element);

			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next button", sno, false);
			Thread.sleep(10000);
			sno++;
//            WebElement element1 = driver.findElement(By.xpath("//*[starts-with(@id, \"postAppAcceptStatus_\")]"));
//      	    js.executeScript("arguments[0].click();", element1);
			driver.findElement(By.xpath("//*[starts-with(@id, \"postAppAcceptStatus_\")]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Accept radio button", sno,
					false);
			Thread.sleep(10000);
			sno++;
//            WebElement element2 = driver.findElement(By.id("commentsInPostActionsApp"));
//      	    js.executeScript("arguments[0].click();", element2);
			driver.findElement(By.id("commentsInPostActionsApp"))
					.sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(10000);
			sno++;
//            WebElement element3 = driver.findElement(By.id("closeButtonInPostApprovalActionsApprove"));
//      	    js.executeScript("arguments[0].click();", element3);
			driver.findElement(By.id("closeButtonInPostApprovalActionsApprove")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Close button", sno, false);
			Thread.sleep(10000);
			sno++;
//            WebElement element4 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
//      	    js.executeScript("arguments[0].click();", element4);
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit button", sno, false);
			Thread.sleep(10000);
			sno++;
//            WebElement element5 = driver.findElement(By.id("eSignPwdInWnd"));
//      	    js.executeScript("arguments[0].click();", element5);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(10000);
			sno++;
//            WebElement element6 = driver.findElement(By.id("subBtnInValidateESign"));
//      	    js.executeScript("arguments[0].click();", element6);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On submit button", sno, false);
			Thread.sleep(10000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(10000);
//            WebElement element7 = driver.findElement(By.className("modal-btn"));
//      	    js.executeScript("arguments[0].click();", element7);
			driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK button", sno, false);
			Thread.sleep(10000);
			sno++;
			driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[3]/a/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecdPostApprovalActionApproval(String chgCtrlId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("ccPostActionsApprovalTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((chgCtrlId == null) || ("".equalsIgnoreCase(chgCtrlId)))) {
				chgCtrlId = driver
						.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((chgCtrlId == null) || ("".equalsIgnoreCase(chgCtrlId))) {
				chgCtrlId = driver
						.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/table/tbody/tr/td[3]"))
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
										".//*[@id='ccPostActionsApprovalTable']/div/table/tbody/tr[ " + i + "]/td[5]"))
								.getText();// documentTypeName
						if (chgCtrlId.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(By.xpath(
									".//*[@id='ccPostActionsApprovalTable']/div/table/tbody/tr[ " + i + "]/td[5]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String capaNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (chgCtrlId.equalsIgnoreCase(capaNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/table/tbody/tr/td[5]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#ccPostActionsApprovalTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(10000);
					table = driver.findElement(By.id("ccPostActionsApprovalTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

}
