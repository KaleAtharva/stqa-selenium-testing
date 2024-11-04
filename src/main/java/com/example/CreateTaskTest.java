package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class CreateTaskTest {
    public static void main(String[] args) {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Open the dashboard page (ensure user is logged in or handle login in test)
            driver.get("http://localhost:3000/dashboard");

            // Wait for form fields to be visible before interacting
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Fill out the task creation form
            WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("taskTitle")));
            titleField.sendKeys("New Task");

            WebElement descriptionField = driver.findElement(By.id("taskDescription"));
            descriptionField.sendKeys("Description of the task.");

            WebElement dueDateField = driver.findElement(By.id("taskDueDate"));
            dueDateField.sendKeys("2024-12-31");

            WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("assign")));

            Select select = new Select(selectElement);

            try {
                select.selectByValue("ak");
                System.out.println("Selected value: " + select.getFirstSelectedOption().getText());
            } catch (Exception e) {
                System.out.println("Error selecting option: " + e.getMessage());
            }

            WebElement createButton = driver.findElement(By.id("createTaskButton"));
            createButton.click();

            // Wait for success message to appear
            WebElement successMessageElement = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
            String successMessage = successMessageElement.getText();

            // Check if success message is as expected
            if (successMessage.contains("Task created successfully")) {
                System.out.println("Task Creation Test Passed");
            } else {
                System.out.println("Task Creation Test Failed: Success message not found");
            }

        } catch (Exception e) {
            System.out.println("Task Creation Test Failed with error: ");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
