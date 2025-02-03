package com.akash.dhembare2000.ex_33022025.JSONPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class JSONPath01 {
    // POST -> Create - Verify the Response
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingID;

    @Test
    public void test_post(){
        String payload_POST= "{\n" +
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

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST);

        response=requestSpecification.when().post();

        System.out.println(response.asString());

        JsonPath jsonPath=new JsonPath(response.asString());
        String bookingId=jsonPath.getString("bookingid");
        String firstname=jsonPath.getString("booking.firstname");
        String checkoutdate=jsonPath.getString("booking.bookingdates.checkout");

        System.out.println(bookingId);
        System.out.println(firstname);
        System.out.println(checkoutdate);

        assertThat(bookingId).isNotEmpty().isNotNull().isGreaterThan("0");
        assertThat(firstname).isNotNull().isNotEmpty().isEqualTo("Akash");
        assertThat(checkoutdate).isNotNull().isNotBlank();

        Assert.assertEquals(firstname, "Akash");


        }
    }

