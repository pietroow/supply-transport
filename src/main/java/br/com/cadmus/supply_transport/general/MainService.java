package br.com.cadmus.supply_transport.general;

import br.com.cadmus.supply_transport.domains.train_stations.TrainStation;
import br.com.cadmus.supply_transport.domains.train_stations.TrainStationsFacade;
import br.com.cadmus.supply_transport.general.objective_a.FinalObject;
import br.com.cadmus.supply_transport.general.objective_a.ObjectiveAComponent;
import br.com.cadmus.supply_transport.general.objective_a.TripInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final ObjectiveAComponent objectiveAComponent;
    private final TrainStationsFacade trainStationsFacade;

    public FinalObject doObjectiveA(TripInformation tripInformation) {
        return objectiveAComponent.resolve(tripInformation);
    }

    public List<TrainStation> findByName(String stationName) {
        return trainStationsFacade.findByNameIgnoringCase(stationName);
    }

}
