package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage extends HelperBase {
    private static final By MUSIC_BUTTON = By.xpath("//div[contains(text(),'Музыка')]");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue(
            new WebDriverWait(driver, TIME_OUT)
                .until((ExpectedCondition<Boolean>) webDriver -> isElementPresent(MUSIC_BUTTON)));
    }

    public MusicPage navigateToMusic() {
        click(MUSIC_BUTTON);
        return new MusicPage(driver);
    }
}
