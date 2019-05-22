package com.vehicle.data.store.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.data.store.customer.model.Vehicle;
import com.vehicle.data.store.customer.repository.VehicleRepository;

@RestController
public class VehicleController {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@RequestMapping(path = "/vehicle/", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Vehicle>> getAllVehicles() {
		List<Vehicle> allVehicles = vehicleRepository.findAll();
		if(CollectionUtils.isEmpty(allVehicles)) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(allVehicles);
		}
	}

}
