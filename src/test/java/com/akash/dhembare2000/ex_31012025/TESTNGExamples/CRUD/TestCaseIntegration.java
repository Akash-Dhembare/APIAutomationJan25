package com.akash.dhembare2000.ex_31012025.TESTNGExamples.CRUD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TestCaseIntegration {
    // Create a Token
    // Create a Booking
    // Perform a PUT request
    // Verify that PUT is success by GET Request
    // Delete the ID
    // Verify it doesn't exist GET Request

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    String bookingID;

    // Create a Token
    public String getToken(){
        String payload ="{\n" +
                "            \"username\" :\"admin\",\n" +
                "                \"password\" :\"password123\"\n" +
                "        }";

        // Given
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);

        // When
        response = requestSpecification.when().post();

        // Then
        validatableResponse= response.then();
        validatableResponse.statusCode(200);

        token= response.jsonPath().getString("token");
        System.out.println(token);

        return token;
    }

    // Create a Booking
    public String getBookingID(){
        String payload_POST="{\n" +
                "    \"firstname\" : \"Tejas\",\n" +
                "    \"lastname\" : \"Bhandwalkar\",\n" +
                "    \"totalprice\" : 222,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2025-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Dinner\"\n" +
                "}";

        // Given
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST);

        // When
        response = requestSpecification.when().post();

        // Then
        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(200);

        bookingID = response.jsonPath().getString("bookingid");
        System.out.println(bookingID);

        return bookingID;
    }


    // Perform a PUT request
    @Test
    public void test_1_update_request_put(){
        token=getToken();
        bookingID=getBookingID();

        String payloadPUT="{\n" +
                "        \"firstname\": \"Akash\",\n" +
                "        \"lastname\": \"Dhembare\",\n" +
                "        \"totalprice\": 111,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2018-01-01\",\n" +
                "            \"checkout\": \"2019-01-01\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        // Given
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        requestSpecification.body(payloadPUT);

        // When
        response = requestSpecification.when().put();

        // Then
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

    // Verify that PUT is success by GET Request
    @Test
    public void test_2_update_request_get(){

        // Given
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);

        // When
        response= requestSpecification.when().get();

        // Then
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

    // Delete the ID
    @Test
    public void test_3_delete_bookingid(){

        // Given
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);

        // When
        response = requestSpecification.when().delete();

        // Then
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
    }

    // Verify it doesn't exist GET Request
    @Test
    public void test_4_verify_get_after_delete(){
        // Given
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);

        // When
        response= requestSpecification.when().get();

        // Then
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
    }





}
