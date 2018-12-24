package core;

import core.wrappers.MusicItem;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class MusicPage extends HelperBase {

    private static final By MUSIC_PLAYER = By.xpath("//div[@class='toolbar-music-layer']");
    private static final By MUSICS = By.xpath("//main[@class='__cxq3lh']");

    private static final By FOR_YOU_MENU = By.xpath("//div[contains(@class, '__vitrine')]");
    private static final By MY_MUSIC_MENU = By.xpath("//div[contains(@class, '__library')]");
    private static final By COLLECTION_MENU = By.xpath("//div[contains(@class, '__collections')]");
    private static final By RADIO_MENU = By.xpath("//div[contains(@class, '__radio')]");

    private final List<MusicItem> musics;
    private static final By SEARCH_MUSIC = By.xpath("//input[@placeholder='Поиск']");
    private static final By SEARCH = By.name("common-search");

    MusicPage(WebDriver driver) {
        super(driver);
        musics = getMusics(driver);
    }

    private List<MusicItem> getMusics(WebDriver driver) {
        return Transformer.wrapMusicList(driver.findElements(MUSICS));
    }

    protected void check() {
        Assert.assertTrue(
            new WebDriverWait(driver, TIME_OUT)
                .until((ExpectedCondition<Boolean>) webDriver -> isElementVisible(MUSIC_PLAYER) && isElementVisible(
                    SEARCH_MUSIC) && isElementPresent(MUSICS))
        );
    }

    public MusicPage playRandomMusic() {
        musics.forEach(i -> {
            i.play();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return this;
    }

    public MusicPage searchMusic(String music) {
        type(music, SEARCH_MUSIC);
        pressEnter(SEARCH);
        return this;
    }

    public List<MusicItem> getMusicList() {
        return getMusics(driver);
    }

    public RadioPage selectRadio() {
        Assert.assertTrue("Can not click by Radio menu button", click(RADIO_MENU));
        return new RadioPage(driver);
    }

    public ForYouPage selectForYou() {
        Assert.assertTrue("Can not click by Music button", click(FOR_YOU_MENU));
        return new ForYouPage(driver);
    }

    public MyMusic selectMyMusic() {
        Assert.assertTrue("Can not click by Music button", click(MY_MUSIC_MENU));
        return new MyMusic(driver);
    }

    public CollectionPage selectCollection() {
        Assert.assertTrue("Can not click by Music button", click(COLLECTION_MENU));
        return new CollectionPage(driver);
    }

    public static class RadioPage extends HelperBase {

        private static final By RADIO_SECTION = By.xpath("//*[contains(@class,'__60o4ct')]");
        private static final By RADIO_LIST = By.xpath("//wm-radio-station[*]");
        private static final By PLAY_BUTTON = By.xpath("//wm-button[@class='__ekai4w']");
        private final List<WebElement> radioList;
        private WebElement playingElement;

        public RadioPage(WebDriver driver) {
            super(driver);
            radioList = driver.findElements(RADIO_LIST);
        }

        @Override
        protected void check() {
            Assert.assertTrue(
                "Не загрузилось достаточное для работы количество товаров",
                new WebDriverWait(driver, TIME_OUT)
                    .until((ExpectedCondition<Boolean>) webDriver -> isElementVisible(RADIO_SECTION) && isElementVisible(
                        RADIO_LIST) && isElementVisible(PLAY_BUTTON))
            );
        }

        public RadioPage playRandom() {
            playingElement = radioList.get(new Random().nextInt(radioList.size() - 1)).findElement(PLAY_BUTTON);
            Assert.assertTrue("Can not select the music", playingElement.isDisplayed());
            playingElement.click();
            return this;
        }

        public boolean isPlay() {
            return playingElement != null && "pause".equals(playingElement.getAttribute("icon"));
        }
    }

    public static class ForYouPage extends HelperBase {
        private static final By FOR_YOU = By.xpath("//*[contains(text(),\"Сборники для вас\")]");

        public ForYouPage(WebDriver driver) {super(driver);}

        @Override
        protected void check() {
            Assert.assertTrue(
                "Не загрузилось достаточное для работы количество товаров",
                new WebDriverWait(driver, TIME_OUT)
                    .until((ExpectedCondition<Boolean>) webDriver -> isElementVisible(FOR_YOU))
            );
        }

    }

    public static class CollectionPage extends HelperBase {
        private static final By COLLECTION = By.xpath("//*[contains(text(),'Сборники')]");

        public CollectionPage(WebDriver driver) {super(driver);}

        @Override
        protected void check() {
            Assert.assertTrue(
                "Не загрузилось достаточное для работы количество товаров",
                new WebDriverWait(driver, TIME_OUT)
                    .until((ExpectedCondition<Boolean>) webDriver -> isElementVisible(COLLECTION))
            );
        }
    }

    public static class MyMusic extends HelperBase {

        private static final By MY_MUSIC = By.xpath("//*[contains(text(),'Моя музыка')]");

        public MyMusic(WebDriver driver) {
            super(driver);
        }

        @Override
        protected void check() {
            Assert.assertTrue(
                new WebDriverWait(driver, TIME_OUT)
                    .until((ExpectedCondition<Boolean>) webDriver -> isElementVisible(MY_MUSIC))
            );
        }
    }

}
