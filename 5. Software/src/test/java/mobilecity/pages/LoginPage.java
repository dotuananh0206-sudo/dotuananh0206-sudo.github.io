package mobilecity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * LoginPage: Page Object quan ly trang Dang nhap MobileCity (https://mobilecity.vn/login)
 */
public class LoginPage extends BasePage {

    private final By loginForm = By.cssSelector("form.form-signin");
    private final By formTitle = By.cssSelector(".sign-title");
    private final By loginInput = By.cssSelector("input[name='login']");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.cssSelector("button.btn-login");
    private final By registerLink = By.cssSelector("a.v2-register-now");
    private final By forgotPasswordLink = By.cssSelector("a.v2-remember-password");
    private final By googleLoginButton = By.cssSelector("a.btn-google");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginFormDisplayed() {
        return isDisplayed(loginForm);
    }

    public String getFormTitle() {
        return getText(formTitle);
    }

    public void enterLoginIdentifier(String identifier) {
        type(loginInput, identifier);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickSubmit() {
        click(submitButton);
    }

    public void performLogin(String identifier, String password) {
        enterLoginIdentifier(identifier);
        enterPassword(password);
        clickSubmit();
    }

    public boolean isLoginInputRequired() {
        WebElement input = waitForVisibility(loginInput);
        String required = input.getAttribute("required");
        return required != null;
    }

    public boolean isPasswordInputRequired() {
        WebElement input = waitForVisibility(passwordInput);
        String required = input.getAttribute("required");
        return required != null;
    }

    public boolean isRegisterLinkDisplayed() {
        return isDisplayed(registerLink);
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return isDisplayed(forgotPasswordLink);
    }

    public boolean isGoogleLoginDisplayed() {
        return isDisplayed(googleLoginButton);
    }
}
