package com.pss.dms.LMSAdmin;

//import com.pss.dms.HelperPackageDms.Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pss.dms.login.QMSLoginDetails;

public class RoleCreationAdmin extends QMSLoginDetails {

	@Test
	public void toDoRolePage() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("adminTabId")).click();
		Thread.sleep(3000);
//	        driver.findElement(By.xpath("//*[@id=\"createRolesInLms\"]/a")).click();
//	        Thread.sleep(1000);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[2]/a"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
		createRolePageAccessMethod(driver);

	}
//	    @BeforeMethod
//	    public void setUpMethod() throws Exception {
//	    }

	private void createRolePageAccessMethod(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("roleNameInCreateDmsRoles")).sendKeys(properties.getProperty("ROLE_NAME"));
		Thread.sleep(3000);
		driver.findElement(By.id("appBySelBtnInDmsRolesReg")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("locationTreeForApproverSelect_1_span")).click();
		Thread.sleep(3000);
		boolean isRecordSelectedForApprover = false;
		int count = 0;
		String ApproverFirstName = properties.getProperty("SINGLE_APPROVAL");
		// String cftLastName ="reviewer3";
//	            String lastNameCFT ="reviewer3";

		String ApproverFullName = ApproverFirstName;
		System.out.println("ApproverFullName: " + ApproverFullName);
		int count2 = 0;
		isRecordSelectedForApprover = selectingTheApproverReview(ApproverFullName, isRecordSelectedForApprover, count);
		// driver.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[2]/td[2]")).click();

		Thread.sleep(3000);
		if (isRecordSelectedForApprover) {
			Thread.sleep(3000);
			driver.findElement(By.id("selBtnInAppSelectDailog")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("descInCreateDmsRoles")).sendKeys("ok");
			Thread.sleep(3000);
			WebElement element = driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[3]/a"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
//	        driver.findElement(By.xpath("//*[@id=\"maskingId\"]/div[3]/ul/li[3]/a")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
			Thread.sleep(3000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			Thread.sleep(1000);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(1000);
		}
	}

	private boolean selectingTheApproverReview(String approverFullName, boolean isRecordSelectedForApprover,
			int count) {
		WebElement table = driver.findElement(By.id("approversTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//     int totalNoOfRecords = 0;
//     int noOfRecordsChecked = 0;
//     if (perPageNoOfRecordsPresent > 0) {
//         String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//         String[] parts = a.split(" of ");
//         try {
//             totalNoOfRecords = Integer.parseInt(parts[1].trim());
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//     }
		// *[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if (((approverFullName == null) || ("".equalsIgnoreCase(approverFullName)))) {
				approverFullName = driver
						.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((approverFullName == null) || ("".equalsIgnoreCase(approverFullName))) {
				approverFullName = driver
						.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			// while (noOfRecordsChecked < totalNoOfRecords) {
			if (perPageNoOfRecordsPresent > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String ReviewerFullName = driver
							.findElement(By.xpath(
									"//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
							.getText();// documentTypeName
					System.out.println("ReviewerFullName: " + ReviewerFullName);
					if (approverFullName.equalsIgnoreCase(ReviewerFullName)) {
						driver.findElement(
								By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr[ " + i + "]/td[3]"))
								.click();
						isRecordSelectedForApprover = true;
						break;
					}
				}

			} else {
				String ReviewerFullName = driver
						.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();
				if (approverFullName.equalsIgnoreCase(ReviewerFullName)) {
					driver.findElement(By.xpath("//*[@id=\"approversTableContainer\"]/div/table/tbody/tr/td[3]"))
							.click();
					isRecordSelectedForApprover = true;

				}
			}
//             noOfRecordsChecked += perPageNoOfRecordsPresent;
//             if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                 driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                 Thread.sleep(3000);
//                 table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                 tableBody = table.findElement(By.tagName("tbody"));
//                 perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//             }
			// }
		}
		return isRecordSelectedForApprover;
	}
}
