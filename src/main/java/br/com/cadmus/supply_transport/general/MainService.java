package br.com.cadmus.supply_transport.general;

import br.com.cadmus.supply_transport.domains.train_stations.TrainStation;
import br.com.cadmus.supply_transport.domains.train_stations.TrainStationsFacade;
import br.com.cadmus.supply_transport.general.trip_connections.TripDTO;
import br.com.cadmus.supply_transport.general.trip_connections.TripConnectionComponent;
import br.com.cadmus.supply_transport.general.trip_connections.TripInformationParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final TripConnectionComponent tripConnectionComponent;
    private final TrainStationsFacade trainStationsFacade;

    public List<TripDTO> getTrips(TripInformationParams tripInformationParams) {
        return tripConnectionComponent.resolve(tripInformationParams);
    }

    public List<TrainStation> findByName(String stationName) {
        return trainStationsFacade.findByNameIgnoringCase(stationName);
    }

}
