package br.com.cadmus.supply_transport.domains.companies.uber_on_rails;

import br.com.cadmus.supply_transport.domains.companies.Generic;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
public class UberOnRails extends Generic {

    public UberOnRails(UberOnRailsFile uberOnRailsFile) {
        this.tripNumber = uberOnRailsFile.getTrip();
        this.originStation = uberOnRailsFile.getOrigin();
        this.destinyStation = uberOnRailsFile.getDestiny();
        this.departureDate = LocalDate.parse(uberOnRailsFile.getDepartureDate());
        this.departureTime = LocalTime.parse(uberOnRailsFile.getDeparture());
        this.arrivalTime = LocalTime.parse(uberOnRailsFile.getArrival());
        this.price = uberOnRailsFile.getValue();
    }

    @Override
    public String toString() {
        return "UberOnRails{" +
                "tripNumber='" + tripNumber + '\'' +
                ", originStation='" + originStation + '\'' +
                ", destinyStation='" + destinyStation + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", price=" + price +
                '}';
    }

}
