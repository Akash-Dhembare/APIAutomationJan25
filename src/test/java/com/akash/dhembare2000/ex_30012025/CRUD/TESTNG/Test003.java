package com.akash.dhembare2000.ex_30012025.CRUD.TESTNG;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Test003 {
    @Test
    public void test_get(){
        RestAssured
                .given()
                .baseUri("https://zippopotam.us")
                .basePath("/IN/560037")
                .when()
                .log().all().get()
                .then()
                .statusCode(200).log().all();
    }
}
