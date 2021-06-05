import { TestBed } from '@angular/core/testing';

import { PhoneServiceService } from './phone-service.service';

describe('PhoneServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PhoneServiceService = TestBed.get(PhoneServiceService);
    expect(service).toBeTruthy();
  });
});
