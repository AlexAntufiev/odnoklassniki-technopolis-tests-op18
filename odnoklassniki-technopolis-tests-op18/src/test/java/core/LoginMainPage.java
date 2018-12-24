package core;

import model.TestBot;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginMainPage extends HelperBase {

    private static final By EMAIL = By.xpath("//*[@id='field_email']");
    private static final By PASSWORD = By.xpath("//*[@id='field_password']");
    private static final By ENTER_BUTTON = By.xpath(".//*[@value='Log in']");

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue(
            new WebDriverWait(driver, TIME_OUT)
                .until((ExpectedCondition<Boolean>) webDriver -> isElementPresent(EMAIL) && isElementPresent(PASSWORD))
        );
    }

    public UserMainPage doLogin(TestBot testBot) {
        type(testBot.getLogin(), EMAIL);
        type(testBot.getPassword(), PASSWORD);
        Assert.assertTrue("Can not click to 'Log in' button", click(ENTER_BUTTON));
        return new UserMainPage(driver);
    }
}