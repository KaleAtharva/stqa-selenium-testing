package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class UserLoginTest {
    public static void main(String[] args) {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Open the login page
            driver.get("http://localhost:3000/login");

            // Setup explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for and interact with login fields
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("ak");

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("atharva21");

            // Submit the login form
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            // Wait for dashboard element to appear after login
            WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard")));

            // Check if dashboardElement is found and print success message
            if (dashboardElement != null) {
                System.out.println("Test passed: Login successful, dashboard accessible.");
            } else {
                System.out.println("Test failed: Dashboard not found after login.");
            }

        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        } finally {
            driver.quit();
        }
    }
}
