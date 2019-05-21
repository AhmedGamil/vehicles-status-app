package com.vehicle.data.store.vehicle.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.data.store.vehicle.model.VehicleStatus;
import com.vehicle.data.store.vehicle.repository.VehicleStatusRepository;

@RestController
public class VehicleStatusController {
	
	@Autowired
	private VehicleStatusRepository vehicleStatusRepository;
	
	@RequestMapping(path = "/connectedVehicles/")
	public ResponseEntity<List<String>> getConnectedVehicles(){
		Calendar minuteAgo = Calendar.getInstance();
		minuteAgo.add(Calendar.MINUTE, -1);
		List<String> connectedVehicles = vehicleStatusRepository.findByLastConnectionDateBetween(minuteAgo.getTime(), new Date()).stream().map(v -> v.getVin()).collect(Collectors.toList());
		return ResponseEntity.ok(connectedVehicles);
	}
	
	@RequestMapping(path = "/vehiclePing/{vin}")
	public void pingVehicle(@PathVariable(name = "vin", required = true) String vin) {
		VehicleStatus vs= vehicleStatusRepository.findById(vin).orElse(new VehicleStatus());
		vs.setVin(vin);
		vs.setLastConnectionDate(new Date());
		vehicleStatusRepository.saveAndFlush(vs);
	}

}
