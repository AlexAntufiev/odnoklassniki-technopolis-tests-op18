package tests;

import core.LoginBotFactory;
import core.TestBase;
import core.wrappers.MusicItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class My4Test extends TestBase {

    @Test
    public void playMusic() throws InterruptedException {
        List<MusicItem> musicList = LoginBotFactory
            .getLoginMainPage(driver)
            .navigateToMusic()
            .getMusicList();

        Assert.assertNotNull(musicList);
        MusicItem musicItem = musicList.get(0);
        Assert.assertEquals(MusicItem.PAUSE_STATUS, musicItem.getState());
        musicItem.play();
        Thread.sleep(1500);
        Assert.assertEquals(MusicItem.PLAY_STATUS, musicItem.getState());
        musicItem.play();
        Thread.sleep(1500);
        Assert.assertEquals(MusicItem.PAUSE_STATUS, musicItem.getState());
    }
}
