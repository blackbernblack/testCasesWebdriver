package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import static org.testng.AssertJUnit.fail;


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
          //  JSONObject obj = null;
//            try {
//                obj = readJsonFromUrl(driver.getCurrentUrl());
//            } catch (IOException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
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

//    private static String readAll(Reader rd) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        int cp;
//        while ((cp = rd.read()) != -1) {
//            sb.append((char) cp);
//        }
//        return sb.toString();
//    }
//
//    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//        InputStream is = new URL(url).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            JSONObject json = new JSONObject(jsonText);
//            return json;
//        } finally {
//            is.close();
//        }
//    }

    public static final String JSON_RESPOND_XPATH = "//body";
}
