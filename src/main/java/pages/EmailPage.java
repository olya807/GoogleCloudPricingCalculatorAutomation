package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://yopmail.com/");
    }

    public void generateRandomEmail() {
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));
        emailField.clear();
        emailField.sendKeys("testemail" + Math.random() + "@yopmail.com"); // Генерируем случайный email
        WebElement generateButton = driver.findElement(By.cssSelector("button#refresh"));
        generateButton.click();
    }

    public String getRandomEmail() {
        WebElement emailField = driver.findElement(By.id("login"));
        return emailField.getAttribute("value");
    }

    public void checkEmailForEstimate(String totalEstimatedCost) {
        WebElement emailSubjectLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".email_subject")));
        emailSubjectLink.click();

        WebElement emailBodyElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".email_body")));
        String emailBodyText = emailBodyElement.getText();

        if (!emailBodyText.contains(totalEstimatedCost)) {
            throw new AssertionError("The cost in the email does not match the one in the calculator.");
        }
    }
}