
package com.rest;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Filters {

    @Test
    public void Filters() {
        given().
                baseUri("https://postman-echo.com").
                filter(new RequestLoggingFilter(LogDetail.BODY)).
                filter(new ResponseLoggingFilter(LogDetail.STATUS)).
                when().
                get("/get").
                then().
                assertThat().
                statusCode(200);
    }



}