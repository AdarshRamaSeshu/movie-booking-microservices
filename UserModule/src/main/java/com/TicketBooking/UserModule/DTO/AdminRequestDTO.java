package com.TicketBooking.UserModule.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AdminRequestDTO {

    private String name;
    private String email;
    private String contact;
    private String password;
    
}
