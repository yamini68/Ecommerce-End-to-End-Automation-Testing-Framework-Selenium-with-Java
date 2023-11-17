package PageObjects;

import Utilites.WebDriverWaitImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsCheckoutPom {

    public WebDriver driver;
    public WebDriverWaitImplementation wait;
    public String email = "littlemajesty684@gmail.com";
    public String password = "Test@123";

    public ProductsCheckoutPom(WebDriver rdriver) {
        driver = rdriver;
        wait = new WebDriverWaitImplementation(driver);
        PageFactory.initElements(rdriver, this);
    }

    public void logInAction() {
        LogInPom logInPom = new LogInPom(driver);
        logInPom.clickActionsignIn();
        logInPom.EnterActualemail(email);
        logInPom.EnterPassword(password);
        logInPom.clickSignInBuutton();
    }

    @FindBy(xpath = "//button[@id='top-cart-btn-checkout']")
    WebElement checkoutBtn;
    By checkbtn_ele=By.xpath("//button[@id='top-cart-btn-checkout']");

    public void checkoutPage(){
        AddProductsToCartPom addProductsToCartPom=new AddProductsToCartPom(driver);
        addProductsToCartPom.DisplayOfProducts();
        addProductsToCartPom.addListOfProductsToCart();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        WebElement cartIcon = driver.findElement(By.xpath("//a[@class='action showcart']"));
        cartIcon.click();
        checkoutBtn.click();
        System.out.println(driver.getTitle());
    }

    By msg=By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    public String DisplayMessages(){

        WebElement element=wait.wait_For_visibilityOfElementLocated(msg);
        String text=element.getText();
        return text;
    }

    @FindBy(xpath = "//span[normalize-space()='Next']")
    WebElement nextBtn_ele;
    By nextBtn=By.xpath("//span[normalize-space()='Next']");
    By placeOrderBtn=By.xpath("//span[normalize-space()='Place Order']");
    By title=By.xpath("//span[@class='title']");
    By cart_icon=By.xpath("//a[@class='action showcart']");
    By succes_msg=By.xpath("//span[@class='base']");

    public void placeOrder() throws InterruptedException {

        // Assuming AddProductsToCartPom and other necessary elements are initialized correctly
        AddProductsToCartPom addProductsToCartPom = new AddProductsToCartPom(driver);
        addProductsToCartPom.DisplayOfProducts();
        addProductsToCartPom.addListOfProductsToCart();

        // Wait for the cart icon to be clickable and then click


        WebElement cartIcon = wait.wait_For_visibilityOfElementLocated(cart_icon);
        cartIcon.click();

        // Wait for the checkout button to be clickable and then click
        WebElement checkoutButton =wait.wait_For_visibilityOfElementLocated(checkbtn_ele);
        checkoutButton.click();
       // Thread.sleep(4000l);

        // Wait for a specific title or element that indicates the page has loaded
        wait.wait_For_visibilityOfElementLocated(title);

        // Scroll to the 'Next' button and click
        WebElement nextBtnElement = wait.wait_For_visibilityOfElementLocated(nextBtn);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", nextBtnElement);
        js.executeScript("arguments[0].click();", nextBtnElement);


        // Wait for the 'Place Order' button to be visible and then click
        WebElement placeOrderButton = wait.wait_For_visibilityOfElementLocated(placeOrderBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
        placeOrderButton.click();

       Thread.sleep(4000l);

        WebElement msg=wait.wait_For_visibilityOfElementLocated(succes_msg);
        String text=msg.getText();
        System.out.println(text);
    }


}
