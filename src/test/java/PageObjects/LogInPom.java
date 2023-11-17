package PageObjects;

import Utilites.WebDriverWaitImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.PublicKey;
import java.time.Duration;

public class LogInPom {

    public WebDriver driver;
    public WebDriverWaitImplementation wait;
    public LogInPom(WebDriver rdriver){
        driver=rdriver;
        wait=new WebDriverWaitImplementation(driver);
        PageFactory.initElements(rdriver,this);
    }

    @FindBy(xpath = "//div[@class='panel header']//li[@class='authorization-link']//a")
    WebElement signIn;
    public void clickActionsignIn(){
        signIn.click();
    }

    @FindBy(xpath = "//input[@id='email_address']")
    WebElement email;
    public void EnterEmail(String str){
        email.clear();
        email.sendKeys(str);
    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement Actualemail;
    public void EnterActualemail(String str){
        Actualemail.clear();
        Actualemail.sendKeys(str);
    }

    @FindBy(xpath = "//fieldset[@class='fieldset login']//input[@id='pass']")
    WebElement Password;
    public void EnterPassword(String str){
        Password.clear();
        Password.sendKeys(str);
    }

    @FindBy(xpath = "//fieldset[@class='fieldset login']//button[@id='send2']")
    WebElement signInButton;
    public void clickSignInBuutton(){
        signInButton.click();
    }


    By welcomeText=By.xpath("//div[@class='panel header']//span[@class='logged-in']");


    public String textcheck() {

       String text=wait.fluentWaitForElementTextNotEmpty(welcomeText,100,30);
       return text;
    }




    By InvaildEmailmsg=By.xpath("//div[@id='email-error']");
    public String  showInvalidEmailmsg(){
        WebElement element=wait.wait_For_visibilityOfElementLocated(InvaildEmailmsg);
        String text=element.getText();
        return text;
    }

    @FindBy(xpath = "//div//a[@class='action remind']//span")
    WebElement ForgotPassword;
    public void clickForgotPassword(){
        ForgotPassword.click();
    }

    @FindBy(xpath = "//button[@class='action submit primary']")
    WebElement ResetButton;
    public void clickResetButton(){
        ResetButton.click();
    }

    By emailmsg=By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    public String DisplayMessages(){
        WebElement element=wait.wait_For_visibilityOfElementLocated(emailmsg);
        String text=element.getText();
        return text;
    }


}
