package br.com.cadmus.supply_transport.domains.companies.itrain;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ITrainComponent {

    public static List<AbstractTripInformation> getList() {
        List<ITrainFile> iTrainFileList = ITrainConverter.getList();
        return iTrainFileList.stream()
                .map(ITrain::new)
                .map(AbstractTripInformation.class::cast)
                .collect(Collectors.toList());
    }

}
