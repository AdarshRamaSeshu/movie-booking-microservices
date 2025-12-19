package com.TicketBooking.PaymentModule.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.TicketBooking.PaymentModule.DTO.CreateOrderRequest;
import com.TicketBooking.PaymentModule.DTO.CreateOrderResponse;
import com.TicketBooking.PaymentModule.Entity.Payment;
import com.TicketBooking.PaymentModule.Enum.GatewayType;
import com.TicketBooking.PaymentModule.Enum.PaymentStatus;
import com.TicketBooking.PaymentModule.Repository.PaymentRepository;
import com.TicketBooking.PaymentModule.Service.GatewayImplementation.PaymentGatewayResolver;


@Service
public class PaymentService {

    private PaymentGatewayResolver resolver;

    private final PaymentRepository paymentRepo;

    PaymentService(PaymentGatewayResolver resolver,PaymentRepository payemntRepo){
        this.resolver=resolver;
        this.paymentRepo=payemntRepo;
    }


    public CreateOrderResponse createOrder(CreateOrderRequest request) {

        GatewayType gatewayType=(request.getGateway()==null)
                                ? GatewayType.RAZORPAY
                                : request.getGateway();
                                
        Payment payment= new Payment();

        try{
        payment.setBookingId(request.getBookinId());
        payment.setAmount(request.getAmount());
        payment.setUserId(request.getUserId());
        payment.setCurrency("INR");
        payment.setGateway(gatewayType);
        payment.setStatus(PaymentStatus.INIT);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        paymentRepo.save(payment);
        }
        catch(Exception ex){
            payment.setStatus(PaymentStatus.FAILED);
            payment.setUpdatedAt(LocalDateTime.now());
            paymentRepo.save(payment);

            throw new RuntimeException("Payment Creation Failed");
        }


        PaymentGateway paymentGateway=resolver.resolve(gatewayType);

        return paymentGateway.createOrder(request);
        
    }

}

