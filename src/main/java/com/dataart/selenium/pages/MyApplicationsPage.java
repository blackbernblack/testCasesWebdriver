package com.dataart.selenium.pages;

/**
 * Created with IntelliJ IDEA.
 * User: tkonstantinov
 * Date: 21/10/13
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
public class MyApplicationsPage extends BasicPage{
    public AppsContentPage getAppsContent() {
        return initPage(AppsContentPage.class);
    }
}
