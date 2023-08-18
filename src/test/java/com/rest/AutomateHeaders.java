package com.rest;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class AutomateHeaders {

    @Test
    public void validate_response_body_logging() {
        given().
                baseUri("https://2a6a5454-2e64-47e4-9012-065a0d37b00f.mock.pstmn.io").
                header("header","value2").
                header("x-mock-match-request-headers", "header").
                when().
                get("/getHeader").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_multiheader_handling() {
      Header header = new Header("header","value2");
      Header matchheader = new Header("x-mock-match-request-headers", "header");
      Headers headers = new Headers(header, matchheader);
        given().
                baseUri("https://2a6a5454-2e64-47e4-9012-065a0d37b00f.mock.pstmn.io").
                headers(headers).
                when().
                get("/getHeader").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_multiheader_handling_usingmap() {
        HashMap<String ,String> headers = new HashMap<String ,String>();
        headers.put("header","value2");
        headers.put("x-mock-match-request-headers", "header");
        given().
                baseUri("https://2a6a5454-2e64-47e4-9012-065a0d37b00f.mock.pstmn.io").
                headers(headers).
                when().
                get("/getHeader").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_multiheader_in_the_request() {
     Header header1 = new Header("multiValueHeader","value3");
     Header header2 = new Header("multiValueHeader","value4");
     Headers headers = new Headers(header1, header2);
        given().
                baseUri("https://2a6a5454-2e64-47e4-9012-065a0d37b00f.mock.pstmn.io").
                headers(headers).
                log().headers().
                when().
                get("/getHeader").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void assert_response_headers() {
        HashMap<String ,String> headers = new HashMap<String ,String>();
        headers.put("header","value2");
        headers.put("x-mock-match-request-headers", "header");
        given().
                baseUri("https://2a6a5454-2e64-47e4-9012-065a0d37b00f.mock.pstmn.io").
                headers(headers).
                when().
                get("/getHeader").
                then().
                log().all().
                assertThat().
                statusCode(200).
                header("responseHeader", "resValue2");
    }

    @Test
    public void extract_response_headers() {
        HashMap<String ,String> headers = new HashMap<String ,String>();
        headers.put("header","value2");
        headers.put("x-mock-match-request-headers", "header");
        Headers extractheaders = given().
                baseUri("https://2a6a5454-2e64-47e4-9012-065a0d37b00f.mock.pstmn.io").
                headers(headers).
                when().
                get("/getHeader").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                headers();
        for (Header header : extractheaders)
        {
            System.out.print("Header name = " + header.getValue());
            System.out.println(" & Header value =" + header.getName());
        }
        System.out.println("The extracted value is " + extractheaders.get("responseHeader").getName());
        System.out.println("The extracted value is " + extractheaders.get("responseHeader").getValue());
    }

    @Test
    public void extarct_multivalue_response_header() {
        HashMap<String ,String> headers = new HashMap<String ,String>();
        headers.put("header","value1");
        headers.put("x-mock-match-request-headers", "header");

        Headers extractheaders = given().
                baseUri("https://2a6a5454-2e64-47e4-9012-065a0d37b00f.mock.pstmn.io").
                headers(headers).
                when().
                get("/getHeader").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                headers();
        List<String> values= extractheaders.getValues("multivalueheader");
        for(String value : values)
        {
            System.out.println("The values are -> ");
            System.out.println(value);
        }
    }
}




