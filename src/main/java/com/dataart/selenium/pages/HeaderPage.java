package com.dataart.selenium.pages;

import org.openqa.selenium.By;

import static com.dataart.selenium.framework.Utils.isElementPresent;

public class HeaderPage extends BasicPage {

    public final static By myApplications = By.xpath("//div[@class='navigation']/a[contains(text(),'My applications')]");

    public String getWelcomeMessage() {
        return driver.findElement(By.xpath("//div[@class='header']/div[@class='welcome']")).getText();
    }

    public Boolean isAbleToUploadApplication() {
        return isElementPresent(myApplications);
    }

    public MyApplicationsPage clickMyApplications() {
       // check Uniq title of application begore create another one
        driver.findElement(myApplications).click();
        return initPage(MyApplicationsPage.class);
    }

    public AjaxTestPage clickAjaxTestPage() {
        driver.findElement(By.xpath("//div[@class='navigation']/a[contains(text(),'Ajax')]")).click();
        return initPage(AjaxTestPage.class);
    }

    public JSTestPage clickJSTestPage() {
        driver.findElement(By.xpath("//div[@class='navigation']/a[contains(text(),'JS test')]")).click();
        return initPage(JSTestPage.class);
    }
}
