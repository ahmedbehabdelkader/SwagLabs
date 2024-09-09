package org.example;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public WebDriver driver;
    public String expected_title;
    public String actual_title;

        @BeforeTest
        public void launchbrowser() {
            System.out.println("launching Chrome browser");
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://www.saucedemo.com/");
        }
       
        @Test(priority = 0)
          public void loginwithvalidusernameandpassword(){
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            expected_title="Swag Labs";
            actual_title= driver.getTitle();
            Assert.assertEquals(expected_title,actual_title);
        }
    @Test(priority = 1)
    public void loginwithinvalidusernameandvalidpassword(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("Ahmed");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String expected_txt="Epic sadface: Username and password do not match any user in this service";
        String actual_txt=driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(expected_txt,actual_txt);
    }
    @Test(priority = 2)
    public void loginwithvalidusernameandinvalidpassword(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("hello");
        driver.findElement(By.id("login-button")).click();
        String expected_text="Epic sadface: Username and password do not match any user in this service";
        String actual_text=driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(expected_text,actual_text);
    }
    @Test(priority = 3)
    public void loginwithinvalidusernameandinvalidpassword(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("Mido");
        driver.findElement(By.id("password")).sendKeys("hello");
        driver.findElement(By.id("login-button")).click();
        String expected="Epic sadface: Username and password do not match any user in this service";
        String actual =driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(expected,actual);
    }
    @Test(priority = 4)
    public void productsnamefunctionality(){
        driver.get("https://www.saucedemo.com/inventory.html");
        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
        String expected ="Sauce Labs Backpack";
        String actual=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();
        Assert.assertEquals(expected,actual);

    }
     
    @Test
    public void productpricesortlowtohigh(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        List<WebElement> beforesortprice=driver.findElements(By.className(".inventory_item_price"));
        List<Double> beforesortpriceList=new ArrayList<>();


        Select drpdown=new Select(driver.findElement(By.className("product_sort_container")));
        drpdown.selectByVisibleText("Price (low to high)");

        List<WebElement> aftersortprice=driver.findElements(By.className(".inventory_item_price"));
        List<Double> aftersortpriceList=new ArrayList<>();
        Collections.sort(beforesortpriceList);
        Assert.assertEquals(beforesortpriceList,aftersortpriceList);
    }
     
    @Test
    public void productpricesorthightolow(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        List<WebElement> beforesortprice=driver.findElements(By.className(".inventory_item_price"));
        List<Double> beforesortpriceList=new ArrayList<>();

        Select drpdown=new Select(driver.findElement(By.className("product_sort_container")));
        drpdown.selectByVisibleText("Price (high to low)");

        List<WebElement> aftersortprice=driver.findElements(By.className(".inventory_item_price"));
        List<Double> aftersortpriceList=new ArrayList<>();

        Collections.sort(beforesortpriceList);
        Assert.assertEquals(beforesortpriceList,aftersortpriceList);

    }
     

    @Test
    public void productsortfromZtoA(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        List<WebElement> beforesort=driver.findElements(By.className(".inventory_item_price"));
        List<String> beforesortList=new ArrayList<>();


        Select sortZtoA=new Select(driver.findElement(By.className("product_sort_container")));
        sortZtoA.selectByVisibleText("Name (Z to A)");

        List<WebElement> aftersort=driver.findElements(By.className(".inventory_item_price"));
        List<String> aftersortList=new ArrayList<>();

        Collections.sort(beforesortList);
        Assert.assertEquals(beforesortList,aftersortList);
    }
     
    @Test
    public void addtocart(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.get("https://www.saucedemo.com/cart.html");

        List<WebElement> cartitemList=driver.findElements(By.className("inventory_item_name"));
        String actual=cartitemList.getFirst().getText();
        String expected="Sauce Labs Backpack";
        Assert.assertEquals(actual,expected);
    }
    
     @Test
    public void removefromcart(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.get("https://www.saucedemo.com/cart.html");
        driver.findElement(By.name("remove-sauce-labs-backpack")).click();
        List<WebElement> cartlist=driver.findElements(By.className("inventory_item_name"));
        boolean cart=cartlist.isEmpty();
        boolean expected=true;
        Assert.assertEquals(cart,expected);
    }
    
    @Test
    public void Logout(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
        String expected_url="https://www.saucedemo.com/";
        String acual_url=driver.getCurrentUrl();
        Assert.assertEquals(expected_url,acual_url);

    }

    }
