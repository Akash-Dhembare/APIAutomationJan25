package com.akash.dhembare2000.ex_31012025.TESTNGExamples.Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Test001_Assertions {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    Integer bookingID;

    @Test
    public void test_create_new_booking(){

        String payload_POST="{\n" +
                "    \"firstname\" : \"Akash\",\n" +
                "    \"lastname\" : \"Dhembare\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        // Given
        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST);

        // When
        response = requestSpecification.when().post();

        // Then
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Assertions
        // 3 Types
        // Rest Assured Default - Hamcrest
        validatableResponse.body("booking.firstname", Matchers.equalTo("Akash"));
        validatableResponse.body("booking.lastname",Matchers.equalTo("Dhembare"));
        validatableResponse.body("booking.depositpaid", Matchers.equalTo(true));

        // TestNG - Soft Assertions vs Hard Assertions
        // Soft Assertions - This means even if any assertion fails, the remaining statements in that test method will be executed.
        // Hard Assertions - This means that if any assertion fails, the remaining statements in that test method will not be executed.

        // AsserJ -> Third Party Library


    }
}
