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
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ObjectiveAComponent {

    private final UberOnRailsComponent uberOnRailsComponent;
    private final ITrainComponent iTrainComponent;

    public FinalObject resolve(TripInformation tripInformation) {

        LocalDate tripDate = tripInformation.getTripDate();
        String originStation = tripInformation.getOriginStation();
        String destinyStation = tripInformation.getDestinyStation();

        List<UberOnRails> uberOnRailsList = uberOnRailsComponent.getList();
        List<ITrain> iTrainList = iTrainComponent.getList();

        List<Generic> genericItrain = GenericDisassembler.mapFromItrain(iTrainList);
        List<Generic> genericUberOnRails = GenericDisassembler.mapFromUberOnRails(uberOnRailsList);

        List<Generic> allTripsFound = Stream.concat(genericItrain.stream(), genericUberOnRails.stream())
                .collect(Collectors.toList());

        List<Generic> uberOnRailsListFiltered = allTripsFound.stream()
                .filter(uberOnRails -> StringUtils.containsIgnoreCase(originStation, uberOnRails.getOriginStation()))
                .filter(uberOnRails -> StringUtils.containsIgnoreCase(destinyStation, uberOnRails.getDestinyStation()))
                .filter(uberOnRails -> isNull(tripDate) || uberOnRails.getDepartureDate().equals(tripDate))
                .collect(Collectors.toList());

//        allTripsFound.stream()
//                .map(o -> new FinalObject(tripInformation, o))

//        return new FinalObject(tripInformation, uberOnRailsListFiltered);
//
//        List<Generic> generics = new ArrayList<>();
//        generics.stream()
//                .sorted(Comparator.comparing(Generic::getDepartureDate))
//                .sorted(Comparator.comparing(Generic::getArrivalTime))
//                .collect(Collectors.toList());
        return null;
    }

}
