package com.TicketBooking.PaymentModule.Service.GatewayImplementation;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.TicketBooking.PaymentModule.DTO.CreateOrderRequest;
import com.TicketBooking.PaymentModule.DTO.CreateOrderResponse;
import com.TicketBooking.PaymentModule.Enum.GatewayType;
import com.TicketBooking.PaymentModule.Service.PaymentGateway;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class RazorpayGateway implements PaymentGateway {

    
    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    @Override
    public GatewayType getGatewayType() {
        return GatewayType.RAZORPAY;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        try{

            RazorpayClient client= new RazorpayClient(keyId, keySecret);

            JSONObject json= new JSONObject();

            json.put("amount", request.getAmount()*100);
            json.put("currency","INR");

            Order order=client.orders.create(json);

            System.out.println(order);

            return CreateOrderResponse.builder()
            
                    .orderId(order.get("id"))
                    .amount(request.getAmount())
                    .key(keyId)
                    .build();
                    
        }catch(Exception e){
            throw new RuntimeCryptoException("Payment Request Failed to Razorpay"+ e.getMessage());
        }
    }

}


