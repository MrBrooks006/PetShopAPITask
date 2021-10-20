package com.pet.Utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseUtils {


    static RequestSpecification requestSpecification;
    static Response response;

    public static RequestSpecification httpRequest() {
        requestSpecification = RestAssured.given ().accept ( ContentType.JSON )
                .and ().contentType ( ContentType.JSON );


        return requestSpecification;
    }


    public static Response requestType(String url, String requestType,Integer id, Object body) {



        switch (requestType.toUpperCase ()) {

            case "GET":
                response = requestSpecification.pathParam ( "petId",id ).get ( url+"/pet/{petId}" );
                break;
            case "POST":
                response = requestSpecification.body ( body ).post ( url+"/pet" );
                break;
            case "PATCH":
                response = requestSpecification.body ( body ).patch ( url+"/pet" );
                break;
            case "PUT":
                response = requestSpecification.body ( body ).put ( url+"/pet" );
                break;
            case "DELETE":
                response = requestSpecification.pathParam ( "petId",id ).delete ( url+"/pet/{petId}" );
                break;

        }

        return response;
    }


}
