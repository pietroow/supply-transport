package br.com.cadmus.supply_transport.exception_handler.exceptions;

public abstract class UnprocessableEntityException extends RuntimeException {

    protected UnprocessableEntityException(String message) {
        super(message);
    }

}
