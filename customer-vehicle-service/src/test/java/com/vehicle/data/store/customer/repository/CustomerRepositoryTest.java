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

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repository;
	
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

		List<Customer> stub = getAllCustomersStub();
		repository.saveAll(stub);

		List<Customer> found = repository.findAll();
		assertEquals(stub.size(), found.size());
		assertEquals(stub.get(0).getId(), found.get(0).getId());
		assertEquals(stub.get(0).getName(), found.get(0).getName());
		assertEquals(stub.get(0).getAddress(), found.get(0).getAddress());
		assertEquals(stub.get(1).getId(), found.get(1).getId());
		assertEquals(stub.get(1).getName(), found.get(1).getName());
		assertEquals(stub.get(1).getAddress(), found.get(1).getAddress());

	}
	
	@Test
	public void shouldFindNoCustomers() {
		
		List<Customer> found = repository.findAll();
		
		assertTrue(found.isEmpty());
		
	}

	private List<Customer> getAllCustomersStub() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer());
		customers.add(new Customer());

		customers.get(0).setId(3L);
		customers.get(0).setName("Name 1");
		customers.get(0).setAddress("Address 1");

		customers.get(1).setId(5L);
		customers.get(1).setName("Name 2");
		customers.get(1).setAddress("Address 2");

		return customers;

	}

}
