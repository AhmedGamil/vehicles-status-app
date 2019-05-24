package com.vehicle.data.store.vehicle.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.data.store.vehicle.service.VehicleStatusService;

@RestController
public class VehicleStatusController {
	
	@Autowired
	private VehicleStatusService vehicleStatusService;
	
	@RequestMapping(path = "/connectedVehicles/", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getConnectedVehicles(){		
		return vehicleStatusService.findConnectedVehicles()
				.map(v -> ok(v))
				.orElse(notFound().build());
	}

	
	
	@RequestMapping(path = "/vehiclePing/{vin}", method = RequestMethod.POST)
	public ResponseEntity<String> pingVehicle(@PathVariable(name = "vin", required = true) String vin) {
		vehicleStatusService.pingVehicle(vin);
		return ResponseEntity.accepted().build();
	}





}
