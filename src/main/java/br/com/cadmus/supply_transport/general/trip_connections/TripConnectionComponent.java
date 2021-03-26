package br.com.cadmus.supply_transport.general.trip_connections;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import br.com.cadmus.supply_transport.domains.companies.itrain.ITrainComponent;
import br.com.cadmus.supply_transport.domains.companies.uber_on_rails.UberOnRailsComponent;
import br.com.cadmus.supply_transport.util.ListUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class TripConnectionComponent {

    private final UberOnRailsComponent uberOnRailsComponent;
    private final ITrainComponent iTrainComponent;

    public List<TripDTO> resolve(TripInformationParams tripInformationParams) {
        List<AbstractTripInformation> allTripsFound = getTrips();
        List<AbstractTripInformation> tripsFiltered = filterTrips(tripInformationParams, allTripsFound);

        return tripsFiltered.stream()
                .map(trip -> new TripDTO(tripInformationParams, trip))
                .sorted(Comparator.comparing(TripDTO::getDeparture))
                .sorted(Comparator.comparing(TripDTO::getArrival))
                .collect(Collectors.toList());
    }

    private List<AbstractTripInformation> getTrips() {
        List<AbstractTripInformation> uberOnRailsList = uberOnRailsComponent.getList();
        List<AbstractTripInformation> iTrainList = iTrainComponent.getList();
        return ListUtils.concatenarListas(uberOnRailsList, iTrainList);
    }

    private List<AbstractTripInformation> filterTrips(TripInformationParams tripInformationParams, List<AbstractTripInformation> allTripsFound) {
        LocalDate tripDate = tripInformationParams.getTripDate();
        return allTripsFound.stream()
                .filter(uberOnRails -> StringUtils.containsIgnoreCase(tripInformationParams.getOriginStation(), uberOnRails.getOriginStation()))
                .filter(uberOnRails -> StringUtils.containsIgnoreCase(tripInformationParams.getDestinyStation(), uberOnRails.getDestinyStation()))
                .filter(uberOnRails -> isNull(tripDate) || uberOnRails.getDepartureDate().equals(tripDate))
                .collect(Collectors.toList());
    }

}
