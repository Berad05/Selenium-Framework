package coms.vsb.vendorportal;

import coms.vsb.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends AbstractPage {


    @FindBy(id="username")
    private WebElement userNameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(id="login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(userNameInput));
        return userNameInput.isDisplayed();
    }

    public void goTo(String url)
    {
        this.driver.get(url);
    }

    public void login(String userName,String password)
    {
        this.userNameInput.sendKeys(userName);
        this.passwordInput.sendKeys(password);
        this.loginButton.click();
    }
}
