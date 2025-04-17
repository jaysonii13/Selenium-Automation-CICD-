package com.nop.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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
        WebElement successMsg = driver.findElement(By.cssSelector("div.result"));
        Assert.assertTrue(successMsg.getText().contains("Your registration completed"));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
