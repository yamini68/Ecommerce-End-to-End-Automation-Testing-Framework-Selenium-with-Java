package PageObjects;

import Utilites.WebDriverWaitImplementation;
import com.github.javafaker.Faker;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import java.security.PublicKey;

public class AddingAddressPom  {

    public  WebDriver driver;
    public WebDriverWaitImplementation wait;
    public  String email="littlemajesty684@gmail.com";
    public  String password="Test@123";
    static Faker faker = new Faker();
    public  String firstNameEle;
    public  String lastNameEle;
    public AddingAddressPom(WebDriver rdriver){
        driver=rdriver;
        wait=new WebDriverWaitImplementation(driver);
        PageFactory.initElements(rdriver,this);
    }

    public void loginAction(){
        LogInPom logInPom=new LogInPom(driver);
        logInPom.EnterActualemail(email);
        logInPom.EnterPassword(password);
        logInPom.clickSignInBuutton();
    }

    @FindBy(xpath = "//input[@id='firstname']")
    WebElement firstname;

    @FindBy(xpath = "//input[@id='lastname']")
    WebElement lastname;

    @FindBy(xpath = "//input[@id='company']")
    WebElement company;

    @FindBy(xpath = "//input[@id='telephone']")
    WebElement telephone;

    @FindBy(xpath = "//input[@id='street_1']")
    WebElement streetAddress_1;

    @FindBy(xpath = "//input[@id='street_2']")
    WebElement streetAddress_2;

    @FindBy(xpath = "//input[@id='city']")
    WebElement city;

    @FindBy(xpath = "//select[@id='region_id']")
    WebElement state;

    @FindBy(xpath = "//input[@id='zip']")
    WebElement zipcode;

    @FindBy(xpath = "//select[@id='country']")
    WebElement country;


    @FindBy(xpath = "//button[@title='Save Address']")
    WebElement saveAddress;

    @FindBy(xpath = "//button[@title='Add New Address']")
    WebElement addNewAddress;


   public void addressing_details(){
       firstname.clear();
       firstNameEle = faker.name().firstName();
       lastNameEle=faker.name().lastName();
       firstname.sendKeys(firstNameEle);
       lastname.clear();
       lastname.sendKeys(lastNameEle);
       company.clear();
       company.sendKeys("goldmansachs");
       telephone.clear();
       telephone.sendKeys(faker.phoneNumber().cellPhone());
       streetAddress_1.clear();
       streetAddress_1.sendKeys(faker.address().streetAddress());
       streetAddress_2.clear();
       streetAddress_2.sendKeys(faker.address().streetAddress());
       city.sendKeys(faker.address().city());
       Select select1=new Select(country);
       select1.selectByValue("IN");
       Select select=new Select(state);
       select.selectByVisibleText("Telangana");
       zipcode.sendKeys(faker.address().zipCode());
       saveAddress.click();
   }

    public  void addAddress() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //WebElement element = driver.findElement(By.id("yourButtonId"));
        js.executeScript("arguments[0].scrollIntoView(true);",addNewAddress );
        addNewAddress.click();
        addressing_details();

    }

    By msg=By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    public String DisplayMessages(){
        WebElement element=wait.wait_For_visibilityOfElementLocated(msg);
        String text=element.getText();
        return text;
    }

    @FindBy(xpath="//div[@class='box box-address-billing']//div[@class='box-actions']//a//span")
    static WebElement changeBillingAddress;

    public void edit_address() throws InterruptedException {
        changeBillingAddress.click();
        addressing_details();

    }

//    @FindBy(xpath = "//table/tbody/tr[td[@class='col firstname' and text()='" + firstNameEle + "'] and td[@class='col lastname' and text()='" + lastNameEle + "']]/td[@class='col actions']//a[contains(@class, 'delete')]")
//    WebElement addressDelete;

    @FindBy(xpath = "//button[@class='action-primary action-accept']")
    WebElement okButton;
    By ele=By.xpath("//button[contains(@class,'action-primary action-accept')]");

    public WebElement getAddressDeleteButton() {
        String xpathExpression = "//table/tbody/tr[td[@class='col firstname' and text()='" + firstNameEle + "'] and td[@class='col lastname' and text()='" + lastNameEle + "']]/td[@class='col actions']//a[contains(@class, 'delete')]";
        return driver.findElement(By.xpath(xpathExpression));
    }
    public void deleteAddress() {
        WebElement addressDeleteButton = getAddressDeleteButton();
        addressDeleteButton.click();
        WebElement element= wait.wait_For_visibilityOfElementLocated(ele);
        element.click();

    }

    @FindBy(xpath = "//div[@id='telephone-error']")
    WebElement Error;
    By Error_ele=By.xpath("//div[@id='telephone-error']");

    public String  error(){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //WebElement element = driver.findElement(By.id("yourButtonId"));
        js.executeScript("arguments[0].scrollIntoView(true);",addNewAddress );
        addNewAddress.click();
        firstname.clear();
        firstNameEle = faker.name().firstName();
        firstname.sendKeys(firstNameEle);
        company.clear();
        company.sendKeys("goldmansachs");
        saveAddress.click();
        WebElement element=wait.wait_For_visibilityOfElementLocated(Error_ele);
        String text= element.getText();
        return text;
    }












}
