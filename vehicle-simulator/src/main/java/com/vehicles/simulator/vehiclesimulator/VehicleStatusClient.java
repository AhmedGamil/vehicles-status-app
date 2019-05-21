package com.vehicles.simulator.vehiclesimulator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="vehiclePing", url = "http://ec2co-ecsel-1cmpxr74dz3to-1272201801.us-east-2.elb.amazonaws.com/vehiclestatus")
public interface VehicleStatusClient {
	
	@RequestMapping("/vehiclePing/{vin}")
	public void vehiclePing(@PathVariable(value = "vin") String vin);
}
