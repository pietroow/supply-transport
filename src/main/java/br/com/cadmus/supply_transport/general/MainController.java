package br.com.cadmus.supply_transport.general;

import br.com.cadmus.supply_transport.domains.train_stations.TrainStation;
import br.com.cadmus.supply_transport.general.trip_connections.TripDTO;
import br.com.cadmus.supply_transport.general.trip_connections.TripInformationParams;
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

    @GetMapping("/trips")
    @ApiOperation("Get trips available from origin station to destiny station")
    public List<TripDTO> getTrips(@RequestParam String originStation,
                                  @RequestParam String destinyStation,
                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tripDate) {
        TripInformationParams tripInformationParams = new TripInformationParams(originStation, destinyStation, tripDate);
        return mainService.getTrips(tripInformationParams);
    }

    @GetMapping("/stations/name")
    @ApiOperation("Returns the list of all the train stations that completely or partialy match a station name criteria")
    public List<TrainStation> findByName(@RequestParam(required = false, defaultValue = "") String stationName) {
        return mainService.findByName(stationName);
    }

}
