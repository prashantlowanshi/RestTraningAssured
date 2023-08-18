package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StaticImports {

    @Test
    public void simple_test_case(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200);
    }
}
