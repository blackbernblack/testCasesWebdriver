package com.dataart.selenium.framework;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.*;
import java.util.Properties;

public class Settings {

    private static final String SELENIUM_BASEURL = "selenium.baseUrl";
    private static final String SELENIUM_BROWSER = "selenium.browser";
    private static final String SELENIUM_PROPERTIES = "selenium.properties";

    private String baseUrl;
    private BrowserType browser;
    private Properties properties = new Properties();

    public Settings() {
        loadSettings();
    }

    private void loadSettings() {
        properties = loadPropertiesFile();
        baseUrl = getPropertyOrThrowException(SELENIUM_BASEURL);
        browser = BrowserType.Browser(getPropertyOrThrowException(SELENIUM_BROWSER));
    }

    private Properties loadPropertiesFile() {
        try {
            // get specified property file
            String filename = getPropertyOrNull(SELENIUM_PROPERTIES);
            // it is not defined, use default
            if (filename == null) {
                filename = SELENIUM_PROPERTIES;
            }
            // try to load from classpath
            InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
            // no file in classpath, look on disk
            if (stream == null) {
                stream = new FileInputStream(new File(filename));
            }
            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }

    public String getPropertyOrNull(String name) {
        return getProperty(name, false);
    }

    public String getPropertyOrThrowException(String name) {
        return getProperty(name, true);
    }

    private String getProperty(String name, boolean forceExceptionIfNotDefined) {
        String result;
        if ((result = System.getProperty(name, null)) != null && result.length() > 0) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null && result.length() > 0) {
            return result;
        } else if (forceExceptionIfNotDefined) {
            throw new UnknownPropertyException("Unknown property: [" + name + "]");
        }
        return result;
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    public WebDriver getDriver() {
        return getDriver(browser);
    }

    private WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case FIREFOX:
                return new FirefoxDriver();
            case IE:
                return new InternetExplorerDriver();
            case GC:
                return new ChromeDriver();
            case OPERA:
                return new OperaDriver();
            case HTMLUNIT:
                return new HtmlUnitDriver();
            default:
                throw new UnknownBrowserException("Cannot create driver for unknown browser type");
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public BrowserType getBrowser() {
        return browser;
    }
}
