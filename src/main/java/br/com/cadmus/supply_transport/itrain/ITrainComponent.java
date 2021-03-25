package br.com.cadmus.supply_transport.itrain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ITrainComponent {

    public List<ITrain> getList() {
        List<ITrainFile> iTrainFileList = ITrainConverter.getList();
        return iTrainFileList.stream()
                .map(ITrain::new)
                .collect(Collectors.toList());
    }

}
