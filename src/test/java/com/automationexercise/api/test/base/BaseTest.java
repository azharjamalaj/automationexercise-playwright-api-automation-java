package com.automationexercise.api.test.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected  Playwright playwright;
    protected APIRequest apiRequest;
    protected APIRequestContext apiRequestContext;
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode;

    @BeforeTest
    public void setUp() {
        playwright =Playwright.create();
        apiRequest = playwright.request();
        apiRequestContext = apiRequest.newContext();
    }

    @AfterTest
    public void tearDown() {
        playwright.close();
    }

}
