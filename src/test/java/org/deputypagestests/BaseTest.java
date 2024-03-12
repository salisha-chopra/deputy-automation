package org.deputypagestests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class BaseTest {
    protected static WebDriver driver;


    //@BeforeTest
    //@Parameters({"targetBrowser"})
//    public void setDriver(){
//        //System.out.println("Parameterized value is: " + targetBrowser);
//        String targetBrowser = System.getProperty("targetBrowser");
//        if(targetBrowser == null || targetBrowser.equalsIgnoreCase("Chrome")) {
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("--start-maximized");
//            chromeOptions.addArguments("--disable-popup-blocking");
//            driver = new ChromeDriver(chromeOptions);
//        } else if (targetBrowser.equalsIgnoreCase("Edge")) {
//            EdgeOptions edgeOptions = new EdgeOptions();
//            edgeOptions.addArguments("--start-maximized");
//            edgeOptions.addArguments("--disable-popup-blocking");
//            driver = new EdgeDriver(edgeOptions);
//        } else if (targetBrowser.equalsIgnoreCase("Firefox")) {
//            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.addArguments("--start-maximized");
//            firefoxOptions.addArguments("--disable-popup-blocking");
//            driver = new FirefoxDriver(firefoxOptions);
//        } else {
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("--start-maximized");
//            chromeOptions.addArguments("--disable-popup-blocking");
//            driver = new ChromeDriver(chromeOptions);
//        }
//        String url = "https://www.deputy.com//";
//        driver.get(url);
//        System.out.println(driver);
//    }

    @BeforeTest
    public void setDriver(ITestContext context){

        //System.out.println("Parameterized value is: " + targetBrowser);
        String targetBrowser = System.getenv("targetBrowser");
        System.out.println(targetBrowser);
        if(targetBrowser == null || targetBrowser.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(chromeOptions);
            System.out.println(driver);
        } else if (targetBrowser.equalsIgnoreCase("Edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--start-maximized");
            edgeOptions.addArguments("--disable-popup-blocking");
            driver = new EdgeDriver(edgeOptions);
        } else if (targetBrowser.equalsIgnoreCase("Firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--start-maximized");
            firefoxOptions.addArguments("--disable-popup-blocking");
            driver = new FirefoxDriver(firefoxOptions);
        } else {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(chromeOptions);
        }
        System.out.println("driver: " + driver);
        context.setAttribute("driver", driver);
    }
//    @AfterTest
//    public void tearDown(){
//        driver.quit();
//    }


}
