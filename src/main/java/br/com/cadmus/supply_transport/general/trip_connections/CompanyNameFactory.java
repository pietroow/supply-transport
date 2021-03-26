package br.com.cadmus.supply_transport.general.trip_connections;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import br.com.cadmus.supply_transport.domains.companies.itrain.ITrain;
import br.com.cadmus.supply_transport.domains.companies.uber_on_rails.UberOnRails;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyNameFactory {

    public static String getFactory(AbstractTripInformation trip) {
        if (trip instanceof ITrain) {
            return "iTrain";
        } else if (trip instanceof UberOnRails) {
            return "UberOnRails";
        }
        throw new NotImplementedException();
    }

}
