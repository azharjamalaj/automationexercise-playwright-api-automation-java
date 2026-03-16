package com.automationexercise.api.test.examples;

import com.automationexercise.api.test.base.BaseAPI;
import com.automationexercise.api.test.base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import freemarker.core.JSONOutputFormat;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostCallMethod extends BaseAPI {

    @Test
    public void createUserNewWay() throws IOException {
        Map<String, Object> bookingdates = new HashMap<String,Object>();
        bookingdates.put("checkin","2026-04-01");
        bookingdates.put("checkout","2026-05-01");
        Map<String, Object> jsonData = new HashMap<String,Object>();
        jsonData.put("firstname", "Azhar");
        jsonData.put("lastname", "Jamal");
        jsonData.put("totalprice", "2000");
        jsonData.put("bookingdates", bookingdates);
        jsonData.put("depositpaid", "true");
        jsonData.put("additionalneeds", "Breakfast");

        System.out.println(
                jsonData
        );

       APIResponse apiResponse= apiRequestContext.post("/booking", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(jsonData));

       int status = apiResponse.status();
        System.out.println(status);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode =objectMapper.readTree(apiResponse.body());

        System.out.println(jsonNode.toPrettyString());

        String bookingID = String.valueOf(jsonNode.get("bookingid"));
        System.out.println(bookingID);


        APIResponse apiResponse1 =apiRequestContext.get(apiResponse.url()+"/"+bookingID, RequestOptions.create().setHeader("Content-Type", "application/json"));
        System.out.println(apiResponse1.status());
        System.out.println(apiResponse1.statusText());
    }
}
