package com.rest;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class BlackListed {

   @Test
   public void validate_response_body_logging() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key"))).
                log().all().
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200);
    }

}
