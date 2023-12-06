package DataProviders_ApachePoiAPI;

import PageObjects.LogInPom;
import Utilites.WebDriverWaitImplementation;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class DataproviderImplentation  {

    public WebDriver driver;
    public WebDriverWaitImplementation FUNS_wait;
    public String email = "littlemajesty684@gmail.com";
    public String password = "Test@123";

    public DataproviderImplentation(WebDriver rdriver) {
        driver = rdriver;
        FUNS_wait = new WebDriverWaitImplementation(driver);
        PageFactory.initElements(rdriver, this);
    }

    public void logInAction() {
        LogInPom logInPom = new LogInPom(driver);
        logInPom.clickActionsignIn();
        logInPom.EnterActualemail(email);
        logInPom.EnterPassword(password);
        logInPom.clickSignInBuutton();
    }

    @FindBy(xpath = "//a[@id='ui-id-4']/span[contains(@class, 'ui-icon-carat-1-e')]")
    WebElement mainMenu;
    By mainmenu_ele = By.xpath("//a[@id='ui-id-4']/span[contains(@class, 'ui-icon-carat-1-e')]");

    @FindBy(xpath = "//a[@id='ui-id-9']")
    WebElement submenu;
    By submenu_ele = By.xpath("//a[@id='ui-id-9']");

    @FindBy(xpath = "//a[@id='ui-id-12']//span[contains(text(),'Hoodies & Sweatshirts')]")
    WebElement jackets;
    By jackets_ele = By.xpath("//a[@id='ui-id-12']//span[contains(text(),'Hoodies & Sweatshirts')]");

    List<String> productNames = Arrays.asList("Cassia Funnel Sweatshirt",
            "Phoebe Zipper Sweatshirt", "Daphne Full-Zip Hoodie");

    @FindBy(xpath = "//form[@data-role='tocart-form']//button[@title='Add to Cart']")
    WebElement addToCartButton;

    By addToCartButton_ele = By.xpath(".//button[contains(@title, 'Add to Cart')]");

    By sizeSelection = By.xpath("//div[@id='option-label-size-143-item-168']");

    @FindBy(xpath = "//div[@id='option-label-color-93-item-57']")
    WebElement colour;

    By colour_ele = By.xpath("//div[@id='option-label-color-93-item-57']");

    @FindBy(xpath = "//span[normalize-space()='Add to Cart']")
    WebElement button;

    By msg=By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");

    @FindBy(xpath = "//a[@class='action showcart']")
    WebElement showCart;
    By showcart=By.xpath("//a[@class='action showcart']");

    @FindBy(xpath = "//span[normalize-space()='OK']")
    WebElement confirm;

    By confirm_ele = By.xpath("//span[normalize-space()='OK']");
    //By deleteicon=By.xpath("//a[text()='" + uniqueProductIdentifier + "']/ancestor::div[contains(@class, 'product-item-details')]//a[@title='Remove item']");

    @FindBy(xpath = "//li[@class='item product product-item odd last']//a[@title='Remove item']")
    WebElement productDelete;




    public void DisplayOfProducts() {
        Actions action = new Actions(driver);
        WebElement mainmenuwait = FUNS_wait.wait_For_visibilityOfElementLocated(mainmenu_ele);
        action.moveToElement(mainmenuwait).build().perform();
        WebElement element = FUNS_wait.wait_For_visibilityOfElementLocated(submenu_ele);
        action.moveToElement(element).build().perform();
        WebElement finalOption = FUNS_wait.wait_For_visibilityOfElementLocated(jackets_ele);
        finalOption.click();

    }



    public void addListOfProductsToCart(List<String> productNames) throws InterruptedException {
        addProductsToCart(productNames);
    }


    public void addProductsToCart(List<String> productIdentifiers) throws InterruptedException {
        for (String uniqueProductIdentifier : productIdentifiers) {
            addSpecificProductToCart(uniqueProductIdentifier);
        }

    }

    public String DisplayMessages(){

        WebElement element=FUNS_wait.wait_For_visibilityOfElementLocated(msg);
        String text=element.getText();
        return text;
    }

    public void addSpecificProductToCart(String uniqueProductIdentifier) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Locate the image element that represents the specific product
        WebElement specificProduct = driver.findElement(By.xpath("//img[@alt='" + uniqueProductIdentifier + "']//ancestor::div[contains(@class,'product-item-info')]"));

        // Hover over the product to reveal the 'Add to Cart' button
        actions.moveToElement(specificProduct).perform();

        // Locate and click the size and color options
        WebElement sizeElement = specificProduct.findElement(By.xpath(".//div[contains(@class, 'swatch-attribute size')]//div[contains(@class, 'swatch-option')]"));
        wait.until(ExpectedConditions.visibilityOf(sizeElement)).click();
        WebElement colorElement = specificProduct.findElement(By.xpath(".//div[contains(@class, 'swatch-attribute color')]//div[contains(@class, 'swatch-option')]"));
        wait.until(ExpectedConditions.visibilityOf(colorElement)).click();



        WebElement addToCartButton = specificProduct.findElement(By.xpath(".//button[@title='Add to Cart']"));
        js.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        try {
            addToCartButton.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Standard click failed, attempting JavaScript click.");
            js.executeScript("arguments[0].click();", addToCartButton);
        }

        // Scroll to the top of the page if needed
        js.executeScript("window.scrollTo(0, 0);");

        // Display any messages
        DisplayMessages();
        // Additional waits or actions can be added here as needed
    }


    public void addSpecificProductToCartAndDelete(String uniqueProductIdentifier) throws InterruptedException {
        // Existing code to add product to cart
        addSpecificProductToCart(uniqueProductIdentifier);
        // Assuming 'driver' is your WebDriver instance
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Execute JavaScript to scroll to the top of the page
        js.executeScript("window.scrollTo(0, 0);");

       // Now you can interact with the cart icon or any other elements at the top of the page
        WebElement cartIcon = driver.findElement(By.xpath("//a[@class='action showcart']"));
        By carticon_ele=By.xpath("//a[@class='action showcart']");
        WebElement element1= FUNS_wait.wait_For_visibilityOfElementLocated(carticon_ele);
        element1.click();


        // Navigate to the cart

        By deleteicon=By.xpath("//a[text()='" + uniqueProductIdentifier + "']/ancestor::div[contains(@class, 'product-item-details')]//a[@title='Remove item']");

        // Find the delete button for the specific product and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement del_element = wait.until(ExpectedConditions.visibilityOfElementLocated(deleteicon));
        del_element.click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait1.until(ExpectedConditions.visibilityOfElementLocated(confirm_ele));
        element.click();

    }

}
