package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class RequestPayloadAsJsonArray {
    ResponseSpecification customresponsespecification;

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://2a6a5454-2e64-47e4-9012-065a0d37b00f.mock.pstmn.io").
                addHeader("x-mock-match-request-body", "true").
                //setConfig(config.encoderConfig(EncoderConfig.encoderConfig().
                //        appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                setContentType("application/json;charset=utf-8").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON);
        customresponsespecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_array_as_list() {
        HashMap<String, String> obj5001 =new HashMap<String, String>();
        obj5001.put("id", "5003");
        obj5001.put("type", "Choclate");

        HashMap<String, String> obj5002 =new HashMap<String, String>();
        obj5002.put("id", "5002");
        obj5002.put("type", "Sugar");

        List<HashMap<String,String>> jsonList = new ArrayList<HashMap<String,String>>();
        jsonList.add(obj5001);
        jsonList.add(obj5002);

        given().
                body(jsonList).
                when().
                post("/post").
                then().spec(customresponsespecification).
                assertThat().
                body("msg", equalTo("Success"));
    }
}
