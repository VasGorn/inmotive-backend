package com.vesmer.inmotive.exception;

public class InmotiveException extends RuntimeException {

    public InmotiveException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public InmotiveException(String exMessage) {
        super(exMessage);
    }

}
