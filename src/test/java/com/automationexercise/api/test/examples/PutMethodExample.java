package com.automationexercise.api.test.examples;

import com.automationexercise.api.pojo.BookingDatesLombok;
import com.automationexercise.api.pojo.UsersLombok;
import com.automationexercise.api.test.base.BaseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

import java.io.IOException;

public class PutMethodExample extends BaseAPI {

    @Test
            public void putMethodCall() throws IOException {
        BookingDatesLombok bookingDatesLombok = BookingDatesLombok.builder()
                .checkin("2025-01-04")
                .checkout("2026-01-04").build();
        UsersLombok usersLombok = UsersLombok.builder().
                firstname("Azhar")
                .lastname("Jamal").totalprice(111).depositpaid(true).bookingdates(bookingDatesLombok).additionalneeds("Lunch").build();

        APIResponse apiResponse = apiRequestContext.post("/booking",
                RequestOptions.create().setHeader("Content-Type", "application/json")
                        .setData(usersLombok));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());

        String bookingId = jsonNode.get("bookingid").toPrettyString();
        System.out.println(bookingId);

        usersLombok.setFirstname("James");
        usersLombok.setLastname("Bond");
        System.out.println(usersLombok);
        APIResponse apiResponse1 = apiRequestContext.put("/booking/"+bookingId,
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Accept", "application/json")
                        .setHeader("Cookie", "token=abc123")
                        .setData(usersLombok));

        System.out.println(apiResponse1.status());

        ObjectMapper objectMapper1 = new ObjectMapper();
        JsonNode jsonNode1 =objectMapper1.readTree(apiResponse1.body());

        System.out.println(jsonNode1.toPrettyString());
    }


}
