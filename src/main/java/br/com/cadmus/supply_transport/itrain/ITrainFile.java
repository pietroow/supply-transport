package br.com.cadmus.supply_transport.itrain;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ITrainFile {

    public static final String CSV_DATE_PATTERN = "yyyy/MM/dd";

    @CsvBindByName(column = "trip_number")
    private String tripNumber;

    @CsvBindByName(column = "origin_station")
    private String originStation;

    @CsvBindByName(column = "destiny_station")
    private String destinyStation;

    @CsvBindByName(column = "date")
    private String date;

    @CsvBindByName(column = "departure_time")
    private String departureTime;

    @CsvBindByName(column = "arrival_time")
    private String arrivalTime;

    @CsvBindByName(column = "price")
    private BigDecimal price;

}
