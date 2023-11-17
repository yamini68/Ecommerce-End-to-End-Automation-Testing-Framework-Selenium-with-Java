package ReportsTestNg;

import Utilites.DriversExecution;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.lang.reflect.Field;

public class Listeners extends DriversExecution implements ITestListener {

   public static WebDriver driver=getDriver();
    ExtentTest test;
    ExtentReports extents=Extentreports.getreportObject();
    ThreadLocal<ExtentTest> sync=new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result) {
        // Logic for when a test starts

        test =   extents.createTest(result.getMethod().getMethodName());
        sync.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Logic for when a test is successful
    }

    @Override
    public void onTestFailure(ITestResult result) {
        sync.get().fail(result.getThrowable());

        // Attempt to retrieve the WebDriver instance from the test class instance
        try {
            Field driverField = null;
            Class<?> currentClass = result.getTestClass().getRealClass();

            // Traverse the class hierarchy until the 'driver' field is found or no more superclass
            while (driverField == null && currentClass != null) {
                try {
                    driverField = currentClass.getDeclaredField("driver");
                    driverField.setAccessible(true); // Make the field accessible if it's private
                } catch (NoSuchFieldException e) {
                    // Move to the superclass if the field is not found in the current class
                    currentClass = currentClass.getSuperclass();
                }
            }

            // If the field was found, get its value, otherwise log an error
            if (driverField != null) {
                driver = (WebDriver) driverField.get(result.getInstance());
            } else {
                System.err.println("The driver field does not exist in the test class or any of its superclasses.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            String filepath = null;
            try {
                filepath = getScreenShot(result.getMethod().getMethodName(), driver);
                sync.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // Logic for when a test is finished
        extents.flush();
    }

}
