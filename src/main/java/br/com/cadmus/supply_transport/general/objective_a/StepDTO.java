package br.com.cadmus.supply_transport.general.objective_a;

import br.com.cadmus.supply_transport.domains.companies.Generic;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class StepDTO {

    private String origin;
    private String destiny;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private String company;
    private BigDecimal price;

    public StepDTO(Generic trip) {
        this.origin = trip.getOriginStation();
        this.destiny = trip.getDestinyStation();
        this.departure = LocalDateTime.of(trip.getDepartureDate(), trip.getDepartureTime());
        this.arrival = LocalDateTime.of(trip.getDepartureDate(), trip.getArrivalTime());
        this.price = trip.getPrice();
        this.company = CompanyNameFactory.getFactory(trip);
    }

}
