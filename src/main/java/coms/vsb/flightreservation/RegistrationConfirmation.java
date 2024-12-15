package coms.vsb.flightreservation;

import coms.vsb.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmation extends AbstractPage {

    private WebDriver driver;

    @FindBy(id="go-to-flights-search")
    private WebElement goToFlightSearchButton;

    public RegistrationConfirmation(WebDriver driver)
    {
       super(driver);

    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(goToFlightSearchButton));
        return this.goToFlightSearchButton.isDisplayed();
    }

    public void goToFlightSearchPage()
    {
        this.goToFlightSearchButton.click();
    }

}
