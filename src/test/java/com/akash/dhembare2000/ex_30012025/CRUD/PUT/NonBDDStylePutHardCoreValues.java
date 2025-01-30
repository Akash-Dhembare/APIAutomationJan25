package com.akash.dhembare2000.ex_30012025.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDStylePutHardCoreValues {
   // Create a New Booking and take booking ID
    // Booking ID - 691
    // Token
    // Payload - {
    //    "firstname" : "Prathamesh",
    //    "lastname" : "Dhembare",
    //    "totalprice" : 222,
    //    "depositpaid" : true,
    //    "bookingdates" : {
    //        "checkin" : "2019-01-01",
    //        "checkout" : "2020-01-01"
    //    },
    //    "additionalneeds" : "Dinner"
    //}

    // POST - Auth - Token
    // POST - Booking ID
    // PUT - Token and Booking ID

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void test_put_positive_tc(){
        // POST - Auth - Token
        String token="906fd6e66b247cf";
        String booking_id="691";
        String payloadPUT="{\n" +
                "    \"firstname\" : \"Prathamesh\",\n" +
                "    \"lastname\" : \"Dhembare\",\n" +
                "    \"totalprice\" : 222,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2019-01-01\",\n" +
                "        \"checkout\" : \"2020-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+booking_id);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        requestSpecification.body(payloadPUT).log().all();

        Response response = requestSpecification.when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


    }

}
