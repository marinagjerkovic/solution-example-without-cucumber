package ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class AuthenticationPage extends BasePage {
    public AuthenticationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email_create")
    WebElement emailFieldCreateAccount;

    @FindBy(id = "SubmitCreate")
    WebElement createAccountButton;

    @FindBy(id = "email")
    WebElement emailFieldLogin;

    @FindBy(id = "passwd")
    WebElement passwordFieldLogin;

    @FindBy(linkText = "Forgot your password?")
    WebElement forgotPassword;

    @FindBy(id = "SubmitLogin")
    WebElement loginButton;

    @FindBy(css = "#center_column > div.alert.alert-danger > ol > li")
    WebElement firstErrorMessage;

    public void enter_emailCreateAccount(String text) {
        emailFieldCreateAccount.sendKeys(text);
    }

    public void click_createAccountButton() {
        createAccountButton.click();
    }

    public void enter_emailLogin(String text) {
        emailFieldLogin.sendKeys(text);
    }

    public void enter_passwordLogin(String text) {
        passwordFieldLogin.sendKeys(text);
    }

    public void click_loginButton() {
        loginButton.click();
    }

    public void login(String email, String password) {
        enter_emailLogin(email);
        enter_passwordLogin(password);
        click_loginButton();
    }
}
