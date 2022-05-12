package com.pss.dms.CCFWD;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.configuration.PropertiesConfiguration;
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
public class PostApprovalActionInitiateChangeDocument3 extends QMSLoginDetails {
	@Test
	public void toPostApprovalActionInitiateChangeDocument()
			throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PostApprovalActionInitiateChangeDocument"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("PostApprovalActionInitiateChangeDocument",
				"PSS-QMS-010", "Pass");
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
		Thread.sleep(5000);
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
		methodToDoPostApprovalActionInitiateChangeDocument();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}

//}

	private void methodToDoPostApprovalActionInitiateChangeDocument() throws Exception {
//	    	Thread.sleep(22000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
//	        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
////	        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//	        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//	        String chgCtrlNoWithPlantCode =  DepartmentCode + "/" ;
//	        Date date = new Date() ;
//	        String sdf = new SimpleDateFormat("YY").format(date);
//	        String chgCtrlId = properties.getProperty("CHANGE_CONTROL_NO");
		String CCNumber = properties.getProperty("chgCtrlId");
//	        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//	        String CCNumberToTrim = CCQMSLoginDetails.finalCCNumber;
//	        String CCNumber = CCNumberToTrim.trim(); 
//	        System.out.println("CC Number is coming........:"+CCNumber);
		Thread.sleep(4000);
		isRecordSelected = selectRecdPostApprovalActionInitiate(CCNumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(5000);
		sno++;
		if (isRecordSelected) {
			WebDriverWait wait = new WebDriverWait(driver, 70);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);

			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(5000);
			sno++;
//	            WebElement element1 = driver.findElement(By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]"));
//	            js.executeScript("arguments[0].click();", element1);

			driver.findElement(
					By.xpath("//*[@id=\"addActionItemsContainerInPostAppInitiate\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno, false);
			Thread.sleep(5000);
			sno++;
//	            WebElement element2 = driver.findElement(By.id("ccActionItemDepartment"));
//	            js.executeScript("arguments[0].click();", element2);

			driver.findElement(By.id("ccActionItemDepartment")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			Thread.sleep(5000);
//	            WebElement element11 = driver.findElement(By.id("treeContainer_2_switch"));
//	            js.executeScript("arguments[0].click();", element11);
			driver.findElement(By.id("treeContainer_2_switch")).click();
			Thread.sleep(5000);
//	            WebElement element12 = driver.findElement(By.id("treeContainer_4_span"));
//	            js.executeScript("arguments[0].click();", element12);
			driver.findElement(By.linkText(properties.getProperty("PostApproval_Department"))).click();
			Thread.sleep(5000);
			sno++;
//	            WebElement element3 = driver.findElement(By.id("selectBtnInLocSelect"));
//	            js.executeScript("arguments[0].click();", element3);

			driver.findElement(By.id("selectBtnInLocSelect")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			Thread.sleep(5000);
//	            sno++;
////	            WebElement element4 = driver.findElement(By.id("actItemDescInAddActItemDlg"));
////	            js.executeScript("arguments[0].click();", element4);
//
			driver.findElement(By.id("actItemDescInAddActItemDlg"))
					.sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item description", sno,
					false);
			Thread.sleep(5000);
//	            sno++;
////	            WebElement element5 = driver.findElement(By.id("addBtnInActionItemAddDlg"));
////	            js.executeScript("arguments[0].click();", element5);
//
			driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(5000);
			driver.findElement(By.id("addNewDocInCheckInCCRevInQaPrim")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"docDetailsTableNewInCcRev\"]/div/div[3]/div[2]/span[2]/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change document button", sno,
					false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("locTreeForAddDocuments_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Location", sno, false);
			Thread.sleep(3000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#documentsWidowDetialsTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(3000);
			String rows = properties.getProperty("CC_DOC_ROW_NO");
			String ChangeDocName = driver
					.findElement(By.xpath(
							"//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[" + rows + "]/td[3]"))
					.getText();
			String ChangeDocNo = driver
					.findElement(By.xpath(
							"//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[" + rows + "]/td[4]"))
					.getText();
			String ChangeDocVerNo = driver
					.findElement(By.xpath(
							"//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[" + rows + "]/td[5]"))
					.getText();
			Thread.sleep(1000);
			System.out.println(ChangeDocNo);
			PropertiesConfiguration properties = new PropertiesConfiguration(
					"src/test/java/com/pss/dms/properties/DMSProperties.properties");
			properties.setProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST", ChangeDocName);
			properties.setProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP", ChangeDocNo);
			properties.setProperty("DocumentVersion", ChangeDocVerNo);
			properties.setProperty("LMSDocumentVersion", ChangeDocVerNo);
			properties.save();

//	            String headerLoc = "//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/thead/tr/th";
//	    		List<WebElement> allHeadersEle = driver.findElements(By.xpath(headerLoc));
//	    		List<String> allHeaderNames = new ArrayList<String>();
//	    		for (WebElement header : allHeadersEle) {
//	    			String headerName = header.getText();
//	    			allHeaderNames.add(headerName);
//	    		}
//	    		
//	    		List<LinkedHashMap<String, String>> allTableData = new ArrayList<LinkedHashMap<String, String>>();
//	    		String rowLoc = "//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr";
//	    		List<WebElement> allRowsEle = driver.findElements(By.xpath(rowLoc));
//	    		for (int i = 1; i <= allRowsEle.size(); i++) {
//	    		
//	    			String specificRowLoc = "//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr[" + i + "]";
//	    			
//	    			List<WebElement> allColumnsEle = driver.findElement(By.xpath(specificRowLoc))
//	    					.findElements(By.tagName("td"));
//	    			
//	    			LinkedHashMap<String, String> eachRowData = new LinkedHashMap<>();
//	    		
//	    			for (int j = 0; j < allColumnsEle.size(); j++) {
//	    			
//	    				String cellValue = allColumnsEle.get(j).getText();
//	    				
//	    				eachRowData.put(allHeaderNames.get(j), cellValue);
//	    			}
//	    		
//	    			allTableData.add(eachRowData);
//	     
//	    		}
//	     
//	    		System.out.println(allTableData);
//	    	

			Thread.sleep(1000);
			int count1 = 0;
			boolean isRecordSelectedForChngDoc = false;
			String ChngDocRec = (String) properties.getProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST");
			Thread.sleep(4000);
			isRecordSelected = selectRecdForChngDoc(ChngDocRec, isRecordSelectedForChngDoc, count1);
			Thread.sleep(4000);
//		        if(isRecordSelectedForChngDoc)
//		        {
//		        driver.findElement(By.id("addBtnInDocumentAdd")).click();
//		        }
//		        else
//		        {
//		        	System.out.println("Record Not Selected for Change Document");
//		        	 Assert.assertTrue(false);
//		        }
			sno++;
			driver.findElement(By.id("addBtnInDocumentAdd")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"docDetailsTableNewInCcRev\"]/div/table/tbody/tr/td[11]/button"))
					.click();
//	            driver.findElement(By.cssSelector("#docDetailsTableNewInCcRev > div > table > tbody > tr > td:nth-child(12) > button > i")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Link with Action Item button",
					sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"actItemJtableForChangeAndTermDocSelInDialog\"]/div/table/tbody/tr/td[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Existing Action Item", sno,
					false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.id("addActItemBtnInAddLinkPostAppActItem")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on SUBMIT button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys((String) properties.getProperty("ESIGN_PASSPWD"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(5000);
			sno++;
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

	private boolean selectRecdPostApprovalActionInitiate(String CCNumber, boolean isRecordSelected, int count)
			throws InterruptedException {
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
							System.out.println(CCNumberSequence);
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

	@AfterMethod
	public void testIT(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}

}
