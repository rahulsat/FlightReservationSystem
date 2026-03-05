package com.connect.repository;

import com.connect.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByTransactionId(String transactionId);

}