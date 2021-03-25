package br.com.cadmus.supply_transport.uber_on_rails;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
public class UberOnRails {

    private final String trip;
    private final String origin;
    private final String destiny;
    private final LocalDate departureDate;
    private final LocalTime departure;
    private final LocalTime arrival;
    private final BigDecimal value;

    public UberOnRails(UberOnRailsFile uberOnRailsFile) {
        this.trip = uberOnRailsFile.getTrip();
        this.origin = uberOnRailsFile.getOrigin();
        this.destiny = uberOnRailsFile.getDestiny();
        this.departureDate = LocalDate.parse(uberOnRailsFile.getDepartureDate());
        this.departure = LocalTime.parse(uberOnRailsFile.getDeparture());
        this.arrival = LocalTime.parse(uberOnRailsFile.getArrival());
        this.value = uberOnRailsFile.getValue();
    }

}
