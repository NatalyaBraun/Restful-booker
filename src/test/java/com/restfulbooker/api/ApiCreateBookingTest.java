package com.restfulbooker.api;

import com.restfulbooker.api.api.BookingApi;
import com.restfulbooker.api.payloads.BookingResponse;
import com.restfulbooker.api.payloads.lombok.Booking;
import com.restfulbooker.api.payloads.lombok.BookingDates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiCreateBookingTest {

    @Test
    public void postBookingAndGetBooking(){
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
        System.out.println(createdBookingResponse.getBooking().toString());
        int bookingId = createdBookingResponse.getBookingid();

        Booking responseGetBookingBuId = BookingApi.getBooking(bookingId, "application/json").as(Booking.class);
        assertEquals(payload, responseGetBookingBuId, "Received booking is not as expected");
    }
}
