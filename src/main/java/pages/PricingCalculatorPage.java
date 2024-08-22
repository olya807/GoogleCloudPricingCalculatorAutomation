package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PricingCalculatorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillComputeEngineForm() {
        WebElement numberOfInstancesField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#input_1")));
        numberOfInstancesField.sendKeys("4");

        selectDropdownByVisibleText(By.cssSelector("md-select#select_1"), "Free: Debian, CentOS, CoreOS, Ubuntu, or another User-Provided OS");
        selectDropdownByVisibleText(By.cssSelector("md-select#select_2"), "Regular");
        selectDropdownByVisibleText(By.cssSelector("md-select#select_3"), "General purpose");
        selectDropdownByVisibleText(By.cssSelector("md-select#select_4"), "N1");
        selectDropdownByVisibleText(By.cssSelector("md-select#select_5"), "n1-standard-8 (vCPUs: 8, RAM: 30 GB)");

        WebElement addGPUsCheckbox = driver.findElement(By.cssSelector("md-checkbox#checkbox_1"));
        addGPUsCheckbox.click();

        selectDropdownByVisibleText(By.cssSelector("md-select#select_6"), "NVIDIA Tesla V100");
        selectDropdownByVisibleText(By.cssSelector("md-select#select_7"), "1");
        selectDropdownByVisibleText(By.cssSelector("md-select#select_8"), "2x375 Gb");
        selectDropdownByVisibleText(By.cssSelector("md-select#select_9"), "Frankfurt (europe-west3)");
        selectDropdownByVisibleText(By.cssSelector("md-select#select_10"), "1 Year");

        WebElement addToEstimateButton = driver.findElement(By.cssSelector("button#add-to-estimate"));
        addToEstimateButton.click();
    }

    private void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdownElement = driver.findElement(locator);
        dropdownElement.click();
        WebElement option = driver.findElement(By.xpath("//md-option[contains(text(), '" + visibleText + "')]"));
        option.click();
    }

    public String getTotalEstimatedCost() {
        WebElement totalCostElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".total-cost")));
        return totalCostElement.getText();
    }

    public void emailEstimate(String email) {
        WebElement emailField = driver.findElement(By.cssSelector("input#email"));
        emailField.sendKeys(email);

        WebElement sendEmailButton = driver.findElement(By.cssSelector("button#send-email"));
        sendEmailButton.click();
    }
}