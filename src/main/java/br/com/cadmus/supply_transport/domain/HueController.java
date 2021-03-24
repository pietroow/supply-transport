package br.com.cadmus.supply_transport.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/check")
public class HueController {

    /**
     * Objective A:
     *
     * Provide 1 (one) endpoint that receives Origin station, Destiny station and optionally Trip date criterias and return a JSON with all the available routes (composed by one or more trips) of the two companies (iTrain and UberOnRails) matching the search criteria, ordened by date and arrival time.
     * Trip connections can be used as long as the interval between the trips are equal or less than 12 hours.
     * A trip from iTrain can connect with a trip from UberOnRails, there is no problem with that.
     * The max of trip connections is up to you.
    *
    * */
    @GetMapping
    public String objectiveA(@RequestParam String originStation,
                             @RequestParam String destinyStation,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tripDate) {
        return "xisde";
    }

    /**
     * Objective B:
     *
     * Provide 1 (one) endpoint that returns the list of all the train stations that completely or partialy match a Station name criteria.
     * The criteria should not be required to use the endpoint.
     * The criteira matching should not be case sensitive.
     *
     * */
    @GetMapping
    public String objectiveB(@RequestParam(required = false) String stationName) {
        return "xisde";
    }

}
