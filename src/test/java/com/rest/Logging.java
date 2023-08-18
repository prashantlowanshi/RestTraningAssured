package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

public class Logging {

    @Test
    public void validate_response_body_logging() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                log().all().
                when().
                get("/workspaces").
                then().
                log().ifError().
                assertThat().
                statusCode(200);
    }
}
