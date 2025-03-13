package com.restfulbooker.api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restfulbooker.api.payloads.lombok.Booking;
import lombok.Getter;

@Getter
public class BookingResponse {

    @JsonProperty
    private int bookingid;
    @JsonProperty
    private Booking booking;

}
