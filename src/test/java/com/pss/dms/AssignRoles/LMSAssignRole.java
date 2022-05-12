package com.pss.dms.AssignRoles;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pss.dms.login.QMSLoginDetails;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LMSAssignRole extends QMSLoginDetails {

	@Test
	public void LMSAssignRoleMethod() throws InterruptedException {
		Thread.sleep(2000);
		driver.get(properties.getProperty("LMSURL"));
		Thread.sleep(3000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("ADMIN_Password"));
		Thread.sleep(1000);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/button[1]")).click();
		Thread.sleep(5000);
//		if(driver.findElement(By.id("NotificationTableContainer")).isDisplayed())
//		{
//			System.out.println("Notification Window Displayed");
//			Actions action = new Actions(driver);
//			action.sendKeys(Keys.ESCAPE).perform();
//		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.id("adminTabIdInGeneralAct")).click();
		Thread.sleep(5000);
//		if(driver.findElement(By.id("NotificationTableContainer")).isDisplayed())
//		{
//			System.out.println("Notification Window Displayed");
//			Actions action = new Actions(driver);
//			action.sendKeys(Keys.ESCAPE).perform();
//		}

		Thread.sleep(5000);
		toLMSAssignRole();

	}

	private void toLMSAssignRole() throws InterruptedException {
		Thread.sleep(1000);

		driver.findElement(By.id("userSelBtnInAssignRoles")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("userSelTree_1_span")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("firstName")).sendKeys(properties.getProperty("LMSUserFirstName"));
		Thread.sleep(1000);
		driver.findElement(By.id("lastName")).sendKeys(properties.getProperty("LMSUserLastName"));
		Thread.sleep(1000);
		driver.findElement(By.id("usersSearchBtnId")).click();
		Thread.sleep(3000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		String LMSUserLastName = properties.getProperty("LMSUserLastName");
		int count = 0;
		boolean isRecordSelected = false;
		isRecordSelected = selectRecordUser(LMSUserLastName, isRecordSelected, count);
		if (isRecordSelected) {

			Thread.sleep(3000);
			driver.findElement(By.id("selBtnInUsers")).click();
			Thread.sleep(3000);
			String RoleName = properties.getProperty("LMSRoleName");
			Select Roletoselect = new Select(
					driver.findElement(By.id("bootstrap-duallistbox-nonselected-list_dualListBoxContinAssignRoles")));
			List<WebElement> allOptionstoselect = Roletoselect.getOptions();
			int jSize = allOptionstoselect.size();
			String[] arrrolestoselect = new String[jSize];
//        Thread.sleep(3000);
			for (int j = 0; j < jSize; j++) {
				arrrolestoselect[j] = allOptionstoselect.get(j).getText();
			}
			Select Roleselected = new Select(
					driver.findElement(By.id("bootstrap-duallistbox-selected-list_dualListBoxContinAssignRoles")));
			List<WebElement> allOptionsselected = Roleselected.getOptions();
			int iSize = allOptionsselected.size();
			String[] arrrolesselected = new String[iSize];
//        Thread.sleep(1000);
			for (int i = 0; i < iSize; i++) {
				arrrolesselected[i] = allOptionsselected.get(i).getText();
			}
//        Thread.sleep(1000);
			if (ArrayUtils.contains(arrrolestoselect, RoleName)) {
				Roletoselect.selectByVisibleText(properties.getProperty("LMSRoleName"));
				Thread.sleep(2000);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,250)");
				Thread.sleep(1000);
				driver.findElement(By.id("submitBtnInAssignRoles")).click();
				Thread.sleep(3000);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
				Thread.sleep(1000);
				driver.findElement(By.id("subBtnInValidateESign")).click();
				Thread.sleep(3000);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
				driver.findElement(By.className("modal-btn")).click();
				Thread.sleep(2000);

			} else if (ArrayUtils.contains(arrrolesselected, RoleName)) {
				System.out.println("Role is Already Selected");
			} else {
				System.out.println("Role Not Available to Select");
			}

			driver.findElement(By.className("username")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			Thread.sleep(1000);
		} else {
			System.out.println("User Not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordUser(String LMSUserLastName, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("usersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((LMSUserLastName == null) || ("".equalsIgnoreCase(LMSUserLastName)))) {
				LMSUserLastName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((LMSUserLastName == null) || ("".equalsIgnoreCase(LMSUserLastName))) {
				LMSUserLastName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String LMSUserLastNameSequence = driver
								.findElement(By
										.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();// documentTypeName
						if (LMSUserLastName.equalsIgnoreCase(LMSUserLastNameSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String LMSUserLastNameSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (LMSUserLastName.equalsIgnoreCase(LMSUserLastNameSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelected;
	}
}
