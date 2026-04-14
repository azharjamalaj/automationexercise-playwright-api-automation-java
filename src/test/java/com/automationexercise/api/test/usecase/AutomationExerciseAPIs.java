package com.automationexercise.api.test.usecase;

import com.automationexercise.api.pojo.BookingDatesLombok;
import com.automationexercise.api.pojo.RegisterUser;
import com.automationexercise.api.test.base.BaseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AutomationExerciseAPIs  extends BaseAPI {

    @Test
    public void invokeProductListMethod() throws IOException {
        APIResponse apiResponse = apiRequestContext.get(prop.getProperty("product-list"));
        Assert.assertEquals(apiResponse.status(),200,"Should give the 200 status message" );
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void invokeBrandList() throws IOException {
        APIResponse apiResponse = apiRequestContext.get(prop.getProperty("brand-list"));
        Assert.assertEquals(apiResponse.status(),200,"Should give the 200 status message" );
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void registerUser() throws IOException {
        RegisterUser registerUser= RegisterUser.builder().
                name("Azhar").email("azhar9@gmail.com").password("azhar9").
                title("Mr").birthDate("21").birthMonth("02").birthYear("1990")
                .firstName("Azhar").lastName("Jamal").company("Google").address1("Bengaluru")
                .address2("Kr Puram").country("India").zipcode("560001").state("Karnataka").city("Bengaluru").mobileNumber("9009009001")
                .build();
        System.out.println(registerUser.toString());
        APIResponse apiResponse = apiRequestContext.post(prop.getProperty("create-account"), RequestOptions.create().setHeader("Content-Type","application/json")
                .setData(registerUser));

        System.out.println(apiResponse.status());
        System.out.println(apiResponse.statusText());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());

        System.out.println(jsonNode.toPrettyString());

        if (jsonNode.get("responseCode").asInt() == 201)
        {
            System.out.println("User Created succesfully");
            Assert.assertEquals(jsonNode.get("message").asText(),"User created!");
        }
        else if(jsonNode.get("responseCode").asInt() == 400)
        {
            System.out.println("Email already present");
            Assert.assertEquals(jsonNode.get("message").asText(),"Bad request, name parameter is missing in POST request.","User already register with the email");
        }

        APIResponse apiResponse1 = apiRequestContext.get(prop.getProperty("search-by-email"),
        RequestOptions.create().setQueryParam("email", registerUser.getEmail()).setHeader("Content-Type","application/json"));



        System.out.println(registerUser.getEmail());
        System.out.println(apiResponse1.url());
        Assert.assertEquals(apiResponse1.status(),200);
        JsonNode jsonNode1 = objectMapper.readTree(apiResponse1.body());
        System.out.println(jsonNode1.toPrettyString());
        String email = jsonNode1.get("email").asText();
        System.out.println(email);
    }
}
