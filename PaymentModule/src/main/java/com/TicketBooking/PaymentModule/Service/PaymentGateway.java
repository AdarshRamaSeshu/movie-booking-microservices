package com.TicketBooking.PaymentModule.Service;

import com.TicketBooking.PaymentModule.DTO.CreateOrderRequest;
import com.TicketBooking.PaymentModule.DTO.CreateOrderResponse;
import com.TicketBooking.PaymentModule.Enum.GatewayType;

public interface PaymentGateway {

    GatewayType getGatewayType();

    CreateOrderResponse createOrder(CreateOrderRequest request);


}
