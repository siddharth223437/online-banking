import { TestBed, inject } from '@angular/core/testing';

import { LoggedinuserservicesService } from './loggedinuserservices.service';

describe('LoggedinuserservicesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoggedinuserservicesService]
    });
  });

  it('should be created', inject([LoggedinuserservicesService], (service: LoggedinuserservicesService) => {
    expect(service).toBeTruthy();
  }));
});
