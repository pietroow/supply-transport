package br.com.cadmus.supply_transport.domains.companies.itrain;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import br.com.cadmus.supply_transport.util.DateUtil;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@ToString(callSuper = true)
public class ITrain extends AbstractTripInformation {

    public ITrain(ITrainFile iTrainFile) {
        this.tripNumber = iTrainFile.getTripNumber();
        this.originStation = iTrainFile.getOriginStation();
        this.destinyStation = iTrainFile.getDestinyStation();
        this.departureDate = DateUtil.convertFromPattern(iTrainFile.getDate(), ITrainFile.CSV_DATE_PATTERN);
        this.departureTime = LocalTime.parse(iTrainFile.getDepartureTime());
        this.arrivalTime = LocalTime.parse(iTrainFile.getArrivalTime());
        this.price = iTrainFile.getPrice();
    }

}
