package com.restfulbooker.api.api;

import com.restfulbooker.api.payloads.Auth;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi {

    private static final String apiUrl = baseUrl + "auth/";

    public static Response postAuth(Auth payload){
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .filters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .post(apiUrl);
    }
}
