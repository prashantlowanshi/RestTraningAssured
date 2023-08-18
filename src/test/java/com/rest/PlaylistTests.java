package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQCW_wwgIpG0Te_h19IpX_-gR4OJIpnL0ldxS6R19aYz5xeaayATFRHvcZzOG0k-2bBBJjQrtCHMW5Ne5gz6asGkGWA6W9t7LSRDP2up_dXp5h9NyS4FWbQsWLB3Tfy-tNQuDL38ypZ-I6eK5oM4oA9_9cc6-w36IG9grZMwxwf-xzXB6AbkqEpqVgBIIi_XUAsgfbabxeQ4qXzaLpyizDiJz2RNYOhjvSLEBO1hM70uzrrY-2cuBJ5kjro6dWdad2mi3LT4Xaz6gqUf";

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("v1").
                addHeader("Authorization", "Bearer " + access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void Shouldbeabletocreateplaylist()
    {
        String payload ="{\n" +
                "    \"name\": \"Prashant Playlist Second\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification).
                body(payload).
                when().post("/users/31lq5fnzg5wzgjoqovue4yeqyh64/playlists").
                then().spec(responseSpecification).
                assertThat().
                statusCode(201).
                body("name", equalTo("Prashant Playlist Second"),
                        "description", equalTo("New playlist description"),
                        "public", equalTo(false));
    }
}
