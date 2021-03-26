package br.com.cadmus.supply_transport.domains.companies.itrain;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ITrainComponent {

    public List<AbstractTripInformation> getList() {
        List<ITrainFile> iTrainFileList = ITrainConverter.getList();
        return iTrainFileList.stream()
                .map(ITrain::new)
                .map(AbstractTripInformation.class::cast)
                .collect(Collectors.toList());
    }

}
