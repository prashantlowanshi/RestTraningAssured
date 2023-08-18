package com.rest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestSpecificationExample {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5");
    }

    @Test
    public void validate_status_code() {
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
                assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void validate_response_body() {
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("workspaces[0].name").toString(), equalTo("Team Workspace"));
    }
}
