package com.dataart.selenium.pages;

import com.dataart.selenium.framework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.AssertJUnit.fail;

/**
 * Created with IntelliJ IDEA.
 * User: tkonstantinov
 * Date: 18/10/13
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationPage extends BasicPage {

    @FindBy(xpath = TITLE_TEXT_XPATH)
    WebElement titleText;
    @FindBy(xpath = DESCRIPTION_TEXT_XPATH)
    WebElement descriptionText;
    @FindBy(xpath = CATEGORY_TEXT_XPATH)
    WebElement categoryText;
    @FindBy(xpath = AUTHOR_TEXT_XPATH)
    WebElement authorText;
    @FindBy(xpath = DOWNLOADS_TEXT_XPATH)
    WebElement downloadsText;
    @FindBy(xpath = DOWNLOAD_BUTTON_XPATH)
    WebElement downloadButton;
    @FindBy(xpath = EDIT_BUTTON_XPATH)
    WebElement editButton;
    @FindBy(xpath = DELETE_BUTTON_XPATH)
    WebElement deleteButton;

    public String getTitleText() {
        return titleText.getText();
    }

    public String getDescriptionText() {
        return descriptionText.getText().substring("Description: ".length());
    }

    public String getCategoryText() {
        return categoryText.getText().substring("Category: ".length());
    }

    public String getAuthorText() {
        return authorText.getText().substring("Author: ".length());
    }

    public int getNumberOfDownloads() {
        return Integer.valueOf(downloadsText.getText().substring("# of downloads: ".length()));
    }

    public DownloadApplicationPage clickDownload() {
        downloadButton.click();
        return initPage(DownloadApplicationPage.class);
    }

    public Boolean isDownloadable() {
        return Utils.isElementPresent(DOWNLOAD_BUTTON_XPATH);
    }

    public CreateEditApplicationPage editNoImageApplication() {
        editButton.click();
        return initPage(CreateEditApplicationPage.class);
    }

    public Boolean isAppPopular(String appTitle) {
        return Utils.isElementPresent(By.xpath(String.format(POPULAR_APP_XPATH, appTitle)));
    }

    public ApplicationPage clickPopularApp(String appTitle) {
        if (!isAppPopular(appTitle)) fail("Application " + appTitle + " does not exist in popular section");
        return initPage(ApplicationPage.class);
    }

    public BasicPage deleteApp() {
        deleteButton.click();
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        return initPage(BasicPage.class);
    }

    public static final String DOWNLOAD_BUTTON_XPATH ="//div[@class='download-button']/a";
    public static final String DESCRIPTION_TEXT_XPATH ="//div[@class='description'][contains(text(),'Description')]";
    public static final String CATEGORY_TEXT_XPATH ="//div[@class='description'][contains(text(),'Category')]";
    public static final String AUTHOR_TEXT_XPATH ="//div[@class='description'][contains(text(),'Author')]";
    public static final String TITLE_TEXT_XPATH ="//div[@class='name']";
    public static final String DOWNLOADS_TEXT_XPATH ="//div[@class='downloads']";
    public static final String EDIT_BUTTON_XPATH ="//div[@class='edit-app-button']/a[contains(text(),'Edit')]";
    public static final String DELETE_BUTTON_XPATH = "//div[@class='edit-app-button']/a[text()='Delete']";
    //for more flexible tests POPULAR_APP_XPATH could be extracted to PopularContentPage.class
    public static final String POPULAR_APP_XPATH ="//div[@class='popular-app']//div[text() = '%s']";

}
