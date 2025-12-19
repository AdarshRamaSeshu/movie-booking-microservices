package com.TicketBooking.PaymentModule.Entity;

import java.time.LocalDateTime;

import com.TicketBooking.PaymentModule.Enum.GatewayType;
import com.TicketBooking.PaymentModule.Enum.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "payments_Table")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    //Business reference
    private String bookingId;
    private String userId;

    //In paise
    private Long amount;

    private String currency;

    //Gateway Info like 
    private GatewayType gateway;
    private String gatewayOrderId;
    private String gatewayPaymentId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
