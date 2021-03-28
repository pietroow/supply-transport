package br.com.cadmus.supply_transport;

import br.com.cadmus.supply_transport.exception_handler.exceptions.FetchFileException;
import org.junit.Test;

public class ExceptionTest {

    @Test(expected = FetchFileException.class)
    public void fetchFileException(){
        throw new FetchFileException("FAILED TO LOAD FILE");
    }

}
