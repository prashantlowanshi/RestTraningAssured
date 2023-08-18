package com.rest;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class upload_file_multipart_form_data {

    @Test
    public void path_parameter() {
        String attributes = "{\"name\":\"FileUpload.txt\", \"parent\":{\"id\":\"123456\"}}";
        given().
                baseUri("https://postman-echo.com").
                multiPart("file", new File("FileUpload.txt")).
                multiPart("attributes", attributes, "application/json").
                log().all().
                when().
                post("/post").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }
}
