package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class AutomatePost {
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
    public void validate_post_request()
    {
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"myThirdWorkspace\",\n" +
                "        \"description\": \"The new workspace\",\n" +
                "        \"type\": \"personal\"\n" +
                "    }\n" +
                "}";
        given().
        body(payload).
                when().
                post("/workspaces").
                then().
                log().all().
                assertThat().
                body("workspace.name", equalTo("myThirdWorkspace"),
                        "workspace.id", matchesPattern("[a-z0-9-]{36}"));

    }
}