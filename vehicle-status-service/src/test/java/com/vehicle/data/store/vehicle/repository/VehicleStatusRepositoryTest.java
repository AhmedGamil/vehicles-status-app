package com.vehicle.data.store.vehicle.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicle.data.store.vehicle.model.VehicleStatus;


@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleStatusRepositoryTest {
	
	private static final int CONNECTION_THRESHOLD_MINUTES = -1;

	@Autowired
	private VehicleStatusRepository repository;
	
	@Before
	public void setup() {
		repository.deleteAll();
	}

	@Test
	public void injectedRepositoryIsNotNull() {
		assertNotNull(repository);
	}

	@Test
	public void shouldFindAllConnectedVehicles() {

		List<VehicleStatus> connectedVehiclesStub = getListOfConnecdVehiclesStub();
		List<VehicleStatus> disconnectedVehiclesStub = getListOfDisconnecdVehiclesStub();
		repository.saveAll(connectedVehiclesStub);
		repository.saveAll(disconnectedVehiclesStub);
		
		Calendar now = Calendar.getInstance();
		Calendar aMinutesAgo = Calendar.getInstance();
		aMinutesAgo.add(Calendar.MINUTE, CONNECTION_THRESHOLD_MINUTES);
		List<VehicleStatus> found = repository.findByLastConnectionDateBetween(aMinutesAgo.getTime(), now.getTime());
		
		assertEquals(connectedVehiclesStub.size(), found.size());
		assertEquals(connectedVehiclesStub.get(0).getVin(), found.get(0).getVin());
	}
	
	@Test
	public void shouldFindNoConnectedVehicles() {

		List<VehicleStatus> disconnectedVehiclesStub = getListOfDisconnecdVehiclesStub();
		repository.saveAll(disconnectedVehiclesStub);
		
		Calendar now = Calendar.getInstance();
		Calendar aMinutesAgo = Calendar.getInstance();
		aMinutesAgo.add(Calendar.MINUTE, CONNECTION_THRESHOLD_MINUTES);
		List<VehicleStatus> found = repository.findByLastConnectionDateBetween(aMinutesAgo.getTime(), now.getTime());
		
		assertTrue(found.isEmpty());
	}

	private List<VehicleStatus> getListOfConnecdVehiclesStub() {
		List<VehicleStatus> vehiclesStatus = new ArrayList<>();
		vehiclesStatus.add(new VehicleStatus());
		
		Calendar now = Calendar.getInstance();
		
		
		vehiclesStatus.get(0).setVin("VIN1");
		vehiclesStatus.get(0).setLastConnectionDate(now.getTime());


		return vehiclesStatus;

	}
	
	
	private List<VehicleStatus> getListOfDisconnecdVehiclesStub() {
		List<VehicleStatus> vehiclesStatus = new ArrayList<>();
		vehiclesStatus.add(new VehicleStatus());

		Calendar twoMinutesAgo = Calendar.getInstance();
		twoMinutesAgo.add(Calendar.MINUTE, CONNECTION_THRESHOLD_MINUTES-1);


		vehiclesStatus.get(0).setVin("VIN2");
		vehiclesStatus.get(0).setLastConnectionDate(twoMinutesAgo.getTime());

		return vehiclesStatus;

	}

}
