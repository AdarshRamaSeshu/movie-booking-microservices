package com.TicketBooking.PaymentModule.Service;

import org.springframework.stereotype.Service;

import com.TicketBooking.PaymentModule.DTO.CreateOrderRequest;
import com.TicketBooking.PaymentModule.DTO.CreateOrderResponse;
import com.TicketBooking.PaymentModule.Enum.GatewayType;
import com.TicketBooking.PaymentModule.Service.GatewayImplementation.PaymentGatewayResolver;


@Service
public class PaymentService {

    private PaymentGatewayResolver resolver;

    PaymentService(PaymentGatewayResolver resolver){
        this.resolver=resolver;
    }


    public CreateOrderResponse createOrder(CreateOrderRequest request) {

        GatewayType gatewayType;

        if(request.getGateway()== null){
            gatewayType=GatewayType.RAZORPAY;
        }
        else{
            gatewayType=request.getGateway();
        }

        PaymentGateway paymentGateway=resolver.resolve(gatewayType);

        return paymentGateway.createOrder(request);
        
    }

}

