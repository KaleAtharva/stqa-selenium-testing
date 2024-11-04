package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class TaskDeletionTest {
    public static void main(String[] args) {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Open the application dashboard page (where tasks are listed)
            driver.get("http://localhost:3000/dashboard");

            // Setup explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Click on the 'Show Tasks' button
            WebElement showTasksButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("task-hide")));
            showTasksButton.click();

            // Wait for tasks to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("taskTitle"))); // Wait for task titles to be
                                                                                           // visible

            // Assuming that we are deleting the first task for this example
            WebElement firstDeleteButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[1]//button[text()='Delete']"))); // Adjust
                                                                                                                      // based
                                                                                                                      // on
                                                                                                                      // your
                                                                                                                      // task
                                                                                                                      // list
                                                                                                                      // structure
            firstDeleteButton.click();

            // Optionally wait for any confirmation dialog and confirm deletion
            // If a confirmation is required, you might have to add that logic here

            // Wait for the task to be removed from the list
            wait.until(ExpectedConditions.stalenessOf(firstDeleteButton)); // Wait until the button is no longer
                                                                           // attached to the DOM

            // Check if the task is no longer visible
            boolean isTaskDeleted = driver.findElements(By.xpath("//ul/li[1]")).isEmpty(); // Adjust to check the first
                                                                                           // task
            assert isTaskDeleted : "Task was not deleted successfully.";

            System.out.println("Test passed: Task deleted successfully.");

        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
