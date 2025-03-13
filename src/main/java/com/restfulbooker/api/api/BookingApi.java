package com.restfulbooker.api.api;

import com.restfulbooker.api.payloads.lombok.Booking;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookingApi extends BaseApi {

    private static final String apiUrl = baseUrl + "booking/";

    public static Response getBookings(){
        return given()
                .filters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .get(apiUrl);
    }

    public static Response getBooking(int id, String mediaType) {
        return given()
                .header("Accept", mediaType)
                .filters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .get(apiUrl + id);
    }

    public static Response postBooking(Booking payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .filters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .post(apiUrl);
    }

    public static Response deleteBooking(int id, String tokenValue) {
        return given()
                .header("Cookie", "token=" + tokenValue)
                .filters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .delete(apiUrl + id);
    }
}
