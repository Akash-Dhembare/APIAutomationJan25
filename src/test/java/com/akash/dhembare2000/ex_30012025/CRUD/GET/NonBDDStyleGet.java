package com.akash.dhembare2000.ex_30012025.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class NonBDDStyleGet {

    static RequestSpecification r= RestAssured.given();;

    public static void main(String[] args) {
        // Rest Assured Provide us lot of classes
        r.baseUri("https://zippopotam.us");

        testnonbdd1();
        testnonbdd2();
    }

    private static void testnonbdd1() {
        r.basePath("/IN/560037");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }
    private static void testnonbdd2() {
        r.basePath("/IN/-1");
        r.when().log().all().get();
        r.then().log().all().statusCode(404);
    }
}
