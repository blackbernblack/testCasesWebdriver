package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.AjaxTestPage;
import com.dataart.selenium.pages.BasicPage;
import com.dataart.selenium.pages.HeaderPage;
import com.dataart.selenium.pages.LoginPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dataart.selenium.framework.BasePage.driver;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.models.UserBuilder.admin;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: tkonstantinov
 * Date: 21/10/13
 * Time: 18:39
 * To change this template use File | Settings | File Templates.
 */
public class AjaxTest extends BaseTest{
    private LoginPage loginPage;
    private BasicPage basicPage;
    private AjaxTestPage  ajaxTestPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        user = admin();
    }

   @Test
    public void sumNumbersAndCheckResultTest() {
        float x = 123.123f;
        float y = 123.123f;
        ajaxTestPage = loginPage.loginAs(user)
                .getHeader()
                .clickAjaxTestPage();
        assertThat(ajaxTestPage.calculate(x, y)).isEqualTo(x + y );
    }

   @Test
    public void sumStringAndCheckIncorrectResultTest() {
        ajaxTestPage = loginPage.loginAs(user).getHeader().clickAjaxTestPage();
        assertThat(ajaxTestPage.isIncorrextStringCalc("1" + AjaxTestPage.X_TEXT_FIELD_XPATH, "2")).isTrue();
    }
}
