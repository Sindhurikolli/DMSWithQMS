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

public class DMSAssignRole extends QMSLoginDetails {

	@Test
	public void DMSAssignRoleMethod() throws InterruptedException {
		Thread.sleep(2000);
		driver.get(properties.getProperty("DMSURL"));
		Thread.sleep(5000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("ADMIN_Password"));
		Thread.sleep(1000);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[3]/button[1]")).click();
		Thread.sleep(5000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1500)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"dmsGenAction\"]/div/div[2]/a[3]/div")).click();
		Thread.sleep(5000);
		if (driver.findElement(By.id("NotificationTableContainer")).isDisplayed()) {
			System.out.println("Notification Window Displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
		}
//		driver.findElement(By.cssSelector("a[href='AdminAssignRolesForm.do']")).click();				
		Thread.sleep(5000);
		toDMSAssignRole();

	}

	private void toDMSAssignRole() throws InterruptedException {
		Thread.sleep(1000);

		driver.findElement(By.id("userSelBtnInAssignRoles")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("locationTreeForUserSelect_1_span")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("searchUserEmpCode")).sendKeys(properties.getProperty("DMSUserE_Code"));
		Thread.sleep(1000);
		driver.findElement(By.id("searchBtnInUserSelectDailog")).click();
		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[4]")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[4]")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("selBtnInUserSelectDailog")).click();
			Thread.sleep(3000);
			String RoleName = properties.getProperty("DMSRoleName");
			Select Roletoselect = new Select(
					driver.findElement(By.id("bootstrap-duallistbox-nonselected-list_dualListBoxContinAssignRoles")));
			List<WebElement> allOptionstoselect = Roletoselect.getOptions();
			int jSize = allOptionstoselect.size();
			String[] arrrolestoselect = new String[jSize];
			Thread.sleep(3000);
			for (int j = 0; j < jSize; j++) {
				arrrolestoselect[j] = allOptionstoselect.get(j).getText();
			}
			Select Roleselected = new Select(
					driver.findElement(By.id("bootstrap-duallistbox-selected-list_dualListBoxContinAssignRoles")));
			List<WebElement> allOptionsselected = Roleselected.getOptions();
			int iSize = allOptionsselected.size();
			String[] arrrolesselected = new String[iSize];
			Thread.sleep(1000);
			for (int i = 0; i < iSize; i++) {
				arrrolesselected[i] = allOptionsselected.get(i).getText();
			}
//        Thread.sleep(1000);
			if (ArrayUtils.contains(arrrolestoselect, RoleName)) {
				Roletoselect.selectByVisibleText(properties.getProperty("DMSRoleName"));
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
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();

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

}
