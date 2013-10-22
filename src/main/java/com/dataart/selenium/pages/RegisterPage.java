package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import com.dataart.selenium.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Created with IntelliJ IDEA.
 * User: tkonstantinov
 * Date: 17/10/13
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class RegisterPage extends BasePage{
    @FindBy(xpath = USER_NAME_TEXT_FIELD_XPATH)
    WebElement userNameTextField;
    @FindBy(xpath = USER_FIRST_NAME_TEXT_FIELD_XPATH)
    WebElement userFirstNameTextField;
    @FindBy(xpath = USER_LAST_NAME_TEXT_FIELD_XPATH)
    WebElement userLastNameTextField;
    @FindBy(xpath = UPASSWORD_TEXT_FIELD_XPATH)
    WebElement passwordTextField;
    @FindBy(xpath = UPASSWORD_CONFIRM_TEXT_FIELD_XPATH)
    WebElement passwordConfirmTextField;
    @FindBy(xpath = UROLE_SINGLE_SELECT_LIST_XPATH)
    WebElement roleSingleSelectList;
    @FindBy(xpath = UREGISTER_BUTTON_XPATH)
    WebElement registerButton;

    /**
    * Register as User or Developer
    * @param user the user data.
     * @param role the user role {@link RegisterPage#USER} or {@link RegisterPage#DEVELOPER}.
    * @return {@link HomePage}.
    */
    public HomePage registerAs(User user, int role) {
        userNameTextField.clear();
        userFirstNameTextField.clear();
        userLastNameTextField.clear();
        passwordTextField.clear();
        passwordConfirmTextField.clear();
        userNameTextField.sendKeys(user.getUsername());
        userFirstNameTextField.sendKeys(user.getFname());
        userLastNameTextField.sendKeys(user.getLname());
        passwordTextField.sendKeys(user.getPassword());
        passwordConfirmTextField.sendKeys(user.getPassword());
        selectRole(role);
        registerButton.click();
        return initPage(HomePage.class);
    }


    private void selectRole(int index) {
        (new Select(roleSingleSelectList)).selectByIndex(index);
    }

    public static final String USER_NAME_TEXT_FIELD_XPATH = "//input[@name='name']";
    public static final String USER_FIRST_NAME_TEXT_FIELD_XPATH = "//input[@name='fname']";
    public static final String USER_LAST_NAME_TEXT_FIELD_XPATH = "//input[@name='lname']";
    public static final String UPASSWORD_TEXT_FIELD_XPATH = "//input[@name='password']";
    public static final String UPASSWORD_CONFIRM_TEXT_FIELD_XPATH = "//input[@name='passwordConfirm']";
    public static final String UROLE_SINGLE_SELECT_LIST_XPATH = "//select[@name='role']";
    public static final String UREGISTER_BUTTON_XPATH = "//input[@value='Register']";

    public static final int USER = 1;
    public static final int DEVELOPER = 0;
}
