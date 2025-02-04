package com.akash.dhembare2000.ex_33022025.gson_Serialization;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

public class GSONSerialization {
    // Strings
    // Hashmap
    // Class - Pojo -
    // Create class for the Payload - pojo

    // PUT Request
    // token, booking id

    //{
    //    "firstname" : "Akash",
    //    "lastname" : "Dhembare",
    //    "totalprice" : 111,
    //    "depositpaid" : true,
    //    "bookingdates" : {
    //        "checkin" : "2018-01-01",
    //        "checkout" : "2019-01-01"
    //    },
    //    "additionalneeds" : "Breakfast"
    //}

    RequestSpecification requestSpecification =  RestAssured.given();

    Response response;
    ValidatableResponse validatableResponse;

    @Description("TC#1 - Verify that create booking is working with valid payload")
    @Test
    public void testNonBDDStylePOSTPositive() {
        Booking booking=new Booking();
        booking.setFirstname("Akash");
        booking.setLastname("Dhembare");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingDates=new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");

        booking.setBookingDates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking); // It will print Java Object

        // Java Object -> JSON String (bytestream) - Serialization

        Gson gson=new Gson();
        String jsonStringPayload = gson.toJson(booking);
        System.out.println(jsonStringPayload);

        String BASE_URL = "https://restful-booker.herokuapp.com/";
        String BASE_PATH = "/booking";


        requestSpecification.baseUri(BASE_URL);
        requestSpecification.basePath(BASE_PATH);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonStringPayload);

        response = requestSpecification.when().log().all().post();
        String responseString = response.asString();

        System.out.println(responseString);

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


    }

}
