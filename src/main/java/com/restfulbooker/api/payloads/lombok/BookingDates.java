package com.restfulbooker.api.payloads.lombok;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDates {

    private Date checkin = new Date();
    private Date checkout = new Date();

}
