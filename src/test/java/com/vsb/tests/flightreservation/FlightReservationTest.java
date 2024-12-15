package com.vsb.tests.flightreservation;

import com.vsb.AbstractTest;
import com.vsb.tests.flightreservation.model.FlightReservationTestData;
import com.vsb.utils.Config;
import com.vsb.utils.Constants;
import com.vsb.utils.JsonUtil;
import coms.vsb.flightreservation.*;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath)
    {

        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);

    }

    @Test
    public void userRegistrationTest()
    {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(this.testData.firstname(),this.testData.lastname());
        registrationPage.enterUserCredentials(this.testData.email(),this.testData.password());
        registrationPage.enterAddress(this.testData.street(),this.testData.city(),this.testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registerConfirmationTest()
    {
        RegistrationConfirmation registerConfirmationPage = new RegistrationConfirmation(driver);
        Assert.assertTrue(registerConfirmationPage.isAt());
        registerConfirmationPage.goToFlightSearchPage();

    }

    @Test(dependsOnMethods = "registerConfirmationTest")
    public void flightSearchTest()
    {
        FlightSearchPage flightSearchPage= new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(this.testData.passengerCount());
        flightSearchPage.searchFlight();

    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest()
    {
        FlightSelectionPage flightSelectionPage= new FlightSelectionPage(driver);
        flightSelectionPage.isAt();
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightConfirmationTest()
    {
        FlightConfirmationPage flightConfirmationPage=new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),this.testData.expectedPrice());

    }
}
