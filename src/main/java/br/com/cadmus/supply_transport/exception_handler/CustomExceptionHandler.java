package br.com.cadmus.supply_transport.exception_handler;

import br.com.cadmus.supply_transport.exception_handler.exceptions.FetchFileException;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FetchFileException.class)
    public ResponseError handle(FetchFileException ex) {
        ex.printStackTrace();
        return new ResponseError(ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseError handle(DateTimeParseException ex) {
        ex.printStackTrace();
        return new ResponseError(ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CsvBeanIntrospectionException.class)
    public ResponseError handle(CsvBeanIntrospectionException ex) {
        ex.printStackTrace();
        return new ResponseError(ex.getMessage());
    }

}
