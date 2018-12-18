package tests;

import core.LoginBotFactory;
import core.MusicPage;
import core.TestBase;
import org.junit.Assert;
import org.junit.Test;

public class My2Test extends TestBase {

    @Test
    public void playRandomMusic() {
        MusicPage musicPage = LoginBotFactory
            .getLoginMainPage(driver)
            .navigateToMusic()
            .playRandomMusic();

        Assert.assertNotNull(musicPage);
    }
}
