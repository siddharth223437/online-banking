import { TestBed, inject } from '@angular/core/testing';

import { ActdrpdownService } from './actdrpdown.service';

describe('ActdrpdownService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ActdrpdownService]
    });
  });

  it('should be created', inject([ActdrpdownService], (service: ActdrpdownService) => {
    expect(service).toBeTruthy();
  }));
});
