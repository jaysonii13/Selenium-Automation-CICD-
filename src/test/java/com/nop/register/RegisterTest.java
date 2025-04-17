package com.nop.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

public class RegisterTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testRegistration() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        // Fill registration form
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Jay");
        driver.findElement(By.id("LastName")).sendKeys("Soni");

        // Generate unique email
        String email = "jay_" + UUID.randomUUID().toString().substring(0, 5) + "@example.com";
        driver.findElement(By.id("Email")).sendKeys(email);

        driver.findElement(By.id("Password")).sendKeys("Test@1234");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Test@1234");

        // Submit form
        driver.findElement(By.id("register-button")).click();

        // Directly click logout without waiting
        try {
            Thread.sleep(3000); // slight pause to allow registration to process
            driver.findElement(By.className("ico-logout")).click();
        } catch (Exception e) {
            System.out.println("Logout button not found or not clickable. Skipping logout.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
