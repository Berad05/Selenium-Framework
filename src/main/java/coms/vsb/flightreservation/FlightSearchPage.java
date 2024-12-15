package coms.vsb.flightreservation;

import coms.vsb.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {


    @FindBy(id="passengers")
    private WebElement passengerSelect;


    @FindBy(id="search-flights")
    private WebElement searchFlightButton;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(searchFlightButton));
        
        return this.searchFlightButton.isDisplayed();
    }

    public void selectPassengers(String noofPassengers)
    {
        Select select=new Select(this.passengerSelect);
        select.selectByValue(noofPassengers);
    }

    public void searchFlight()
    {
        this.searchFlightButton.click();
    }
}
