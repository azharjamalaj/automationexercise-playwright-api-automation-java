package com.automationexercise.api.test.usecase;

import com.automationexercise.api.test.base.BaseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AutomationExerciseAPIs  extends BaseAPI {

    @Test
    public void invokeProductListMethod() throws IOException {
        APIResponse apiResponse = apiRequestContext.get("api/productsList");
        Assert.assertEquals(apiResponse.status(),200,"Should give the 200 status message" );
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());

        System.out.println(jsonNode.toPrettyString());

    }

}
