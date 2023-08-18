package com.rest;

import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class DownloadFile {
 // https://github.com/appium-boneyard/sample-code/raw/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk
 // https://raw.githubusercontent.com/appium-boneyard/sample-code/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk

    @Test
    public void download_file() throws IOException {
        InputStream is = given().
                baseUri("https://raw.githubusercontent.com")
                .log().all().
                when().
                get("/appium-boneyard/sample-code/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
                then().log().all().extract().response().asInputStream();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        os.write(bytes);
        os.close();
    }
}
