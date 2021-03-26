package br.com.cadmus.supply_transport.domains.companies.uber_on_rails;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
class UberOnRailsFile {

    private String trip;
    private String origin;
    private String destiny;
    private String departureDate;
    private String departure;
    private String arrival;
    private BigDecimal value;

}
