package br.com.cadmus.supply_transport.domains.companies.itrain;

import br.com.cadmus.supply_transport.exception_handler.exceptions.FetchFileException;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ITrainConverter {

    private static final String ITRAIN_PATH = "\\files\\iTrain.csv";
    private static final String FETCH_FAIL_MESSAGE = "Failed to fetch iTrain file.";

    public static List<ITrainFile> getList() {
        try {
            ClassPathResource classPathResource = new ClassPathResource(ITRAIN_PATH);
            InputStream inputStream = classPathResource.getInputStream();
            Reader inputStreamReader = new InputStreamReader(inputStream);
            return new CsvToBeanBuilder<ITrainFile>(inputStreamReader)
                    .withType(ITrainFile.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FetchFileException(FETCH_FAIL_MESSAGE);
    }

}
