package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: tkonstantinov
 * Date: 22/10/13
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class JSTestPage extends BasePage {
    @FindBy(xpath = TOP_TEXT_FIELD_XPATH)
    WebElement topTextField;
    @FindBy(xpath = LEFT_TEXT_FIELD_XPATH)
    WebElement leftTextField;
    @FindBy(xpath = PROCESS_BUTTON_XPATH)
    WebElement processButton;

    public String find(){
        String s = null;
        if (driver instanceof JavascriptExecutor) {
            JavascriptExecutor js = (JavascriptExecutor)driver;
            topTextField.sendKeys(String.valueOf(js.executeScript(TOP_COORDINATE_JS)));
            leftTextField.sendKeys(String.valueOf(js.executeScript(LEFT_COORDINATE_JS)));
            processButton.click();
            Alert alert = driver.switchTo().alert();
            s = alert.getText();
            alert.accept();
            //driver.switchTo().defaultContent();
        }
        return s;
    }

    public static final String LEFT_COORDINATE_JS = "return document.getElementsByClassName('flash').item(0).offsetLeft";
    public static final String TOP_COORDINATE_JS = "return document.getElementsByClassName('flash').item(0).offsetTop";

    public static final String TOP_TEXT_FIELD_XPATH = "//input[@id='top']";
    public static final String LEFT_TEXT_FIELD_XPATH = "//input[@id='left']";
    public static final String PROCESS_BUTTON_XPATH = "//button[@id='process']";
}
