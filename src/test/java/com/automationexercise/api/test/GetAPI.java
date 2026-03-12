package com.automationexercise.api.test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class GetAPI {

    @Test

    public void getUserInfo()
    {
    Playwright playwright = Playwright.create();
    APIRequest apiRequest =playwright.request();
    APIRequestContext  apiRequestContext = apiRequest.newContext();
    APIResponse apiResponse =apiRequestContext.get("https://automationexercise.com/api/productsList");
    int statusCode = apiResponse.status();
    System.out.println(statusCode);

    System.out.println(apiResponse.statusText());
    }
}
