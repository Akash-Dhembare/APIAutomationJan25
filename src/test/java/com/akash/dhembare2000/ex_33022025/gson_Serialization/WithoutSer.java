package com.akash.dhembare2000.ex_33022025.gson_Serialization;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class WithoutSer {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Description ("Verify that create booking is working with valid payload")
    @Test
    public void testNonBDDStylePOSTPositive(){
        String BASE_URL = "https://restful-booker.herokuapp.com/";
        String BASE_PATH = "/booking";

        String payload = "{\n" +
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

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.basePath(BASE_PATH);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);

        response = requestSpecification.when().log().all().post();

        validatableResponse = response.then().log().all().statusCode(200);

    }
}
