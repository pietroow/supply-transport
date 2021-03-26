package br.com.cadmus.supply_transport.domains.companies;

import br.com.cadmus.supply_transport.domains.companies.itrain.ITrain;
import br.com.cadmus.supply_transport.domains.companies.uber_on_rails.UberOnRails;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericDisassembler {

    public static List<Generic> mapFromUberOnRails(List<UberOnRails> uberOnRailsList) {
        return uberOnRailsList.stream()
                .map(Generic.class::cast)
                .collect(Collectors.toList());
    }

    public static List<Generic> mapFromITrain(List<ITrain> iTrainList) {
        return iTrainList.stream()
                .map(Generic.class::cast)
                .collect(Collectors.toList());
    }

}
