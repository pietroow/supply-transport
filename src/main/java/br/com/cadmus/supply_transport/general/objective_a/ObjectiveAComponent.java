package br.com.cadmus.supply_transport.general.objective_a;

import br.com.cadmus.supply_transport.domains.companies.Generic;
import br.com.cadmus.supply_transport.domains.companies.GenericDisassembler;
import br.com.cadmus.supply_transport.domains.companies.itrain.ITrain;
import br.com.cadmus.supply_transport.domains.companies.itrain.ITrainComponent;
import br.com.cadmus.supply_transport.domains.companies.uber_on_rails.UberOnRails;
import br.com.cadmus.supply_transport.domains.companies.uber_on_rails.UberOnRailsComponent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ObjectiveAComponent {

    private final UberOnRailsComponent uberOnRailsComponent;
    private final ITrainComponent iTrainComponent;

    public List<TripDTO> resolve(TripInformation tripInformation) {
        List<UberOnRails> uberOnRailsList = uberOnRailsComponent.getList();
        List<ITrain> iTrainList = iTrainComponent.getList();

        List<Generic> allTripsFound = concatLists(uberOnRailsList, iTrainList);
        List<Generic> tripsFiltered = filterTrips(tripInformation, allTripsFound);

        return tripsFiltered.stream()
                .map(trip -> new TripDTO(tripInformation, trip))
                .sorted(Comparator.comparing(TripDTO::getDeparture))
                .sorted(Comparator.comparing(TripDTO::getArrival))
                .collect(Collectors.toList());
    }

    private List<Generic> concatLists(List<UberOnRails> uberOnRailsList, List<ITrain> iTrainList) {
        List<Generic> genericItrain = GenericDisassembler.mapFromITrain(iTrainList);
        List<Generic> genericUberOnRails = GenericDisassembler.mapFromUberOnRails(uberOnRailsList);
        return Stream.concat(genericItrain.stream(), genericUberOnRails.stream())
                .collect(Collectors.toList());
    }

    private List<Generic> filterTrips(TripInformation tripInformation, List<Generic> allTripsFound) {
        LocalDate tripDate = tripInformation.getTripDate();
        return allTripsFound.stream()
                .filter(uberOnRails -> StringUtils.containsIgnoreCase(tripInformation.getOriginStation(), uberOnRails.getOriginStation()))
                .filter(uberOnRails -> StringUtils.containsIgnoreCase(tripInformation.getDestinyStation(), uberOnRails.getDestinyStation()))
                .filter(uberOnRails -> isNull(tripDate) || uberOnRails.getDepartureDate().equals(tripDate))
                .collect(Collectors.toList());
    }

}
