package br.com.cadmus.supply_transport.general.trip_connections;

import br.com.cadmus.supply_transport.domains.companies.AbstractTripInformation;
import br.com.cadmus.supply_transport.domains.companies.TripsFacade;
import br.com.cadmus.supply_transport.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TripConnectionComponent {

    private final TripsFacade tripsFacade;

    public List<TripDTO> resolve(TripInformationParams tripInformationParams) {
        List<AbstractTripInformation> tripsWithSameDestiny = tripsFacade.findByDestinyStationIgnoreCase(tripInformationParams.getDestinyStation());
        List<AbstractTripInformation> tripsWithSameOrigin = tripsFacade.findByOriginStationIgnoreCaseAndDepartureDate(tripInformationParams.getOriginStation(), tripInformationParams.getTripDate());

//        List<TripDTO> singleTrips = getSingleTrips(tripInformationParams, tripsWithSameOrigin);
//        List<TripDTO> tripsWithConnection = getTripsWithConnection(tripInformationParams, tripsWithSameOrigin, tripsWithSameDestiny);
//        List<TripDTO> allTrips = ListUtils.concatLists(singleTrips, tripsWithConnection);
//        return allTrips.stream()
//                .sorted(Comparator.comparing(TripDTO::getDeparture))
//                .sorted(Comparator.comparing(TripDTO::getArrival))
//                .collect(Collectors.toList());

        return getTripWithManyConnections(tripInformationParams, tripsWithSameOrigin);
    }

    private List<TripDTO> getTripWithManyConnections(TripInformationParams tripInformationParams, List<AbstractTripInformation> tripsWithSameOrigin) {
        Map<Integer, List<AbstractTripInformation>> mapGeral = new HashMap<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);

        tripsWithSameOrigin.forEach(trip -> {
            if(trip.getDestinyStation().equals("VIX")){
                System.out.println("EU");
            }
            List<AbstractTripInformation> finalDestinies = new ArrayList<>();
            if (isDestinoFinal(trip.getDestinyStation(), tripInformationParams.getDestinyStation())) {
                finalDestinies.add(trip);
                mapGeral.put(atomicInteger.get(), finalDestinies);
                atomicInteger.incrementAndGet();
                return;
            }

            List<AbstractTripInformation> xisde = tripsFacade.findNextTripAfterOrigin(trip);
            List<AbstractTripInformation> ateQui = new ArrayList<>();
            ateQui.add(trip);
            extracted(tripInformationParams, mapGeral, atomicInteger, ateQui, xisde);
        });
        return mapGeral.values().stream()
                .map(o -> new TripDTO(tripInformationParams, o))
                .sorted(Comparator.comparing(TripDTO::getDeparture))
                .sorted(Comparator.comparing(TripDTO::getArrival))
                .collect(Collectors.toList());
    }

    private void extracted(TripInformationParams tripInformationParams, Map<Integer, List<AbstractTripInformation>> mapGeral, AtomicInteger atomicInteger, List<AbstractTripInformation> trip, List<AbstractTripInformation> xisde) {
        for (AbstractTripInformation segundaItera : xisde) {
            if (isDestinoFinal(segundaItera.getDestinyStation(), tripInformationParams.getDestinyStation())) {
                trip.add(segundaItera);
                mapGeral.put(atomicInteger.get(), trip);
                atomicInteger.incrementAndGet();
            } else {
                List<AbstractTripInformation> nextTrips = tripsFacade.findNextTripAfterOrigin(segundaItera);
                if(nextTrips.isEmpty())
                    break;
                trip.add(segundaItera);
                extracted(tripInformationParams, mapGeral, atomicInteger, trip, nextTrips);
            }
        }
    }

    private boolean isDestinoFinal(String destinyLoop, String finalDestiny) {
        return destinyLoop.equalsIgnoreCase(finalDestiny);
    }

    private List<TripDTO> getSingleTrips(TripInformationParams tripInformationParams, List<AbstractTripInformation> origins) {
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
