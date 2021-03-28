package br.com.cadmus.supply_transport.domains.train_stations;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TrainStation implements Comparable<TrainStation> {

    private String name;
    private String station;
    private String city;

    @Override
    public int compareTo(TrainStation trainStation) {
        return this.name.compareTo(trainStation.name);
    }

}
