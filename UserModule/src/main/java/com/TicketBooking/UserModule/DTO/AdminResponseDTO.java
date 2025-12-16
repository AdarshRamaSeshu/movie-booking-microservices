package com.TicketBooking.UserModule.DTO;

import java.time.LocalDateTime;

import com.TicketBooking.UserModule.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO {
/* This class is used for User Response */
    private Long id;
    private String name;
    private String email;
    private String contact;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
