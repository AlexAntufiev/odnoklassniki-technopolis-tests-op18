package tests;

import core.LoginBotFactory;
import core.TestBase;
import core.wrappers.MusicItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class My4Test extends TestBase {

    @Test
    public void playMusic() {
        List<MusicItem> musicList = LoginBotFactory
            .getLoginMainPage(driver)
            .navigateToMusic()
            .getMusicList();

        Assert.assertNotNull(musicList);
        MusicItem musicItem = musicList.get(0);
        musicItem.play();
        Assert.assertTrue(musicItem.isPlay());
        musicItem.play();
        Assert.assertFalse(musicItem.isPlay());
    }
}
