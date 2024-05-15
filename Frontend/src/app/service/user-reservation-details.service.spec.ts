import { TestBed } from '@angular/core/testing';

import { UserReservationDetailsService } from './user-reservation-details.service';

describe('UserReservationDetailsService', () => {
  let service: UserReservationDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserReservationDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
