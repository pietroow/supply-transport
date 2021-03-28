package br.com.cadmus.supply_transport.domains.companies;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public abstract class AbstractTripInformation {

    protected String tripNumber;
    protected String originStation;
    protected String destinyStation;
    protected LocalDate departureDate;
    protected LocalTime departureTime;
    protected LocalTime arrivalTime;
    protected BigDecimal price;

    public LocalDateTime getDepartureLocalDateTime() {
        return LocalDateTime.of(departureDate, departureTime);
    }

    public LocalDateTime getArrivalLocalDateTime() {
        return LocalDateTime.of(departureDate, arrivalTime);
    }

}
