package br.com.cadmus.supply_transport.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    public static final int MAX_HOURS_TRIP_DIFFERENCE = 12;

    public static LocalDate convertFromPattern(String date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, dateTimeFormatter);
    }

    public static boolean checkMaximumHoursDifference(LocalDateTime arrivalTrip, LocalDateTime departureNextTrip) {
        LocalDateTime maximumWaitingTime = arrivalTrip.plusHours(MAX_HOURS_TRIP_DIFFERENCE);
        return isBeforeOrEquals(departureNextTrip, maximumWaitingTime);
    }

    public static boolean isBeforeOrEquals(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isBefore(dateTime2) || dateTime1.isEqual(dateTime2);
    }

    public static boolean isAfterOrEquals(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isAfter(dateTime2) || dateTime1.isEqual(dateTime2);
    }

}
