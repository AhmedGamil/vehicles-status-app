package com.vehicle.data.store.vehicle.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vehicle.data.store.vehicle.service.VehicleStatusService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleStatusControllerTest {

	@InjectMocks
	private VehicleStatusController controller;

	@Mock
	private VehicleStatusService service;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void shouldReturnAllConnectedVehiclesWithOkResponseCode() throws Exception {
		final List<String> mock = Arrays.asList("VIN123");

		when(service.findConnectedVehicles()).thenReturn(Optional.of(mock));

		mockMvc.perform(get("/connectedVehicles/")).andExpect(jsonPath("$.[0]").value(mock.get(0)))

				.andExpect(status().isOk());

		verify(service, times(1)).findConnectedVehicles();
	}

	@Test
	public void shouldReturnEmptyListWithOkResponseCode() throws Exception {
		final List<String> mock = new ArrayList<>();

		when(service.findConnectedVehicles()).thenReturn(Optional.of(mock));

		mockMvc.perform(get("/connectedVehicles/")).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isEmpty()).andExpect(status().isOk());

		verify(service, times(1)).findConnectedVehicles();
	}

	@Test
	public void shouldReturnotFoundResponseCode() throws Exception {
		when(service.findConnectedVehicles()).thenReturn(Optional.empty());

		mockMvc.perform(get("/connectedVehicles/")).andExpect(status().isNotFound());

		verify(service, times(1)).findConnectedVehicles();
	}

	@Test
	public void shouldReturnAcceptedResponseCodewhenPingVehicle() throws Exception {
		final String vin = "VIN123";

		mockMvc.perform(post("/vehiclePing/" + vin)).andExpect(status().isAccepted());

	}

}
