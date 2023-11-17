package Utilites;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;


import java.time.Duration;


public class WebDriverWaitImplementation {
    private WebDriver driver;

    public WebDriverWaitImplementation(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement wait_For_visibilityOfElementLocated(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


    public String fluentWaitForElementTextNotEmpty(By locator, int timeoutSeconds, int pollingMillis) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        fluentWait.until(driver -> {
            WebElement element = driver.findElement(locator);
            String text = element.getText();
            return text != null && !text.isEmpty();
        });

        return driver.findElement(locator).getText();
    }

    public Alert waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        return wait.until(ExpectedConditions.alertIsPresent());
    }











    public void waitForElementToAppear(By ele){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ele));
        }

        public void waitForVisiabilityOfElement(By ele){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
        }

        public void waitForVisiabilityOfListOfeleemnts(By ele){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ele));
        }

        public void waitForVisiabilityOfWebElement(WebElement ele){
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(ele));
        }

        public void waitForElementToDisAppear(WebElement ele){
            WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(ele));
        }

    }
