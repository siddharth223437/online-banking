import { TestBed, inject } from '@angular/core/testing';

import { VerifyuserguardService } from './verifyuserguard.service';

describe('VerifyuserguardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VerifyuserguardService]
    });
  });

  it('should be created', inject([VerifyuserguardService], (service: VerifyuserguardService) => {
    expect(service).toBeTruthy();
  }));
});
