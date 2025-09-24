package com.itsajs.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.itsajs.dto.OrderStatus;
import com.itsajs.dto.PaymentRequestDto;
import com.itsajs.dto.PaymentResponseDto;
import com.itsajs.dto.PaymentStatus;
import com.itsajs.entity.Payment;
import com.itsajs.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl
{
  private final PaymentRepository paymentRepository;
  private final OrderClient orderClient;
  
  public PaymentResponseDto processPayment(PaymentRequestDto paymentRequestDto)
  {
	  log.info("entered in PaymentServiceImpl.java processPayment()..."+paymentRequestDto);
	  
	  String paymentId = this.generatePaymentId();
	  
	  Payment payment = new Payment();
	  payment.setPaymentId(paymentId);
	  payment.setOrderId(paymentRequestDto.getOrderId());
	  payment.setCustomerId(paymentRequestDto.getCustomerId());
	  payment.setPaymentDate(LocalDateTime.now());
	  
	  boolean isPaymentSuccess = new Random().nextBoolean();
	  if(isPaymentSuccess)
	  {
		  payment.setPaymentStatus(PaymentStatus.SUCCESS);  
		  payment.setPaymentTransactionId(UUID.randomUUID().toString());
		  this.orderClient.updateOrderStatus(paymentRequestDto.getOrderId(), OrderStatus.CONFIRMED);
	  }
	  else
	  {
		  payment.setPaymentStatus(PaymentStatus.FAILED);
		  payment.setPaymentTransactionId("N/A");
		  this.orderClient.updateOrderStatus(paymentRequestDto.getOrderId(), OrderStatus.CANCELLED);
	  }
	  
	  payment.setPaymentAmount(paymentRequestDto.getPaymentAmount());
	  
	  this.paymentRepository.save(payment);
	  
	  PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
	  paymentResponseDto.setOrderId(payment.getOrderId());
	  //paymentResponseDto.setCustomerId(payment.getCustomerId());
	  //paymentResponseDto.setPaymentDate(payment.getPaymentDate());
	  paymentResponseDto.setPaymentId(payment.getPaymentId());
	  paymentResponseDto.setPaymentAmount(payment.getPaymentAmount());
	  paymentResponseDto.setPaymentStatus(payment.getPaymentStatus());
	  paymentResponseDto.setPaymentTransactionId(payment.getPaymentTransactionId());
	 return paymentResponseDto;
  }
  
  private String generatePaymentId()
  {
	  log.info("entered in PaymentServiceImpl.java generatePaymentId()...");
	  
	  return "pay"+UUID.randomUUID().toString().substring(0,8);
  }
  
  public PaymentResponseDto getPaymentDetailsByOrderId(String orderId)
  {
	  log.info("entered in PaymentServiceImpl.java getPaymentDetailsByOrderId()..."+orderId);
	  
	  Payment payment = this.paymentRepository.getPaymentDetailsByOrderId(orderId).orElseThrow(()-> new RuntimeException("Payment details not found by orderId : "+orderId));
	  
		/*
		 * if(payment == null) { throw new
		 * RuntimeException("Payment details not found by orderId : "+orderId); }
		 */
	  PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
	  paymentResponseDto.setOrderId(payment.getOrderId());
	  paymentResponseDto.setPaymentId(payment.getPaymentId());
	  paymentResponseDto.setPaymentAmount(payment.getPaymentAmount());
	  paymentResponseDto.setPaymentStatus(payment.getPaymentStatus());
	  paymentResponseDto.setPaymentTransactionId(payment.getPaymentTransactionId());
	  
	  return paymentResponseDto;
  }
  
  
}
