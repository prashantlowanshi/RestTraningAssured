package com.rest;

import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AutomateGetHamcrest {

    @Test
    public void validate_response_body_hamcrest_learnings() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", contains("Team Workspace", "My Workspace"));
    }

    @Test
    public void validate_response_body_hamcrest_empty() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", contains("Team Workspace", "My Workspace"),
                "workspaces.name", is(not(emptyArray())),
                "workspaces.name", hasSize(2),
                //"workspaces.name", hasValue(endsWith("Workspace")),
                "workspaces[0]", hasKey("id"),
                "workspaces[0]", hasValue("Team Workspace"),
                "workspaces[0]", hasEntry("id", "737c6947-ddb0-4e5b-8e3f-b9ba8d07bebb"),
                "workspaces[0]", not(equalTo(Collections.emptyMap()))
                );
    }
}
