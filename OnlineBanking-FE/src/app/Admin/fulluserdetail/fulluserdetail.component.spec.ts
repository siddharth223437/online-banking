import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FulluserdetailComponent } from './fulluserdetail.component';

describe('FulluserdetailComponent', () => {
  let component: FulluserdetailComponent;
  let fixture: ComponentFixture<FulluserdetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FulluserdetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FulluserdetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
