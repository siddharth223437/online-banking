import { TestBed, inject } from '@angular/core/testing';

import { VerifyadminguardService } from './verifyadminguard.service';

describe('VerifyadminguardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VerifyadminguardService]
    });
  });

  it('should be created', inject([VerifyadminguardService], (service: VerifyadminguardService) => {
    expect(service).toBeTruthy();
  }));
});
