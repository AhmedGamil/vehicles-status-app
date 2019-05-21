package com.vehicle.data.store.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.data.store.customer.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String>{

}
