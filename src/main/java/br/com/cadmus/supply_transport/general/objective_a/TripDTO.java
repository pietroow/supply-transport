package br.com.cadmus.supply_transport.general.objective_a;

import br.com.cadmus.supply_transport.domains.companies.Generic;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TripDTO {

    private final String origin;
    private final String destiny;
    private final LocalDateTime departure;
    private final LocalDateTime arrival;
    private final List<StepDTO> steps;

    public TripDTO(TripInformation tripInformation, Generic trip) {
        this.origin = tripInformation.getOriginStation();
        this.destiny = tripInformation.getDestinyStation();
        this.steps = mapToStep(List.of(trip));
        this.departure = getDepartureValue();
        this.arrival = getArrivalValue();
    }

    private List<StepDTO> mapToStep(List<Generic> trips) {
        return trips.stream()
                .map(StepDTO::new)
                .collect(Collectors.toList());
    }

    private LocalDateTime getDepartureValue() {
        return steps.stream()
                .map(StepDTO::getDeparture)
                .min(LocalDateTime::compareTo)
                .orElse(null);
    }

    private LocalDateTime getArrivalValue() {
        return steps.stream()
                .map(StepDTO::getArrival)
                .min(LocalDateTime::compareTo)
                .orElse(null);
    }

}
