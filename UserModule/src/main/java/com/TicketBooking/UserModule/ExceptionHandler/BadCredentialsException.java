package com.TicketBooking.UserModule.ExceptionHandler;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(String message){
        super(message);
    }
}
