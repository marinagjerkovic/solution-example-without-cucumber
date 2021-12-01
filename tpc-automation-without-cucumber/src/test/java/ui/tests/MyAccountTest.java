package ui.tests;

import data.Users;
import helpers.Links;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pages.AuthenticationPage;
import ui.pages.HeaderMenuPage;
import ui.pages.MyAccountPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MyAccountTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void load_pages_and_login() {
        authenticationPage = new AuthenticationPage(driver, wait);
        myAccountPage = new MyAccountPage(driver, wait);
        headerMenuPage = new HeaderMenuPage(driver, wait);

        driver.get(Links.authenticationPage);
        authenticationPage.login(Users.valid_email, Users.valid_password);
        assertTrue(myAccountPage.wait_until_element_shown(myAccountPage.getMyAccountLabel()));
    }

    @Test(groups = "groupExample")
    public void test_header_contains_users_name_and_lastname() {
        assertEquals(headerMenuPage.getMyAccountButton().getText(), Users.name + " " + Users.lastname_petar);
    }

    @Test
    public void test_page_title_is_correct() {
        assertEquals(driver.getTitle(), "My account - My Store");
    }
}
