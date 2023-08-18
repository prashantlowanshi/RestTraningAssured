package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class ValidatePostRequestAsMap {
    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("X-Api-Key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_post_request_using_map() {
        HashMap<String, Object > mainObject = new HashMap<String, Object>();

        HashMap <String, String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name", "MySixthWorkspace");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "RestAssuerd created this");
        mainObject.put("workspace", nestedObject);
        given().
                body(mainObject).
        when().
                post("/workspaces").
        then().
                log().all().
                assertThat().
                body("workspace.name", equalTo("MySixthWorkspace"),
                        "workspace.id", matchesPattern("[a-z0-9-]{36}"));
    }
}