package TestCases;

import PageObjects.AddProductsToCartPom;
import PageObjects.ProductsCheckoutPom;
import Utilites.DriversExecution;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class ProductsCheckout extends DriversExecution {

    public static WebDriver initializing_driver(){
        WebDriver driver=getDriver();
        ProductsCheckoutPom productsCheckoutPom=new ProductsCheckoutPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        productsCheckoutPom.logInAction();
        return driver;
    }



    @Test
    public void Navigation_to_checkout_Page() throws InterruptedException {

        WebDriver driver=initializing_driver();
        ProductsCheckoutPom productsCheckoutPom=new ProductsCheckoutPom(driver);
        productsCheckoutPom.checkoutPage();

    }

    @Test
    public void Place_Order() throws InterruptedException {

        WebDriver driver=initializing_driver();
        ProductsCheckoutPom productsCheckoutPom=new ProductsCheckoutPom(driver);
        productsCheckoutPom.placeOrder();


    }
}
