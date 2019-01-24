package core.wrappers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MusicItem {

    private WebElement mainElement;
    private WebDriver driver;
    private final static By ARTIST = By.xpath(".//*[contains(@class, \"wm-track_artist\")]");
    private final static By SONG = By.xpath(".//*[contains(@class,\"wm-track_name\")]");
    private static final By PLAY_ICON = By.xpath(".//*[contains(@class,'wm-track_cover')]");
    private static final By STATE = By.xpath(".//*[@class='__md3vmb']");
    private static final String STATE_ATTRIBUTE = "playing";

    private String song;
    private String artist;

    public MusicItem(WebElement mainElement, WebDriver driver) {
        this.mainElement = mainElement;
        this.driver = driver;
        song = mainElement.findElement(SONG).getText();
        artist = mainElement.findElement(ARTIST).getText();
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }

    public void play() {
        WebElement element = mainElement.findElement(PLAY_ICON);
        Assert.assertTrue("Can not play the music", element.isDisplayed());
        element.click();
        try {
            // необходимое ожидание элемента
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlay() {
        return "true".equals(mainElement.findElement(STATE).getAttribute(STATE_ATTRIBUTE));
    }
}
