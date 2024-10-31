import { TestBed } from '@angular/core/testing';

import { MaintenanceRequestsService } from './maintenance-requests.service';

describe('MaintenanceRequestsService', () => {
  let service: MaintenanceRequestsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MaintenanceRequestsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
