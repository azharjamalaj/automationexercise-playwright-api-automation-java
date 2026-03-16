package com.automationexercise.api.test.examples;

import com.automationexercise.api.test.base.BaseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PostCallUsingJsonFile extends BaseAPI {

    @Test
    public void postCallUsingJsonFile() throws IOException {
        File file = new File("./src/main/java/com/automationexercise/api/jsonfile/CreateJson.json");
        byte[] fileByte =Files.readAllBytes(file.toPath());

        APIResponse apiResponse= apiRequestContext.post("/booking", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(fileByte));

        System.out.println(apiResponse.status());

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
