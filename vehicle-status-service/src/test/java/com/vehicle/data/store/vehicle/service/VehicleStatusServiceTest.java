package com.vehicle.data.store.vehicle.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicle.data.store.vehicle.model.VehicleStatus;
import com.vehicle.data.store.vehicle.repository.VehicleStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleStatusServiceTest {

	private static final int CONNECTION_THRESHOLD_MINUTES = -1;

	@InjectMocks
	private VehicleStatusService service;

	@Mock
	private VehicleStatusRepository repository;

	@Before
	public void setup() {
		initMocks(this);
	}


	@Test
	public void shouldThrowIllegalArgumentExceptionWhenVinisEmpty() {
		String vin = "";
		final VehicleStatus status = new VehicleStatus();
		when(repository.findById(vin)).thenReturn(Optional.empty());
		when(repository.save(status)).thenReturn(null);

		assertThatThrownBy(() -> service.pingVehicle("")).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("vin is mandatory");

		verify(repository, times(0)).findById("");
		verify(repository, times(0)).save(status);
	}

	@Test
	public void shouldUpdateVehicleLastConnectionDateWhenPingIsCalled() {
		Date now = new Date();
		String vin = "VIN123";
		final VehicleStatus update = new VehicleStatus();
		update.setVin(vin);
		update.setLastConnectionDate(now);

		final VehicleStatus status = new VehicleStatus();
		when(repository.findById(vin)).thenReturn(Optional.of(status));
		when(repository.save(status)).thenReturn(update);

		service.pingVehicle(vin);

		assertEquals(update.getVin(), status.getVin());
		assertThat(status.getLastConnectionDate()).isInSameSecondAs(update.getLastConnectionDate());

		verify(repository, times(1)).findById(vin);
		verify(repository, times(1)).save(status);
	}

	private List<VehicleStatus> getListOfConnecdVehiclesStub() {
		List<VehicleStatus> vehiclesStatus = new ArrayList<>();
		vehiclesStatus.add(new VehicleStatus());
		Calendar now = Calendar.getInstance();

		vehiclesStatus.get(0).setVin("VIN1");
		vehiclesStatus.get(0).setLastConnectionDate(now.getTime());

		return vehiclesStatus;

	}

}
