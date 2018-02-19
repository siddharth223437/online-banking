import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferbetactComponent } from './transferbetact.component';

describe('TransferbetactComponent', () => {
  let component: TransferbetactComponent;
  let fixture: ComponentFixture<TransferbetactComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransferbetactComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransferbetactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
