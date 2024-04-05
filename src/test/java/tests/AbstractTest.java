package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    public void setDriver() {
        if (Boolean.getBoolean("selenium.grid.enabled")) {
            this.driver = getRemoteDriver();
        } else {
            this.driver = getLocalDriver();
        }
    }

    private WebDriver getRemoteDriver() {
        //http://localhost:4444/wb/hub
        Capabilities capabilities;
        if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
            capabilities = new ChromeOptions();
        } else {
            capabilities = new FirefoxOptions();
        }

        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void exit() {
        this.driver.quit();
    }
}
