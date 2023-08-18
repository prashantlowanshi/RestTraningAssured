
package com.rest;

        import io.restassured.RestAssured;
        import io.restassured.builder.RequestSpecBuilder;
        import io.restassured.builder.ResponseSpecBuilder;
        import io.restassured.config.EncoderConfig;
        import io.restassured.filter.log.LogDetail;
        import io.restassured.http.ContentType;
        import io.restassured.specification.ResponseSpecification;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import static io.restassured.RestAssured.config;
        import static io.restassured.RestAssured.given;
        import static org.hamcrest.Matchers.equalTo;
        import static org.hamcrest.Matchers.matchesPattern;

public class RequestPayloadComplexJson {
    ResponseSpecification customresponsespecification;

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://2a6a5454-2e64-47e4-9012-065a0d37b00f.mock.pstmn.io").
                addHeader("x-mock-match-request-body", "true").
                //setConfig(config.encoderConfig(EncoderConfig.encoderConfig().
                //        appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                        setContentType("application/json;charset=utf-8").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON);
        customresponsespecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_post_request_payload_complex_json()
    {
        List<Integer> idArrayList = new ArrayList<Integer>();
        idArrayList.add(5);
        idArrayList.add(9);

        HashMap<String, Object> batterHashMap1 = new HashMap<String, Object>();
        batterHashMap1.put("id", "1001");
        batterHashMap1.put("type", "Regular");

        HashMap<String, Object> batterHashMap2 = new HashMap<String, Object>();
        batterHashMap2.put("id", idArrayList);
        batterHashMap2.put("type", "Chocolate");

        List<HashMap<String, Object>> batterArrayList = new ArrayList<HashMap<String, Object>>();
        batterArrayList.add(batterHashMap1);
        batterArrayList.add(batterHashMap2);

        HashMap< String, List<HashMap<String, Object>>> batterHashMap =
                new HashMap<String, List<HashMap<String, Object>>>();
        batterHashMap.put("batter", batterArrayList);

        List<String> typeArrayList = new ArrayList<String>();
        typeArrayList.add("test1");
        typeArrayList.add("test2");

        HashMap<String, Object> toppingHashmap1 = new HashMap<String, Object>();
        toppingHashmap1.put("id", "5001");
        toppingHashmap1.put("type", "None");

        HashMap<String, Object> toppingHashmap2 = new HashMap<String, Object>();
        toppingHashmap2.put("id", "5002");
        toppingHashmap2.put("type", typeArrayList);

        List<HashMap<String, Object>> toppingArrayList = new ArrayList<HashMap<String, Object>>();
        toppingArrayList.add(toppingHashmap1);
        toppingArrayList.add(toppingHashmap2);

        HashMap<String, Object> mainHashMap = new HashMap<String, Object>();
        mainHashMap.put("id", "0001");
        mainHashMap.put("type", "donut");
        mainHashMap.put("name", "Cake");
        mainHashMap.put("ppu", 0.55);
        mainHashMap.put("batters", batterHashMap);
        mainHashMap.put("topping", toppingArrayList);

        given().
                body(mainHashMap).
                when().
                post("/postComplexJson").
                then().spec(customresponsespecification).
                assertThat().
                body("msg", equalTo("Success"));
    }


}