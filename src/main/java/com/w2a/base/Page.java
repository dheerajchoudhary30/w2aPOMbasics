package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Utilities;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.Reporter;

public class Page {

    public static WebDriver driver;
    public static TopMenu menu;
    public static WebDriverWait wait;

    public static Properties config = new Properties();
    public static Properties or = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(
            System.getProperty("user.dir") + "\\src\\main\\resources\\com\\w2a\\excel\\testdata.xlsx");
    public ExtentReports repo = ExtentManager.getInstance();
    public static ExtentTest test;
    public static String browser;

    public Page() {

        if (driver == null) {
            try {
                fis = new FileInputStream(
                        System.getProperty("user.dir") + "\\src\\main\\resources\\com\\w2a\\properties\\Config.properties");

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.debug("Config properties loaded!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(
                        System.getProperty("user.dir") + "\\src\\main\\resources\\com\\w2a\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                or.load(fis);
                log.debug("OR properties loaded!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            // This is the comment
            if((!System.getenv("browser").isEmpty()) && (System.getenv("browser")!=null)) {

                browser = System.getenv("browser");

            } else {
                browser = config.getProperty("browser");
            }

            config.setProperty("browser", browser);

            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");

            //this is amazings
            if (config.getProperty("browser").equals("chrome")) {
                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + "\\src\\main\\resources\\com\\w2a\\executables\\chromedriver.exe");
                driver = new ChromeDriver(options);
                log.debug("Chrome Opened!");
            } else if (config.getProperty("browser").equals("firefox")) {
                System.setProperty("webdriver.gecko.driver",
                        System.getProperty("user.dir") + "\\src\\main\\resources\\com\\w2a\\executables\\geckodriver.exe");
                driver = new FirefoxDriver();
                log.debug("Firefox Opened!");
            } else if (config.getProperty("browser").equals("ie")) {
                System.setProperty("webdriver.ie.driver",
                        System.getProperty("user.dir") + "\\src\\main\\resources\\com\\w2a\\executables\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                log.debug("IE Opened!");
            }

            driver.get(config.getProperty("testsiteurl"));
            log.debug("URL Loaded " + config.getProperty("testsiteurl"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
                    TimeUnit.SECONDS);

            wait = new WebDriverWait(driver, 10);
            menu = new TopMenu(driver);
        }
    }

    public static void switchToWindow(){
        Set<String> handles = driver.getWindowHandles();

        Iterator iterator = handles.iterator();
        while(iterator.hasNext()){
            driver.switchTo().window(iterator.next().toString());
        }


    }

    public static void click(String locator) {

        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(or.getProperty(locator))).click();
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(or.getProperty(locator))).click();
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(or.getProperty(locator))).click();
        } else if (locator.endsWith("_NAME")) {
            driver.findElement(By.name(or.getProperty(locator))).click();
        }

        log.debug("Clicking on a element : " + locator);
        test.log(LogStatus.INFO, "Clicked on: " + locator);

    }

    public static void type(String locator, String value) {

        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_NAME")) {
            driver.findElement(By.name(or.getProperty(locator))).sendKeys(value);
        }

        log.debug("Typing on a element : " + locator + "with value as: "+value);
        test.log(LogStatus.INFO, "Typing on : " + locator + " entered value as: " + value);

    }

    public boolean isElementPresent(By by) {

        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    static WebElement element;

    public static void select(String locator, String value) {
        if (locator.endsWith("_CSS")) {
            element = driver.findElement(By.cssSelector(or.getProperty(locator)));
        } else if (locator.endsWith("_XPATH")) {
            element = driver.findElement(By.xpath(or.getProperty(locator)));
        } else if (locator.endsWith("_ID")) {
            element = driver.findElement(By.id(or.getProperty(locator)));
        } else if (locator.endsWith("_NAME")) {
            element = driver.findElement(By.name(or.getProperty(locator)));
        }

        Select select = new Select(element);
        select.selectByVisibleText(value);

        log.debug("selecting an element as: " + locator + "having value : " + value);
        test.log(LogStatus.INFO, "Selecting from drop down: " + locator + " with Value: " + value);

    }




    public static void verifyEquals(String expected, String actual) throws IOException {

        try {
            AssertJUnit.assertEquals(actual, expected);
        } catch (Throwable t) {

            Utilities.captureScreenshot();
            // ReportNG
            Reporter.log("Verification Failure : " + t.getMessage() + "<br>");
            Reporter.log("Click to see Screenshot");
            Reporter.log("<a target=\"_BLANK\" href=" + Utilities.screenshotName + ">screenshot</a>");
            Reporter.log("<br>");
            Reporter.log("<br>");
            Reporter.log("<img src = " + Utilities.screenshotName + "  width=300 height = 300><a target=\"_BLANK\" href="
                    + Utilities.screenshotName + "></a></img>");

            // Extent Reports

            test.log(LogStatus.FAIL, " Verification failed with exception: " + t.getMessage());
            test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

        }

    }

    public static void quitAll(){
        if(driver!=null) {
            driver.close();
        }
    }



}
