package TestCases;

import PageObjects.AccountCerationPom;
import PageObjects.LogInPom;
import Utilites.DriversExecution;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogIn extends DriversExecution {

    Faker faker = new Faker();
    public static String email="littlemajesty684@gmail.com";
    public static String password="Test@123";
    public static String msg="Welcome, Anala yamini!";

    @Test(priority = 1)
    public void LogIn_with_Valid_Details() throws InterruptedException {
        WebDriver driver=getDriver();
        LogInPom logInPom = new LogInPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        logInPom.clickActionsignIn();
        logInPom.EnterActualemail(email);
        logInPom.EnterPassword(password);
        logInPom.clickSignInBuutton();

        String text=logInPom.textcheck();
        System.out.println(text);
        Assert.assertEquals(text,msg);

    }

    @Test(priority = 2)
    public void LogIn_with_Invalid_email(){
        WebDriver driver=getDriver();
        String msg="The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        LogInPom logInPom = new LogInPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        logInPom.clickActionsignIn();
        String erroremail = faker.internet().emailAddress();
        String invalid_email = erroremail.replace('@', ' ');
        logInPom.EnterActualemail(erroremail);
        logInPom.EnterPassword(password);
        logInPom.clickSignInBuutton();
        String text=logInPom.DisplayMessages();
        System.out.println(text);
        Assert.assertEquals(text,msg);

    }

    @Test(priority = 3)
    public void LogIn_with_Invalid_Password(){
        WebDriver driver=getDriver();
        String msg="Th account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        LogInPom logInPom = new LogInPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        logInPom.clickActionsignIn();
        logInPom.EnterActualemail(email);
        String  Invalidpassword= faker.internet().password(2, 3, false, true, true);
        logInPom.EnterPassword(Invalidpassword);
        logInPom.clickSignInBuutton();
        String text=logInPom.DisplayMessages();
        System.out.println(text);
        Assert.assertEquals(text,msg);



    }

    @Test(priority = 4)
    public void LogIn_with_Unregisterd_Details(){
        WebDriver driver=getDriver();
        String msg="The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        LogInPom logInPom = new LogInPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        logInPom.clickActionsignIn();
        String validemail = faker.internet().emailAddress();
        logInPom.EnterActualemail(validemail);
        String validpassword=faker.internet().password(8, 16, true, true, true);
        logInPom.EnterPassword(validpassword);
        logInPom.clickSignInBuutton();
        String text=logInPom.DisplayMessages();
        System.out.println(text);
        Assert.assertEquals(text,msg);



    }

    @Test(priority = 5)
    public void Password_Recovery(){
        WebDriver driver=getDriver();
        LogInPom logInPom = new LogInPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        logInPom.clickActionsignIn();
        //logInPom.EnterEmail(email);
        logInPom.clickForgotPassword();
        logInPom.EnterEmail(email);
        logInPom.clickResetButton();
        String text=logInPom.DisplayMessages();
        System.out.println(text);
    }


}
