package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.EmailPage;
import pages.HomePage;
import pages.PricingCalculatorPage;
import utils.WebDriverManager;

public class CalculatorTest {
    private static WebDriver driver;
    private static HomePage homePage;
    private static PricingCalculatorPage calculatorPage;
    private static EmailPage emailPage;
    private static String totalEstimatedCost;

    @BeforeAll
    public static void setup() {
        driver = WebDriverManager.getDriver();
        homePage = new HomePage(driver);
        calculatorPage = new PricingCalculatorPage(driver);
        emailPage = new EmailPage(driver);
    }

    @Test
    public void testPricingCalculatorEstimate() {

        homePage.open();

        homePage.searchForPricingCalculator("Google Cloud Platform Pricing Calculator");
        homePage.openCalculatorPage();

        calculatorPage.fillComputeEngineForm();
        totalEstimatedCost = calculatorPage.getTotalEstimatedCost();

        calculatorPage.emailEstimate("test@example.com");


        emailPage.open();
        emailPage.generateRandomEmail();
        String randomEmail = emailPage.getRandomEmail();
        calculatorPage.emailEstimate(randomEmail);
        emailPage.checkEmailForEstimate(totalEstimatedCost);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}