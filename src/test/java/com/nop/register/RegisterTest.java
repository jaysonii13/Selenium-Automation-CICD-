package com.nop.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class RegisterTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void testRegistration() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        // Fill form
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Jay");
        driver.findElement(By.id("LastName")).sendKeys("Soni");

        // Unique email using UUID
        String email = "jay_" + UUID.randomUUID().toString().substring(0, 5) + "@example.com";
        driver.findElement(By.id("Email")).sendKeys(email);

        driver.findElement(By.id("Password")).sendKeys("Test@1234");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Test@1234");

        // Click Register
        driver.findElement(By.id("register-button")).click();

        // Wait for success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.result")));
        Assert.assertTrue(successMessage.getText().contains("Your registration completed"), "✅ Registration message not displayed");

        // Optional debug logs
        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());

        // Wait for "My account" link
        WebElement myAccountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.ico-account")));
        Assert.assertTrue(myAccountLink.isDisplayed(), "❌ 'My account' link not visible");

        System.out.println("✅ Registration successful and user is logged in.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
