package com.vehicle.data.store.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.data.store.customer.model.Customer;
import com.vehicle.data.store.customer.repository.CustomerRepository;



@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(path = "/customer/", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomers = customerRepository.findAll();		
		if(CollectionUtils.isEmpty(allCustomers)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(allCustomers);
	}

}
