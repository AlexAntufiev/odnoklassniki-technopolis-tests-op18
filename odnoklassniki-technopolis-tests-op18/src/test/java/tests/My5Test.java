package tests;

import core.LoginBotFactory;
import core.MusicPage;
import core.TestBase;
import org.junit.Assert;
import org.junit.Test;

public class My5Test extends TestBase {

    @Test
    public void playRadio() {
        MusicPage.RadioPage radio = LoginBotFactory
            .getLoginMainPage(driver)
            .navigateToMusic()
            .selectRadio();

        Assert.assertFalse(radio.isPlay());
        radio.playRandom();
        Assert.assertTrue(radio.isPlay());
    }
}
