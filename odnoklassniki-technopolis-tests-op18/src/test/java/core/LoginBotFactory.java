package core;

import model.TestBot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginBotFactory {

    public static UserMainPage getLoginMainPage(WebDriver driver) {

        Properties properties = new Properties();
        try (InputStream inStream = LoginBotFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(inStream);
        } catch (IOException e) {
            throw new RuntimeException("Can not read properties file", e);
        }
        return new LoginMainPage(driver).doLogin(new TestBot(properties.getProperty("login"), properties.getProperty("password")));
    }
}
