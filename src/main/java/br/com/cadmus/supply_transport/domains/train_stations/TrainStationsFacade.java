package br.com.cadmus.supply_transport.domains.train_stations;

import java.util.List;

public interface TrainStationsFacade {

    List<TrainStation> findByNameIgnoringCase(String name);

}
