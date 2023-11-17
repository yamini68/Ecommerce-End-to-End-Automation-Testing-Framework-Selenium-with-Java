package TestCases;

import PageObjects.AddProductsToWhishlistPom;
import PageObjects.AddingAddressPom;
import Utilites.DriversExecution;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;



public class AddProductsToWishList extends DriversExecution {

    public static WebDriver initializing_driver(){
        WebDriver driver=getDriver();
        AddProductsToWhishlistPom addProductsToWhishlistPom=new AddProductsToWhishlistPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        addProductsToWhishlistPom.logInAction();
        return driver;
    }

    @Test(priority = 1)
    public void Navigation_Through_products_Page(){
        WebDriver driver=initializing_driver();
        AddProductsToWhishlistPom addProductsToWhishlistPom=new AddProductsToWhishlistPom(driver);
        addProductsToWhishlistPom.DisplayOfProducts();
        System.out.println(driver.getTitle());
    }

    @Test(priority = 2)
    public void Add_a_product_to_Wishlist_after_login(){
        WebDriver driver=initializing_driver();
        AddProductsToWhishlistPom addProductsToWhishlistPom=new AddProductsToWhishlistPom(driver);
        addProductsToWhishlistPom.DisplayOfProducts();
        addProductsToWhishlistPom.addListOfProducts();
    }

    @Test(priority = 3)
    public void Verfiy_wheather_product_added_successfully_in_Whishlist(){
        WebDriver driver=initializing_driver();
        AddProductsToWhishlistPom addProductsToWhishlistPom=new AddProductsToWhishlistPom(driver);
        addProductsToWhishlistPom.DisplayOfProducts();
        addProductsToWhishlistPom.verficationOfProducts();

    }

    @Test(priority = 4)
    public void Add_items_to_wishlist_before_Login(){
        WebDriver driver=getDriver();
        String item="Olivia 1/4 Zip Light Jacket";
        AddProductsToWhishlistPom addProductsToWhishlistPom=new AddProductsToWhishlistPom(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        addProductsToWhishlistPom.DisplayOfProducts();
        String msg=addProductsToWhishlistPom.Errormessage(item);
        System.out.println(msg);

    }

    @Test(priority = 5)
    public void Verify_Wheather_QTY_Increases_On_Adding_Same_Product_Again(){
        WebDriver driver=initializing_driver();
        String item="Olivia 1/4 Zip Light Jacket";
        AddProductsToWhishlistPom addProductsToWhishlistPom=new AddProductsToWhishlistPom(driver);
        addProductsToWhishlistPom.DisplayOfProducts();
        addProductsToWhishlistPom.Quantity_check(item);

    }

    @Test(priority = 6)
    public void Delete_a_Product_from_whishList(){
        WebDriver driver=initializing_driver();
        String item="Olivia 1/4 Zip Light Jacket";
        AddProductsToWhishlistPom addProductsToWhishlistPom=new AddProductsToWhishlistPom(driver);
        addProductsToWhishlistPom.DisplayOfProducts();
        String msg = addProductsToWhishlistPom.deleteProduct(item);
        System.out.println(msg);
    }

}
