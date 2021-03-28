package br.com.cadmus.supply_transport.domains.companies.uber_on_rails;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UberOnRailsComponent {

    public static List<AbstractTripInformation> getList() {
        List<UberOnRailsFile> uberOnRailsFileList = UberOnRailsConverter.getList();
        return uberOnRailsFileList.stream()
                .map(UberOnRails::new)
                .map(AbstractTripInformation.class::cast)
                .collect(Collectors.toList());
    }

}
