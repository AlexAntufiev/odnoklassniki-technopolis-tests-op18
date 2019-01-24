package core;

import core.wrappers.MusicItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transformer {

    public static List<MusicItem> wrapMusicList(List<WebElement> elements, WebDriver driver) {
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<MusicItem>() {{
            elements.forEach(i -> add(new MusicItem(i, driver)));
        }};
    }
}
