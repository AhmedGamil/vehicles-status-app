package com.vehicle.data.store.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.data.store.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
