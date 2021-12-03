package ui.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SelectingElementsTest extends BaseTest {

    @Test
    public void test_selecting_elements() {
        WebElement byId = driver.findElement(By.id("idValue"));
        WebElement byClass = driver.findElement(By.className("className"));
        WebElement byTag = driver.findElement(By.tagName("tagName"));
        WebElement byName = driver.findElement(By.name("nameValue"));

        WebElement byCssSelectorId = driver.findElement(By.cssSelector("#id"));
        WebElement byCssSelectorClass = driver.findElement(By.cssSelector(".className"));
        WebElement byCssSelectorTagInsideTag = driver.findElement(By.cssSelector("tag1 tag2")); // select tag2 element inside tag1 element
        WebElement byCssSelectorAttributeValue = driver.findElement(By.cssSelector("[attributeName='attributeValue']"));

        WebElement byLinkText = driver.findElement(By.linkText("linkTextValue"));
        WebElement byPartialLinkText = driver.findElement(By.partialLinkText("partialLinkTextValue"));
        WebElement byXPath = driver.findElement(By.xpath("//tagName[@attributeName='attributeValue']"));
    }
}
