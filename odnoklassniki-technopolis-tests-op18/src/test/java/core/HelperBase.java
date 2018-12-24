package core;

import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class HelperBase {
    protected WebDriver driver;
    private boolean acceptNextAlert = true;
    final int TIME_OUT = 10;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
        check();
    }

    protected abstract void check();

    protected void type(String text, By locator) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue("Element must be selected", element.isDisplayed());
        element.clear();
        element.sendKeys(text);
    }

    protected void pressEnter(By locator) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue("Element must be selected", element.isDisplayed());
        element.sendKeys(Keys.ENTER);
    }

    protected boolean click(By locator) {
        if (isElementPresent(locator)) {
            WebElement element = driver.findElement(locator);
            element.click();
            return true;
        }
        return false;
    }

    protected void clickBy(By locator, int xOffSet, int yOffSet) {
        WebElement webElement = driver.findElement(locator);
        Actions builder = new Actions(driver);
        builder.moveToElement(webElement, xOffSet, yOffSet).click().build().perform();
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    protected boolean isElementVisible(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Ожидание
     */
    public boolean explicitWait(
        final ExpectedCondition<?> condition,
        long maxCheckTimeInSeconds,
        long millisecondsBetweenChecks
    ) {
        Preconditions.checkNotNull(condition, "Condition must be not null");
        Preconditions.checkArgument(
            TimeUnit.MINUTES.toSeconds(3) > maxCheckTimeInSeconds,
            "Max check time in seconds should be less than 3 minutes"
        );
        checkConditionTimeouts(maxCheckTimeInSeconds, millisecondsBetweenChecks);
        try {
            // сбрасываем ожидания в 0
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            // создаем эксплицитное ожидание
            WebDriverWait explicitWait = new WebDriverWait(driver, maxCheckTimeInSeconds, millisecondsBetweenChecks);
            // проверяем его
            explicitWait.until(condition);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            // при любом результате восстанавливаем значение имплицитного ожидания по умолчанию
            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } else {
                throw new IllegalArgumentException("Driver shouldnt be null");
            }
        }
    }

    /**
     * Проверяет таймаут провекри условия и интервал между проверками: таймаут
     * должен быть больше нуля, интервал проверки должен быть больше нуля
     * интервал между проверками умноженный на 1000 должен быть меньше таймаута
     * проверки
     *
     * @param maxCheckTimeInSeconds     максимальное время проверки в секундах
     * @param millisecondsBetweenChecks интервал между проверками в милисекундах
     */
    private void checkConditionTimeouts(long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkState(maxCheckTimeInSeconds > 0, "maximum check time in seconds must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks > 0, "milliseconds count between checks must be not 0");
        Preconditions.checkState(
            millisecondsBetweenChecks < (maxCheckTimeInSeconds * 1000),
            "Millis between checks must be less than max seconds to wait"
        );
    }

    public void moveToElement(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).build().perform();
    }
}