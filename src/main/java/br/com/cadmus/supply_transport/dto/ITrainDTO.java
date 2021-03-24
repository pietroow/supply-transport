package br.com.cadmus.supply_transport.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ITrainDTO {

//    @Size(6 lenght)
    private String trip_number;

//    @Size(3 lenght)
    private String origin_station;

//    @Size(3 lenght)
    private String destiny_station;

    private LocalDate date;

    private LocalTime departure_time;

    private LocalTime arrival_time;

    private BigDecimal price;

}
