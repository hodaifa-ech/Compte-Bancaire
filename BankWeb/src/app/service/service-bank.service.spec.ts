import { TestBed } from '@angular/core/testing';

import { ServiceBankService } from './service-bank.service';

describe('ServiceBankService', () => {
  let service: ServiceBankService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceBankService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
