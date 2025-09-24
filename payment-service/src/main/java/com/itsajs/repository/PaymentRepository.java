package com.itsajs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsajs.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String>
{
	public abstract Optional<Payment> getPaymentDetailsByOrderId(String orderId);
}
