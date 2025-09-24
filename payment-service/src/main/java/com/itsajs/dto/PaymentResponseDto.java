package com.itsajs.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto 
{
	private String orderId;
	private String customerId;
	private String paymentAmount;
	private String paymentId;
	private LocalDateTime paymentDate;
	private PaymentStatus paymentStatus;
	private String paymentTransactionId;
	
	
}
