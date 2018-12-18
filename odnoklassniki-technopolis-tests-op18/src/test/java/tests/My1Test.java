package tests;

import core.LoginBotFactory;
import core.MusicPage;
import core.TestBase;
import org.junit.Test;

public class My1Test extends TestBase {

    @Test
    public void checkTabs() {
        MusicPage musicPage = LoginBotFactory.getLoginMainPage(driver).navigateToMusic();

        musicPage.selectForYou();
        musicPage.selectMyMusic();
        musicPage.selectCollection();
        musicPage.selectRadio();
    }
}
