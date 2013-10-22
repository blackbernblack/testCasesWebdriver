package com.dataart.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.dataart.selenium.framework.Utils.isElementPresent;
import static org.testng.AssertJUnit.fail;

/**
 * Created with IntelliJ IDEA.
 * User: tkonstantinov
 * Date: 18/10/13
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class AppsContentPage extends BasicPage {

    public Boolean isAbleToSeeApplication() {
        return isElementPresent("//div[@class='apps']");
    }

    public ApplicationPage openAppPage(int number) {
        getAppElement(number, By.xpath(APPS_DETAILS_LINK_XPATH)).click();
        return initPage(ApplicationPage.class);
    }

    public ApplicationPage openAppPage(String appTitle) {
        driver.findElement(By.xpath(String.format(ADDED_APP_DETAILS_BUTTON_XPATH, appTitle))).click();
        return initPage(ApplicationPage.class);
    }

    private WebElement getAppElement(int number, By by) {
        List<WebElement> elements = driver.findElements(by);
        if (number >= elements.size() || number < 0) {
            fail("Application number " + number + " does not exist");
        }
        return elements.get(number);
    }

    public CreateEditApplicationPage clickToAddNewApp() {
        driver.findElement(By.xpath(NEW_APP_LINK_XPATH)).click();
        return initPage(CreateEditApplicationPage.class);
    }

    public Boolean isAppDisplayed(String appTitle) {
        return driver.findElement(By.xpath(String.format(ADDED_APP_TITLE_XPATH, appTitle))).isDisplayed();
    }

    public static final String APPS_DETAILS_LINK_XPATH = "//div[@class='apps']/div//a";
    public static final String NEW_APP_LINK_XPATH = "//div[@class='new-app-link']/a";
    public static final String ADDED_APP_TITLE_XPATH = "//div[@class='apps']/div/div[1][contains(text(),'%s')]";
    public static final String ADDED_APP_DETAILS_BUTTON_XPATH = "//div[@class='apps']/div/a[contains(@href,'%s')]";
}
