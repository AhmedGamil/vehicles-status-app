package com.vehicle.data.store.customer.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vehicle.data.store.customer.model.Customer;
import com.vehicle.data.store.customer.model.Vehicle;
import com.vehicle.data.store.customer.repository.VehicleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleControllerTest {
	
	@InjectMocks
	private VehicleController controller;
	
	@Mock
	private VehicleRepository repository;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void shouldGetAllCustomerswithOkResponseCode() throws Exception {
		final List<Vehicle> mockVehicles = new ArrayList<>();
		Vehicle vehicle = new Vehicle();
		vehicle.setVin("vin123456");
		vehicle.setRegNumber("ABC123");
		vehicle.setCustomer(new Customer());
		vehicle.getCustomer().setId(1L);
		vehicle.getCustomer().setName("Name 1");
		vehicle.getCustomer().setAddress("Address 1");
		mockVehicles.add(vehicle);

		when(repository.findAll()).thenReturn(mockVehicles);

		mockMvc.perform(get("/vehicle/"))
				.andExpect(jsonPath("$.[0].vin").value(vehicle.getVin()))
				.andExpect(jsonPath("$.[0].regNumber").value(vehicle.getRegNumber()))
				.andExpect(jsonPath("$.[0].customer.id").value(vehicle.getCustomer().getId()))
				.andExpect(jsonPath("$.[0].customer.name").value(vehicle.getCustomer().getName()))
				.andExpect(jsonPath("$.[0].customer.address").value(vehicle.getCustomer().getAddress()))
				.andExpect(status().isOk());
		
		verify(repository, times(1)).findAll();
	}
	
	@Test
	public void shouldReturnEmptyListWithOkResponseCode() throws Exception {
		final List<Vehicle> all = new ArrayList<>();

		when(repository.findAll()).thenReturn(all);

		mockMvc.perform(get("/vehicle/"))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isEmpty())
				.andExpect(status().isOk());
		
		verify(repository, times(1)).findAll();
	}

}
