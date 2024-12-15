package com.vsb;

import com.google.common.util.concurrent.Uninterruptibles;
import com.vsb.listeners.TestListeners;
import com.vsb.utils.Config;
import com.vsb.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

@Listeners({TestListeners.class})
public class AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setUpConfig()
    {
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException, URISyntaxException {
       this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))?getRemoteDriver():getLocalDriver();
       ctx.setAttribute(Constants.Driver,this.driver);
       this.driver.manage().window().maximize();
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private WebDriver getRemoteDriver() throws URISyntaxException, MalformedURLException {
        Capabilities capabilities = new ChromeOptions();

        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER)))
        {
            capabilities=new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat,hubHost);
        log.info("grid url {}",url);
        return new RemoteWebDriver(new URI(url).toURL(),capabilities);
    }

    @AfterTest
    public void quitDriver()
    {
        this.driver.quit();
    }

    @AfterMethod
    public void sleep()
    {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }
}
