package com.TicketBooking.UserModule.Controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TicketBooking.UserModule.DTO.UserPasswordUpdateRequestDTO;
import com.TicketBooking.UserModule.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService=userService;
    }

    @PatchMapping("/update-my-password")
    public ResponseEntity<?> updatePassword(@RequestBody UserPasswordUpdateRequestDTO request, Principal principal ){

        String email=principal.getName();
        
        String response= userService.updatePassword(email, request.getOldPassword(), request.getUpdatedPassword());

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
