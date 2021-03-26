package br.com.cadmus.supply_transport.general.objective_a;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TripInformation {

    private final String originStation;
    private final String destinyStation;
    private final LocalDate tripDate;

    public TripInformation(String originStation, String destinyStation, LocalDate tripDate) {
        this.originStation = originStation;
        this.destinyStation = destinyStation;
        this.tripDate = tripDate;
    }

}
