package com.akash.dhembare2000.ex_30012025.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class NonBDDStylePost {

        // POST Request
        // URL -> https://restful-booker.herokuapp.com/auth
        // BODY - PAYLOAD - JSON
//        {
//            "username" : "admin",
//            "password" : "password123"
//        }
        // HEARDERS -> Content-Type = application/json

        public static void main(String[] args) {
            // Payload -> String (1%), Hashmap (4%), Classes (95%)
            String payload = "{\n" +
                    "            \"username\" : \"admin\",\n" +
                    "            \"password\" : \"password123\"\n" +
                    "        }";

            // Given -> Request Specification
            RequestSpecification r=RestAssured.given();
            r.baseUri("https://restful-booker.herokuapp.com");
            r.basePath("/auth");
            r.contentType(ContentType.JSON).log().all();
            r.body(payload);

            // When -> Response
            Response response=r.when().post();

            // Then -> Validatable Response
            ValidatableResponse validatableResponse=response.then();
            validatableResponse.log().all().statusCode(200);
        }
}
