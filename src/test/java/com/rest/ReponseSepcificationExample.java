package com.rest;

        import io.restassured.RestAssured;
        import io.restassured.builder.RequestSpecBuilder;
        import io.restassured.filter.log.LogDetail;
        import io.restassured.http.ContentType;
        import io.restassured.response.Response;
        import io.restassured.specification.ResponseSpecification;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;

        import static io.restassured.RestAssured.*;
        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.Matchers.equalTo;

public class ReponseSepcificationExample {

    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-Api-Key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5");
        requestSpecBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        responseSpecification = RestAssured.expect().
                statusCode(200).
                contentType(ContentType.JSON);
    }

    @Test
    public void validate_status_code() {
         get("/workspaces").
                then().spec(responseSpecification).
                log().all();
    }

    @Test
    public void validate_response_body() {
        Response response = get("/workspaces").
                then().spec(responseSpecification).
                log().
                all().extract().response();
        assertThat(response.path("workspaces[0].name").toString(), equalTo("Team Workspace"));
    }

}