package com.rest;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import static io.restassured.RestAssured.given;
public class LoggingFilters {

    @Test
    public void LoggingFiltersMethod() throws FileNotFoundException {
        PrintStream FileOutPutStream = new PrintStream(new File("restassured.log"));
        given().
                baseUri("https://postman-echo.com").
                filter(new RequestLoggingFilter(LogDetail.BODY, FileOutPutStream)).
                filter(new ResponseLoggingFilter(LogDetail.STATUS, FileOutPutStream)).
                when().
                get("/get").
                then().
                assertThat().
                statusCode(200);
    }
}
