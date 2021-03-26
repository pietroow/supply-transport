package br.com.cadmus.supply_transport.general.objective_a;

import br.com.cadmus.supply_transport.domains.companies.Generic;
import br.com.cadmus.supply_transport.domains.companies.itrain.ITrain;
import br.com.cadmus.supply_transport.domains.companies.uber_on_rails.UberOnRails;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyNameFactory {

    public static String getFactory(Generic trip) {
        if (trip instanceof ITrain) {
            return "iTrain";
        }
        if (trip instanceof UberOnRails) {
            return "UberOnRails";
        }
        throw new IllegalStateException();
    }

}
