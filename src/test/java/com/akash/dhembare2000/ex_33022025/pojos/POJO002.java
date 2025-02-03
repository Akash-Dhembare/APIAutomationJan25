package com.akash.dhembare2000.ex_33022025.pojos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class POJO002 {
    // Hashmap Payload
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    String bookingID;

    @Test
    public void test_post_with_hashmap(){
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

        // Hashmap -> {} - map
        Map<String , Object> jsonBodyUsingMap= new LinkedHashMap<>();
        jsonBodyUsingMap.put("firstname","Akash");
        jsonBodyUsingMap.put("lastname","Dhembare");
        jsonBodyUsingMap.put("totalprice",111);
        jsonBodyUsingMap.put("depositpaid",true);

        Map<String , Object> bookingdatesMap=new LinkedHashMap<>();
        bookingdatesMap.put("checkin", "2018-01-01");
        bookingdatesMap.put("checkout", "2019-01-01");

        jsonBodyUsingMap.put("bookingdates", bookingdatesMap);
        jsonBodyUsingMap.put("additionalneeds", "Breakfast");

        System.out.println(jsonBodyUsingMap);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonBodyUsingMap);

        response = requestSpecification.when().log().all().post();

        validatableResponse=response.then().log().all().statusCode(200);

        bookingID = response.jsonPath().getString("bookingid");
        System.out.println(bookingID);
    }
}
