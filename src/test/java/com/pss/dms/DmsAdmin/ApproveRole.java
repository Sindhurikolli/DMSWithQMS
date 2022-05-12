package com.pss.dms.DmsAdmin;

import com.pss.dms.login.QMSLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ApproveRole extends QMSLoginDetails {

	@Test
	public void toDoRoleApprove() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("myActivitiesInDMS")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dmsApprovalId\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dmsRoleAppPageId\"]/a")).click();
		Thread.sleep(1000);
		methodToDoRoleApproveInDms();
	}

	private void methodToDoRoleApproveInDms() throws InterruptedException {
		boolean isRecordSelectedForUsers = false;
		// String singleApprovalName = properties.getProperty("ROLE_NAME ");
		driver.findElement(By.xpath("//*[@id=\"dmsRoleAppTable\"]/div/table/tbody/tr/td[3]")).click();
		int count = 0;

		if (isRecordSelectedForUsers = true) {
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"dmsRoleAppTable\"]/div/table/tbody/tr/td[6]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("cmntsInDmsRoleAppWin")).sendKeys(properties.getProperty("COMMENTS"));
			Thread.sleep(3000);
			driver.findElement(By.id("appBtnInRoleAppWin")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("password"));
			Thread.sleep(3000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(1000);
		} else {
			System.out.println("NO Record Is Present");

		}

	}
}
