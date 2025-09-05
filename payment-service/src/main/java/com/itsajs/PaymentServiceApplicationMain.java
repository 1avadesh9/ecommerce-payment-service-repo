package com.itsajs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PaymentServiceApplicationMain 
{
	public static void main(String[] args)
	{
		log.info("entered in PaymentServiceApplicationMain.java main()...");
		
		SpringApplication.run(PaymentServiceApplicationMain.class, args);
		
		log.info("************PAYMENT SERVICE APPLICATION STARTED************");
	}

}
