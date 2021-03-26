package br.com.cadmus.supply_transport.domains.train_stations;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainStationsFacadeImpl implements TrainStationsFacade {

    private List<TrainStation> trainStations;

    @PostConstruct
    private void loadFile() {
        this.trainStations = TrainStationConverter.getList();
    }

    @Override
    public List<TrainStation> findByNameIgnoringCase(String name) {
        return trainStations.stream()
                .filter(station -> StringUtils.containsIgnoreCase(station.getName(), name))
                .sorted()
                .collect(Collectors.toList());
    }

}
