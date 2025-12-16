package com.TicketBooking.PaymentModule.DTO;


import com.TicketBooking.PaymentModule.Enum.GatewayType;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private String bookinId;
    private String userId;
    private Double amount;
    private GatewayType gateway;

}
