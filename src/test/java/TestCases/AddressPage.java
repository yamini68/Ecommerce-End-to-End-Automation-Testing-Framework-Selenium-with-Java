package TestCases;

import PageObjects.AddingAddressPom;
import Utilites.DriversExecution;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddressPage extends DriversExecution {


    public static WebDriver initializing_driver(){
        WebDriver driver=getDriver();
        AddingAddressPom addingAddressPom=new AddingAddressPom(driver);
        driver.get("https://magento.softwaretestingboard.com/customer/address/index/");
        driver.manage().window().maximize();
        addingAddressPom.loginAction();
        return driver;
    }


    @Test(priority = 1)
    public void Successful_Navigation_To_AddressBook_Page(){
        String text="Address Book";
        WebDriver driver= initializing_driver();
        String title= driver.getTitle();
        Assert.assertEquals(title,text);
    }

    @Test(priority = 2)
    public void Enter_valid_Address_details() throws InterruptedException {
        WebDriver driver=initializing_driver();
        AddingAddressPom addingAddressPom=new AddingAddressPom(driver);
        addingAddressPom.addAddress();

    }

    @Test(priority = 3)
    public void Verify_Address_AddedSuccessfully_InList() throws InterruptedException {
        WebDriver driver=initializing_driver();
        String s="You saved the address.";
        AddingAddressPom addingAddressPom=new AddingAddressPom(driver);
        addingAddressPom.addAddress();
       String text= addingAddressPom.DisplayMessages();
       Assert.assertEquals(text,s);
    }

    @Test(priority = 4)
    public void Editing_the_default_address() throws InterruptedException {
         WebDriver driver=initializing_driver();
        AddingAddressPom addingAddressPom=new AddingAddressPom(driver);
        addingAddressPom.edit_address();

    }

    @Test(priority = 5)
    public void Verify_Address_Added_Successfully_After_Editing() throws InterruptedException {
        WebDriver driver=initializing_driver();
        AddingAddressPom addingAddressPom=new AddingAddressPom(driver);
        addingAddressPom.edit_address();
        String s="You saved the address.";
        String text= addingAddressPom.DisplayMessages();
        Assert.assertEquals(text,s);
    }

    @Test(priority = 6)
    public void Deleting_the_Address() throws InterruptedException {
        String msg="You deleted the address.";
        WebDriver driver=initializing_driver();
        AddingAddressPom addingAddressPom=new AddingAddressPom(driver);
        // Add an address to ensure firstNameEle and lastNameEle are set
        addingAddressPom.addAddress();
        // Now attempt to delete the address
        addingAddressPom.deleteAddress();
        String text=addingAddressPom.DisplayMessages();
        Assert.assertEquals(text,msg);
    }

    @Test(priority = 7)
    public void Incomplete_Address(){
        WebDriver driver=initializing_driver();
        AddingAddressPom addingAddressPom=new AddingAddressPom(driver);
        String msg= addingAddressPom.error();
        System.out.println(msg);
    }





}
