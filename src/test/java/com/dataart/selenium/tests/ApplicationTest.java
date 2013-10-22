package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.framework.Utils;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dataart.selenium.framework.BasePage.driver;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.framework.Utils.isElementPresent;
import static com.dataart.selenium.models.UserBuilder.admin;
import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: tkonstantinov
 * Date: 18/10/13
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationTest extends BaseTest {

    private LoginPage loginPage;
    private BasicPage basicPage;
    private AppsContentPage appsContentPage;
    private ApplicationPage applicationPage;
    private DownloadApplicationPage downloadApplicationPage;
    private CreateEditApplicationPage createEditApplicationPage;
    private MyApplicationsPage myApplicationsPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        appsContentPage = initPage(AppsContentPage.class);
        user = admin();
    }

   @Test
    public void verifyCorrespondingAppValuesTest() {
        loginPage.loginAs(user);
        applicationPage = appsContentPage.openAppPage(1);
        String noteAuthor = applicationPage.getAuthorText();
        String noteTitle = applicationPage.getTitleText();
        String noteDescription = applicationPage.getDescriptionText();
        String noteCategory = applicationPage.getCategoryText();
        int noteDownloads = applicationPage.getNumberOfDownloads();
        downloadApplicationPage = applicationPage.clickDownload();
        assertThat(
                downloadApplicationPage.hasCorrespondingValues(noteTitle, noteDescription, noteCategory, noteAuthor,
                        noteDownloads)
        ).isEqualTo(true);

    }

   @Test
    public void createAppWithoutImagesTest() {
        loginPage.loginAs(user).getHeader().clickMyApplications();
        String appTitle = "Application " + Utils.getUUID();
        createEditApplicationPage =  appsContentPage.clickToAddNewApp();
        appsContentPage = createEditApplicationPage.createNoImageApplication(appTitle).getAppsContent();
        assertThat(appsContentPage.isAppDisplayed(appTitle)).isTrue();
        applicationPage = appsContentPage.openAppPage(appTitle);
        assertThat(applicationPage.isDownloadable()).isTrue();
    }

    @Test
    public void editAppWithoutImagesAndVerifyChangesTest() {
        loginPage.loginAs(user).getHeader().clickMyApplications();
        //All apps without images, so choose first app for instance
        applicationPage = appsContentPage.openAppPage(0);
        createEditApplicationPage = applicationPage.editNoImageApplication();
        createEditApplicationPage.editNoImageApplication("Edited Application" + Utils.getUUID());
        assertThat(isElementPresent(basicPage.flash)).isTrue();
        assertThat(basicPage.getFlashMessage()).isEqualTo("Application edited");
    }

    @Test
    public void createAppWithImageAndIconTest() {
        loginPage.loginAs(user).getHeader().clickMyApplications();
        createEditApplicationPage = appsContentPage.clickToAddNewApp();
        createEditApplicationPage.createImageApplication("Application " + Utils.getUUID());
    }

    @Test
    public void createAppDownloadAndVerifyPopular() {
        loginPage.loginAs(user).getHeader().clickMyApplications();
        createEditApplicationPage = appsContentPage.clickToAddNewApp();
        String appTitle = "Application " + Utils.getUUID();
        myApplicationsPage = createEditApplicationPage.createNoImageApplication(appTitle);
        applicationPage =  myApplicationsPage.getAppsContent().openAppPage(appTitle);
        while (!applicationPage.isAppPopular(appTitle)) {
            applicationPage.clickDownload();
            driver.navigate().back();
            driver.navigate().refresh();
        }
        assertTrue(applicationPage.isAppPopular(appTitle));
        assertEquals(applicationPage.clickPopularApp(appTitle).getTitleText(),appTitle);
    }

    @Test
    public void deleteAppAndVerifyRemoved() {
        loginPage.loginAs(user).getHeader().clickMyApplications();
        applicationPage = appsContentPage.openAppPage(0);
        basicPage = applicationPage.deleteApp();
        assertThat(isElementPresent(basicPage.flash)).isTrue();
        assertThat(basicPage.getFlashMessage()).isEqualTo("Deleted");
    }
}
