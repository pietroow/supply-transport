package br.com.cadmus.supply_transport.domains.companies.uber_on_rails;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UberOnRailsComponent {

    public List<UberOnRails> getList() {
        List<UberOnRailsFile> uberOnRailsFileList = UberOnRailsConverter.getList();
        return uberOnRailsFileList.stream()
                .map(UberOnRails::new)
                .collect(Collectors.toList());
    }

}
