package br.com.cadmus.supply_transport.domains.companies;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
public abstract class AbstractTripInformation {

    protected String tripNumber;
    protected String originStation;
    protected String destinyStation;
    protected LocalDate departureDate;
    protected LocalTime departureTime;
    protected LocalTime arrivalTime;
    protected BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTripInformation that = (AbstractTripInformation) o;
        return Objects.equals(tripNumber, that.tripNumber) &&
                Objects.equals(originStation, that.originStation) &&
                Objects.equals(destinyStation, that.destinyStation) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(departureTime, that.departureTime) &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripNumber, originStation, destinyStation, departureDate, departureTime, arrivalTime, price);
    }

}
