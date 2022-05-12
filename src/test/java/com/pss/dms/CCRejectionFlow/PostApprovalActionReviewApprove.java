package com.pss.dms.CCRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class PostApprovalActionReviewApprove extends QMSLoginDetails {

	@Test
	public void toPostApprovalActionReviewApprove() throws Exception {

//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PostApprovalActionReviewApprove"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("PostApprovalActionReviewApprove", "PSS-QMS-041",
				"Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("QMSLoginUrl"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CCDepartmentHod"));
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
//        Thread.sleep(16000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ccPostAppActionsReviewPage.do']")));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review at Post Approval Actions menu", sno,
				false);
		sno++;
		driver.findElement(By.cssSelector("a[href='ccPostAppActionsReviewPage.do']")).click();
		
//        sno++;
//        Thread.sleep(16000);
//        driver.findElement(By.cssSelector("#ccPostAppActionsListId > ul > li > a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on review submenu", sno,false);
		Thread.sleep(2000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#ccPostApprovalReviewTable > div > div.jtable-busy-message[style='display: none;']")));

		todoPostApprovalActionReviewApprove();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
	}

//	}

	private void todoPostApprovalActionReviewApprove() throws Exception {

		Thread.sleep(5000);
		
		int count = 0;
		boolean isRecordSelected = false;
//	        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//	        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//	        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//	        String chgCtrlNoWithPlantCode = DepartmentCode + "/" ;
//	        Date date = new Date() ;
//	        String sdf = new SimpleDateFormat("YY").format(date);
		String CCNumber = properties.getProperty("chgCtrlId");
//	        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//	        String CCNumberToTrim = CCQMSLoginDetails.finalCCNumber;
//	        String CCNumber = CCNumberToTrim.trim(); 
//	        System.out.println("CC Number is coming........:"+CCNumber);
		Thread.sleep(4000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		sno++;
		isRecordSelected = selectRecdPostApprovalActionReview(CCNumber, isRecordSelected, count);
		
		Thread.sleep(5000);
		if (isRecordSelected) {
			Thread.sleep(2000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			
			Thread.sleep(3000);
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#actionItemsContainerInPostAppReview > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(2000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit button", sno, false);
			sno++;
			driver.findElement(By.xpath("//*[starts-with(@id, \"editBtn_\")]")).click();			
			Thread.sleep(3000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			sno++;
			driver.findElement(By.id("selectOwnerInDevQaReview1")).click();			
			Thread.sleep(5000);
			driver.findElement(By.id("locationTreeForSingleUserSelect_2_switch")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("locationTreeForSingleUserSelect_3_span")).click();
			Thread.sleep(5000);
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#singleSelUsersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
//	            sno++;
//	            WebElement element1 = driver.findElement(By.id("locTreeInQmsProdReg_3_span"));
//	            JavascriptExecutor js = (JavascriptExecutor)driver;
//	            js.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			Thread.sleep(3000);
			sno++;
			boolean isRecordSelectedForUser = false;
			String UserFirstName = properties.getProperty("CCActionItemOwnerE-Code");
//	            String regLastName  = "reviewer3";
			String UserFullName = UserFirstName;
			int count3 = 0;
			isRecordSelectedForUser = selectingTheRegReview(UserFullName, isRecordSelectedForUser, count3);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the User", sno, false);
			Thread.sleep(3000);
			sno++;
			if (isRecordSelectedForUser) {
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,
						false);
				driver.findElement(By.id("selBtn_InSingleUserSelectDialog")).click();
				

				Thread.sleep(2000);
			} else {
				System.out.println("Action Item Owner not selected");
			}
			((JavascriptExecutor) driver).executeScript(
					"document.getElementById('actItmDueDaysInActItemDlg').removeAttribute('readonly',0);");
			WebElement identifiedDate = driver.findElement(By.id("actItmDueDaysInActItemDlg"));
			identifiedDate.clear();
			Thread.sleep(2000);
			sno++;
			SimpleDateFormat formattedDate = new SimpleDateFormat("d/M/yyyy");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 30); // number of days to add
			String futureDate = (String) (formattedDate.format(c.getTime()));
			driver.findElement(By.id("actItmDueDaysInActItemDlg")).sendKeys(futureDate);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Action Item Due date", sno,
					false);
			sno++;
			Thread.sleep(3000);
			driver.findElement(By.id("actItmOwnerNameInActItemDlg")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.id("addBtnInActionItemDlg")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			sno++;
			Thread.sleep(5000);
			// JavaScriptExecutor js = (JavaScriptExecutor) driver;
			// js.executeScript("document.findElementBy('**idOfTheRadioButton**').click");
//	            driver.findElement(By.("ColumDataAlign")).click();

			// *[@id="actionItemsContainerInPostAppReview"]/div/table/tbody/tr/td[8]
			Thread.sleep(3000);
			
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Accept radio button", sno,
					false);
			driver.findElement(By.xpath("//*[starts-with(@id, \"postAppAcceptStatus_\")]")).click();
			
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("commentsInPostAppReview"))
					.sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("closeButtonInPostApprovalActionsReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(5000);
//	            driver.findElement(By.xpath("//*[@id=\"editBtn_20\"]/i")).click();
//	            Thread.sleep(5000);
//	            
//	            boolean isRecordSelectedForActionItemUser = false;
//	            String ActionItemUserFirstName = properties.getProperty("UserName");
////	            String regLastName  = "Initiator";
//	            String ActionItemFullName = ActionItemUserFirstName;
//	            int count3=0;
//	             isRecordSelectedForActionItemUser=selectingTheActionItemUser(ActionItemFullName,isRecordSelectedForActionItemUser,count3);
//	            Thread.sleep(3000);
//	            driver.findElement(By.id("usersSelBtnInDevReview")).click();
//	            Thread.sleep(3000);
//	            driver.findElement(By.id("actItmDueDaysInActItemDlg")).sendKeys(properties.getProperty("Action_Item_Target_Days"));
//	            Thread.sleep(5000);
//	            driver.findElement(By.id("addBtnInActionItemDlg")).click();
//	            Thread.sleep(5000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(3000);
			sno++;

			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			driver.findElement(By.className("modal-btn")).click();
			
			Thread.sleep(3000);
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

	private boolean selectingTheRegReview(String UserFullName, boolean isRecordSelectedForUser, int count3)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("singleSelUsersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
			if (((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
				UserFullName = driver
						.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
				UserFullName = driver
						.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String RegReviewerFullName = driver.findElement(By.xpath(
								"//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						System.out.println("RegReviewerFullName: " + RegReviewerFullName);
						if (UserFullName.equalsIgnoreCase(RegReviewerFullName)) {
							driver.findElement(By.xpath(
									"//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForUser = true;
							break;
						}
					}
					if (isRecordSelectedForUser) {
						break;
					}

				} else {
					String cftReviewerFullName = driver
							.findElement(
									By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
							.getText();
					if (UserFullName.equalsIgnoreCase(cftReviewerFullName)) {
						driver.findElement(
								By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
								.click();
						isRecordSelectedForUser = true;
						break;
					}
				}
			}
			noOfRecordsChecked += perPageNoOfRecordsPresent;
			if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
				driver.findElement(By.cssSelector(
						"#singleSelUsersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
						.click();// next page in Document approve list
				Thread.sleep(3000);
				table = driver.findElement(By.id("singleSelUsersTableContainer"));// Document Tree approve table
				tableBody = table.findElement(By.tagName("tbody"));
				perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
			}
//	                return isRecordSelectedForUser;
		}

		return isRecordSelectedForUser;
	}

	private boolean selectRecdPostApprovalActionReview(String chgCtrlId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("ccPostApprovalReviewTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"ccPostApprovalReviewTable\"]/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath("//*[@id=\"ccPostApprovalReviewTable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((chgCtrlId == null) || ("".equalsIgnoreCase(chgCtrlId))) {
				chgCtrlId = driver
						.findElement(By.xpath("//*[@id=\"ccPostApprovalReviewTable\"]/div/table/tbody/tr/td[3]"))
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
										".//*[@id='ccPostApprovalReviewTable']/div/table/tbody/tr[ " + i + "]/td[6]"))
								.getText();// documentTypeName
						if (chgCtrlId.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(By.xpath(
									".//*[@id='ccPostApprovalReviewTable']/div/table/tbody/tr[ " + i + "]/td[6]"))
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
							.findElement(By.xpath("//*[@id=\"ccPostApprovalReviewTable\"]/div/table/tbody/tr/td[6]"))
							.getText();
					if (chgCtrlId.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"ccPostApprovalReviewTable\"]/div/table/tbody/tr/td[6]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#ccPostApprovalReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("ccPostApprovalReviewTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	private boolean selectingTheActionItemUser(String RegFullName, boolean recordSelectedForActionItemUser,
			int count3) {
		WebElement table = driver.findElement(By.id("usersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
//	            String a = driver.findElement(By.xpath("//*[@id=\"regulatoryTeamReAddDetailsWindowTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//	            String[] parts = a.split(" of ");
//	            try {
//	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//	            } catch (Exception e) {
//	                System.out.println(e.getMessage());
//	            }
		}
		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
			if (((RegFullName == null) || ("".equalsIgnoreCase(RegFullName)))) {
				RegFullName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((RegFullName == null) || ("".equalsIgnoreCase(RegFullName))) {
				RegFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// documentType

			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			// while (noOfRecordsChecked < totalNoOfRecords) {
			if (perPageNoOfRecordsPresent > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String RegReviewerFullName = driver
							.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
							.getText();// documentTypeName
					System.out.println("RegReviewerFullName: " + RegReviewerFullName);
					if (RegFullName.equalsIgnoreCase(RegReviewerFullName)) {
						driver.findElement(
								By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
								.click();
						recordSelectedForActionItemUser = true;
						break;
					}
				}

			} else {
				String cftReviewerFullName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
				if (RegFullName.equalsIgnoreCase(cftReviewerFullName)) {
					driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
					recordSelectedForActionItemUser = true;

				}
			}
//	                noOfRecordsChecked += perPageNoOfRecordsPresent;
//	                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//	                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//	                    Thread.sleep(3000);
//	                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//	                    tableBody = table.findElement(By.tagName("tbody"));
//	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//	                }
			// }
		}
		return recordSelectedForActionItemUser;
	}

}
