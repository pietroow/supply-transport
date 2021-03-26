package br.com.cadmus.supply_transport.general;

import br.com.cadmus.supply_transport.domains.train_stations.TrainStation;
import br.com.cadmus.supply_transport.general.objective_a.FinalObject;
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

    /**
     * Objective A:
     * <p>
     * Provide 1 (one) endpoint that receives Origin station, Destiny station and optionally Trip date criterias and return a
     * JSON with all the available routes (composed by one or more trips) of the two companies (iTrain and UberOnRails) matching
     * the search criteria, ordened by date and arrival time.
     * Trip connections can be used as long as the interval between the trips are equal or less than 12 hours.
     * A trip from iTrain can connect with a trip from UberOnRails, there is no problem with that.
     * The max of trip connections is up to you.
     * @return
     */
    @ApiOperation("Get values of objective A")
    @GetMapping("/objectiveA")
    public FinalObject objectiveA(@RequestParam String originStation,
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
