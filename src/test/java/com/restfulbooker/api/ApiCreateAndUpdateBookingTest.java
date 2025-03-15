package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.api.BookingApi;
import com.restfulbooker.api.payloads.Auth;
import com.restfulbooker.api.payloads.AuthResponse;
import com.restfulbooker.api.payloads.BookingResponse;
import com.restfulbooker.api.payloads.lombok.Booking;
import com.restfulbooker.api.payloads.lombok.BookingDates;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ApiCreateAndUpdateBookingTest {
    @Test
    public void createAndUpdateBooking() {
        BookingDates dates = new BookingDates();
        Booking payload = Booking.builder()
                .firstname("Jane")
                .lastname("Eyre")
                .totalprice(500)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();

        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);

        System.out.println(createdBookingResponse.getBooking().toString());
        int bookingId = createdBookingResponse.getBookingid();

        Booking newPayload = Booking.builder()
                .firstname("Mary")
                .lastname("White")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();

        Auth auth = new Auth.Builder()
                .setUsername("admin")
                .setPassword("password123")
                .build();

        AuthResponse authResponse = AuthApi.postAuth(auth).as(AuthResponse.class);

        Booking putResponse = BookingApi.putBooking(newPayload,
                createdBookingResponse.getBookingid(),
                authResponse.getToken()).as(Booking.class);

        assertNotEquals(createdBookingResponse.getBooking(), putResponse, "Expected and Actual booking are not equal");

        Booking responseGetBookingBuId = BookingApi.getBooking(bookingId, "application/json").as(Booking.class);
        assertEquals(newPayload, responseGetBookingBuId, "Received booking is not as expected");
    }
}
