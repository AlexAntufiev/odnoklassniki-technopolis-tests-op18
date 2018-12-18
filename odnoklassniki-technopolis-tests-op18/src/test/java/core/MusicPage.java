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
    private static final By MUSIC_BUTTON = By.xpath(
        "//div[@class='toolbar_nav_i_tx-w usel-off'][contains(text(),'Музыка')]");

    private final int THRESHOLD_TO_LOAD = 10;
    private static final By MUSIC_PLAYER = By.xpath("//div[@id='music_layer_wrapper']");
    private static final By SEARCH_FIELD = By.xpath("//input[@placeholder='Искать песни на сайте']");
    private static final By SEARCH_BUTTON = By.xpath("//a[@class='vl_btn']");
    private static final By MUSIC_LIST = By.xpath("//div[@class='mus-tr_lst']");
    private static final By MUSICS = By.xpath("//*[@class='__cxq3lh']//wm-track[*]");

    private static final By FOR_YOU_MENU = By.xpath("//*[contains(@class, '__vitrine')]");
    private static final By MY_MUSIC_MENU = By.xpath("//*[contains(@class, '__library')]");
    private static final By COLLECTION_MENU = By.xpath("//*[contains(@class, '__collections')]");
    private static final By RADIO_MENU = By.xpath("//*[contains(@class, '__radio')]");

    private final List<MusicItem> musics;
    private static final By SEARCH_MUSIC = By.xpath("//input[@placeholder='Поиск']");

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
        pressEnter();
        return this;
    }

    public List<MusicItem> getMusicList() {
        return getMusics(driver);
    }

    public RadioPage selectRadio() {
        driver.findElement(RADIO_MENU).click();
        return new RadioPage(driver);
    }

    public ForYouPage selectForYou() {
        driver.findElement(FOR_YOU_MENU).click();
        return new ForYouPage(driver);
    }

    public MyMusic selectMyMusic() {
        driver.findElement(MY_MUSIC_MENU).click();
        return new MyMusic(driver);
    }

    public CollectionPage selectCollection() {
        driver.findElement(COLLECTION_MENU).click();
        return new CollectionPage(driver);
    }

    public static class RadioPage extends HelperBase {

        private static final By RADIO_SECTION = By.tagName("wm-radio-section");
        private static final By RADIO_LIST = By.xpath("//wm-radio-station[*]");
        private static final By PLAY_BUTTON = By.tagName("wm-button");
        private static final By PLAY_BUTTON_2 = By.className("wm-button_icon");
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
            playingElement.click();
            return this;
        }

        public boolean isPlay() {
            return playingElement != null && "pause".equals(playingElement.getAttribute("icon"));
        }
    }

    public static class ForYouPage extends HelperBase {
        private static final By FOR_YOU = By.tagName("wm-vitrine-section");

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
        private static final By COLLECTION = By.tagName("wm-collections-section");

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

        private static final By MY_MUSIC = By.tagName("wm-library-section");

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
