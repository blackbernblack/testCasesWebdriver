package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * Created with IntelliJ IDEA.
 * User: tkonstantinov
 * Date: 18/10/13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class DownloadApplicationPage extends BasePage {

    public boolean hasCorrespondingValues(String title, String description,String category, String author, int downloads){
//      another way to cath String representation of JSONObject
//      String source = driver.getPageSource();
//      source = source.substring(source.indexOf("<pre>") + "<pre>".length(), source.indexOf("</pre>"));
        try {
            JSONObject obj = new JSONObject(driver.findElement(By.xpath(JSON_RESPOND_XPATH)).getText());
//          JSONObject obj = new JSONObject(source);
            if (obj.getString("description").equals(description) && obj.getString("title").equals(title)
                    && obj.getJSONObject("category").getString("title").equals(category)
                    && obj.getJSONObject("author").getString("name").equals(author)
                    && obj.getInt("numberOfDownloads") == downloads + 1) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static final String JSON_RESPOND_XPATH = "//body";
}
