package com.dataart.selenium.pages;

public class HomePage extends BasicPage {

    public HeaderPage getHeader() {
        return initPage(HeaderPage.class);
    }

    public AppsContentPage getAppsContent() {
        return initPage(AppsContentPage.class);
    }
}
