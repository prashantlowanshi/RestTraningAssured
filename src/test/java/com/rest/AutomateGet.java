package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AutomateGet {

    Response res;

    @Test
    public void validate_status_code() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_response_body(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", hasItems("Team Workspace", "My Workspace"),
                        "workspaces.type", hasItems("team", "team"),
                        "workspaces[0].name", equalTo("Team Workspace"),
                        "workspaces[1].name", is(equalTo("My Workspace")),
                        "workspaces.size()", equalTo(2),
                        "workspaces.name", hasItem("Team Workspace")
                );
    }

    @Test
    public void extract_response(){
        res = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract().
                response();
        System.out.println("response = " + res.asString());
    }

    @Test
    public void extract_single_value_from_response(){
        String name = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract().
                response().path("workspaces[1].name");
        System.out.println("workspace name = " + name);
        //System.out.println("workspace name = " + JsonPath.from(res).getString("workspaces[0].name"));
        //System.out.println("workspace name = " + jsonPath.getString("workspaces[0].name"));
        //System.out.println("workspace name = " + res.path("workspaces[0].name"));
    }

    @Test
    public void hamcrest_assert_on_extracted_response(){
        String name = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract().
                response().path("workspaces[0].name");
        System.out.println("workspace name = " + name);

        //   assertThat(name, equalTo("Team Workspace1"));
        Assert.assertEquals(name, "Team Workspace");
        //System.out.println("workspace name = " + JsonPath.from(res).getString("workspaces[0].name"));
        //ystem.out.println("workspace name = " + jsonPath.getString("workspaces[0].name"));
        //System.out.println("workspace name = " + res.path("workspaces[0].name"));
    }
}