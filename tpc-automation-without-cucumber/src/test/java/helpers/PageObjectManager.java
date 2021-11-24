package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.AuthenticationPage;
import ui.pages.HeaderMenuPage;
import ui.pages.MyAccountPage;

public class PageObjectManager {
    private WebDriver driver;
    private WebDriverWait wait;

    private AuthenticationPage authenticationPage;
    private MyAccountPage myAccountPage;
    private HeaderMenuPage headerMenuPage;

    public PageObjectManager(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public AuthenticationPage getAuthenticationPage() {
        return (authenticationPage == null) ? authenticationPage = new AuthenticationPage(driver, wait) : authenticationPage;
    }

    public MyAccountPage getMyAccountPage() {
        return (myAccountPage == null) ? myAccountPage = new MyAccountPage(driver, wait) : myAccountPage;
    }

    public HeaderMenuPage getHeaderMenuPage() {
        return (headerMenuPage == null) ? headerMenuPage = new HeaderMenuPage(driver, wait) : headerMenuPage;
    }
}
