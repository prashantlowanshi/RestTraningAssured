package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Jsonschemavalidator {
    @Test
    public void validateJsonschema()
    {
        given().
                baseUri("https://postman-echo.com").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200).
        body(matchesJsonSchemaInClasspath("EchoGet.json"));
    }

}
