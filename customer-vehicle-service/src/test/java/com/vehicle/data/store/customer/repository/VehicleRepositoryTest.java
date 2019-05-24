package com.vehicle.data.store.customer.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicle.data.store.customer.model.Customer;
import com.vehicle.data.store.customer.model.Vehicle;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryTest {

	@Autowired
	private VehicleRepository repository;
	
	@Before
	public void setup() {
		repository.deleteAll();
	}

	@Test
	public void injectedRepositoryIsNotNull() {
		assertNotNull(repository);
	}

	@Test
	public void shouldFindAllCustomers() {
		List<Vehicle> stub = getAllVehiclesStub();
		repository.saveAll(stub);

		List<Vehicle> found = repository.findAll();
		assertEquals(stub.size(), found.size());
		assertEquals(stub.get(0).getVin(), found.get(0).getVin());
		assertEquals(stub.get(0).getRegNumber(), found.get(0).getRegNumber());
		assertEquals(stub.get(0).getCustomer().getId(), found.get(0).getCustomer().getId());
		assertEquals(stub.get(0).getCustomer().getName(), found.get(0).getCustomer().getName());
		assertEquals(stub.get(0).getCustomer().getAddress(), found.get(0).getCustomer().getAddress());
	}
	
	@Test
	public void shouldFindNoVehicles() {		
		List<Vehicle> found = repository.findAll();
		
		assertTrue(found.isEmpty());
		
	}
	

	private List<Vehicle> getAllVehiclesStub() {
		List<Vehicle> mockVehicles = new ArrayList<>();
		Vehicle vehicle = new Vehicle();
		vehicle.setVin("vin123456");
		vehicle.setRegNumber("ABC123");
		vehicle.setCustomer(new Customer());
		vehicle.getCustomer().setId(1L);
		vehicle.getCustomer().setName("Name 1");
		vehicle.getCustomer().setAddress("Address 1");
		mockVehicles.add(vehicle);

		return mockVehicles;

	}

}
