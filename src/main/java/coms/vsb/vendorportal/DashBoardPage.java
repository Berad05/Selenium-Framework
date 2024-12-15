package coms.vsb.vendorportal;

import coms.vsb.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashBoardPage extends AbstractPage {
    private static Logger log = LoggerFactory.getLogger(DashBoardPage.class);

   @FindBy(id="monthly-earning")
   private WebElement monthlyEarningElement;

    @FindBy(id="annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id="profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id="available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(css="#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id="dataTable_info")
    private WebElement searchResultCount;

    @FindBy(css="#userDropdown img")
    private WebElement userProfilePictureElement;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css="#logoutModal a")
    private WebElement modalLogoutButton;


    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(monthlyEarningElement));
        return this.monthlyEarningElement.isDisplayed();
    }

    public String getMonthlyEarning()
    {
        return this.monthlyEarningElement.getText();
    }

    public String getAnnualEarning()
    {
        return this.annualEarningElement.getText();
    }

    public String getProfitMargin()
    {
        return this.profitMarginElement.getText();
    }

    public String getAvailableInventory()
    {
        return this.availableInventoryElement.getText();
    }

    public void searchOrderHistoryBy(String keyword)
    {
        this.searchInput.sendKeys(keyword);
    }

    public int getSearchResultsCount()
    {
       String searchResultCount = this.searchResultCount.getText();
       String [] searchArray = searchResultCount.split(" ");
       int count = Integer.parseInt(searchArray[5]);
       log.info("Result count: {}",count);
       return count;
    }

    public void logout()
    {
        this.userProfilePictureElement.click();
        this.wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.elementToBeClickable(modalLogoutButton));
        this.modalLogoutButton.click();
    }
}
