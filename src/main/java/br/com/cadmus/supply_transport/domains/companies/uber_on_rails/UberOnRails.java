package br.com.cadmus.supply_transport.domains.companies.uber_on_rails;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString(callSuper = true)
public class UberOnRails extends AbstractTripInformation {

    public UberOnRails(UberOnRailsFile uberOnRailsFile) {
        this.tripNumber = uberOnRailsFile.getTrip();
        this.originStation = uberOnRailsFile.getOrigin();
        this.destinyStation = uberOnRailsFile.getDestiny();
        this.departureDate = LocalDate.parse(uberOnRailsFile.getDepartureDate());
        this.departureTime = LocalTime.parse(uberOnRailsFile.getDeparture());
        this.arrivalTime = LocalTime.parse(uberOnRailsFile.getArrival());
        this.price = uberOnRailsFile.getValue();
    }

}
