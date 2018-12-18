package core;

import model.TestBot;
import org.openqa.selenium.WebDriver;

public class LoginBotFactory {

    private static final String login = "89117539431";
    private static final String password = "1z2x3c4v";

    public static UserMainPage getLoginMainPage(WebDriver driver) {
        LoginMainPage loginMainPage = new LoginMainPage(driver);
        return loginMainPage.doLogin(new TestBot(login, password));
    }
}
