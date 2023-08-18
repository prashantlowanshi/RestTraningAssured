package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import static io.restassured.RestAssured.*;

public class ReuseFilters {

    @BeforeClass
    public void beforeclass() throws FileNotFoundException {

        PrintStream fileoutPrintStream = new PrintStream(new File("restassured.log"));

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addFilter(new RequestLoggingFilter(fileoutPrintStream)).
                addFilter(new ResponseLoggingFilter(fileoutPrintStream));
        requestSpecification = requestSpecBuilder.build();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void LoggingFiltersMethod() throws FileNotFoundException {
        PrintStream FileOutPutStream = new PrintStream(new File("restassured.log"));
        given(requestSpecification).
                baseUri("https://postman-echo.com").
                filter(new RequestLoggingFilter(LogDetail.BODY, FileOutPutStream)).
                filter(new ResponseLoggingFilter(LogDetail.STATUS, FileOutPutStream)).
                when().
                get("/get").
                then().spec(responseSpecification).
                assertThat().
                statusCode(200);
    }
}
