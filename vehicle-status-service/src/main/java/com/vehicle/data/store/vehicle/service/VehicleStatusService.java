package com.vehicle.data.store.vehicle.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vehicle.data.store.vehicle.model.VehicleStatus;
import com.vehicle.data.store.vehicle.repository.VehicleStatusRepository;

@Service
public class VehicleStatusService {

	@Autowired
	private VehicleStatusRepository vehicleStatusRepository;

	public Optional<List<String>> findConnectedVehicles() {
		Calendar minuteAgo = Calendar.getInstance();
		minuteAgo.add(Calendar.MINUTE, -1);
		List<String> connectedVehicles = vehicleStatusRepository
				.findByLastConnectionDateBetween(minuteAgo.getTime(), new Date()).stream().map(v -> v.getVin())
				.collect(Collectors.toList());
		return Optional.ofNullable(connectedVehicles);
	}

	public void pingVehicle(String vin) {
		Assert.hasText(vin, "vin is mandatory");
		VehicleStatus vehicleStatus = vehicleStatusRepository.findById(vin).orElse(new VehicleStatus());
		vehicleStatus.setVin(vin);
		vehicleStatus.setLastConnectionDate(new Date());
		vehicleStatusRepository.save(vehicleStatus);
	}

}
