package coms.vsb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected AbstractPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    }

    protected abstract boolean isAt();
}
