package br.com.cadmus.supply_transport.domains.companies.itrain;

import br.com.cadmus.supply_transport.domains.companies.Generic;
import br.com.cadmus.supply_transport.util.DateUtil;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class ITrain extends Generic {

    public ITrain(ITrainFile iTrainFile) {
        this.tripNumber = iTrainFile.getTripNumber();
        this.originStation = iTrainFile.getOriginStation();
        this.destinyStation = iTrainFile.getDestinyStation();
        this.departureDate = DateUtil.convertFromPattern(iTrainFile.getDate(), ITrainFile.CSV_DATE_PATTERN);
        this.departureTime = LocalTime.parse(iTrainFile.getDepartureTime());
        this.arrivalTime = LocalTime.parse(iTrainFile.getArrivalTime());
        this.price = iTrainFile.getPrice();
    }

    @Override
    public String toString() {
        return "ITrain{" +
                "tripNumber='" + tripNumber + '\'' +
                ", originStation='" + originStation + '\'' +
                ", destinyStation='" + destinyStation + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", price=" + price +
                '}';
    }

}
