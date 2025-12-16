package com.TicketBooking.PaymentModule.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TicketBooking.PaymentModule.DTO.CreateOrderRequest;
import com.TicketBooking.PaymentModule.DTO.CreateOrderResponse;
import com.TicketBooking.PaymentModule.Service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request){
        CreateOrderResponse response=paymentService.createOrder(request);

        return ResponseEntity.ok(response);
    }
}
