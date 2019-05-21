package com.vehicle.data.store.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerVehicleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerVehicleServiceApplication.class, args);
	}

}
