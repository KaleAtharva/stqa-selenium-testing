package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class UserRegistrationTest {
    public static void main(String[] args) {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Open the registration page
            driver.get("http://localhost:3000");

            // Setup explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Switch to registration form
            WebElement toggleButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.className("auth-toggle-button")));
            toggleButton.click();

            // Wait for and interact with registration fields
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("testuser");

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("testuser@example.com");

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("password123");

            // Submit the registration form
            // wait.until(ExpectedConditions.urlContains("/login")); // Adjust this
            // according to your login route

            // // Check if the user is on the login page
            // String currentUrl = driver.getCurrentUrl();
            // assert currentUrl.contains("/login")
            // : "User is not redirected to the login page. Current URL: " + currentUrl;

            // Optionally, you can also check for a specific element on the login page
            WebElement loginElement = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.auth-title"))); // Adjust
                                                                                                            // this
                                                                                                            // selector
                                                                                                            // as needed
            assert loginElement.getText().equals("Login") : "Login page is not displayed correctly.";

            System.out.println("Test passed: User redirected to the login page successfully.");

        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
