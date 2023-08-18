package com.rest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonStaticImports {

    @Test
    public void simple_test_case(){
        RestAssured.given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                when().
                get("/workspaces").
                then().
                statusCode(200);
    }
}

