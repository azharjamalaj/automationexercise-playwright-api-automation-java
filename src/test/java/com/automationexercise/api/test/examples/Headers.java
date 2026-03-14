package com.automationexercise.api.test.examples;

import com.automationexercise.api.test.base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.HttpHeader;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class Headers extends BaseTest {

    @Test
    public void headersMap() throws IOException {

       APIResponse apiResponse = apiRequestContext.get("https://restful-booker.herokuapp.com/booking/");

        ObjectMapper objectMapper =  new ObjectMapper();
        JsonNode jsonNode =objectMapper.readTree(apiResponse.body());

//        System.out.println(jsonNode.toPrettyString());
        System.out.println(apiResponse.headers());

        Map<String, String> header = apiResponse.headers();

        header.forEach((k,v) -> System.out.println( k + " : "  +v));
    }

    @Test
    public void usingHeadersArray()
    {
        APIResponse apiResponse =apiRequestContext.get("https://restful-booker.herokuapp.com/booking/");

        List<HttpHeader> headers =apiResponse.headersArray();

        headers.forEach(e-> System.out.println(e.name + " : "  +e.value));
    }
}
