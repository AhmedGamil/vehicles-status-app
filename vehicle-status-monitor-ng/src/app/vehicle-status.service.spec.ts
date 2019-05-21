import { TestBed } from '@angular/core/testing';

import { VehicleStatusService } from './vehicle-status.service';

describe('VehicleStatusService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VehicleStatusService = TestBed.get(VehicleStatusService);
    expect(service).toBeTruthy();
  });
});
