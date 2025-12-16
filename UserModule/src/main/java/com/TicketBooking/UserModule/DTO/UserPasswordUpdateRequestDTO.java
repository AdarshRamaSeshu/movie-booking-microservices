package com.TicketBooking.UserModule.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordUpdateRequestDTO{

    private String oldPassword;

    private String updatedPassword;

}
