package com.pss.dms.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.pss.dms.login.QMSLoginDetails;

public class Utilities extends QMSLoginDetails {

	public static Document getScreenShotAndAddInLogDoc(WebDriver driver, Document document, String logStatements,
			Integer sno, boolean isLastSS) throws Exception {
		byte[] input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

		Image im = Image.getInstance(input);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);

//		im.scaleToFit(PageSize.A4.getWidth() / 3, PageSize.A4.getHeight() / 3);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno.toString() + "." + logStatements + Utilities.prepareSSNumber(sno), font));
		document.add(im);
		if (sno % 2 != 0) {

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
		}
		if (!isLastSS) {
			if (sno % 2 == 0) {
				document.newPage();
			}
		}
		return document;
	}

	public static String prepareSSNumber(int seqNum) {
		StringBuilder ssNo = new StringBuilder();
		StringBuilder sequenceNoZeroChars = new StringBuilder();
		while (sequenceNoZeroChars.length() != 3 - String.valueOf(seqNum).length()) {
			sequenceNoZeroChars.append("0");
		}
		ssNo.append("\n SSNo : ").append(Constants.SS_NO_PATTERN).append(sequenceNoZeroChars.toString()).append(seqNum);
		return ssNo.toString();
	}

	public static void qmsLoginMethod(String username, String password) throws InterruptedException {

		driver.findElement(By.name("loginUserName")).sendKeys(username);

		driver.findElement(By.name("loginPassword")).sendKeys(password);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		module.selectByIndex(2);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='loginform']/div[7]/button[1]")).click();

		Thread.sleep(5000);
	}

}
