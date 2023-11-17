package Utilites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class DriversExecution {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        // Setup Chrome WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Initialize the ChromeDriver and assign it to the class-level driver variable
        driver = new ChromeDriver();
    }

    public String getScreenShot(String testcase,WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        File file= new File(System.getProperty("user.dir")+ "\\Reports\\"+ testcase +".png");
        FileUtils.copyFile(source,file);
        return  testcase +".png";

    }

    @BeforeMethod
    public void setUp() {
        getDriver();
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Set driver to null
            System.out.println("Driver quit successfully");
        }
    }

}
