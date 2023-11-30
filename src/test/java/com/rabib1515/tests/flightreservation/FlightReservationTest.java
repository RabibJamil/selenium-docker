package com.rabib1515.tests.flightreservation;

import com.rabib1515.pages.flightreservation.*;
import com.rabib1515.tests.AbstractTest;
import com.rabib1515.tests.flightreservation.model.FlightReservationTestData;
import com.rabib1515.tests.vendorportal.model.VendorPortalTestData;
import com.rabib1515.util.Config;
import com.rabib1515.util.Constants;
import com.rabib1515.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;


    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) throws IOException {
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);

    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(testData.firstName(),testData.lastName());
        registrationPage.enterUserCredentials(testData.email(),testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(),testData.zip());
        registrationPage.register();

    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){

        RegistrationConfirmationPage registrationConfirmationPagePage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPagePage.isAt());
        Assert.assertEquals(registrationConfirmationPagePage.getFirstName(),testData.firstName());
        registrationConfirmationPagePage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(testData.passengersCount());
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),testData.expectedPrice());

    }



}
