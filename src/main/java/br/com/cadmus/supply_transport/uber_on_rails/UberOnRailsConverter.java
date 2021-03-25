package br.com.cadmus.supply_transport.uber_on_rails;

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
class UberOnRailsConverter {

    private static final String UBER_ON_RAILS_PATH = "\\files\\uberOnRails.json";
    private static final String FETCH_FAIL_MESSAGE = "Failed to fetch uber on rails file.";

    public static List<UberOnRailsFile> getList() {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource classPathResource = new ClassPathResource(UBER_ON_RAILS_PATH);
        try {
            InputStream inputStream = classPathResource.getInputStream();
            return Arrays.asList(mapper.readValue(inputStream, UberOnRailsFile[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FetchFileException(FETCH_FAIL_MESSAGE);
    }

}
