package com.pss.dms.CCFWD;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class CCInitiation1 extends QMSLoginDetails {

	@Test

	public void toCCInitiation() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CCInitiation" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CCInitiation", "PSS-QMS-001", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.get(properties.getProperty("QMSLoginUrl"));
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 420);
		Utilities.qmsLoginMethod(properties.getProperty("CCInitiator"), properties.getProperty("PassWord"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		im = Image.getInstance(input);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[5]/button[1]")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(
				sno + "." + "Enter the username, password and click on login button" + Utilities.prepareSSNumber(sno),
				font));
		document.add(im);

		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
//			sno++;
//			wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")));
//        driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module",sno,false);
//        sno++;
//        Thread.sleep(20000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
		sno++;
//        Thread.sleep(20000);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ccInitiatePage.do']")));
		driver.findElement(By.cssSelector("a[href='ccInitiatePage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate menu", sno, false);
		Thread.sleep(5000);
		toInitiateRecord();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}

//}

	private void toInitiateRecord() throws Exception {
//        Thread.sleep(10000);
//        sno++;
//        driver.findElement(By.id("ccInitiateDepartment")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Department Select button", sno,false);
//        Thread.sleep(2000);
//        driver.findElement(By.id("treeContainer_2_switch")).click();
//        Thread.sleep(2000);
////        under
//        List<WebElement>departmentlist=driver.findElements(By.className("node_name"));
//        for (int i=0; i<departmentlist.size();i++)
//        {
//           String registerdepartment=properties.getProperty("registerdepartment");
//         if ((departmentlist.get(i).getText()).equalsIgnoreCase(registerdepartment))
//           {
//               departmentlist.get(i).click();
//               break;
//           }
//        }
//
//        sno++;
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
		Thread.sleep(2000);
		driver.findElement(By.id("ccShortDescriptionInCcInit")).sendKeys(properties.getProperty("CC_300"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter change control short description",
				sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"reasonJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("existMethodInReasonDlg")).sendKeys(properties.getProperty("CC_300"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter existing method procedure", sno,
				false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("propChangeInReasonDlg")).sendKeys(properties.getProperty("CC_300"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter proposed change", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("changeReasonInReasonDlg")).sendKeys(properties.getProperty("CC_300"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter reason for change", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addBtnInReasonsAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
		Thread.sleep(5000);
		sno++;
//        WebDriver driver = new FirefoxDriver();
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.xpath("//*[@id=\"attatchmentsJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]"));
//         jse.executeScript("arguments[0].scrollIntoView(true);", element);

		driver.findElement(By.xpath("//*[@id=\"attatchmentsJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("attachmentFileInCCInit_1")).sendKeys(properties.getProperty("Document-1"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate menu", sno, false);
		Thread.sleep(5000);
//        sno++;
//        driver.findElement(By.id("addBtnInReasonsAdd")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//        Thread.sleep(2000);
//        sno++;
//        JavascriptExecutor je = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.xpath(".//*[@id='attatchmentsJtableInCCInit']/div/div[3]/div[2]/span/span[2]"));
//        je.executeScript("arguments[0].scrollIntoView(true);", element);
//        driver.findElement(By.xpath(".//*[@id='attatchmentsJtableInCCInit']/div/div[3]/div[2]/span/span[2]")).click();
//      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno,false);
//      Thread.sleep(2000);
//      sno++;
//      driver.findElement(By.id("attachmentFileInCCInit_1")).sendKeys(properties.getProperty("Document-1 "));
//      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno,false);
//      Thread.sleep(2000);
		sno++;
		Select changetype = new Select(driver.findElement(By.id("ccChangeTypeInCcInit")));
		changetype.selectByIndex(2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the change type", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("implementationPlanInCcInit")).sendKeys(properties.getProperty("CC_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the implementation plan details", sno,
				false);
		Thread.sleep(5000);
//        driver.findElement(By.id("safetyAspectsOfPropChngInCcInit")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
//        Thread.sleep(5000);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(5000);
		sno++;
//        driver.findElement(By.xpath("//*[@id=\"content\"]/div[8]/div[1]/div[2]/label")).click();
		// suppDocYesInCcInit
		try {
			if (driver.findElement(By.xpath("//*[@id=\"suppDocYesInCcInit\"]")).isDisplayed()) {
				driver.findElement(By.xpath("//*[@id=\"suppDocYesInCcInit\"]")).click();

				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Supporting documents ",
						sno, false);
				Thread.sleep(4000);
				sno++;
				driver.findElement(By.id("ccAddSuppDocs")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
				Thread.sleep(4000);
				sno++;
				driver.findElement(By.id("supptDocumentForText1")).sendKeys(properties.getProperty("Document_Name"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document name", sno,
						false);
				Thread.sleep(5000);
				sno++;
				driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
				Thread.sleep(5000);

			}
		} catch (Exception e) {
			System.out.println("This Radion Button is not Displayed");
		}
		sno++;
		JavascriptExecutor jse1 = ((JavascriptExecutor) driver);
		jse1.executeScript("window.scrollBy(0,2500)");
		Thread.sleep(5000);
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        JavascriptExecutor scl1 = ((JavascriptExecutor) driver);
//        scl1.executeScript("window.scrollBy(0,-500)");
//        Thread.sleep(5000);
		driver.findElement(By.cssSelector("#productChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on product check box", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("documentChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on document check box", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("materialChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on material check box", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("instrumentChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on Instrument check box", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("equipmentChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on equipment check box", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("facilityChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on Facility check box", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("specificationChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on Specification check box", sno,
				false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("methodChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on Analytical method check box", sno,
				false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("othersChkInCcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on Others check box", sno, false);
		Thread.sleep(5000);
		JavascriptExecutor jse2 = ((JavascriptExecutor) driver);
		jse2.executeScript("window.scrollBy(0,1000)");
		sno++;
		driver.findElement(By.id("continueInitiateBtn")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Continue button", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"productJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add product", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("prodNameInCCProdDlg")).sendKeys(properties.getProperty("PRODUCT_NAME"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the product name", sno, false);
		Thread.sleep(5000);
		sno++;
		Select producttype = new Select(driver.findElement(By.id("prodTypeInCCProdDlg")));
		producttype.selectByIndex(1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the manufacturing process", sno,
				false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("addBtnInCCProdDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.cssSelector(
				"#documentJtableInCCInit > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text"))
				.click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add document", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("docTitleInCCDocDlg")).sendKeys(properties.getProperty("Document_Name"));
		;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document name", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addDetailsInCCDocDlg")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the additional details", sno, false);
		Thread.sleep(5000);
		sno++;
//         driver.findElement(By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
//        Thread.sleep(5000);
		driver.findElement(By.id("addBtnInCCDocDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"materialJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add material", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("materialNameInCCMaterDlg")).sendKeys(properties.getProperty("MATERIAL_NAME"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the material name", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addBtnInCCMaterDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"equipmentJtableInCCInit\"]/div/div[3]/div[2]/span/span[1]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add equipment", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("eqptNameInCCEqptDlg")).sendKeys(properties.getProperty("EQUIPMENT_NAME"));
		;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the equipment name", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addBtnInCCEqptDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse5 = (JavascriptExecutor) driver;
		WebElement element5 = driver
				.findElement(By.cssSelector("#specJtableInCCInit > div > div.jtable-title > div.jtable-title-text"));
		jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"specJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add specification", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("prodNameInCCSpecDlg")).sendKeys(properties.getProperty("PRODUCT_NAME"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the product name", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addBtnInCCSpecDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"instrumentJTableInCcInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add instruments", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("instrNoInCCInstrumentDlg")).sendKeys(properties.getProperty("INSTRUMENT_ID"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the instrument id", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("instrNameInCCInstrumentDlg")).sendKeys(properties.getProperty("INSTRUMENT_NAME"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the instrument name", sno, false);
		Thread.sleep(5000);
//        sno++;
//        Select instrumenttype = new Select(driver.findElement(By.id("instrTypeInCCInstrumentDlg")));
//        instrumenttype.selectByIndex(2);
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the instrument type", sno,false);
//        Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("instrLocInCCInstrumentDlg")).sendKeys(properties.getProperty("INSTRUMENT_LOCATION"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the location", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addDetailsInCCInstrumentDlg"))
				.sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the location", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addBtnInCCInstrmntDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add buttonu", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"facilityJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add facility", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("areaNameInCCFacilityDlg")).sendKeys(properties.getProperty("INSTRUMENT_LOCATION"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the area name", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addBtnInCCFacilityDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(5000);
		sno++;
//        JavascriptExecutor jse3 = (JavascriptExecutor) driver;
//        WebElement element3 = driver.findElement(By.id("submitBtnInCCInitWin"));
//        jse3.executeScript("arguments[0].scrollIntoView(true);", element3);
//        Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"analMethodJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add analytical method", sno,
				false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("prodNameInCCAnalMethDlg")).sendKeys(properties.getProperty("PRODUCT_NAME"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the product name", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addBtnInCCAnalMethDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("submitBtnInCCInitWin"));
		jse3.executeScript("arguments[0].scrollIntoView(true);", element3);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"othersJtableInCCInit\"]/div/div[3]/div[2]/span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add others", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("othersInCCOthersDlg")).sendKeys(properties.getProperty("CC_300"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter others", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addBtnInCCOthersDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(5000);
		WebElement deptHodCheck = driver.findElement(By.cssSelector(
				"#deptReviewUserDetailsContainer > div > table > thead > tr > th.jtable-command-column-header.jtable-column-header-selecting > div > input[type=checkbox]"));

		if (!deptHodCheck.isSelected())
			deptHodCheck.click();

		WebElement qareviewCheck = driver.findElement(By.cssSelector(
				"#qaReviewUserDetailsContainer > div > table > thead > tr > th.jtable-command-column-header.jtable-column-header-selecting > div > input[type=checkbox]"));

		if (!qareviewCheck.isSelected())
			qareviewCheck.click();

//        driver.findElement(By.xpath(".//*[@id='deptReviewUserDetailsContainer']/div/table/tbody/tr[2]/td[1]/input")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("addBtnInCCDocDlg")).click();
//        Thread.sleep(2000);

		// enble when there are multiple users are there for review

//        sno++;
//        driver.findElement(By.xpath("//*[@id=\"deptReviewUserDetailsContainer\"]/div/table/tbody/tr[1]/td[1]/input")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the deptreviewer user", sno,false);
//        Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.xpath("//*[@id=\"qaReviewUserDetailsContainer\"]/div/table/tbody/tr[7]/td[1]/input")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the qareviewer user", sno,false);
//        Thread.sleep(2000);
		JavascriptExecutor jse4 = (JavascriptExecutor) driver;
		WebElement element4 = driver.findElement(By.id("submitBtnInCCInitWin"));
		jse4.executeScript("arguments[0].scrollIntoView(true);", element4);
		Thread.sleep(1000);
		String CCString = "";
		sno++;
		driver.findElement(By.id("submitBtnInCCInitWin")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(4000);
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")))
				.sendKeys(properties.getProperty("ESIGN_PASSPWD"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the e-signature", sno, false);
		Thread.sleep(8000);
		sno++;
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(15000);
//        driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
//        Thread.sleep(6000);
//          CCString = driver.findElement(By.cssSelector("#modal-window > div > div > div.modal-text > center")).getText();
//          System.out.println("CCString: "+CCString);                                             
//        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center/text()[2]"));
		Thread.sleep(2000);
//        String separateCCNumber[] = CCString.split(" ");
//        System.out.println("separateCCNumber: "+separateCCNumber[5]);
//        String CCNo = separateCCNumber[5]; 
//        Thread.sleep(2000);
//        CCNo = CCNo.replace(")", ""); 
//        finalCCNumber = CCNo.trim();
//        System.out.println("finalCCNumber: "+finalCCNumber);
//        Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
		String Incident = driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[2]/center")).getText();
		String[] parts = Incident.split(" : ");
//		System.out.println(parts[1]);
		String IncidentNo = parts[1].replace(")", "").trim();
		System.out.println(IncidentNo);
		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src/test/java/com/pss/dms/properties/DMSProperties.properties");
		properties.setProperty("chgCtrlId", IncidentNo);
		properties.setProperty("ACTION_ITEM_chgCtrlId", IncidentNo + "/A1");
		properties.save();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
		sno++;
		driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
		sno++;
		Thread.sleep(5000);
		driver.findElement(By.className("username")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		sno++;
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);
	}

	@AfterMethod
	public void testIT(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}

}
