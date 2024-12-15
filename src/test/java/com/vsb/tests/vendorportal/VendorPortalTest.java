package com.vsb.tests.vendorportal;

import com.vsb.AbstractTest;
import com.vsb.tests.vendorportal.model.VendorPortalTestData;
import com.vsb.utils.Config;
import com.vsb.utils.Constants;
import com.vsb.utils.JsonUtil;

import coms.vsb.vendorportal.DashBoardPage;
import coms.vsb.vendorportal.LoginPage;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath)
    {
        loginPage=new LoginPage(driver);
        dashBoardPage=new DashBoardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);

    }
    @Test
    public void loginTest()
    {
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(this.testData.username(),this.testData.password());

    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest()
    {
        Assert.assertTrue(dashBoardPage.isAt());

        Assert.assertEquals(dashBoardPage.getMonthlyEarning(),this.testData.monthlyEarning());
        Assert.assertEquals(dashBoardPage.getAnnualEarning(),this.testData.annualEarning());
        Assert.assertEquals(dashBoardPage.getProfitMargin(),this.testData.profitMargin());
        Assert.assertEquals(dashBoardPage.getAvailableInventory(),this.testData.availableInventory());

        dashBoardPage.searchOrderHistoryBy(this.testData.searchKeyword());
        Assert.assertEquals(dashBoardPage.getSearchResultsCount(),this.testData.searchResultCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest()
    {
        dashBoardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
