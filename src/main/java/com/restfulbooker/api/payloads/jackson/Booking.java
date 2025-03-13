package com.restfulbooker.api.payloads.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Booking {

    @JsonProperty
    private String firstname;
    @JsonProperty
    private String lastname;
    @JsonProperty
    private int totalprice;
    @JsonProperty
    private boolean depositpaid;
    @JsonProperty
    private BookingDates bookingdates;
    @JsonProperty
    private String additionalneeds;

    // default constructor required by Jackson
    private Booking() {
        // nothing here
    }

    private Booking(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public static class Builder {

        private String firstname;
        private String lastname;
        private int totalprice;
        private boolean depositpaid;
        private BookingDates bookingdates;
        private String additionalneeds;

        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setTotalprice(int totalprice) {
            this.totalprice = totalprice;
            return this;
        }

        public Builder setDepositpaid(boolean depositpaid) {
            this.depositpaid = depositpaid;
            return this;
        }

        public Builder setBookingdates(BookingDates bookingdates) {
            this.bookingdates = bookingdates;
            return this;
        }

        public Builder setAdditionalneeds(String additionalneeds) {
            this.additionalneeds = additionalneeds;
            return this;
        }

        public Booking build(){
            return new Booking(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return totalprice == booking.totalprice && depositpaid == booking.depositpaid && Objects.equals(firstname, booking.firstname) && Objects.equals(lastname, booking.lastname) && Objects.equals(additionalneeds, booking.additionalneeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, totalprice, depositpaid, additionalneeds);
    }

    @Override
    public String toString() {
        return "Booking { " + "\n" +
                "firstname = " + firstname + "\n" +
                "lastname = " + lastname + "\n" +
                "totalprice = " + totalprice + "\n" +
                "depositpaid = " + depositpaid + "\n" +
                "bookingdates = " + bookingdates + "\n" +
                "additionalneeds = " + additionalneeds + "\n" +
                "}";
    }
}
