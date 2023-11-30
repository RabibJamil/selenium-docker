package com.rabib1515.pages.flightreservation;

import com.rabib1515.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id="go-to-flights-search")
    private WebElement gotToFlightsSearchButton;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.gotToFlightsSearchButton));
        return this.gotToFlightsSearchButton.isDisplayed();
    }

    public void goToFlightsSearch(){

        this.gotToFlightsSearchButton.click();
    }

    public String getFirstName(){
        return this.firstNameElement.getText();
    }


}
