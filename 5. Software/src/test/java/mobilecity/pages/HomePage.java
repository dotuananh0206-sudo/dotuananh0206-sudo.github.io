package mobilecity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * HomePage: Page Object dai dien cho Trang chu MobileCity (https://mobilecity.vn/)
 * Chua cac Locator va Action duoc kiem tra thuc te tren giao dien MobileCity.
 */
public class HomePage extends BasePage {

    // ===== LOCATORS (Xac minh truc tiep tu DOM website MobileCity) =====
    private final By logo = By.cssSelector(".v3_logo a img, .v3_logo a");
    private final By locationDropdown = By.cssSelector("select.v3_location_name");
    private final By searchInput = By.id("keyword");
    private final By searchButton = By.cssSelector("button.btn-search");
    private final By suggestSearchBox = By.cssSelector(".suggest-search");
    private final By suggestSearchItems = By.cssSelector(".suggest-search-list li");

    // Menu danh muc san pham
    private final By menuPhone = By.cssSelector("a[href='https://mobilecity.vn/dien-thoai'], a[href*='/dien-thoai']");
    private final By menuTablet = By.cssSelector("a[href='https://mobilecity.vn/may-tinh-bang'], a[href*='/may-tinh-bang']");
    private final By menuLaptop = By.cssSelector("a[href='https://mobilecity.vn/laptop'], a[href*='/laptop']");
    private final By menuAccessories = By.cssSelector("a[href='https://mobilecity.vn/phu-kien'], a[href*='/phu-kien']");

    // Header Actions
    private final By loginLink = By.cssSelector(".v3_logs a[href*='login']");
    private final By registerLink = By.cssSelector(".v3_logs a[href*='register']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ===== ACTIONS & VERIFICATIONS =====

    public boolean isLogoDisplayed() {
        return isDisplayed(logo);
    }

    public void clickLogo() {
        click(logo);
    }

    public boolean isSearchBoxDisplayed() {
        return isDisplayed(searchInput);
    }

    public boolean isLocationDropdownDisplayed() {
        return isDisplayed(locationDropdown);
    }

    public boolean isPhoneMenuDisplayed() {
        return isDisplayed(menuPhone);
    }

    public void enterSearchKeyword(String keyword) {
        type(searchInput, keyword);
    }

    public void clickSearchButton() {
        click(searchButton);
    }

    public void submitSearchWithEnter() {
        waitForVisibility(searchInput).sendKeys(Keys.ENTER);
    }

    public void search(String keyword) {
        enterSearchKeyword(keyword);
        clickSearchButton();
    }

    public String getSearchInputValue() {
        return waitForVisibility(searchInput).getAttribute("value");
    }

    public boolean isSuggestSearchDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(suggestSearchBox)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getSuggestItemCount() {
        try {
            List<WebElement> items = findElements(suggestSearchItems);
            return items.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public ProductListPage openPhoneCategory() {
        click(menuPhone);
        return new ProductListPage(driver);
    }

    public ProductListPage openTabletCategory() {
        click(menuTablet);
        return new ProductListPage(driver);
    }

    public LoginPage openLoginPage() {
        click(loginLink);
        return new LoginPage(driver);
    }

    public void openRegisterPage() {
        click(registerLink);
    }

    public void selectLocationByText(String locationText) {
        WebElement selectElement = waitForVisibility(locationDropdown);
        Select select = new Select(selectElement);
        select.selectByVisibleText(locationText);
    }

    public String getSelectedLocation() {
        WebElement selectElement = waitForVisibility(locationDropdown);
        Select select = new Select(selectElement);
        return select.getFirstSelectedOption().getText().trim();
    }
}
