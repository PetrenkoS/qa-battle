package com.ls.battle.tests;

import com.codeborne.selenide.Configuration;
import com.ls.battle.appmanager.ApplicationManager;
import com.ls.battle.utils.PropertiesContext;
import com.ls.battle.utils.vars.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestBase {
    protected final ApplicationManager app = new ApplicationManager();
    private static PropertiesContext propertiesContext = PropertiesContext.getInstance();
    private static final String URL = propertiesContext.getProperty("url");
    protected static final String USERNAME = propertiesContext.getProperty("user");
    protected static final String PASSWORD = propertiesContext.getProperty("password");
    protected static final String USER_EMAIL_MEMBER = propertiesContext.getProperty("user.email.member");
    protected static final String USER_PASS_MEMBER = propertiesContext.getProperty("user.password.member");
    private static Browser browser = Browser.toEnum(propertiesContext.getProperty("browser"));


    @BeforeMethod
    public void setUp() {
        if (browser.toString().equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            System.setProperty("firefoxprofile.dom.webnotifications.serviceworker.enabled", "false");
            System.setProperty("firefoxprofile.dom.webnotifications.enabled", "false");
            String home = System.getProperty("user.home");
            String downloadFilepath = home + "/Downloads/test/data.txt";
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.dir", downloadFilepath);
            Configuration.browser = "firefox";
        } else if (browser.toString().equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            Configuration.browser = "chrome";
        } else {
            browser = null;
            Configuration.browser = "chrome";
        }
        clearBrowserCache();
        startMaximized = false;
        browserSize = "1600x1000";
        timeout = 10000;

        open(URL);

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        closeWebDriver();
    }

}



