package com.TicketBooking.PaymentModule.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TicketBooking.PaymentModule.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
