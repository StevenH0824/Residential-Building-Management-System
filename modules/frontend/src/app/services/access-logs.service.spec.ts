import { TestBed } from '@angular/core/testing';

import { AccessLogsService } from './access-logs.service';

describe('AccessLogsService', () => {
  let service: AccessLogsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccessLogsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
