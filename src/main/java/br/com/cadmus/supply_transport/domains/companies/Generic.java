package br.com.cadmus.supply_transport.domains.companies;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
public abstract class Generic {

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
        Generic generic = (Generic) o;
        return Objects.equals(tripNumber, generic.tripNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripNumber);
    }

}
