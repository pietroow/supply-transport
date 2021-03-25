package br.com.cadmus.supply_transport.general;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final CustomService customService;

    /**
     * Objective A:
     * <p>
     * Provide 1 (one) endpoint that receives Origin station, Destiny station and optionally Trip date criterias and return a
     * JSON with all the available routes (composed by one or more trips) of the two companies (iTrain and UberOnRails) matching
     * the search criteria, ordened by date and arrival time.
     * Trip connections can be used as long as the interval between the trips are equal or less than 12 hours.
     * A trip from iTrain can connect with a trip from UberOnRails, there is no problem with that.
     * The max of trip connections is up to you.
     */
    @ApiOperation("Get values of objective A")
    @GetMapping("/objectiveA")
    public String objectiveA(@RequestParam String originStation,
                             @RequestParam String destinyStation,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tripDate) {
        return customService.doObjectiveA(originStation, destinyStation, tripDate);
    }

    /**
     * Objective B:
     * <p>
     * Provide 1 (one) endpoint that returns the list of all the train stations that completely or partialy match a Station name criteria.
     * The criteria should not be required to use the endpoint.
     * The criteira matching should not be case sensitive.
     */
    @ApiOperation("Get values of objective B")
    @GetMapping("/objectiveB")
    public String objectiveB(@RequestParam(required = false, defaultValue = "") String stationName) {
        return customService.doObjectiveB(stationName);
    }

}
