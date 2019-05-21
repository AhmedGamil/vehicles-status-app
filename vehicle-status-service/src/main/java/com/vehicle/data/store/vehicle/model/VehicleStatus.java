package com.vehicle.data.store.vehicle.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "vehicle_status")
public class VehicleStatus {
	
	@Id
	private String vin;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastConnectionDate;
	

	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public Date getLastConnectionDate() {
		return lastConnectionDate;
	}
	
	public void setLastConnectionDate(Date lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}
	
}
