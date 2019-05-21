import { TestBed } from '@angular/core/testing';

import { CustomerVehicleService } from './customer-vehicle.service';

describe('CustomerVehicleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CustomerVehicleService = TestBed.get(CustomerVehicleService);
    expect(service).toBeTruthy();
  });
});
