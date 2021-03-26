package br.com.cadmus.supply_transport.general.objective_a;

import br.com.cadmus.supply_transport.domains.companies.Generic;
import br.com.cadmus.supply_transport.domains.companies.uber_on_rails.UberOnRails;
import br.com.cadmus.supply_transport.domains.companies.GenericDisassembler;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FinalObject {

    private String origin;
    private String destiny;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private List<StepDTO> steps;

    public FinalObject(TripInformation tripInformation) {
        this.origin = tripInformation.getOriginStation();
        this.destiny = tripInformation.getDestinyStation();
//        this.steps = mapToStep(uberOnRailsListFiltered);
        this.departure = getDepartureValue();
        this.arrival = getArrivalValue();
    }

    private LocalDateTime getArrivalValue() {
        return steps.stream()
                .map(StepDTO::getArrival)
                .min(LocalDateTime::compareTo)
                .orElse(null);
    }

    private LocalDateTime getDepartureValue() {
        return steps.stream()
                .map(StepDTO::getDeparture)
                .min(LocalDateTime::compareTo)
                .orElse(null);
    }

    private List<StepDTO> mapToStep(List<UberOnRails> uberOnRailsListFiltered) {
        List<Generic> generics = convertHere(uberOnRailsListFiltered);
        return generics.stream()
                .map(StepDTO::new)
                .collect(Collectors.toList());
    }

    private List<Generic> convertHere(List<UberOnRails> uberOnRailsListFiltered) {
//        List<Generic> iTrain = GenericDisassembler.mapFromItrain(iTrainFiltered);
        return GenericDisassembler.mapFromUberOnRails(uberOnRailsListFiltered);
//        return ListUtils.concatenarListas(iTrain, uberOnRails);

    }

}
