package tests;

import core.LoginBotFactory;
import core.TestBase;
import core.wrappers.MusicItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class My3Test extends TestBase {

    @Test
    public void findArtist() {
        String artist = "adele";
        List<MusicItem> musicList = LoginBotFactory
            .getLoginMainPage(driver)
            .navigateToMusic()
            .searchMusic(artist)
            .getMusicList();

        Assert.assertFalse(musicList.isEmpty());
        for (MusicItem i : musicList) {
            String actualArtistLowerCase = i.getArtist().toLowerCase();
            if (artist.contains(actualArtistLowerCase) || artist.equals(actualArtistLowerCase)) {
                return;
            }
        }
        Assert.fail();
    }

    @Test
    public void findSong() {
        String song = "hello";
        List<MusicItem> musicList = LoginBotFactory
            .getLoginMainPage(driver)
            .navigateToMusic()
            .searchMusic(song)
            .getMusicList();

        Assert.assertFalse(musicList.isEmpty());
        for (MusicItem i : musicList) {
            String actualArtistLowerCase = i.getSong().toLowerCase();
            if (song.contains(actualArtistLowerCase) || song.equals(actualArtistLowerCase)) {
                return;
            }
        }
        Assert.fail();
    }
}
