
package com.rest;

        import io.restassured.RestAssured;
        import io.restassured.builder.RequestSpecBuilder;
        import io.restassured.builder.ResponseSpecBuilder;
        import io.restassured.filter.log.LogDetail;
        import io.restassured.http.ContentType;
        import io.restassured.response.Response;
        import io.restassured.specification.ResponseSpecification;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;
        import static io.restassured.RestAssured.get;
        import static io.restassured.RestAssured.given;
        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.Matchers.equalTo;
        import static org.hamcrest.Matchers.matchesPattern;

public class AutomatePut {
    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("X-Api-Key", "PMAK-64885d8ad79b421d66c98db6-2160d508ef313042392da64c9c1fff3bb5").
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_put_request()
    {
        String Workspaceid = "373c8f9e-ceb5-4db8-975f-81f1fc14845f";

        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"id\": \"373c8f9e-ceb5-4db8-975f-81f1fc14845f\",\n" +
                "        \"name\": \"WorkSpace Last\",\n" +
                "        \"description\": \"Last workspace is the best\",\n" +
                "        \"type\": \"personal\"\n" +
                "    }\n" +
                "}";

        given().
                body(payload).
                pathParam("workspaceId", Workspaceid).
                when().
                put("workspaces/{workspaceId}").
                then().
                log().all().
                assertThat().
                body("workspace.name", equalTo("WorkSpace Last"),
                        "workspace.id", matchesPattern("[a-z0-9-]{36}"),
                        "workspace.id", equalTo(Workspaceid));
    }
}