/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.LMSAdmin;

import com.pss.dms.HelperPackageDms.Helper;
import com.pss.dms.login.QMSLoginDetails;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RoleBaseAccessDmsAdminForUsers extends QMSLoginDetails {

	@Test
	public void toDoRoleBaseAccessDmsAdmin() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("adminTabId")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[8]/a")).click();
		Thread.sleep(1000);
		methodToDoRolePageAccessDmsAdminForUsers();
	}

	private void methodToDoRolePageAccessDmsAdminForUsers() throws InterruptedException {
		Thread.sleep(1000);
		Select rolesDropDownList = Helper.getSelectInstance(driver, By.id("rolesInDmsRoleBaseAccPage"));
		List<WebElement> webElementList = rolesDropDownList.getOptions();
		if (webElementList.size() > 0) {
			rolesDropDownList
					.selectByIndex(Integer.parseInt(properties.getProperty("ROLE_ID_DMS_ROLE_PAGE_ACCESS_USERS")));
		} else {
			System.out.println("No options in Select(Roles - drop down list)");
		}
		Thread.sleep(1000);
		// Selecting All menus in My Activities Tab
		driver.findElement(By.id("selectAllForMyActivities")).click();
		Thread.sleep(1300);
		driver.findElement(By.id("submitButtonInDmsRoleBaseAccess")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
		Thread.sleep(1000);
		driver.findElement(By.id("subBtnInValidateESign")).click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
		Thread.sleep(1000);
		driver.findElement(By.className("modal-btn")).click();
		Thread.sleep(1000);

	}
}
