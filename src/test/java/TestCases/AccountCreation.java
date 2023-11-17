package TestCases;

import PageObjects.AccountCerationPom;
import Utilites.DriversExecution;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AccountCreation extends DriversExecution {


    Faker faker = new Faker();
    public static String email;
    public static String password;

    @Test(priority = 1)
    public void Account_Creation_With_Valid_Details(){

        WebDriver driver=getDriver();
        AccountCerationPom accountCerationPom = new AccountCerationPom(driver);
        String msgtext="My Account";
        String msgtext1="Thank you for registering with Main Website Store.";
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        driver.manage().window().maximize();
        String firstName = faker.name().firstName();
        accountCerationPom.setFirstname(firstName);
        String lastname = faker.name().lastName();
        accountCerationPom.setLastname(lastname);
         email = faker.internet().emailAddress();
        accountCerationPom.setEmail(email);
         password= faker.internet().password(8, 16, true, true, true);
        accountCerationPom.setPassword(password);
        accountCerationPom.SetConformPassword(password);
        accountCerationPom.submitbutton();
        String title=driver.getTitle();
        Assert.assertEquals(title,msgtext);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String successtext=accountCerationPom.SuccessMessage();
        //System.out.println(successtext);
        Assert.assertEquals(successtext,msgtext1);

    }

    @Test(priority = 2)
    public void Successful_login_after_accountCreation(){

        WebDriver driver=getDriver();
        AccountCerationPom accountCerationPom = new AccountCerationPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        accountCerationPom.click_on_signIn_button();
        accountCerationPom.setLoginemail(email);
        accountCerationPom.setLoginpassword(password);
        accountCerationPom.setSignbutton();
        String text=driver.getTitle();
        Assert.assertEquals(text,"Home Page");

    }

   @Test(priority = 3)
   public void Account_creation_with_invalid_email_format(){
        String errormsg="Please enter a valid email address (Ex: johndoe@domain.com).";
       WebDriver driver=getDriver();
       AccountCerationPom accountCerationPom = new AccountCerationPom(driver);
       driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
       driver.manage().window().maximize();
       String firstName = faker.name().firstName();
       accountCerationPom.setFirstname(firstName);
       String lastname = faker.name().lastName();
       accountCerationPom.setLastname(lastname);
       String erroremail = faker.internet().emailAddress();
       String invalid_email = erroremail.replace('@', ' ');
       accountCerationPom.setEmail(invalid_email);
       accountCerationPom.submitbutton();
       String text=accountCerationPom.email_error_msg();
       Assert.assertEquals(text,errormsg);

    }

    @Test(priority = 4)
    public void Account_Creation_with_Registered_email(){
        String throwsError="There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.";
        WebDriver driver=getDriver();
        AccountCerationPom accountCerationPom = new AccountCerationPom(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        driver.manage().window().maximize();
        String firstName = faker.name().firstName();
        accountCerationPom.setFirstname(firstName);
        String lastname = faker.name().lastName();
        accountCerationPom.setLastname(lastname);
        accountCerationPom.setEmail(email);
        password= faker.internet().password(8, 16, true, true, true);
        accountCerationPom.setPassword(password);
        accountCerationPom.SetConformPassword(password);
        accountCerationPom.submitbutton();
       String text= accountCerationPom.Errormessage();
       Assert.assertEquals(text,throwsError);
        System.out.println(text);

    }

    @Test(priority = 5)
    public void Account_creation_short_Password(){
        String throwsError="Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.";
        WebDriver driver=getDriver();
        AccountCerationPom accountCerationPom = new AccountCerationPom(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        driver.manage().window().maximize();
        String firstName = faker.name().firstName();
        accountCerationPom.setFirstname(firstName);
        String lastname = faker.name().lastName();
        accountCerationPom.setLastname(lastname);
       String email3 = faker.internet().emailAddress();
        accountCerationPom.setEmail(email3);
        password= faker.internet().password(2, 4, false, false, true);
        accountCerationPom.setPassword(password);
        accountCerationPom.SetConformPassword(password);
        accountCerationPom.submitbutton();
       String text= accountCerationPom.password_error_msg();
       Assert.assertEquals(text,throwsError);
    }

    @Test(priority = 6)
    public void Account_creation_with_Missing_Mandatory_fields(){
        String ThrowsError="This is a required field.";
        WebDriver driver=getDriver();
        AccountCerationPom accountCerationPom = new AccountCerationPom(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        driver.manage().window().maximize();
        accountCerationPom.submitbutton();
        String text1= accountCerationPom.Firstname_error_msg();
        Assert.assertEquals(text1,ThrowsError);
        String text2= accountCerationPom.Lastname_error_msg();
        Assert.assertEquals(text2,ThrowsError);
        String text3= accountCerationPom.email_error_msg();
        Assert.assertEquals(text3,ThrowsError);
        String text4= accountCerationPom.password_error_msg();
        Assert.assertEquals(text4,ThrowsError);

    }
}
