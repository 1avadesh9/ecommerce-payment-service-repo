package com.itsajs.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.itsajs.dto.OrderStatusUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@Component
//@RequiredArgsConstructor
public class OrderClient 
{
  private final RestTemplate restTemplate;

  public OrderClient(RestTemplateBuilder restTemplateBuilder) 
  {
	this.restTemplate = restTemplateBuilder.build();
  }
  
  public void updateOrderStatus(String orderId, Enum orderStatus)
  {
	 String url = "http://127.0.0.1:8082/orders/"+orderId+"/status?status="+orderStatus;
	 
	 OrderStatusUpdateRequestDto orderStatusUpdateRequestDto 
	 = new OrderStatusUpdateRequestDto(orderId, orderStatus);
	 
	 restTemplate.postForObject(url, orderStatusUpdateRequestDto, Void.class);
	 
  }
  
  
}
