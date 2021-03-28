package br.com.cadmus.supply_transport.general.trip_connections;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import br.com.cadmus.supply_transport.domains.companies.TripsFacade;
import br.com.cadmus.supply_transport.util.DateUtil;
import br.com.cadmus.supply_transport.util.ListUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TripConnectionComponent {

    private final TripsFacade tripsFacade;

    public List<TripDTO> resolve(TripInformationParams tripInformationParams) {
        List<AbstractTripInformation> tripsWithSameDestiny = tripsFacade.findByDestinyStationIgnoreCase(tripInformationParams.getDestinyStation());
        List<AbstractTripInformation> tripsWithSameOrigin = tripsFacade.findByOriginStationIgnoreCaseAndDepartureDate(tripInformationParams.getOriginStation(), tripInformationParams.getTripDate());

        List<TripDTO> singleTrips = getSingleTrip(tripInformationParams, tripsWithSameOrigin);
        List<TripDTO> tripsWithConnection = getTripsWithConnection(tripInformationParams, tripsWithSameOrigin, tripsWithSameDestiny);
        List<TripDTO> allTrips = ListUtils.concatLists(singleTrips, tripsWithConnection);

        return allTrips.stream()
                .sorted(Comparator.comparing(TripDTO::getDeparture))
                .sorted(Comparator.comparing(TripDTO::getArrival))
                .collect(Collectors.toList());
    }

    private List<TripDTO> getSingleTrip(TripInformationParams tripInformationParams, List<AbstractTripInformation> origins) {
        return origins.stream()
                .filter(origin -> isSameOriginAndDestiny(tripInformationParams, origin))
                .map(origin -> new TripDTO(tripInformationParams, List.of(origin)))
                .collect(Collectors.toList());
    }

    private boolean isSameOriginAndDestiny(TripInformationParams tripInformationParams, AbstractTripInformation origem) {
        return StringUtils.equalsIgnoreCase(tripInformationParams.getOriginStation(), origem.getOriginStation()) &&
                StringUtils.equalsIgnoreCase(tripInformationParams.getDestinyStation(), origem.getDestinyStation());
    }

    private List<TripDTO> getTripsWithConnection(TripInformationParams tripInformationParams, List<AbstractTripInformation> origins, List<AbstractTripInformation> destiny) {
        return origins.stream()
                .map(origin -> mapToTripsWithConnections(destiny, origin))
                .filter(routes -> !routes.isEmpty())
                .map(trips -> new TripDTO(tripInformationParams, trips))
                .collect(Collectors.toList());
    }

    private List<AbstractTripInformation> mapToTripsWithConnections(List<AbstractTripInformation> destiny, AbstractTripInformation origin) {
        List<AbstractTripInformation> trips = new ArrayList<>();
        List<AbstractTripInformation> conexoes = destiny.stream()
                .filter(secondConnection -> verifyConditions(origin, secondConnection))
                .collect(Collectors.toList());
        if (!conexoes.isEmpty()) {
            trips.add(origin);
            trips.addAll(conexoes);
        }
        return trips;
    }

    private boolean verifyConditions(AbstractTripInformation tripOrigin, AbstractTripInformation secondConnection) {
        return tripOrigin.getDestinyStation().equals(secondConnection.getOriginStation()) &&
                DateUtil.checkMaximumHoursDifference(tripOrigin.getArrivalLocalDateTime(), secondConnection.getDepartureLocalDateTime()) &&
                DateUtil.isAfterOrEquals(secondConnection.getDepartureLocalDateTime(), tripOrigin.getArrivalLocalDateTime());
    }

}
