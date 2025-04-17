package com.nop.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.UUID;

public class RegisterTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testRegistration() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/register");

        // Gender
        driver.findElement(By.id("gender-male")).click();

        // First name
        driver.findElement(By.id("FirstName")).sendKeys("Jay");

        // Last name
        driver.findElement(By.id("LastName")).sendKeys("Soni");

        // Email (random for uniqueness)
        String email = "jay" + UUID.randomUUID().toString().substring(0, 5) + "@mail.com";
        driver.findElement(By.id("Email")).sendKeys(email);

        // Password & Confirm
        driver.findElement(By.id("Password")).sendKeys("Test@1234");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Test@1234");

        // Click register
        driver.findElement(By.id("register-button")).click();

        Thread.sleep(2000); // Optional wait

        // Validate success message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement myAccountLink = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.ico-account"))
        );  
        Assert.assertTrue(myAccountLink.isDisplayed(), "Registration might have failed - 'My account' link not visible.");
        System.out.println("✅ Registration successful. 'My account' link is visible.");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
