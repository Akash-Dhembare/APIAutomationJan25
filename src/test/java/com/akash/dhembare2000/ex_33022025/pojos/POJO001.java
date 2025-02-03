package com.akash.dhembare2000.ex_33022025.pojos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POJO001 {
    public static void main(String[] args) {
        String payload= "{\n" +
                "                        \"username\" : \"admin\" ,\n" +
                "                        \"password\" : \"password123\"\n" +
                "                }";

        RestAssured.given().baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/auth")
                .contentType(ContentType.JSON).log().all()
                .body(payload)
                .when().post().then().log().all().statusCode(200);

    }
}
