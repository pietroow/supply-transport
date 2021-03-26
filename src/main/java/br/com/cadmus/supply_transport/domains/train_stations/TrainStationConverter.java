package br.com.cadmus.supply_transport.domains.train_stations;

import br.com.cadmus.supply_transport.exception_handler.exceptions.FetchFileException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class TrainStationConverter {

    private static final String TRAIN_STATION_PATH = "\\files\\trainStations.json";
    private static final String FETCH_FAIL_MESSAGE = "Failed to fetch train stations file.";

    public static List<TrainStation> getList() {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource classPathResource = new ClassPathResource(TRAIN_STATION_PATH);
        try {
            InputStream inputStream = classPathResource.getInputStream();
            return Arrays.asList(mapper.readValue(inputStream, TrainStation[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FetchFileException(FETCH_FAIL_MESSAGE);
    }

}

