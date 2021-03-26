package br.com.cadmus.supply_transport.domains.train_stations;

import lombok.Getter;

import java.util.Objects;

@Getter
public class TrainStation implements Comparable<TrainStation> {

    private String name;
    private String station;
    private String city;

    @Override
    public int compareTo(TrainStation trainStation) {
        return this.name.compareTo(trainStation.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainStation that = (TrainStation) o;
        return Objects.equals(name, that.name) && Objects.equals(station, that.station) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, station, city);
    }

}
