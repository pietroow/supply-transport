package br.com.cadmus.supply_transport.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class UberOnRailsDTO {

    //    @Size(8 lenght)
    private String trip_number;

    //    @Size(3 lenght)
    private String origin;

    //    @Size(3 lenght)
    private String destiny;

    private LocalDate departureDate;

    private LocalTime departure;

    private LocalTime arrival;

    private BigDecimal value;

}
