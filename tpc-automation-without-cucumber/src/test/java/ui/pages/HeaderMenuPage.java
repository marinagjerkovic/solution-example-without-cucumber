package ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class HeaderMenuPage extends BasePage {
    public HeaderMenuPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#header > div.nav > div > div > nav > div:nth-child(1) > a")
    WebElement myAccountButton;

    public void click_myAccountButton() {
        myAccountButton.click();
    }
}
