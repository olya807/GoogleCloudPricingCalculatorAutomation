package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://cloud.google.com/");
    }

    public void searchForPricingCalculator(String searchQuery) {
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[aria-label='Search']")));
        searchBox.sendKeys(searchQuery);
        searchBox.submit();
    }

    public void openCalculatorPage() {
        WebElement resultLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Google Cloud Platform Pricing Calculator")));
        resultLink.click();
    }
}