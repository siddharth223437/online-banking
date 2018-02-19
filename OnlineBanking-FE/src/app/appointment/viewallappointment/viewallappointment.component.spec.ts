import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewallappointmentComponent } from './viewallappointment.component';

describe('ViewallappointmentComponent', () => {
  let component: ViewallappointmentComponent;
  let fixture: ComponentFixture<ViewallappointmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewallappointmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewallappointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
