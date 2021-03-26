package br.com.cadmus.supply_transport.general;

import br.com.cadmus.supply_transport.domains.train_stations.TrainStation;
import br.com.cadmus.supply_transport.general.objective_a.TripDTO;
import br.com.cadmus.supply_transport.general.objective_a.TripInformation;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @ApiOperation("Get values of objective A")
    @GetMapping("/objectiveA")
    public List<TripDTO> objectiveA(@RequestParam String originStation,
                                    @RequestParam String destinyStation,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tripDate) {
        TripInformation tripInformation = new TripInformation(originStation, destinyStation, tripDate);
        return mainService.doObjectiveA(tripInformation);
    }

    @ApiOperation("Returns the list of all the train stations that completely or partialy match a station name criteria")
    @GetMapping("/stations/name")
    public List<TrainStation> findByName(@RequestParam(required = false, defaultValue = "") String stationName) {
        return mainService.findByName(stationName);
    }

}
