package com.teksenz.demo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebElementPractice {
    private final String baseUrl = "https://www.qaguru.ca/webelementapp.php";
    private WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void selectRadioButton(){
        WebElement radMale = driver.findElement(By.xpath("//*[@id=\"home-5\"]/form[2]/input[1]"));
        Assert.assertTrue(radMale.isSelected(),"Male radio button is not selected by default");

        driver.findElement(By.xpath("//*[@id=\"home-5\"]/form[2]/input[2]"))
                .click();

    }
    @Test
    public void selectFromListBox(){
        WebElement cars = driver.findElement(By.name("cars"));
        Select listbox = new Select(cars);
        listbox.selectByVisibleText("Audi");
        listbox.getOptions().forEach(webElement -> System.out.println(webElement.getText()));

    }

    @Test
    public void selectCheckBox() throws InterruptedException {
        WebElement webElement = driver.findElement(By.name("vehicle1"));
        Thread.sleep(500);
        System.out.println(webElement.isSelected());
        webElement.click();
    }

    @Test
    public void textBox() throws InterruptedException {
        WebElement webElement = driver.findElement(By.name("firstname"));
        Thread.sleep(500);
        webElement.sendKeys("John");
    }

    @Test
    public void pushButtonAndAlert() throws InterruptedException {
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"home-5\"]/button"));
        webElement.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    @Test
    public void calender() throws InterruptedException {
        WebElement webElement = driver.findElement(By.name("bday"));
        Thread.sleep(500);
        webElement.sendKeys("2000"+ Keys.TAB+ "1001");
    }

    @Test
    public void implicitWait() throws InterruptedException {
        WebElement webElement = driver.findElement(By.name("bday"));
        synchronized (driver)
        {
            driver.wait(1000);
        }
        webElement.sendKeys("2000"+ Keys.TAB+ "1001");
    }

    @Test
    public void explicitWait() throws InterruptedException {
        WebElement webElement = driver.findElement(By.name("bday"));
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).sendKeys("2000"+ Keys.TAB+ "1001");
    }
}
