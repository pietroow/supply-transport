package br.com.cadmus.supply_transport.general.trip_connections;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TripInformationParams {

    private final String originStation;
    private final String destinyStation;
    private final LocalDate tripDate;

    public TripInformationParams(String originStation, String destinyStation, LocalDate tripDate) {
        this.originStation = originStation;
        this.destinyStation = destinyStation;
        this.tripDate = tripDate;
    }

}
