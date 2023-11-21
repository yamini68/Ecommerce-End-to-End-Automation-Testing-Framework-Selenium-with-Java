package TestCases;

import PageObjects.AddProductsToCartPom;
import PageObjects.AddProductsToWhishlistPom;
import Utilites.DriversExecution;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class AddProductsToCart extends DriversExecution {

    public static WebDriver initializing_driver(){
        WebDriver driver=getDriver();
        AddProductsToCartPom addProductsToCartPom=new AddProductsToCartPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        addProductsToCartPom.logInAction();
        return driver;
    }

    @Test(priority = 1)
    public void Navigation_to_products_page(){
        WebDriver driver=initializing_driver();
        AddProductsToCartPom addProductsToCartPom=new AddProductsToCartPom(driver);
        addProductsToCartPom.DisplayOfProducts();

    }

    @Test(priority = 2)
    public void Add_Products_To_Cart() throws InterruptedException {
        WebDriver driver=initializing_driver();
        AddProductsToCartPom addProductsToCartPom=new AddProductsToCartPom(driver);
        addProductsToCartPom.DisplayOfProducts();
        addProductsToCartPom.addListOfProductsToCart();
    }

    @Test(priority =3)
    public void Delete_products_From_Cart() throws InterruptedException {
        WebDriver driver=initializing_driver();
        AddProductsToCartPom addProductsToCartPom=new AddProductsToCartPom(driver);
        addProductsToCartPom.DisplayOfProducts();
        addProductsToCartPom.addSpecificProductToCartAndDelete("Eos V-Neck Hoodie");


    }
}
