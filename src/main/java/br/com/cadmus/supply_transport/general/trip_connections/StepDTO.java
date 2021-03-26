package br.com.cadmus.supply_transport.general.trip_connections;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class StepDTO {

    private final String origin;
    private final String destiny;
    private final LocalDateTime departure;
    private final LocalDateTime arrival;
    private final String company;
    private final BigDecimal price;

    public StepDTO(AbstractTripInformation trip) {
        this.origin = trip.getOriginStation();
        this.destiny = trip.getDestinyStation();
        this.departure = LocalDateTime.of(trip.getDepartureDate(), trip.getDepartureTime());
        this.arrival = LocalDateTime.of(trip.getDepartureDate(), trip.getArrivalTime());
        this.price = trip.getPrice();
        this.company = CompanyNameFactory.getFactory(trip);
    }

}
