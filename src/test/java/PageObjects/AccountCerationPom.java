package PageObjects;

import Utilites.WebDriverWaitImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.time.Duration;

public class AccountCerationPom {

    public WebDriver driver;
    public WebDriverWaitImplementation wait;
    public AccountCerationPom(WebDriver rdriver){
        driver=rdriver;
        wait=new WebDriverWaitImplementation(driver);
        PageFactory.initElements(rdriver,this);
    }

    @FindBy(id="firstname")
    WebElement Firstname;

    @FindBy(id="lastname")
    WebElement Lastname;

    @FindBy(id="email_address")
    WebElement Email;

    @FindBy(id="password")
    WebElement Password;

    @FindBy(id="password-confirmation")
    WebElement ConformPassword;

    @FindBy(xpath = "//button[@title='Create an Account']")
    WebElement AccountCreationButton;

    //div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']

    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    WebElement message;

    //sign in or login page



    @FindBy(xpath = "//input[@name='login[username]']")
    WebElement loginemail;

    @FindBy(xpath = "//input[@name='login[password]']")
    WebElement loginpassword;

    @FindBy(xpath = "//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")
    WebElement signbutton;

    @FindBy(xpath = "//div[@class='panel header']//span[@role='button']")
    WebElement expandIcon;

    @FindBy(xpath = "//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")
    WebElement signout;

    @FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
    WebElement signin;

    public void setFirstname(String fname){
        Firstname.clear();
        Firstname.sendKeys(fname);
    }

    public void setLastname(String lname){
        Lastname.clear();
        Lastname.sendKeys(lname);
    }

    public void setEmail(String Uemail){
        Email.clear();
        Email.sendKeys(Uemail);
    }

    public void setPassword(String Upassword){
        Password.clear();
        Password.sendKeys(Upassword);
    }

    public void SetConformPassword(String Upassword){
        ConformPassword.clear();
        ConformPassword.sendKeys(Upassword);
    }

    public void submitbutton(){

        AccountCreationButton.click();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    By msg=By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    public String  SuccessMessage(){

//        wait.waitForVisiabilityOfWebElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
//        String text=message.getText();
//        return text;

       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
      //  WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"))).get(0);
        WebElement element = wait.wait_For_visibilityOfElementLocated(msg);
        String text = element.getText();
        return text;


    }


    public void click_on_signIn_button(){
        signin.click();
    }


    //testcase-2

    public void setSignin(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(expandIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(signout)).click();
        wait.until(ExpectedConditions.elementToBeClickable(signin)).click();

//        expandIcon.click();
//        signout.click();
//        signin.click();
    }

    public void setLoginemail(String email){

        loginemail.clear();
        loginemail.sendKeys(email);
    }

    public void setLoginpassword(String password){
        loginpassword.clear();
        loginpassword.sendKeys(password);
    }

    public void setSignbutton(){
        signbutton.click();
    }

    //testcase3

    @FindBy(xpath = "//div[@class='panel header']//a[normalize-space()='Create an Account']")
    WebElement accountcretion;

    public void setAccountcretion(){
        accountcretion.click();
    }

    public String  Errormessage(){

//        wait.waitForElementToAppear(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
//        String text=message.getText();
//        return text;

        WebElement element = wait.wait_For_visibilityOfElementLocated(msg);
        String text = element.getText();
        return text;


    }

    //Testcase-4

//    @FindBy(xpath = "//div[@id='email_address-error']")
//    WebElement errormsg;
    By firstnameError= By.xpath("//div[@id='firstname-error']");
    By lastnameError= By.xpath("//div[@id='lastname-error']");
    By errormsg= By.xpath("//div[@id='email_address-error']");
    By errormsg1=By.xpath("//div[@id='password-error']");

    public String Firstname_error_msg(){
        WebElement element = wait.wait_For_visibilityOfElementLocated(firstnameError);
        String text = element.getText();
        return text;

    }

    public String Lastname_error_msg(){
        WebElement element = wait.wait_For_visibilityOfElementLocated(lastnameError);
        String text = element.getText();
        return text;

    }

    public String email_error_msg(){
        WebElement element = wait.wait_For_visibilityOfElementLocated(errormsg);
        String text = element.getText();
        return text;

    }

    public String password_error_msg(){
        WebElement element = wait.wait_For_visibilityOfElementLocated(errormsg1);
        String text = element.getText();
        return text;
    }


    public void CreateAccountOption() {
        System.out.println("hello");
    }
}
