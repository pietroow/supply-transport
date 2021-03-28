package br.com.cadmus.supply_transport.domains.companies;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;

import java.time.LocalDate;
import java.util.List;

public interface TripsFacade {

    List<AbstractTripInformation> findByDestinyStationIgnoreCase(String destinyStation);

    List<AbstractTripInformation> findByOriginStationIgnoreCaseAndDepartureDate(String originStation, LocalDate tripDate);

}
