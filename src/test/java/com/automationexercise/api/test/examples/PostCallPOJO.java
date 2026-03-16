package com.automationexercise.api.test.examples;

import com.automationexercise.api.pojo.BookingDates;
import com.automationexercise.api.pojo.User;
import com.automationexercise.api.test.base.BaseAPI;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

public class PostCallPOJO extends BaseAPI {
    BookingDates bookingDates = new BookingDates("2026-04-01", "2026-05-01" );
    User user = new User("Azhar", "Jamal", 1111, true,bookingDates,"Breakfast");

    @Test
    public void createPostCallPojo()
    {
        System.out.println(user);
    APIResponse apiResponse = apiRequestContext.post("/booking", RequestOptions.create().setHeader("Content-Type", "application/json").setData(user));
        System.out.println(apiResponse.status());

    }
}
