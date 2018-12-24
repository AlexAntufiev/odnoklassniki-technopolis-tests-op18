package core.wrappers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MusicItem {

    private WebElement mainElement;
    private final static By ARTIST = By.xpath(".//*[contains(@class, \"wm-track_artist\")]");
    private final static By SONG = By.xpath(".//*[contains(@class,\"wm-track_name\")]");
    private static final By PLAY_ICON = By.xpath(".//*[contains(@class, \"wm-simple-play_cnt\")]");
    private static final By STATE = By.xpath(".//wm-simple-play[@class=\"__3xfx9j\"]");
    private static final String STATE_ATTRIBUTE = "state";

    public static final String PAUSE_STATUS = "play";
    public static final String PLAY_STATUS = "pause";

    private String song;
    private String artist;

    public MusicItem(WebElement mainElement) {
        this.mainElement = mainElement;
        song = mainElement.findElement(SONG).getText();
        artist = mainElement.findElement(ARTIST).getText();
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }

    public String getState() {
        return mainElement.findElement(STATE).getAttribute(STATE_ATTRIBUTE);
    }

    public void play() {
        WebElement element = mainElement.findElement(PLAY_ICON);
        Assert.assertTrue("Can not play the music", element.isDisplayed());
        element.click();
    }
}
