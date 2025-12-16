package com.TicketBooking.UserModule.ExceptionHandler;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super(message);
    }
}
