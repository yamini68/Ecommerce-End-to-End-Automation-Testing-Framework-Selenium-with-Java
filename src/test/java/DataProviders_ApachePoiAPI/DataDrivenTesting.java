package DataProviders_ApachePoiAPI;

import PageObjects.AddProductsToCartPom;
import Utilites.DriversExecution;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DataDrivenTesting extends DriversExecution {

    public static WebDriver initializing_driver(){
        WebDriver driver=getDriver();
        DataproviderImplentation dataproviderImplentation=new DataproviderImplentation(driver);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        dataproviderImplentation.logInAction();
        return driver;
    }

    @Test(dataProvider = "productDataProvider")
    public void testAddProductsToCart(List<String> productNames) throws InterruptedException {
        WebDriver driver=initializing_driver();
        DataproviderImplentation dataproviderImplentation=new DataproviderImplentation(driver);
        dataproviderImplentation.DisplayOfProducts();
        dataproviderImplentation.addListOfProductsToCart(productNames);
    }

    @DataProvider(name = "productDataProvider")
    public Object[][] productDataProvider() {
        return new Object[][]{
                {Arrays.asList("Cassia Funnel Sweatshirt", "Phoebe Zipper Sweatshirt", "Daphne Full-Zip Hoodie")},
                {Arrays.asList("Miko Pullover Hoodie", "Autumn Pullie", "Hera Pullover Hoodie")} // Add another set of products here
        };
    }
}
