package com.automationexercise.api.test.examples;

import com.automationexercise.api.pojo.BookingDates;
import com.automationexercise.api.pojo.BookingDatesLombok;
import com.automationexercise.api.pojo.UsersLombok;
import com.automationexercise.api.test.base.BaseAPI;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

public class UsingLombok extends BaseAPI {

    @Test
    public void postCallMethodUsingLombok ()
    {
       BookingDatesLombok bookingDatesLombok = BookingDatesLombok.builder().
                checkin("2026-04-01")
               .checkout("2026-04-02").build();

        UsersLombok usersLombok = UsersLombok.builder().firstname("Azhar")
                .lastname("Jamal").totalprice(111).depositpaid(true).bookingdates(bookingDatesLombok).additionalneeds("Lunch").build();

        APIResponse apiResponse =apiRequestContext.post("/booking", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(usersLombok));
        System.out.println(apiResponse.status());
        System.out.println(usersLombok);
    }
}
