package PageObjects;

import Utilites.WebDriverWaitImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AddProductsToCartPom {

    public WebDriver driver;
    public WebDriverWaitImplementation FUNS_wait;
    public String email = "littlemajesty684@gmail.com";
    public String password = "Test@123";

    public AddProductsToCartPom(WebDriver rdriver) {
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

    public void DisplayOfProducts() {
        Actions action = new Actions(driver);
        WebElement mainmenuwait = FUNS_wait.wait_For_visibilityOfElementLocated(mainmenu_ele);
        action.moveToElement(mainmenuwait).build().perform();
        WebElement element = FUNS_wait.wait_For_visibilityOfElementLocated(submenu_ele);
        action.moveToElement(element).build().perform();
        WebElement finalOption = FUNS_wait.wait_For_visibilityOfElementLocated(jackets_ele);
        finalOption.click();

    }

    List<String> productNames = Arrays.asList("Cassia Funnel Sweatshirt",
            "Phoebe Zipper Sweatshirt", "Daphne Full-Zip Hoodie");

    public void addListOfProductsToCart() {
        addProductsToCart(productNames);
    }


    public void addProductsToCart(List<String> productIdentifiers) {
        for (String uniqueProductIdentifier : productIdentifiers) {
            // Use the previous method to add each product to the wishlist
            addSpecificProductToCart(uniqueProductIdentifier);

            // Assuming you need to wait for some confirmation after each addition
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("confirmation_message_xpath")));

            // You might need to navigate back to the products page if the action redirects you away
            // driver.get("URL_OF_THE_PRODUCTS_PAGE");
        }

    }

    @FindBy(xpath = "//form[@data-role='tocart-form']//button[@title='Add to Cart']")
    WebElement addToCartButton;
    By addToCartButton_ele = By.xpath("//form[@data-role='tocart-form']//button[@title='Add to Cart']");
    By sizeSelection = By.xpath("//div[@id='option-label-size-143-item-168']");
    @FindBy(xpath = "//div[@id='option-label-color-93-item-57']")
    WebElement colour;

    By colour_ele = By.xpath("//div[@id='option-label-color-93-item-57']");

    @FindBy(xpath = "//span[normalize-space()='Add to Cart']")
    WebElement button;

    By msg=By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    public String DisplayMessages(){

        WebElement element=FUNS_wait.wait_For_visibilityOfElementLocated(msg);
        String text=element.getText();
        return text;
    }


    public void addSpecificProductToCart(String uniqueProductIdentifier) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Locate the image element that represents the specific product
        WebElement specificProduct = driver.findElement(By.xpath("//img[@alt='" + uniqueProductIdentifier + "']//ancestor::div[contains(@class,'product-item-info')]"));

        // Hover over the product to reveal the 'Add to Cart' button
        Actions actionBuilder = new Actions(driver);
        actionBuilder.moveToElement(specificProduct).perform();

        // Locate the size and color options relative to the specific product
        WebElement sizeElement = specificProduct.findElement(By.xpath(".//div[contains(@class, 'swatch-attribute size')]//div[contains(@class, 'swatch-option')]"));
        wait.until(ExpectedConditions.visibilityOf(sizeElement));
        sizeElement.click(); // Click on the size option

        WebElement colorElement = specificProduct.findElement(By.xpath(".//div[contains(@class, 'swatch-attribute color')]//div[contains(@class, 'swatch-option')]"));
        wait.until(ExpectedConditions.visibilityOf(colorElement));
        colorElement.click(); // Click on the color option

        // Scroll to and click the 'Add to Cart' button
        WebElement addToCartButton = specificProduct.findElement(By.xpath(".//button[contains(@title, 'Add to Cart')]"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;

// Execute JavaScript to scroll to the top of the page
        js.executeScript("window.scrollTo(0, 0);");

       // FUNS_wait.wait_For_visibilityOfElementLocated(msg);
         DisplayMessages();





        // Additional waits or actions can be added here as needed
    }

    @FindBy(xpath = "//a[@class='action showcart']")
    WebElement showCart;
    By showcart=By.xpath("//a[@class='action showcart']");

    @FindBy(xpath = "//span[normalize-space()='OK']")
    WebElement confirm;
    By confirm_ele = By.xpath("//span[normalize-space()='OK']");
    //By deleteicon=By.xpath("//a[text()='" + uniqueProductIdentifier + "']/ancestor::div[contains(@class, 'product-item-details')]//a[@title='Remove item']");

    @FindBy(xpath = "//li[@class='item product product-item odd last']//a[@title='Remove item']")
    WebElement productDelete;

    public void addSpecificProductToCartAndDelete(String uniqueProductIdentifier) throws InterruptedException {
        // Existing code to add product to cart
        addSpecificProductToCart(uniqueProductIdentifier);
        // Assuming 'driver' is your WebDriver instance
        JavascriptExecutor js = (JavascriptExecutor) driver;

// Execute JavaScript to scroll to the top of the page
        js.executeScript("window.scrollTo(0, 0);");

// Now you can interact with the cart icon or any other elements at the top of the page
        WebElement cartIcon = driver.findElement(By.xpath("//a[@class='action showcart']")); // Replace with actual XPath
        cartIcon.click();


        // Navigate to the cart

//        WebElement cartIcon = driver.findElement(By.xpath("//a[@class='action showcart']")); // Replace with actual XPath
//        cartIcon.click();
        By deleteicon=By.xpath("//a[text()='" + uniqueProductIdentifier + "']/ancestor::div[contains(@class, 'product-item-details')]//a[@title='Remove item']");

        // Find the delete button for the specific product and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement del_element = wait.until(ExpectedConditions.visibilityOfElementLocated(deleteicon));
        del_element.click();
//        WebElement deleteButton = driver.findElement(By.xpath("//a[text()='" + uniqueProductIdentifier + "']/ancestor::div[contains(@class, 'product-item-details')]//a[@title='Remove item']"));
//        deleteButton.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait1.until(ExpectedConditions.visibilityOfElementLocated(confirm_ele));
        element.click();

    }

}
