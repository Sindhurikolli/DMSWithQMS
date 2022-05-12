package com.pss.dms.Listerners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class AbstractWebDriverListeners extends AbstractWebDriverEventListener {

	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		if (driver.findElement(By.id("ui-id-39")).isDisplayed()) {
			driver.findElement(By.id("sessionStay")).click();
			System.out.println("Clicked on Session Stay");
		}
	}

}
