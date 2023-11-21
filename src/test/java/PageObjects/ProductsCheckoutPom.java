package PageObjects;

import Utilites.WebDriverWaitImplementation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public void checkoutPage() throws InterruptedException {
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

//    public void placeOrder() throws InterruptedException {
//
//        // Assuming AddProductsToCartPom and other necessary elements are initialized correctly
//        AddProductsToCartPom addProductsToCartPom = new AddProductsToCartPom(driver);
//        addProductsToCartPom.DisplayOfProducts();
//        addProductsToCartPom.addListOfProductsToCart();
//
//        // Wait for the cart icon to be clickable and then click
//
//
//        WebElement cartIcon = wait.wait_For_visibilityOfElementLocated(cart_icon);
//        cartIcon.click();
//
//        // Wait for the checkout button to be clickable and then click
//        WebElement checkoutButton =wait.wait_For_visibilityOfElementLocated(checkbtn_ele);
//        checkoutButton.click();
//       // Thread.sleep(4000l);
//
//        // Wait for a specific title or element that indicates the page has loaded
//        wait.wait_For_visibilityOfElementLocated(title);
//
//        // Scroll to the 'Next' button and click
//        WebElement nextBtnElement = wait.wait_For_visibilityOfElementLocated(nextBtn);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true);", nextBtnElement);
//        js.executeScript("arguments[0].click();", nextBtnElement);
//
//
//        // Wait for the 'Place Order' button to be visible and then click
//        WebElement placeOrderButton = wait.wait_For_visibilityOfElementLocated(placeOrderBtn);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
//        placeOrderButton.click();
//
//       Thread.sleep(4000l);
//
//        WebElement msg=wait.wait_For_visibilityOfElementLocated(succes_msg);
//        String text=msg.getText();
//        System.out.println(text);
//    }

    public void placeOrder() throws InterruptedException {

        // Assuming AddProductsToCartPom and other necessary elements are initialized correctly
        AddProductsToCartPom addProductsToCartPom = new AddProductsToCartPom(driver);
        addProductsToCartPom.DisplayOfProducts();
        addProductsToCartPom.addListOfProductsToCart();

        // Wait for the cart icon to be clickable and then click
        WebElement cartIcon = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(cart_icon));
        clickWithRetry(cart_icon, 3);


        // Wait for the checkout button to be clickable and then click
        WebElement checkoutButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(checkbtn_ele));
       // clickWithRetry(checkbtn_ele, 3);
        checkoutButton.click();

        // Wait for the page to load completely
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Wait for a specific title or element that indicates the page has loaded
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkout-loader")));

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(title));

        // Scroll to the 'Next' button and click

        WebElement nextBtnElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(nextBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtnElement);
       // nextBtnElement.click();
        clickWithRetry(nextBtn, 3);

        // Wait for the 'Place Order' button to be visible and then click
        WebElement placeOrderButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(placeOrderBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
        clickWithRetry(placeOrderBtn, 3);
        //placeOrderButton.click();

//        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
//                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, Duration.ofSeconds(20))
  .until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/checkout/onepage/success/"));

        // Wait for the success message to be visible
        WebElement msg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(succes_msg));
        String text = msg.getText();
        System.out.println(text);
    }

    public void clickWithRetry(By by, int retries) throws InterruptedException {
        for (int i = 0; i < retries; i++) {
            try {
                // Wait for any potential overlays to disappear before each attempt
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));

                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(by));
                element.click();
                return; // If click is successful, exit the method
            } catch (ElementClickInterceptedException e) {
                System.out.println("Click Intercepted, retrying... Attempt: " + (i + 1));
                // Add a brief wait before retrying
                Thread.sleep(1000);
            }
        }
        throw new RuntimeException("Failed to click element after " + retries + " retries");
    }



}
