package com.vehicles.simulator.vehiclesimulator;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

	private static final String[] VEHICLES = new String[] { 
			"YS2R4X20005399401", 
			"VLUR4X20009093588",
			"VLUR4X20009048066",
			"YS2R4X20005388011",
			"YS2R4X20005387949",
			"VLUR4X20009048067",
			"YS2R4X20005387055",
	};

	@Autowired
	private VehicleStatusClient vehicleStatusClient;

	@Scheduled(fixedRate = 60 * 1000)
	public void reportCurrentTime() {
		Random random = new Random();
		int i = random.nextInt(7);
		for (int j = 0; j < i; j++) {
			vehicleStatusClient.vehiclePing(VEHICLES[random.nextInt(6)]);
		}

	}
}
