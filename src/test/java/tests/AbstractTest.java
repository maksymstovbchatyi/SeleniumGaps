package tests;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import listener.TestListener;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import util.Config;
import util.Constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public abstract class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void init(){
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext iTestContext) {
//        if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) {
//            this.driver = getRemoteDriver();
//        } else {
//            this.driver = getLocalDriver();
//        }
        this.driver = getLocalDriver();//Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        iTestContext.setAttribute(Constants.DRIVER, this.driver);
    }

    private WebDriver getRemoteDriver() {
        //http://localhost:4444/wb/hub
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("grid url: {}", url);


        try {
            return new RemoteWebDriver(new URL(url), capabilities);
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

    @AfterMethod
    public void sleep(){
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }
}
