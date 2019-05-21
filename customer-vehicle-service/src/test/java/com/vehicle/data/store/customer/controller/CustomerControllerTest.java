package com.vehicle.data.store.customer.controller;

import static org.junit.Assert.*;
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
import com.vehicle.data.store.customer.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {
	
	@InjectMocks
	private CustomerController customerController;
	
	@Mock
	private CustomerRepository customerRepository;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void shouldGetAllCustomerswithOkResponseCode() throws Exception {
		final List<Customer> all = new ArrayList<>();
		Customer customer = new Customer();
		customer.setName("Name 1");
		all.add(customer);

		when(customerRepository.findAll()).thenReturn(all);

		mockMvc.perform(get("/customer/"))
				.andExpect(jsonPath("$.[0].name").value(customer.getName()))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldReturnNotFoundResponseCode() throws Exception {
		final List<Customer> all = new ArrayList<>();

		when(customerRepository.findAll()).thenReturn(all);

		mockMvc.perform(get("/customer/"))				
				.andExpect(status().isNotFound());
	}

}
