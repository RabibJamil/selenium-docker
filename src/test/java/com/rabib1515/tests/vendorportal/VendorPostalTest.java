package com.rabib1515.tests.vendorportal;

import com.rabib1515.pages.vendorportal.DashboardPage;
import com.rabib1515.pages.vendorportal.LoginPage;
import com.rabib1515.tests.AbstractTest;
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

public class VendorPostalTest extends AbstractTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) throws IOException {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());

    }

    @Test(dependsOnMethods = "loginTest")
    public void doshBoardTest(){
        Assert.assertTrue(dashboardPage.isAt());


        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());


       dashboardPage.searchOrderHistory(testData.searchKeyword());
       Assert.assertEquals(dashboardPage.searchResultsCount(), testData.searchResultsCount());



    }

    @Test(dependsOnMethods = "doshBoardTest")
    public void logoutTest(){
        dashboardPage.logout();
    }



}
