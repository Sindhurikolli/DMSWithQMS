package com.pss.dms.login;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.dms.Listerners.WebDriverListener;
import com.pss.dms.util.DynamicBrowser;

public class QMSLoginDetails {
	public static Properties properties;
	public static WebDriver driver;
	private static RemoteWebDriver driver1;
	public static String finalCCNumber = "";
	private static String browserName = System.getProperty("browser");
	private static String url = System.getProperty("url");
//	private static String loginUserName = System.getProperty("username");
//	private static String loginUserPassword = System.getProperty("userpassword");
	private static String driverPath = System.getProperty("driverPath");
	private String propertiesFilePath = System.getProperty("propertiesPathFile");

	protected byte[] input;
	protected Document document;
	protected String output;
	protected FileOutputStream fos;
	protected Integer sno = 1;
	protected PdfWriter writer;
	protected Image im;

	@BeforeClass

	public void setUp() throws Exception {

		System.out.println("Before Login");
		System.out.println("browser");
		String propertyFilePathName = "";
		if (propertiesFilePath != null) {
			propertyFilePathName = propertiesFilePath;
		} else {
			propertyFilePathName = "src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties";
		}
		final String propertyFilePath = propertyFilePathName;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("DMSProperties.properties not found at " + propertyFilePath);
		}

		Thread.sleep(2000);
		System.out.println("browerName : " + browserName);
		if ((url != null) && (browserName != null) && (driverPath != null)) {
			System.out.println("With Command Prompt");
			if (browserName.equalsIgnoreCase("IEDriverServer")) {
				System.setProperty("webdriver.ie.driver", driverPath);
				driver = new InternetExplorerDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {

				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			} else if (browserName.equalsIgnoreCase("chromedriver")) {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}
			driver.get(url);
//			driver.findElement(By.name("loginUserName")).sendKeys(loginUserName);
//			Thread.sleep(1000);
//			driver.findElement(By.name("loginPassword")).sendKeys(loginUserPassword);
//			Thread.sleep(1000);
		} else if (DynamicBrowser.browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			WebDriverWait wait = new WebDriverWait(driver, 120);
//            driver.get(properties.getProperty("QMSLoginUrl"));
			driver.manage().window().maximize();
		} else if (DynamicBrowser.browserName.equalsIgnoreCase("Remote")) {
			DesiredCapabilities dr = DesiredCapabilities.chrome();

			dr.setBrowserName("chrome");
			dr.setPlatform(Platform.WIN10);

//			 dr.setVersion("90.0.4430.212");
			RemoteWebDriver driver1 = new RemoteWebDriver(new URL("http://10.0.0.17:4444/wd/hub"), dr);
			driver = driver1;

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 120);
//           driver.get(properties.getProperty("EDMSLoginUrl"));
			driver.manage().window().maximize();
		} else if (DynamicBrowser.browserName.equalsIgnoreCase("headlesschrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1200");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			// driver.get(properties.getProperty("QMSLoginUrl"));
			driver.manage().window().maximize();

		}
//		else if (DynamicBrowser.browserName.equalsIgnoreCase("html")){
//			//driver = new HtmlUnitDriver();
//			((HtmlUnitDriver)driver).setJavascriptEnabled(true);
//			//driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//          //  WebDriverWait wait1 = new WebDriverWait(driver, 120);
//		}

		else {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.get("chrome://settings/clearBrowserData");
			driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//            driver.get(properties.getProperty("QMSLoginUrl"));
			driver.manage().window().maximize();
		}

//		System.out.println("url : " + url);
//		System.out.println("loginUserName : " + loginUserName);
//		System.out.println("loginUserPassword : " + loginUserPassword);
		System.out.println("propertiesFilePath: " + propertiesFilePath);

		System.out.println("QMSLoginDetails --- END");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println(" After Logout");
		driver.quit();
		// WebDriver driver = new HtmlUnitDriver();
	}

}
