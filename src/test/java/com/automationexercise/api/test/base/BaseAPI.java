package com.automationexercise.api.test.base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseAPI {

    protected APIRequestContext apiRequestContext;
    protected Properties prop;
    protected Playwright playwright;
    protected APIRequest apiRequest;



    @BeforeTest
    public void setup() {

        try {
            prop = new Properties();

            FileInputStream fis = new FileInputStream("./src/main/java/com/automationexercise/api/resources/config.properties");
            prop.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }
        playwright = Playwright.create();
        apiRequest = playwright.request();
        apiRequestContext = apiRequest.newContext(new APIRequest.NewContextOptions().setBaseURL(prop.getProperty("base-url")));
    }

    @AfterTest
    public void tearDown()
    {
        playwright.close();
    }
}
