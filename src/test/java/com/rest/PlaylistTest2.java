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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTest2 {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQDIYI2_pNwgrTKFyOOD1JgEFn_XAmDM43H0iPCalrQotUs7pKJkt3b1S_LJ7dlP0e578D1_1q9MyNF7qkRB1-BsZOL0m2cTNu7m8B1-C4kDx6dCJS4t1eaThvvnpHvW6zOoLwhiz9PmB_f5abio29o6TbWvWa1qcwe-rn9mu7KYvntJUS5OAPgSxULzqef36BY99oGddP0RrzOntvE4zCh1e6xfzaFj6ctSLf17An9ZyAkOYx4zuor0mmcEPnyscD0gS3ZCmJJjMFt4";

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
                //expectContentType(ContentType.JSON).
                        log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @org.testng.annotations.Test
    public void Shouldbeabletocreateplaylistwithname() {
        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given(requestSpecification).
                body(payload).
                when().post("/users/31lq5fnzg5wzgjoqovue4yeqyh64/playlists").
                then().spec(responseSpecification).
                assertThat().
                statusCode(400).
                body("error.status", equalTo(400),
                        "error.message", equalTo("Missing required field: name"));
    }

    @Test
    public void ShouldNotBeAbleToCreatePlaylistWithExpiredToken() {
        String payload = "{\n" +
                "    \"name\": \"New playlist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given().
                baseUri("https://api.spotify.com").
                basePath("v1").
                header("Authorization", "Bearer " + "12345").
                contentType(ContentType.JSON).
                log().all().
                body(payload).
                when().post("/users/31lq5fnzg5wzgjoqovue4yeqyh64/playlists").
                then().spec(responseSpecification).
                assertThat().
                statusCode(401).
                body("error.status", equalTo(401),
                        "error.message", equalTo("Invalid access token"));
    }
}
