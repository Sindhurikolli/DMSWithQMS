package com.pss.dms.CCRejectionFlow;

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

////@Listeners(com.pss.dms.Listerners.TestListener.class)
public class QAReviewApproveAfterReject extends QMSLoginDetails {

	@Test
	public void toQAReviewApproveAfterReject() throws Exception {

//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewApproveAfterReject"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReviewApproveAfterReject", "PSS-QMS-024", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("QMSLoginUrl"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CCQMScoordinator"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PassWord"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByIndex(2);
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id='loginform']/div[7]/button[1]")).click();
		Thread.sleep(5000);
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
		Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module",sno,false);
//        sno++;
//        Thread.sleep(12000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ccReviewPage.do']")));
		Thread.sleep(3000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on review menu", sno, false);
		driver.findElement(By.cssSelector("a[href='ccReviewPage.do']")).click();		
		Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#changeControlReviewTableContainer > div > div.jtable-busy-message[style='display: none;']")));

		todoQAReviewApproveAfterReject();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
	}

//	}

	private void todoQAReviewApproveAfterReject() throws Exception {

		int count = 0;
		Thread.sleep(3000);
		boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String changeCtrlSequence = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = DepartmentCode + "/" ;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String chgCtrlId = "/0104";
//        String ChangeControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
		sno++;
		String CCNumber = properties.getProperty("chgCtrlId");
//         String CCNumberToTrim = CCQMSLoginDetails.finalCCNumber;
//        String CCNumber = CCNumberToTrim.trim(); 
//        System.out.println("CC Number is coming........:"+CCNumber);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record and Click on Review", sno, false);
		Thread.sleep(5000);
		
		isRecordSelected = selectRecdQAReviewCC(CCNumber, isRecordSelected, count);
		
		Thread.sleep(20000);
		if (isRecordSelected) {
			sno++;
			Select ImpactOnProduct = new Select(driver.findElement(By.id("impOnProdQualityInQaPrimaryCcReview")));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Impact on Product quality",
					sno, false);
			ImpactOnProduct.selectByIndex(2);
			Thread.sleep(2000);
			sno++;
			Select ImpactOnQualitySystem = new Select(
					driver.findElement(By.id("impOnQualSysOrProcInQaPrimaryCcReview")));
			ImpactOnQualitySystem.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Impact on Quality System/Processes",
					sno, false);
			Thread.sleep(2000);
			sno++;
			Select Category = new Select(
					driver.findElement(By.id("cateOfChangeInQaPrimaryCcReview")));
			Category.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Category of Change",
					sno, false);
			Thread.sleep(2000);
			sno++;
//             JavascriptExecutor jse = (JavascriptExecutor) driver;
//            WebElement element = driver.findElement(By.id("customerNotifInQAPrimary"));
//            jse.executeScript("arguments[0].scrollIntoView(true);", element);
//            Thread.sleep(1000);
			driver.findElement(By.id("customerNotifInQAPrimary")).click();
			Thread.sleep(3000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on customer notification", sno,
					false);
			sno++;
			driver.findElement(By.id("coutinueOtherOpinionQaPrimaryCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on Regulatory/CFT team review radio button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("regulatoryTeamReviewAddButton")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on regulatory team review add button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("locTreeForRegulatoryTeamInQmsCcReview_2_span")).click();
			Thread.sleep(5000);
//            driver.findElement(By.id("locTreeForRegulatoryTeamInQmsCcReview_3_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			Thread.sleep(12000);
			boolean isRecordSelectedForReg = false;
			String regFirstName = properties.getProperty("CCREG_E-code");
			String RegFullName = regFirstName;
			sno++;
			int count3 = 0;
			isRecordSelectedForReg = selectingTheRegReview(RegFullName, isRecordSelectedForReg, count3);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Record", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("regulatoryTeamWinAddButton")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
			Thread.sleep(3000);
			JavascriptExecutor jse3 = (JavascriptExecutor) driver;
			WebElement element3 = driver.findElement(By.id("crossFunctionalTeamReviewButton"));
			jse3.executeScript("arguments[0].click();", element3);
			Thread.sleep(1000);
			sno++;
			// driver.findElement(By.id("crossFunctionalTeamReviewButton")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on cross functional team review add button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("locTreeForDeptTeamInQmsDevReview_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			Thread.sleep(3000);
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			WebElement element1 = driver
					.findElement(By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/div[4]/table/tbody/tr/td[3]"));
			jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
			Thread.sleep(1000);
			boolean isRecordSelectedForCFT = false;
			String cftFirstName = properties.getProperty("CCCFT_Name");
			String cftFullName = cftFirstName;
			System.out.println("cftFullName: " + cftFullName);
			sno++;
			int count2 = 0;
			isRecordSelectedForCFT = selectingTheCFTReview(cftFullName, isRecordSelectedForCFT, count2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the CFT Department", sno, false);
			Thread.sleep(6000);
			if (isRecordSelectedForCFT) {
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("addBtnInCftDeptAdd")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
				Thread.sleep(3000);
				JavascriptExecutor jse2 = (JavascriptExecutor) driver;
				WebElement element2 = driver.findElement(By.id("commentsQaPrimaryCcReview"));
				jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
				Thread.sleep(1000);
				sno++;
				driver.findElement(By.id("commentsQaPrimaryCcReview")).sendKeys(properties.getProperty("CC_1500"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("approveQaPrimaryCcReview")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno,
						false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a[2]")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes button", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password",
						sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("subBtnInValidateESign")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno,
						false);
				Thread.sleep(3000);
				sno++;
				WebDriverWait wait = new WebDriverWait(driver, 70);
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
				Thread.sleep(3000);
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
				sno++;
				Thread.sleep(5000);
				driver.findElement(By.className("username")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
				sno++;
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

			} else {
				System.out.println("Record is not Selected For CFT Review");
				Assert.assertTrue(false);
			}
		}

	}

	private boolean selectRecdQAReviewCC(String ChangeControlNumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeControlReviewTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			WebElement ele1 = driver
					.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/div[4]/div[2]/span"));
			js1.executeScript("arguments[0].scrollIntoView(true);", ele1);
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
			if ((totalNoOfRecords > 1)
					&& ((ChangeControlNumber == null) || ("".equalsIgnoreCase(ChangeControlNumber)))) {
				ChangeControlNumber = driver
						.findElement(
								By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((ChangeControlNumber == null) || ("".equalsIgnoreCase(ChangeControlNumber))) {
				ChangeControlNumber = driver
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
						JavascriptExecutor js2 = (JavascriptExecutor) driver;
						WebElement ele2 = driver.findElement(By.xpath(
								".//*[@id='changeControlReviewTableContainer']/div/table/tbody/tr[ " + i + "]/td[3]"));
						js2.executeScript("arguments[0].scrollIntoView(true);", ele2);
						String CCNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (ChangeControlNumber.equalsIgnoreCase(CCNumberSequence)) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							WebElement ele = driver.findElement(
									By.xpath(".//*[@id='changeControlReviewTableContainer']/div/table/tbody/tr[ " + i
											+ "]/td[56]/button"));
							js.executeScript("arguments[0].click();", ele);
							// driver.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[
							// " + i + " ]/td[56]/button")).click();
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
					if (ChangeControlNumber.equalsIgnoreCase(CCNumberSequence)) {
						Thread.sleep(4000);
						driver.findElement(By.xpath(
								"//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[56]/button"))
								.click();
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

//					 driver.findElement(By.cssSelector("#changeControlReviewTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("changeControlReviewTableContainer"));// Document Tree approve
																							// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	// Method To Select CFT User In The Table
	private boolean selectingTheCFTReview(String cftFullName, boolean recordSelected, int count) {
		WebElement table = driver.findElement(By.id("cftDeptTableWindowContainer"));
		WebElement tableBody = table.findElement(By.cssSelector(
				"#cftDeptTableWindowContainer > div > div.jtable-main-container.scroll-content > table > tbody"));

		int perPageNoOfRecordsPresent = tableBody.findElements(By.cssSelector(
				"#cftDeptTableWindowContainer > div > div.jtable-main-container.scroll-content > table > tbody > tr"))
				.size();
		System.out.println("tableBody: " + tableBody);
		System.out.println("perPageNoOfRecordsPresent: " + perPageNoOfRecordsPresent);
//        int totalNoOfRecords = 0;
//        int noOfRecordsChecked = 0;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            try {
//                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
		// *[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if (((cftFullName == null) || ("".equalsIgnoreCase(cftFullName)))) {
				cftFullName = driver
						.findElement(By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((cftFullName == null) || ("".equalsIgnoreCase(cftFullName))) {
				cftFullName = driver
						.findElement(By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			// while (noOfRecordsChecked < totalNoOfRecords) {
			if (perPageNoOfRecordsPresent > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String cftReviewerFullName = driver.findElement(By.xpath(
							"//*[@id=\"cftDeptTableWindowContainer\"]/div/div[4]/table/tbody/tr[ " + i + " ]/td[3]"))
							.getText();// documentTypeName
					System.out.println("cftReviewerFullName: " + cftReviewerFullName);
					if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
						driver.findElement(By.xpath(
								"//*[@id=\"cftDeptTableWindowContainer\"]/div/div[4]/table/tbody/tr[" + i + "]/td[3]"))
								.click();
						recordSelected = true; // *[@id="cftDeptTableWindowContainer"]/div/div[4]/table/tbody/tr[4]/td[3]
						break; // *[@id="cftDeptTableWindowContainer"]/div/div[4]/table/tbody/tr[3]/td[3]
					}
				}

			} else {
				String cftReviewerFullName = driver
						.findElement(
								By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/div[4]/table/tbody/tr/td[3]"))
						.getText();
				if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
//                        driver.findElement(By.cssSelector("#cftDeptTableWindowContainer > div > div.jtable-main-container.scroll-content > table > tbody > tr.jtable-data-row.jtable-row-selected > td:nth-child(3)")).click();
					driver.findElement(
							By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/div[4]/table/tbody/tr/td[3]"))
							.click();
					recordSelected = true;

				}
			}
//                noOfRecordsChecked += perPageNoOfRecordsPresent;
//                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                    Thread.sleep(3000);
//                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                    tableBody = table.findElement(By.tagName("tbody"));
//                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                }
			// }
		}
		return recordSelected;
	}

	private boolean selectingTheRegReview(String RegFullName, boolean recordSelectedForReg, int count3) {
		WebElement table = driver.findElement(By.id("regulatoryTeamReAddDetailsWindowTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"regulatoryTeamReAddDetailsWindowTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            try {
//                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
		}
		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
			if (((RegFullName == null) || ("".equalsIgnoreCase(RegFullName)))) {
				RegFullName = driver
						.findElement(By.xpath(
								"//*[@id=\"regulatoryTeamReAddDetailsWindowTable\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((RegFullName == null) || ("".equalsIgnoreCase(RegFullName))) {
				RegFullName = driver
						.findElement(
								By.xpath("//*[@id=\"regulatoryTeamReAddDetailsWindowTable\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType

			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			// while (noOfRecordsChecked < totalNoOfRecords) {
			if (perPageNoOfRecordsPresent > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String RegReviewerFullName = driver.findElement(By.xpath(
							"//*[@id=\"regulatoryTeamReAddDetailsWindowTable\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
							.getText();// documentTypeName
					System.out.println("RegReviewerFullName: " + RegReviewerFullName);
					if (RegFullName.equalsIgnoreCase(RegReviewerFullName)) {
						driver.findElement(
								By.xpath("//*[@id=\"regulatoryTeamReAddDetailsWindowTable\"]/div/table/tbody/tr[ " + i
										+ " ]/td[4]"))
								.click();
						recordSelectedForReg = true;
						break;
					}
				}

			} else {
				String cftReviewerFullName = driver
						.findElement(By.xpath(
								"//*[@id=\"regulatoryTeamReAddDetailsWindowTable\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();
				if (RegFullName.equalsIgnoreCase(cftReviewerFullName)) {
					driver.findElement(
							By.xpath("//*[@id=\"regulatoryTeamReAddDetailsWindowTable\"]/div/table/tbody/tr[1]/td[4]"))
							.click();
					recordSelectedForReg = true;

				}
			}
//                noOfRecordsChecked += perPageNoOfRecordsPresent;
//                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                    Thread.sleep(3000);
//                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                    tableBody = table.findElement(By.tagName("tbody"));
//                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                }
			// }
		}
		return recordSelectedForReg;
	}

}
