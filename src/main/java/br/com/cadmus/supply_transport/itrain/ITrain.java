package br.com.cadmus.supply_transport.itrain;

import br.com.cadmus.supply_transport.util.DateUtil;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
public class ITrain {

    private final String tripNumber;
    private final String originStation;
    private final String destinyStation;
    private final LocalDate date;
    private final LocalTime departureTime;
    private final LocalTime arrivalTime;
    private final BigDecimal price;

    public ITrain(ITrainFile iTrainFile) {
        this.tripNumber = iTrainFile.getTripNumber();
        this.originStation = iTrainFile.getOriginStation();
        this.destinyStation = iTrainFile.getDestinyStation();
        this.date = DateUtil.convertFromPattern(iTrainFile.getDate(), ITrainFile.CSV_DATE_PATTERN);
        this.departureTime = LocalTime.parse(iTrainFile.getDepartureTime());
        this.arrivalTime = LocalTime.parse(iTrainFile.getArrivalTime());
        this.price = iTrainFile.getPrice();
    }

}
