package com.TicketBooking.UserModule.ExceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handle Not Found Exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex, HttpServletRequest request){
        ErrorResponse response= ErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .error("Bad Request")
                                .message(ex.getMessage())
                                .path(request.getRequestURI())
                                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    //Handle User Not Found Exceptions
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(RuntimeException ex, HttpServletRequest request){

        ErrorResponse response= ErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.NOT_FOUND.value())
                                .error("Not Found") 
                                .path(request.getRequestURI())
                                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Handle Invalid Password Exceptions
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPaaswordException(RuntimeException ex, HttpServletRequest request){

        ErrorResponse response= ErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .error("Bad Request")
                                .message(ex.getMessage())
                                .path(request.getRequestURI())
                                .build();
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Handle User Already Exisits Exceptions
    @ExceptionHandler(UserAlreadyExisitException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExisitException(RuntimeException ex, HttpServletRequest request){

        ErrorResponse response = ErrorResponse.builder()
                                 .timestamp(LocalDateTime.now())
                                 .status(HttpStatus.BAD_REQUEST.value())
                                 .error("Bad Request.")
                                 .message(ex.getMessage())
                                 .path(request.getRequestURI())
                                 .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    //Handle Global Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerSideRuntimeException(Exception ex, HttpServletRequest request){

        ErrorResponse response = ErrorResponse.builder()
                                 .timestamp(LocalDateTime.now())
                                 .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                 .error("Internal Server Error")
                                 .message(ex.getMessage())
                                 .path(request.getRequestURI())
                                 .build();

        ex.printStackTrace();

        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


