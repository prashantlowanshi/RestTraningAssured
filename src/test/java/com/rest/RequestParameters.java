
package com.rest;

import io.restassured.config.EncoderConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.HashMap;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestParameters {

    Response res;

    @Test
    public void validate_single_parameter() {
        given().
                baseUri("https://postman-echo.com").
                param("foo1", "bar1").
                queryParam("foo2", "bar2").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_multiple_parameter() {
        HashMap<String, String> queryparams = new HashMap<String, String>();
        queryparams.put("foo1", "bar1");
        queryparams.put("foo2", "bar1");

        given().
                baseUri("https://postman-echo.com").
                queryParams(queryparams).
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_multiple_value_query_parameter() {
        given().
                baseUri("https://postman-echo.com").
                queryParam("foo", "bar1,bar2,bar3").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void path_parameter() {
        given().
                baseUri("https://reqres.in/").
                pathParam("userId", "2").
                log().all().
                when().
                get("/api/users/{userId}").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void multipart_form_data() {
        given().
                baseUri("https://postman-echo.com").
                multiPart("foo1", "bar1").
                multiPart("foo2", "bar2").
                log().all().
                when().
                post("/post").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void form_urlencoded() {
        given().
                baseUri("https://postman-echo.com").
                config(config().encoderConfig(EncoderConfig.encoderConfig().
                        appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                formParam("key1", "value1").
                formParam("key2", "value2").
                log().all().
        when().
                post("/post").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }



}


