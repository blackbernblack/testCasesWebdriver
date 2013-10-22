package com.dataart.selenium.pages;

import com.dataart.selenium.framework.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: tkonstantinov
 * Date: 21/10/13
 * Time: 11:25
 * To change this template use File | Settings | File Templates.
 */
public class CreateEditApplicationPage extends BasicPage {
    @FindBy(xpath = CREATE_BUTTON_XPATH)
    WebElement createdButton;
    @FindBy(xpath = TITLE_TEXT_FIELD_XPATH)
    WebElement titleTextField;
    @FindBy(xpath = DESCRIPTION_TEXT_FIELD_XPATH)
    WebElement descriptionTextField;
    @FindBy(xpath = LOAD_IMAGE_XPATH)
    WebElement loadImageField;
    @FindBy(xpath = LOAD_ICON_XPATH)
    WebElement loadIconField;


    public MyApplicationsPage createNoImageApplication(String appTitle) {
        titleTextField.sendKeys(appTitle);
        descriptionTextField.sendKeys(appTitle + appTitle);
        createdButton.click();
        return initPage(MyApplicationsPage.class);
    }

    public MyApplicationsPage createImageApplication(String appTitle) {
        titleTextField.sendKeys(appTitle);
        descriptionTextField.sendKeys(appTitle + appTitle);
        loadIconField.sendKeys(FILE_ICON_PATH);
        loadImageField.sendKeys(FILE_IMAGE_PATH);
        createdButton.click();
        return initPage(MyApplicationsPage.class);
    }

    public MyApplicationsPage editNoImageApplication(String description) {
        descriptionTextField.sendKeys(description);
        createdButton.click();
        return initPage(MyApplicationsPage.class);
    }

    public static final String TITLE_TEXT_FIELD_XPATH = "//input[@name='title']";
    public static final String DESCRIPTION_TEXT_FIELD_XPATH = "//textarea[@name='description']";
    public static final String CREATE_BUTTON_XPATH = "//input[@type='submit']";
    public static final String LOAD_ICON_XPATH = "//input[@name='icon']";
    public static final String LOAD_IMAGE_XPATH = "//input[@name='image']";

    public static final String FILE_IMAGE_PATH = "C:\\Users\\tkonstantinov\\Downloads\\large.JPG";
    public static final String FILE_ICON_PATH = "C:\\Users\\tkonstantinov\\Downloads\\Capture.JPG";
}
