package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.api.BookingApi;
import com.restfulbooker.api.payloads.Auth;
import com.restfulbooker.api.payloads.AuthResponse;
import com.restfulbooker.api.payloads.BookingResponse;
import com.restfulbooker.api.payloads.lombok.Booking;
import com.restfulbooker.api.payloads.lombok.BookingDates;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class ApiStatusCodeTest {

    @Test
    public void getBookingShouldReturn200() {
        Response response = BookingApi.getBookings();

        Approvals.verify(response.getStatusCode());
    }

    @Test
    public void getBookingIdShouldReturn200() {
        Response response = BookingApi.getBooking(1, "application/json");
        Approvals.verify(response.getStatusCode());
    }

    @Test
    public void getBookingIdWithBadAcceptShouldReturn418() {
        Response response = BookingApi.getBooking(1, "text/plain");

        Approvals.verify(response.getStatusCode());
    }

    @Test
    public void postBookingReturns200() {
        BookingDates dates = new com.restfulbooker.api.payloads.lombok.BookingDates();
        Booking payload = com.restfulbooker.api.payloads.lombok.Booking.builder()
                .firstname("Mary")
                .lastname("White")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();

        Response response = BookingApi.postBooking(payload);
        Approvals.verify(response.getStatusCode());
    }

    @Test
    public void deleteBookingReturns201() {
        BookingDates dates = new BookingDates();
        Booking payload = Booking.builder()
                .firstname("Mary")
                .lastname("White")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();

        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);

        Auth auth = new Auth.Builder()
                .setUsername("admin")
                .setPassword("password123")
                .build();

        AuthResponse authResponse = AuthApi.postAuth(auth).as(AuthResponse.class);

        Response deleteResponse = BookingApi.deleteBooking(
                createdBookingResponse.getBookingid(),
                authResponse.getToken());

        Approvals.verify(deleteResponse.getStatusCode());
    }

}
