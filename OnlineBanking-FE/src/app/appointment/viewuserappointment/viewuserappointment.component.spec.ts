import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewuserappointmentComponent } from './viewuserappointment.component';

describe('ViewuserappointmentComponent', () => {
  let component: ViewuserappointmentComponent;
  let fixture: ComponentFixture<ViewuserappointmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewuserappointmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewuserappointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
