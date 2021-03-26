package br.com.cadmus.supply_transport.domains.companies.uber_on_rails;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UberOnRailsComponent {

    public List<AbstractTripInformation> getList() {
        List<UberOnRailsFile> uberOnRailsFileList = UberOnRailsConverter.getList();
        return uberOnRailsFileList.stream()
                .map(UberOnRails::new)
                .map(AbstractTripInformation.class::cast)
                .collect(Collectors.toList());
    }

}
