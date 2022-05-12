package com.pss.dms.CCFWD;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
import com.pss.dms.HelperPackageDms.Helper;
import com.pss.dms.login.QMSLoginDetails;
import com.pss.dms.util.HeaderFooterPageEvent;
import com.pss.dms.util.Utilities;
import com.pss.qms.ExtentTestNGPkg.Utility;

////@Listeners(com.pss.dms.Listerners.TestListener.class)
public class QAReReviewCC extends QMSLoginDetails {

	@Test
	public void toQAReReviewCC() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReReviewCC" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReReviewCC", "PSS-QMS-008", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("QMSLoginUrl"));
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CCQMScoordinator"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		module.selectByIndex(2);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id='loginform']/div[7]/button[1]")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(
				sno + "." + "Enter the username, password, Select Change Control Module and click on login button" + Utilities.prepareSSNumber(sno),
				font));
		document.add(im);

		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;

		Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='ccReviewPage.do']")));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on review menu", sno, false);
		driver.findElement(By.cssSelector("a[href='ccReviewPage.do']")).click();
		
		Thread.sleep(5000);
//        WebDriverWait wait1 = new WebDriverWait(driver, 460);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#changeControlReviewTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		todoQAReReviewCC();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}

//}

	private void todoQAReReviewCC() throws Exception {
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
//	    	WebElement element = driver.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/div[4]/div[2]/span"));
//	    	jse.executeScript("arguments[0].scrollIntoView(true);", element);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
//	        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT");
//	        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//	        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//	        String chgCtrlNoWithPlantCode =  DepartmentCode + "/";
//	        Date date = new Date();
//	        String sdf = new SimpleDateFormat("YY").format(date);
//	        String chgCtrlId = "/0104";
//	        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
		String CCNumber = properties.getProperty("chgCtrlId");
//	         String CCNumberToTrim = CCQMSLoginDetails.finalCCNumber;
//	        String CCNumber = CCNumberToTrim.trim(); 
//	        System.out.println("CC Number is coming........:"+CCNumber);
		Thread.sleep(4000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record and Click on Review", sno, false);
		isRecordSelected = selectRecdQAReviewChgControlDoctPositiveFlow(CCNumber, isRecordSelected, count);
		
		Thread.sleep(5000);
		if (isRecordSelected) {
			Thread.sleep(3000);

//			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
//	        	sno++;
//	            Select ImpactOnProduct = new Select(driver.findElement(By.id("impOnProdQualityInQaPrimaryCcReview")));
//	            ImpactOnProduct.selectByIndex(2);
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Impact  On product quality", sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            Select ImpactOnQualitySystem = new Select(driver.findElement(By.id("impOnQualSysOrProcInQaPrimaryCcReview")));
//	            ImpactOnQualitySystem.selectByIndex(1);
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Impact on quality system", sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            Select CategoryofChange = new Select(driver.findElement(By.id("cateOfChangeInQaPrimaryCcReview")));
//	            CategoryofChange.selectByIndex(3);
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Category of change", sno,false);
//	            Thread.sleep(2000);         
//	            WebElement element1 = driver.findElement(By.id("reviewCompletedQaPrimaryCcReview"));
//	            jse.executeScript("arguments[0].scrollIntoView(true);", element1);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("reviewCompletedQaPrimaryCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on QAhod radio button", sno,
					false);
			Thread.sleep(4000);

			WebElement qaHodCheck = driver.findElement(By.cssSelector(
					"#qaApproveUserDetailsContainer > div > table > thead > tr > th.jtable-command-column-header.jtable-column-header-selecting > div > input[type=checkbox]"));
			if (!qaHodCheck.isSelected())
				qaHodCheck.click();

			// enble when there are multiple qa approvers
//	            sno++;
//	            driver.findElement(By.xpath("//*[@id=\"qaApproveUserDetailsContainer\"]/div/table/tbody/tr[7]/td[1]/input")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the User", sno,false);
//	            Thread.sleep(4000);
			sno++;
			WebElement element2 = driver.findElement(By.id("reRevCommentsQaPrimaryCcReview"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element2);
			Thread.sleep(5000);
			driver.findElement(By.id("reRevCommentsQaPrimaryCcReview")).sendKeys(properties.getProperty("CC_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Re review comments", sno,
					false);
			Thread.sleep(4000);
			sno++;
			WebElement element3 = driver.findElement(By.id("approveQaPrimaryCcReview"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element3);
			Thread.sleep(5000);
			driver.findElement(By.id("approveQaPrimaryCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on SUBMIT button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(5000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(5000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			driver.findElement(By.className("modal-btn")).click();
			
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdQAReviewChgControlDoctPositiveFlow(String chgControlNumber, boolean isRecordSelected,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeControlReviewTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber)))) {
				chgControlNumber = driver
						.findElement(
								By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber))) {
				chgControlNumber = driver
						.findElement(
								By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						Helper.scrollElement(driver, By.xpath(
								"//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"));
						String CCNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (chgControlNumber.equalsIgnoreCase(CCNumberSequence)) {
							Helper.clickElement(driver, By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[ " + i
									+ " ]/td[3]"));
							Helper.clickElement(driver, By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[ " + i
											+ " ]/td[56]/button"));
							
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String CCNumberSequence = driver
							.findElement(
									By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (chgControlNumber.equalsIgnoreCase(CCNumberSequence)) {
						WebElement element = driver.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[56]/button"));
						JavascriptExecutor js1 = (JavascriptExecutor) driver;
						js1.executeScript("arguments[0].click();", element);
//						driver.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[56]/button"))
//								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					WebElement element = driver.findElement(By.cssSelector(
							"#changeControlReviewTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", element);

//	                    driver.findElement(By.cssSelector("#changeControlReviewTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
					Thread.sleep(3000);
					WebDriverWait wait1 = new WebDriverWait(driver, 60);
					wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
							"#changeControlReviewTableContainer > div > div.jtable-busy-message[style='display: none;']")));
					table = driver.findElement(By.id("changeControlReviewTableContainer"));// Document Tree approve
																							// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	@AfterMethod
	public void testIT(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}

}
