package br.com.cadmus.supply_transport.general.objective_a;

import br.com.cadmus.supply_transport.domains.companies.Generic;
import br.com.cadmus.supply_transport.domains.companies.itrain.ITrain;
import br.com.cadmus.supply_transport.domains.companies.uber_on_rails.UberOnRails;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class StepDTO {

    protected String origin;
    protected String destiny;
    protected LocalDateTime departure;
    protected LocalDateTime arrival;
    protected String company;
    protected BigDecimal price;

    public StepDTO(Generic o) {
        this.origin = o.getOriginStation();
        this.destiny = o.getDestinyStation();
        this.departure = LocalDateTime.of(o.getDepartureDate(), o.getDepartureTime());
        this.arrival = LocalDateTime.of(o.getDepartureDate(), o.getArrivalTime());
        this.price = o.getPrice();
        this.company = getCompanyInstance(o);
    }

    private String getCompanyInstance(Generic o) {
        if (o instanceof ITrain) {
            return "iTrain";
        }

        if (o instanceof UberOnRails) {
            return "UberOnRails";
        }
        throw new IllegalStateException();
    }

}
