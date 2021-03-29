package br.com.cadmus.supply_transport.domains.companies;

import br.com.cadmus.supply_transport.domains.companies.itrain.ITrainComponent;
import br.com.cadmus.supply_transport.domains.companies.uber_on_rails.UberOnRailsComponent;
import br.com.cadmus.supply_transport.util.DateUtil;
import br.com.cadmus.supply_transport.util.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
class TripsFacadeImpl implements TripsFacade {

    private List<AbstractTripInformation> trips;

    @PostConstruct
    private void loadFile() {
        List<AbstractTripInformation> uberOnRails = UberOnRailsComponent.getList();
        List<AbstractTripInformation> iTrain = ITrainComponent.getList();
        this.trips = ListUtils.concatLists(uberOnRails, iTrain);
    }

    @Override
    public List<AbstractTripInformation> findByDestinyStationIgnoreCase(String destinyStation) {
        return trips.stream()
                .filter(trip -> StringUtils.equalsIgnoreCase(destinyStation, trip.getDestinyStation()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractTripInformation> findByOriginStationIgnoreCaseAndDepartureDate(String originStation, LocalDate tripDate) {
        return trips.stream()
                .filter(trip -> StringUtils.equalsIgnoreCase(originStation, trip.getOriginStation()))
                .filter(trip -> isFilteringDate(tripDate, trip))
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractTripInformation> findNextTripAfterOrigin(AbstractTripInformation origin) {
        return trips.stream()
                .filter(trip -> StringUtils.equalsIgnoreCase(origin.getDestinyStation(), trip.getOriginStation()))
                .filter(trip -> DateUtil.isAfterOrEquals(trip.getDepartureLocalDateTime(), origin.getArrivalLocalDateTime()))
                .filter(trip -> DateUtil.checkMaximumHoursDifference(origin.getArrivalLocalDateTime(), trip.getDepartureLocalDateTime()))
                .collect(Collectors.toList());
    }

    private boolean isFilteringDate(LocalDate tripDate, AbstractTripInformation trip) {
        return isNull(tripDate) || trip.getDepartureDate().equals(tripDate);
    }

}
