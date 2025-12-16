package com.TicketBooking.PaymentModule.Service.GatewayImplementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.TicketBooking.PaymentModule.Enum.GatewayType;
import com.TicketBooking.PaymentModule.Service.PaymentGateway;

@Component
public class PaymentGatewayResolver {

    private Map<GatewayType, PaymentGateway> gatewayMap;

    PaymentGatewayResolver(List<PaymentGateway> gateways){

        this.gatewayMap = new HashMap<>();

        for (PaymentGateway paymentGateway : gateways) {
            gatewayMap.put(paymentGateway.getGatewayType(), paymentGateway);
        }

    }

    public PaymentGateway resolve(GatewayType gatewayname){
        return gatewayMap.get(gatewayname);
    }
}
