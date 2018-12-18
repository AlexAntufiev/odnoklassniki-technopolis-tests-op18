package tests;

import core.LoginBotFactory;
import core.MusicPage;
import core.TestBase;
import org.junit.Assert;
import org.junit.Test;

public class My5Test extends TestBase {

    @Test
    public void playRadio() throws InterruptedException {
        MusicPage.RadioPage radio = LoginBotFactory
            .getLoginMainPage(driver)
            .navigateToMusic()
            .selectRadio();

        Assert.assertFalse(radio.isPlay());
        radio.playRandom();
        Thread.sleep(1500);
        Assert.assertTrue(radio.isPlay());
    }
}
