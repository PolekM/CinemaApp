import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserReservationDetailsComponent } from './user-reservation-details.component';

describe('UserReservationDetailsComponent', () => {
  let component: UserReservationDetailsComponent;
  let fixture: ComponentFixture<UserReservationDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserReservationDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UserReservationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
