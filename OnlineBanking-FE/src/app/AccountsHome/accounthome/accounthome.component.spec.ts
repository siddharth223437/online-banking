import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccounthomeComponent } from './accounthome.component';

describe('AccounthomeComponent', () => {
  let component: AccounthomeComponent;
  let fixture: ComponentFixture<AccounthomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccounthomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccounthomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
