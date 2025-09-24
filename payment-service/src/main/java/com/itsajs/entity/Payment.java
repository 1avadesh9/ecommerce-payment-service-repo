package com.itsajs.entity;

import java.time.LocalDateTime;

import com.itsajs.dto.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data   //consists of @Getter, @Setter, @RequiredArsConstructor, @ToString, @EqualsAndHashCode, @NotNull
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_PAYMENT", schema = "ECOMMERCE_PAYMENT_USER")
public class Payment 
{
  @Id
  @Column(name = "PAYMENT_ID")
  private String paymentId;
  
  @Column(name = "ORDER_ID")
  private String orderId;
  
  @Column(name = "CUSTOMER_ID")
  private String customerId;
  
  @Column(name = "PAYMENT_AMOUNT")
  private String paymentAmount;
  
  @Column(name = "PAYMENT_DT")
  private LocalDateTime paymentDate;
  
  @Column(name = "PAYMENT_STATUS")
  private PaymentStatus paymentStatus;
  
  @Column(name = "PAYMENT_TRANSACTION_ID")
  private String paymentTransactionId;
  
}
