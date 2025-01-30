package com.akash.dhembare2000.ex_30012025.CRUD.GET;

import io.restassured.RestAssured;

public class BDDStyleGet {
    // GET Request - https://zippopotam.us/IN/560037
    // URL
    // Header
    // Get Method
    // base url = https://zippopotam.us
    // base path = /IN/560037
    // Payload - No
    // Auth - Basic, Digest, JWT, Bearer Token, OAuth 2.0? = No

    // Verification
    // Status Code
    // Response Body
    // Time, Headers, Cookies

    // Gherkin -> Given, When, Them -> Style ->

    // BDD - Framework different
    // Syntax - Gherkin Syntax - (given, when, then)
    // Non BDD and still Gherkin syntax (Using classes)

    // given
    // URL
    // Header, Cookies
    // base url = https://zippopotam.us
    // base path = /IN/560037
    // Auth - Basic, Digest, JWT, Bearer Token, OAuth 2.0? = No

    // when
    // Payload - No/Yes - JSON, XML -> String, Hashmap, Object Class
    // GET Method

    // Then()
    // Response Validation
    // Status Code
    // Response Body
    // Time, Headers, Cookies

    public static void main(String[] args) {

        test1();
        test2();
    }

    private static void test1() {
        RestAssured
                .given()
                .baseUri("https://zippopotam.us")
                .basePath("/IN/560037")
                .when()
                .log().all().get()
                .then()
                .statusCode(200).log().all();
    }

    private static void test2() {
        RestAssured
                .given()
                .baseUri("https://zippopotam.us")
                .basePath("/IN/-1")
                .when()
                .log().all().get()
                .then()
                .statusCode(201).log().all();
    }
    // Duplicacy in Code in BDD

}
