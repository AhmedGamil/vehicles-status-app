package com.vehicle.data.store.vehicle.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.data.store.vehicle.model.VehicleStatus;

public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, String>{

	List<VehicleStatus> findByLastConnectionDateBetween(Date minuteAgo, Date now);
}