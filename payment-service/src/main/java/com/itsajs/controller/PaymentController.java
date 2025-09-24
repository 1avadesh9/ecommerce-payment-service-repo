package com.itsajs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsajs.dto.PaymentRequestDto;
import com.itsajs.dto.PaymentResponseDto;
import com.itsajs.service.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController 
{
  private final PaymentServiceImpl paymentServiceImpl;
  
  //@Autowired
  //public PaymentController(PaymentServiceImpl paymentServiceImpl)
  //{
  //  this.paymentServiceImpl = paymentServiceImpl;
  //}
  
  @PostMapping("/")
  public ResponseEntity<?> processPayment(@RequestBody PaymentRequestDto paymentRequestDto)
  {
	  log.info("entered in PaymentController.java processPayment()..."+paymentRequestDto);
	  
	  PaymentResponseDto paymentResponseDto = this.paymentServiceImpl.processPayment(paymentRequestDto);
	  
	  return ResponseEntity.status(HttpStatus.CREATED)
			  .body("Payment processed successfully for payment id : "+paymentResponseDto.getPaymentId());
  }
  
  
}
