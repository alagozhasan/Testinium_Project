package Pages;

import Utilities.BaseDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Parent {
    WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(),
            Duration.ofSeconds(50));

    public void sendKeysFunction(WebElement element, String text) {
        waitUntilVisible(element);
        scrollToElement(element);
        for (int i = 0; i < 50; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }

        element.sendKeys(text);
    }

    public void clickFunction(WebElement element) {
        if (element.isEnabled()) {
            scrollToElement(element);
            waitUntilVisible(element);
            waitUntilClickable(element);
            element.click();
        }
    }

    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) BaseDriver.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickFunctionJS(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) BaseDriver.getDriver();
        waitUntilClickable(element);
        scrollToElement(element);

        js.executeScript("arguments[0].click();", element);
    }


}
