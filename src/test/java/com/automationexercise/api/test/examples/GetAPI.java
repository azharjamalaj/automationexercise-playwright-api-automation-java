package com.automationexercise.api.test.examples;

import com.automationexercise.api.test.base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.HttpHeader;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GetAPI extends BaseTest {



    @BeforeTest
    public void setUp() {
        playwright =Playwright.create();
        apiRequest = playwright.request();
        apiRequestContext = apiRequest.newContext();
    }

    @Test
    public void getSpecificUser() throws IOException {
      APIResponse apiResponse=  apiRequestContext.get("https://automationexercise.com/api/productsList", RequestOptions.create()
              .setQueryParam("id",1)
              .setQueryParam("brand","Polo"));
      int statusCode = apiResponse.status();

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNodeResponse =objectMapper.readTree(apiResponse.body());
        String jsonPrettyResponse = jsonNodeResponse.toPrettyString();
//       JsonNode userNode = jsonNodeResponse.get("products");
        System.out.println(jsonPrettyResponse);

      Iterator<String> itr = jsonNodeResponse.fieldNames();
      while (itr.hasNext())
      {
         String fieldname= itr.next();
          System.out.println(fieldname);
      }



    }

    @Test
    public void getUserInfo() throws IOException {

        APIResponse apiResponse = apiRequestContext.get("https://restful-booker.herokuapp.com/booking");
//        APIResponse apiResponse = apiRequestContext.get("https://automationexercise.com/api/productsList");
        int statusCode = apiResponse.status();
        System.out.println(statusCode);

        System.out.println(apiResponse.statusText());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyResponse = jsonResponse.toPrettyString();
        Iterator<String> itr = jsonResponse.fieldNames();

        while (itr.hasNext())
        {
            String field = itr.next();
            System.out.println(field);
        }
        System.out.println(jsonResponse.fieldNames().toString());


//        System.out.println(jsonPrettyResponse);

        Map<String, String> headers =apiResponse.headers();
        System.out.println(headers);

        List<HttpHeader> headerArray = apiResponse.headersArray();
        System.out.println(headerArray);
    }


}
