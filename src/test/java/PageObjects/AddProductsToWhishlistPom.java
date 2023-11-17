package PageObjects;

import Utilites.WebDriverWaitImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddProductsToWhishlistPom {

    public WebDriver driver;
    public WebDriverWaitImplementation wait;
    public String email = "littlemajesty684@gmail.com";
    public String password = "Test@123";

    public AddProductsToWhishlistPom(WebDriver rdriver) {
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

    @FindBy(xpath = "//a[@id='ui-id-4']/span[contains(@class, 'ui-icon-carat-1-e')]")
    WebElement mainMenu;
    By mainmenu_ele=By.xpath("//a[@id='ui-id-4']/span[contains(@class, 'ui-icon-carat-1-e')]");

    @FindBy(xpath = "//a[@id='ui-id-9']")
    WebElement submenu;
    By submenu_ele = By.xpath("//a[@id='ui-id-9']");

    @FindBy(xpath = "//a[@id='ui-id-11']//span[contains(text(),'Jackets')]")
    WebElement jackets;
    By jackets_ele = By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]");

    public void DisplayOfProducts() {
        Actions action = new Actions(driver);
        WebElement mainmenuwait=wait.wait_For_visibilityOfElementLocated(mainmenu_ele);
        action.moveToElement(mainmenuwait).build().perform();
        WebElement element = wait.wait_For_visibilityOfElementLocated(submenu_ele);
        action.moveToElement(element).build().perform();
        WebElement finalOption = wait.wait_For_visibilityOfElementLocated(jackets_ele);
        finalOption.click();

    }





    List<String> productNames = Arrays.asList("Olivia 1/4 Zip Light Jacket",
            "Ingrid Running Jacket", "Riona Full Zip Jacket");



    public void addListOfProducts(){
        addProductsToWishlist(productNames);
    }


    public void addProductsToWishlist(List<String> productIdentifiers) {
        for (String uniqueProductIdentifier : productIdentifiers) {
            // Use the previous method to add each product to the wishlist
            addSpecificProductToWishlist(uniqueProductIdentifier);

            // Assuming you need to wait for some confirmation after each addition
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("confirmation_message_xpath")));

            // You might need to navigate back to the products page if the action redirects you away
            // driver.get("URL_OF_THE_PRODUCTS_PAGE");
        }

    }

    By here_ele=By.xpath("//a[normalize-space()='here']"); //button for navigation to productpage back after wishlist page

    public void addSpecificProductToWishlist(String uniqueProductIdentifier) {
        WebElement specificProduct = driver.findElement(By.xpath("//img[@alt='" + uniqueProductIdentifier + "']/ancestor::div[contains(@class,'product-item')]"));

        // Hover over the product to reveal the 'Add to Wishlist' button
        Actions actionBuilder = new Actions(driver);
        actionBuilder.moveToElement(specificProduct).perform();

        // Find the 'Add to Wishlist' button and click it
        WebElement wishlistButton = specificProduct.findElement(By.xpath(".//a[contains(@class, 'wishlist')]"));
        wishlistButton.click();

        WebElement element=wait.wait_For_visibilityOfElementLocated(here_ele);
        element.click();

    }

    By msg=By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    public String DisplayMessages(){

        WebElement element=wait.wait_For_visibilityOfElementLocated(msg);
        String text=element.getText();
        return text;
    }

    @FindBy(xpath = "//a[@title='Go to Wish List']")
    WebElement wishlistButton;
    By wishlist_ele=By.xpath("//a[@title='Go to Wish List']");

    @FindBy(xpath = "//div[@class='products-grid wishlist']//strong[@class='product-item-name']//a")
    List<WebElement> wishlistItems;

    public List<String> getProductsFromWishlist() {

        List<String> productNames = new ArrayList<>();
        for (WebElement item : wishlistItems) {
            // Get the text of the element, which is the product name
            productNames.add(item.getText().trim());
        }
        return productNames;
    }

    public boolean verifyProductsAddedToWishlist(List<String> expectedProductNames) {

        List<String> actualProductNames = getProductsFromWishlist();

        // Check if the list of actual product names contains all the expected product names
        return actualProductNames.containsAll(expectedProductNames);
    }

    public void verficationOfProducts(){

        wishlistButton.click();

        boolean isVerified = verifyProductsAddedToWishlist(productNames);
        if (isVerified) {
            System.out.println("All products were successfully added to the wishlist.");
        }
        else {
            System.out.println("Some products were not added to the wishlist.");
        }
    }

    public String Errormessage(String uniqueProductIdentifier){
        WebElement specificProduct = driver.findElement(By.xpath("//img[@alt='" + uniqueProductIdentifier + "']/ancestor::div[contains(@class,'product-item')]"));

        // Hover over the product to reveal the 'Add to Wishlist' button
        Actions actionBuilder = new Actions(driver);
        actionBuilder.moveToElement(specificProduct).perform();

        // Find the 'Add to Wishlist' button and click it
        WebElement wishlistButton = specificProduct.findElement(By.xpath(".//a[contains(@class, 'wishlist')]"));
        wishlistButton.click();
        String text =DisplayMessages();
        return text;
    }


    @FindBy(xpath = "//li[@id='item_13578']//input[contains(@id,'qty')]")
    WebElement qty;
    public void Quantity_check(String uniqueProductIdentifier){
        WebElement element=wait.wait_For_visibilityOfElementLocated(wishlist_ele);
        element.click();
        WebElement specificProduct = driver.findElement(By.xpath("//img[@alt='" + uniqueProductIdentifier + "']/ancestor::div[contains(@class,'product-item')]"));

        // Hover over the product to reveal the 'Add to Wishlist' button
        Actions actionBuilder = new Actions(driver);
        actionBuilder.moveToElement(specificProduct).perform();
        WebElement qtyInput =specificProduct.findElement(By.xpath("//a[contains(@href, 'olivia-1-4-zip-light-jacket') and @class='product-item-link']/ancestor::div[contains(@class, 'product-item-info')]//input[contains(@class, 'qty')]"));
        int initialQuantity = Integer.parseInt(qtyInput.getAttribute("value"));
        if(initialQuantity>1){
            System.out.println("Qutatity increased successfully"+initialQuantity);
        }
        else{
            System.out.println("quatity not increased successfully");
        }

    }

//    @FindBy(xpath = "//li[@id='item_13578']//a[@title='Remove Item']")
//    WebElement delete;

    public String deleteProduct(String uniqueProductIdentifier){
        wishlistButton.click();
        WebElement specificProduct = driver.findElement(By.xpath("//img[@alt='" + uniqueProductIdentifier + "']/ancestor::div[contains(@class,'product-item')]"));

        // Hover over the product to reveal the 'Add to Wishlist' button
        Actions actionBuilder = new Actions(driver);
        actionBuilder.moveToElement(specificProduct).perform();
        WebElement delete=specificProduct.findElement(By.xpath("//div[@class='products-grid wishlist']//li[contains(normalize-space(), 'Olivia 1/4 Zip Light Jacket')]//a[@data-role='remove']"));
        delete.click();
        String text = DisplayMessages();
        return text;

    }











}
