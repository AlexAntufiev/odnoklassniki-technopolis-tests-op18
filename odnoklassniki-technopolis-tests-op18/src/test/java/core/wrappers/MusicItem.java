package core.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MusicItem {

    private WebElement mainElement;
    private final static By ARTIST = By.className("wm-track_artist");
    private final static By SONG = By.className("wm-track_name");
    private static final By PLAY_ICON = By.className("wm-simple-play_cnt");
    private static final By STATE = By.tagName("wm-simple-play");
    private static final String STATE_ATTRIBUTE = "state";

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
        mainElement.findElement(PLAY_ICON).click();
    }
}
