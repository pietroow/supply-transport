package br.com.cadmus.supply_transport.general.trip_connections;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
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

    public TripDTO(TripInformationParams tripInformationParams, List<AbstractTripInformation> trips) {
        this.origin = tripInformationParams.getOriginStation();
        this.destiny = tripInformationParams.getDestinyStation();
        this.steps = mapToSteps(trips);
        this.departure = getDepartureValue();
        this.arrival = getArrivalValue();
    }

    private List<StepDTO> mapToSteps(List<AbstractTripInformation> trips) {
        return trips.stream()
                .map(StepDTO::new)
                .collect(Collectors.toList());
    }

    private LocalDateTime getDepartureValue() {
        return steps.stream()
                .map(StepDTO::getDeparture)
                .min(LocalDateTime::compareTo)
                .orElseThrow();
    }

    private LocalDateTime getArrivalValue() {
        return steps.stream()
                .map(StepDTO::getArrival)
                .max(LocalDateTime::compareTo)
                .orElseThrow();
    }

}
