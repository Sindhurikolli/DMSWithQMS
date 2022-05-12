/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.dms.LMSAdmin;

import com.pss.dms.HelperPackageDms.Helper;
import com.pss.dms.login.QMSLoginDetails;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jeevan
 */
public class RoleBaseAccessDmsAdminForAdmin extends QMSLoginDetails {

	public RoleBaseAccessDmsAdminForAdmin() {
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//

	@Test
	public void toDoRoleBaseAccessDmsAdminForAdmin() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("bmrHomeInCommonHPHeader")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("adminTabId")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[8]/a")).click();
		Thread.sleep(1000);
		methodToDoRolePageAccessDmsAdminForAdmin();
	}

	private void methodToDoRolePageAccessDmsAdminForAdmin() throws InterruptedException {
		Thread.sleep(1000);
		Select rolesDropDownList = Helper.getSelectInstance(driver, By.id("rolesInDmsRoleBaseAccPage"));
		List<WebElement> webElementList = rolesDropDownList.getOptions();
		if (webElementList.size() > 0) {
			rolesDropDownList
					.selectByIndex(Integer.parseInt(properties.getProperty("ROLE_ID_DMS_ROLE_PAGE_ACCESS_ADMIN")));
		} else {
			System.out.println("No options in Select(Roles - drop down list)");
		}
		Thread.sleep(1000);
		// Selecting All menus in Dms Admin
		driver.findElement(By.id("selectAllForDmsAdmin")).click();
		Thread.sleep(1000);
		// Selecting All menus in My Activities Tab
		driver.findElement(By.id("selectAllForMyActivities")).click();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.id("termUserActivityInMyAct"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		// Selecting All menus in Master List
		driver.findElement(By.xpath("//*[@id=\"masterListInDmsInRoleBase\"]")).click();
		Thread.sleep(3000);
		// Selecting All menus in Obsolete List
		driver.findElement(By.xpath("//*[@id=\"obsListTabInDms\"]")).click();
		Thread.sleep(1000);
		// Selecting All menus in Terminate List
		driver.findElement(By.id("termListTabInDms")).click();
		Thread.sleep(1000);
		// Selecting All menus in Format Issuance
		driver.findElement(By.id("selectAllForFormatIssunace")).click();
		Thread.sleep(1000);

		// Selecting All menus in WorkFlow
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.id("selectAllForLifeCycle"));
		jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
		Thread.sleep(2000);
		driver.findElement(By.id("selectAllForLifeCycle")).click();
		Thread.sleep(1000);
		// Selecting All menus in Status
		driver.findElement(By.id("selectAllForDMS")).click();
		Thread.sleep(1000);
		// Selecting All menus in Reports
		driver.findElement(By.xpath("//*[@id=\"selectAllForReports\"]")).click();
		Thread.sleep(1000);
		// Selecting All menus in Audit Trails
		driver.findElement(By.id("selectAllForAuditTrialsInDMS")).click();
		Thread.sleep(1200);
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
