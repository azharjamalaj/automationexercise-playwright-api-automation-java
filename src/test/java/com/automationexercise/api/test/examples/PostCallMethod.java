package com.automationexercise.api.test.examples;

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

public class PostCallMethod extends BaseTest {

    @Test
    public String createUSer() throws IOException {
        Map<String, Object> jsonData = new HashMap<String, Object>();

        String data =  "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}'";
        jsonData.put("firstname", "Azhar");
        APIResponse apiResponse =apiRequestContext.post("https://restful-booker.herokuapp.com/booking" , RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(data));


        System.out.println(apiResponse.status());  ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());

        System.out.println(jsonNode.toPrettyString());

        return "1111";
    }
}
