package com.akash.dhembare2000.ex_33022025.Homework;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class IntegrationScenario {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingID;

    @Test
    public void test1_CreateToken(){
        String payloadForToken = "{\n" +
                "                \"username\" : \"admin\",\n" +
                "                \"password\" : \"password123\"\n" +
                "        }";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payloadForToken);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        token=response.then().extract().path("token");
        System.out.println("token is : "+ token);
    }

    @Test
    public void test2_createBookingForBookingID(){
        Booking booking=new Booking();
        booking.setFirstname("Tejas");
        booking.setLastname("Bhandwalkar");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates  bookingDates=new BookingDates();
        bookingDates.setCheckin("2024-02-02");
        bookingDates.setCheckout("2024-02-04");

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Snacks");

        System.out.println(booking);

        Gson gson=new Gson();
        String jsonStringPayload= gson.toJson(booking);
        System.out.println(jsonStringPayload);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.body(jsonStringPayload);
        requestSpecification.contentType(ContentType.JSON);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        String responseString=response.asString();
        System.out.println(responseString);

        BookingResponse bookingResponse=gson.fromJson(responseString, BookingResponse.class);
        System.out.println(bookingResponse);

        bookingID= bookingResponse.getBookingid();
        System.out.println(bookingID);

    }

    @Description("Update the Booking Name, Get the Booking by Id and verify.")
    @Test
    public void test3_UpdateBookingandVerifyBooking(){
        //Step3-Token ID and Booking use in PUT request
        // {
        //        "firstname": "Akash",
        //        "lastname": "Dhembare",
        //        "totalprice": 111,
        //        "depositpaid": true,
        //        "bookingdates": {
        //            "checkin": "2018-01-01",
        //            "checkout": "2019-01-01"
        //        },
        //        "additionalneeds": "Breakfast"
        //}
        Map<String, Object> payloadPutMap=new LinkedHashMap<>();
        payloadPutMap.put("firstname", "Akash");
        payloadPutMap.put("lastname", "Dhembare");
        payloadPutMap.put("totalprice", 111);
        payloadPutMap.put("depositpaid", false);

        Map<String, Object> bookingDatesMap=new LinkedHashMap<>();
        bookingDatesMap.put("checkin","2018-01-01");
        bookingDatesMap.put("checkout","2019-02-01");

        payloadPutMap.put("bookingdates", bookingDatesMap);
        payloadPutMap.put("additionalneeds","Breakfast");

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.body(payloadPutMap);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        response=requestSpecification.when().log().all().put();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        String responseStringGet=response.asString();
        System.out.println(responseStringGet);

        Gson gson= new Gson();
        BookingResponse bookingResponseGet=gson.fromJson(responseStringGet, BookingResponse.class);
        System.out.println(bookingResponseGet);

        JsonPath jsonPath= JsonPath.from(responseStringGet);
        System.out.println(jsonPath.getString("firstname"));


        // TestNG Assertions
        Assert.assertEquals(jsonPath.getString("firstname"), "Akash");
        Assert.assertEquals(jsonPath.getString("lastname"), "Dhembare");
        Assert.assertEquals(jsonPath.getString("totalprice"), "111");
        Assert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01");
    }

    @Description("Create a Booking, Delete the Booking with Id and Verify using GET request that it should not exist.")
    @Test
    public void test4_DeleteBooking(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.cookie("token", token);
        requestSpecification.contentType(ContentType.JSON);

        response= requestSpecification.when().log().all().delete();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
    }

    @Description("Verify Delete Booking using GET request that it should not exist.")
    @Test
    public void test5_VerifyDeleteBooking(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);

        response=requestSpecification.when().log().all().get();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(404);
    }

    @Description("Trying to Update on a Delete Id -> 404")
    @Test
    public void test6_VerifyUpdateDeletedID(){
        Map<String, Object> payloadPutMap=new LinkedHashMap<>();
        payloadPutMap.put("firstname", "Vikram");
        payloadPutMap.put("lastname", "Arekar");
        payloadPutMap.put("totalprice", 222);
        payloadPutMap.put("depositpaid", false);

        Map<String, Object> bookingDatesMap=new LinkedHashMap<>();
        bookingDatesMap.put("checkin","2020-01-01");
        bookingDatesMap.put("checkout","2021-02-01");

        payloadPutMap.put("bookingdates", bookingDatesMap);
        payloadPutMap.put("additionalneeds","Dinner");

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.body(payloadPutMap);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        response=requestSpecification.when().log().all().put();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(405);
    }



    @Description("Trying to Update with invalid payload")
    @Test
    public void test7_VerifyWithInvalidPayload(){
        String payload_Post="{\n" +
                "   1233 \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_Post);

        response = requestSpecification.when().log().all().post();

        validatableResponse = response.then().log().all().statusCode(400);

    }


}
