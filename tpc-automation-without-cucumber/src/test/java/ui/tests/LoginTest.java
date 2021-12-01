package ui.tests;

import data.Users;
import helpers.Links;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.pages.AuthenticationPage;
import ui.pages.MyAccountPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void load_pages_and_navigate_to_authentication_page() {
        authenticationPage = new AuthenticationPage(driver, wait);
        myAccountPage = new MyAccountPage(driver, wait);

        driver.get(Links.authenticationPage);
        assertTrue(authenticationPage.wait_until_element_shown(authenticationPage.getEmailFieldLogin()));
    }

    @Test(dataProvider = "valid_credentials")
    public void test_my_account_page_is_shown_when_login_is_successful(String email, String password) {
        authenticationPage.login(email, password);
        assertTrue(myAccountPage.getMyAccountLabel().isDisplayed());
    }

    @Test(dataProvider = "authentication_failed_credentials")
    public void test_authentication_failed_message_shown(String email, String password) {
        authenticationPage.login(email, password);
        assertTrue(authenticationPage.getFirstErrorMessage().isDisplayed());
        assertEquals(authenticationPage.getFirstErrorMessage().getText(), "Authentication failed.");
    }

    @Test
    public void test_invalid_email_message_shown() {
        authenticationPage.login(Users.invalid_email, Users.valid_password);
        assertTrue(authenticationPage.getFirstErrorMessage().isDisplayed());
        assertEquals(authenticationPage.getFirstErrorMessage().getText(), "Invalid email address.");
    }

    @Test
    public void test_email_required_message_shown() {
        authenticationPage.login("", Users.valid_password);
        assertTrue(authenticationPage.getFirstErrorMessage().isDisplayed());
        assertEquals(authenticationPage.getFirstErrorMessage().getText(), "An email address required.");
    }

    @Test
    public void test_password_required_message_shown() {
        authenticationPage.login(Users.valid_email, "");
        assertTrue(authenticationPage.getFirstErrorMessage().isDisplayed());
        assertEquals(authenticationPage.getFirstErrorMessage().getText(), "Password is required.");
    }

    @DataProvider(name = "valid_credentials")
    public Object[][] provide_valid_credentials() {
        return new Object[][] {
                {Users.valid_email, Users.valid_password},
                {Users.valid_email_petar, Users.valid_password},
                {"   " + Users.valid_email + "  ", Users.valid_password}
        };
    }

    @DataProvider(name = "authentication_failed_credentials")
    public Object[][] provide_authentication_failed_credentials() {
        return new Object[][] {
                {Users.valid_email, Users.invalid_password},
                {Users.not_registered_email, Users.valid_password}
        };
    }
}
