package com.TicketBooking.PaymentModule.Entity;

import java.time.LocalDateTime;

import com.TicketBooking.PaymentModule.Enum.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments_Table")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String paymentId;

    //Business reference
    private String bookindId;
    private String userId;

    //In paise
    private Long amount;

    private String currency;

    //Gateway Info like 
    private String gateway;
    private String gatewayOrderId;
    private String gatewayPaymentId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
