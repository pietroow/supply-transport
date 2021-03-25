package br.com.cadmus.supply_transport.general;

import br.com.cadmus.supply_transport.itrain.ITrain;
import br.com.cadmus.supply_transport.itrain.ITrainComponent;
import br.com.cadmus.supply_transport.uber_on_rails.UberOnRails;
import br.com.cadmus.supply_transport.uber_on_rails.UberOnRailsComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ObjectiveAComponent {

    private final UberOnRailsComponent uberOnRailsComponent;
    private final ITrainComponent iTrainComponent;

    public String resolve(String originStation, String destinyStation, LocalDate tripDate) {
        List<UberOnRails> uberOnRailsList = uberOnRailsComponent.getList();
        List<ITrain> iTrainList = iTrainComponent.getList();
        return String.format("uber on rails -> %d, iTrain -> %d", uberOnRailsList.size(), iTrainList.size());
    }

}
