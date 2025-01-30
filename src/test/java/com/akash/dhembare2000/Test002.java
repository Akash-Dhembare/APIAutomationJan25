package com.akash.dhembare2000;

import io.restassured.RestAssured;

public class Test002 {
    public static void main(String[] args) {
        System.out.println("Rest Assured Test Case");
        System.out.println("Get Request Demo");

        // Gherkins Syntax
        // given() - url, headers, body or payload
        // when() - http methods - get, post, patch, put, delete
        // then() - verify the response - Expected Result == Actual Result
        RestAssured
                .given()
                  .baseUri("https://restful-booker.herokuapp.com")
                  .basePath("/booking/1").log().all()
                .when()
                  .get()
                .then().log().all()
                .statusCode(200);
    }
}
