package com.automationexercise.api.test.examples;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLOutput;

public class ApiDisposeMethod {

    @Test
    public void useOfDisposeMethod() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();

        APIRequestContext apiRequestContext=apiRequest.newContext();

        APIResponse apiResponse =apiRequestContext.get("https://restful-booker.herokuapp.com/booking/698");

        System.out.println(apiResponse.status());
        System.out.println(apiResponse.statusText());
        System.out.println(apiResponse.url());
        System.out.println(apiResponse.headers());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode =objectMapper.readTree(apiResponse.body());

        System.out.println(jsonNode.toPrettyString());
        System.out.println(jsonNode.get("bookingdates").get("checkin"));





    }
}
