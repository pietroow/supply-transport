package br.com.cadmus.supply_transport.general;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomService {

    private final ObjectiveAComponent objectiveAComponent;
    private final ObjectiveBComponent objectiveBComponent;

    public String doObjectiveA(String originStation, String destinyStation, LocalDate tripDate) {
        return objectiveAComponent.resolve(originStation, destinyStation, tripDate);
    }

    public String doObjectiveB(String stationName) {
        return objectiveBComponent.resolve(stationName);
    }

}
